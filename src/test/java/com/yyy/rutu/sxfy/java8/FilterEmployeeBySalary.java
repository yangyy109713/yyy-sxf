package com.yyy.rutu.sxfy.java8;

public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean compare(Employee employee) {
        return employee.getSalary() > 800;
    }
}
