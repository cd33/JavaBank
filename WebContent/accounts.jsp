<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    	<%@ include file="/header.jsp" %>

		<c:forEach items="${client.comptes}" var="compte" varStatus="loop">
	   		<p><a href="${pageContext.request.contextPath}/transactions/${compte.numero-1}"><fmt:message key="bank.label.account"/> ${compte.numero} : ${compte.libelle} * Solde : ${balanceAccount[compte.numero]}</a></p>
		</c:forEach>
        
    </body>
</html>