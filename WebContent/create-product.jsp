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
<head>
<title><fmt:message key="label.title.create-product" /></title>

<link type="text/css" rel="stylesheet" href="css/style2.css">
<link type="text/css" rel="stylesheet" href="css/add.css">
</head>

<body>
	<form action="LogoutServlet" method="GET">
		<input type="submit" value="Wyloguj" class="logout" />

	</form>
	<hr>
	<a href="ProductControllerServlet?theLocale=en_US">Polski (PL)</a> |
	<a href="ProductControllerServlet?theLocale=pl_PL">English (US)</a>

	<hr>

	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.h2.create-product" />
			</h2>
		</div>
	</div>

	<div id="container">

		<br>
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
		<form action="ProductControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD-PRODUCT" />

			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.customer.name" />:
						</label></td>
						<td><input type="text" name="productName" /></td>
					</tr>

					<tr>
						<td><fmt:message key="label.warehouse" /></td>
						<td><select style="width: 15em" name="warehouse">
								<option>---select---</option>
								<c:forEach var="tempCombo" items="${Warehouses}">
									<option>${tempCombo.name}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit"
							value="<fmt:message key="label.button.save"/>" class="save" /></td>
					</tr>

				</tbody>
			</table>
		</form>


		<br> <br>
		<form action="ProductControllerServlet" method="GET">

			<table>

				<tr>
					<th>Id</th>
					<th><fmt:message key="label.customer.name" /></th>
					<th><fmt:message key="label.warehouse" /></th>
					<th><fmt:message key="label.qty" /></th>
					<th><fmt:message key="label.action" /></th>

				</tr>

				<c:forEach var="tempPro" items="${Products}">
					<!-- set up a link for each customers  -->
					<c:url var="tempLink" value="ProductControllerServlet">
						<c:param name="command" value="LOAD-PRODUCT" />
						<c:param name="productId" value="${tempPro.id}" />
					</c:url>

					<!-- set up a link to delete a document -->
					<c:url var="deleteLink" value="ProductControllerServlet">
						<c:param name="command" value="DELETE-PRODUCT" />
						<c:param name="productId" value="${tempPro.id}" />
					</c:url>

					<tr>

						<td>${tempPro.id}</td>
						<td>${tempPro.productName}</td>
						<td>${tempPro.warehouse}</td>
						<td>${tempPro.qty}</td>

						<td><a href="${tempLink}"><fmt:message key="label.update" /></a>
							| <a href="${deleteLink}"
							onClick="if (!(confirm('<fmt:message key="label.delete.message"/>'))) return false">
								<fmt:message key="label.delete" />
						</a></td>

					</tr>

				</c:forEach>


			</table>

		</form>

		<div style="clear: both;"></div>

		<p>
			<a href="Document1ControllerServlet"><fmt:message
					key="label.back.home" /></a>
		</p>
	</div>



</body>

</html>