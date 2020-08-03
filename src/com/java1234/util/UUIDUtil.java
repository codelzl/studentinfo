package com.java1234.util;

import java.util.UUID;

/**
 * UUID工具类
 * @author 
 *
 */
public class UUIDUtil {

	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(UUIDUtil.getUUID());
	}
}
