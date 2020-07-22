package com.admin.upload.service.impl.common;


import com.admin.upload.service.AbstractHpService;
import com.admin.upload.service.SifResult;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.util.Map;

@Component
public class CommonActionImpl extends AbstractHpService
{

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
			}
		}
		
		return result.getResultMap();
	}
	
}
