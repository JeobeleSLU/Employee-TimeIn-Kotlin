package Managers;

import org.group1.BaseClasses.Employee;
import org.group1.Loader.SingletonEmployeeLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class EmployeeManager {
    HashMap<String,Employee>employeeList;

    public EmployeeManager() {
        this.employeeList = new HashMap<>();
        loadEmployees();
    }

    private void loadEmployees() {
        SingletonEmployeeLoader loader = SingletonEmployeeLoader.INSTANCE;
        ArrayList<Employee> temp = loader.loadEmployees();
        if(temp.isEmpty()){
            return;
        }

        for (Employee employee : temp) {
            employeeList.put(employee.getId(),employee);
        }
    }

    public boolean employeeExists(String id){
        return employeeList.containsKey(id);
    }

    public boolean addEmployee(String id, String name, String department){
        if (employeeExists(id)){
            return false;
        }
        employeeList.put(id,new Employee(id,name,department));
        return true;
    }

    public boolean removeEmployee(String id){
        if (employeeList.containsKey(id)){
            return false;
        }
        employeeList.remove(id);
        return true;
    }
    public boolean editEmployeeName(String id,String name){
        if (!employeeExists(id)){
            return false;
        }
        employeeList.get(id).setName(name);
        return true;
    }

    public boolean editEmployeeDepartment(String id, String department){
        if (!employeeExists(id)){
            return false;
        }
        employeeList.get(id).setDepartment(department);
        return true;
    }

}
