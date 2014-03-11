package ua.miratech.zhukov.service;

import ua.miratech.zhukov.dto.CheckedWord;

import java.util.List;

public interface SpellCheckerService {

	List<CheckedWord> check(String content);

}
