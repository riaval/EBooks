package ua.miratech.zhukov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ua.miratech.zhukov.dao.JobDAO;
import ua.miratech.zhukov.dto.Job;
import ua.miratech.zhukov.util.JobThread;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Component
public class JobsService {

	@Autowired()
	@Qualifier("executorService")
	ExecutorService service;

	@Autowired(required = false)
	JobDAO jobDAO;

	public void newIndexFileJob(String path) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		Job job = new Job(
				email,
				"Indexation books",
				Calendar.getInstance().getTime(),
				null
		);

		jobDAO.insertJob(job);

		service.submit(new JobThread(path, job.getId(), jobDAO));
	}

	public List<Job> getJobs() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		return jobDAO.getJob(email);
	}

}
