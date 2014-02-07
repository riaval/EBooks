package ua.miratech.zhukov.dao;

import org.apache.ibatis.annotations.Param;
import ua.miratech.zhukov.dto.Job;

import java.util.Date;
import java.util.List;

public interface JobDAO {

	public List<Job> getJob(
			@Param("userEmail") String userEmail
	);

	public Integer insertJob(Job job);

	public void updateEndTime(
			@Param("endTime")Date endTime,
			@Param("jobId") Long jobId
	);

}
