package com.mercadolibrewannabe.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Photo {

	@Column (nullable = false)
	@NotBlank
	private String url;

	/**
	 * @deprecated (Just for framework usages)
	 */
	@Deprecated
	public Photo () {
	}

	public Photo (String url) {
		this.url = url;
	}

	public static List<String> mapPhotoListToPhotoUrlList (List<Photo> photoList) {
		return photoList.stream().map(Photo::getUrl).collect(Collectors.toList());
	}

	public String getUrl () {
		return url;
	}
}
