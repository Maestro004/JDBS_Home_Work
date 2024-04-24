package Java13;

import Java13.model.Employee;
import Java13.model.Job;
import Java13.service.EmployeeService;
import Java13.service.JobService;
import Java13.service.serviceImpl.EmployeeServiceImpl;
import Java13.service.serviceImpl.JobServiceImpl;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        JobService jobService = new JobServiceImpl();
        jobService.createJobTable();
        System.out.println("save job");
        Job job = new Job();
        job.setPosition("mentor");
        job.setProfession("java");
        job.setDescription("krasivay");
        job.setExperience(5);
        jobService.addJob(job);
        System.out.println(jobService.getJobById(10L));
        System.out.println(jobService.getJobByEmployeeId(1L));
        jobService.deleteDescriptionColumn();

        employeeService.createEmployee();
        employeeService.addEmployee(new Employee("bektur", "temirbekov", 28, "bekmail.com", 1L));
        employeeService.dropTable();
        employeeService.updateEmployee(1L,new Employee("bektur", "temirbekov", 18, "bgmail.com", 1L));
          employeeService.cleanTable();
        System.out.println(employeeService.getAllEmployees());
        System.out.println(employeeService.findByEmail("bekmail.com"));
        System.out.println(employeeService.getEmployeeById(3L));
        System.out.println(employeeService.getEmployeeByPosition("mentor"));
    }
}
