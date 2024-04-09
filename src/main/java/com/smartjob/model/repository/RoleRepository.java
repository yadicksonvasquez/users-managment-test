/**
 * 
 */
package com.smartjob.model.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smartjob.model.Role;

/**
 * Role repository
 * 
 * @author yadickson
 */
public interface RoleRepository extends JpaRepository<Role, BigInteger> {

	@Query("SELECT r FROM Role r join r.users u WHERE u.email=:emailParam")
	List<Role> getRoleByUserEmail(@Param("emailParam") String email);

	@Query("SELECT r FROM Role r WHERE r.name=:nameParam")
	Role getRoleByName(@Param("nameParam") String roleName);

}
