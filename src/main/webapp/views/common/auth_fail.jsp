<%@ page language="java" contentType="text/html;charset=euc-kr" import="com.admin.upload.common.util.CommonUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

    String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");

    String sCipherTime = "";	// 복호화한 시간
    String sErrorCode = "";		// 인증 결과코드
    String sAuthType = "";		// 인증 수단
    String sMessage = "";
    String sPlainData = "";
    String errMessage = "";
    String sSiteCode = CommonUtil.getProperty("siteCode");		// NICE로부터 부여받은 사이트 코드
    String sSitePassword = CommonUtil.getProperty("sitePass");	// NICE로부터 부여받은 사이트 패스워드
    String sRequestNumber = CommonUtil.getProperty("reqNumber");	// 요청 번호

    int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);
    if( iReturn == 0 )
    {
        sPlainData = niceCheck.getPlainData();
        sCipherTime = niceCheck.getCipherDateTime();
        
        // 데이타를 추출합니다.
        java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
        sRequestNumber 	= (String)mapresult.get("REQ_SEQ");
        sErrorCode 		= (String)mapresult.get("ERR_CODE");
        sAuthType 		= (String)mapresult.get("AUTH_TYPE");
        
        if("0001".equals(sErrorCode)){
        	errMessage = "인증 불일치";
        }else if("0003".equals(sErrorCode)){
        	errMessage = "기타인증오류";
        }else if("0010".equals(sErrorCode)){
        	errMessage = "인증번호 불일치(소켓)";
        }else if("0012".equals(sErrorCode)){
        	errMessage = "요청정보오류(입력값오류)";
        }else if("0013".equals(sErrorCode)){
        	errMessage = "암호화 시스템 오류";
        }else if("0014".equals(sErrorCode)){
        	errMessage = "암호화 처리 오류";
        }else if("0015".equals(sErrorCode)){
        	errMessage = "암호화 데이터 오류";
        }else if("0016".equals(sErrorCode)){
        	errMessage = "복호화 처리 오류";
        }else if("0017".equals(sErrorCode)){
        	errMessage = "복호화 데이터 오류";
        }else if("0018".equals(sErrorCode)){
        	errMessage = "통신오류";
        }else if("0019".equals(sErrorCode)){
        	errMessage = "데이터베이스 오류";
        }else if("0020".equals(sErrorCode)){
        	errMessage = "유효하지않은 CP코드";
        }else if("0021".equals(sErrorCode)){
        	errMessage = "중단된 CP코드";
        }else if("0022".equals(sErrorCode)){
        	errMessage = "휴대전화본인확인 사용불가 CP코드";
        }else if("0023".equals(sErrorCode)){
        	errMessage = "미등록 CP코드";
        }else if("0031".equals(sErrorCode)){
        	errMessage = "유효한 인증이력 없음";
        }else if("0035".equals(sErrorCode)){
        	errMessage = "기인증완료건(소켓)";
        }else if("0040".equals(sErrorCode)){
        	errMessage = "본인확인차단고객(통신사)";
        }else if("0041".equals(sErrorCode)){
        	errMessage = "인증문자발송차단고객(통신사)";
        }else if("0050".equals(sErrorCode)){
        	errMessage = "NICE 명의보호서비스 이용고객 차단";
        }else if("0052".equals(sErrorCode)){
        	errMessage = "부정사용차단";
        }else if("0070".equals(sErrorCode)){
        	errMessage = "간편인증앱 미설치";
        }else if("0071".equals(sErrorCode)){
        	errMessage = "앱인증 미완료";
        }else if("0072".equals(sErrorCode)){
        	errMessage = "간편인증 처리중 오류";
        }else if("0073".equals(sErrorCode)){
        	errMessage = "간편인증앱 미설치(LG U+ Only)";
        }else if("0074".equals(sErrorCode)){
        	errMessage = "간편인증앱 재설치필요";
        }else if("0075".equals(sErrorCode)){
        	errMessage = "간편인증사용불가-스마트폰아님";
        }else if("0076".equals(sErrorCode)){
        	errMessage = "간편인증앱 미설치";
        }else if("0078".equals(sErrorCode)){
        	errMessage = "14세 미만 인증 오류";
        }else if("0079".equals(sErrorCode)){
        	errMessage = "간편인증 시스템 오류";
        }else if("9097".equals(sErrorCode)){
        	errMessage = "인증번호 3회 불일치";
        }else{
        	errMessage = "기타오류";
        }
    }
    else if( iReturn == -1)
    {
        sMessage = "복호화 시스템 에러입니다.";
    }    
    else if( iReturn == -4)
    {
        sMessage = "복호화 처리오류입니다.";
    }    
    else if( iReturn == -5)
    {
        sMessage = "복호화 해쉬 오류입니다.";
    }    
    else if( iReturn == -6)
    {
        sMessage = "복호화 데이터 오류입니다.";
    }    
    else if( iReturn == -9)
    {
        sMessage = "입력 데이터 오류입니다.";
    }    
    else if( iReturn == -12)
    {
        sMessage = "사이트 패스워드 오류입니다.";
    }    
    else
    {
        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
    }
%>
<%!
	public String requestReplace (String paramValue, String gubun) {
        String result = "";
        if (paramValue != null) {
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");
        	
        	if(gubun != "encodeData"){
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
        	}
        	result = paramValue;
        }

       	return result;
	}
%>

<html>
<head>
<title>NICE평가정보</title>
<script src="<c:url value='/js/jquery-2.1.4.js'/>"></script>

<script language='javascript'>
	$(function(){
		alert("개인 인증에 에러가 발생했습니다.[<%= sErrorCode %>][<%= errMessage %>]");		
	});
</script>      
</head>
<body>

</body>
</html>