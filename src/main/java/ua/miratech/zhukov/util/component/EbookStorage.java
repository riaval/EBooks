package ua.miratech.zhukov.util.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EbookStorage {

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

		System.out.println(mainCatalogue);
	}

	public String getMainCatalogue() {
		return mainCatalogue;
	}

	public void setMainCatalogue(String mainCatalogue) {
		this.mainCatalogue = mainCatalogue;
	}

	public String getIndexCatalogue() {
		return indexCatalogue;
	}

	public void setIndexCatalogue(String indexCatalogue) {
		this.indexCatalogue = indexCatalogue;
	}

	public String getTempCatalogue() {
		return tempCatalogue;
	}

	public void setTempCatalogue(String tempCatalogue) {
		this.tempCatalogue = tempCatalogue;
	}
}
