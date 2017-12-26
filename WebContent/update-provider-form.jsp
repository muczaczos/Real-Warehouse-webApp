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
<title><fmt:message key="label.title.update-provider" /></title>

<link type="text/css" rel="stylesheet" href="css/style2.css">
<link type="text/css" rel="stylesheet" href="css/add.css">
</head>

<body>
<a href="LogoutServlet">Wyloguj</a>
<hr>
	<a
		href="update-provider-form.jsp?command=LOAD-PROVIDER&providerId=${THE_CUSTOMER.id}&theLocale=en_US">Polski
		(PL)</a> |
	<a
		href="update-provider-form.jsp?command=LOAD-PROVIDER&providerId=${THE_CUSTOMER.id}&theLocale=pl_PL">English
		(US)</a>

	<hr>
	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.title.update-provider" />
			</h2>
		</div>
	</div>

	<div id="container">

		<br> <br> <br>
		<form action="WarehouseControllerServlet" method="GET">

			<input type="hidden" name="command" value="UPDATE-PROVIDER" /> <input
				type="hidden" name="providerId" value="${THE_PROVIDER.id}" />

			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.customer.name" />:</label></td>
						<td><input type="text" name="name"
							value="${THE_PROVIDER.name}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.address" />:</label></td>
						<td><input type="text" name="address"
							value="${THE_PROVIDER.address}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.telephone" />:</label></td>
						<td><input type="text" name="telephone"
							value="${THE_PROVIDER.telephone}" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit"
							value="<fmt:message key="label.button.save"/>" class="save" /></td>
					</tr>

				</tbody>
			</table>
		</form>

		<div style="clear: both;"></div>

		<p>
			<a href="create-provider.jsp"><fmt:message
					key="label.back.provider" /></a>
		</p>
	</div>
</body>

</html>



