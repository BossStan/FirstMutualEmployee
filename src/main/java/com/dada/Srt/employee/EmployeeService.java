package com.dada.Srt.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {
    private static final Logger logger= LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepository employeeRepository;


    public List<com.dada.Srt.employee.Employee> getEmployee(){

        return employeeRepository.findAll();
    }

    public void addEmployee(com.dada.Srt.employee.Employee employee) {

        Optional<com.dada.Srt.employee.Employee> employeeByAddress=employeeRepository.findEmployeeByAddress(employee.getAddress());
        if (employeeByAddress.isPresent()){
            logger.error("Duplication of records when creating new Employee");

            throw new IllegalStateException("address exists");

        }
        employeeRepository.save(employee);
        logger.info(" Employee" +employee.getName()+"record created successfully ");
    }

    public void deleteEmployee(Integer employeeId) {

        boolean avail=employeeRepository.existsById(employeeId);
        if(!avail){
            logger.error("No such employee record with "+employeeId);
            throw  new IllegalStateException("employee with Id not available");
        }
        employeeRepository.deleteById(employeeId);
        logger.error("employee with "+employeeId+" has been deleted");

    }

    @Transactional
    public void updateEmployeee(int employeeId, String name, String address) {
        com.dada.Srt.employee.Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->new IllegalStateException("employee with employee id "+employeeId+" does not exist"));

        if(name !=null&& name.length()>0&& !Objects.equals(employee.getName(),name)){
            employee.setName(name);
            logger.info("updated successfully, new name is  "+name);
        }
        if(address !=null&&address.length()>0 && !Objects.equals(employee.getAddress(),address)){
            employee.setAddress(address);
            logger.info("updated successfully, new address is  "+address);
        }


    }
}



