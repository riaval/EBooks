package ua.miratech.zhukov.util.component;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.miratech.zhukov.dto.controller.CreatedUser;

import java.util.regex.Pattern;

@Component
public class CreatedUserValidator implements Validator {

	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

	@Override
	public boolean supports(Class<?> clazz) {
		return CreatedUser.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty");

		CreatedUser user = (CreatedUser) o;

		boolean isValidEmail = pattern.matcher(user.getEmail()).matches();
		if (!isValidEmail) {
			errors.rejectValue("email", "email.notValid", "Not valid email");
		}

		int passwordLength = user.getPassword().length();
		if (passwordLength < 6 || passwordLength > 30) {
			errors.rejectValue("password", "password.notValid", "Password must be between 6 and 30 characters long");
		}

		if (!user.getPassword().equals(user.getPasswordAgain())) {
			errors.rejectValue("password", "password.notMatch", "Passwords don't match");
		}
	}

}
