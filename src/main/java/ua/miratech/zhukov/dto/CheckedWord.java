package ua.miratech.zhukov.dto;

public class CheckedWord {

	private boolean right;
	private String word;

	public CheckedWord() { }

	public CheckedWord(boolean right, String word) {
		this.right = right;
		this.word = word;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
