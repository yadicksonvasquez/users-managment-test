/**
 * 
 */
package com.smartjob.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Usuario
 * 
 * @author yadickson
 */
@Entity
@Table(name = "SMARTJOB_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private BigInteger userId;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "created", columnDefinition = "TIMESTAMP")
	private LocalDateTime created;

	@Column(name = "modified", columnDefinition = "TIMESTAMP")
	private LocalDateTime modified;

	@Column(name = "last_login", columnDefinition = "TIMESTAMP")
	private LocalDateTime lastLogin;

	@Column(name = "token")
	private String token;

	@Column(name = "isactive")
	private Boolean isActive;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@Builder.Default
	private List<Phone> phones = new ArrayList<>();

	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Role> roles;

}
