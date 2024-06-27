package com.javaguides.ems.service;

import com.javaguides.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto creteEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

  List<EmployeeDto>geAll();

  EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee);

  void deleteEmployee(Long id);

}
