<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="fr.ynovBank.javaBankDiallo.i18n.text" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
<title><fmt:message key="login.button.submit"/></title>
</head>
<body>

		<form style="float : right">
		    <select id="language" name="language" onchange="submit()">
		    		<option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
		       	<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
		    </select>
		</form>
		
		<br><br>
		
		<c:if test="${ !empty sessionScope.sessionClient.login && !empty sessionScope.sessionClient.passwd}">
			<fmt:message key="bank.label.welcome"/> ${sessionScope.sessionClient.login}
		</c:if>
		
		<br><br>
		
		<form method="post" action="login">
			<fieldset>
				<legend><fmt:message key="login.button.submit"/></legend>
				
			    <label for="login"><fmt:message key="login.label.login" />:</label>
			    <input type="text" id="login" name="login" size="20" maxlength="60" >
			    <span class="error">${logM.errors['login']}</span>
	            <br/>
	           
			    <label for="password"><fmt:message key="login.label.password" />:</label>
			    <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
	            <span class="error">${logM.errors['passwd']}</span>
	            <br/>
	            
			    <fmt:message key="login.button.submit" var="buttonValue" />
			    <input type="submit" name="submit" value="${buttonValue}">
			    <br/>
			    
			    <p class="${empty logM.errors ? 'success' : 'error'}">${logM.result}</p>
            </fieldset>
		</form>

</body>
</html>