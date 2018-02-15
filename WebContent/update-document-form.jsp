<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<c:set var="theLocale"
	value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale
 : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle
	basename="com.muczo.mvc.warehouse.i18h.resources.mylabels" />

<!DOCTYPE html>

<html>
<title><fmt:message key="label.title.update-document" /></title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
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
		<div class="w3-bar w3-green w3-card w3-left-align w3-large">
			<a
				class="w3-bar-item w3-button w3-hover-green w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-red"
				href="javascript:void(0);" onclick="myFunction()"
				title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a> <a
				href="Document1ControllerServlet"
				class="w3-bar-item w3-button w3-padding-large w3-white"><fmt:message
					key="label.menu.doc" /></a> <a href="Document2ControllerServlet"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"><fmt:message
					key="label.menu.doc2" /></a> <a href="InvoiceControllerServlet"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"><fmt:message
					key="label.menu.inv" /></a> <a href="ProviderControllerServlet"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"><fmt:message
					key="label.menu.prov" /></a> <a href="CustomerControllerServlet"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"><fmt:message
					key="label.menu.cus" /></a> <a href="ReciepientControllerServlet"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"><fmt:message
					key="label.menu.rec" /></a> <a href="ProductControllerServlet"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"><fmt:message
					key="label.menu.pro" /></a> <a href="PriceControllerServlet2"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"><fmt:message
					key="label.menu.pri" /></a> <a href="EmployeesControllerServlet"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"><fmt:message
					key="label.menu.emp" /></a> <a href="WarehouseControllerServlet"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"><fmt:message
					key="label.menu.war" /></a> <a href="ProductionControllerServlet"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"><fmt:message
					key="label.production" /></a> <a href="LogoutServlet"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Logout</a>
		</div>

		<!-- Navbar on small screens -->
		<div id="navDemo"
			class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium">
			<a href="Document2ControllerServlet"
				class="w3-bar-item w3-button w3-padding-large"><fmt:message
					key="label.menu.doc2" /></a> <a href="InvoiceControllerServlet"
				class="w3-bar-item w3-button w3-padding-large"><fmt:message
					key="label.menu.inv" /></a> <a href="ProviderControllerServlet"
				class="w3-bar-item w3-button w3-padding-large"><fmt:message
					key="label.menu.prov" /></a> <a href="CustomerControllerServlet"
				class="w3-bar-item w3-button w3-padding-large"><fmt:message
					key="label.menu.cus" /></a> <a href="ReciepientControllerServlet"
				class="w3-bar-item w3-button w3-padding-large"><fmt:message
					key="label.menu.rec" /></a> <a href="ProductControllerServlet"
				class="w3-bar-item w3-button w3-padding-large"><fmt:message
					key="label.menu.pro" /></a> <a href="PriceControllerServlet2"
				class="w3-bar-item w3-button w3-padding-large"><fmt:message
					key="label.menu.pri" /></a> <a href="EmployeesControllerServlet"
				class="w3-bar-item w3-button w3-padding-large"><fmt:message
					key="label.menu.emp" /></a> <a href="WarehouseControllerServlet"
				class="w3-bar-item w3-button w3-padding-large"><fmt:message
					key="label.menu.war" /></a> <a href="LogoutServlet"
				class="w3-bar-item w3-button w3-padding-large">Logout</a>
		</div>
	</div>

	<!-- Header -->
	<header class="w3-container w3-green w3-center w3-margin-bottom "
		style="padding: 75px 16px; background-image: url(images/tektura.jpg)">




		<hr>
		<h1>
			<fmt:message key="label.title.update-document" />
		</h1>

		<hr>

	</header>
	
	<div class="w3-panel w3-border w3-border-green w3-margin">
		<div class="w3-display-container" style="height: 50px;">
			<div class="w3-display-left">Jesteś zalogowany jako ${userName}</div>
			<div class="w3-display-right">
				<a href="Document1ControllerServlet?command=LOAD-DOCUMENT&documentId=${THE_DOCUMENT.id}&theLocale=en_US">Polski
		(PL)</a> 
				| <a href="Document1ControllerServlet?command=LOAD-DOCUMENT&documentId=${THE_DOCUMENT.id}&theLocale=pl_PL">English
		(US)</a>
			</div>
		</div>
	</div>

<div class="w3-row-padding w3-light-grey w3-padding-64 w3-container">

		
		<form action="Document1ControllerServlet" method="GET">

			<input type="hidden" name="command" value="UPDATE-DOCUMENT" /> <input
				type="hidden" name="documentId" value="${THE_DOCUMENT.id}" />

		
			
			
			
				<ul class ="w3-ul">
				<li>
				
					<label><fmt:message key="label.customer" />:</label>
				
				
						<input type="text" name="customer"
							value="${THE_DOCUMENT.customer}" />
						
				</li>
				<li>
					<label><fmt:message key="label.reciepient" />:</label>
						<input type="text" name="reciepient"
							value="${THE_DOCUMENT.reciepient}" />
				</li>
				
				<li>
						<label><fmt:message key="label.docno" />:</label>
						<input type="text" name="noOfDoc"
							value="${THE_DOCUMENT.noOfDoc}" />
				</li>
				<li>
						<label><fmt:message key="label.date" />:</label>
						<input type="text" name="date"
							value="${THE_DOCUMENT.date}" />
				</li>			
							
			
				<li>
				<label><fmt:message key="label.product" /> 1:</label>
						<input type="text" name="product1"
							value="${THE_DOCUMENT.product1}" />
						<label><fmt:message key="label.qty" />:</label>
						<input type="text" name="qty1"
							value="${THE_DOCUMENT.qty1}" />
							
									<label><fmt:message key="label.info" />: 1</label>
						<input name="info1" value="${THE_DOCUMENT.info1}" />
				</li>		
				
				<li>
				<label><fmt:message key="label.product" /> 2:</label>
						<input type="text" name="product2"
							value="${THE_DOCUMENT.product2}" />
						<label><fmt:message key="label.qty" />:</label>
						<input type="text" name="qty2"
							value="${THE_DOCUMENT.qty2}" />
							
							<label><fmt:message key="label.info" />: 2</label>
						<input name="info2" value="${THE_DOCUMENT.info2}" />
			    </li>
			  
				<li>
					<label><fmt:message key="label.product" /> 3:</label>
						<input type="text" name="product3"
							value="${THE_DOCUMENT.product3}" />
						<label><fmt:message key="label.qty" />:</label>
						<input type="text" name="qty3"
							value="${THE_DOCUMENT.qty3}" />
							
								<label><fmt:message key="label.info" />: 3</label>
						<input name="info3" value="${THE_DOCUMENT.info3}" />
				</li>
							
				<li>
				<label><fmt:message key="label.product" /> 4:</label>
					<input type="text" name="product4"
							value="${THE_DOCUMENT.product4}" />
						<label><fmt:message key="label.qty" />:</label>
						<input type="text" name="qty4"
							value="${THE_DOCUMENT.qty4}" />
							
								<label><fmt:message key="label.info" />: 4</label>
						<input name="info4" value="${THE_DOCUMENT.info4}" />
					
				</li>
				<li>	
						<label><fmt:message key="label.product" /> 5:</label>
						<input type="text" name="product5"
							value="${THE_DOCUMENT.product5}" />
						<label><fmt:message key="label.qty" />:</label>
						<input type="text" name="qty5"
							value="${THE_DOCUMENT.qty5}" />
							
									<label><fmt:message key="label.info" />: 5</label>
						<input name="info5" value="${THE_DOCUMENT.info5}" />
				</li>

				<li>
						<label><fmt:message key="label.product" /> 6:</label>
						<input type="text" name="product6"
							value="${THE_DOCUMENT.product6}" />
						<label><fmt:message key="label.qty" />:</label>
						<input type="text" name="qty6"
							value="${THE_DOCUMENT.qty6}" />
							
							<label><fmt:message key="label.info" />: 6</label>
						<input name="info6" value="${THE_DOCUMENT.info6}" />
				</li>
				<li>	
						<label><fmt:message key="label.product" /> 7:</label>
						<input type="text" name="product7"
							value="${THE_DOCUMENT.product7}" />
						<label><fmt:message key="label.qty" />:</label>
						<input type="text" name="qty7"
							value="${THE_DOCUMENT.qty7}" />
							
									
						<label><fmt:message key="label.info" />: 7</label>
						<input name="info7" value="${THE_DOCUMENT.info7}" />
				</li>	
			
				
			

  <li>
  <button type="submit" class="w3-button w3-green  w3-hover-red w3-padding-large w3-large w3-margin-top">
					
		     <fmt:message key="label.button.save"/> </button>
  

	</li>			
		     
		     
			
			</ul>
		
				
		</form>
</div>
		<div style="clear: both;"></div>

		<p>
			<a href="Document1ControllerServlet"><fmt:message
					key="label.back.home" /></a>
		</p>

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



