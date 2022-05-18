package com.EmployeePayrollDataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EmployeePayrollDb {
	
	public static String EMP_DATA_FETCH = "select empId,empName,Gender,Address,empPhone,startDate from employee_payroll";
	
	static List<EmployeeModel> employeeList = new ArrayList<>();


		
		public static void main(String[] args) {
			
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				Connection con = null;
				try {
					con = DriverManager.getConnection
							("jdbc:mysql://localhost:3306/payroll_service_database","root","root");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	


		

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(EMP_DATA_FETCH);

			while (rs.next()) {

				EmployeeModel model = new EmployeeModel();

				model.setEmpid(rs.getInt("empid"));
				model.setEmpName(rs.getString("empName"));
				model.setGender(rs.getString("gender"));
				model.setEmpPhone(rs.getString("EmpPhone"));
				model.setAddress(rs.getString("address"));
				model.setStartDate(
						rs.getDate("startDate"));
				employeeList.add(model);
			}
		
			employeeList.forEach(e -> {
				System.out.println("Id : " + e.getEmpid());
				System.out.println("Name : " + e.getEmpName());
				String gender = e.getGender().equals("M")
						? "Male"
						: "Female";
				System.out.println("Gender : " + gender);
				System.out
						.println("Phone : " + e.getEmpPhone());
				System.out.println(
						"Address : " + e.getAddress());
				System.out.println("Joining Date : "
						+ e.getStartDate());
				System.out.println(
						"------------------------------------------");
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
