package com.mercadolibrewannabe.model.form;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.mercadolibrewannabe.model.Product;
import com.mercadolibrewannabe.model.Question;

import javax.validation.constraints.NotNull;

@JsonAutoDetect (fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class QuestionForm {

	@NotNull
	private String title;

	public Question toModel (Product product) {
		return new Question(this.title, product);
	}

}
