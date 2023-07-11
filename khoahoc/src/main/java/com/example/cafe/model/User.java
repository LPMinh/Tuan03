package com.example.cafe.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Course> courses;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
