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

<title><fmt:message key="label.title.create-production" /></title>

<link type="text/css" rel="stylesheet" href="css/style2.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>


<body>

	<form action="LogoutServlet" method="GET">
		<input type="submit" value="Wyloguj" class="logout" />

	</form>

	<hr>
	Jesteś zalogowany jako ${userName}
	<br>
	<a href="ProductionControllerServlet?theLocale=en_US">Polski (PL)</a> |
	<a href="ProductionControllerServlet?theLocale=pl_PL">English (US)</a>

	<hr>

	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.h2.create-production" />
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
				<li><a href="ProductionControllerServlet"><fmt:message
							key="label.production" /></a></li>
			</ul>

			<br> <br>

			<form action="ProductionControllerServlet" method="GET">
				<input type="hidden" name="command" value="FIRST-LIST" />

				<fmt:message key="label.menu.war" />
				: <select style="width: 12em" name="warehouse1">
					<option>---select---</option>
					<c:forEach var="tempWarehouse" items="${WAREHOUSES_LIST}">
						<option>${tempWarehouse.name}</option>
					</c:forEach>

				</select> <select style="width: 12em" name="warehouse2">
					<option>---select---</option>
					<c:forEach var="tempWarehouse" items="${WAREHOUSES_LIST}">
						<option>${tempWarehouse.name}</option>
					</c:forEach>
				</select> <br> <br>
				<!-- put new button: precreate doc -->
				<input type="submit"
					value="ok">

			</form>

			<br> <br>

			<form action="ProductionControllerServlet" method="GET">
				<input type="hidden" name="command" value="ADD-PRODUCTION" />

				<table>
					<tbody>

						<tr>
							<td><fmt:message key="label.product" /></td>
							<td><select style="width: 15em" name="product1">
									<option>---select---</option>
									<c:forEach var="tempProduct" items="${PRODUCT_LIST1}">
										<option>${tempProduct.productName}</option>
									</c:forEach>
							</select></td>
						</tr>

						<tr>
							<td><fmt:message key="label.product" /></td>
							<td><select style="width: 15em" name="product2">
									<option>---select---</option>
									<c:forEach var="tempProduct" items="${PRODUCT_LIST2}">
										<option>${tempProduct.productName}</option>
									</c:forEach>
							</select></td>
						</tr>

						<tr><td>-<fmt:message key="label.qty" /></td>
						<td>: <input type="text" name="qty" /></td>
						
						</tr>
						<tr>
							<td><label></label></td>
							<td><input type="submit"
								value="<fmt:message key="label.button.save"/>" class="save" /></td>
						</tr>

					</tbody>
				</table>
			</form>


		</div>

	</div>



</body>

</html>

