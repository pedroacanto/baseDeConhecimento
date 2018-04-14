package org.chamados.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.chamados.daos.ChamadoDAO;
import org.chamados.daos.FuncionarioDAO;
import org.chamados.daos.SistemaDAO;
import org.chamados.models.*;
import org.chamados.validation.ChamadosValidation;
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
@RequestMapping("chamados")
public class ChamadosController {

	@Autowired
	SistemaDAO sistemaDao = new SistemaDAO();
	
	@Autowired
	FuncionarioDAO funcionarioDao = new FuncionarioDAO();
	
	@Autowired
	ChamadoDAO chamadoDao = new ChamadoDAO();
	
	@RequestMapping("/form")
	public ModelAndView form(Chamado chamado){
		
		List<Sistema> sistemas = sistemaDao.listar();
		List<Funcionario> funcionarios = funcionarioDao.listar();
		
		ModelAndView modelAndView = new ModelAndView("chamados/form");
		modelAndView.addObject("tipos", TipoChamado.values()); //Busca os valores do ENUM
		
		modelAndView.addObject("sistemas", sistemas);
		modelAndView.addObject("funcionarios", funcionarios);
		
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView salvar(@Valid Chamado chamado, BindingResult result,
							   RedirectAttributes redirectAttributes,
							   @RequestParam("script_list") String[] scripts,
							   @RequestParam("idSistema") Long idSistema,
							   @RequestParam("idFuncionario") Integer idFuncionario,
							   @RequestParam("tipoChamado") TipoChamado tipoChamado){
		if(result.hasErrors()){
			return form(chamado);
		}
		
		Funcionario funcionario = funcionarioDao.buscaFuncionarioId(idFuncionario);
		chamado.setFuncionario_responsavel(funcionario);
		
		Sistema sistema = sistemaDao.buscaSistemaId(idSistema);
		chamado.setSistema(sistema);
		
		chamado.setTipo_chamado(tipoChamado);

		List<Script> scriptList = Arrays.asList(scripts).stream()
				.map(s -> {
					Script script = new Script();
					script.setSql(s);
					return script;
				}).collect(Collectors.toList());

		chamado.setScripts(scriptList);
		
		try{
			chamadoDao.gravar(chamado);
			redirectAttributes.addFlashAttribute("sucesso","Chamado cadastrado com sucesso");
			return new ModelAndView("redirect:/chamados");
		}catch(Exception ex){
			ex.printStackTrace();//Joga erro no console
			redirectAttributes.addFlashAttribute("erro","Algo deu errado!");
			return new ModelAndView("redirect:/chamados");
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar(){
		List<Chamado> chamados = chamadoDao.listar();
		
		ModelAndView modelAndView = new ModelAndView("/chamados/lista");
		modelAndView.addObject("chamados", chamados);
		return modelAndView;
	}
	
	@RequestMapping(value="/busca", method=RequestMethod.GET)
	public ModelAndView buscarChamado(@RequestParam("numero_chamado") String numero_chamado){
		List<Chamado> chamados = chamadoDao.buscaChamadoCod(numero_chamado);
		
		ModelAndView modelAndView = new ModelAndView("/chamados/lista");
		modelAndView.addObject("chamados", chamados);
		return modelAndView;
		
	}
	
	@RequestMapping(value="/buscaAvancada", method=RequestMethod.GET)
	public ModelAndView buscaAvancada(Chamado chamado){
		ModelAndView modelAndView = new ModelAndView("/chamados/busca");
		
		List<Funcionario> funcionarios = funcionarioDao.listar();
		modelAndView.addObject("funcionarios",funcionarios);
		
		List<Sistema> sistemas = sistemaDao.listar();
		modelAndView.addObject("sistemas", sistemas);
		
		modelAndView.addObject("tipos", TipoChamado.values()); 
		
		return modelAndView;
	}
	
	@RequestMapping(value="/buscaAvancada", method=RequestMethod.POST)
	public ModelAndView buscaAvancadaChamado(Chamado chamado,
										     @RequestParam("idSistema") Long idSistema,
										     @RequestParam("idFuncionario") Integer idFuncionario, 
										     @RequestParam("tipoChamado") TipoChamado tipoChamado){
		
		if(idSistema != 0){
			Sistema sistema = sistemaDao.buscaSistemaId(idSistema);
			chamado.setSistema(sistema);
		}	
		if(idFuncionario != 0){
			Funcionario funcionario = funcionarioDao.buscaFuncionarioId(idFuncionario);
			chamado.setFuncionario_responsavel(funcionario);
		
		}if(tipoChamado != null){
			chamado.setTipo_chamado(tipoChamado);
		}
				
		List<Chamado> chamadosBusca = chamadoDao.buscaAvancada(chamado);
		
		ModelAndView modelAndView = new ModelAndView("/chamados/busca");
		
		List<Funcionario> funcionarios = funcionarioDao.listar();
		modelAndView.addObject("funcionarios",funcionarios);
		
		List<Sistema> sistemas = sistemaDao.listar();
		modelAndView.addObject("sistemas", sistemas);
		
		modelAndView.addObject("tipos", TipoChamado.values()); 
		
		modelAndView.addObject("chamadosBusca", chamadosBusca);
				
		return modelAndView;	
	}
	
	@RequestMapping(value="/view/{id}", method=RequestMethod.GET)
	public ModelAndView visualizar(@PathVariable("id") int id){ //@PathVariable pega tudo que tá na URL
		Chamado chamado = chamadoDao.buscaChamadoId(id);		
		ModelAndView modelAndView = new ModelAndView("/chamados/view");
		modelAndView.addObject("chamado", chamado);
		
		return modelAndView;		
	}
	
	@InitBinder
	public void InitBinder(WebDataBinder binder){
		binder.addValidators(new ChamadosValidation());
	}	
}
