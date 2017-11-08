<%@ include file="/header.jsp" %>

<c:if test="${not empty account.errors}">
	<div class="alert alert-danger" role="alert">
	  <h4 class="alert-heading"><fmt:message key="transfer.label.failed"/></h4>
	  <p>${account.result}</p>
	</div>
</c:if>

<div class="container">
	<p><fmt:message key="bank.label.createAccount"/></p>
	<form method="post" action="createAccount">
	    <div class="form-group">
	          <label for="wording"><fmt:message key="bank.label.wording"/></label>
	          <input type="text" class="form-control" id="wording" name="wording">
	          <span class="error">${account.errors['wording']}</span>
	    </div>
		<fmt:message key="transfer.button.confirm" var="buttonValue" />
		<input type="submit" class="btn btn-success" style="float: right;" name="submit" value="<fmt:message key="transfer.button.confirm"/>"/>
   	</form>
   </div>
    
<%@ include file="/footer.jsp" %>