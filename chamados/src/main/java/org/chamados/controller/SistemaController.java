package org.chamados.controller;

import java.util.List;

import javax.validation.Valid;

import org.chamados.daos.SistemaDAO;
import org.chamados.models.Sistema;
import org.chamados.validation.SistemaValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("sistemas")
public class SistemaController {
	
	@Autowired
	SistemaDAO sistemaDao = new SistemaDAO();
	
	@RequestMapping("/form")
	public ModelAndView form(Sistema sistema){
		return new ModelAndView("sistemas/form");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar(){
		List<Sistema> sistemas = sistemaDao.listar();
		ModelAndView modelAndView = new ModelAndView("/sistemas/lista");
		modelAndView.addObject("sistemas",sistemas);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Sistema sistema, BindingResult result ,RedirectAttributes redirectAttributes){
		if(result.hasErrors()){
			return form(sistema);
		}
		
		sistemaDao.gravar(sistema);
		redirectAttributes.addFlashAttribute("sucesso", "Sistema Cadastrado com sucesso");
		return new ModelAndView("redirect:sistemas");
	}
	
	@RequestMapping(value="/busca", method = RequestMethod.GET)
	public ModelAndView buscarSistema(@RequestParam("nome") String nome){
		List<Sistema> sistemas = sistemaDao.buscarSistemaNome(nome);
		ModelAndView modelAndView = new ModelAndView("/sistemas/lista");
		modelAndView.addObject("sistemas", sistemas);
		return modelAndView;
	}
	
	@RequestMapping(value="/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editarSistema(@PathVariable("id") int id){
		ModelAndView modelAndView = new ModelAndView("sistemas/edit");
		Sistema sistema = sistemaDao.buscaSistemaId(id);
		modelAndView.addObject("sistema", sistema);
		return modelAndView;
	}
	
	@RequestMapping(value="/editar", method= RequestMethod.POST)
	public ModelAndView editar(@Valid Sistema sistema, BindingResult result, RedirectAttributes redirectAttributes ){
		if(result.hasErrors()){
			return edit(sistema);
		}		
		sistemaDao.editar(sistema);
		redirectAttributes.addFlashAttribute("sucesso", "Sistema alterado com sucesso");
		return new ModelAndView("redirect:/sistemas");
		
	}
	
	@RequestMapping(value="/excluir/{id}", method = RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") int id, RedirectAttributes redirectAttributes){
		try{
			sistemaDao.excluirSistema(id);
			redirectAttributes.addFlashAttribute("sucesso", "Sistema excluido com sucesso");
			return new ModelAndView("redirect:/sistemas");
		}catch(Exception ex){
			redirectAttributes.addFlashAttribute("erro", "Não foi possível excluir o sistema. Verifique se o mesmo não possui Funcionários atrelados.");
			return new ModelAndView("redirect:/sistemas");
		}		
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(Sistema sistema){
		return new ModelAndView("sistemas/edit");
	}
	
	@InitBinder
	public void InitBinder (WebDataBinder binder){
		binder.addValidators(new SistemaValidation());
	}
	
}
