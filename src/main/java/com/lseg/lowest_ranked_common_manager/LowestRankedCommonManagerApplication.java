package com.lseg.lowest_ranked_common_manager;

import com.lseg.lowest_ranked_common_manager.classes.Employee;

public class LowestRankedCommonManagerApplication {
	static Employee e11, e12, e13, e14, e15, e16, e17, e18, e19;
	static Employee e20, e21, e22, e23, e24, e25, e26;
	static Employee e30, e31, e32, e33;
	static Employee e40, e41;
	static Employee e50, e51;

	public static void main(String[] args) {
		initTestData();
		System.out.println(Employee.getTheNameOfTheLowestRankedCommonManager(e12, e22));
		System.out.println(Employee.getTheNameOfTheLowestRankedCommonManager(e32, e19));
		System.out.println(Employee.getTheNameOfTheLowestRankedCommonManager(e15, e26));
	}

	public static void initTestData () {
		e11 = Employee.createNewEmployee(
				"e11",
				"e11 addr",
				1,
				null,
				null,
				null
		);
		e12 = Employee.createNewEmployee(
				"e12",
				"e12 addr",
				1,
				null,
				null,
				null
		);
		e13 = Employee.createNewEmployee(
				"e13",
				"e13 addr",
				1,
				null,
				null,
				null
		);
		e14 = Employee.createNewEmployee(
				"e14",
				"e14 addr",
				1,
				null,
				null,
				null
		);
		e15 = Employee.createNewEmployee(
				"e15",
				"e15 addr",
				1,
				null,
				null,
				null
		);
		e16 = Employee.createNewEmployee(
				"e16",
				"e16 addr",
				1,
				null,
				null,
				null
		);
		e17 = Employee.createNewEmployee(
				"e17",
				"e17 addr",
				1,
				null,
				null,
				null
		);
		e18 = Employee.createNewEmployee(
				"e18",
				"e18 addr",
				1,
				null,
				null,
				null
		);
		e19 = Employee.createNewEmployee(
				"e19",
				"e19 addr",
				1,
				null,
				null,
				null
		);
		e20 = Employee.createNewEmployee(
				"e20",
				"e20 addr",
				2,
				null,
				e11,
				null
		);
		e21 = Employee.createNewEmployee(
				"e21",
				"e21 addr",
				2,
				null,
				e12,
				null
		);
		e22 = Employee.createNewEmployee(
				"e22",
				"e22 addr",
				2,
				null,
				e13,
				e14
		);
		e23 = Employee.createNewEmployee(
				"e23",
				"e23 addr",
				2,
				null,
				e15,
				null
		);
		e24 = Employee.createNewEmployee(
				"e24",
				"e24 addr",
				2,
				null,
				e16,
				e17
		);
		e25 = Employee.createNewEmployee(
				"e25",
				"e25 addr",
				2,
				null,
				e18,
				e19
		);
		e26 = Employee.createNewEmployee(
				"e26",
				"e26 addr",
				2,
				null,
				null,
				null
		);
		e30 = Employee.createNewEmployee(
				"e30",
				"e30 addr",
				3,
				null,
				e20,
				e21
		);
		e31 = Employee.createNewEmployee(
				"e31",
				"e31 addr",
				3,
				null,
				e22,
				null
		);
		e32 = Employee.createNewEmployee(
				"e32",
				"e32 addr",
				3,
				null,
				e23,
				null
		);
		e33 = Employee.createNewEmployee(
				"e33",
				"e33 addr",
				3,
				null,
				e24,
				e25
		);
		e40 = Employee.createNewEmployee(
				"e40",
				"e40 addr",
				4,
				null,
				e30,
				e31
		);
		e41 = Employee.createNewEmployee(
				"e41",
				"e41 addr",
				4,
				null,
				e32,
				e33
		);
		e50 = Employee.createNewEmployee(
				"e50",
				"e50 addr",
				5,
				null,
				e40,
				e41
		);
		e51 = Employee.createNewEmployee(
				"e51",
				"e51 addr",
				5,
				null,
				e26,
				null
		);

		e11.setManager(e20);
		e12.setManager(e21);
		e13.setManager(e22);
		e14.setManager(e22);
		e15.setManager(e23);
		e16.setManager(e24);
		e17.setManager(e24);
		e18.setManager(e25);
		e19.setManager(e25);

		e20.setManager(e30);
		e21.setManager(e30);
		e22.setManager(e31);
		e23.setManager(e32);
		e24.setManager(e33);
		e25.setManager(e33);
		e26.setManager(e51);

		e30.setManager(e40);
		e31.setManager(e40);
		e32.setManager(e41);
		e33.setManager(e41);

		e40.setManager(e50);
		e41.setManager(e50);
	}

}
