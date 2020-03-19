package com.yyy.rutu.sxfy.java8;

public class FilterEmployeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean compare(Employee employee) {
        return employee.getAge() > 30;
    }
}
