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
<title><fmt:message key="label.title.update-employee" /></title>

<link type="text/css" rel="stylesheet" href="css/style2.css">
<link type="text/css" rel="stylesheet" href="css/add.css">

</head>

<body>

<hr>
	<a
		href="EmployeesControllerServlet?command=LOAD-EMPLOYEE&employeeId=${THE_EMPLOYEE.id}&theLocale=en_US">Polski
		(PL)</a> |
	<a
		href="EmployeesControllerServlet?command=LOAD-EMPLOYEE&employeeId=${THE_EMPLOYEE.id}&theLocale=pl_PL">English
		(US)</a>
	<hr>
	<div id="wrapper">
		<div id="header">
			<h2>
				<fmt:message key="label.title.update-employee" />
			</h2>
		</div>
	</div>

	<div id="container">

		<br> <br> <br>

		<form action="EmployeesControllerServlet" method="GET">

			<input type="hidden" name="command" value="UPDATE-EMPLOYEE" /> <input
				type="hidden" name="employeeId" value="${THE_EMPLOYEE.id}" />

			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.employee.name" />:
						</label></td>
						<td><input type="text" name="name"
							value="${THE_EMPLOYEE.name}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.employee.surname" />:
						</label></td>
						<td><input type="text" name="surname"
							value="${THE_EMPLOYEE.surname}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message key="label.employee.address" />:
						</label></td>
						<td><input type="text" name="address"
							value="${THE_EMPLOYEE.address}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message
									key="label.employee.telephone" />: </label></td>
						<td><input type="text" name="telephone"
							value="${THE_EMPLOYEE.telephone}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message
									key="label.employee.profession" />: </label></td>
						<td><input type="text" name="profession"
							value="${THE_EMPLOYEE.profession}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message
									key="label.employee.safetyTraining" />: </label></td>
						<td><input type="text" name="safetyTraining"
							value="${THE_EMPLOYEE.safetyTraining}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message
									key="label.employee.medicalVisit" />: </label></td>
						<td><input type="text" name="medicalVisit"
							value="${THE_EMPLOYEE.medicalVisit}" /></td>
					</tr>

					<tr>
						<td><label><fmt:message
									key="label.employee.contractDate" />: </label></td>
						<td><input type="text" name="contractDate"
							value="${THE_EMPLOYEE.contractDate}" /></td>
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
			<a href="EmployeesControllerServlet"><fmt:message
					key="label.back.employee" /></a>
		</p>

	</div>

</body>

</html>



