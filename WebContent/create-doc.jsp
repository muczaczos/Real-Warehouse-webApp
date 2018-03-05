<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="com.muczo.mvc.warehouse.i18h.resources.mylabels" />

<!DOCTYPE html>

<html>
	<title><fmt:message key="label.title.create-doc" /></title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<style>
	
		body, h1, h2, h3, h4, h5, h6 {
		font-family: "Lato", sans-serif
		}		

		.w3-bar, h1, button {
		font-family: "Montserrat", sans-serif
		}

		.fa-anchor, .fa-coffee, .fa-question, .fa-info-circle {
		font-size: 200px
		}	

		.fa-hourglass-start, .fa-balance-scale, .fa-phone, .fa-envelope {
		font-size: 100px
		}

		.fa-facebook-official, .fa-youtube, .fa-twitter-square,
		.fa-google-plus-square {
		font-size: 35px
		}
		
	</style>

	<body>

		<!-- Navbar -->
		<div class="w3-top">
			<div class="w3-bar w3-black w3-card w3-left-align w3-large">
				<a class="w3-bar-item w3-button w3-hover-green w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-red"
				href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a> 
				
				<a href="Document1ControllerServlet" class="w3-bar-item w3-button w3-padding-large w3-white">
				<fmt:message key="label.menu.doc" /></a> 
			
				<a href="Document2ControllerServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">
				<fmt:message key="label.menu.doc2" /></a> 
			
				<a href="InvoiceControllerServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">
				<fmt:message key="label.menu.inv" /></a> 
			
				<a href="ProviderControllerServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">
				<fmt:message key="label.menu.prov" /></a> 
			
				<a href="CustomerControllerServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">
				<fmt:message key="label.menu.cus" /></a> 
			
				<a href="ReciepientControllerServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">
				<fmt:message key="label.menu.rec" /></a> 
			
				<a href="ProductControllerServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">
				<fmt:message key="label.menu.pro" /></a> 
		
				<a href="PriceControllerServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">
				<fmt:message key="label.menu.pri" /></a> 
			
				<a href="EmployeesControllerServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">
				<fmt:message key="label.menu.emp" /></a> 
			
				<a href="WarehouseControllerServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">
			
				<fmt:message key="label.menu.war" /></a> 
				<a href="ProductionControllerServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">
			
				<fmt:message key="label.production" /></a> 
				<a href="LogoutServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Logout</a>
		   </div>

			<!-- Navbar on small screens -->
			<div id="navDemo"
			
				class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium">
			
				<a href="Document2ControllerServlet" class="w3-bar-item w3-button w3-padding-large"><fmt:message key="label.menu.doc2" /></a> 
				
				<a href="InvoiceControllerServlet" class="w3-bar-item w3-button w3-padding-large"><fmt:message key="label.menu.inv" /></a> 
				
				<a href="ProviderControllerServlet" class="w3-bar-item w3-button w3-padding-large"><fmt:message key="label.menu.prov" /></a> 
			
				<a href="CustomerControllerServlet" class="w3-bar-item w3-button w3-padding-large"><fmt:message key="label.menu.cus" /></a> 
			
				<a href="ReciepientControllerServlet" class="w3-bar-item w3-button w3-padding-large"><fmt:message key="label.menu.rec" /></a> 
			
				<a href="ProductControllerServlet" class="w3-bar-item w3-button w3-padding-large"><fmt:message key="label.menu.pro" /></a> 
			
				<a href="PriceControllerServlet" class="w3-bar-item w3-button w3-padding-large"><fmt:message key="label.menu.pri" /></a> 
			
				<a href="EmployeesControllerServlet" class="w3-bar-item w3-button w3-padding-large"><fmt:message key="label.menu.emp" /></a> 
			
				<a href="WarehouseControllerServlet" class="w3-bar-item w3-button w3-padding-large"><fmt:message key="label.menu.war" /></a> 
				
				<a href="LogoutServlet" class="w3-bar-item w3-button w3-padding-large">Logout</a>
				
			</div>
			
		
		</div>


		<!-- Header -->
		<header class="w3-container w3-green w3-center w3-margin-bottom " style="padding: 75px 16px; background-image: url(images/tektura.jpg)">

			<hr>
			
			<h1>	<fmt:message key="label.h2.create-doc" />	</h1>
			
			<hr>

		</header>
		
		<div class="w3-panel w3-border w3-padding-16 w3-border-green w3-margin">
			<div class="w3-display-container" style="height: 50px;">
		
				<div class="w3-display-left">Jesteś zalogowany jako ${userName}</div>
			
				<div class="w3-display-right">
					<div class="w3-text-blue">
						<a href="Document1ControllerServlet?theLocale=en_US">Polski (PL)</a> | 
						<a href="Document1ControllerServlet?theLocale=pl_PL">English (US)</a>
					</div>
				</div>
			
			</div>
		</div>



		<!-- First Grid -->
		<div class="w3-row-padding w3-dark-grey w3-padding-64 w3-container">
			<div class="w3-container w3-third">
				<div class="w3-panel w3-gray w3-padding-16">

					<h2><fmt:message key="label.menu.cus" /></h2>

					<form action="Document1ControllerServlet" method="GET">
						<input type="hidden" name="command" value="FIRST-LIST" />
							<table>
								<tbody>
									<tr>
										<td><fmt:message key="label.customer" /> : </td>
										<td> 
											<select style="width: 12em" name="customer">
												<option>---select---</option>
												<c:forEach var="tempCustomer" items="${CUSTOMERS_LIST}">
													<option>${tempCustomer.name}</option>
												</c:forEach>
											</select> 
										</td>
									</tr>
									<!-- warehouses
									<tr>
										<td><fmt:message key="label.product" /> 1</td>
										<td>
											<select style="width: 15em" name="warehouse1">
												<option>---select---</option>
												<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
													<option>${tempCombo.name}</option>
												</c:forEach>
											</select> 
										</td>
									</tr>
									<tr>
										<td><fmt:message key="label.product" /> 2</td>
										<td>
											<select style="width: 15em" name="warehouse2">
												<option>---select---</option>
												<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
													<option>${tempCombo.name}</option>
												</c:forEach>
											</select> 
										</td>
									</tr>
									<tr>
										<td> <fmt:message key="label.product" /> 3</td>
										<td>
											<select style="width: 15em" name="warehouse3">
												<option>---select---</option>
												<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
													<option>${tempCombo.name}</option>
												</c:forEach>
											</select> 
										</td>
									</tr>
									<tr>
										<td><fmt:message key="label.product" /> 4</td>
										<td>
										    <select style="width: 15em" name="warehouse4">
												<option>---select---</option>
												<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
													<option>${tempCombo.name}</option>
												</c:forEach>
											</select> 
										</td>
									</tr>
									<tr>
										<td><fmt:message key="label.product" /> 5 </td>
										<td>
											<select style="width: 15em" name="warehouse5">
												<option>---select---</option>
												<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
													<option>${tempCombo.name}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td><fmt:message key="label.product" /> 6</td>
										<td>
				 							<select style="width: 15em" name="warehouse6">
												<option>---select---</option>
												<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
													<option>${tempCombo.name}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td><fmt:message key="label.product" /> 7</td>
										<td>
											<select style="width: 15em" name="warehouse7">
												<option>---select---</option>
												<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
													<option>${tempCombo.name}</option>
												</c:forEach>
											</select>
										</td>
									</tr> -->
									<tr>	
										<!-- put new button: precreate doc -->
										<td>
 											  <button type="submit" class="w3-button w3-green w3-hover-red w3-padding-large w3-large w3-margin-top">
		    								  <fmt:message key="label.button.precreate"/> </button>
		    							</td>
		    						</tr>
		    					</tbody>
		    				</table>
							</form>
						</div>
					</div>


					<div class="w3-container w3-twothird">
						<div class="w3-panel w3-gray w3-padding-16">

						<h2><fmt:message key="label.create-doc" /></h2>

						<form action="Document1ControllerServlet" method="GET">
							<input type="hidden" name="command" value="ADD-DOCUMENT" /> 
							<table>
								<tbody>
									<tr>
										<td>- <fmt:message key="label.customer" /> :</td>
										<td> <input type="text" name="preCustomer" value="${preCustomer}" /></td>
										<td> <label for="datepicker"><fmt:message key="label.enter.date" />:</label></td>
										<td><input type="text" name="date" value="${TODAY_DATE}" id="datepicker"></td>
									</tr>
									<tr>
										<td>- <fmt:message key="label.noOfDoc" /> : </td> 
										<td> <input type="text" name="docId" value="${noOfDoc}" /> </td> 
									</tr>
									<tr>
										<td>- <fmt:message key="label.reciepient" /> : </td>
										<td>
											<select style="width: 12em" name="reciepient">
												<option>---select---</option>
												<c:forEach var="tempReciepient" items="${RECIEPIENTS_LIST}">
													<option>${tempReciepient.name}</option>
												</c:forEach>
											</select> 
										</td>
									</tr>
									<tr>
										<td> - <fmt:message key="label.product" /> 1 :</td>
										<td>
											<select style="width: 15em" name="product1">
												<option>---select---</option>
												<c:forEach var="tempProduct" items="${PRODUCTS_LIST}">
													<option>${tempProduct.productName}</option>
												</c:forEach>
											</select> 
										</td>
										<td>- <fmt:message key="label.qty" /> : </td>
										<td><input type="text" name="qty1" /> </td>
										<td>-info: <input type="text" name="info1" /></td>
									 </tr>
									 <tr>
									 	<td> - <fmt:message key="label.product" /> 2 :</td>
									 	<td>
									 		<select style="width: 15em" name="product2">
												<option>---select---</option>
												<c:forEach var="tempProduct" items="${PRODUCTS_LIST}">
													<option>${tempProduct.productName}</option>
												</c:forEach>
											</select> 
										</td>
										<td>- <fmt:message key="label.qty" /> : </td>
										<td><input type="text" name="qty2" /> </td>
										<td>-info: <input type="text" name="info2" /></td>
									 </tr>
									 <tr>
									 	<td> - <fmt:message key="label.product" /> 3 :</td>
									 	<td>
									 		<select style="width: 15em" name="product3">
												<option>---select---</option>
												<c:forEach var="tempProduct" items="${PRODUCTS_LIST}">
													<option>${tempProduct.productName}</option>
												</c:forEach>
											</select> 
										</td>	
										<td>- <fmt:message key="label.qty" /> : </td>
										<td><input type="text" name="qty3" /> </td>
										<td>-info: <input type="text" name="info3" /> </td> 
									</tr>
									<tr>
										<td> - <fmt:message key="label.product" /> 4 :</td>
										<td>
											<select style="width: 15em" name="product4">
												<option>---select---</option>
												<c:forEach var="tempProduct" items="${PRODUCTS_LIST}">
													<option>${tempProduct.productName}</option>
												</c:forEach>
											</select>
										</td>
										<td> - <fmt:message key="label.qty" /> : </td>
										<td><input type="text" name="qty4" /> </td>
										<td>-info: <input type="text"name="info4" /></td>
									</tr>
									<tr>
										<td> - <fmt:message key="label.product" /> 5 :</td>
										<td>
											<select style="width: 15em" name="product5">
												<option>---select---</option>
												<c:forEach var="tempProduct" items="${PRODUCTS_LIST}">
													<option>${tempProduct.productName}</option>
												</c:forEach>
											</select> 
										</td>
										<td> - <fmt:message key="label.qty" /> : </td>
										<td> <input type="text" name="qty5" /></td>
										<td>-info: <input type="text" name="info5" /></td> 
									</tr>
									<tr>
										<td> - <fmt:message key="label.product" /> 6 : </td>
										<td>
											<select style="width: 15em" name="product6">
												<option>---select---</option>
												<c:forEach var="tempProduct" items="${PRODUCTS_LIST}">
													<option>${tempProduct.productName}</option>
												</c:forEach>
											</select> 
										</td>
										<td> - <fmt:message key="label.qty" /> : </td>
										<td><input type="text" name="qty6" /></td>
										<td>-info: <input type="text" name="info6" /></td>
									</tr>
									<tr>
										<td> - <fmt:message key="label.product" /> 7 : </td>
										<td>
											<select style="width: 15em" name="product7">
												<option>---select---</option>
												<c:forEach var="tempProduct" items="${PRODUCTS_LIST}">
													<option>${tempProduct.productName}</option>
												</c:forEach>
											</select>
										</td>
										<td> - <fmt:message key="label.qty" /> : </td>
										<td><input type="text" name="qty7" /></td>
										<td> -info: <input type="text" name="info7" /></td>
									</tr>
									<tr>
										<td><button type="submit" class="w3-button w3-green w3-hover-red w3-padding-large w3-large w3-margin-top">
					 						<fmt:message key="label.button.save"/> </button>
					 					</td>
					 				</tr>
					 			</tbody>
					 		</table>
							</form>

			</div>
		</div>

		</div>

		<!-- Second Grid -->
		<div class="w3-row-padding w3-padding-16 w3-container w3-light-gray">
			<div class="w3-container w3-third w3-border w3-padding-16 w3-border-green w3-margin">
				 <form action="" class="w3-container">
				 	
				 	<h2>Search documents by...</h2>

					<label class="w3-text-black"><b>Customer</b></label>
					<select class="w3-select" name="customer">
						<option value="" disabled selected>Choose your customer</option>
						<c:forEach var="tempCustomer" items="${CUSTOMERS_LIST}">
							<option>${tempCustomer.name}</option>
						</c:forEach>
					</select> 
			
 
					<label class="w3-text-black"><b>Last Name</b></label>
					<input class="w3-input w3-border" type="text">

					<button class="w3-btn w3-green w3-margin">Filtruj</button>
				</form>
			</div>
		</div>
			
		<!-- Third Grid -->
		<div class="w3-row-padding w3-padding-16 w3-container w3-light-gray">
		
				 <table class="w3-table w3-bordered">
   				 	<tr class="w3-light-green">
   				 		<th>
								Id
						</th>
						<th>
								<fmt:message key="label.date" />
						</th>
						<th>
								<fmt:message key="label.customer" />
						</th>
						<th>
								<fmt:message key="label.reciepient" />
						</th>
						<th>
								<fmt:message key="label.docno" />
						</th>
						<th>
								<fmt:message key="label.action" />
						</th>
					</tr>

					<c:forEach var="tempDoc" items="${DOCUMENTS_LIST}">

						<!-- set up a link for each documents  -->
						<c:url var="tempLink" value="Document1ControllerServlet">
						<c:param name="command" value="LOAD-DOCUMENT" />
						<c:param name="documentId" value="${tempDoc.id}" />
						</c:url>

						<!-- set up a link to delete a document -->
						<c:url var="deleteLink" value="Document1ControllerServlet">
						<c:param name="command" value="DELETE-DOCUMENT" />
						<c:param name="documentId" value="${tempDoc.id}" />
						</c:url>

						<!-- set up a link to delete a document -->
						<c:url var="generateLink" value="Document1ControllerServlet">
						<c:param name="command" value="PRINT-DOCUMENT" />
						<c:param name="documentId" value="${tempDoc.id}" />
						</c:url>

						<!-- set up a link to delete a document -->
						<c:url var="downloadLink" value="DownloadControllerServlet">
						<c:param name="command" value="PRINT-DOCUMENT" />
						<c:param name="documentId" value="${tempDoc.id}" />
						</c:url>

						<tr>
							<td>
								${tempDoc.id}
							</td>
							<td>
								${tempDoc.date}
							</td>
							<td>
								${tempDoc.customer}
							</td>
							<td>
								${tempDoc.reciepient}
							</td>
							<td>
								${tempDoc.noOfDoc}
							</td>
							<td>
								<div class="w3-text-blue">
									<a href="${tempLink}"><fmt:message key="label.update" /></a> | 
									<a href="${deleteLink}" onClick="if (!(confirm('<fmt:message key="label.delete.message"/>'))) return false">
									<fmt:message key="label.delete" /> </a> | 
									<a href="${generateLink}"><fmt:message key="label.generate.doc" /></a> 
									<!--Remember!!!  <a href="${downloadLink}"><fmt:message key="label.download.doc" /></a> -->
							   </div>
							 </td>
							</tr>
					</c:forEach>
				</table>
			</div>
		
		<!-- Third Grid -->
		<div class="w3-row-padding w3-padding-64 w3-container">
		
			<hr>
			
		</div>

		<script>
			$(function() {

			$('#datepicker').datepicker({
				dateFormat : 'dd/mm/yy'
			});
			});
		</script>
	
		<script>
			// Used to toggle the menu on small screens when clicking on the menu button
			function myFunction() {
    			var x = document.getElementById("navDemo");
  		 		 if (x.className.indexOf("w3-show") == -1) {
      	 			 x.className += " w3-show";
 		  		 } else { 
       			 	x.className = x.className.replace(" w3-show", "");
   			 	 }
			}
	
		</script>

	</body>

</html>

