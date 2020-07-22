<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/egovframework/sample.css'/>"/>
<title>로그인 요청 발생</title>
<script type="text/javascript" src="<c:url value='/static/js/jquery/jquery-1.6.4.min.js' />"></script>
<script type="text/javascript">
    function gfn_showMessage() {
        var isSessionCopied = "<%=request.getParameter("isSessionCopied")%>";
        
        if(isSessionCopied=="true") {
        	alert("JSESSIONID 복제 해킹 시도가 발생하였습니다.\n모니터링되고 있습니다. 주의하십시오.");
        }
    }

    $(document).ready(function() {
        gfn_showMessage();
    });
</script>
</head>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="100%" height="100%" align="center" valign="middle" style="padding-top:150px;">
      <table border="0" cellspacing="0" cellpadding="0">
	    <tr>
		  <td class="error"><span style="font-family:Tahoma; font-weight:bold; color:#000000; line-height:150%; width:440px; height:70px;">로그인하여 주십시오.</span></td>
	    </tr>
	  </table>
	</td>
  </tr>
</table>
</body>
</html>