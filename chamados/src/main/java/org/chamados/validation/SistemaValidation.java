package org.chamados.validation;

import org.chamados.models.Sistema;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SistemaValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Sistema.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors erros) {	
		ValidationUtils.rejectIfEmpty(erros, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(erros, "descricao", "field.required");
	}

}
