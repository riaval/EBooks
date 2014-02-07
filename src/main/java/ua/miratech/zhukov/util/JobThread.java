package ua.miratech.zhukov.util;

import ua.miratech.zhukov.dao.JobDAO;
import ua.miratech.zhukov.lucene.FileIndexer;

import java.util.Calendar;

public class JobThread implements Runnable {

	private String filePath;
	private Long jobId;
	private JobDAO jobDAO;

	public JobThread(String filePath, Long jobId, JobDAO jobDAO) {
		this.filePath = filePath;
		this.jobId = jobId;
		this.jobDAO = jobDAO;
	}

	@Override
	public void run() {
		System.out.println("index started " + filePath);
		FileIndexer.indexFile(filePath);
		System.out.println("index finished " + filePath);

		finish();
	}

	private void finish() {
		System.out.println(jobId);
		jobDAO.updateEndTime(
				Calendar.getInstance().getTime(),
				jobId
		);
	}
}
