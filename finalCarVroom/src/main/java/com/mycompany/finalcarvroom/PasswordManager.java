package com.mycompany.finalcarvroom;

import org.mindrot.jbcrypt.BCrypt;
// To be use to encrypt password as well as password validation
public class PasswordManager {
	// Password encryption method
	// Returns encrypted password
	public static String hashPassword(String plainPassword) {
		//Generate salt (Random data to strengthen hashing)
		String salt = BCrypt.gensalt(12);
		
		// Hash password with salt
		String hashedPassword = BCrypt.hashpw(plainPassword, salt);
		
		return hashedPassword;
	}
	// Password validation
	// Returns true if valid, false if invalid
	public static boolean verifyPassword(String plainPassword, String hashedPassword) {
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}
}
