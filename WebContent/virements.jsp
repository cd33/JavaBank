<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<%@ include file="/header.jsp" %>
	
	<c:if test="${not empty success}">
		<div class="alert alert-success" role="alert">
		  <h4 class="alert-heading"><fmt:message key="transfer.label.success"/></h4>
		  <p><fmt:message key="transfer.label.transferMade"/></p>
		</div>
	</c:if>
	
	<div class="container">
	
		<form method="post" action="transfer">
			<div class="form-group">
			  <label for="myAccounts"><fmt:message key="menu.label.myAccounts"/></label>
			  <select class="form-control" id="accountsFormControlSelect" name="accountsFormControlSelect">
				<c:forEach items="${accountSender}" var="accountSender">
				   <option value="${accountSender.numero}">${accountSender.libelle}</option>
				</c:forEach>
			  </select>
			</div>

			<div class="form-group">
			  <label for="myAccounts"><fmt:message key="bank.label.accounts"/> <fmt:message key="bank.label.receiver"/></label>
			  <select class="form-control" id="accountsFormControlSelect2" name="accountsFormControlSelect2">
				<c:forEach items="${clients}" var="client">
					<c:forEach items="${client.comptes}" var="compte">
					   <option value="${compte.numero}">${client.prenom} ${client.nom} : ${compte.libelle}</option>
					</c:forEach>
				</c:forEach>
			  </select>
			  <input type="button" class="btn btn-success" style="margin-top: 25px; float: right;" value="<fmt:message key="transfer.button.confirm"/>" onclick="hideDiv('divHiden');"/>
			</div>
	    
		    <div id="divHiden" style="display:none; margin-top:80px;">
			    <div class="form-group">
			          <label for="amount"><fmt:message key="bank.label.amount" /></label>
			          <input type="number" class="form-control" id="amount" name="amount">
			    </div>
			    <div class="form-group">
			          <label for="wording"><fmt:message key="bank.label.wording" /></label>
			          <input type="text" class="form-control" id="wording" name="wording">
			    </div>
				<fmt:message key="transfer.button.confirm" var="buttonValue" />
				<input type="submit" class="btn btn-success" style="float: right;" name="submit" value="<fmt:message key="transfer.button.makeTransfer"/>"/>
		    </div> 
    	</form>
    </div>
    	
	<script type="text/javascript">
		function hideDiv(id) {
			if (document.getElementById(id).style.display == 'none') {
				document.getElementById(id).style.display = 'block';
			}
			else {
				document.getElementById(id).style.display = 'none';
			}
		}
	</script>

<%@ include file="/footer.jsp" %>