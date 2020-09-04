<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${header['Referer'] eq null || fn:contains(header['Referer'], 'olivar') || fn:contains(header['Referer'], 'olv')}">

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes" />
<title>주소가져오기</title>


<script src="<c:url value='/js/jquery-2.1.4.js'/>"></script>
<script src="<c:url value='/js/jquery-ui.js'/>"></script>
<script src="<c:url value='/js/jquery.blockUI.js'/>"></script>
<script src="<c:url value='/js/commonUtil.js'/>"></script>

<script type="text/javascript">

$(document).ready(function() {
	execDaumPostcode();
});


</script>

</head>
<body>
<div id="wrap">
 
</div>


</body>
</html>



<style>
.even_popup{background-color:#fff;}
.even_popup .pbox_01{min-height:250px;}
.even_popup .title{position:relative; text-align:center; height:47px; line-height:47px; border-bottom:1px solid #888;}
.even_popup .title strong{font-size:1.388em;}
.even_popup .btn_close{display:block; position:absolute; top:14px; right:10px; width:17px; height:17px;}
.even_popup .btn_close img{width:17px; height:17px;vertical-align: top;}
 #layer #__daum__layer_1 {-webkit-overflow-scrolling: touch;}
 #layer #__daum__layer_2 {-webkit-overflow-scrolling: touch;}
</style>

<div id="layer" class="even_popup" style="left: 0px; top: 0px; display: none; height: 80%; width: 80%; position: fixed; background-color:#fff;  z-index:999999;" >
	<p class="title"><strong><!-- 타이틀 -->우편번호</strong><a href="javascript:closeDaumPostcode();" class="btn_close"><img src="/resource/images/btn_layer_close.png" alt="팝업 닫기"></a></p>
</div>

<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<script>
$(document).ready(function() {
	$('.even_popup').css('height', $(window).height());
	$(window).resize(function() {
		$('.even_popup').css('height', $(window).height());
	});
});
</script>

<script>
    // 우편번호 찾기 화면을 넣을 element
    var element_layer = document.getElementById('layer');

    function closeDaumPostcode() {
    	window.SmartChangeApp.close();
    	
    	
//     	$('body').css({overflow:'auto'});
//         // iframe을 넣은 element를 안보이게 한다.
//         element_layer.style.display = 'none';
    }

    function execDaumPostcode() {
    	$('body').css({overflow:'hidden'});
    	
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
					
					window.SmartChangeApp.selectAddr(data.zonecode,fullAddr);
//                 document.getElementById('post_no').value = data.zonecode; //5자리 새우편번호 사용
//                 document.getElementById('addr01').value = fullAddr;
//                 //document.getElementById('sample2_addressEnglish').value = data.addressEnglish;

//                 // iframe을 넣은 element를 안보이게 한다.
//                 // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
//                 element_layer.style.display = 'none';
//                 $('body').css({overflow:'auto'});
           	 	
            },
            width : '100%',
            height : '100%'
        }).embed(element_layer, {});

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
    }

    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
    function initLayerPosition(){
        var width = 300; //우편번호서비스가 들어갈 element의 width
        var height = 460; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 1; //샘플에서 사용하는 border의 두께
        
        
        
        width =  parseInt($(window).width());
        height = parseInt($(window).height());


        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        // element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        // element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        //element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    }
</script>



</c:if>
