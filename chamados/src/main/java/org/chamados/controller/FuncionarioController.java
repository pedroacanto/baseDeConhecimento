package org.chamados.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.chamados.daos.FuncionarioDAO;
import org.chamados.daos.SistemaDAO;
import org.chamados.models.Funcionario;
import org.chamados.models.Sistema;
import org.chamados.validation.FuncionarioValidation;
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
@RequestMapping("funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioDAO funcionarioDAO;
	
	@Autowired
	SistemaDAO sistemaDao = new SistemaDAO();


	
	@RequestMapping("/form")
	public ModelAndView form(Funcionario funcionario){
		
		List<Sistema> sistemas =  sistemaDao.listar();
		
		ModelAndView modelAndView = new ModelAndView("funcionarios/form");
		modelAndView.addObject("sistemas", sistemas);
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravar(@Valid Funcionario funcionario, BindingResult result,
			                   RedirectAttributes redirectAttributes, @RequestParam("idSistema") List<Long> idSistemas){
		if(result.hasErrors()){
			return form(funcionario);
		}		
		
		ArrayList<Sistema> sistemasSelecionados = new ArrayList<Sistema>();
		
		for(Long idSistema: idSistemas){
			Sistema sistemaEncontrado = sistemaDao.buscaSistemaId(idSistema);				
			sistemasSelecionados.add(sistemaEncontrado);
		}
		
		funcionario.setSistemas(sistemasSelecionados);
		
		funcionarioDAO.gravar(funcionario);
		redirectAttributes.addFlashAttribute("sucesso", "Funcionário cadastrado com sucesso!");
		return new ModelAndView("redirect:funcionarios");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar(){
		List<Funcionario> funcionarios = funcionarioDAO.listar();
		ModelAndView modelAndView = new ModelAndView("/funcionarios/lista");
		modelAndView.addObject("funcionarios",funcionarios);
		return modelAndView;
	}
	
	@RequestMapping(value="/busca", method = RequestMethod.GET)
	public ModelAndView buscarFuncionario(@RequestParam("nome") String nome){
		List<Funcionario> funcionarios = funcionarioDAO.buscarFuncionarioNome(nome);
		ModelAndView modelAndView = new ModelAndView("/funcionarios/lista");
		modelAndView.addObject("funcionarios", funcionarios);
		return modelAndView;
	}
	
	@RequestMapping(value="/excluir/{id}", method = RequestMethod.GET)
	public ModelAndView excluirFuncionario(@PathVariable("id") int id, RedirectAttributes redirectAttributes){
		funcionarioDAO.excluirFuncionario(id);
		redirectAttributes.addFlashAttribute("sucesso", "Funcionário excluido com sucesso!");
		return new ModelAndView("redirect:/funcionarios");
	}
	
	@RequestMapping(value="/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editarFuncionario(@PathVariable("id") int id){
		ModelAndView modelAndView = new ModelAndView("funcionarios/edit");
		
		Funcionario funcionario = funcionarioDAO.buscaFuncionarioId(id);
		modelAndView.addObject("funcionario", funcionario);
		
		List<Sistema> sistemasTodos =  sistemaDao.listar();
		
		for(Sistema sistema : sistemasTodos){
			for(Sistema sistemaFuncionario : funcionario.getSistemas()){
				if(sistema.getId() == sistemaFuncionario.getId()){
					sistema.setChecked(true);
				}
			}
		}
		
		modelAndView.addObject("sistemas", sistemasTodos);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/editar",method=RequestMethod.POST)
	public ModelAndView editar(@Valid Funcionario funcionario, BindingResult result, 
			                   RedirectAttributes redirectAttributes,  @RequestParam("idSistema") List<Long> idSistemas){
		if(result.hasErrors()){
			return edit(funcionario);
		}
	
		ArrayList<Sistema> sistemasSelecionados = new ArrayList<Sistema>();
		
		for(Long idSistema: idSistemas){
			Sistema sistemaEncontrado = sistemaDao.buscaSistemaId(idSistema);				
			sistemasSelecionados.add(sistemaEncontrado);
		}
		
		funcionario.setSistemas(sistemasSelecionados);
		
		
		funcionarioDAO.editar(funcionario);
		redirectAttributes.addFlashAttribute("sucesso", "Funcionário alterado com sucesso!");
		return new ModelAndView("redirect:/funcionarios");
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(Funcionario funcionario){
		return new ModelAndView("funcionarios/edit");
	}
	
	@InitBinder
	public void InitBinder(WebDataBinder binder){
		binder.addValidators(new FuncionarioValidation());
	}
}
