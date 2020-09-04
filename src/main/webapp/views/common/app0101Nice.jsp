<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${header['Referer'] eq null || fn:contains(header['Referer'], 'pass') || fn:contains(header['Referer'], 'passApp')}">


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes" />
<title>본인인증</title>

<script src="<c:url value='/js/jquery-2.1.4.js'/>"></script>
<script src="<c:url value='/js/jquery-ui.js'/>"></script>
<script src="<c:url value='/js/jquery.blockUI.js'/>"></script>
<script src="<c:url value='/js/commonUtil.js'/>"></script>

<script type="text/javascript">


$(document).ready(function() {
	goAction('AUTH_PRIVACY');
	
});

window.name ="Parent_window";
function authPopup(){
	//window.open('', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
	document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
	//document.form_chk.target = "popupChk";
	document.form_chk.submit();
}

function authSucc(code, name, mobi, msg){
	if(code == '0'){
		$("#order_name").val(name);
		$("#order_mobile").val(mobi);
		
		alert('성공');
	}else{
		alert("개인 인증에 에러가 발생했습니다.[" + code + "][" + msg + "]");		
	}
}

function authFail(code, msg){
	alert('실패');
	alert("개인 인증에 에러가 발생했습니다.[" + code + "][" + msg + "]");		
}	


function goAction(act){
	
	var dataArr = getFormData($("#form1")); 
	dataArr.action = act;
	
	$.ajax({
	    type : "POST" 
	    , async : true
	    , url : "/common/commonAction.do"
	    , dataType : "json"
	    , data : dataArr
	    ,beforeSend: function() {
			$.blockUI({ message: '<img src="<c:url value='/images/busy.gif'/>" width="100"/>', css : {backgroundColor: 'rgba(0,0,0,0.0)', color: '#000000', border: '0px solid #a00'}});
	    }
	    , error : function(request, status, error) {
	    	$.unblockUI();
	    	alert("처리 중 오류가 발생하였습니다.");
	    }
	    , success : function(data) {
	    	$.unblockUI();
	    	if(data.RETURN_CODE == "E"){
	    		alert(data.RETURN_MSG);
	    		return;
	    	}else{
	    		
	    		if(act == "AUTH_PRIVACY"){
	    			$("#EncodeData").val(data.auth_enc_data);
	    			authPopup();
	    		}
	    		
	    	}
	    }
	});   
}


</script>

</head>
<body>
<div id="wrap">

	<form name="form1" id="form1" method="post"  >
		<input type="hidden" name="order_name" id="order_name" value="" />
		<input type="hidden" name="order_mobile" id="order_mobile" value="" />	
		<input type="hidden" name="order_birth" id="order_birth" value="" />	
    </form>
    
    <form name="form_chk" method="post">
		<input type="hidden" name="m" value="checkplusSerivce">
		<input type="hidden" name="EncodeData" id="EncodeData" value="">
	</form>
    
    <!-- //container -->
  
    
</div>


</body>
</html>

</c:if>
