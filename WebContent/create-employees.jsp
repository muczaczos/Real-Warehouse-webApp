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

<title><fmt:message key="label.title.create-employee" /></title>

<link type="text/css" rel="stylesheet" href="css/style2.css">
<link type="text/css" rel="stylesheet" href="css/add.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>

<body>
	<a href="create-employees.jsp?theLocale=en_US">Polski (PL)</a> |
	<a href="create-employees.jsp?theLocale=pl_PL">English (US)</a>

	<hr>

	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.h2.create-employee" />
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
			<input type="hidden" name="command" value="ADD-EMPLOYEE" />

			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.employee.name" />:
						</label></td>
						<td><input type="text" name="name" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.employee.surname" />:
						</label></td>
						<td><input type="text" name="surname" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.employee.address" />:
						</label></td>
						<td><input type="text" name="address" /></td>
					</tr>

					<tr>
						<td><label><fmt:message
									key="label.employee.telephone" />: </label></td>
						<td><input type="text" name="telephone" /></td>
					</tr>

					<tr>
						<td><label><fmt:message
									key="label.employee.profession" />: </label></td>
						<td><input type="text" name="profession" /></td>
					</tr>

					<tr>
						<td><label for="datepicker1"><fmt:message
									key="label.employee.safetyTraining" />:</label></td>
						<td><input type="text" name="safetyTraining" value="${Date}"
							id="datepicker1"></td>
					</tr>

					<tr>
						<td><label for="datepicker2"><fmt:message
									key="label.employee.medicalVisit" />:</label></td>
						<td><input type="text" name="medicalVisit" value="${Date}"
							id="datepicker2"></td>
					</tr>

					<tr>
						<td><label for="datepicker3"><fmt:message
									key="label.employee.contractDate" />:</label></td>
						<td><input type="text" name="contractDate" value="${Date}"
							id="datepicker3"></td>
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
					<th><fmt:message key="label.employee.name" /></th>
					<th><fmt:message key="label.employee.surname" /></th>
					<th><fmt:message key="label.employee.address" /></th>
					<th><fmt:message key="label.employee.telephone" /></th>
					<th><fmt:message key="label.employee.profession" /></th>
					<th><fmt:message key="label.employee.safetyTraining" /></th>
					<th><fmt:message key="label.employee.medicalVisit" /></th>
					<th><fmt:message key="label.employee.contractDate" /></th>
					<th><fmt:message key="label.action" /></th>
				</tr>

				<c:forEach var="tempEmploy" items="${Employees}">
					<!-- set up a link for each customers  -->
					<c:url var="tempLink" value="WarehouseControllerServlet">
						<c:param name="command" value="LOAD-EMPLOYEE" />
						<c:param name="employeeId" value="${tempEmploy.id}" />
					</c:url>

					<!-- set up a link to delete a document -->
					<c:url var="deleteLink" value="WarehouseControllerServlet">
						<c:param name="command" value="DELETE-EMPLOYEE" />
						<c:param name="employeeId" value="${tempEmploy.id}" />
					</c:url>

					<tr>

						<td>${tempEmploy.id}</td>
						<td>${tempEmploy.name}</td>
						<td>${tempEmploy.surname}</td>
						<td>${tempEmploy.address}</td>
						<td>${tempEmploy.telephone}</td>
						<td>${tempEmploy.profession}</td>
						<td>${tempEmploy.safetyTraining}</td>
						<td>${tempEmploy.medicalVisit}</td>
						<td>${tempEmploy.contractDate}</td>

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

	<script>
		$(function() {

			$('#datepicker1').datepicker({
				dateFormat : 'dd/mm/yy'
			});
		});
	</script>

	<script>
		$(function() {

			$('#datepicker2').datepicker({
				dateFormat : 'dd/mm/yy'
			});
		});
	</script>

	<script>
		$(function() {

			$('#datepicker3').datepicker({
				dateFormat : 'dd/mm/yy'
			});
		});
	</script>



</body>

</html>