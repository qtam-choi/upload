package com.admin.upload.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHpService implements HpService {
	
	
	@Autowired
    protected SqlSessionTemplate sqlSession;
	
	@Autowired
    protected SqlSessionTemplate sqlSessionUp;
	
	@Autowired
	protected DataSourceTransactionManager transactionManager;
	
	@Autowired
	protected DataSourceTransactionManager transactionManagerUp;
	
    protected HashMap userMap;
	
	
	/**
	 * 세션체크가 필요한 페이지 인지 지정
	 * 기본으로 세션을 체크하도록 하고, 하지 말아야할 페이지(로그인, 공지사항 등)는 override 하여 return false;
	 * @return 
	 */
	protected boolean checkSession(){
		return true;
	}
	
	public Object serviceCall(Map dataMap) {
		
		Object rtObj = null;
		try {
			

			if(checkSession()){   //로그인 TRUE 
				//현재 요청중인 thread local의 HttpServletRequest 객체 가져오기
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();					
				//HttpSession 객체 가져오기
				HttpSession session = request.getSession();
				userMap = (HashMap) session.getAttribute("USER_INFO");
				
				 if (userMap == null || userMap.isEmpty()) {  
					Map retrunMap = new HashMap() {{put("resultCode", "noSession");}};
					return retrunMap;
				 }
				 
				 dataMap.put("user_id", userMap.get("user_id"));
				 dataMap.put("user_nm", userMap.get("user_nm"));
				 dataMap.put("level", userMap.get("level"));
				
			}
			
			rtObj = serviceCallImpl(dataMap);
		} catch (IOException e) {
			System.out.println("타임아웃");
			e.printStackTrace();
		} catch (ClassCastException e) {
			System.out.println("CAST 에러");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("SERVICE 에러");
			e.printStackTrace();
		} catch (UncategorizedSQLException e) {
			System.out.println("DB 에러");
			e.printStackTrace();
		} 
		
		return rtObj;
		
	}
	
	public abstract Object serviceCallImpl(Map dataMap) throws IOException;

}
