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

<head>
<title><fmt:message key="label.title.create-invoice" /></title>

<link type="text/css" rel="stylesheet" href="css/style2.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>



</head>


<body>
	<form action="LogoutServlet" method="GET">
		<input type="submit" value="Wyloguj" class="logout" />

	</form>
	<hr>
	<a href="InvoiceControllerServlet?theLocale=en_US">Polski (PL)</a> |
	<a href="InvoiceControllerServlet?theLocale=pl_PL">English (US)</a>


	<hr>

	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.h2.create-invoice" />
			</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<ul>
				<li><a href="Document1ControllerServlet"><fmt:message
							key="label.menu.doc" /></a></li>
				<li><a href="Document2ControllerServlet"><fmt:message
							key="label.menu.doc2" /></a></li>
				<li><a href="InvoiceControllerServlet"><fmt:message
							key="label.menu.inv" /></a></li>
				<li><a href="ProviderControllerServlet"><fmt:message
							key="label.menu.prov" /></a></li>
				<li><a href="CustomerControllerServlet"><fmt:message
							key="label.menu.cus" /></a></li>
				<li><a href="ReciepientControllerServlet"><fmt:message
							key="label.menu.rec" /></a></li>
				<li><a href="ProductControllerServlet"><fmt:message
							key="label.menu.pro" /></a></li>
				<li><a href="PriceControllerServlet"><fmt:message
							key="label.menu.pri" /></a></li>
				<li><a href="EmployeesControllerServlet"><fmt:message
							key="label.menu.emp" /></a></li>
				<li><a href="WarehouseControllerServlet"><fmt:message
							key="label.menu.war" /></a></li>
			</ul>
			<br> <br>
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
				<input type="submit"
					value="<fmt:message key="label.button.precreate"/>">
			</form>

			<br> <br> <br> <br>

			<form action="InvoiceControllerServlet" method="GET">
				<fmt:message key="label.customer" />
				: <select style="width: 12em" name="inv2customer">

					<option>${invcustomer}</option>
				</select> <input type="hidden" name="command" value="CALCULATE-INVOICE" />

				<table>

					<tr>
						<th><fmt:message key="label.check" /></th>
						<th>Id</th>
						<th><fmt:message key="label.date" /></th>
						<th><fmt:message key="label.customer" /></th>
						<th><fmt:message key="label.reciepient" /></th>
						<th><fmt:message key="label.docno" /></th>
						<th><fmt:message key="label.action" /></th>

					</tr>


					<c:forEach var="tempDoc" items="${CUSTOM_DOCUMENTS_LIST}">

						<tr>
							<td><input type="checkbox" name="docId"
								value="${tempDoc.id}">
							<td>${tempDoc.id}</td>
							<td>${tempDoc.date}</td>
							<td>${tempDoc.customer}</td>
							<td>${tempDoc.reciepient}</td>
							<td>${tempDoc.noOfDoc}</td>

						</tr>

					</c:forEach>

					<!-- put new button: precreate doc -->



				</table>

				<hr>
				
				

				<input type="submit"
					value="<fmt:message key="label.button.calculate"/>">

				<textarea name="info" cols="60" rows="10"><c:out
						value="${amount}" /></textarea>

			</form>
			
			<hr>
			
			<form action="InvoiceControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD-INVOICE" />

			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.employee.name" />:
						</label></td>
						<td><input type="text" name="name" /></td>
					</tr>

					<tr>
						<td><label><fmt:message
									key="label.invoice.no" />: </label></td>
						<td><input type="text" name="invNumber" /></td>
					</tr>

					<tr>
						<td><label for="datepicker2"><fmt:message
									key="label.date" />:</label></td>
						<td><input type="text" name="date" value="${Date}"
							id="datepicker2"></td>
					</tr>
					
						<tr>
						<td><input type="hidden" name="startDocRange" value="${startDocRange}" /></td>
					</tr>
					
						<tr>
						<td><input type="hidden" name="endDocRange" value="${endDocRange}" /></td>
					</tr>
					
					<tr>
						<td><input type="hidden" name="grossAmount" value="${grossAmount}" /></td>
					</tr>
						
					<tr>
						<td><label></label></td>
						<td><input type="submit"
							value="<fmt:message key="label.button.save"/>" class="save" /></td>
					</tr>

				</tbody>
			</table>
		</form>
			

			<hr>

			<form action="InvoiceControllerServlet" method="GET">

				<table>

					<tr>
						<th>Id</th>
						<th><fmt:message key="label.customer.name" /></th>
						<th><fmt:message key="label.date" /></th>
						<th><fmt:message key="label.invoice.no" /></th>
						<th><fmt:message key="label.invoice.first.document" /></th>
						<th><fmt:message key="label.invoice.last.document" /></th>
						<th><fmt:message key="label.invoice.gross.amount" /></th>
						<th><fmt:message key="label.action" /></th>
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

							<td>${tempInvoice.id}</td>
							<td>${tempInvoice.customer}</td>
							<td>${tempInvoice.date}</td>
							<td>${tempInvoice.invNumber}</td>
							<td>${tempInvoice.startDocRange}</td>
							<td>${tempInvoice.endDocRange}</td>
							<td>${tempInvoice.grossAmount}</td>

							<td><a href="${updateLink}"><fmt:message
										key="label.update" /></a> | <a href="${deleteLink}"
								onClick="if (!(confirm('<fmt:message key="label.delete.message"/>'))) return false">
									<fmt:message key="label.delete" />
							</a></td>

						</tr>

					</c:forEach>


				</table>

			</form>



		</div>

	</div>

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

