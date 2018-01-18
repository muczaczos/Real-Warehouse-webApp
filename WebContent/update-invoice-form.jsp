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
		href="InvoiceControllerServlet?command=LOAD-INVOICE&invoiceId=${THE_INVOICE.id}&theLocale=en_US">Polski
		(PL)</a> |
	<a
		href="InvoiceControllerServlet?command=LOAD-INVOICE&invoiceId=${THE_INVOICE.id}&theLocale=pl_PL">English
		(US)</a>

	<hr>

	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.title.update-invoice" />
			</h2>
		</div>
	</div>

	<div id="container">

		<br> <br> <br>

		<form action="InvoiceControllerServlet" method="GET">

			<input type="hidden" name="command" value="UPDATE-INVOICE" /> <input
				type="hidden" name="invoiceId" value="${THE_INVOICE.id}" />

			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.customer" />:</label></td>
						<td><input type="text" name="customer"
							value="${THE_INVOICE.customer}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.date" />:</label></td>
						<td><input type="text" name="date"
							value="${THE_INVOICE.date}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.invNumber" />:</label></td>
						<td><input type="text" name="invNumber"
							value="${THE_INVOICE.invNumber}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.startDocRange" />:</label></td>
						<td><input type="text" name="startDocRange"
							value="${THE_INVOICE.startDocRange}" /></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.endDocRange" />:</label></td>
						<td><input type="text" name="endDocRange"
							value="${THE_INVOICE.endDocRange}" /></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.grossAmount" />:</label></td>
						<td><input type="text" name="grossAmount"
							value="${THE_INVOICE.grossAmount}" /></td>
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
					key="label.back.create.invoice" /></a>
		</p>
	</div>
</body>

</html>



