package com.dada.Srt.employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {
    private static final Logger logger= LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private final com.dada.Srt.employee.EmployeeService employeeService;

    public EmployeeController(com.dada.Srt.employee.EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping()
    public List<com.dada.Srt.employee.Employee> employees(){
    logger.info("@@@@@@@@@ reading employees  ");
        return employeeService.getEmployee();
    }
    @PostMapping
    public void registerEmployee(@RequestBody com.dada.Srt.employee.Employee employee){

        if (employee==null){
            throw new IllegalCallerException("employee cannot be null");
        }

        logger.info("@@@@@@@@@@ creating employee record");
        employeeService.addEmployee(employee);
    }

    @DeleteMapping(path = "{employeeId}")
    public void deleteEmployee(@PathVariable( "employeeId") Integer employeeId){
        if(employeeId==null){
            throw new IllegalArgumentException("employee Id cannot be null ");
        }
        logger.info("deleting employee with id "+ employeeId);
        employeeService.deleteEmployee(employeeId);
    }
    @PutMapping(path = "{employeeId}")
    public void updateEmployee(
            @PathVariable("employeeId")int employeeId,
            @RequestParam(required = false)String name,
            @RequestParam(required = false)String address){

        System.out.println(employeeId+name+address);
        employeeService.updateEmployeee(employeeId,name,address);

    }
}

