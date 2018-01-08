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

<title><fmt:message key="label.title.update-product" /></title>

<link type="text/css" rel="stylesheet" href="css/style2.css">
<link type="text/css" rel="stylesheet" href="css/add.css">

</head>

<body>

<hr>
	<a
		href="update-document2-form.jsp?command=LOAD-DOCUMENT2&productId=${THE_DOCUMENT2.id}&theLocale=en_US">Polski
		(PL)</a> |
	<a
		href="update-document2-form.jsp?command=LOAD-DOCUMENT2&productId=${THE_DOCUMENT2.id}&theLocale=pl_PL">English
		(US)</a>
	
	<hr>
	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.title.update-document2" />
			</h2>
		</div>
	</div>

	<div id="container">

		<br> <br> <br>
		<form action="WarehouseControllerServlet" method="GET">

			<input type="hidden" name="command" value="UPDATE-DOCUMENT2" /> <input
				type="hidden" name="doc2Id" value="${THE_DOCUMENT2.id}" />

			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.provider" />:</label></td>
						<td><input type="text" name="provider"
							value="${THE_DOCUMENT2.provider}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.product" />:</label></td>
						<td><input type="text" name="product"
							value="${THE_DOCUMENT2.product}" /></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty"
							value="${THE_DOCUMENT2.qty}" /></td>
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
			<a href="create-doc2.jsp"><fmt:message
					key="label.back.doc2" /></a>
		</p>
	</div>
</body>

</html>



