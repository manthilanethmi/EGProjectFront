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
		var status = validatePerformanceForm();
		if (status != true)
 		{
 			$("#alertError").text(status);
 			$("#alertError").show();
 			return;
 		}
	
			// If valid------------------------
		var type = ($("#hidPerformIDSave").val() == "") ? "POST" : "PUT";
 		
 		$.ajax(
		{
			 url : "PerformAPI",
 			 type : type,
 			 data : $("#formPerformance").serialize(),
 			 dataType : "text",
 			 complete : function(response, status)
 				{
 					onPerformSaveComplete(response.responseText, status);
 				}
 	});
 	
 });

function onPerformSaveComplete(response, status)
	{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully saved.");
 			$("#alertSuccess").show();
 			$("#divPerformsGrid").html(resultSet.data);
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
 		$("#hidPerformIDSave").val("");
 		$("#formPerformance")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 		$("#hidPerformIDSave").val($(this).data("perid"));
 		$("#Year").val($(this).closest("tr").find('td:eq(0)').text());
 		$("#manufAmnt").val($(this).closest("tr").find('td:eq(1)').text());
	    $("#consAmnt").val($(this).closest("tr").find('td:eq(2)').text());
 		$("#storedAmnt").val($(this).closest("tr").find('td:eq(3)').text());
 		$("#totSales").val($(this).closest("tr").find('td:eq(4)').text());
 		$("#totCost").val($(this).closest("tr").find('td:eq(5)').text());
 		$("#totProfit").val($(this).closest("tr").find('td:eq(6)').text());
		$("#avgGen").val($(this).closest("tr").find('td:eq(7)').text());
		$("#avgCons").val($(this).closest("tr").find('td:eq(8)').text());
});

//DELETE==========================================
$(document).on("click", ".btnRemove", function(event)
{
 		$.ajax(
 		{
 			url : "PerformAPI",
 			type : "DELETE",
 			data : "perID=" + $(this).data("perid"),
 			dataType : "text",
 			complete : function(response, status)
 	    	{
 				onPerformDeleteComplete(response.responseText, status);
 			}
 	  });
});

function onPerformDeleteComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully deleted.");
 			$("#alertSuccess").show();
 			$("#divPerformsGrid").html(resultSet.data);
 			
 		} else if (resultSet.status.trim() == "error")
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
function validatePerformanceForm()
{
	// PERFORMANCE YEAR
	if ($("#Year").val().trim() == "")
	 {
	 	return "Insert Performance Year.";
	 }
	 
	// MANUFACTURED AMOUNT
	if ($("#manufAmnt").val().trim() == "")
	 {
	 	return "Insert Manufactured Amount.";
	 }
	 
	// CONSUMED AMOUNT
	if ($("#consAmnt").val().trim() == "")
	 {
	 	return "Insert Consumed Amount.";
	 }
	 
	// STORED AMOUNT
	if ($("#storedAmnt").val().trim() == "")
	 {
	 	return "Insert Stored Amount.";
	 }
	 
	// TOTAL SALES
	if ($("#totSales").val().trim() == "")
	 {
	 	return "Insert Total Sales.";
	 }
	 
	// TOTAL COST
	if ($("#totCost").val().trim() == "")
	 {
	 	return "Insert Total Cost.";
	 }
	 	
	// TOTAL PROFIT
	if ($("#totProfit").val().trim() == "")
	 {
	 	return "Insert Total Profit.";
	 }
	 
	// AVERAGE POWER GENERATION
	if ($("#avgGen").val().trim() == "")
	 {
	 	return "Insert Average Power Generation.";
	 }
	 
	 // AVERAGE POWER CONSUMPTION
	if ($("#avgCons").val().trim() == "")
	 {
	 	return "Insert Average Power Consumption.";
	 }
	 
   return true;
}