package com.lseg.lowest_ranked_common_manager.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employee {
    private static int lastEmployeeID;
    private static final int MAX_RANK = 5;
    private static final int MIN_RANK = 1;
    private static final Logger logger = LogManager.getLogger(Employee.class);

    private int id;
    private String name;
    private String address;
    private int rank;

    private Employee manager;
    private Employee reportingEmployee1;
    private Employee reportingEmployee2;

    /**
     *
     * */
    private Employee () {}

    public static Employee createNewEmployee (final String name, final String address,
                                       final int rank, final Employee manager,
                                       final Employee reportingEmployee1, final Employee reportingEmployee2) {
        Employee theNewestEmployee = new Employee();
        try {
            theNewestEmployee.setName(name);
            theNewestEmployee.setAddress(address);
            theNewestEmployee.setRank(rank);
            theNewestEmployee.setManager(manager);
            theNewestEmployee.setReportingEmployee(reportingEmployee1);
            theNewestEmployee.setReportingEmployee(reportingEmployee2);
            theNewestEmployee.setID();
        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
        return theNewestEmployee;
    }

    public static String getTheNameOfTheLowestRankedCommonManager (Employee employee1, Employee employee2) {
        if (employee1 == null || employee2 == null)
            return "The input is null!";
        int rankEmployee1 = employee1.getRank();
        int rankEmployee2 = employee2.getRank();
        int rankMax = Math.max(rankEmployee1, rankEmployee2) + 1; // + 1 because the method search for the common manager

        if (rankMax > MAX_RANK)
            return "One of the employee doesn't have a manager!";

        while (rankEmployee1 < rankMax) {
            employee1 = employee1.getManager();
            rankEmployee1 = employee1.getRank();
        }

        while (rankEmployee2 < rankMax) {
            employee2 = employee2.getManager();
            rankEmployee2 = employee2.getRank();
        }

        while (true) {
            if (employee1.getID() == employee2.getID()) {
                return employee1.getName();
            } else {
                employee1 = employee1.getManager();
                employee2 = employee2.getManager();

                if (employee1 == null || employee2 == null)
                    return "These 2 employees have no common supervisor";

                rankEmployee1 = employee1.getRank();
                rankEmployee2 = employee2.getRank();
                rankMax = Math.max(rankEmployee1, rankEmployee2);

                while (rankEmployee1 < rankMax) {
                    employee1 = employee1.getManager();
                    rankEmployee1 = employee1.getRank();
                }

                while (rankEmployee2 < rankMax) {
                    employee2 = employee2.getManager();
                    rankEmployee2 = employee2.getRank();
                }
            }
        }
    }

    private void setName (final String name) throws Exception {
        if (name == null || name.equals(""))
            throw new Exception("the name field is empty!");
        this.name = name;
    }

    private void setAddress (final String address) throws Exception {
        if (address == null || address.equals(""))
            throw new Exception("the address field is empty!");
        this.address = address;
    }

    private void setRank (final int rank) throws Exception {
        if (rank < MIN_RANK || rank > MAX_RANK)
            throw new Exception("the rank field is out of range!");
        this.rank = rank;
    }

    public void setManager (final Employee manager) {
        this.manager = manager;
    }

    public void setReportingEmployee(final Employee reportingEmployee) throws Exception {
        if (reportingEmployee == null)
            return;
        if (reportingEmployee != null  && this.rank == MIN_RANK)
            throw new Exception("This Employee is not a manager (rank 1)!");
        if (reportingEmployee != null && reportingEmployee.getRank() >= this.rank)
            throw new Exception("The reporting employee should have a lower rank!");
        if (this.reportingEmployee1 != null && this.reportingEmployee2 != null)
            throw new Exception("You can only add 2 reporting employees to a manager");
        if (reportingEmployee.getManager() != null)
            throw new Exception("This employee already has a manager!");

        if (this.reportingEmployee1 == null) {
            this.reportingEmployee1 = reportingEmployee;
            this.reportingEmployee1.setManager(this);
            return;
        }
        this.reportingEmployee2 = reportingEmployee;
        this.reportingEmployee2.setManager(this);
    }

    private void setID () {
        this.lastEmployeeID++;
        this.id = this.lastEmployeeID;
    }

    public String getName () {
        return this.name;
    }

    public Employee getManager () {
        return this.manager;
    }

    public int getRank () {
        return this.rank;
    }

    public  int getID () {
        return this.id;
    }
}