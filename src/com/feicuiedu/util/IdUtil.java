package com.feicuiedu.util;

import java.util.UUID;

public class IdUtil {
	
	
	public static String getUUID()
	{
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replaceAll("-", "");
		return id;
	}
	
}
