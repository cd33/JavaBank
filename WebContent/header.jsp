<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="fr.ynovBank.javaBankDiallo.i18n.text" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css"/>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
</head>
<body>
<div id='cssmenu'>
	<ul>
		<li><a href="${pageContext.request.contextPath}/accounts"><fmt:message key="menu.label.myAccounts"/></a></li>
		<li><a href="${pageContext.request.contextPath}/transfer"><fmt:message key="menu.label.transfers"/></a></li>
		<li><a href="${pageContext.request.contextPath}/logout"><fmt:message key="menu.label.logout"/></a></li>
		<li style="float : right">
		<form>
			<select id="language" name="language" onchange="submit()">
		   		<option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
		      	<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
	    	</select>
		</form>
		</li>
	</ul>
</div>
<div>
	<span>${client.getPrenom()} ${client.getNom()}</span>
</div>