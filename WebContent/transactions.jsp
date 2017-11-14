<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/header.jsp"%>

<div class="container">
	<p style="font-size: 20px; color: green;">${account.wording}</p>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th><fmt:message key="bank.label.wording" /></th>
				<th><fmt:message key="bank.label.date" /></th>
				<th><fmt:message key="bank.label.amount" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${transactionsList}" var="transaction">
				<tr>
					<td>${transaction.wording}</td>
					<td>${transaction.date}</td>
					<td>${transaction.amount}€</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div style="text-align: center; margin-top: 20px; font-size: 20px;">
		<fmt:message key="bank.label.balance" />
		: ${balance}
		<fmt:message key="bank.label.currency" />
	</div>
</div>

<%@ include file="/footer.jsp" %>