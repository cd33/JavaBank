<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    	<%@ include file="/header.jsp" %>
		
		<div class="container">
			<ul class="list-group">
				<c:forEach items="${client.accounts}" var="account" varStatus="loop">
			   		<li class="list-group-item"><a href="${pageContext.request.contextPath}/transactions/${account.number-1}"><fmt:message key="bank.label.account"/> ${account.number} : ${account.wording}
			   		<div style="float:right;"><fmt:message key="bank.label.balance"/> : ${balanceAccount[account.number]} <fmt:message key="bank.label.currency"/></div></a></li>
				</c:forEach>
			</ul>
		
			<div style="text-align:center; margin-top:20px; font-size:20px;"><fmt:message key="bank.label.balanceAvailable"/> : ${balanceAccountAvailable} <fmt:message key="bank.label.currency"/></div>
		</div>
        
<%@ include file="/footer.jsp" %>