package com.votr;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CloudSearchUploadQueue {
	public static CloudSearchUploadQueue singleQueue;

	private final ScheduledThreadPoolExecutor executor;
	private final Queue<Vote> queue;
	private final indexUploader indexUploader;

	public CloudSearchUploadQueue(ScheduledThreadPoolExecutor executor,
			Queue<Vote> queue, indexUploader indexUploader) {
		this.executor = executor;
		this.queue = queue;
		this.indexUploader = indexUploader;
		executor.scheduleAtFixedRate(new UploadRunnable(), 3000, 2000,
				TimeUnit.MILLISECONDS);
	}

	public static void init() {
		if (singleQueue == null) {
			singleQueue = new CloudSearchUploadQueue(
					new ScheduledThreadPoolExecutor(4),
					new LinkedBlockingQueue<Vote>(), new indexUploader());
		}

	}

	public boolean enqueue(Vote e) {
		return queue.add(e);
	}

	private class UploadRunnable implements Runnable {

		@Override
		public void run() {
			List<Vote> votes = new ArrayList<Vote>();
			while (!queue.isEmpty()) {
				votes.add(queue.poll());
			}
			if (votes.size() > 0) {
				indexUploader.indexVote(votes, (int) System.currentTimeMillis());
			}
		}

	}
}
