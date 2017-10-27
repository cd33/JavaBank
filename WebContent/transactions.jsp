<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
		<%@ include file="/header.jsp" %>
		
		<p><fmt:message key="bank.label.account"/> : ${Compte.libelle}</p>
		
		<c:forEach items="${listeTransactions}" var="transaction">
		   <p><fmt:message key="bank.label.transfer"/> : ${transaction.libelle}; Date : ${transaction.date}; <fmt:message key="bank.label.amount"/> : ${transaction.montant} €</p>
		</c:forEach> 
		<p><fmt:message key="bank.label.balance"/> : ${Solde} €</p>
		
	</body>
</html>