package com.example.kchen.todo.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "Tasks")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "Title")
	private String title;

	@Column(name = "Description")
	private String description;

	@Column(name = "IsDeleted")
	private boolean isDeleted;

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
}
