package com.mercadolibrewannabe.model;

import org.hibernate.annotations.Type;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Email {

	@Id
	@Type (type="uuid-char")
	@GeneratedValue
	private UUID id;

	@NotBlank
	@Column(nullable = false)
	private String title;

	@NotBlank
	@Column(nullable = false)
	private String body;

	@PastOrPresent
	@Column(nullable = false)
	private LocalDateTime sendDate;

	@ManyToOne(optional = false)
	private User seller;

	@ManyToOne(optional = false)
	private User questionAuthor;

	/**
	 * @deprecated usage only for frameworks
	 */
	@Deprecated
	public Email () { }

	public Email (String title,
	              String body,
	              LocalDateTime sendDate,
	              User seller,
	              User questionAuthor) {

		Assert.hasText(title);
		Assert.hasText(body);
		Assert.isTrue(!(sendDate.isAfter(LocalDateTime.now())));
		Assert.notNull(seller);
		Assert.notNull(questionAuthor);

		this.title = title;
		this.body = body;
		this.sendDate = sendDate;
		this.seller = seller;
		this.questionAuthor = questionAuthor;
	}
}
