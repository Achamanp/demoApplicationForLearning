package com.E_CommerceApplication.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String mobileNumber;
	private String address;
	@OneToMany(mappedBy = "user")
	private List<Order> order;
	@OneToMany(mappedBy = "user")
	private List<Review> review;
	 public List<Review> getReview() {
		return review;
	}
	public void setReview(List<Review> review) {
		this.review = review;
	}
//	@ManyToMany(fetch = FetchType.EAGER)
//	    @JoinTable(
//	        name = "user_role",
//	        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//	        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
//	    )
//	    private Set<Role> roles = new HashSet<>();
//	public Set<Role> getRoles() {
//		return roles;
//	}
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
