package com.info.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.info.dto.CartItemDTO;
import com.info.dto.CartResponseDTO;
import com.info.entity.Cart;
import com.info.entity.CartItems;
import com.info.entity.CartStatus;
import com.info.entity.Product;
import com.info.entity.User;
import com.info.exception.ResourceNotFoundException;
import com.info.repo.CartItemsRepository;
import com.info.repo.CartRepository;
import com.info.repo.ProductRepository;


@Service
@Transactional
public class CartService {

    private final CartRepository cartRepo;
    private final CartItemsRepository itemRepo;
    private final ProductRepository productRepo;

    public CartService(CartRepository cartRepo, CartItemsRepository itemRepo, ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.itemRepo = itemRepo;
        this.productRepo = productRepo;
    }

    // ADD ITEM 
    public void addItem(Integer userId, Integer productId, int qty) {
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0");

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Cart cart = cartRepo.findByUserAndStatus(new User(userId), CartStatus.Active)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUser(new User(userId));
                    c.setStatus(CartStatus.Active);
                    return cartRepo.save(c);
                });

        CartItems item = itemRepo.findByCartAndProduct(cart, product)
                .orElseGet(() -> {
                    CartItems ci = new CartItems();
                    ci.setCart(cart);
                    ci.setProduct(product);
                    ci.setQuantity(0);
                    return ci;
                });

        item.setQuantity(item.getQuantity() + qty);
        itemRepo.save(item);
    }

    // VIEW CART
    public CartResponseDTO viewCart(Integer userId) {

        Cart cart = cartRepo.findByUserAndStatus(new User(userId), CartStatus.Active)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        double total = 0;

        List<CartItemDTO> items = cart.getItems().stream().map(i -> {
            CartItemDTO dto = new CartItemDTO();
            dto.setProductId(i.getProduct().getId());
            dto.setProductName(i.getProduct().getName());
            dto.setPrice(i.getProduct().getPrice());
            dto.setQuantity(i.getQuantity());
            return dto;
        }).toList();

        
        for (CartItems i : cart.getItems()) {
            total += i.getProduct().getPrice() * i.getQuantity();
        }

        
        CartResponseDTO response = new CartResponseDTO();
        response.setCartId(cart.getId());
        response.setUserId(userId);
        response.setItems(items);
        response.setTotalAmount(total);

        return response;
    }

    // REMOVE ITEM
    public void removeItem(Integer userId, Integer productId) {
        Cart cart = cartRepo.findByUserAndStatus(new User(userId), CartStatus.Active)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        CartItems item = itemRepo.findByCartAndProduct(cart, new Product(productId))
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        itemRepo.delete(item);
    }
}
