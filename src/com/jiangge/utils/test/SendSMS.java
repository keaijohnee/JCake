package com.jiangge.utils.test;

import java.util.Map;

public class SendSMS {
	/**
	 * 向指定手机号发送短信
	 * @param phone
	 * @param content
	 */
	public void send(Map<String, String> taskParam){
		/**获取手机号和发送的内容**/
		String phone = taskParam.get("phone");
		String content = taskParam.get("content");
		System.out.println("向手机号:" + phone + "发送短信,内容是:" + content);
	}
}
