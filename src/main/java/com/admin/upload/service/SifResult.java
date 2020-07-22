package com.admin.upload.service;

import java.util.HashMap;
import java.util.Map;

/**  
 * @ClassName : SifResult.java
 * @Description : Interface Result Class
 * @Author  모바일개발팀
 * @Since 2013. 3. 19.
 * @Version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일             수정자                수정내용
 *  --------------        ---------------       ---------------------------
 *   2013. 3. 19.        모바일개발팀            최초작성
 *
 * </pre>
 *  
 * Copyright (C) by SMC All right reserved.
 */
public class SifResult {

	final private Map<String, Object> cont = new HashMap<String, Object>();

	/**
	 * 생성자. 기본 값을 성공으로 Set...
	 */
	public SifResult(){
		cont.put("RETURN_CODE", "S");
		cont.put("RETURN_MSG", "SUCCESS");
	}
	
	/**
	 * 인터페이스 호출 결과 Message set
	 * @param b 호출 결과 true / false
	 * @param message 결과 Message
	 */
	public void setResult(boolean b, String message ){
		cont.put("RETURN_CODE", b ? "S":"E");
		cont.put("RETURN_MSG", message);
	}
	
	/**
	 * 조회 등 Client 로 전달할 Data 가 있는경우 호출
	 * @param key 데이터 ID
	 * @param obj Object
	 */
	public void setResultObject(String key, Object obj){
		cont.put(key, obj);
	}
	
	/**
	 * 현재의 성공 여부 값을 조회
	 * @return true / false
	 */
	public boolean getCurrentSatus(){
		return cont.get("RETURN_CODE").equals("S") ? true : false;
	}
	
	
	/**
	 * 결과 Map
	 * @return 결과 Map
	 */
	public Map<String, Object> getResultMap(){
		return cont;
	}
}
