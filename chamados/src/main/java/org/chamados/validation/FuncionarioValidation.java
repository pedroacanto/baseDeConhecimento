package org.chamados.validation;

import org.chamados.models.Funcionario;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class FuncionarioValidation implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Funcionario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors erros) {
		ValidationUtils.rejectIfEmpty(erros, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(erros, "email", "field.required");
		ValidationUtils.rejectIfEmpty(erros, "funcao", "field.required");
		
	}
	
}
