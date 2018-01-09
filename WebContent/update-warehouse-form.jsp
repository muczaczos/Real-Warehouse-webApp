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
<title><fmt:message key="label.title.update-warehouse" /></title>

<link type="text/css" rel="stylesheet" href="css/style2.css">
<link type="text/css" rel="stylesheet" href="css/add.css">
</head>

<body>

<hr>
	<a
		href="WarehouseControllerServlet?command=LOAD-WAREHOUSE&warehouseId=${THE_WAREHOUSE.id}&theLocale=en_US">Polski
		(PL)</a> |
	<a
		href="WarehouseControllerServlet?command=LOAD-WAREHOUSE&warehouseId=${THE_WAREHOUSE.id}&theLocale=pl_PL">English
		(US)</a>

	<hr>
	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.title.update-warehouse" />
			</h2>
		</div>
	</div>

	<div id="container">

		<br> <br> <br>
		<form action="WarehouseControllerServlet" method="GET">

			<input type="hidden" name="command" value="UPDATE-WAREHOUSE" /> <input
				type="hidden" name="warehouseId" value="${THE_WAREHOUSE.id}" />

			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.customer.name" />:</label></td>
						<td><input type="text" name="name"
							value="${THE_WAREHOUSE.name}" /></td>
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
			<a href="WarehouseControllerServlet"><fmt:message
					key="label.back.warehouse" /></a>
		</p>
	</div>
</body>

</html>



