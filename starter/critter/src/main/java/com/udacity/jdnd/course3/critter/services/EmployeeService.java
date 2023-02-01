package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.dao.EmployeeDAO;
import com.udacity.jdnd.course3.critter.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;

    public Employee saveEmployee(Employee employee){
        employeeDAO.save(employee);
        return employee;
    }

    public EmployeeDTO findEmployeeById(long employeeId) {
         return convertEmployeeToDTO(employeeDAO.findById(employeeId).get());
    }
    public EmployeeDTO convertEmployeeToDTO(Employee employee){
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getSkills(), employee.getDaysAvailable());
    }

    public void changeEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeDAO.findById(employeeId).get();
        if(employee != null){
            employee.setDaysAvailable(daysAvailable);
            employeeDAO.save(employee);
        }
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {
        List<EmployeeDTO> employees = new ArrayList<>();
        for(Employee employee : employeeDAO.findAll()){
            Set<EmployeeSkill> tempSetSkills = new HashSet<>(employee.getSkills());
            tempSetSkills.retainAll(employeeRequestDTO.getSkills());
            LocalDate dt = LocalDate.parse(employeeRequestDTO.getDate().toString());
            if(tempSetSkills.size() == employeeRequestDTO.getSkills().size() && employee.getDaysAvailable().contains(dt.getDayOfWeek())){
                employees.add(new EmployeeDTO(employee.getId(), employee.getName(), employee.getSkills(), employee.getDaysAvailable()));
            }
        }
        return employees;
    }
}
