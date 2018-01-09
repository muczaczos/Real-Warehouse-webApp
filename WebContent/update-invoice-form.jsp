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
<title><fmt:message key="label.title.update-reciepient" /></title>

<link type="text/css" rel="stylesheet" href="css/style2.css">
<link type="text/css" rel="stylesheet" href="css/add.css">
</head>

<body>

	<hr>
	<a
		href="InvoiceControllerServlet?command=LOAD-DOCUMENT&documentId=${THE_DOCUMENT.id}&theLocale=en_US">Polski
		(PL)</a> |
	<a
		href="InvoiceControllerServlet?command=LOAD-DOCUMENT&documentId=${THE_DOCUMENT.id}&theLocale=pl_PL">English
		(US)</a>

	<hr>

	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.title.update-document" />
			</h2>
		</div>
	</div>

	<div id="container">

		<br> <br> <br>

		<form action="InvoiceControllerServlet" method="GET">

			<input type="hidden" name="command" value="UPDATE-DOCUMENT" /> <input
				type="hidden" name="documentId" value="${THE_DOCUMENT.id}" />

			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.customer" />:</label></td>
						<td><input type="text" name="customer"
							value="${THE_DOCUMENT.customer}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.reciepient" />:</label></td>
						<td><input type="text" name="reciepient"
							value="${THE_DOCUMENT.reciepient}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.docno" />:</label></td>
						<td><input type="text" name="noOfDoc"
							value="${THE_DOCUMENT.noOfDoc}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.date" />:</label></td>
						<td><input type="text" name="date"
							value="${THE_DOCUMENT.date}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.product" /> 1:</label></td>
						<td><input type="text" name="product1"
							value="${THE_DOCUMENT.product1}" /></td>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty1"
							value="${THE_DOCUMENT.qty1}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.product" /> 2:</label></td>
						<td><input type="text" name="product2"
							value="${THE_DOCUMENT.product2}" /></td>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty2"
							value="${THE_DOCUMENT.qty2}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.product" /> 3:</label></td>
						<td><input type="text" name="product3"
							value="${THE_DOCUMENT.product3}" /></td>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty3"
							value="${THE_DOCUMENT.qty3}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.product" /> 4:</label></td>
						<td><input type="text" name="product4"
							value="${THE_DOCUMENT.product4}" /></td>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty4"
							value="${THE_DOCUMENT.qty4}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.product" /> 5:</label></td>
						<td><input type="text" name="product5"
							value="${THE_DOCUMENT.product5}" /></td>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty5"
							value="${THE_DOCUMENT.qty5}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.product" /> 6:</label></td>
						<td><input type="text" name="product6"
							value="${THE_DOCUMENT.product6}" /></td>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty6"
							value="${THE_DOCUMENT.qty6}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.product" /> 7:</label></td>
						<td><input type="text" name="product7"
							value="${THE_DOCUMENT.product7}" /></td>
						<td><label><fmt:message key="label.qty" />:</label></td>
						<td><input type="text" name="qty7"
							value="${THE_DOCUMENT.qty7}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.info" />:</label></td>
						<td><input name="info" value="${THE_DOCUMENT.info}" /></td>
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
			<a href="InvoiceControllerServlet"><fmt:message
					key="label.back.home" /></a>
		</p>
	</div>
</body>

</html>



