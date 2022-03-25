package com.pizza.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Uils {
	private String str;
	
	public Uils (String mp) throws NoSuchAlgorithmException {
		MessageDigest msg = MessageDigest.getInstance("MD5");
		byte [] hash = msg.digest(mp.getBytes());
		StringBuilder s = new StringBuilder();
		for (byte b: hash) {
			s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		}
		this.str =  s.toString();
	}
	public String getStr() {
		return str;
	}
}
