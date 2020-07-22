<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="format-detection" content="telephone=no">
    <title>DownLoad TEST</title>
    <script src="<c:url value='/js/jquery-1.12.4.min.js'/>"></script>
    <script class="include" src="<c:url value='/js/jquery-ui.js'/>"></script>
    <script class="include" src="<c:url value='/js/jquery.blockUI.js'/>"></script>
    <script class="include" src="<c:url value='/js/commonUtil.js'/>"></script>

    <script type="text/javascript">

    function goAction(){

        //multForm으로 변환
        var formData = getMultiFormData($("#form1"));
        //formData.append("action", act);

        formData.append("files1",$('input[name=file_no]')[0].files[0]);

        $.ajax({
            type : "POST"
            , async : true
            , url : "CommMultiAction.do"
            , processData: false
            , contentType: false
            , dataType : "json"
            , data : formData
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
                    alert('파일이 업로드 되었습니다.');
                }
            }
        });
    }

	
	
	
	

</script>

</head>
<body>
        <div id="Wrap">
            <table>
                <tr>
                    <th  class="thCell" >파일</th>
                    <td  class="tdCell" >
                        <input type="file"  id="file_no"  name="file_no" >
                    </td>
                    <td>
                        <a href="javascript:goAction()">업로드</a>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
		