<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="fr.ynovBank.javaBankDiallo.i18n.text" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
<link rel="stylesheet" href="CSS/styles.css"/>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
</head>
<body>
<div id='cssmenu'>
	<ul>
		<li><a href="/Comptes">Mes Comptes</a></li>
		<li><a href="/Virements">Virements</a></li>
		<li><a href="/">Déconnexion</a></li>
	</ul>
</div>
<form>
    <select id="language" name="language" onchange="submit()">
   		<option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
      	<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
    </select>
</form>

</body>
</html>