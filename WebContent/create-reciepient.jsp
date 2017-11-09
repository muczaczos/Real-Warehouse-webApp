<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale 
: pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="com.muczo.mvc.warehouse.i18h.resources.mylabels" />

<!DOCTYPE html>
<html>

<head>

<head>
	<title><fmt:message key="label.title.create-reciepient"/></title>
	
	<link type="text/css" rel="stylesheet" href="css/style2.css">
	<link type="text/css" rel="stylesheet" href="css/add.css">
</head>

<body>
	<a href="create-reciepient.jsp?theLocale=en_US">English (US)</a>
	|
	<a href="create-reciepient.jsp?theLocale=pl_PL">Polski (PL)</a>
	
	<hr>
	
	<div id="wrapper">
		<div id="header">
			<h2><fmt:message key="label.h2.create-reciepient"/>...</h2>
		</div>
	</div>
	
	<div id="container">
		
		
		<form action="WarehouseControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD-RECIEPIENT"/>
			
			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.customer.name"/>: </label></td>
						<td><input type="text"name="name"/></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.address"/>: </label></td>
						<td><input type="text"name="address"/></td>
					</tr>
					
					<tr>
						<td><label><fmt:message key="label.telephone"/>: </label></td>
						<td><input type="text"name="telephone"/></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="<fmt:message key="label.button.save"/>" class="save"/></td>
					</tr>
					
				</tbody>
			</table>
		</form>

		
		<br>
			<br>
			<form action = "WarehouseControllerServlet" method="GET">
					
			<table>
				
				<tr>
					<th> Id </th>
					<th> <fmt:message key="label.customer.name"/> </th>
					<th> <fmt:message key="label.address"/></th>
					<th> <fmt:message key="label.telephone"/> </th>
					<th> <fmt:message key="label.action"/></th>
				</tr>
			
				<c:forEach var="tempRec" items="${Reciepients}">
				<!-- set up a link for each customers  -->
					<c:url var="tempLink" value="WarehouseControllerServlet">
						<c:param name="command" value="LOAD-RECIEPIENT"/>
						<c:param name="reciepientId" value="${tempRec.id}"/>
					</c:url>
				
					<!-- set up a link to delete a document -->
					<c:url var="deleteLink" value="WarehouseControllerServlet">
						<c:param name="command" value="DELETE-RECIEPIENT"/>
						<c:param name="reciepientId" value="${tempRec.id}"/>
					</c:url>
					
					<tr>
					
						<td>${tempRec.id} </td>
						<td>${tempRec.name} </td>
						<td>${tempRec.address} </td>
						<td>${tempRec.telephone} </td>
						
						<td> <a href="${tempLink}"><fmt:message key="label.update"/></a>
							|
							<a href="${deleteLink}"
							onClick="if (!(confirm('<fmt:message key="label.delete.message"/>'))) return false">
							<fmt:message key="label.delete"/></a>
						</td>
						
					</tr>
					
				</c:forEach>

				
			</table>
			
			</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="WarehouseControllerServlet"><fmt:message key="label.back.home"/></a>
		</p>
	</div>

	

</body>

</html>