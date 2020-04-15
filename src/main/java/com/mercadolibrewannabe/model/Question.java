package com.mercadolibrewannabe.model;

import com.mercadolibrewannabe.event.QuestionCreatedEvent;
import com.mercadolibrewannabe.infra.EmailBodyProvider;
import com.mercadolibrewannabe.model.dto.QuestionDto;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

	public static List<QuestionDto> mapQuestionListToQuestionListDto (List<Question> questionList) {
		return questionList.stream().map(QuestionDto::new).collect(Collectors.toList());
	}

	public LocalDateTime getCreatedAt () {
		return createdAt;
	}

	public User getAuthor () {
		return author;
	}

	public String getTitle () {
		return title;
	}
}
