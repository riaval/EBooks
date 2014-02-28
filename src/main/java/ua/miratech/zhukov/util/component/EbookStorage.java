package ua.miratech.zhukov.util.component;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EbookStorage {

	private static final Logger logger = Logger.getLogger(EbookStorage.class);

	private final static String MAIN_CATALOGUE = "MAIN_CATALOGUE/";
	private final static String INDEX_CATALOGUE = "INDEX_CATALOGUE/";
	private final static String TEMP_FILES = "TEMP_FILES/";

	@Value("#{environment.EBOOKS_STORAGE}")
	private String basePath;

	private String mainCatalogue;
	private String indexCatalogue;
	private String tempCatalogue;

	@PostConstruct
	private void init() {
		mainCatalogue = basePath + MAIN_CATALOGUE;
		indexCatalogue = basePath + INDEX_CATALOGUE;
		tempCatalogue = basePath + TEMP_FILES;

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
