package ua.miratech.zhukov;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.miratech.zhukov.redis.DictionaryRepository;
import ua.miratech.zhukov.util.DamerauLevenshtein;
import ua.miratech.zhukov.util.PhoneticCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/redis-config.xml"})
public class RedisTests {

	@Autowired
	DictionaryRepository dictionaryRepository;

	@Test
	public void connectionTest() {
		long startTime = System.currentTimeMillis();
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
			System.out.println(words.get(shortestIndex) + " " + distances[i]);
		}
		rightWord = words.get(shortestIndex);
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println("That took " + duration + " milliseconds");
		System.out.println(rightWord);
	}

//	@Test
	public void redFileTest() {
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("D:\\EBOOKS_STORAGE\\dictionary.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				dictionaryRepository.put(PhoneticCode.metaPhone(sCurrentLine), sCurrentLine);
//				System.out.println(PhoneticCode.metaPhone(sCurrentLine));
//				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

//	@Test
//	public void redFileTest() {
//		BufferedReader br = null;
//
//		try {
//
//			String sCurrentLine;
//
//			br = new BufferedReader(new FileReader("D:\\EBOOKS_STORAGE\\OWL.txt"));
//			PrintWriter writer = new PrintWriter("D:\\EBOOKS_STORAGE\\NewOWL.txt", "UTF-8");
//
//			while ((sCurrentLine = br.readLine()) != null) {
//				String arr[] = sCurrentLine.split(" ", 2);
//				String firstWord = arr[0];
//				writer.println(firstWord.toLowerCase());
////				System.out.println(firstWord);
//			}
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (br != null)br.close();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//	}

}
