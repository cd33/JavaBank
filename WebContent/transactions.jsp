<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction</title>
</head>
<body>
    <%@ include file="/menu.jsp" %>
    
    <p>Bienvenue ${client.prenom} ${client.nom}</p>   
	
	<p>Compte : ${Compte.libelle}</p>
	
	<c:forEach items="${listeTransactions}" var="transaction">
	   <p>Transaction : ${transaction.libelle}; Date : ${transaction.date}; Montant : ${transaction.montant} €</p>
	</c:forEach> 
	<p>Solde : ${Solde} €</p>
</body>
</html>