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


@WebServlet("/PerformAPI")
public class PerformAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    performance performObj = new performance();  
   
    public PerformAPI() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = performObj.insertPerforms(
				request.getParameter("Year"),
				request.getParameter("manufAmnt"),
				request.getParameter("consAmnt"),
				request.getParameter("storedAmnt"),
				request.getParameter("totSales"),
				request.getParameter("totCost"),
				request.getParameter("totProfit"),
				request.getParameter("avgGen"),
				request.getParameter("avgCons"));
			
				response.getWriter().write(output);
				
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Map paras = getParasMap(request);
		
		 String output = performObj.updatePerforms(paras.get("hidPerformIDSave").toString(),
				 	paras.get("Year").toString(),
				 	paras.get("manufAmnt").toString(),
					paras.get("consAmnt").toString(),
					paras.get("storedAmnt").toString(),
					paras.get("totSales").toString(),
					paras.get("totCost").toString(),
					paras.get("totProfit").toString(),
					paras.get("avgGen").toString(),
					paras.get("avgCons").toString());
					 	
					response.getWriter().write(output);
		} 


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Map paras = getParasMap(request);
		String output = performObj.deletePerforms(paras.get("perID").toString());
	  
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