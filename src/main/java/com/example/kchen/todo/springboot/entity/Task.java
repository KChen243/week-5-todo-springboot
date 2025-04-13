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

	@Column(name = "Title", length = 250)
	private String title;

	@Column(name = "Description", length = 2000)
	private String description;

	@Column(name = "Category", length = 2000)
	private String category;

	@Column(name = "IsDeleted")
	private boolean isDeleted;

	@Column(name = "Completed")
	private boolean completed;

	@Column(name = "DueDate")
	private Timestamp dueDate;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created", updatable = false)
	private Timestamp created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Updated")
	private Timestamp updated;

	public Task(String title, String description, String category, Timestamp dueDate) {
		this.title = title;
		this.description = description;
		this.category = category;
		this.dueDate = dueDate;
	}
}
