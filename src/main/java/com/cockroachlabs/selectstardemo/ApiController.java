package com.cockroachlabs.selectstardemo;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	private final EmployeeRepository repository;

	public ApiController(EmployeeRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/api/employees")
	List<Employee> findAll() {
		return repository.findAll();
	}

	@GetMapping("/api/employees/{id}")
	Employee findById(@PathVariable UUID id) {
		return repository.findById(id) //
				.orElseThrow(() -> new RuntimeException("Can't find Employee with id '" + id + "'"));
	}

	@GetMapping("/api/employees/role/{role}")
	List<Employee> findByRole(@PathVariable String role) {
		return repository.findByRole(role);
	}

	@GetMapping("/api/employees/name/{search}")
	List<Employee> findByName(@PathVariable String search) {
		return repository.findByNameContaining(search);
	}

	@GetMapping("/api/employees/recent/role/{role}")
	List<Employee> findRecentByRole(@PathVariable String role) {
		return repository.findRecentByRole(role);
	}

	@PostMapping( "/api/employees")
	Employee create(@RequestBody Employee employee) {
		return repository.save(employee);
	}

	@ResponseStatus(code= HttpStatus.NO_CONTENT)
	@DeleteMapping( "/api/employees/{id}")
	void delete(@PathVariable UUID id) {
		repository.deleteById(id);
	}
}
