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
<title><fmt:message key="label.title.update-price" /></title>

<link type="text/css" rel="stylesheet" href="css/style2.css">
<link type="text/css" rel="stylesheet" href="css/add.css">
</head>

<body>

<hr>
	<a
		href="update-price-form.jsp?command=LOAD-PRICE&priceId=${THE_PRICE.id}&theLocale=en_US">Polski
		(PL)</a> |
	<a
		href="update-price-form.jsp?command=LOAD-PRICE&priceId=${THE_PRICE.id}&theLocale=pl_PL">English
		(US)</a>
	
	<hr>
	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.title.update-price" />
			</h2>
		</div>
	</div>

	<div id="container">

		<br> <br> <br>
		<form action="WarehouseControllerServlet" method="GET">

			<input type="hidden" name="command" value="UPDATE-PRICE" /> <input
				type="hidden" name="priceId" value="${THE_PRICE.id}" />

			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.customer" />:</label></td>
						<td><input type="text" name="customer"
							value="${THE_PRICE.customer}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.product" />:</label></td>
						<td><input type="text" name="product"
							value="${THE_PRICE.product}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.price" />:</label></td>
						<td><input type="text" name="price"
							value="${THE_PRICE.price}" /></td>
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
			<a href="create-price.jsp"><fmt:message key="label.back.price" /></a>
		</p>
	</div>
</body>

</html>



