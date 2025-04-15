package com.example.kchen.todo.springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "Tasks")
@Setter
@Getter
@NoArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "title", length = 250)
	private String title;

	@Column(name = "description", length = 2000)
	private String description;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "completed")
	private boolean completed;

	@Column(name = "due_date")
	private Timestamp dueDate;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", updatable = false)
	private Timestamp created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	private Timestamp updated;

	public Task(String title, String description, Category category, Timestamp dueDate) {
		this.title = title;
		this.description = description;
		this.category = category;
		this.dueDate = dueDate;
	}
}
