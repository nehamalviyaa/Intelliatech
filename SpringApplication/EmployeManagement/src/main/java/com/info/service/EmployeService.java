package com.info.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.info.dto.EmployeDTO;
import com.info.dto.EmployeResponseDTO;
import com.info.entity.Employe;
import com.info.exception.ResourceNotFoundException;
import com.info.repo.EmployeRepository;

@Service
public class EmployeService {

    private final EmployeRepository repo;
    private final BCryptPasswordEncoder passwordEncoder;

    public EmployeService(EmployeRepository repo,
                          BCryptPasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

   
    //save
    @Transactional
    public EmployeDTO saveEmploye(EmployeDTO dto) {

        Employe emp = new Employe();
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setPassword(passwordEncoder.encode(dto.getPassword()));
        emp.setDepartment(dto.getDepartment());
        emp.setSalary(dto.getSalary());

        repo.save(emp);
        return dto;
    }
 
    
   //findALL
    public Page<EmployeDTO> findAll(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Employe> empPage = repo.findAll(pageable);

        return empPage.map(emp -> {
            EmployeDTO dto = new EmployeDTO();
           
            dto.setName(emp.getName());
            dto.setEmail(emp.getEmail());
            dto.setDepartment(emp.getDepartment());
            dto.setSalary(emp.getSalary());
            return dto;
        });
    }

   
    //FindById
    public EmployeDTO findById(Integer id) {
        Employe emp = repo.findById(id)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Employe not found with id : " + id)
                );

        EmployeDTO dto = new EmployeDTO();
        dto.setName(emp.getName());
        dto.setEmail(emp.getEmail());
        dto.setDepartment(emp.getDepartment());
        dto.setSalary(emp.getSalary());
        dto.setPassword(emp.getPassword());

        return dto;
    }
    
    //Update
    @Transactional
    public EmployeResponseDTO updateEmploye(Integer id, EmployeResponseDTO dto) {

        Employe emp = repo.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Employe not found with id : " + id)
                );

        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setDepartment(dto.getDepartment());
        emp.setSalary(dto.getSalary());

        repo.save(emp);
        return dto;
    }

   
    //Delete
    @Transactional
    public void delete(Integer id) {
        Employe emp = repo.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Employe not exists with id : " + id)
                );

        repo.delete(emp);
    }

}
