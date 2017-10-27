<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Virements</title>
</head>
<body>
	<%@ include file="/menu.jsp" %>
    
    <p><fmt:message key="bank.label.welcome"/> ${client.prenom} ${client.nom}</p>   
	
	<p><fmt:message key="menu.label.myAccounts"/></p>
	
	<FORM>
	<SELECT name="Comptes Émetteur" size="1">
	<c:forEach items="${comptesEme}" var="compteEme">
	   <OPTION>${compteEme.libelle}
	</c:forEach>
	</SELECT>
    </FORM>
    
    <p><fmt:message key="bank.label.accounts"/> <fmt:message key="bank.label.receiver"/></p>
    
    <FORM>
		<SELECT name="Receiver Accounts" size="1">
			<c:forEach items="${clients}" var="client2">
				<c:forEach items="${client2.comptes}" var="compte">
			   		<OPTION>${client2.prenom} ${client2.nom} : ${compte.libelle}
				</c:forEach>
			</c:forEach>
			<input type="button" value="<fmt:message key="transfer.button.confirm"/>" onclick="hideDiv('divHiden');" />
		</SELECT>
    </FORM>
    
    <div id="divHiden" style="display:none;">
	    <form method="post">
	          <label for="amount"><fmt:message key="bank.label.amount" />:</label>
	          <input type="number" id="amount" name="amount">
	          <br>
	          <label for="wording"><fmt:message key="bank.label.wording" />:</label>
	          <input type="text" id="wording" name="wording">
	          <br>
	          <fmt:message key="transfer.button.confirm" var="buttonValue" />
	          <input type="submit" name="submit" value="${buttonValue}">
     	</form>
    </div> 
    	
	<script type="text/javascript">
		function hideDiv(id) {
			if (document.getElementById(id).style.display == 'none') {
				document.getElementById(id).style.display = 'block';
			}
			else {
				document.getElementById(id).style.display = 'none';
			}
		}
	</script>
</body>
</html>