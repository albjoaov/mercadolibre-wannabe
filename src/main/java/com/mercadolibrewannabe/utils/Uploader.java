package com.mercadolibrewannabe.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Uploader {

	String upload (List<MultipartFile> multipartFile);
}
