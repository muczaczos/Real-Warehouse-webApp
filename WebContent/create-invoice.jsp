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
	<a href="create-invoice.jsp?theLocale=en_US">Polski (PL)</a> |
	<a href="create-invoice.jsp?theLocale=pl_PL">English (US)</a>


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
				<li><a href="WarehouseControllerServlet"><fmt:message
							key="label.menu.doc" /></a></li>
				<li><a href="create-doc2.jsp"><fmt:message
							key="label.menu.doc2" /></a></li>
				<li><a href="create-invoice.jsp"><fmt:message
							key="label.menu.inv" /></a></li>
				<li><a href="create-provider.jsp"><fmt:message
							key="label.menu.prov" /></a></li>
				<li><a href="create-customer.jsp"><fmt:message
							key="label.menu.cus" /></a></li>
				<li><a href="create-reciepient.jsp"><fmt:message
							key="label.menu.rec" /></a></li>
				<li><a href="WarehouseControllerServlet?command=LIST-PRODUCTS?"><fmt:message
							key="label.menu.pro" /></a></li>
				<li><a href="create-price.jsp"><fmt:message
							key="label.menu.pri" /></a></li>
				<li><a href="create-employees.jsp"><fmt:message
							key="label.menu.emp" /></a></li>
				<li><a href="create-warehouse.jsp"><fmt:message
							key="label.menu.war" /></a></li>
			</ul>

			<br> <br>
			<form
				action="WarehouseControllerServlet?invcustomer=${invcustomer}&theLocale=pl_PL"
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

			<form action="WarehouseControllerServlet" method="GET">
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

				<input type="submit"
					value="<fmt:message key="label.button.calculate"/>">

				<textarea name="info" cols="60" rows="10"><c:out
						value="${amount}" /></textarea>

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

</body>

</html>

