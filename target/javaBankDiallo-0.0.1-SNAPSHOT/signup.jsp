<%@ include file="/header.jsp"%>

<c:if test="${not empty signup.errors}">
	<div class="alert alert-danger" role="alert">
		<h4 class="alert-heading">
			<fmt:message key="login.label.error2" />
		</h4>
	</div>
</c:if>

<div class="container">
	<form method="post" action="signup">
		<div class="form-group">
			<label for="name"><fmt:message key="login.label.name" /></label> <input
				type="text" class="form-control" id="name" name="name">
			<c:if test="${signup.errors['name']=='name'}">
				<span class="error"><fmt:message key='login.error.name' /></span>
			</c:if>
		</div>
		<div class="form-group">
			<label for="firstname"><fmt:message
					key="login.label.firstname" /></label> <input type="text"
				class="form-control" id="firstname" name="firstname">
			<c:if test="${signup.errors['firstname']=='firstname'}">
				<span class="error"><fmt:message key='login.error.firstname' /></span>
			</c:if>
		</div>
		<div class="form-group">
			<label for="login"><fmt:message key="login.label.login" /></label> <input
				type="text" class="form-control" id="login" name="login">
			<c:if test="${signup.errors['login']=='login'}">
				<span class="error"><fmt:message key='login.error.login' /></span>
			</c:if>
			<c:if test="${signup.errors['login']=='login2'}">
				<span class="error"><fmt:message key='login.error.login2' /></span>
			</c:if>
		</div>
		<div class="form-group">
			<label for="passwd"><fmt:message key="login.label.password" /></label>
			<input type="password" class="form-control" id="passwd" name="passwd">
			<c:if test="${signup.errors['passwd']=='passwd'}">
				<span class="error"><fmt:message key='login.error.passwd' /></span>
			</c:if>
			<c:if test="${signup.errors['passwd']=='passwd2'}">
				<span class="error"><fmt:message key='login.error.passwd2' /></span>
			</c:if>
		</div>
		<fmt:message key="transfer.button.confirm" var="buttonValue" />
		<input type="submit" class="btn btn-success" style="float: right;"
			name="submit" value="<fmt:message key="transfer.button.confirm"/>" />
	</form>
</div>

<%@ include file="/footer.jsp"%>