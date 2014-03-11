package ua.miratech.zhukov.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DictionaryRepository {

	private static final String metaphone = "metaphone";

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, List<String>> redisTemplate;

	public void put(String soundex, String word) {
		List<String> words = get(soundex);
		if (words == null) {
			words = new ArrayList<>();
		}
		words.add(word);

		redisTemplate.opsForHash()
				.put(metaphone, soundex, words);
	}

	public List<String> get(String soundex) {
		return (List<String>) redisTemplate.opsForHash().get(metaphone, soundex);
	}

}
