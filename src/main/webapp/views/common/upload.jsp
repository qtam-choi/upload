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
    <title>파일 업로드</title>

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/reset.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/list.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css?v=0.2'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css?v=0.2'/>">

    <script src="<c:url value='/js/jquery-2.1.4.min.js'/>"></script>
    <script src="<c:url value='/js/jquery-ui.js'/>"></script>
    <script src="<c:url value='/js/jquery.blockUI.js'/>"></script>
    <script src="<c:url value='/js/commonUtil.js'/>"></script>

    <script type="text/javascript">

    function goAction(act){


        if(!$('#doc_title').val()){
            alert('파일 제목을 입력하세요.');
            $('#doc_title').focus();
            return;
        }

        if(!$('#file_no').val()){
            alert('파일을 첨부 하세요');
            return;
        }


        //multForm으로 변환
        var formData = getMultiFormData($("#form1"));
        formData.append("action", act);

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
                    $("._up_end").show();
                    $("._upload_btn").attr("onclick", "#");
                    $("._download").attr("href", data.dnUrl);
                }
            }
        });
    }


</script>

</head>


<body>

    <div id="wrap" class="wrap">
        <div class="skip_nav">
            <a href="#" class="go_content">본문 바로가기</a>
        </div>
        <header class="header">
            <div class="logo"><a href="#"><img src="<c:url value='/images/logo.png'/>" alt="KT 모바일통지 CI"></a></div>
        </header>

        <div id="content" class="content full login_content">
            <form name="form1" id="form1" method="POST" action="<c:out value='${requestScope["javax.servlet.forward.request_uri"]}'/>"  >


                <!-- 결과 수신 메시지  -->


                <div class="form_wrap">
                    <h2 class="tit">오픈API 배포 서비스</h2>


                    <div class="login_box">
                        <label class="login_tit">파일제목</label>
                        <span class="input_wrap" id="test02">
                            <input type="text" id="doc_title" name="doc_title" maxlength="100" placeholder="파일제목을 입력하세요" title="기관명" value="" onSubmit="return false;">
                            </span>
                    </div>

                    <div class="login_box">
                        <label class="login_tit">파일선택</label>
                        <div class="input_wrap"><input type="file" id="file_no"  name="file_no" ></div>
                    </div>

                    <button type="button" class="btn_login _upload_btn" onclick="goAction('DOC_UPLOAD')">파일 업로드</button>

                    <div class="type _up_end" style="display:none;">
                        <div class="sns">
                            <a href="" class="login_certify _download"><span>다운로드</span></a>
                            <a href="javascript:location.reload();" class="login_none btn_pop" ><span>다시 업로드</span></a>
                        </div>
                    </div>

                </div>


            </form>
        </div>
    </div>

</body>

</html>
		