<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

	<div class="container">
	
		<form method="post" action="signup">
		    <div class="form-group">
		          <label for="wording"><fmt:message key="bank.label.wording" /></label>
		          <input type="text" class="form-control" id="wording" name="wording">
		    </div>
			<fmt:message key="transfer.button.confirm" var="buttonValue" />
			<input type="submit" class="btn btn-success" style="float: right;" name="submit" value="<fmt:message key="transfer.button.confirm"/>"/>
    	</form>
    </div>
    
<%@ include file="/footer.jsp" %>