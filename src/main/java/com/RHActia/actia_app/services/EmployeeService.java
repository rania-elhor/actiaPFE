package com.RHActia.actia_app.services;

import com.RHActia.actia_app.model.Employee;
import com.RHActia.actia_app.model.Gender;
import com.RHActia.actia_app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service

public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        List<Employee> Employee = new ArrayList<>();
        employeeRepository.findAll().forEach(e -> Employee.add(e));
        return Employee;
    }
    public Employee addEmployee(Employee employee) {
        if (employeeExists(employee.getEmail())) {
            throw new IllegalArgumentException("Employee already exists");
        }
        return employeeRepository.save(employee);
    }

    public boolean employeeExists(String email) {
        Employee existingEmployee = employeeRepository.findByEmail(email);
        return existingEmployee != null;
    }


    public List<Employee> addAllEmployees(List<Employee> employees) {
        List<Employee> newEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (!employeeExists(employee.getEmail())) {
                newEmployees.add(employee);
            }
        }
        return employeeRepository.saveAll(newEmployees);
    }


    public Employee getEmployeeByID(int id) {
        return employeeRepository.findById(id).orElse(null);

    }

    public Employee getEmployeeByName(String firstname) {
        return employeeRepository.findByFirstname(firstname);
    }
    public List<Employee> getEmployeesByGender(Gender gender) {
        return employeeRepository.findByGender(gender);
    }


    /*public List<Employee> getEmployeesByBirthDateRange(Date  startDate, Date  endDate) {
        return employeeRepository.findByBirthDateBetween(startDate, endDate);
    }
    public List<Employee> getEmployeesByBirthYear(int year) {
        List<Employee> allEmployees = employeeRepository.findAll(); // Assuming findAll() returns all employees
        return allEmployees.stream()
                .filter(employee -> getYearFromDate(employee.getBirthDate()) == year)
                .collect(Collectors.toList());
    }*/

    private int getYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public Employee updateEmployee(Employee employee) {
        Employee existingEMP = employeeRepository.findById(employee.getId()).orElse(null);
        if(existingEMP == null) {
            throw new IllegalArgumentException("Employee not found with ID: " + employee.getId());
        } else {
            existingEMP.setFirstname(employee.getFirstname());
            existingEMP.setLastname(employee.getLastname());
            existingEMP.setEmail(employee.getEmail());
            existingEMP.setGender(employee.getGender());
            existingEMP.setPhoto(employee.getPhoto());
            return employeeRepository.save(existingEMP);
        }
    }





    public boolean deleteEmployeeByID(int id) {
        Employee existingEMP = employeeRepository.getById(id);
        if (existingEMP != null){
            employeeRepository.deleteById(id);
            return true;
        }
        return false;

    }
}
