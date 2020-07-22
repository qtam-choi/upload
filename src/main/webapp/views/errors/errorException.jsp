<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!doctype html>
<html lang="ko">
<head>
<meta name="generator" content="ONEWARE">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, width=device-width">
<meta name="format-detection" content="telephone=no">
<meta name="robots" content="noindex, nofollow" />
<title>시스템 관리자</title>
<style>

.mError1{position:absolute;top:50%;left:0;width:100%;margin-top:-214px;text-align:center;}
.mError1 h1{font-size:50px;line-height:130%;padding:120px 0 0 0;}
.mError1 .txt{font-size:20px;line-height:170%;margin:25px 0 0;}

.mButton1{margin-top:60px;text-align:center;}
.mButton1 a{margin:0 4px;}

.mBtn1{display:inline-block;background-color:#031257;border:1px solid #031257;color:#fff;font-size:17px;padding:0 10px;text-align:center;min-width:100px;line-height:68px;height:68px;}
.mBtn1.black{background-color:#000;}
.mBtn1.white{background-color:#fff;border-color:#eaeaea;color:#000;}
.mBtn1.s{font-size:13px;min-width:auto;line-height:25px;height:25px;}
.mBtn2{display:inline-block;background-color:#031257;border:1px solid #031257;color:#fff;font-size:20px;padding:0 10px;text-align:center;border-radius:30px;min-width:160px;line-height:60px;height:60px;}
.mBtn2.white{background-color:#fff;border-color:#eaeaea;color:#000;}
.mBtn2 .iDown{display:inline-block;background:url(../images/ico_down1.png) 100% 50% no-repeat;padding-right:30px;}



/* 1200 */
@media  (max-width:1250px){}
/* 1200 */

/* 1150px */
@media  (max-width:1150px){}
/* 1150px */

/* 950px */
@media  (max-width:950px){ }
/* //950px */

/* mobile */
@media (min-width:0) and (max-width:768px){
	body,
	input, button,textarea{font-family: 'NanumGothic';}
	html,
	body,
	#wrap{min-height:100%;}
	.br{display:inline;}
	.brForMobile{display:block;}
	.forMobile{display:block;}		
	.forWeb{display:none;}
	.forMobile{display:block;}
			
	/* addNew-m2 */
	.gPag .gRt{text-align:center;}
	.mError1{margin-top:-150px;}
	.mError1 h1{background-size:auto 32px;font-size:25px;padding:50px 10px 0;}
	.mError1 .txt{font-size:14px;margin-top:15px;padding:0 10px;}

}


</style>
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

</head>
<body>

<!-- error -->
<div class="mError1">
	<h1>서비스에 접속할 수 없습니다.</h1>
	<p class="txt">
		모바일통지 오픈API 관리자 페이지입니다. <br>
		이용에 문제가 있으면 관리자에게 문의바랍니다.
	</p>
	<div class="mButton1">
		<a href="<c:url value='/main.do'/>" class="mBtn2">홈으로</a>
	</div>
</div>
<!-- //error -->

</body>
</html>
