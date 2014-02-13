package ua.miratech.zhukov.mapper;

import org.apache.ibatis.annotations.Param;
import ua.miratech.zhukov.dto.Job;

import java.util.Date;
import java.util.List;

@Deprecated
public interface JobMapper {

	public List<Job> getJob(
			@Param("userEmail") String userEmail
	);

	public void insertJob(Job job);

	public void updateEndTime(
			@Param("endTime")Date endTime,
			@Param("jobId") Long jobId
	);

}
