<%@ include file="/header.jsp"%>

<c:if test="${not empty passwd.errors}">
	<div class="alert alert-danger" role="alert">
		<h4 class="alert-heading">
			<fmt:message key="transfer.label.failed" />
		</h4>
	</div>
</c:if>

<div class="container">
	<form method="post" action="parameters">
		<legend>
			<fmt:message key="bank.label.edit" />
			<fmt:message key="login.label.password" />
		</legend>

		<div class="form-group">
			<label for="oldPasswd"><fmt:message key="bank.label.old" />
				<fmt:message key="login.label.password" />:</label> <input type="password"
				class="form-control" id="oldPasswd" name="oldPasswd" size="20"
				maxlength="60">
			<c:if test="${passwd.errors['passwd']=='passwd'}">
				<span class="error"><fmt:message key='login.error.passwd'/></span>
			</c:if>
			<c:if test="${passwd.errors['passwd']=='passwd2'}">
				<span class="error"><fmt:message key='login.error.passwd2' /></span>
			</c:if>
		</div>

		<div class="form-group">
			<label for="newPasswd"><fmt:message key="bank.label.new" />
				<fmt:message key="login.label.password" />:</label> <input type="password"
				class="form-control" id="newPasswd" name="newPasswd" value=""
				size="20" maxlength="20" />
			<c:if test="${passwd.errors['passwd']=='passwd'}">
				<span class="error"><fmt:message key='login.error.passwd' /></span>
			</c:if>
			<c:if test="${passwd.errors['passwd']=='passwd2'}">
				<span class="error"><fmt:message key='login.error.passwd2' /></span>
			</c:if>
		</div>

		<div class="form-group">
			<label for="newPasswd2"><fmt:message
					key="transfer.button.confirm" /> <fmt:message key="bank.label.new" />
				<fmt:message key="login.label.password" />:</label> <input type="password"
				class="form-control" id="newPasswd2" name="newPasswd2" value=""
				size="20" maxlength="20" />
			<c:if test="${passwd.errors['passwd']=='passwd'}">
				<span class="error"><fmt:message key='login.error.passwd' /></span>
			</c:if>
			<c:if test="${passwd.errors['passwd']=='passwd2'}">
				<span class="error"><fmt:message key='login.error.passwd2' /></span>
			</c:if>
			<c:if test="${passwd.errors['passwd']=='passwd3'}">
				<span class="error"><fmt:message key='login.error.passwd3' /></span>
			</c:if>
		</div>
		<div class="button">
			<input type="submit" class="btn btn-primary btn-lg" name="submit" value="<fmt:message key="bank.label.update"/>"/>
		</div>
	</form>
</div>

<%@ include file="/footer.jsp"%>