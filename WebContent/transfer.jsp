<%@ include file="/header.jsp" %>
		
<!--<c:if test="${empty transfer.errors}">
	<div class="alert alert-success" role="alert">
		<h4 class="alert-heading"><fmt:message key="transfer.label.success"/></h4>
		<p><fmt:message key="transfer.label.transferMade"/></p>
	</div>
</c:if>-->
<c:if test="${not empty transfer.errors}">
    <div class="alert alert-danger" role="alert">
		<h4 class="alert-heading"><fmt:message key="transfer.label.failed"/></h4>
		<p><fmt:message key="transfer.label.transferFailed"/></p>
		<p>${transfer.errors}</p>
	</div>
</c:if>

<div class="container">
	<form method="post" action="transfer">
		<div class="form-group">
		  <label for="myAccounts"><fmt:message key="menu.label.myAccounts"/></label>
		  <select class="form-control" id="accountsFormControlSelect" name="accountsFormControlSelect" onchange="displayList();">
			<c:forEach items="${accountSender}" var="accountSender">
			   <option value="${accountSender.number}">${accountSender.wording}</option>
			</c:forEach>
		  </select>
		</div>

		<div class="form-group" id="divHiden" style="display:none;" >
		  <label for="myAccounts"><fmt:message key="bank.label.accounts"/> <fmt:message key="bank.label.receiver"/></label>
		  <select class="form-control" id="accountsFormControlSelect2" name="accountsFormControlSelect2">
			<c:forEach items="${clients}" var="client">
				<c:forEach items="${client.accounts}" var="account">
				   <option value="${account.number}">${client.firstname} ${client.name} : ${account.wording}</option>
				</c:forEach>
			</c:forEach>
		  </select>
		  <input type="button" class="btn btn-success" style="margin-top: 25px; float: right;" value="<fmt:message key="transfer.button.confirm"/>" onclick="hideDiv('divHiden2');"/>
		</div>
    
	    <div id="divHiden2" style="display:none; margin-top:80px;">
		    <div class="form-group">
		          <label for="amount"><fmt:message key="bank.label.amount" /></label>
		          <input type="number" class="form-control" id="amount" name="amount">
		          <span class="error">${transfer.errors['amount']}</span>
		    </div>
		    <div class="form-group">
		          <label for="wording"><fmt:message key="bank.label.wording" /></label>
		          <input type="text" class="form-control" id="wording" name="wording">
		          <span class="error">${transfer.errors['wording']}</span>
		    </div>
			<fmt:message key="transfer.button.confirm" var="buttonValue" />
			<input type="submit" class="btn btn-success" style="float: right;" name="submit" value="<fmt:message key="transfer.button.makeTransfer"/>"/>
	    </div> 
   	</form>
</div>
   	
<script type="text/javascript">
	function hideDiv(id) {
		if (document.getElementById(id).style.display == 'none') {
			document.getElementById(id).style.display = 'block';
		}
		/*else {
			document.getElementById(id).style.display = 'none';
		}*/
	}
	
	/*function updateDiv(div)
	{ 
	    $( "#"+div+"" ).load(window.location.href + "#"+div+"" );
	}*/
	
	function displayList() {
	    var selectBox = document.getElementById("accountsFormControlSelect");
	    var selectBox2 = document.getElementById("accountsFormControlSelect2");
	    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	    var selectedValue2 = selectBox2.innerHTML.indexOf('value="' + selectedValue + '"');
	    if (selectedValue==selectedValue2) {
	    	//$("#accountsFormControlSelect2 option[value='"+selectedValue+"']").hide();
	    	selectedValue2.hide();
	    }
	    hideDiv(divHiden);
	    //updateDiv(divHiden);
	}
</script>

<%@ include file="/footer.jsp" %>