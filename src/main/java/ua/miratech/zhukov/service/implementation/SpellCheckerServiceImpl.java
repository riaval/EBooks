package ua.miratech.zhukov.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.miratech.zhukov.dto.CheckedWord;
import ua.miratech.zhukov.repository.redis.DictionaryRepository;
import ua.miratech.zhukov.service.SpellCheckerService;
import ua.miratech.zhukov.util.DamerauLevenshtein;
import ua.miratech.zhukov.util.PhoneticCode;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpellCheckerServiceImpl implements SpellCheckerService {

	@Autowired
	private DictionaryRepository dictionaryRepository;

	@Override
	public List<CheckedWord> check(String content) {
		String[] inputWords = content.split(" ");
		List<CheckedWord> outputWords = new ArrayList<>();
		boolean isAllRight = true;

		for (String word: inputWords) {
			CheckedWord checkedWord = convert(word);
			if (!checkedWord.isRight()) {
				isAllRight = false;
			}
			outputWords.add(checkedWord);
		}
		return isAllRight ? null : outputWords;
	}

	private CheckedWord convert(String source) {
		if (source.length() < 3 || Character.isUpperCase(source.charAt(0))) {
			return new CheckedWord(true, source);
		}

		String rightWord;

		List<String> words = dictionaryRepository.get(PhoneticCode.metaPhone(source));
		if (words == null || words.isEmpty()) {
			return new CheckedWord(true, source);
		}

		int[] distances = new int[words.size()];
		DamerauLevenshtein damerauLevenshtein = new DamerauLevenshtein(1, 1, 1, 1);
		for (int i = 0; i < words.size(); i++) {
			int distance = damerauLevenshtein.execute(source, words.get(i));
			distances[i] = distance;
		}
		int shortestDistance = 100;
		int shortestIndex = 0;
		for (int i = 0; i < distances.length; i++) {
			if (distances[i] < shortestDistance) {
				shortestDistance = distances[i];
				shortestIndex = i;
			}
		}
		rightWord = words.get(shortestIndex);

		CheckedWord checkedWord = new CheckedWord(
				source.equals(rightWord),
				rightWord
		);

		return checkedWord;
	}

}
