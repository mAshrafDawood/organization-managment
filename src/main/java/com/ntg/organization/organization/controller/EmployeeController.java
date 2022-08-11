package com.ntg.organization.organization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ntg.organization.organization.entity.Employee;
import com.ntg.organization.organization.exception.EmployeeNotFoundException;
import com.ntg.organization.organization.exception.ErrorResponse;
import com.ntg.organization.organization.exception.Errors;
import com.ntg.organization.organization.service.EmployeeService;

@RestController
@RequestMapping("/emp/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/all")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@GetMapping(value = "/getByName/{name}/{email}")
	public Employee getEmployeeByName(@PathVariable String name,
			@PathVariable String email) throws EmployeeNotFoundException{
		Employee emp = employeeService.getEmployeeByName(name, email);

		if (emp == null) {
			throw new EmployeeNotFoundException();
			/*
			 * return new ResponseEntity<>( new
			 * ErrorResponse(Errors.EMPPLOYEE_NOT_FOUND.getCode(),
			 * Errors.EMPPLOYEE_NOT_FOUND.getMessage()), HttpStatus.BAD_REQUEST);
			 */
		}

		return emp;
	}

	@PostMapping(value = "/add")
	public Employee createNewEmployee(@RequestBody Employee newEmp) {
		return employeeService.createNewEmployee(newEmp);
	}

	@DeleteMapping(value = "/del/{empId}")
	public boolean deleteEmployee(@PathVariable(value = "empId") Long id) {
		return employeeService.deleteEmployeeById(id);
	}

	@PostMapping(value = "empDep/{empId}/{depId}")
	public Employee setDepartment(@PathVariable Long empId, @PathVariable Long depId){
		return employeeService.setDepartment(empId, depId);
	}

}
