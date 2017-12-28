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
<title><fmt:message key="label.title.create-doc2" /></title>

<link type="text/css" rel="stylesheet" href="css/style2.css">
<link type="text/css" rel="stylesheet" href="css/add.css">
</head>

<body>
<a href="LogoutServlet">Wyloguj</a>
<hr>
	Jesteś zalogowany jako ${userName}
	<br>
	<a href="create-doc2.jsp?theLocale=en_US">Polski (PL)</a> |
	<a href="create-doc2.jsp?theLocale=pl_PL">English (US)</a>

	<hr>

	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.h2.create-doc2" />
			</h2>
		</div>
	</div>

	<div id="container">

		<br>
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
			<li><a href="create-product.jsp"><fmt:message
						key="label.menu.pro" /></a></li>
			<li><a href="create-price.jsp"><fmt:message
						key="label.menu.pri" /></a></li>
			<li><a href="create-employees.jsp"><fmt:message
						key="label.menu.emp" /></a></li>
			<li><a href="create-warehouse.jsp"><fmt:message
						key="label.menu.war" /></a></li>
		</ul>
		<br> <br>

		<form action="WarehouseControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD-DOCUMENT2" />

			<table>
				<tbody>
					<tr>
						<td><fmt:message key="label.provider" /></td>
						<td><select style="width: 12em" name="provider">
								<option>---select---</option>
								<c:forEach var="tempProvider" items="${Providers}">
									<option>${tempProvider.name}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td><fmt:message key="label.product" /></td>
						<td><select style="width: 12em" name="product">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${Products}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.qty" />: </label></td>
						<td><input type="text" name="qty" /></td>
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
		<form action="WarehouseControllerServlet" method="GET">

			<table>

				<tr>
					<th>Id</th>
					<th><fmt:message key="label.provider" /></th>
					<th><fmt:message key="label.product" /></th>
					<th><fmt:message key="label.qty" /></th>
					<th><fmt:message key="label.action" /></th>
				</tr>

				<c:forEach var="tempDoc2" items="${documents2}">
					<!-- set up a link for each customers  -->
					<c:url var="tempLink" value="WarehouseControllerServlet">
						<c:param name="command" value="LOAD-DOCUMENT2" />
						<c:param name="doc2Id" value="${tempDoc2.id}" />
					</c:url>

					<!-- set up a link to delete a document -->
					<c:url var="deleteLink" value="WarehouseControllerServlet">
						<c:param name="command" value="DELETE-DOCUMENT2" />
						<c:param name="doc2Id" value="${tempDoc2.id}" />
					</c:url>

					<tr>

						<td>${tempDoc2.id}</td>
						<td>${tempDoc2.provider}</td>
						<td>${tempDoc2.product}</td>
						<td>${tempDoc2.qty}</td>

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
			<a href="WarehouseControllerServlet"><fmt:message
					key="label.back.home" /></a>
		</p>
	</div>



</body>

</html>