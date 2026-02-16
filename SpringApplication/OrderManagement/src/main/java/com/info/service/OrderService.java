package com.info.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.info.dto.OrderResponseDTO;
import com.info.entity.Cart;
import com.info.entity.CartItems;
import com.info.entity.CartStatus;
import com.info.entity.Order;
import com.info.entity.OrderItems;
import com.info.entity.Product;
import com.info.entity.User;
import com.info.exception.ResourceNotFoundException;
import com.info.exception.StockNotAvailableException;
import com.info.repo.CartRepository;
import com.info.repo.OrderRepository;
import com.info.repo.ProductRepository;


@Service
@Transactional
public class OrderService {

    private final CartRepository cartRepo;
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final DiscountService discountService;

    public OrderService(CartRepository cartRepo, OrderRepository orderRepo,
                        ProductRepository productRepo, DiscountService discountService) {
        this.cartRepo = cartRepo;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.discountService = discountService;
    }

    // PLACE ORDER
    public OrderResponseDTO placeOrder(Integer userId, Integer productId) {

        Cart cart = cartRepo.findByUserAndStatus(
                new User(userId), CartStatus.Active
        ).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        CartItems cartItem = cart.getItems().stream()
                .filter(ci -> ci.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Product not found in cart"));

        Product product = cartItem.getProduct();

        if (product.getStock() < cartItem.getQuantity())
            throw new StockNotAvailableException("Out of stock: " + product.getName());

        // create order
        Order order = new Order();
        order.setUser(new User(userId));
        order.setReference("ORD-" + System.currentTimeMillis());

        // update stock
        product.setStock(product.getStock() - cartItem.getQuantity());

        // order item
        OrderItems oi = new OrderItems();
        oi.setOrder(order);
        oi.setProduct(product);
        oi.setQuantity(cartItem.getQuantity());
        oi.setPrice(product.getPrice());

        order.getItems().add(oi);

        double total = product.getPrice() * cartItem.getQuantity();
        total = discountService.applyDiscount(total);
        order.setTotalAmount(total);

        // remove ordered item from cart
        cart.getItems().remove(cartItem);

        // if cart empty → mark ordered
        if (cart.getItems().isEmpty()) {
            cart.setStatus(CartStatus.ordered);
        }

        orderRepo.save(order);

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setReference(order.getReference());
        dto.setTotalAmount(order.getTotalAmount());

        return dto;
    }

    
    // ORDER HISTORY
    public List<OrderResponseDTO> orderHistory(Integer userId) {

        List<Order> orders = orderRepo.findByUserId(userId);

        return orders.stream()
                .map(o -> {
                    OrderResponseDTO dto = new OrderResponseDTO();
                    dto.setReference(o.getReference());
                    dto.setTotalAmount(o.getTotalAmount());
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
