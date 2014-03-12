package ua.miratech.zhukov.service;

import ua.miratech.zhukov.dto.UploadedFile;

/**
 * Service that provides conversion operations
 */
public interface ConverterService {

	/**
	 * Converts File (as bytes array) from .mobi or .epub into .fb2
	 *
	 * @param uploadedFile DTO with bytes array and information about file
	 * @return DTO with bytes array and information about a <b>new</b> file
	 */
	UploadedFile convertToFb2(UploadedFile uploadedFile);

}
