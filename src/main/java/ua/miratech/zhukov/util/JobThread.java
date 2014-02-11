package ua.miratech.zhukov.util;

import ua.miratech.zhukov.mapper.JobMapper;
import ua.miratech.zhukov.lucene.FileIndexer;

import java.util.Calendar;

public class JobThread implements Runnable {

	private String filePath;
	private Long jobId;
	private JobMapper jobMapper;

	public JobThread(String filePath, Long jobId, JobMapper jobMapper) {
		this.filePath = filePath;
		this.jobId = jobId;
		this.jobMapper = jobMapper;
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
		jobMapper.updateEndTime(
				Calendar.getInstance().getTime(),
				jobId
		);
	}
}
