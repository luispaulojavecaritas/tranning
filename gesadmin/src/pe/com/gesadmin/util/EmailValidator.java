package pe.com.gesadmin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	private static final Pattern PHONE_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		Matcher matcher = PHONE_PATTERN.matcher(value.toString());

		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage("Invalid phone format",
					String.format("invalid input: %s, The valid format regex: %s", value, PHONE_PATTERN.pattern()));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}