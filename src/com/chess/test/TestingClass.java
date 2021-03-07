package com.chess.test;

import com.chess.common.security.SHA256;

public class TestingClass {

	
	public static void main(String[] args) {
		String pass = "CeciEstUnPass";
		String hashed = SHA256.hash(pass, "null");
		System.out.println("Have to be true: " + SHA256.comparePassword(hashed, pass));
		System.out.println("Can be true: " + SHA256.comparePassword(hashed, hashed));
		System.out.println("Have to be false: " + SHA256.comparePassword(hashed, "another"));
	}
}
