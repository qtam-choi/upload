package com.admin.upload.service.impl.common;


import com.admin.upload.service.AbstractHpService;
import com.admin.upload.service.SifResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class CommonActionImpl extends AbstractHpService
{

	private static String siteCode;
	@Value("${nice-priv-auth.siteCode}")
	public void setSiteCode(String str){
		siteCode = str;
	}

	private static String sitePass;
	@Value("${nice-priv-auth.sitePass}")
	public void setSitePass(String str){
		sitePass = str;
	}


	private static String reqNumber;
	@Value("${nice-priv-auth.reqNumber}")
	public void setReqNumber(String str){
		reqNumber = str;
	}


	private static String returnUrl;
	@Value("${nice-priv-auth.returnUrl}")
	public void setReturnUrl(String str){
		returnUrl = str;
	}


	private static String errorUrl;
	@Value("${nice-priv-auth.errorUrl}")
	public void setErrorUrl(String str){
		errorUrl = str;
	}



	//로그인 필요없을시 아래 오버라이드 추가할것, 로그인 필요시 아래 오버라이드 제거
	@Override
	protected boolean checkSession() {
		return false;
	}


	@Override
	public Object serviceCallImpl(Map dataMap)
	{	
		SifResult result = new SifResult();
		String action = (String)dataMap.get("action");

		if(action.equals("FILEDOWN")){

			Map fileInfo = sqlSession.selectOne("ADMIN_COMMON.selFileDown", dataMap);

			if(fileInfo == null){
				result.setResult(false, "데이터가 없습니다.");
				return result.getResultMap();
			}

			result.setResultObject("fileInfo", fileInfo);

		}else if(action.equals("FILE_UPLOAD")) {

			String rst = (String) dataMap.get("result");
			String msg = (String) dataMap.get("msg");

			if (!rst.equals("S")) {
				result.setResult(false, msg);
			} else {
				sqlSession.insert("ADMIN_COMMON.insFileUpload", dataMap);
				result.setResultObject("fileInfo", dataMap);
				result.setResultObject("file_no", dataMap.get("file_no"));
				result.setResultObject("file_name", dataMap.get("file_name"));
			}

		}else if(action.equals("DOC_UPLOAD")) {

			sqlSession.insert("ADMIN_COMMON.insDocUpload", dataMap);

			String dnUrl = "/common/fileDownload.do?no=" + dataMap.get("file_no") + "&filename=" + dataMap.get("file_name");

			result.setResultObject("dnUrl", dnUrl);


		}else if(action.equals("AUTH_PRIVACY")){ //개인인증
			HttpSession session = (HttpSession)dataMap.get("session");
			//본인인증 처리 추가
			NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();



			String sSiteCode = siteCode;		 // NICE로부터 부여받은 사이트 코드
			String sSitePassword = sitePass;	 // NICE로부터 부여받은 사이트 패스워드
			String sRequestNumber = reqNumber;	 // NICE로부터 부여받은 사이트 패스워드

			sRequestNumber = niceCheck.getRequestNO(sSiteCode);
			session.setAttribute("REQ_SEQ" , sRequestNumber);	// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.

			String sAuthType = "M";      	// 없으면 기본 선택화면, M: 핸드폰, C: 신용카드, X: 공인인증서
			String popgubun 	= "N";		//Y : 취소버튼 있음 / N : 취소버튼 없음
			String customize 	= "";		//없으면 기본 웹페이지 / Mobile : 모바일페이지
			String sGender = ""; 			//없으면 기본 선택 값, 0 : 여자, 1 : 남자

			// CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
			//리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
			String sReturnUrl = (String)dataMap.get("host") + returnUrl;   // 성공시 이동될 URL
			String sErrorUrl =  (String)dataMap.get("host") + errorUrl;     // 실패시 이동될 URL

			// 입력될 plain 데이타를 만든다.
			String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
					"8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
					"9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
					"7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
					"7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
					"11:POPUP_GUBUN" + popgubun.getBytes().length + ":" + popgubun +
					"9:CUSTOMIZE" + customize.getBytes().length + ":" + customize +
					"6:GENDER" + sGender.getBytes().length + ":" + sGender;

			String sMessage = "";
			String sEncData = "";
			int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
			if( iReturn == 0 )
			{
				sEncData = niceCheck.getCipherData();
			}
			else if( iReturn == -1)
			{
				sMessage = "암호화 시스템 에러입니다.";
			}
			else if( iReturn == -2)
			{
				sMessage = "암호화 처리오류입니다.";
			}
			else if( iReturn == -3)
			{
				sMessage = "암호화 데이터 오류입니다.";
			}
			else if( iReturn == -9)
			{
				sMessage = "입력 데이터 오류입니다.";
			}
			else
			{
				sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
			}

			if(iReturn == 0){
				result.setResult(true, "true");
				result.setResultObject("auth_ret", iReturn);
				result.setResultObject("auth_enc_data", sEncData);
			}else{
				result.setResult(false, sMessage);
				result.setResultObject("auth_ret", iReturn);
				return result.getResultMap();
			}

		}
		
		return result.getResultMap();
	}
	
}
