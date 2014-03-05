package ua.miratech.zhukov.util.component;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EbookStorage {

	private static final Logger logger = Logger.getLogger(EbookStorage.class);

	@Value("#{environment.EBOOKS_STORAGE}")
	private String basePath;

	@Value("${main_catalogue}")
	private String baseMainCatalogue;
	@Value("${index_catalogue}")
	private String baseIndexCatalogue;
	@Value("${temp_catalogue}")
	private String baseTempCatalogue;

	private String mainCatalogue;
	private String indexCatalogue;
	private String tempCatalogue;

	@PostConstruct
	private void init() {
		mainCatalogue = basePath + baseMainCatalogue;
		indexCatalogue = basePath + baseIndexCatalogue;
		tempCatalogue = basePath + baseTempCatalogue;

		logger.info("Main catalogue initialized in [" + mainCatalogue + "]");
		logger.info("Index catalogue initialized in [" + indexCatalogue + "]");
		logger.info("Temp catalogue initialized in [" + tempCatalogue + "]");
	}

	public String getMainCatalogue() {
		return mainCatalogue;
	}

	public String getIndexCatalogue() {
		return indexCatalogue;
	}

	public String getTempCatalogue() {
		return tempCatalogue;
	}

}
