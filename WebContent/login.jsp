<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="fr.ynovBank.javaBankDiallo.i18n.text" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
<title>BANKDIALLO - <fmt:message key="login.button.submit"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>

   	<c:if test="${not empty login.errors}">
		<div class="alert alert-danger" role="alert">
		  <h4 class="alert-heading"><fmt:message key="login.label.error"/></h4>
		</div>
	</c:if>
	<c:if test="${not empty accountFail}">
		<div class="alert alert-danger" role="alert">
		  <h4 class="alert-heading"><fmt:message key="login.connection.failed"/></h4>
		</div>
	</c:if>
	<c:if test="${not empty expiredMessage}">
		<div class="alert alert-warning" role="alert">
		  <h4 class="alert-heading"><fmt:message key="login.label.sesExp"/></h4>
		  <fmt:message key="login.label.sesExp2"/>
		</div>
	</c:if>

	<div class="container" style="margin-top:200px;">
		<form class="loginlangage">
		    <select class="custom-select" id="language" name="language" onchange="submit()">
		    		<option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
		       	<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
		    </select>
		</form>
			
		<form method="post" action="login">
			<legend><fmt:message key="login.button.submit"/></legend>
			
			<div class="form-group">
			    <label for="login"><fmt:message key="login.label.login" />:</label>
			    <input type="text" class="form-control" id="login" name="login" size="20" maxlength="60">
			    <c:if test="${login.errors['login']=='login'}">
					<span class="error"><fmt:message key='login.error.login'/></span>
			  	</c:if>
			  	<c:if test="${login.errors['login']=='login2'}">
					<span class="error"><fmt:message key='login.error.login2'/></span>
			  	</c:if>
	        </div>
	          
          	<div class="form-group">
			    <label for="passwd"><fmt:message key="login.label.password" />:</label>
			    <input type="password" class="form-control" id="passwd" name="passwd" value="" size="20" maxlength="20" />
	            <c:if test="${login.errors['passwd']=='passwd'}">
					<span class="error"><fmt:message key='login.error.passwd'/></span>
			  	</c:if>
			  	<c:if test="${login.errors['passwd']=='passwd2'}">
					<span class="error"><fmt:message key='login.error.passwd2'/></span>
			  	</c:if>
        	</div>
	        <div class="buttons">   
			    <div class="button" style="float:left">
			    	<input type="submit" class="btn btn-primary btn-lg" name="submit" value="<fmt:message key="login.button.submit"/>"/>
		    	</div>
		    	<div class="button" style="float:right">
	      			<a class="btn btn-success btn-lg" role="button" href="${pageContext.request.contextPath}/signup"><fmt:message key="bank.label.signup"/></a>
   				</div>
   			</div>
		</form>
	</div>

<%@ include file="/footer.jsp" %>