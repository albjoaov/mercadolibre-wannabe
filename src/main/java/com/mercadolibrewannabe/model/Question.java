package com.mercadolibrewannabe.model;

import com.mercadolibrewannabe.infra.EmailBodyProvider;
import com.mercadolibrewannabe.event.QuestionCreatedEvent;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners (AuditingEntityListener.class)
public class Question {

	@Id
	@GeneratedValue
	@Type (type="uuid-char")
	private UUID id;

	@Column (nullable = false, updatable = false)
	private final LocalDateTime createdAt = LocalDateTime.now();

	@ManyToOne (optional = false)
	private User author;

	@NotNull
	@Column(nullable = false)
	private String title;

	@NotNull
	@ManyToOne(optional = false)
	private Product product;

	/**
	 * @deprecated usage only for frameworks
	 */
	@Deprecated
	public Question () { }

	public Question (@NotNull String title,
	                 @NotNull Product product,
	                 @NotNull User author) {

		Assert.notNull(title);
		Assert.notNull(product);
		Assert.notNull(author);

		this.title = title;
		this.product = product;
		this.author = author;
	}

	public QuestionCreatedEvent toEvent() {
		return new QuestionCreatedEvent(this);
	}

	@Override
	public String toString () {
		return "Question{" +
				"createdAt=" + createdAt +
				", author=" + author +
				", title='" + title + '\'' +
				", product=" + product +
				'}';
	}

	public Email buildEmail(EmailBodyProvider emailBodyProvider) {

		return new Email(this.title,
				emailBodyProvider.get(),
				createdAt,
				this.product.getUser(),
				this.author);
	}
}
