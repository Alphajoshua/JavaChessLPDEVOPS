package com.chess.common.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

	public static String hash(String password, String salt) {
		try {
			return "$SHA$" + salt + "$" + getSHA256(String.valueOf(getSHA256(password)) + salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean comparePassword(String hash, String password) {
		if(password.contains("\\$"))
			return hash.equals(password);
		String[] line = hash.split("\\$");
		return hash.equals(hash(password, line[2]));
	}

	private static String getSHA256(String message) throws NoSuchAlgorithmException {
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		sha256.reset();
		sha256.update(message.getBytes());
		byte[] digest = sha256.digest();
		return String.format("%0" + (digest.length << 1) + "x", new BigInteger(1, digest));
	}
}
