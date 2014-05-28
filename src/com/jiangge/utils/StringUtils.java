package com.jiangge.utils;

import java.util.List;

/**
 * 字符串处理工具类
 * @author jiang.li
 * @date 2013-12-18 11:22
 */
public class StringUtils {

	/**
	 * 检查字符串是否为空
	 * @param str 字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else if (str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 检查字符串是否为空
	 * @param str 字符串
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str == null) {
			return false;
		} else if (str.length() == 0) {
			return false;
		} else {
			return true;
		}
	}


	 /**
	  * 把字符串按分隔符转换为数组
	  * @param str  字符串
	  * @param expr 分隔符
	  * @return
	  */
	 public static String[] stringToArray(String str, String expr){
		 return str.split(expr);
	 }
	 
	 /**
	  * 将数组按照给定的分隔转化成字符串
	  * @param arr
	  * @param expr
	  * @return
	  */
	 public static String arrayToString(String[] arr,String expr){
		 String strInfo = "";
		 if(arr != null && arr.length > 0){
			 StringBuffer sf = new StringBuffer();
			 for(String str : arr){
				 sf.append(str);
				 sf.append(expr);
			 }
			 strInfo = sf.substring(0, sf.length()-1);
		 }
		 return strInfo;
	 }
	 
	 
	 /**
	  * 将集合按照给定的分隔转化成字符串
	  * @param arr
	  * @param expr
	  * @return
	  */
	 public static String listToString(List<String> list,String expr){
		 String strInfo = "";
		 if(list != null && list.size() > 0){
			 StringBuffer sf = new StringBuffer();
			 for(String str : list){
				 sf.append(str);
				 sf.append(expr);
			 }
			 strInfo = sf.substring(0, sf.length()-1);
		 }
		 return strInfo;
	 }

}
