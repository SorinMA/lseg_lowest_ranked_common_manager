package com.lseg.lowest_ranked_common_manager.classes.unit_tests;

import com.lseg.lowest_ranked_common_manager.classes.Employee;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EmployeeUnitTests {
    protected Employee employeeTest;
    protected Employee managerTest1;
    protected Employee managerTest2;

    {
        employeeTest = Employee.createNewEmployee(
                "e",
                "e",
                1,
                null,
                null,
                null
        );
        managerTest1 = Employee.createNewEmployee(
            "m1",
                "m1",
                2,
                null,
                null,
                null
        );
        managerTest2 = Employee.createNewEmployee(
                "m2",
                "m2",
                3,
                null,
                employeeTest,
                managerTest1
        );
    }

    /**
     * this test check if the createNewEmployee method can create
     * a new Employee with regular data
     */
    @Test
    public void createNewEmployeeUnitTest1 () {
        Employee testEmployee = Employee.createNewEmployee(
            "a",
                "a",
                1,
                null,
                null,
                null
        );
        assertEquals(testEmployee == null, false);
    }

    /**
     * this test call the createNewEmployee with a reportingEmployee
     * that already has a manager and should create the new employee
     * and allocate the new employee as a manager for employeeTest
     */
    @Test
    public void createNewEmployeeUnitTest2 () {
        Employee testEmployee = Employee.createNewEmployee(
                "a",
                "a",
                4,
                null,
                employeeTest,
                null
        );
        assertEquals(testEmployee == null, false);
    }

    /**
     * this test will check the setManager methods
     * */
    @Test
    public void createNewEmployeeUnitTest3 () {
        Employee testEmployee = Employee.createNewEmployee(
                "a",
                "a",
                4,
                null,
                null,
                null
        );
        try {
            testEmployee.setManager(managerTest2);
            assertEquals(false, true);
        } catch (Exception e) {
            assertEquals(false, false);
            assertEquals(e.getMessage(),"The manager should have higher rank than the reportingEmployee");
        }
    }

    /**
     * this test will check the setEmployee methods
     * */
    @Test
    public void createNewEmployeeUnitTest4 () {
        Employee testEmployee = Employee.createNewEmployee(
                "a",
                "a",
                4,
                null,
                null,
                null
        );
        try {
            managerTest2.setEmployee1(testEmployee);
            assertEquals(false, true);
        } catch (Exception e) {
            assertEquals(false, false);
            assertEquals(e.getMessage(),"The reporting employee should have a lower rank!");
        }
    }
}
