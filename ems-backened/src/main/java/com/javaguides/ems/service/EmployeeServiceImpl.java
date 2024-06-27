package com.javaguides.ems.service;

import com.javaguides.ems.dto.EmployeeDto;
import com.javaguides.ems.entity.Employee;
import com.javaguides.ems.exception.ResourceNotFoundException;
import com.javaguides.ems.mapper.EmployeeMapper;
import com.javaguides.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
   @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto creteEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    // Get By Id
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
      Employee employee= employeeRepository.findById(employeeId).orElseThrow(()->
               new  ResourceNotFoundException("Employee is not Exist with given id:"+employeeId));
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
        return employeeDto;
    }

    // Get All
    @Override
    public List<EmployeeDto> geAll() {
        List<Employee> employees=employeeRepository.findAll();
        return  employees.stream().map((employee) ->EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    //Update
    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
       Employee employee = employeeRepository.findById(employeeId)
               .orElseThrow(()-> new ResourceNotFoundException("Employee is Not Available"+ employeeId));
       employee.setFirstName(updateEmployee.getFirstName());
       employee.setLastName(updateEmployee.getLastName());
       employee.setEmail(updateEmployee.getEmail());
      Employee employee1 =  employeeRepository.save(employee);
      EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee1);

        return employeeDto;
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee is not Available"+ id));
        employeeRepository.deleteById(id);
    }




}
