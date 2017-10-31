<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>
	<title>Real warehouse web app</title>
	
	<link type="text/css" rel="stylesheet" href="css/style2.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    

    
</head>


<body>


	<div id="wrapper">
		<div id="header">
			<h2>Create warehouse doc...</h2>
		</div>
	</div>
	
	<div id="container">
		
		<div id="content">
		
		<ul>
  			<li><a href="create-customer.jsp">Documents</a></li>
 			<li><a href="#Add customer">Invoices</a></li>
  			<li><a href="create-customer.jsp">Customers</a></li>
  			<li><a href="#Add product">Reciepient</a></li>
  			<li><a href="#Add product">Products</a></li>
  			<li><a href="#Add product">Price List</a></li>
  			<li><a href="#Add product">Employess</a></li>
		</ul>
		<br>
		<br>
		<form action = "WarehouseControllerServlet" method="GET">
		<input type="hidden" name="command" value="LIST-PRODUCTS"/>
		
			Product 1 ...from which warehouse?
			<select style="width:15em" name="warehouse1">
				<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			<br> 
			Product 2 ...from which warehouse?
			<select style="width:15em" name="warehouse2">
				<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			<br> 
			Product 3 ...from which warehouse?
			<select style="width:15em" name="warehouse3">
				<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			<br> 
			Product 4 ...from which warehouse?
			<select style="width:15em" name="warehouse4">
				<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			<br> 
			Product 5 ...from which warehouse?
			<select style="width:15em" name="warehouse5">
				<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			<br> 
			Product 6 ...from which warehouse?
			<select style="width:15em" name="warehouse6">
				<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			<br> 
			Product 7 ...from which warehouse?
			<select style="width:15em" name="warehouse7">
			<option>---select---</option>
				<option>main-warehouse</option>
				<option>production-warehouse</option>
				<option>czech-warehouse</option>
				<option>small-production-warehouse</option>
			</select>
			
			<br>
			<br>
			<!-- put new button: precreate doc -->
			<input type="submit" value="Precreate Doc">
		</form>
		
			<br>
			<br>
	
	<form action = "WarehouseControllerServlet" method="GET">
	<table>	
	<tr>
	
			<td style="width:100%" align="left">
			
			<input type="hidden" name="command" value="PRINT-DOCUMENT"/>
				<label for="datepicker">Enter date:</label>
                <input type="text" name="selDate" value="${TODAY_DATE}" id="datepicker">
				<br>
				<br>
				<br>
				-Customer  : 
				<select style="width:12em" name="customer">
					<option>---select---</option>
					<c:forEach var="tempCustomer" items="${CUSTOMERS_LIST}">
						<option>${tempCustomer.name}</option>
					</c:forEach>
				</select>

				<br>
				-Reciepient: 
				<select style="width:12em" name="reciepient">
					<option>---select---</option>
					<c:forEach var="tempReciepient" items="${RECIEPIENTS_LIST}">
						<option>${tempReciepient.name}</option>
					</c:forEach>
				</select>
                
				<br>
				
				-Product 1 :
				<select style="width:15em"name="product1">
					<option>---select---</option>
					<c:forEach var="tempProduct" items="${PRODUCT_LIST1}">
						<option>${tempProduct.name}</option>
					</c:forEach>
				</select>
				-Qty: <input type="text"name="qty1"/>
				<br>
			
				-Product 2 : 
				<select style="width:15em"name="product2">
					<option>---select---</option>
					<c:forEach var="tempProduct" items="${PRODUCT_LIST2}">
						<option>${tempProduct.name}</option>
					</c:forEach>
				</select>
				-Qty: <input type="text"name="qty2"/>
				<br>
			
				-Product 3 : 
				<select style="width:15em"name="product3">
					<option>---select---</option>
					<c:forEach var="tempProduct" items="${PRODUCT_LIST3}">
						<option>${tempProduct.name}</option>
					</c:forEach>
				</select>
				-Qty: <input type="text"name="qty3"/>
				<br>
			
				-Product 4 : 
				<select style="width:15em"name="product4">
					<option>---select---</option>
					<c:forEach var="tempProduct" items="${PRODUCT_LIST4}">
						<option>${tempProduct.name}</option>
					</c:forEach>
				</select>
				-Qty: <input type="text"name="qty4"/>
				<br>
			
				-Product 5 : 
				<select style="width:15em"name="product5">
					<option>---select---</option>
					<c:forEach var="tempProduct" items="${PRODUCT_LIST5}">
						<option>${tempProduct.name}</option>
					</c:forEach>
				</select>
				-Qty: <input type="text"name="qty5"/>
				<br>
			
				-Product 6 : 
				<select style="width:15em"name="product6">
					<option>---select---</option>
					<c:forEach var="tempProduct" items="${PRODUCT_LIST6}">
						<option>${tempProduct.name}</option>
					</c:forEach>
				</select>
				-Qty: <input type="text"name="qty6"/>
				<br>
			
				-Product 7 : 
				<select style="width:15em"name="product7">
					<option>---select---</option>
					<c:forEach var="tempProduct" items="${PRODUCT_LIST7}">
						<option>${tempProduct.name}</option>
					</c:forEach>
				</select>
				-Qty: <input type="text"name="qty7"/>
			
				<br>
				<br>
				<input type="submit" value="Save Doc">
				<br>
				<br>
				
			</td>
			
			<td align="left">
			
				<textarea name="information" cols="60" rows="10"></textarea>
			</td>
			
		</tr>
		
	</table>		

		</form>
			
			
			<br>
			<br>
			<table>
				
				<tr>
					<th> Id </th>
					<th> Date </th>
					<th> Customer</th>
					<th> Reciepient </th>
					<th> Doc no. </th>
					<th> Action </th>
				</tr>
			
				<c:forEach var="tempDoc" items="${DOCUMENTS_LIST}">
				
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD"/>
						<c:param name="studentId" value="${tempDoc.id}"/>
					</c:url>
				
					<!-- set up a link to delete a student -->
					<c:url var="deleteLink" value="StudentControllerServlet">
						<c:param name="command" value="DELETE"/>
						<c:param name="studentId" value="${tempDoc.id}"/>
					</c:url>
					
					<tr>
					
						<td>${tempDoc.id} </td>
						<td>${tempDoc.date} </td>
						<td>${tempDoc.customer} </td>
						<td>${tempDoc.reciepient} </td>
						<td>${tempDoc.noOfDoc} </td>
						
						<td> <a href="${tempLink}">Update</a>
							|
							<a href="${deleteLink}"
							onClick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
							Delete</a>
						</td>
						
					</tr>
					
				</c:forEach>

				
			</table>
			
		</div>
		
	</div>
	
	<script>
            $(function () {
             
            $('#datepicker').datepicker();
            });
    </script>
	
</body>

</html>

    