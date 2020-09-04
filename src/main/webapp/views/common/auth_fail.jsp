<%@ page language="java" contentType="text/html;charset=euc-kr" import="com.admin.upload.common.util.CommonUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

    String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");

    String sCipherTime = "";	// ��ȣȭ�� �ð�
    String sErrorCode = "";		// ���� ����ڵ�
    String sAuthType = "";		// ���� ����
    String sMessage = "";
    String sPlainData = "";
    String errMessage = "";
    String sSiteCode = CommonUtil.getProperty("siteCode");		// NICE�κ��� �ο����� ����Ʈ �ڵ�
    String sSitePassword = CommonUtil.getProperty("sitePass");	// NICE�κ��� �ο����� ����Ʈ �н�����
    String sRequestNumber = CommonUtil.getProperty("reqNumber");	// ��û ��ȣ

    int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);
    if( iReturn == 0 )
    {
        sPlainData = niceCheck.getPlainData();
        sCipherTime = niceCheck.getCipherDateTime();
        
        // ����Ÿ�� �����մϴ�.
        java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
        sRequestNumber 	= (String)mapresult.get("REQ_SEQ");
        sErrorCode 		= (String)mapresult.get("ERR_CODE");
        sAuthType 		= (String)mapresult.get("AUTH_TYPE");
        
        if("0001".equals(sErrorCode)){
        	errMessage = "���� ����ġ";
        }else if("0003".equals(sErrorCode)){
        	errMessage = "��Ÿ��������";
        }else if("0010".equals(sErrorCode)){
        	errMessage = "������ȣ ����ġ(����)";
        }else if("0012".equals(sErrorCode)){
        	errMessage = "��û��������(�Է°�����)";
        }else if("0013".equals(sErrorCode)){
        	errMessage = "��ȣȭ �ý��� ����";
        }else if("0014".equals(sErrorCode)){
        	errMessage = "��ȣȭ ó�� ����";
        }else if("0015".equals(sErrorCode)){
        	errMessage = "��ȣȭ ������ ����";
        }else if("0016".equals(sErrorCode)){
        	errMessage = "��ȣȭ ó�� ����";
        }else if("0017".equals(sErrorCode)){
        	errMessage = "��ȣȭ ������ ����";
        }else if("0018".equals(sErrorCode)){
        	errMessage = "��ſ���";
        }else if("0019".equals(sErrorCode)){
        	errMessage = "�����ͺ��̽� ����";
        }else if("0020".equals(sErrorCode)){
        	errMessage = "��ȿ�������� CP�ڵ�";
        }else if("0021".equals(sErrorCode)){
        	errMessage = "�ߴܵ� CP�ڵ�";
        }else if("0022".equals(sErrorCode)){
        	errMessage = "�޴���ȭ����Ȯ�� ���Ұ� CP�ڵ�";
        }else if("0023".equals(sErrorCode)){
        	errMessage = "�̵�� CP�ڵ�";
        }else if("0031".equals(sErrorCode)){
        	errMessage = "��ȿ�� �����̷� ����";
        }else if("0035".equals(sErrorCode)){
        	errMessage = "�������Ϸ��(����)";
        }else if("0040".equals(sErrorCode)){
        	errMessage = "����Ȯ�����ܰ�(��Ż�)";
        }else if("0041".equals(sErrorCode)){
        	errMessage = "�������ڹ߼����ܰ�(��Ż�)";
        }else if("0050".equals(sErrorCode)){
        	errMessage = "NICE ���Ǻ�ȣ���� �̿�� ����";
        }else if("0052".equals(sErrorCode)){
        	errMessage = "�����������";
        }else if("0070".equals(sErrorCode)){
        	errMessage = "���������� �̼�ġ";
        }else if("0071".equals(sErrorCode)){
        	errMessage = "������ �̿Ϸ�";
        }else if("0072".equals(sErrorCode)){
        	errMessage = "�������� ó���� ����";
        }else if("0073".equals(sErrorCode)){
        	errMessage = "���������� �̼�ġ(LG U+ Only)";
        }else if("0074".equals(sErrorCode)){
        	errMessage = "���������� �缳ġ�ʿ�";
        }else if("0075".equals(sErrorCode)){
        	errMessage = "�����������Ұ�-����Ʈ���ƴ�";
        }else if("0076".equals(sErrorCode)){
        	errMessage = "���������� �̼�ġ";
        }else if("0078".equals(sErrorCode)){
        	errMessage = "14�� �̸� ���� ����";
        }else if("0079".equals(sErrorCode)){
        	errMessage = "�������� �ý��� ����";
        }else if("9097".equals(sErrorCode)){
        	errMessage = "������ȣ 3ȸ ����ġ";
        }else{
        	errMessage = "��Ÿ����";
        }
    }
    else if( iReturn == -1)
    {
        sMessage = "��ȣȭ �ý��� �����Դϴ�.";
    }    
    else if( iReturn == -4)
    {
        sMessage = "��ȣȭ ó�������Դϴ�.";
    }    
    else if( iReturn == -5)
    {
        sMessage = "��ȣȭ �ؽ� �����Դϴ�.";
    }    
    else if( iReturn == -6)
    {
        sMessage = "��ȣȭ ������ �����Դϴ�.";
    }    
    else if( iReturn == -9)
    {
        sMessage = "�Է� ������ �����Դϴ�.";
    }    
    else if( iReturn == -12)
    {
        sMessage = "����Ʈ �н����� �����Դϴ�.";
    }    
    else
    {
        sMessage = "�˼� ���� ���� �Դϴ�. iReturn : " + iReturn;
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
<title>NICE������</title>
<script src="<c:url value='/js/jquery-2.1.4.js'/>"></script>

<script language='javascript'>
	$(function(){
		alert("���� ������ ������ �߻��߽��ϴ�.[<%= sErrorCode %>][<%= errMessage %>]");		
	});
</script>      
</head>
<body>

</body>
</html>