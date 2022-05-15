package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class customer {
	
	//method to connect to database
	private Connection connect()
	 {
			Connection con = null;
			
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3309/users", "root", "");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			return con;
	 } 

	public String readCustomers()
	{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
				}
	 
				// Prepare the html table to be displayed
				output = "<table border=\"1\" class=\"table\"><tr>"
						+ "<th>Customer Account No</th>"
						+ "<th>Customer Address</th>"
						+ "<th>Customer Bank Account No</th>"
						+ "<th>Customer Email</th>"
						+ "<th>Customer NIC</th>"
						+ "<th>Customer Name</th>"
						+ "<th>Customer Phone</th>"
						+ "<th>Customer Password</th>"
						+ "<th>Update</th>"
						+ "<th>Remove</th></tr>";
     
				String query = "select * from person";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
	 
				// iterate through the rows in the result set
				while (rs.next())
				{	
					String cusID = Integer.toString(rs.getInt("cusID"));
					String CustomerAccountNo = rs.getString("cusAccountNo");
					String CustomerAddress = rs.getString("cusAddress");
					String CustomerBankAccountNo = rs.getString("cusBankAccNo");
					String CustomerEmail = rs.getString("cusEmail");
					String CustomerNIC = rs.getString("cusNIC");
					String CustomerName = rs.getString("cusName");
					String CustomerPhone = rs.getString("cusPhone");
					String CustomerPassword = rs.getString("cusPassword");
	 
				// Add into the html table
					output += "<tr><td><input id='hidCustomerIDUpdate' name='hidCustomerIDUpdate' type='hidden' "
							+ "value='" + cusID + "'>" + CustomerAccountNo + "</td>";
					output += "<td>" + CustomerAddress + "</td>";
					output += "<td>" + CustomerBankAccountNo + "</td>";
					output += "<td>" + CustomerEmail + "</td>"; 
					output += "<td>" + CustomerNIC + "</td>"; 
					output += "<td>" + CustomerName + "</td>"; 
					output += "<td>" + CustomerPhone + "</td>"; 
					output += "<td>" + CustomerPassword + "</td>"; 
	 
				// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-cusid='" + cusID + "'></td>" 
							+ "<td><input name='btnRemove' type='button' "
							+ "value='Remove' class='btnRemove btn btn-danger' data-cusid='" + cusID + "'></td></tr>"; 
				}
				
				con.close();
	 
				// Complete the html table
				output += "</table>";
			} 
		catch (Exception e)
		{
			output = "Error while reading the customers.";
			System.err.println(e.getMessage());
		}
			
			return output;
	}
	
	public String insertCustomers(String accno, String address, String bankaccno, String email, String nIC, String name,
			String phone, String password)
		{
			 String output = "";
			 
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for inserting.";
				 }
				 
			 // create a prepared statement
			 String query = " insert into person (`cusID`,`cusAccountNo`,`cusAddress`,`cusBankAccNo`,`cusEmail`,`cusNIC`,`cusName`,`cusPhone`,`cusPassword`)"+ " values (?,?,?, ?, ?, ?,?,?,?)";
			 
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, accno);
					 preparedStmt.setString(3, address);
					 preparedStmt.setString(4, bankaccno);
					 preparedStmt.setString(5, email);
					 preparedStmt.setString(6, nIC);
					 preparedStmt.setString(7, name);
					 preparedStmt.setString(8, phone);
					 preparedStmt.setString(9, password);
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newCustomer = readCustomers();
					 output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
				}
			 
			catch (Exception e)
			{
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the customer.\"}";
					System.err.println(e.getMessage());
			}
					 return output;
		} 
	
	  public String updateCustomer(String ID, String accno, String address, String bankaccno, String email, String nIC, String name,
			  			String phone, String password)
			 {
			 	String output = "";
			 try
			 {
				 	Connection con = connect();
				 	if (con == null)
				 	{
				 			return "Error while connecting to the database for updating.";
				 	}
				 	
			 // create a prepared statement
			 String query = "UPDATE person SET cusAccountNo= ?,cusAddress= ?,cusBankAccNo= ?,cusEmail= ?,cusNIC= ?,cusName= ?,cusPhone= ?,cusPassword= ? WHERE cusID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, accno);
			 preparedStmt.setString(2, address);
			 preparedStmt.setString(3, bankaccno);
			 preparedStmt.setString(4, email);
			 preparedStmt.setString(5, nIC);
			 preparedStmt.setString(6, name);
			 preparedStmt.setString(7, phone);
			 preparedStmt.setString(8, password);
			 preparedStmt.setInt(9, Integer.parseInt(ID));
			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 String newCustomer = readCustomers();
			 	output = "{\"status\":\"success\", \"data\": \"" +
			    newCustomer + "\"}";
			 }
			 catch (Exception e)
			 {
				 output = "{\"status\":\"error\", \"data\":\"Error while updating the customer.\"}";
				 System.err.println(e.getMessage());
			 }
			 
			 	return output;
		}
	
	public String deleteCustomer(String cusID)
	 	{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for deleting.";
				}
		 
				// create a prepared statement
				String query = "delete from person where cusID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
	 
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(cusID));
	 
				// execute the statement
				preparedStmt.execute();
				con.close();
	 
				String newCustomer = readCustomers();
					output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the customer.\"}";
				System.err.println(e.getMessage());
			}
			return output;
	 	}
	
}

