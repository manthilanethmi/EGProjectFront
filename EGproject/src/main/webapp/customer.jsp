<%@page import="com.customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
 	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="Views/front.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/customer.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">

	<hr>
 	<h2><b>CUSTOMER REGISTRATION</b></h2>
 	<hr>
 	<br>

 	<form id="formCustomer" name="formCustomer">
    
 		<!-- BILL ACCOUNT NO -->
 		<b> Customer Account No : </b>
 			<input id="cusAccountNo" name="cusAccountNo" type="text"
 			   class="form-control form-control-sm" placeholder="Enter Account Number">
 		<br>
 		
 		<!-- ADDRESS -->
 		<b> Customer Address : </b>
 			<input id="cusAddress" name="cusAddress" type="text"
 			   class="form-control form-control-sm" placeholder="Enter Residential Address">
 		<br>
 		
 		<!-- BANK ACCOUNT NO -->
 		<b> Bank Account No : </b>
 			<input id="cusBankAccNo" name="cusBankAccNo" type="text"
 			    class="form-control form-control-sm" placeholder="Enter Bank Account Number">
 		<br>
 		
 		<!-- EMAIL -->
 		<b> Email : </b>
 			<input id="cusEmail" name="cusEmail" type="text"
 		        class="form-control form-control-sm"  placeholder="Enter Email Address">
 		<br>
 		
 		<!-- NIC -->
 		<b> NIC No : </b>
 			<input id="cusNIC" name="cusNIC" type="text"
 			   class="form-control form-control-sm" placeholder="Enter National Identity Card Number">
 		<br>
 		
 		<!-- NAME -->
 	    <b> Name: </b>
 			<input id="cusName" name="cusName" type="text"
 			   class="form-control form-control-sm" placeholder="Enter Full Name">
 		<br> 		
 		
 		<!-- PHONE -->
        <b> Contact No: </b>
 			<input id="cusPhone" name="cusPhone" type="text"
 				class="form-control form-control-sm" placeholder="Enter Contact Number">
 		<br> 	
 		
 		<!-- PASSWORD -->
        <b> Create a Password: </b>
 			<input id="cusPassword" name="cusPassword" type="text"
 			    class="form-control form-control-sm" placeholder="Create & Enter a Password">
 		<br> 
 		
 		<center>
        <input id="btnSave" name="btnSave" type="button" value="Save Details" class="btn btn-primary">
        </center>
 	    <input type="hidden" id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
 	   
	</form><br>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	
	<div id="divCustomersGrid">
		<%
			 customer customerObj = new customer();
			 out.print(customerObj.readCustomers());
		 %>
	</div>

</div></div></div>	
</body>
</html>
