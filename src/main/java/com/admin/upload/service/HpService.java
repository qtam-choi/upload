package com.admin.upload.service;

import java.io.IOException;
import java.util.Map;

public interface HpService {

	public abstract Object serviceCall(Map dataMap) throws IOException;
	
}
