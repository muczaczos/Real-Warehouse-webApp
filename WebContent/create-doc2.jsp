<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="com.muczo.mvc.warehouse.i18h.resources.mylabels" />

<!DOCTYPE html>

<html>
	<title><fmt:message key="label.title.create-doc2" /></title>
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
				
				<a	href="Document1ControllerServlet" class="w3-bar-item w3-button w3-padding-large w3-white">
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
			
			<h1>	<fmt:message key="label.h2.create-doc2" />	</h1>
			
			<hr>

		</header>
		
		<div class="w3-panel w3-border w3-padding-16 w3-border-green w3-margin">
		<div class="w3-display-container" style="height: 50px;">
		
			<div class="w3-display-left">Jesteś zalogowany jako ${userName}</div>
			
			<div class="w3-display-right">
				<div class="w3-text-blue"> 
						<a href="Document2ControllerServlet?theLocale=en_US">Polski (PL)</a>
						| <a href="Document2ControllerServlet?theLocale=pl_PL">English (US)</a>
				</div>
			</div>
			
		</div>
		</div>

		<!-- First Grid -->
		<div class="w3-row-padding w3-padding-16 w3-container w3-dark-gray">
			<div class="w3-content w3-half">
				<div class="w3-panel w3-gray w3-padding-16">
				<form action="Document2ControllerServlet" method="GET">
				<input type="hidden" name="command" value="ADD-DOCUMENT2" />

				<table>
					<tbody>
						<tr>
							<td><label for="datepicker1"><fmt:message key="label.date" />:</label></td>
							<td><input type="text" name="date" value="${Date}" id="datepicker1"></td>
						</tr>
						<tr>
							<td><label><fmt:message key="label.noOfDoc" />: </label></td>
							<td><input type="text" name="documentNumber" /></td>
						</tr>

						<tr>
							<td><fmt:message key="label.provider" /></td>
							<td>
								<select style="width: 12em" name="provider">
									<option>---select---</option>
									<c:forEach var="tempProvider" items="${Providers}">
										<option>${tempProvider.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>

						<tr>
							<td><fmt:message key="label.product" />: 1</td>
							<td>
								<select style="width: 12em" name="product1">
									<option>---select---</option>
									<c:forEach var="tempProduct" items="${Products}">
										<option>${tempProduct.productName}</option>
									</c:forEach>
								</select>
							</td>
							<td><label><fmt:message key="label.qty" />: </label></td>
							<td><input type="text" name="qty1" /></td>
						</tr>

						<tr>
							<td><fmt:message key="label.product" />: 2</td>
							<td>
								<select style="width: 12em" name="product2">
									<option>---select---</option>
									<c:forEach var="tempProduct" items="${Products}">
										<option>${tempProduct.productName}</option>
									</c:forEach>
								</select>
							</td>
							<td><label><fmt:message key="label.qty" />: </label></td>
							<td><input type="text" name="qty2" /></td>
						</tr>

						<tr>
							<td><fmt:message key="label.product" />: 3</td>
							<td>
								<select style="width: 12em" name="product3">
									<option>---select---</option>
									<c:forEach var="tempProduct" items="${Products}">
										<option>${tempProduct.productName}</option>
									</c:forEach>
								</select>
							</td>
							<td><label><fmt:message key="label.qty" />: </label></td>
							<td><input type="text" name="qty3" /></td>
						</tr>

						<tr>
							<td><fmt:message key="label.product" />: 4</td>
							<td>
								<select style="width: 12em" name="product4">
									<option>---select---</option>
									<c:forEach var="tempProduct" items="${Products}">
										<option>${tempProduct.productName}</option>
									</c:forEach>
								</select>
							</td>
							<td><label><fmt:message key="label.qty" />: </label></td>
							<td><input type="text" name="qty4" /></td>
						</tr>

						<tr>
							<td><fmt:message key="label.product" />: 5</td>
							<td>
								<select style="width: 12em" name="product5">
									<option>---select---</option>
									<c:forEach var="tempProduct" items="${Products}">
										<option>${tempProduct.productName}</option>
									</c:forEach>
							</select>
							</td>
							<td><label><fmt:message key="label.qty" />: </label></td>
							<td><input type="text" name="qty5" /></td>
						</tr>
						<tr>
							<td><fmt:message key="label.product" />: 6</td>
							<td>
								<select style="width: 12em" name="product6">
									<option>---select---</option>
									<c:forEach var="tempProduct" items="${Products}">
										<option>${tempProduct.productName}</option>
									</c:forEach>
								</select>
							</td>
							<td><label><fmt:message key="label.qty" />: </label></td>
							<td><input type="text" name="qty6" /></td>
						</tr>
						<tr>
							<td><fmt:message key="label.product" />: 7</td>
							<td>
								<select style="width: 12em" name="product7">
									<option>---select---</option>
									<c:forEach var="tempProduct" items="${Products}">
										<option>${tempProduct.productName}</option>
									</c:forEach>
								</select>
							</td>
							<td><label><fmt:message key="label.qty" />: </label></td>
							<td><input type="text" name="qty7" /></td>
						</tr>
						<tr>
							<td>
						 	 <button type="submit" class="w3-button w3-green w3-hover-red w3-padding-large w3-large w3-margin-top">
								<fmt:message key="label.button.save"/> </button>
						</tr>
					</tbody>
				</table>
				</form>
				</div>
			</div>
		</div>
		
		<!-- Second Grid -->
			<div class="w3-row-padding w3-padding-64 w3-container w3-light-gray">
				<div class="w3-panel w3-margin">
					<form action="Document2ControllerServlet" method="GET">
					 <table class="w3-table w3-bordered">
   				 		<tr class="w3-light-green">
   				 			<th>
								Id
							</th>
							<th>
								<fmt:message key="label.provider" />
							</th>
							<th>
								<fmt:message key="label.date" />
							</th>
							<th>
								<fmt:message key="label.noOfDoc" />
							</th>
							<th>
								<fmt:message key="label.action" />
							</th>
						</tr>
					</table>

					<c:forEach var="tempDoc2" items="${documents2}">
						<!-- set up a link for each customers  -->
						<c:url var="tempLink" value="Document2ControllerServlet">
						<c:param name="command" value="LOAD-DOCUMENT2" />
						<c:param name="doc2Id" value="${tempDoc2.id}" />
						</c:url>

						<!-- set up a link to delete a document -->
						<c:url var="deleteLink" value="Document2ControllerServlet">
						<c:param name="command" value="DELETE-DOCUMENT2" />
						<c:param name="doc2Id" value="${tempDoc2.id}" />
						</c:url>

						<tr>
							<td>
								${tempDoc2.id}
							</td>
							<td>
								${tempDoc2.provider}
							</td>
							<td>
								${tempDoc2.date}
							</td>
							<td>
								${tempDoc2.documentNumber}
							</td>
							<td>
								<div class="w3-text-blue">
									<a href="${tempLink}"><fmt:message key="label.update" /></a>
									| <a href="${deleteLink}" onClick="if (!(confirm('<fmt:message key="label.delete.message"/>'))) return false">
									<fmt:message key="label.delete" /></a>
								</div>
							</td>
						</tr>
					</c:forEach>
				</form>
			</div>
		</div>
		
		<hr>
		
		<div class="w3-text-blue w3-margin">
			<a href="Document1ControllerServlet"><fmt:message key="label.back.home" /></a>
		</div>
		
		<hr>

		<script>
			$(function() {

			$('#datepicker1').datepicker({
				dateFormat : 'dd/mm/yy'
			});
			});
		</script>

	</body>

</html>