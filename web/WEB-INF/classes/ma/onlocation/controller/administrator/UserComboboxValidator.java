package ma.onlocation.controller.administrator;

import ma.onlocation.models.User;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserComboboxValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {

		return User.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
              
		if ((user.getRoles() != null) && (user.getRoles().getIdRole() == null)) {

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles.idRole",
					"rolee.required");

		}
	}

}
