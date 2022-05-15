$(document).ready(function()
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
		// Clear alerts---------------------
 		$("#alertSuccess").text("");
 		$("#alertSuccess").hide();
	    $("#alertError").text("");
 		$("#alertError").hide();
 
		// Form validation-------------------
		var status = validateCustomerForm();
		if (status != true)
 		{
 			$("#alertError").text(status);
 			$("#alertError").show();
 			return;
 		}
 
		// If valid------------------------
		var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT";
 		
 		$.ajax(
		{
			 url : "CustomerAPI",
 			 type : type,
 			 data : $("#formCustomer").serialize(),
 			 dataType : "text",
 			 complete : function(response, status)
 				{
 					onCustomerSaveComplete(response.responseText, status);
 				}
 	});
 	
}); 	
 	function onCustomerSaveComplete(response, status)
	{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully saved.");
 			$("#alertSuccess").show();
 			
 			$("#divCustomersGrid").html(resultSet.data);
 			
 		} else if (resultSet.status.trim() == "error")
 			{
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			}
 		} else if (status == "error")
 			{
 				$("#alertError").text("Error while saving.");
 				$("#alertError").show();
 		} else
 			{
 				$("#alertError").text("Unknown error while saving..");
 				$("#alertError").show();
 		}
 		$("#hidCustomerIDSave").val("");
 		$("#formCustomer")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 		$("#hidCustomerIDSave").val($(this).data("cusid"));
 		$("#cusAccountNo").val($(this).closest("tr").find('td:eq(0)').text());
 		$("#cusAddress").val($(this).closest("tr").find('td:eq(1)').text());
	    $("#cusBankAccNo").val($(this).closest("tr").find('td:eq(2)').text());
 		$("#cusEmail").val($(this).closest("tr").find('td:eq(3)').text());
 		$("#cusNIC").val($(this).closest("tr").find('td:eq(4)').text());
 		$("#cusName").val($(this).closest("tr").find('td:eq(5)').text());
 		$("#cusPhone").val($(this).closest("tr").find('td:eq(6)').text());
		$("#cusPassword").val($(this).closest("tr").find('td:eq(7)').text());
});

//DELETE==========================================
$(document).on("click", ".btnRemove", function(event)
{
 		$.ajax(
 		{
 			url : "CustomerAPI",
 			type : "DELETE",
 			data : "cusID=" + $(this).data("cusid"),
 			dataType : "text",
 			complete : function(response, status)
 	    	{
 				onCustomerDeleteComplete(response.responseText, status);
 			}
 	  });
});

function onCustomerDeleteComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully deleted.");
 			$("#alertSuccess").show();
 			$("#divCustomersGrid").html(resultSet.data);
 			
 		} 
 		else if (resultSet.status.trim() == "error")
 		{
 			$("#alertError").text(resultSet.data);
 			$("#alertError").show();
 		}
    } else if (status == "error")
 		{
 			$("#alertError").text("Error while deleting.");
 			$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while deleting..");
 		$("#alertError").show();
 	}
}

// CLIENT-MODEL================================================================
function validateCustomerForm()
{
	// ACCOUNT NO
	if ($("#cusAccountNo").val().trim() == "")
	 {
	 	return "Insert Customer Account Number.";
	 }
	 
	// ADDRESS
	if ($("#cusAddress").val().trim() == "")
	 {
	 	return "Insert Customer Address.";
	 }
	 
	// BANK ACCOUNT NO
	if ($("#cusBankAccNo").val().trim() == "")
	 {
	 	return "Insert Customer Bank Account Number.";
	 }
	 
	// EMAIL
	if ($("#cusEmail").val().trim() == "")
	 {
	 	return "Insert Customer Email.";
	 }
	 
	// NIC
	if ($("#cusNIC").val().trim() == "")
	 {
	 	return "Insert Customer NIC No.";
	 }
	 
	// NAME
	if ($("#cusName").val().trim() == "")
	 {
	 	return "Insert Customer Name.";
	 }
	 
	// PHONE
	if ($("#cusPhone").val().trim() == "")
	 {
	 	return "Insert Customer Contact No.";
	 }
	 
	// PASSWORD
	if ($("#cusPassword").val().trim() == "")
	 {
	 	return "Create an insert a password.";
	 }
	 
   return true;
}
