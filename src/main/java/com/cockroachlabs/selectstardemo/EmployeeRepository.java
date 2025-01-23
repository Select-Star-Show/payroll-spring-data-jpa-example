package com.cockroachlabs.selectstardemo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

	List<Employee> findByRole(String role);

	List<Employee> findByNameContaining(String partial);

	@Query(value = """
			SELECT *
			FROM employee
			AS OF SYSTEM TIME '-30s'
			WHERE role = (:role)
			""", nativeQuery = true)
	List<Employee> findRecentByRole(String role);
}
