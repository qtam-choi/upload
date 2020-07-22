package com.admin.upload.common.util;

import java.util.HashMap;

public class CaseInsensitiveMap extends HashMap<Object, Object> {

    @Override
    public Object put(Object key, Object value) {
    	return super.put(((String)key).toLowerCase().replaceAll("_re_nm", ""), value);
    }

    // not @Override because that would require the key parameter to be of type Object
    public Object getKey(String key) {
       return super.get(((String)key).toLowerCase());
    }
}