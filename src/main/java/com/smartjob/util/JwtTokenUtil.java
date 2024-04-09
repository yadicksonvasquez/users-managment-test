/**
 * 
 */
package com.smartjob.util;

import java.security.SecureRandom;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase utilitaria para generar token JWT
 * 
 * @author yadickson
 */
@Component
@Slf4j
public class JwtTokenUtil {

	@Autowired
	private Environment env;

	private byte[] sharedSecret = new byte[32];

	public JwtTokenUtil() {
		SecureRandom random = new SecureRandom();
		random.nextBytes(sharedSecret);
	}

	/**
	 * Genera el token JWT
	 * 
	 * @param user
	 * @return
	 */
	public String generateToken(String userEmail) {

		try {
			// Create HMAC signer
			JWSSigner signer = new MACSigner(sharedSecret);

			// Prepare JWS object
			JWTClaimsSet claims = new JWTClaimsSet.Builder().claim(JWTClaimNames.ISSUER, env.getProperty("jwt.issuer"))
					.claim(JWTClaimNames.ISSUED_AT, new Date()).claim(JWTClaimNames.SUBJECT, userEmail)
					.claim(JWTClaimNames.EXPIRATION_TIME,
							new Date(System.currentTimeMillis()
									+ Integer.parseInt(env.getProperty("jwt.access_token_validate_seconds")) * 1000))
					.build();

			Payload payload = new Payload(claims.toJSONObject());

			JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), payload);

			// Apply the HMAC
			jwsObject.sign(signer);

			return jwsObject.serialize();
		} catch (KeyLengthException e) {
			log.error("Error", e);
		} catch (NumberFormatException e) {
			log.error("Error", e);
		} catch (JOSEException e) {
			log.error("Error", e);
		}

		return null;
	}

	/**
	 * Obtiene el correo del usuario almacenado como claim subject
	 * 
	 * @param token
	 * @return String
	 */
	public String getUserEmail(String token) {
		String payload = null;
		try {
			JWSObject jwsObject = JWSObject.parse(token);
			payload = jwsObject.getPayload().toString();
			ObjectMapper objectMapper = new ObjectMapper();
			PayLoadJWT payloadObj = objectMapper.readValue(payload, PayLoadJWT.class);
			return payloadObj.getSub();
		} catch (Exception e) {
			log.error("Error", e);
		}
		return payload;
	}

	/**
	 * Valida el token contra la clave compartida
	 * 
	 * @param token
	 * @return boolean
	 */
	public boolean validate(String token) {
		boolean validated = false;
		try {
			JWSObject jwsObject = JWSObject.parse(token);
			JWSVerifier verifier = new MACVerifier(sharedSecret);
			validated = jwsObject.verify(verifier);
		} catch (Exception e) {
			log.error("Error", e);
		}
		return validated;
	}

}
