package com.ntg.organization.organization.service;

import java.util.List;
import java.util.Optional;

import com.ntg.organization.organization.entity.Department;
import com.ntg.organization.organization.respository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntg.organization.organization.entity.Employee;
import com.ntg.organization.organization.respository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Employee> getAllEmployee() {
		return (List<Employee>) employeeRepository.findAll();
	}

	public Employee createNewEmployee(Employee newEmp) {
		if (newEmp != null) {
			return employeeRepository.save(newEmp);
		}
		return null;
	}

	public boolean deleteEmployeeById(Long id) {
		if (id != null) {
			employeeRepository.deleteById(id);
			return true;
		}

		return false;
	}

	public Employee getEmployeeByName(String name, String email) {
		return employeeRepository.findByNameAndEmail(name, email);
	}

	public Employee setDepartment(Long empId, Long depId){
		Optional<Employee> optEmp = employeeRepository.findById(empId);
		Employee emp;
		Department dep;
		if (optEmp.isPresent()){
			emp = optEmp.get();
			Optional <Department> optDep = departmentRepository.findById(depId);
			if (optDep.isPresent()) dep = optDep.get();
			else return null;
			emp.setDepartment(dep);
			return employeeRepository.save(emp);
		}
		return null;
	}

}
