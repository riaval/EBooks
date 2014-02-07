package ua.miratech.zhukov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.miratech.zhukov.dto.Job;
import ua.miratech.zhukov.service.JobsService;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobsController {

	@Autowired
	JobsService jobsService;

	@RequestMapping(method = RequestMethod.GET)
	public String printJobsPage(ModelMap model) {
		List<Job> jobs = jobsService.getJobs();

		model.addAttribute("jobs", jobs);

		return "jobs-tiles";
	}

}
