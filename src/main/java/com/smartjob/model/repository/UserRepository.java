/**
 * 
 */
package com.smartjob.model.repository;

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smartjob.model.User;

/**
 * User Repository
 * 
 * @author yadickson
 */
public interface UserRepository extends JpaRepository<User, BigInteger> {

	@Query("SELECT u FROM User u WHERE u.email = :email_param")
	User findByEmail(@Param("email_param") String email);

}
