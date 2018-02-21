<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="com.muczo.mvc.warehouse.i18h.resources.mylabels" />

<!DOCTYPE html>

<html>
	<title><fmt:message key="label.title.create-price" /></title>
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
			
			<h1>	<fmt:message key="label.h2.create-price" />	</h1>
			
			<hr>

		</header>
		
		<div class="w3-panel w3-border w3-padding-16 w3-border-green w3-margin">
		<div class="w3-display-container" style="height: 50px;">
		
			<div class="w3-display-left">Jesteś zalogowany jako ${userName}</div>
			
			<div class="w3-display-right">
				<div class="w3-text-blue">
					<a href="PriceControllerServlet?theLocale=en_US">Polski (PL)</a> |
					<a href="PriceControllerServlet?theLocale=pl_PL">English (US)</a>
				</div>
			</div>
			
		</div>
		</div>

		<!-- First Grid -->
		<div class="w3-row-padding w3-padding-16 w3-container w3-light-gray">
			<div class="w3-content w3-third">
				<div class="w3-panel w3-border w3-border-red w3-padding-16">

				<form action="PriceControllerServlet" method="GET">
				<input type="hidden" name="command" value="ADD-PRICE" />

				<table>
					<tbody>
					
						<tr>
							<td><label><fmt:message key="label.customer" />: </label></td>
							<td><select style="width: 15em" name="customer">
								<option>---select---</option>
								<c:forEach var="tempCombo" items="${Customers }">
									<option>${tempCombo.name}</option>
								</c:forEach>
							  </select>
						    </td>
					   </tr>

					   <tr>
							<td><label><fmt:message key="label.product" />: </label></td>
							<td><select style="width: 15em" name="product">
								<option>---select---</option>
								<c:forEach var="tempCombo" items="${Products}">
									<option>${tempCombo.productName}</option>
								</c:forEach>
								</select>
							</td>
						</tr>

						<tr>
							<td><label><fmt:message key="label.price" />: </label></td>
							<td><input type="text" name="price" /></td>
						</tr>

						<tr>
							<td><button type="submit" class="w3-button w3-green w3-hover-red w3-padding-large w3-large w3-margin-top">
		     							<fmt:message key="label.button.save"/> </button></td>
						</tr>

					</tbody>
				</table>
				</form>
		
		</div>
		</div>
		
		<!-- Second Grid -->
		<div class="w3-row-padding w3-padding-64 w3-container w3-light-gray">
			<div class="w3-panel w3-border w3-border-green w3-margin">
			<form action="PriceControllerServlet" method="GET">
			
				<ul class="w3-ul">
				
					<li>
					
						<div class="w3-row w3-container">
						
		 					<div class="w3-row">
		 					
		   						<div class="w3-col m2 w3-green">
		   							Id
		   						</div>
		   						<div class="w3-col m2 w3-green">
									<fmt:message key="label.customer" />
								</div>
								<div class="w3-col m2 w3-green">
									<fmt:message key="label.product" />
								</div>
								<div class="w3-col m2 w3-green">
									<fmt:message key="label.price" />
								</div>
								<div class="w3-col m2 w3-green">
									<fmt:message key="label.action" />
								</div>
								<div class="w3-col m2 w3-green">
									...
								</div>
							</div>
						</div>
					</li>
			

					<c:forEach var="tempPri" items="${Prices}">
						<!-- set up a link for each customers  -->
						<c:url var="tempLink" value="PriceControllerServlet">
						<c:param name="command" value="LOAD-PRICE" />
						<c:param name="priceId" value="${tempPri.id}" />
						</c:url>

						<!-- set up a link to delete a document -->
						<c:url var="deleteLink" value="PriceControllerServlet">
						<c:param name="command" value="DELETE-PRICE" />
						<c:param name="priceId" value="${tempPri.id}" />
						</c:url>
					
						<li>
							<div class="w3-row w3-container">
						
								<div class="w3-row">
									
									<div class="w3-col m2">
										${tempPri.id}
									</div>
									<div class="w3-col m2">
										${tempPri.customer}
									</div>
									<div class="w3-col m2">
										${tempPri.product}
									</div>
									<div class="w3-col m2">
										${tempPri.price}
									</div>
									<div class="w3-col m2 w3-text-blue">
										<a href="${tempLink}"><fmt:message key="label.update" /></a>
										| <a href="${deleteLink}"
										onClick="if (!(confirm('<fmt:message key="label.delete.message"/>'))) return false">
										<fmt:message key="label.delete" />
										</a>
									</div>
								</div>
							</div>
					</c:forEach>
				</ul>
			</form>
			</div>
		</div>
	  </div>
	  
	<hr>
	  <div class="w3-text-blue w3-margin">
			<a href="Document1ControllerServlet"><fmt:message
					key="label.back.home" /></a>
	 </div>
	<hr>
	
  </body>

</html>