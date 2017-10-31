<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>
<title>Real warehouse</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>


<body>


	<div id="wrapper">
		<div id="header">
			<h2>Warehouse docs</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">
			
		<form action = "WarehouseControl">
			Product 1
			<select name="warehouse1">
				<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			<br> 
			Product 2
			<select name="warehouse2">
				<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			<br> 
			Product 3
			<select name="warehouse3">
				<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			<br> 
			Product 4
			<select name="warehouse4">
				<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			<br> 
			Product 5
			<select name="warehouse5">
				<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			<br> 
			Product 6
			<select name="warehouse6">
				<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			<br> 
			Product 7
			<select name="warehouse7">
			<option>---select---</option>
				<c:forEach var="tempProduct" items="${PRODUCT1_LIST}">
					<option>${tempProduct.productName}</option>
				</c:forEach>
	
			</select>

			<br>
			<br>
				
			<input type="submit" value="Add document" />

		</form>
	
			<br>
			<br>
			
			<table>

				<tr>
					<th>Customer</th>
					<th>Reciepient</th>
					<th>No. of Doc</th>
					<th>Date</th>
					<th>Action</th>
				</tr>

				<c:forEach var="tempStudent" items="${STUDENT_LIST}">

					<tr>
						<td>${tempStudent.firstName}</td>
						<td>${tempStudent.lastName}</td>
						<td>${tempStudent.email}</td>
						<td><a href="${tempLink}">Update</a> | <a
							href="${deleteLink}"
							onClick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
								Delete</a></td>

					</tr>

				</c:forEach>


			</table>

		</div>

	</div>
</body>

</html>