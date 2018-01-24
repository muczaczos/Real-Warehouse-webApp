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
	<a href="Document2ControllerServlet?theLocale=en_US">Polski (PL)</a> |
	<a href="Document2ControllerServlet?theLocale=pl_PL">English (US)</a>

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

		<form action="Document2ControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD-DOCUMENT2" />

			<table>
				<tbody>

					<tr>
						<td><label for="datepicker1"><fmt:message
									key="label.date" />:</label></td>
						<td><input type="text" name="date" value="${Date}"
							id="datepicker1"></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.noOfDoc" />: </label></td>
						<td><input type="text" name="documentNumber" /></td>
					</tr>

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
						<td><fmt:message key="label.product" />: 1</td>
						<td><select style="width: 12em" name="product1">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${Products}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.qty" />: </label></td>
						<td><input type="text" name="qty1" /></td>
					</tr>

					<tr>
						<td><fmt:message key="label.product" />: 2</td>
						<td><select style="width: 12em" name="product2">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${Products}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.qty" />: </label></td>
						<td><input type="text" name="qty2" /></td>
					</tr>

					<tr>
						<td><fmt:message key="label.product" />: 3</td>
						<td><select style="width: 12em" name="product3">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${Products}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.qty" />: </label></td>
						<td><input type="text" name="qty3" /></td>
					</tr>

					<tr>
						<td><fmt:message key="label.product" />: 4</td>
						<td><select style="width: 12em" name="product4">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${Products}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.qty" />: </label></td>
						<td><input type="text" name="qty4" /></td>
					</tr>

					<tr>
						<td><fmt:message key="label.product" />: 5</td>
						<td><select style="width: 12em" name="product5">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${Products}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.qty" />: </label></td>
						<td><input type="text" name="qty5" /></td>
					</tr>

					<tr>
						<td><fmt:message key="label.product" />: 6</td>
						<td><select style="width: 12em" name="product6">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${Products}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.qty" />: </label></td>
						<td><input type="text" name="qty6" /></td>
					</tr>

					<tr>
						<td><fmt:message key="label.product" />: 7</td>
						<td><select style="width: 12em" name="product7">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${Products}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.qty" />: </label></td>
						<td><input type="text" name="qty7" /></td>
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
		<form action="Document2ControllerServlet" method="GET">

			<table>

				<tr>
					<th>Id</th>
					<th><fmt:message key="label.provider" /></th>
					<th><fmt:message key="label.date" /></th>
					<th><fmt:message key="label.noOfDoc" /></th>
					<th><fmt:message key="label.action" /></th>
				</tr>

				<c:forEach var="tempDoc2" items="${documents2}">
					<!-- set up a link for each customers  -->
					<c:url var="tempLink" value="Document2ControllerServlet">
						<c:param name="command" value="LOAD-DOCUMENT2" />
						<c:param name="doc2Id" value="${tempDoc2.id}" />
					</c:url>

					<!-- set up a link to delete a document -->
					<c:url var="deleteLink" value="Document2ControllerServlet">
						<c:param name="command" value="DELETE-DOCUMENT2" />
						<c:param name="doc2Id" value="${tempDoc2.id}" />
					</c:url>

					<tr>

						<td>${tempDoc2.id}</td>
						<td>${tempDoc2.provider}</td>
						<td>${tempDoc2.date}</td>
						<td>${tempDoc2.documentNumber}</td>
						

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

	<script>
		$(function() {

			$('#datepicker1').datepicker({
				dateFormat : 'dd/mm/yy'
			});
		});
	</script>

</body>

</html>