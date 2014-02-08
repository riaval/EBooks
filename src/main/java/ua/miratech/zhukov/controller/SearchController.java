package ua.miratech.zhukov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.miratech.zhukov.lucene.FileIndexer;

@Controller
@RequestMapping("/search")
public class SearchController {

	@RequestMapping(method = RequestMethod.GET)
	public String printSearchPage(ModelMap model) {
		return "search-tiles";
	}

	@RequestMapping(method = RequestMethod.GET, params = {"content"})
	public String mainSearch(
			ModelMap model
			, @RequestParam(value = "content") String content
	) {
		try {
			FileIndexer.own(content);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "search-tiles";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String getSearchContent(ModelMap model) {
		return "search-tiles";
	}

}
