<!DOCTYPE html>
<html>

<head>
	<title>Update Customer</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Update customer...</h2>
		</div>
	</div>
	
	<div id="container">
		
		
		<form action="WarehouseControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE-CUSTOMER" />

			<input type="hidden" name="customerId" value="${THE_CUSTOMER.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="name" 
								   value="${THE_STUDENT.name}" /></td>
					</tr>

					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="address" 
								   value="${THE_CUSTOMER.address}" /></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="telephone" 
								   value="${THE_CUSTOMER.telephone}" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="WarehouseControllerServlet">Back to hoem page</a>
		</p>
	</div>
</body>

</html>



