package com.jiangge.utils.task;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理
 * @author jiang.li
 * @date 2013-12-17 11:10
 */
public class TaskPoolManager {
	
	/**构造一个单例的线程池**/
	private static TaskPoolManager tpm = new TaskPoolManager();
	
    private TaskPoolManager() {
    	
    }
    
	public static TaskPoolManager newInstance() { 
		return tpm;
	}

	/**线程池维护线程的最少数量**/
	private final static int CORE_POOL_SIZE = 4;
	
	/**线程池维护线程的最大数量**/
	private final static int MAX_POOL_SIZE = 100;
	
	/**线程池维护线程所允许的空闲时间**/
	private final static int KEEP_ALIVE_TIME = 0;
	
	/**线程池所使用的缓冲队列大小**/
	private final static int WORK_QUEUE_SIZE = 100;
	
	/**消息缓冲队列**/
	private Queue<TaskEntity> taskQueue = new LinkedList<TaskEntity>();
	
	/**访问消息缓存的调度线程**/
	final Runnable accessBufferThread = new Runnable() {
		public void run() {
			/**查看是否有待定请求，如果有，则创建一个新的TaskEntity，并添加到线程池中**/
			if (hasMoreAcquire()) {
				TaskEntity msg = taskQueue.poll();
				Runnable task = new TaskRunner(msg);
				threadPool.execute(task);
			}
		}
	};
	
	final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			taskQueue.offer(((TaskRunner) r).getTask());
		}
	};
	
	/**管理线程池**/
	final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
			CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(WORK_QUEUE_SIZE), this.handler);
	
	/**调度线程池**/
	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	final ScheduledFuture<?> taskHandler = scheduler.scheduleAtFixedRate(accessBufferThread, 0, 1, TimeUnit.SECONDS);


	/**
	 * 判断线程池时候为空
	 * @return
	 */
	private boolean hasMoreAcquire() {
		return !taskQueue.isEmpty();
	}

	/**
	 * 向线程池添加单个任务
	 * @param msg
	 */
	public void addTask(TaskEntity msg) {
		Runnable task = new TaskRunner(msg);
		threadPool.execute(task);
	}
	
	/**
	 * 向线程池添加多个任务
	 * @param msgList
	 */
	public void addTasks(List<TaskEntity> msgList) {
		Runnable task = null;
		for(TaskEntity msg : msgList){
			task = new TaskRunner(msg);
			threadPool.execute(task);
		}
	}
}
