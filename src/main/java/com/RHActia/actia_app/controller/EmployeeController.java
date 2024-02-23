package com.RHActia.actia_app.controller;

import com.RHActia.actia_app.model.Employee;
import com.RHActia.actia_app.model.Gender;
import com.RHActia.actia_app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/emp")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService ;

    // add new employee
    @PostMapping ("/addEmployee")
    public Employee addEmployee (@RequestBody Employee employee ) {
        return employeeService.addEmployee(employee);
    }
    @GetMapping("/AllEmployees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    // add more than 1 employee
    @PostMapping("/addEmployees")
    public List<Employee> addAllEmployee (@RequestBody List<Employee> employees){

        return employeeService.addAllEmployees(employees);
    }

    //GEt employee by Id
    @GetMapping("/getEmployeeByID/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return employeeService.getEmployeeByID(id);
    }
    // Get employee by name
    @GetMapping("/getEmployeeByName/{firstname}")
    public Employee getEmployeeByName(@PathVariable String firstname ){
        return  employeeService.getEmployeeByName(firstname);
    }
    @GetMapping("/searchByGender/{gender}")
    public List<Employee> searchByGender(@PathVariable Gender gender) {
        return employeeService.getEmployeesByGender(gender);
    }

    /*@GetMapping("/searchByBirthDateRange/{startDate}/{endDate}")
    public List<Employee> searchByBirthDateRange(
            @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date  startDate,
            @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date  endDate) {

        return employeeService.getEmployeesByBirthDateRange(startDate, endDate);
    }
    @GetMapping("/searchByBirthYear/{year}")
    public List<Employee> searchByBirthYear(@PathVariable int year) {
        return employeeService.getEmployeesByBirthYear(year);
    }*/
    //Update Employee
    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employee.setId(id); // Set the ID from the path variable to the employee object
        return employeeService.updateEmployee(employee);
    }
    //Delete employee
    @DeleteMapping("/deleteEmployeeById/{id}")
    public boolean deleteEmployeeByID(@PathVariable int id){
        return employeeService.deleteEmployeeByID(id);
    }
}
