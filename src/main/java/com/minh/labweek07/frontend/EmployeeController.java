package com.minh.labweek07.frontend;

import com.minh.labweek07.backend.models.Employee;
import com.minh.labweek07.backend.models.EmployeeStatus;
import com.minh.labweek07.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("/add-employee")
    public String addEmployee(Model model) {
        model.addAttribute("EmployeeStatus", EmployeeStatus.values());
        return "admin/add-employee";
    }
    @PostMapping("/handle-add-employee")
    public String handleAddEmployee(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("address") String address , @RequestParam("phone") String phone,@RequestParam("dob") String dob,@RequestParam int status) {
        Employee Employee = new Employee();
        Employee.setFullName(name);
        Employee.setAddress(address);
        Employee.setEmail(email);
        Employee.setPhone(phone);
        Employee.setDob(dob);
        Employee.setEmployeeStatus(EmployeeStatus.getByCode(status));
        employeeRepository.save(Employee);
        return "redirect:/admin/employee";
    }
    @PostMapping ("/edit-employee")
    public String editEmployee(@RequestParam("id") long id,@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("address") String address ,@RequestParam("phone") String phone,@RequestParam("dob") String dob,@RequestParam("status") int status) {
        Employee Employee = employeeRepository.findById(id).get();
        Employee.setFullName(name);
        Employee.setAddress(address);
        Employee.setEmail(email);
        Employee.setPhone(phone);
        Employee.setDob(dob);
        Employee.setEmployeeStatus(EmployeeStatus.getByCode(status));
        employeeRepository.save(Employee);
        return "redirect:/admin/employee";
    }
    @GetMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/admin/employee";
    }
    @GetMapping("/update-employee")
    public String updateEmployee(@RequestParam("id") Long id, Model model) {
        Employee Employee = employeeRepository.findById(id).get();
        model.addAttribute("employee", Employee);

        return "admin/update-employee";
    }
    @GetMapping("/search-employee")
    public String searchEmployee(@RequestParam("query") String query, Model model) {
        List<Employee> Employee;
        Employee=employeeRepository.findEmployeesByFullNameContainingIgnoreCase(query);
        if(Employee.size()==0){
            Employee=employeeRepository.findEmployeesByEmailIsContainingIgnoreCase(query);
            model.addAttribute("employees", Employee);
        }

        if(Employee.size()==0){
            Employee=employeeRepository.findEmployeesByPhoneIsContainingIgnoreCase(query);
            model.addAttribute("employees", Employee);
        }
        model.addAttribute("employees", Employee);
        return "admin/employee";
    }
   
}
