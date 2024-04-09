/**
 * 
 */
package com.smartjob.model.repository;

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartjob.model.Phone;

/**
 * Phone Repository
 * @author yadickson
 */
public interface PhoneRepository extends JpaRepository<Phone, BigInteger> {

}
