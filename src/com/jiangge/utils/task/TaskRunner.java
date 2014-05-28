package com.jiangge.utils.task;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 执行任务的线程
 * @author jiang.li
 * @date 2013-12-17 13:17
 */
public class TaskRunner implements Runnable{

	/**任务实体定义**/
	private TaskEntity task;

	public TaskEntity getTask() {
		return task;
	}
	
	public TaskRunner(TaskEntity task) {
		this.task = task;
	}
	
	/**
	 * 线程开始执行
	 */
	public void run() {
		try {
			/**利用Java反射机制实现任务调度**/
			Class<?> className = task.getTaskClass();
			String classMethod = task.getTaskMethod();
			Method method = className.getMethod(classMethod, Map.class);
	        method.invoke(className.newInstance(),task.getTaskParam());
		}catch (Exception e) {
			System.out.println("出错了：" + e.getMessage());
		}
	}

}
