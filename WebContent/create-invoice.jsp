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
<title><fmt:message key="label.title.create-invoice" /></title>
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
					key="label.menu.pro" /></a> <a href="PriceControllerServlet"
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
					key="label.menu.pro" /></a> <a href="PriceControllerServlet"
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
			<fmt:message key="label.h2.create-invoice" />
		</h1>

		<hr>

	</header>

	<div class="w3-panel w3-border w3-border-green w3-margin">
		<div class="w3-display-container" style="height: 50px;">
			<div class="w3-display-left">Jesteś zalogowany jako ${userName}</div>
			<div class="w3-display-right">
				<a href="InvoiceControllerServlet?theLocale=en_US">Polski (PL)</a> |
	<a href="InvoiceControllerServlet?theLocale=pl_PL">English (US)</a>
			</div>
		</div>
	</div>


<div
		class="w3-row-padding w3-light-grey w3-padding-64 w3-container">

		<div class="w3-third">
<div class="w3-panel w3-border w3-border-red w3-padding-16">
			<form
				action="InvoiceControllerServlet?invcustomer=${invcustomer}&theLocale=pl_PL"
				method="GET">
				<input type="hidden" name="command" value="PRECREATE-INVOICE" />

				<fmt:message key="label.customer" />
				: <select style="width: 12em" name="invcustomer">
					<option>---select---</option>
					<c:forEach var="tempCustomer" items="${Customers}">
						<option>${tempCustomer.name}</option>
					</c:forEach>
				</select> <br> <label for="datepicker"><fmt:message
						key="label.enter.date" />:</label> <input type="text" name="invdate"
					value="${Date}" id="datepicker"> <br> <label><fmt:message
						key="label.invoice.no" />: </label> <input type="text" name="invnumber" />

				<br> <br>
				<!-- put new button: precreate doc -->
				<button type="submit" class="w3-button w3-green w3-hover-red w3-padding-large w3-large w3-margin-top">
					<fmt:message key="label.button.precreate"/> </button>
			
			</form>
</div>
</div>
</div>
			<br> <br> <br> <br>

			<form action="InvoiceControllerServlet" method="GET">
				<fmt:message key="label.customer" />
				: <select style="width: 12em" name="inv2customer">

					<option>${invcustomer}</option>
				</select> <input type="hidden" name="command" value="CALCULATE-INVOICE" />
<div class="w3-row">

		   <div class="w3-col w3-container w3-green w3-center" style="width:16.65%">
			
						<fmt:message key="label.check" />
						</div>
						<div class="w3-col w3-container w3-green w3-center" style="width:16.65%">
						Id
						</div>
						<div class="w3-col w3-container w3-green w3-center" style="width:16.65%">
						<fmt:message key="label.date" />
						</div>
						<div class="w3-col w3-container w3-green w3-center" style="width:16.65%">
						<fmt:message key="label.customer" />
						</div>
						<div class="w3-col w3-container w3-green w3-center" style="width:16.65%">
						<fmt:message key="label.reciepient" />
						</div>
						<div class="w3-col w3-container w3-green w3-center" style="width:16.65%">
						<fmt:message key="label.docno" />
						</div>
				
</div>


					<c:forEach var="tempDoc" items="${CUSTOM_DOCUMENTS_LIST}">

						<div class="w3-row">

		   <div class="w3-col w3-container w3-center" style="width:16.65%">
							<input type="checkbox" name="docId"
								value="${tempDoc.id}">
								</div>
								<div class="w3-col w3-container w3-center" style="width:16.65%">
							${tempDoc.id}
							</div>
							<div class="w3-col w3-container w3-center" style="width:16.65%">
							${tempDoc.date}
							</div>
							<div class="w3-col w3-container w3-center" style="width:16.65%">
							${tempDoc.customer}
							</div>
							<div class="w3-col w3-container w3-center" style="width:16.65%">
							${tempDoc.reciepient}
							</div>
							<div class="w3-col w3-container w3-center" style="width:16.65%">
							${tempDoc.noOfDoc}
							</div>

					
</div>

					</c:forEach>

					<!-- put new button: precreate doc -->



			

				<hr>


	<button type="submit" class="w3-button w3-green w3-hover-red w3-padding-large w3-large w3-margin">
					<fmt:message key="label.button.calculate"/> </button>


			</form>
<div class="w3-row ">
  <div class="w3-half w3-container">
  
				<textarea name="info" cols="80" rows="10"><c:out
						value="${amount}" /></textarea>
  	
</div>
<div class="w3-half w3-container">
			<form action="InvoiceControllerServlet" method="GET">
				<input type="hidden" name="command" value="ADD-INVOICE" /> <input
					type="hidden" name="grossAmount" value="${grossAmount}" /> <input
					type="hidden" name="startDocRange" value="${startDocRange}" /> <input
					type="hidden" name="endDocRange" value="${endDocRange}" /> <input
					type="hidden" name="customer" value="${inv2customer}" />
				<table>
					<tbody>
						<tr>
							<td><label><fmt:message key="label.employee.name" />:
							</label></td>
							<td><input type="text" name="name" /></td>
						</tr>

						<tr>
							<td><label><fmt:message key="label.invoice.no" />:
							</label></td>
							<td><input type="text" name="invNumber" /></td>
						</tr>

						<tr>
							<td><label for="datepicker2"><fmt:message
										key="label.date" />:</label></td>
							<td><input type="text" name="date" value="${Date}"
								id="datepicker2"></td>
						</tr>
						<tr>
<td>
							<button type="submit" class="w3-button w3-green w3-hover-red w3-padding-large w3-large w3-margin-top">
					<fmt:message key="label.button.save"/> </button></td>
						
						</tr>

					</tbody>
				</table>
			</form>
			</div>
</div>

			<hr>

			<form action="InvoiceControllerServlet" method="GET">

			<div class="w3-row w3-margin-left">

		   <div class="w3-col w3-container w3-green w3-center" style="width:12.5%">
						Id
						</div>
						 <div class="w3-col w3-container w3-green w3-center" style="width:12.5%">
						<fmt:message key="label.customer.name" />
						</div>
						 <div class="w3-col w3-container w3-green w3-center" style="width:12.5%">
						<fmt:message key="label.date" />
						</div>
						 <div class="w3-col w3-container w3-green w3-center" style="width:12.5%">
						<fmt:message key="label.invoice.no" />
						</div>
						 <div class="w3-col w3-container w3-green w3-center" style="width:12.5%">
						<fmt:message key="label.invoice.first.document" />
						</div>
						 <div class="w3-col w3-container w3-green w3-center" style="width:12.5%">
						<fmt:message key="label.invoice.last.document" />
						</div>
						 <div class="w3-col w3-container w3-green w3-center" style="width:12.5%">
						<fmt:message key="label.invoice.gross.amount" />
						</div>
						 <div class="w3-col w3-container w3-green w3-center" style="width:12.5%">
						<fmt:message key="label.action" />
				</div>	
		</div>
					<c:forEach var="tempInvoice" items="${Invoices}">
						<!-- set up a link for each customers  -->
						<c:url var="updateLink" value="InvoiceControllerServlet">
							<c:param name="command" value="LOAD-INVOICE" />
							<c:param name="invoiceId" value="${tempInvoice.id}" />
						</c:url>

						<!-- set up a link to delete a document -->
						<c:url var="deleteLink" value="InvoiceControllerServlet">
							<c:param name="command" value="DELETE-INVOICE" />
							<c:param name="invoiceId" value="${tempInvoice.id}" />
						</c:url>

					
			<div class="w3-row">

		   <div class="w3-col w3-container w3-center" style="width:12.5%">
							${tempInvoice.id}
							</div>
							  <div class="w3-col w3-container w3-center" style="width:12.5%">
							${tempInvoice.customer}
							</div>
							  <div class="w3-col w3-container w3-center" style="width:12.5%">
							${tempInvoice.date}
							</div>
							  <div class="w3-col w3-container w3-center" style="width:12.5%">
							${tempInvoice.invNumber}
							</div>
							  <div class="w3-col w3-container w3-center" style="width:12.5%">
							${tempInvoice.startDocRange}
							</div>
							  <div class="w3-col w3-container w3-center" style="width:12.5%">
							${tempInvoice.endDocRange}
							</div>
							  <div class="w3-col w3-container w3-center" style="width:12.5%">
							${tempInvoice.grossAmount}
</div>
  <div class="w3-col w3-container w3-center" style="width:12.5%">
  <div class="w3-text-blue">
							<a href="${updateLink}"><fmt:message
										key="label.update" /></a> | <a href="${deleteLink}"
								onClick="if (!(confirm('<fmt:message key="label.delete.message"/>'))) return false">
									<fmt:message key="label.delete" />
							</a>
							</div>
</div>
					
</div>
					</c:forEach>



			</form>





	<script>
		$(function() {

			$('#datepicker').datepicker({
				dateFormat : 'dd/mm/yy'
			});
		});
	</script>

	<script>
		$(function() {

			$('#datepicker2').datepicker({
				dateFormat : 'dd/mm/yy'
			});
		});
	</script>

</body>

</html>

