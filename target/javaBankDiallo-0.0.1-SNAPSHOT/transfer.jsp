<%@ include file="/header.jsp" %>
		
<c:if test="${not empty transfer.errors}">
    <div class="alert alert-danger" role="alert">
		<h4 class="alert-heading"><fmt:message key="transfer.label.failed"/></h4>
		<p><fmt:message key="transfer.label.transferFailed"/></p>
		<c:if test="${transfer.errors['amount']=='amount'}">
			<p><span class="error"><fmt:message key="transfer.error.amount" /></span></p>
		</c:if>
		<c:if test="${transfer.errors['amount']=='amount2'}">
			<p><span class="error"><fmt:message key="transfer.error.amount2" /></span></p>
		</c:if>
		<c:if test="${transfer.errors['wording']=='wording'}">
			<p><span class="error"><fmt:message key="transfer.error.wording" /></span></p>
		</c:if>
		<c:if test="${transfer.errors['wording']=='wording2'}">
			<p><span class="error"><fmt:message key="transfer.error.wording2" /></span></p>
		</c:if>
	</div>
</c:if>

<div class="container">
	<form method="post" action="transfer">
		<legend><fmt:message key="transfer.button.makeTransfer"/></legend>
		<div class="form-group">
		  <label for="myAccounts"><fmt:message key="menu.label.myAccounts"/></label>
		  <select class="form-control" id="accountsFormControlSelect" name="accountsFormControlSelect" onchange="displayList();">
			<c:forEach items="${client.accounts}" var="accountSender">
			   <option value="${accountSender.number}">${accountSender.wording}</option>
			</c:forEach>
		  </select>
		</div>

		<div class="form-group" id="list2" >
		  <label for="myAccounts"><fmt:message key="bank.label.accounts"/> <fmt:message key="bank.label.receiver"/></label>
		  <select class="form-control" id="accountsFormControlSelect2" name="accountsFormControlSelect2">
			<c:forEach items="${clients}" var="client">
				<c:forEach items="${client.accounts}" var="account">
				   <option value="${account.number}">${client.firstname} ${client.name} : ${account.wording}</option>
				</c:forEach>
			</c:forEach>
		  </select>
		  <div class="button">
	    	 <button type="button" class="btn btn-primary btn-lg" style="margin-top: 25px;" onclick="hideDiv('divHiden2');"><fmt:message key="transfer.button.confirm"/></button>
    	  </div>
		</div>

		<div id="divHiden2" style="display: none; margin-top: 80px;">
			<div class="form-group">
				<label for="amount"><fmt:message key="bank.label.amount" /></label>
				<input type="number" class="form-control" id="amount" name="amount">
			</div>
			<div class="form-group">
				<label for="wording"><fmt:message key="bank.label.wording" /></label>
				<input type="text" class="form-control" id="wording" name="wording">
			</div>
			<div class="button">
				<input type="submit" class="btn btn-success btn-lg" name="submit"
					value="<fmt:message key="transfer.button.makeTransfer"/>" />
			</div>
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
	
	function updateDiv(div)
	{ 
	    $( "#"+div+"" ).load(window.location.href + "#"+div+"" );
	}
	
	function displayList() {
	    var selectBox = document.getElementById("accountsFormControlSelect");
	    var selectBox2 = document.getElementById("accountsFormControlSelect2");
	    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	    var selectedValue2 = selectBox2.innerHTML.indexOf('value="' + selectedValue + '"');
	    if (selectedValue==selectedValue2) {
	    	//$("#accountsFormControlSelect2 option[value='"+selectedValue+"']").hide();
	    	selectedValue2.hide();
	    }
	    updateDiv(list2);
	}
</script>

<%@ include file="/footer.jsp" %>