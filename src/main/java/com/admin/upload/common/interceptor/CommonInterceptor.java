package com.admin.upload.common.interceptor;

import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 로그인 세션체크 예외 주소 처리  preHandle  //controller 이전 처리 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String requestURI = request.getRequestURI();


		HttpSession session = request.getSession();
		HashMap userMap = (HashMap) session.getAttribute("USER_INFO");

		if (userMap != null && !userMap.isEmpty()) {  //admin 로그인 했고,

			if (requestURI.indexOf("/login.do") > -1) {  //admin 로그인화면이면
				request.getRequestDispatcher("/main.do").forward(request, response);
				return false;

			} else {

				boolean check = true;

				String auth = (String) userMap.get("auth");

				if (!auth.equals("SUPER") && requestURI.indexOf("/menu03/") > -1) {
					check = false;
				}

				if (auth.equals("SILVER") && requestURI.indexOf("/menu02/") > -1) {
					check = false;
				}


				if (check == false) {
					// 정상적인 세션정보가 없으면 로그인페이지로 이동
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('접근권한이 없습니다.');history.back();</script>");
					out.flush();
					out.close();

					return false;
				}

			}

		}

		return true;

	}
	
	
	/**
	 * 로그인 세션체크 예외 주소 처리  preHandle  // 컨트롤러가 요청을 처리한 뒤에 호출된다.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		/** request 로 넘어온 데이타를 가공할때 쓰는 곳이다 */
		
		 if(modelAndView != null)
		 {
			 Map<String, Object> p = modelAndView.getModel();
			 String noSession = (String)p.get("resultCode");

			 if(noSession != null && noSession.equals("noSession"))   //로그인이 필요한데 세션이 없을경우
			 {

				 // 정상적인 세션정보가 없으면 로그인페이지로 이동
				 if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {  // ajax

				 	 JSONObject json = new JSONObject();
					 json.put("RETURN_CODE", "E");
					 json.put("RETURN_MSG", "세션이 종료되었습니다.");
					 json.put("LOCATION", request.getContextPath()+"/login.do");

					 response.setContentType("text/html; charset=UTF-8");
					 PrintWriter out = response.getWriter();
					 out.print(json);
					 out.flush();
					 out.close();


					 // Handle ajax response (e.g. return JSON data object).
				 } else { //request

					 // 정상적인 세션정보가 없으면 로그인페이지로 이동
					 response.setContentType("text/html; charset=UTF-8");
					 PrintWriter out = response.getWriter();
					 out.println("<script>alert('세션이 종료되었습니다.');location.href='"+request.getContextPath()+"/login.do';</script>");
					 out.flush();
					 out.close();
				 }

				//request.getRequestDispatcher("/login.do").forward(request, response);
		     }

		 }

	}
	 
	 
	 @Override   // 클라이언트의 요청을 처리한 뒤, 즉 뷰를 통해서 클라이언트에 응답을 전송한 뒤에 실행
	 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		 super.afterCompletion(request, response, handler, ex);
		 
		 /** 공통 Exception 처리를 해주는것이 좋다. 에러 메일을 보내는것등 */

	  }
	
	
	
	
	
	

}
