package com.jiangge.utils;

import java.util.List;

import com.jiangge.utils.task.TaskEntity;
import com.jiangge.utils.task.TaskPoolManager;

/**
 * Java多线程、队列实现任务调度
 * @author jiang.li
 * @date 2013-12-23 14:25
 */
public class TaskUtils {
	
	
    /**
     * 添加异步任务(任务列表)
     * @param taskList
     */
	public static void addTaskList(List<TaskEntity> taskList){
		TaskPoolManager.newInstance().addTasks(taskList);
	}
	
	 /**
     * 添加异步任务(单个任务)
     * @param taskList
     */
	public static void addTask(TaskEntity task){
		TaskPoolManager.newInstance().addTask(task);
	}
	
}
