package com.admin.upload.controller;

import com.admin.upload.common.util.CommonUtil;
import com.admin.upload.common.util.FileDownload;
import com.admin.upload.common.util.FileUpload;
import com.admin.upload.service.impl.common.CommonActionImpl;
import com.admin.upload.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommonController {

	@Autowired
	CommonActionImpl commonActionImpl;

	// 파일 다운로드
	@RequestMapping("/common/fileDownload.do")
	public ModelAndView fileDownload(HttpServletRequest request,
									  ModelMap model, HttpServletResponse response) throws IOException {

		ModelAndView modelAndView = new ModelAndView(new FileDownload());

		Map paramMap = new HashMap();

		Enumeration req = request.getParameterNames();

		while(req.hasMoreElements()){
			String key = req.nextElement().toString();
			String value = request.getParameter(key);

			paramMap.put(key, value);
		}
		paramMap.put("action", "FILEDOWN");

		Map resultMap = (Map)commonActionImpl.serviceCall(paramMap);

		modelAndView.addAllObjects(resultMap);

		return modelAndView;
	}

	//File Upload
	@RequestMapping("/common/CommMultiAction.do")
	public ModelAndView CommMultiAction(MultipartHttpServletRequest request,
											ModelMap model, HttpServletResponse response) throws IOException {

		ModelAndView modelAndView = new ModelAndView("jsonView");

		Map paramMap = new HashMap();

		Enumeration req = request.getParameterNames();

		while(req.hasMoreElements()){
			String key = req.nextElement().toString();
			String value = request.getParameter(key);

			paramMap.put(key, value);
		}

		/*파일 첨부 START */
		MultipartFile files1 = request.getFile("files1");

		//파일업로드
		String client = "doc";
		String uploadDir =  DateUtil.getToday("yyyyMM");


		String originalFileName = files1.getOriginalFilename();
		String onlyFileName = originalFileName.substring(0, originalFileName.indexOf("."));
		String extension = originalFileName.substring(originalFileName.indexOf(".")+1);

		FileUpload fileUpload = new FileUpload(request.getFile("files1"), client, uploadDir, client + CommonUtil.crefileName() + "1."+extension);

		Map fileMap = fileUpload.getFileInfo();

		fileMap.put("action", "FILE_UPLOAD");
		fileMap.put("file_seq", 1);


		Map resultMap = (Map)commonActionImpl.serviceCall(fileMap);

		if(resultMap.get("RETURN_CODE").equals("E")){
			modelAndView.addAllObjects(resultMap);
			return modelAndView;

		}else{
			paramMap.put("file_no", resultMap.get("file_no"));
			paramMap.put("file_name", resultMap.get("file_name"));
		}

		/*파일 첨부 ENDED */

		paramMap.put("clientIp", request.getRemoteAddr());

		resultMap = (Map)commonActionImpl.serviceCall(paramMap);

		modelAndView.addAllObjects(resultMap);

		return modelAndView;
	}



	@RequestMapping("/common/commonAction.do")
	public ModelAndView comPubAction(HttpServletRequest request,
									 ModelMap model, HttpServletResponse response) throws IOException {

		ModelAndView modelAndView = new ModelAndView("jsonView");

		Map paramMap = new HashMap();

		Enumeration req = request.getParameterNames();

		while(req.hasMoreElements()){
			String key = req.nextElement().toString();
			String value = request.getParameter(key);

			paramMap.put(key, value);
		}

		HttpSession session = request.getSession();
		paramMap.put("session", session);
		paramMap.put("clientIp", request.getRemoteAddr());
		paramMap.put("host" , request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort());

		Map resultMap = (Map)commonActionImpl.serviceCall(paramMap);

		modelAndView.addAllObjects(resultMap);

		return modelAndView;
	}



	//공통메뉴
	@RequestMapping("/{menu1}/{menu2}.do")
	public String page(@PathVariable String menu1, @PathVariable String menu2){
		return menu1 + "/" + menu2;
	}


}
