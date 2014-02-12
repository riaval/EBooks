package ua.miratech.zhukov.mapper;

import ua.miratech.zhukov.dto.IndexBook;

import java.io.IOException;

public interface BookIndexer {

	public void doIndex(IndexBook book) throws IOException;

}
