package ua.miratech.zhukov.service.implementation;

import org.springframework.stereotype.Component;

@Deprecated
@Component
public class JobsService {

//	@Autowired()
//	@Qualifier("executorService")
//	ExecutorService service;
//
//	@Autowired(required = false)
//	JobMapper jobMapper;
//
//	@Deprecated
//	public void newIndexFileJob(String path) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String email = auth.getName();
//
//		Job job = new Job(
//				email,
//				"Indexation books",
//				Calendar.getInstance().getTime(),
//				null
//		);
//
//		jobMapper.insertJob(job);
//
//		service.submit(new JobThread(path, job.getId(), jobMapper));
//	}
//
//	@Deprecated
//	public List<Job> getJobs() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String email = auth.getName();
//
//		return jobMapper.getJob(email);
//	}

}
