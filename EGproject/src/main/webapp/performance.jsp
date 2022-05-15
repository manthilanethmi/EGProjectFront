<%@page import="com.performance"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Performance Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="Views/front.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/performs.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
	
	<hr>
 	<h2><b>PERFORMANCE MANAGEMENT</b></h2>
 	<hr>
 	<br>

 	<form id="formPerformance" name="formPerformance">
 	
 	<b> Company Performance Year : <b>
 			<input id="Year" name="Year" type="text"
 			   class="form-control form-control-sm" placeholder="Enter Performance Year">
 		<br>
 		
 	<b> Manufactured Power Units : </b>
 			<input id="manufAmnt" name="manufAmnt" type="text"
 			   class="form-control form-control-sm" placeholder="Enter Manufactured Amount">
 		<br>
 		
 	<b> Consumed Power Units : </b>
 			<input id="consAmnt" name="consAmnt" type="text"
 			   class="form-control form-control-sm" placeholder="Enter Consumed Amount">
 		<br>
 		
 	<b> Stored Power Units : </b>
 			<input id="storedAmnt" name="storedAmnt" type="text"
 			   class="form-control form-control-sm" placeholder="Enter Stored Amount">
 		<br>
 	
 	<b> Total Sales : </b>
 			<input id="totSales" name="totSales" type="text"
 			   class="form-control form-control-sm" placeholder="Enter Total Sales">
 		<br>
 	
 	<b> Total Cost : </b>
 			<input id="totCost" name="totCost" type="text"
 			   class="form-control form-control-sm" placeholder="Enter Total Cost">
 		<br>
 	
 	<b> Total Profit : </b>
 			<input id="totProfit" name="totProfit" type="text"
 			   class="form-control form-control-sm" placeholder="Enter Total Profit">
 		<br>
 		
 	<b> Average Power Generation : </b>
 			<input id="avgGen" name="avgGen" type="text"
 			   class="form-control form-control-sm" placeholder="Enter Average Power Generation">
 		<br>
 		
 	<b> Average Power Consumption : </b>
 			<input id="avgCons" name="avgCons" type="text"
 			   class="form-control form-control-sm" placeholder="Enter Average Power Consumption">
 		<br>
 		
 		<center>
 	    <input id="btnSave" name="btnSave" type="button" value="Save Details" class="btn btn-primary">
 	    </center>	
 	    <input type="hidden" id="hidPerformIDSave" name="hidPerformIDSave" value="">
 	    
	</form>
 	<br>
 	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	<div id="divPerformsGrid">
		<%
			 performance performObj = new performance();
			 out.print(performObj.readPerforms());
		 %>
	</div>

</div></div></div>	
</body>
</html>