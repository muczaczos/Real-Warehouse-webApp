<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="com.muczo.mvc.warehouse.i18h.resources.mylabels" />

<!DOCTYPE html>

<html>
	<title><fmt:message key="label.title.create-invoice" /></title>
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
			
				 <div class="w3-dropdown-hover">
  					<button class="w3-button w3-black w3-hide-small w3-padding-large"><fmt:message key="label.doc" /></button>
  						<div class="w3-dropdown-content w3-bar-block w3-black ">
    						<a href="Document1ControllerServlet" class="w3-bar-item w3-button w3-hover-ligth-gray"><fmt:message key="label.menu.doc" /></a>
   							<a href="Document2ControllerServlet" class="w3-bar-item w3-button w3-hover-ligth-gray"><fmt:message key="label.menu.doc2" /></a>
    						<a href="InvoiceControllerServlet" class="w3-bar-item w3-button w3-hover-ligth-gray"><fmt:message key="label.menu.inv" /></a>
    						
  						</div>
				</div> 
				
				<div class="w3-dropdown-hover">
  					<button class="w3-button w3-black w3-hide-small w3-padding-large"><fmt:message key="label.records" /></button>
  						<div class="w3-dropdown-content w3-bar-block w3-black ">
    						<a href="CustomerControllerServlet" class="w3-bar-item w3-button w3-hover-ligth-gray"><fmt:message key="label.menu.cus" /></a>
   							<a href="ReciepientControllerServlet" class="w3-bar-item w3-button w3-hover-ligth-gray"><fmt:message key="label.menu.rec" /></a>
    						<a href="ProductControllerServlet" class="w3-bar-item w3-button w3-hover-ligth-gray"><fmt:message key="label.menu.pro" /></a>
    						<a href="PriceControllerServlet" class="w3-bar-item w3-button w3-hover-ligth-gray"><fmt:message key="label.menu.pri" /></a>
   							<a href="ProviderControllerServlet" class="w3-bar-item w3-button w3-hover-ligth-gray"><fmt:message key="label.menu.prov" /></a>
    						<a href="EmployeesControllerServlet" class="w3-bar-item w3-button w3-hover-ligth-gray"><fmt:message key="label.menu.emp" /></a>
    						<a href="WarehouseControllerServlet" class="w3-bar-item w3-button w3-hover-ligth-gray"><fmt:message key="label.menu.war" /></a>
  						</div>
				</div> 
				
				<a href="ProductionControllerServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-ligth-gray">
				<fmt:message key="label.production" /></a> 
				
				<a href="LogoutServlet" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-ligth-gray w3-right">Logout</a>
					
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
			
			<h1>	<fmt:message key="label.h2.create-invoice" />	</h1>
			
			<hr>

		</header>
		
		<div class="w3-panel w3-border w3-padding-16 w3-border-green w3-margin">
			<div class="w3-display-container" style="height: 50px;">
		
				<div class="w3-display-left">Jesteś zalogowany jako ${userName}</div>
			
				<div class="w3-display-right">
					<div class="w3-text-blue">
						<a href="InvoiceControllerServlet?theLocale=en_US">Polski (PL)</a> |
						<a href="InvoiceControllerServlet?theLocale=pl_PL">English (US)</a>
					</div>
				</div>
			
			</div>
		</div>

		<!-- First Grid -->
		<div class="w3-row-padding w3-dark-grey w3-padding-64 w3-container">

			<div class="w3-third">
				<div class="w3-panel w3-gray w3-padding-16">
					<form  action="InvoiceControllerServlet?invcustomer=${invcustomer}&theLocale=pl_PL" method="GET" class="w3-container">
						<input type="hidden" name="command" value="PRECREATE-INVOICE" />

						<table>
							<tbody>
								<tr>
									<td><label class="w3-text-black"><b><fmt:message key="label.customer" /></b></label>
										<select class="w3-input" name="invcustomer">
										<option>---select---</option>
										<c:forEach var="tempCustomer" items="${Customers}">
											<option>${tempCustomer.name}</option>
										</c:forEach>			
										</select> 
									</td>
								</tr>
								<tr>
									<td>
										<label class="w3-text-black" for="datepicker"><b><fmt:message key="label.enter.date" /></b></label>
										<input class="w3-input" type="text" name="invdate" value="${Date}" id="datepicker">
									</td>
								</tr>	
								<tr>
									<td>
										<!-- put new button: precreate doc -->
										<button type="submit" class="w3-button w3-green w3-hover-red w3-padding-large w3-large w3-margin-top">
										<fmt:message key="label.button.precreate"/> </button>
									</td>
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
				<form action="InvoiceControllerServlet" method="GET">
					<input type="hidden" name="inv2customer" value="${invcustomer}" />
					<input type="hidden" name="command" value="CALCULATE-INVOICE" />
					
					 <table class="w3-table w3-bordered">
   				 		<tr class="w3-light-green">
   				 			<th>
								<fmt:message key="label.check" />
							</th>
							<th>
								<fmt:message key="label.docno" />
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
								Id
							</th>
							
						</tr>
					
						<c:forEach var="tempDoc" items="${CUSTOM_DOCUMENTS_LIST}">
						<tr>
							<td>
								<input type="checkbox" name="docId" value="${tempDoc.id}">
							</td>
							
							<td>
								${tempDoc.noOfDoc}
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
								${tempDoc.id}
							</td>
							
						</tr>
						</c:forEach>
					</table>
					<!-- put new button: precreate doc -->
					<hr>
					<button type="submit" class="w3-button w3-green w3-hover-red w3-padding-large w3-large w3-margin">
					<fmt:message key="label.button.calculate"/> </button>
				
				</form>
			</div>	
		</div>
		
		<!-- Third Grid -->
		<div class="w3-row-padding w3-padding-64 w3-container w3-light-gray">
			<div class="w3-panel w3-margin">
				<div class="w3-row ">
				
				<div class="w3-panel w3-gray w3-padding-16">
  					<div class="w3-half w3-container">
						<textarea name="info" cols="42" rows="20"><c:out value="${amount}" /></textarea>
					</div>
				</div>
				<div class="w3-panel w3-gray w3-padding-16">
					<div class="w3-half w3-container">
						<form action="InvoiceControllerServlet" method="GET" class="w3-container">
						<input type="hidden" name="command" value="ADD-INVOICE" /> <input
							type="hidden" name="grossAmount" value="${grossAmount}" /> <input
							type="hidden" name="startDocRange" value="${startDocRange}" /> <input
							type="hidden" name="endDocRange" value="${endDocRange}" /> <input
							type="hidden" name="customer" value="${inv2customer}" />
							<table>
								<tbody>
									<tr>
										<td>
											<label class="w3-text-black"><b><fmt:message key="label.invoice.no" /></b></label>
											<input class="w3-input" type="text" name="invNumber" />
										</td>
									</tr>

									<tr>
										<td>
											<label class="w3-text-black" for="datepicker2"><b><fmt:message key="label.date" /></b></label>
											<input class="w3-input" type="text" name="date" value="${Date}" id="datepicker2">
										</td>
									</tr>
									<tr>
										<td>
											<button type="submit" class="w3-button w3-green w3-hover-red w3-padding-large w3-large w3-margin-top">
											<fmt:message key="label.button.save"/> </button>
										</td>
									</tr>
							  </tbody>
						  </table>
						</form>
					</div>
					</div>
				</div>
			</div>
		</div>
		
			
		<!-- Forth Grid -->
		<div class="w3-row-padding w3-padding-64 w3-container w3-light-gray">
			<div class="w3-panel w3-margin">
				<form action="InvoiceControllerServlet" method="GET">
					 <table class="w3-table w3-bordered">
   				 		<tr class="w3-light-green">
   				 			<th>   
								Id
							</th>
							<th>
								<fmt:message key="label.customer.name" />
							</th>
							<th>
								<fmt:message key="label.date" />
							<th>
								<fmt:message key="label.invoice.no" />
							</th>
							<th>
								<fmt:message key="label.invoice.first.document" />
							</th>
							<th>
								<fmt:message key="label.invoice.last.document" />
							</th>
							<th>
								<fmt:message key="label.invoice.gross.amount" />
							</th>
							<th>
								<fmt:message key="label.action" />
							</th>
						</tr>
					
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
						
							<tr>
								<td>
									${tempInvoice.id}
								</td>
								<td>
									${tempInvoice.customer}
								</td>
								<td>
									${tempInvoice.date}
								</td>
								<td>
									${tempInvoice.invNumber}
								</td>
								<td>
									${tempInvoice.startDocRange}
								</td>
								<td>
									${tempInvoice.endDocRange}
								</td>
								<td>
									${tempInvoice.grossAmount}
								</td>
								<td>
 								 	<div class="w3-text-blue">
										<a href="${updateLink}"><fmt:message key="label.update" /></a> | 
										<a href="${deleteLink}" onClick="if (!(confirm('<fmt:message key="label.delete.message"/>'))) return false">
										<fmt:message key="label.delete" /></a>
									</div>
								</td>
							</tr>
						</c:forEach>
						</table>
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

