package com.cockroachlabs.selectstardemo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

	List<Employee> findByRole(String role);

	@Query(value = """
            SELECT *
            FROM employee
            WHERE role = :role
            AS OF SYSTEM TIME '-5s'
            """, nativeQuery = true)
	List<Employee> findByRoleWithFollowerRead(String role);

}
