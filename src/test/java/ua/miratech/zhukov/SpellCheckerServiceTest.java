package ua.miratech.zhukov;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.miratech.zhukov.repository.redis.DictionaryRepository;
import ua.miratech.zhukov.util.DamerauLevenshtein;
import ua.miratech.zhukov.util.PhoneticCode;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/redis-config.xml"})
public class SpellCheckerServiceTest {

	@Autowired
	DictionaryRepository dictionaryRepository;

	@Test
	public void spellTest() {
		String source = "thausend";
		String rightWord;

		List<String> words = dictionaryRepository.get(PhoneticCode.metaPhone(source));
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
		Assert.assertEquals(rightWord, "thousand");
	}

}
