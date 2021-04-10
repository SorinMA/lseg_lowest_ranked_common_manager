package com.lseg.lowest_ranked_common_manager.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employee {
    protected static int lastEmployeeID;
    protected static final int MAX_RANK = 5;
    protected static final int MIN_RANK = 1;
    protected static final Logger logger = LogManager.getLogger(Employee.class);

    protected int id;
    protected String name;
    protected String address;
    protected int rank;

    protected Employee manager;
    protected Employee reportingEmployee1;
    protected Employee reportingEmployee2;

    /**
     * the constructor is private, because we want to create a new Employee object
     * only if his arguments respect the rules
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
            theNewestEmployee.setEmployee1(reportingEmployee1);
            theNewestEmployee.setEmployee2(reportingEmployee2);
            theNewestEmployee.setID();
        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
        return theNewestEmployee;
    }

    /**
     * this is the method :)
     * */
    public static String getTheNameOfTheLowestRankedCommonManager (Employee employee1, Employee employee2) {
        if (employee1 == null || employee2 == null)
            return "The input is null!";

        // step1: find the rankMax value that is equal with the maximum rank value of (employee1, employee2) + 1
        // the rankMax value is used to obtains the managers for employee1 & employee2 at the same rank
        // and 1 is added because we search for the common manager
        int rankEmployee1 = employee1.getRank();
        int rankEmployee2 = employee2.getRank();
        int rankMax = Math.max(rankEmployee1, rankEmployee2) + 1;

        if (rankMax > MAX_RANK)
            return "These 2 employees have no common supervisor! (One of the employees doesn't have a manager!)";

        while (true) { //step2.0 finding the managers for rankEmployee1 & rankEmployee2 with the same rank
            // step2.1: having the rankMax, we'll obtains the manager at rankMax for employee1
            while (rankEmployee1 < rankMax) {
                employee1 = employee1.getManager();
                if (employee1 == null)
                    return "These 2 employees have no common supervisor";
                rankEmployee1 = employee1.getRank();
            }

            // step2.2: having the rankMax, we'll obtains the manager at rankMax for employee2
            while (rankEmployee2 < rankMax) {
                employee2 = employee2.getManager();
                if (employee2 == null)
                    return "These 2 employees have no common supervisor";
                rankEmployee2 = employee2.getRank();
            }

            if (rankEmployee1 == rankEmployee2) { // 2.3.1: the managers were found
                break;
            } else { // 2.3.2: the managers found have diff. ranks, and will try again
                rankMax = Math.max(rankEmployee1, rankEmployee2);
            }
        }

        while (true) {
            // step3.1: if the found managers have the same id, we stop and return the solution (the name of manager)
            if (employee1.getID() == employee2.getID()) {
                return employee1.getName();
            } else { // step 3.2.1: if the managers have diff. ids, for each manager, we get the next manager
                employee1 = employee1.getManager();
                employee2 = employee2.getManager();

                if (employee1 == null || employee2 == null)
                    return "These 2 employees have no common supervisor";

                // 3.2.2: for each manager we get the rank to be sure that they have the same rank
                rankEmployee1 = employee1.getRank();
                rankEmployee2 = employee2.getRank();
                rankMax = Math.max(rankEmployee1, rankEmployee2);

                while (true) { // 3.2.3: finding the managers for rankEmployee1 & rankEmployee2 with the same rank
                    // 3.2.4: if the rank of manager1 (employee1) isn't the rankMax, we add obtain
                    // the rankMax manager for manager1
                    while (rankEmployee1 < rankMax) {
                        employee1 = employee1.getManager();
                        if (employee1 == null)
                            return "These 2 employees have no common supervisor";
                        rankEmployee1 = employee1.getRank();
                    }

                    // 3.2.5: if the rank of manager2 (employee2) isn't the rankMax, we add obtain
                    // the rankMax manager for manager2
                    while (rankEmployee2 < rankMax) {
                        employee2 = employee2.getManager();
                        if (employee2 == null)
                            return "These 2 employees have no common supervisor";
                        rankEmployee2 = employee2.getRank();
                    }

                    if (rankEmployee1 == rankEmployee2) { // 3.2.6: the managers were found
                        break;
                    } else { // 3.2.7: the managers found have diff. ranks, and will try again
                        rankMax = Math.max(rankEmployee1, rankEmployee2);
                    }
                }
            }
        }
    }

    protected void setName (final String name) throws Exception {
        if (name == null || name.equals(""))
            throw new Exception("the name field is empty!");
        this.name = name;
    }

    protected void setAddress (final String address) throws Exception {
        if (address == null || address.equals(""))
            throw new Exception("the address field is empty!");
        this.address = address;
    }

    protected void setRank (final int rank) throws Exception {
        if (rank < MIN_RANK || rank > MAX_RANK)
            throw new Exception("the rank field is out of range!");
        this.rank = rank;
    }

    public void setManager (final Employee manager) throws Exception {
        if (manager == null)
            return;
        if (this.rank == MAX_RANK)
            throw new Exception("This employee has MAX_RANK, so cannot have a manager!");
        if (this.rank >= manager.getRank())
            throw new Exception("The manager should have higher rank than the reportingEmployee");
        this.manager = manager;
    }

    /*
     * this method is used to validate a new reportingEmployee,
     * and check:
     *      1. if the employee you want to assign the new reportingEmployee has MIN_RANK (aka that employee isn't a manager)
     *      2. if the employee has a lower rank then the reportingEmployee (the reportingEmployee should report to
     *         a higher ranking employee)
     * */
    protected void validateNewReportingEmployee (final Employee reportingEmployee) throws Exception {
        if (reportingEmployee != null  && this.rank == MIN_RANK)
            throw new Exception("This Employee is not a manager (rank 1)!");
        if (reportingEmployee != null && reportingEmployee.getRank() >= this.rank)
            throw new Exception("The reporting employee should have a lower rank!");
    }

    public void setEmployee1 (final Employee reportingEmployee) throws Exception {
        if (reportingEmployee == null)
            return;
        // validate the new reportingEmployee
        validateNewReportingEmployee(reportingEmployee);
        // set the new reportingEmployee
        this.reportingEmployee1 = reportingEmployee;
        // set the employee as a manager for the new reportingEmployee
        this.reportingEmployee1.setManager(this);
    }

    public void setEmployee2 (final Employee reportingEmployee) throws Exception {
        if (reportingEmployee == null)
            return;
        // validate the new reportingEmployee
        validateNewReportingEmployee(reportingEmployee);
        // set the new reportingEmployee
        this.reportingEmployee2 = reportingEmployee;
        // set the employee as a manager for the new reportingEmployee
        this.reportingEmployee2.setManager(this);
    }

    /*
     * this method sets the id for a new employee automatically, with the last id + 1
     */
    protected void setID () {
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