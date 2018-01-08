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
<title><fmt:message key="label.title.create-doc" /></title>

<link type="text/css" rel="stylesheet" href="css/style2.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>



</head>


<body>

	<form action="LogoutServlet" method="GET">
						<input type="submit"
							value="Wyloguj" class="logout" />
			
		</form>

<hr>
	Jesteś zalogowany jako ${userName}
	<br>
	<a href="WarehouseControllerServlet?theLocale=en_US">Polski (PL)</a> |
	<a href="WarehouseControllerServlet?theLocale=pl_PL">English (US)</a>

	<hr>

	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.h2.create-doc" />
			</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

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
			<li><a href="WarehouseControllerServlet?command=LIST-PRODUCTS?"><fmt:message
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
				<input type="hidden" name="command" value="FIRST-LIST" />

				<fmt:message key="label.customer" />
				: <select style="width: 12em" name="customer">
					<option>---select---</option>
					<c:forEach var="tempCustomer" items="${CUSTOMERS_LIST}">
						<option>${tempCustomer.name}</option>
					</c:forEach>
				</select> <br>
				<fmt:message key="label.product" />
				1
				<fmt:message key="label.which.warehouse" />
				<select style="width: 15em" name="warehouse1">
					<option>---select---</option>
					<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
						<option>${tempCombo.name}</option>
					</c:forEach>
				</select> <br>
				<fmt:message key="label.product" />
				2
				<fmt:message key="label.which.warehouse" />
				<select style="width: 15em" name="warehouse2">
					<option>---select---</option>
					<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
						<option>${tempCombo.name}</option>
					</c:forEach>
				</select> <br>
				<fmt:message key="label.product" />
				3
				<fmt:message key="label.which.warehouse" />
				<select style="width: 15em" name="warehouse3">
					<option>---select---</option>
					<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
						<option>${tempCombo.name}</option>
					</c:forEach>
				</select> <br>
				<fmt:message key="label.product" />
				4
				<fmt:message key="label.which.warehouse" />
				<select style="width: 15em" name="warehouse4">
					<option>---select---</option>
					<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
						<option>${tempCombo.name}</option>
					</c:forEach>
				</select> <br>
				<fmt:message key="label.product" />
				5
				<fmt:message key="label.which.warehouse" />
				<select style="width: 15em" name="warehouse5">
					<option>---select---</option>
					<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
						<option>${tempCombo.name}</option>
					</c:forEach>
				</select> <br>
				<fmt:message key="label.product" />
				6
				<fmt:message key="label.which.warehouse" />
				<select style="width: 15em" name="warehouse6">
					<option>---select---</option>
					<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
						<option>${tempCombo.name}</option>
					</c:forEach>
				</select> <br>
				<fmt:message key="label.product" />
				7
				<fmt:message key="label.which.warehouse" />
				<select style="width: 15em" name="warehouse7">
					<option>---select---</option>
					<c:forEach var="tempCombo" items="${WAREHOUSES_LIST}">
						<option>${tempCombo.name}</option>
					</c:forEach>
				</select> <br> <br>
				<!-- put new button: precreate doc -->
				<input type="submit"
					value="<fmt:message key="label.button.precreate"/>">

			</form>

			<br> <br>

			<form action="WarehouseControllerServlet" method="GET">
				<table>
					<tr>

						<td style="width: 100%" align="left"><input type="hidden"
							name="command" value="ADD-DOCUMENT" /> <label for="datepicker"><fmt:message
									key="label.enter.date" />:</label> <input type="text" name="date"
							value="${TODAY_DATE}" id="datepicker"> <br> -<fmt:message
								key="label.customer" /> : <input type="text" name="preCustomer"
							value="${preCustomer}" /> <br> <br> -<fmt:message
								key="label.noOfDoc" /> : <input type="text" name="docId"
							value="${noOfDoc}" /> <br> -<fmt:message
								key="label.reciepient" /> : <select style="width: 12em"
							name="reciepient">
								<option>---select---</option>
								<c:forEach var="tempReciepient" items="${RECIEPIENTS_LIST}">
									<option>${tempReciepient.name}</option>
								</c:forEach>
						</select> <br> -<fmt:message key="label.product" /> 1 : <select
							style="width: 15em" name="product1">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${PRODUCT_LIST1}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select> -<fmt:message key="label.qty" />: <input type="text" name="qty1" />
							<br> -<fmt:message key="label.product" /> 2 : <select
							style="width: 15em" name="product2">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${PRODUCT_LIST2}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select> -<fmt:message key="label.qty" />: <input type="text" name="qty2" />
							<br> -<fmt:message key="label.product" /> 3 : <select
							style="width: 15em" name="product3">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${PRODUCT_LIST3}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select> -<fmt:message key="label.qty" />: <input type="text" name="qty3" />
							<br> -<fmt:message key="label.product" /> 4 : <select
							style="width: 15em" name="product4">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${PRODUCT_LIST4}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select> -<fmt:message key="label.qty" />: <input type="text" name="qty4" />
							<br> -<fmt:message key="label.product" /> 5 : <select
							style="width: 15em" name="product5">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${PRODUCT_LIST5}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select> -<fmt:message key="label.qty" />: <input type="text" name="qty5" />
							<br> -<fmt:message key="label.product" /> 6 : <select
							style="width: 15em" name="product6">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${PRODUCT_LIST6}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select> -<fmt:message key="label.qty" />: <input type="text" name="qty6" />
							<br> -<fmt:message key="label.product" /> 7 : <select
							style="width: 15em" name="product7">
								<option>---select---</option>
								<c:forEach var="tempProduct" items="${PRODUCT_LIST7}">
									<option>${tempProduct.productName}</option>
								</c:forEach>
						</select> -<fmt:message key="label.qty" />: <input type="text" name="qty7" />

							<br> <br> <input type="submit"
							value="<fmt:message key="label.button.save"/>">
						<td align="left"><label><fmt:message key="label.info" />:</label>
							<textarea name="info" cols="60" rows="10"></textarea></td>


					</tr>

				</table>

			</form>


			<br> <br>
			<table>

				<tr>
					<th>Id</th>
					<th><fmt:message key="label.date" /></th>
					<th><fmt:message key="label.customer" /></th>
					<th><fmt:message key="label.reciepient" /></th>
					<th><fmt:message key="label.docno" /></th>
					<th><fmt:message key="label.action" /></th>
				</tr>

				<c:forEach var="tempDoc" items="${DOCUMENTS_LIST}">

					<!-- set up a link for each documents  -->
					<c:url var="tempLink" value="WarehouseControllerServlet">
						<c:param name="command" value="LOAD-DOCUMENT" />
						<c:param name="documentId" value="${tempDoc.id}" />
					</c:url>

					<!-- set up a link to delete a document -->
					<c:url var="deleteLink" value="WarehouseControllerServlet">
						<c:param name="command" value="DELETE-DOCUMENT" />
						<c:param name="documentId" value="${tempDoc.id}" />
					</c:url>

					<!-- set up a link to delete a document -->
					<c:url var="printLink" value="WarehouseControllerServlet">
						<c:param name="command" value="PRINT-DOCUMENT" />
						<c:param name="documentId" value="${tempDoc.id}" />
					</c:url>

					<tr>

						<td>${tempDoc.id}</td>
						<td>${tempDoc.date}</td>
						<td>${tempDoc.customer}</td>
						<td>${tempDoc.reciepient}</td>
						<td>${tempDoc.noOfDoc}</td>

						<td><a href="${tempLink}"><fmt:message key="label.update" /></a>
							| <a href="${deleteLink}"
							onClick="if (!(confirm('<fmt:message key="label.delete.message"/>'))) return false">
								<fmt:message key="label.delete" />
						</a> | <a href="${printLink}"><fmt:message key="label.print" /></a></td>

					</tr>

				</c:forEach>


			</table>

		</div>

	</div>

	<script>
		$(function() {

			$('#datepicker').datepicker({
				dateFormat : 'dd/mm/yy'
			});
		});
	</script>

</body>

</html>

