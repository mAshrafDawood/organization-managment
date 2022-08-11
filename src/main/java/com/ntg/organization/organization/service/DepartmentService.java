package com.ntg.organization.organization.service;

import com.ntg.organization.organization.entity.Department;
import com.ntg.organization.organization.respository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List <Department> getAllDepartment() {return (List<Department>) departmentRepository.findAll();};

    public Department createNewDepartment(Department newDept){
        if (newDept != null){
            return departmentRepository.save(newDept);
        }
        return null;
    }

    public boolean deleteDepartmentById(Long id){
        if (id != null){
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Department getDepartmentByName(String name){
        return departmentRepository.findByName(name);
    }
}
