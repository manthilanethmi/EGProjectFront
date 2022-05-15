package com;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class performance {
	
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
	
	public String readPerforms()
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
						+ "<th>Year</th>"
						+ "<th>Manufactured Amount</th>"
						+ "<th>Consumed Amount</th>"
						+ "<th>Stored Amount</th>"
						+ "<th>Total Sales</th>"
						+ "<th>Total Cost</th>"
						+ "<th>Total Profit</th>"
						+ "<th>Average Generation</th>"
						+ "<th>Average Consumption</th>"
						+ "<th>Update</th>"
						+ "<th>Remove</th></tr>";
     
				String query = "select * from performance";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
	 
				// iterate through the rows in the result set
				while (rs.next())
				{	
					String perID = Integer.toString(rs.getInt("perID"));
					String Year = Integer.toString(rs.getInt("Year"));
					String ManfAmnt = Integer.toString(rs.getInt("manufAmnt"));
					String ConsAmnt = Integer.toString(rs.getInt("consAmnt"));
					String StoredAmnt = Integer.toString(rs.getInt("storedAmnt"));
					String TotSales = Double.toString(rs.getDouble("totSales"));
					String TotCost = Double.toString(rs.getDouble("totCost"));
					String TotProfit = Double.toString(rs.getDouble("totProfit"));
					String AvgGen = Integer.toString(rs.getInt("avgGen"));
					String AvgCons = Integer.toString(rs.getInt("avgCons"));
	 
				// Add into the html table
					output += "<tr><td><input id='hidPerformIDUpdate' name='hidPerformIDUpdate' type='hidden' "
							+ "value='" + perID + "'>" + Year + "</td>";
					output += "<td>" + ManfAmnt + "</td>";
					output += "<td>" + ConsAmnt + "</td>";
					output += "<td>" + StoredAmnt + "</td>";
					output += "<td>" + TotSales + "</td>"; 
					output += "<td>" + TotCost + "</td>"; 
					output += "<td>" + TotProfit + "</td>"; 
					output += "<td>" + AvgGen + "</td>"; 
					output += "<td>" + AvgCons + "</td>"; 
	 
				// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-perid='" + perID + "'></td>" 
							+ "<td><input name='btnRemove' type='button' "
							+ "value='Remove' class='btnRemove btn btn-danger' data-perid='" + perID + "'></td></tr>"; 
				}
				
				con.close();
	 
				// Complete the html table
				output += "</table>";
			} 
		catch (Exception e)
		{
			output = "Error while reading the performances.";
			System.err.println(e.getMessage());
		}
			
			return output;
	}
	
	public String insertPerforms(String year, String manufamnt, String consamnt, String storedamnt, String totsales, String totcost,
			String totprofit, String avggen, String avgcons)
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
			 String query = " insert into performance (`perID`,`Year`,`manufAmnt`,`consAmnt`,`storedAmnt`,`totSales`,`totCost`,`totProfit`,"
			 		+ "`avgGen`,`avgCons`)"
			        + " values (?,?,?,?,?,?,?,?,?,?)";
			 
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setInt(2, Integer.parseInt(year));
					 preparedStmt.setInt(3, Integer.parseInt(manufamnt));
					 preparedStmt.setInt(4, Integer.parseInt(consamnt));
					 preparedStmt.setInt(5, Integer.parseInt(storedamnt));
					 preparedStmt.setDouble(6, Double.parseDouble(totsales));
					 preparedStmt.setDouble(7, Double.parseDouble(totcost));
					 preparedStmt.setDouble(8, Double.parseDouble(totprofit));
					 preparedStmt.setInt(9, Integer.parseInt(avggen));
					 preparedStmt.setInt(10, Integer.parseInt(avgcons));
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newPerform = readPerforms();
					 output = "{\"status\":\"success\", \"data\": \"" + newPerform + "\"}";
				}
			 
			catch (Exception e)
			{
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the performance.\"}";
					System.err.println(e.getMessage());
			}
					 return output;
		} 
	
	public String updatePerforms(String id,String year, String manufamnt, String consamnt, String storedamnt, String totsales, String totcost, 
			String totprofit, String avggen, String avgcons)
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
				String query = "UPDATE performance SET Year=?,manufAmnt= ?,consAmnt= ?,storedAmnt= ?,"
						+ "totSales= ?,totCost= ?,totProfit= ?,avgGen= ?,avgCons= ? WHERE perID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
 
				// binding values
				 preparedStmt.setInt(1, Integer.parseInt(year));
				 preparedStmt.setInt(2, Integer.parseInt(consamnt));
				 preparedStmt.setInt(3, Integer.parseInt(storedamnt));
				 preparedStmt.setDouble(4, Double.parseDouble(totsales));
				 preparedStmt.setDouble(5, Double.parseDouble(totcost));
				 preparedStmt.setDouble(6, Double.parseDouble(totprofit));
				 preparedStmt.setInt(7, Integer.parseInt(avggen));
				 preparedStmt.setInt(8, Integer.parseInt(avgcons));
				 preparedStmt.setInt(9, Integer.parseInt(id));
 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
 
				 String newPerform = readPerforms();
				 output = "{\"status\":\"success\", \"data\": \"" + newPerform + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while updating the performance.\"}";
				System.err.println(e.getMessage());
			}
 
			return output;
}
	
	public String deletePerforms(String perID)
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
			String query = "delete from performance where perID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(perID));
 
			// execute the statement
			preparedStmt.execute();
			con.close();
 
			String newPerform = readPerforms();
				output = "{\"status\":\"success\", \"data\": \"" + newPerform + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the performance.\"}";
			System.err.println(e.getMessage());
		}
		return output;
 	}
	
}