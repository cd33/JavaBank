<%@ include file="/header.jsp" %>

   	<c:if test="${not empty signup.errors}">
		<div class="alert alert-danger" role="alert">
		  <h4 class="alert-heading"><fmt:message key="login.label.error2"/></h4>
		  <p>${signup.result}</p>
		</div>
	</c:if>

	<div class="container">
	
		<form method="post" action="signup">
		    <div class="form-group">
		          <label for="name"><fmt:message key="login.label.name" /></label>
		          <input type="text" class="form-control" id="name" name="name">
		          <span class="error">${signup.errors['name']}</span>
		    </div>
		    <div class="form-group">
		          <label for="firstname"><fmt:message key="login.label.firstname" /></label>
		          <input type="text" class="form-control" id="firstname" name="firstname">
		          <span class="error">${signup.errors['firstname']}</span>
		    </div>
		    <div class="form-group">
		          <label for="login"><fmt:message key="login.label.login" /></label>
		          <input type="text" class="form-control" id="login" name="login">
		          <span class="error">${signup.errors['login']}</span>
		    </div>
		    <div class="form-group">
		          <label for="passwd"><fmt:message key="login.label.passwd" /></label>
		          <input type="password" class="form-control" id="passwd" name="passwd">
		          <span class="error">${signup.errors['passwd']}</span>
		    </div>
			<fmt:message key="transfer.button.confirm" var="buttonValue" />
			<input type="submit" class="btn btn-success" style="float: right;" name="submit" value="<fmt:message key="transfer.button.confirm"/>"/>
    	</form>
    </div>
    
<%@ include file="/footer.jsp" %>