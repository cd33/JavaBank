<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<%@ include file="/header.jsp" %>
	
	<div class="container">
	
		<form>
			<div class="form-group">
			  <label for="myAccounts"><fmt:message key="menu.label.myAccounts"/></label>
			  <select class="form-control" id="accountsFormControlSelect">
				<c:forEach items="${accountSender}" var="accountSender">
				   <option>${accountSender.libelle}</option>
				</c:forEach>
			  </select>
			</div>
		</form>

	    <form>
			<div class="form-group">
			  <label for="myAccounts"><fmt:message key="bank.label.accounts"/> <fmt:message key="bank.label.receiver"/></label>
			  <select class="form-control" id="accountsFormControlSelect2">
				<c:forEach items="${clients}" var="client">
					<c:forEach items="${client.comptes}" var="compte">
					   <option>${client.prenom} ${client.nom} : ${compte.libelle}</option>
					</c:forEach>
				</c:forEach>
				<input type="button" class="btn btn-success" style="margin-top: 25px; float: right;" value="<fmt:message key="transfer.button.confirm"/>" onclick="hideDiv('divHiden');"/>
			  </select>
			</div>
		 </form>
	    
	    <div id="divHiden" style="display:none; margin-top:80px;">
		    <form method="post" action="transfer">
		          <label for="amount"><fmt:message key="bank.label.amount" /></label>
		          <input type="number" class="form-control" id="amount" name="amount">
		          <br>
		          <label for="wording"><fmt:message key="bank.label.wording" /></label>
		          <input type="text" class="form-control" id="wording" name="wording">
		          <br>
		          <fmt:message key="transfer.button.confirm" var="buttonValue" />
		          <input type="submit" class="btn btn-success" style="float: right;" name="submit" value="<fmt:message key="transfer.button.makeTransfer"/>"/>
	     	</form>
	    </div> 
    
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