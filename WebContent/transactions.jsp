<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/menu.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="bank.label.transfer"/></title>
</head>
<body>
    
    <p><fmt:message key="bank.label.welcome"/> ${client.prenom} ${client.nom}</p>   
	
	<p><fmt:message key="bank.label.account"/> : ${Compte.libelle}</p>
	
	<c:forEach items="${listeTransactions}" var="transaction">
	   <p><fmt:message key="bank.label.transfer"/> : ${transaction.libelle}; Date : ${transaction.date}; <fmt:message key="bank.label.amount"/> : ${transaction.montant} €</p>
	</c:forEach> 
	<p><fmt:message key="bank.label.balance"/> : ${Solde} €</p>
</body>
</html>