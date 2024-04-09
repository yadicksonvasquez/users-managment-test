/**
 * 
 */
package com.smartjob.model;

import java.math.BigInteger;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Telefono de usuario
 * 
 * @author yadickson
 */
@Entity
@Table(name = "USER_PHONE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private BigInteger id;

	@Column(name = "phonbe_number")
	private String phoneNumber;

	@Column(name = "city_code")
	private String cityCode;

	@Column(name = "country_code")
	private String countryCode;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
