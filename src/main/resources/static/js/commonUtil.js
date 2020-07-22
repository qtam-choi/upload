/*--------------------------------------------------
// 이메일 형식 체크
----------------------------------------------------*/
function CheckEmail(strEmail)
{
	var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return regEmail.test(strEmail);
}


/*--------------------------------------------------
// form input => json object
----------------------------------------------------*/
function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function getMultiFormData($form){
	
	var formData = new FormData();
	
    var unindexed_array = $form.serializeArray();
   
    $.map(unindexed_array, function(n, i){
    	formData.append(n['name'],  n['value'] );
    });
    
    return formData;
}




/*--------------------------------------------------
//form input => param
----------------------------------------------------*/

function getFormParam($form){
    var unindexed_array = $form.serializeArray();
    var param = "";

    $.map(unindexed_array, function(n, i){
    	
    	param += n['name'] + "=" + n['value'] +"&";
    });

    return param;
}


/*--------------------------------------------------
오늘 날짜 
----------------------------------------------------*/
function getTimeStamp() {
  var d = new Date();

  var s =
      leadingZeros(d.getFullYear(), 4) + '-' +
      leadingZeros(d.getMonth() + 1, 2) + '-' +
      leadingZeros(d.getDate(), 2);

  return s;
}

function leadingZeros(n, digits) {
  var zero = '';
  n = n.toString();

  if (n.length < digits) {
      for (i = 0; i < digits - n.length; i++)
          zero += '0';
  }
  return zero + n;
}


/*--------------------------------------------------
일수 더하기 
----------------------------------------------------*/

function date_add(sDate, nDays) {
    var yy = parseInt(sDate.substr(0, 4), 10);
    var mm = parseInt(sDate.substr(5, 2), 10);
    var dd = parseInt(sDate.substr(8), 10);
 
    d = new Date(yy, mm - 1, dd + nDays);
 
    yy = d.getFullYear();
    mm = d.getMonth() + 1; mm = (mm < 10) ? '0' + mm : mm;
    dd = d.getDate(); dd = (dd < 10) ? '0' + dd : dd;
 
    return '' + yy + '-' +  mm  + '-' + dd;
}


/*--------------------------------------------------
     월수 더하기 
----------------------------------------------------*/
function date_add_month(sDate, nMonth) {

	var rtnValue = -1;

	var yyyy = parseInt(sDate.substr(0, 4), 10);
	var mm = parseInt(sDate.substr(5, 2), 10);
	var dd = sDate.substr(8);

	var newMm = null;
	// 월수를 더하여 1년이 넘는 경우
	if ((eval(mm) + eval(nMonth)) > 12) {
		yyyy = eval(yyyy) + 1;
		newMm = eval(mm) + eval(nMonth) - 12;
	}else if((eval(mm) + eval(nMonth)) < 1) {
		yyyy = eval(yyyy) - 1;
		newMm = eval(mm) + eval(nMonth) + 12;
	}else {
		newMm = eval(mm) + eval(nMonth);
	}
	// 윤년 처리
	var isYoonYear = false;
	// 4 로 나누어 떨어지면 윤년
	// 100 으로 나누어 떨어지면 윤년 아님
	// 400 으로 나누어 떨어지면 윤년
	if ((eval(yyyy) % 4) == 0)
		isYoonYear = true;
	if ((eval(yyyy) % 100) == 0)
		isYoonYear = false;
	if ((eval(yyyy) % 400) == 0)
		isYoonYear = true;
	// 윤년인 경우
	if (isYoonYear) {
		if ((newMm == '02') && (dd == '30' || dd == '31'))
			dd = '29';
		// 평년인 경우
	} else {
		if ((newMm == '02') && (dd == '29' || dd == '30' || dd == '31'))
			dd = '28';
	}
	// 월의 자리수를 맞춘다. ( 2 월 -> 02 )
	if (eval(newMm) < 10) {
		newMm = "0" + newMm
	}

	rtnValue = yyyy + '-' + newMm + '-' + dd;
	return rtnValue;
}


/*--------------------------------------------------
     콤마 관련
----------------------------------------------------*/
//콤마찍기
function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

//콤마풀기
function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}


/*--------------------------------------------------
     전화번호 유효성 체크 
----------------------------------------------------*/
function OnCheckPhone(oTa) { 
    var oForm = oTa.form ; 
    var sMsg = oTa.value ; 
    var onlynum = "" ; 
    var imsi=0; 
    onlynum = RemoveDash2(sMsg);  //하이픈 입력시 자동으로 삭제함 
    onlynum =  checkDigit(onlynum);  // 숫자만 입력받게 함 
    var retValue = ""; 

    if(event.keyCode != 12 ) { 
        if(onlynum.substring(0,2) == 02) {  // 서울전화번호일 경우  10자리까지만 나타나교 그 이상의 자리수는 자동삭제 
                if (GetMsgLen(onlynum) <= 1) oTa.value = onlynum ; 
                if (GetMsgLen(onlynum) == 2) oTa.value = onlynum + "-"; 
                if (GetMsgLen(onlynum) == 4) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,3) ; 
                if (GetMsgLen(onlynum) == 4) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,4) ; 
                if (GetMsgLen(onlynum) == 5) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) ; 
                if (GetMsgLen(onlynum) == 6) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,6) ; 
                if (GetMsgLen(onlynum) == 7) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-" + onlynum.substring(5,7) ; ; 
                if (GetMsgLen(onlynum) == 8) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,6) + "-" + onlynum.substring(6,8) ; 
                if (GetMsgLen(onlynum) == 9) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-" + onlynum.substring(5,9) ; 
                if (GetMsgLen(onlynum) == 10) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,6) + "-" + onlynum.substring(6,10) ; 
                if (GetMsgLen(onlynum) == 11) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,6) + "-" + onlynum.substring(6,10) ; 
                if (GetMsgLen(onlynum) == 12) oTa.value = onlynum.substring(0,2) + "-" + onlynum.substring(2,6) + "-" + onlynum.substring(6,10) ; 
        } 
        if(onlynum.substring(0,2) == 05 ) {  // 05로 시작되는 번호 체크 
            if(onlynum.substring(2,3) == 0 ) {  // 050으로 시작되는지 따지기 위한 조건문 
                    if (GetMsgLen(onlynum) <= 3) oTa.value = onlynum ; 
                    if (GetMsgLen(onlynum) == 4) oTa.value = onlynum + "-"; 
                    if (GetMsgLen(onlynum) == 5) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,5) ; 
                    if (GetMsgLen(onlynum) == 6) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,6) ; 
                    if (GetMsgLen(onlynum) == 7) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,7) ; 
                    if (GetMsgLen(onlynum) == 8) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) ; 
                    if (GetMsgLen(onlynum) == 9) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,7) + "-" + onlynum.substring(7,9) ; ; 
                    if (GetMsgLen(onlynum) == 10) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) + "-" + onlynum.substring(8,10) ; 
                    if (GetMsgLen(onlynum) == 11) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,7) + "-" + onlynum.substring(7,11) ; 
                    if (GetMsgLen(onlynum) == 12) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) + "-" + onlynum.substring(8,12) ; 
                    if (GetMsgLen(onlynum) == 13) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) + "-" + onlynum.substring(8,12) ; 
          } else { 
                if (GetMsgLen(onlynum) <= 2) oTa.value = onlynum ; 
                if (GetMsgLen(onlynum) == 3) oTa.value = onlynum + "-"; 
                if (GetMsgLen(onlynum) == 4) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,4) ; 
                if (GetMsgLen(onlynum) == 5) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,5) ; 
                if (GetMsgLen(onlynum) == 6) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) ; 
                if (GetMsgLen(onlynum) == 7) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) ; 
                if (GetMsgLen(onlynum) == 8) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,8) ; ; 
                if (GetMsgLen(onlynum) == 9) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,9) ; 
                if (GetMsgLen(onlynum) == 10) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,10) ; 
                if (GetMsgLen(onlynum) == 11) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11) ; 
                if (GetMsgLen(onlynum) == 12) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11) ; 
          } 
        } 

        if(onlynum.substring(0,2) == 03 || onlynum.substring(0,2) == 04  || onlynum.substring(0,2) == 06  || onlynum.substring(0,2) == 07  || onlynum.substring(0,2) == 08 ) {  // 서울전화번호가 아닌 번호일 경우(070,080포함 // 050번호가 문제군요) 
                if (GetMsgLen(onlynum) <= 2) oTa.value = onlynum ; 
                if (GetMsgLen(onlynum) == 3) oTa.value = onlynum + "-"; 
                if (GetMsgLen(onlynum) == 4) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,4) ; 
                if (GetMsgLen(onlynum) == 5) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,5) ; 
                if (GetMsgLen(onlynum) == 6) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) ; 
                if (GetMsgLen(onlynum) == 7) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) ; 
                if (GetMsgLen(onlynum) == 8) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,8) ; ; 
                if (GetMsgLen(onlynum) == 9) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,9) ; 
                if (GetMsgLen(onlynum) == 10) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,10) ; 
                if (GetMsgLen(onlynum) == 11) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11) ; 
                if (GetMsgLen(onlynum) == 12) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11) ; 

        } 
        if(onlynum.substring(0,2) == 01) {  //휴대폰일 경우 
            if (GetMsgLen(onlynum) <= 2) oTa.value = onlynum ; 
            if (GetMsgLen(onlynum) == 3) oTa.value = onlynum + "-"; 
            if (GetMsgLen(onlynum) == 4) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,4) ; 
            if (GetMsgLen(onlynum) == 5) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,5) ; 
            if (GetMsgLen(onlynum) == 6) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) ; 
            if (GetMsgLen(onlynum) == 7) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) ; 
            if (GetMsgLen(onlynum) == 8) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,8) ; 
            if (GetMsgLen(onlynum) == 9) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,9) ; 
            if (GetMsgLen(onlynum) == 10) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,10) ; 
            if (GetMsgLen(onlynum) == 11) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11) ; 
            if (GetMsgLen(onlynum) == 12) oTa.value = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11) ; 
        } 

        if(onlynum.substring(0,1) == 1) {  // 1588, 1688등의 번호일 경우 
            if (GetMsgLen(onlynum) <= 3) oTa.value = onlynum ; 
            if (GetMsgLen(onlynum) == 4) oTa.value = onlynum + "-"; 
            if (GetMsgLen(onlynum) == 5) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,5) ; 
            if (GetMsgLen(onlynum) == 6) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,6) ; 
            if (GetMsgLen(onlynum) == 7) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,7) ; 
            if (GetMsgLen(onlynum) == 8) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) ; 
            if (GetMsgLen(onlynum) == 9) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) ; 
            if (GetMsgLen(onlynum) == 10) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) ; 
            if (GetMsgLen(onlynum) == 11) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) ; 
            if (GetMsgLen(onlynum) == 12) oTa.value = onlynum.substring(0,4) + "-" + onlynum.substring(4,8) ; 
        } 
    } 
} 

function RemoveDash2(sNo) { 
var reNo = "" 
for(var i=0; i<sNo.length; i++) { 
    if ( sNo.charAt(i) != "-" ) { 
    reNo += sNo.charAt(i) 
    } 
} 
return reNo 
} 

function GetMsgLen(sMsg) { // 0-127 1byte, 128~ 2byte 
var count = 0 
    for(var i=0; i<sMsg.length; i++) { 
        if ( sMsg.charCodeAt(i) > 127 ) { 
            count += 2 
        } 
        else { 
            count++ 
        } 
    } 
return count 
} 

function checkDigit(num) { 
    var Digit = "1234567890"; 
    var string = num; 
    var len = string.length 
    var retVal = ""; 

    for (i = 0; i < len; i++) 
    { 
        if (Digit.indexOf(string.substring(i, i+1)) >= 0) 
        { 
            retVal = retVal + string.substring(i, i+1); 
        } 
    } 
    return retVal; 
} 


/*--------------------------------------------------
태그 제거
----------------------------------------------------*/
function removeTag( html ) {
    return html.replace(/(<([^>]+)>)/gi, "");
}


