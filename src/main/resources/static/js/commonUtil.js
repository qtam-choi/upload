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


