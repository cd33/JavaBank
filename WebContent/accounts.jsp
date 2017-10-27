<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/menu.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><fmt:message key="menu.label.myAccounts"/></title>
	</head>
    <body>
    
    <p><fmt:message key="bank.label.welcome"/> ${client.prenom} ${client.nom}</p>
		
	<c:forEach items="${comptes}" var="compte" varStatus="loop">
	   <p><fmt:message key="bank.label.account"/> ${loop.count} : ${compte.libelle}</p>
	</c:forEach>
        
    </body>
</html>