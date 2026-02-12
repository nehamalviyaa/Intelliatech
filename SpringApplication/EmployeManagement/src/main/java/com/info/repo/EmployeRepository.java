package com.info.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.info.entity.Employe;

@Repository
public interface EmployeRepository  extends JpaRepository<Employe , Integer>{

	Optional<Employe> findByEmail(String email);

	
}
