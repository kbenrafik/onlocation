package ma.onlocation.controller.manager;

import ma.onlocation.models.Photo;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PhotoComboboxValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {

		return Photo.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Photo photo = (Photo) target;

		if (photo.getIsVisible() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isVisible",
					"visibilite.required");

		}
		/*
		if ((photo.getLocation() != null)
				&& (photo.getLocation().getLocationID() == null)) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"location.locationID", "location.required");

		}*/
	}

}
