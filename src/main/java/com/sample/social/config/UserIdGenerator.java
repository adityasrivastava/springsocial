package com.sample.social.config;

import org.springframework.social.UserIdSource;

public class UserIdGenerator implements UserIdSource{

	@Override
	public String getUserId() {
		long unixTime = System.currentTimeMillis() / 1000L;
		return ""+unixTime;
	}

}
