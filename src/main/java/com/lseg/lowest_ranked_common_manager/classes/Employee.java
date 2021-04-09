package com.lseg.lowest_ranked_common_manager.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

public class Employee {
    private static final Logger logger = LogManager.getLogger(Employee.class);
    private static int lastEmployeeID;
    private int id;
    private String name;
    private String address;
    private int rank;

    private Employee manager;
    private Employee reportingEmployee1;
    private Employee reportingEmployee2;

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
            logger.log(Level.WARN, e.toString());
            System.out.println(e);
            return null;
        }
        return theNewestEmployee;
    }

    public static String getTheNameOfTheLowestRankedCommonManager (Employee employee1, Employee employee2) {
        int rankEmployee1 = employee1.getRank();
        int rankEmployee2 = employee2.getRank();
        int rankMax = Math.max(rankEmployee1, rankEmployee2);

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
        if (rank < 1 || rank > 5)
            throw new Exception("the rank field is out of range!");
        this.rank = rank;
    }

    public void setManager (final Employee manager) {
        this.manager = manager;
    }

    public void setReportingEmployee(final Employee reportingEmployee) throws Exception {
        if (reportingEmployee != null  && this.rank < 2)
            throw new Exception("This Employee is not a manager (rank 1)!");
        if (reportingEmployee != null && reportingEmployee.getRank() >= this.rank)
            throw new Exception("The reporting employee should have a lower rank!");
        if (this.reportingEmployee1 != null && this.reportingEmployee2 != null)
            throw new Exception("You can only add 2 reporting employees to a manager");
        if (this.reportingEmployee1 == null) {
            this.reportingEmployee1 = reportingEmployee;
            return;
        }
        this.reportingEmployee2 = reportingEmployee;
    }

    private void setID () {
        this.lastEmployeeID++;
        this.id = this.lastEmployeeID;
    }

    public String getName () {
        return this.name;
    }

    public int getRank () {
        return this.rank;
    }

    public Employee getManager () {
        return this.manager;
    }

    public  int getID () {
        return this.id;
    }
}