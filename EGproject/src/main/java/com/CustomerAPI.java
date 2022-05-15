package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CustomerAPI")
public class CustomerAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	customer customerObj = new customer();
	
    public CustomerAPI() {
    	super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String output = customerObj.insertCustomers(
				request.getParameter("cusAccountNo"),
				request.getParameter("cusAddress"),
				request.getParameter("cusBankAccNo"),
				request.getParameter("cusEmail"),
				request.getParameter("cusNIC"),
				request.getParameter("cusName"),
				request.getParameter("cusPhone"),
				request.getParameter("cusPassword"));
			
				response.getWriter().write(output);
				
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		 String output = customerObj.updateCustomer(paras.get("hidCustomerIDSave").toString(),
				 	paras.get("cusAccountNo").toString(),
					paras.get("cusAddress").toString(),
					paras.get("cusBankAccNo").toString(),
					paras.get("cusEmail").toString(),
					paras.get("cusNIC").toString(),
					paras.get("cusName").toString(),
					paras.get("cusPhone").toString(),
					paras.get("cusPassword").toString());
					 	
					response.getWriter().write(output);
		} 

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		  String output = customerObj.deleteCustomer(paras.get("cusID").toString());
		  
		  response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
			Map<String, String> map = new HashMap<String, String>();
		    try {
		    	Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		    	String queryString = scanner.hasNext() ?
		    			scanner.useDelimiter("\\A").next() : "";
		    	scanner.close();
		    	String[] params = queryString.split("&");
		    	for (String param : params)
		    	{ 
		    		String[] p = param.split("=");
		    		map.put(p[0], p[1]);
		    	}
			}
			catch (Exception e)
		 	{
		 	}
		return map;
	}

}
