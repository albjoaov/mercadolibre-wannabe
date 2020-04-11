package com.mercadolibrewannabe.utils;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Profile ("dev")
public class UploaderMock implements Uploader {

	@Override
	public List<String> upload (List<MultipartFile> multipartFileList) {
		return multipartFileList.stream().map(this::getFileAddress).collect(Collectors.toList());
	}

	private String getFileAddress (MultipartFile file) {
		String fakeStorageServiceURL = "https://s3.amazon.com/";
		return fakeStorageServiceURL + file.getOriginalFilename() + UUID.randomUUID();
	}
}
