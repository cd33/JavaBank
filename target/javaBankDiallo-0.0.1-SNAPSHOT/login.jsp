<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="fr.ynovBank.javaBankDiallo.i18n.text" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
</head>
<body>

      <form>
          <select id="language" name="language" onchange="submit()">
          		<option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
             	<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
          </select>
      </form>
      <br><br><br>
      <form method="post">
          <label for="login"><fmt:message key="login.label.login" />:</label>
          <input type="text" id="login" name="login">
          <br>
          <label for="password"><fmt:message key="login.label.password" />:</label>
          <input type="password" id="password" name="password">
          <br>
          <fmt:message key="login.button.submit" var="buttonValue" />
          <input type="submit" name="submit" value="${buttonValue}">
      </form>

</body>
</html>
        
<!--  
<form name="firstForm" action="Controleur" style="width:50%;margin:auto;background-color:#c1d9fc;padding-bottom:15px;" method="post">
                 
      <h2 style="text-align:center;color:white;background-color:#6683b1;">Espace Client</h2>
      <p style="text-align:center;">Nom : <input type="text" name="login" /></p>
      <p style="text-align:center;">Prénom : <input type="password" name="pwd" /></p>
           
      <p style="text-align:center;width:50%;margin:auto;"><input type="submit" name="Valider" value="Valider"/></p>
         
</form>
-->