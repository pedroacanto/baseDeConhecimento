package org.chamados.validation;

import org.chamados.models.Chamado;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ChamadosValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Chamado.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors erros) {
		ValidationUtils.rejectIfEmpty(erros, "numero_chamado", "field.required");
		ValidationUtils.rejectIfEmpty(erros, "descricao", "field.required");
		ValidationUtils.rejectIfEmpty(erros, "data_abertura", "field.required");
		ValidationUtils.rejectIfEmpty(erros, "resolucao", "field.required");
		ValidationUtils.rejectIfEmpty(erros, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(erros, "aberto_funcionario", "field.required");
	}

}
