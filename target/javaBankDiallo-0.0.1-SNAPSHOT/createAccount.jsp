<%@ include file="/header.jsp" %>

<c:if test="${not empty account.errors}">
	<div class="alert alert-danger" role="alert">
	  <h4 class="alert-heading"><fmt:message key="transfer.label.failed"/></h4>
	  <c:forEach items="${account.errors}" var="p">
	  	<c:if test="${p.value=='wording'}">
			<fmt:message key="transfer.error.wording"/>
		</c:if>
		<c:if test="${p.value=='wording2'}">
			<fmt:message key="transfer.error.wording2"/>
		</c:if>
	  </c:forEach>
	</div>
</c:if>

<div class="container">
	<form method="post" action="createAccount">
		<legend><fmt:message key="bank.label.createAccount"/></legend>
	    <div class="form-group">
	          <label for="wording"><fmt:message key="bank.label.wording"/></label>
	          <input type="text" class="form-control" id="wording" name="wording">
	          <c:if test="${account.errors['wording']=='wording'}">
					<span class="error"><fmt:message key="transfer.error.wording"/></span>
			  </c:if>
			  <c:if test="${account.errors['wording']=='wording2'}">
					<span class="error"><fmt:message key="transfer.error.wording2"/></span>
			  </c:if>
	    </div>
		<div class="button">
	    	<input type="submit" class="btn btn-success btn-lg" name="submit" value="<fmt:message key="transfer.button.confirm"/>"/>
    	</div>
   	</form>
</div>
    
<%@ include file="/footer.jsp" %>