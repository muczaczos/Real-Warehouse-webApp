<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : not empty theLocale ? theLocale
 : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="com.muczo.mvc.warehouse.i18h.resources.mylabels" />

<!DOCTYPE html>
<html>

<head>
	<title><fmt:message key="label.title.update-warehouse"/></title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<a href="update-warehouse-form.jsp?command=LOAD-WAREHOUSE&warehouseId=${THE_WAREHOUSE.id}&theLocale=en_US">Polski (PL)</a>
	|
	<a href="update-warehouse-form.jsp?command=LOAD-WAREHOUSE&warehouseId=${THE_WAREHOUSE.id}&theLocale=pl_PL">English (US)</a>
	
	<div id="wrapper">
		<div id="header">
			<h2><fmt:message key="label.title.update-warehouse"/>...</h2>
		</div>
	</div>
	
	<div id="container">
		
			<br>
		<ul>
				<li><a href="WarehouseControllerServlet"><fmt:message
							key="label.menu.doc" /></a></li>
				<li><a href="create-invoice.jsp"><fmt:message
							key="label.menu.inv" /></a></li>
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
		
			<input type="hidden" name="command" value="UPDATE-WAREHOUSE" />

			<input type="hidden" name="warehouseId" value="${THE_WAREHOUSE.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.customer.name"/>:</label></td>
						<td><input type="text" name="name" 
								   value="${THE_WAREHOUSE.name}" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="<fmt:message key="label.button.save"/>" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="create-warehouse.jsp"><fmt:message key="label.back.warehouse"/></a>
		</p>
	</div>
</body>

</html>



