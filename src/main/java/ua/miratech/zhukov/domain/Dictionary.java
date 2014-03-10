package ua.miratech.zhukov.domain;

public class Dictionary implements RedisDomain {

	public static final String OBJECT_KEY = "DICTIONARY";

	private String word;
	private String soundex;

	public Dictionary() { }

	public Dictionary(String word, String soundex) {
		this.word = word;
		this.soundex = soundex;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSoundex() {
		return soundex;
	}

	public void setSoundex(String soundex) {
		this.soundex = soundex;
	}

	@Override
	public String getKey() {
		return word;
	}

	@Override
	public String getObjectKey() {
		return OBJECT_KEY;
	}

	@Override
	public String toString() {
		return "Word [word=" + word + ", soundex=" + soundex + "]";
	}

}
