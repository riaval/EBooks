package ua.miratech.zhukov.service;

public class SecurityServiceMock implements SecurityService {

	public final static String CURRENT_USER = "current_user";

	@Override
	public String getUserEmail() {
		return "current_user";
	}

}
