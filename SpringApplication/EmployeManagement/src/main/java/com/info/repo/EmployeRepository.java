package com.info.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.dto.EmployeDTO;
import com.info.dto.EmployeResponseDTO;
import com.info.entity.Employe;

@Repository
public interface EmployeRepository  extends JpaRepository<Employe , Integer>{

	Optional<Employe> findByEmail(String email);
	
	Optional<Employe> findTopByOrderBySalaryDesc();
	
	List<Employe> getEmployeByDepartment(String department);

	
	List<Employe>findAll();
	
	 List<Employe> findBySalaryGreaterThan(Double amount);
}
