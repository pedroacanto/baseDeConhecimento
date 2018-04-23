package org.chamados.controller;

import java.util.List;

import javax.validation.Valid;

import org.chamados.models.Sistema;
import org.chamados.repositories.SistemaRepository;
import org.chamados.validation.SistemaValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sistemas")
public class SistemaController {

	@Autowired
	SistemaRepository repo;
	
	@GetMapping
	public ModelAndView listar(){
		List<Sistema> sistemas = repo.findAll();

		ModelAndView modelAndView = new ModelAndView("jsp/sistemas/lista");
		modelAndView.addObject("sistemas",sistemas);

		return modelAndView;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Sistema sistema){
		return new ModelAndView("jsp/sistemas/cadastrar");
	}

	@RequestMapping(value="/buscar", method = RequestMethod.GET)
	public ModelAndView buscar(@RequestParam("nome") String nome){
		List<Sistema> sistemas = repo.findByNomeContaining(nome);

		ModelAndView modelAndView = new ModelAndView("jsp/sistemas/lista");
		modelAndView.addObject("sistemas", sistemas);

		return modelAndView;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id){
		ModelAndView modelAndView = new ModelAndView("jsp/sistemas/editar");

		Sistema sistema = repo.findById(id).get();
		modelAndView.addObject("sistema", sistema);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Sistema sistema, BindingResult result ,RedirectAttributes redirectAttributes){
		if(result.hasErrors()){
			return cadastrar(sistema);
		}

		repo.save(sistema);

		redirectAttributes.addFlashAttribute("sucesso", "Sistema Cadastrado com sucesso");
		return new ModelAndView("redirect:sistemas");
	}

	@GetMapping("teste")
	public ModelAndView teste(){
		return new ModelAndView("thymeleaf/sistemas/lista");
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		try{
			repo.deleteById(id);
			redirectAttributes.addFlashAttribute("sucesso", "Sistema excluido com sucesso");
		}catch(Exception ex){
			redirectAttributes.addFlashAttribute("erro", "Não foi possível excluir o sistema. Verifique se o mesmo não possui Funcionários atrelados.");
		} finally {
			return new ModelAndView("redirect:/sistemas");
		}
	}

	@InitBinder
	public void InitBinder (WebDataBinder binder){
		binder.addValidators(new SistemaValidation());
	}
	
}
