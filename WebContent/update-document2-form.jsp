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
		href="Document2ControllerServlet?command=LOAD-DOCUMENT2&doc2Id=${THE_DOCUMENT2.id}&theLocale=en_US">Polski
		(PL)</a> |
	<a
		href="Document2ControllerServlet?command=LOAD-DOCUMENT2&doc2Id=${THE_DOCUMENT2.id}&theLocale=pl_PL">English
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
		<form action="Document2ControllerServlet" method="GET">

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
						<td><input type="text" name="product1"
							value="${THE_DOCUMENT2.product1}" /></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty1"
							value="${THE_DOCUMENT2.qty1}" /></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.product" />:</label></td>
						<td><input type="text" name="product2"
							value="${THE_DOCUMENT2.product2}" /></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty2"
							value="${THE_DOCUMENT2.qty2}" /></td>
					</tr>
					
						<tr>
						<td><label><fmt:message key="label.product" />:</label></td>
						<td><input type="text" name="product3"
							value="${THE_DOCUMENT2.product3}" /></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty3"
							value="${THE_DOCUMENT2.qty3}" /></td>
					</tr>
					
						<tr>
						<td><label><fmt:message key="label.product" />:</label></td>
						<td><input type="text" name="product4"
							value="${THE_DOCUMENT2.product4}" /></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty4"
							value="${THE_DOCUMENT2.qty4}" /></td>
					</tr>
					
						<tr>
						<td><label><fmt:message key="label.product" />:</label></td>
						<td><input type="text" name="product5"
							value="${THE_DOCUMENT2.product5}" /></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty5"
							value="${THE_DOCUMENT2.qty5}" /></td>
					</tr>
					
						<tr>
						<td><label><fmt:message key="label.product" />:</label></td>
						<td><input type="text" name="product6"
							value="${THE_DOCUMENT2.product6}" /></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty6"
							value="${THE_DOCUMENT2.qty6}" /></td>
					</tr>
					
						<tr>
						<td><label><fmt:message key="label.product" />:</label></td>
						<td><input type="text" name="product7"
							value="${THE_DOCUMENT2.product7}" /></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty7"
							value="${THE_DOCUMENT2.qty7}" /></td>
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
			<a href="Document2ControllerServlet"><fmt:message
					key="label.back.doc2" /></a>
		</p>
	</div>
</body>

</html>



