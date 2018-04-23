package org.chamados.controller;



import java.util.List;

import javax.validation.Valid;

import org.chamados.daos.ChamadoDAO;
import org.chamados.daos.FuncionarioDAO;
import org.chamados.daos.SistemaDAO;
import org.chamados.models.Chamado;
import org.chamados.models.Funcionario;
import org.chamados.models.Script;
import org.chamados.models.Sistema;
import org.chamados.models.TipoChamado;
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
							   @RequestParam("script_list[]") String[] script_list,
							   @RequestParam("idSistema") Long idSistema,
							   @RequestParam("idFuncionario") Integer idFuncionario,
							   @RequestParam("tipoChamado") TipoChamado tipoChamado){
		if(result.hasErrors()){
			return form(chamado);
		}				
		
		/*List<Script> scripts = new ArrayList<Script>();
		
		for(String le_array_script : script_list) {
			Script novo_script = new Script();
			novo_script.setScript_usado(le_array_script);
			scripts.add(novo_script);
		}
		
		chamado.setScripts(scripts);*/
		
		Funcionario funcionario = funcionarioDao.buscaFuncionarioId(idFuncionario);
		chamado.setFuncionario_responsavel(funcionario);
		
		Sistema sistema = sistemaDao.buscaSistemaId(idSistema);
		chamado.setSistema(sistema);
		
		chamado.setTipo_chamado(tipoChamado);
		
		try{
			chamadoDao.gravar(chamado,script_list);
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
										     @RequestParam("tipoChamado") TipoChamado tipoChamado,
										     @RequestParam("script") String script){
		
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
		
		ModelAndView modelAndView = new ModelAndView("/chamados/busca");
		
		List<Funcionario> funcionarios = funcionarioDao.listar();
		modelAndView.addObject("funcionarios",funcionarios);
		
		List<Sistema> sistemas = sistemaDao.listar();
		modelAndView.addObject("sistemas", sistemas);
		
		modelAndView.addObject("tipos", TipoChamado.values()); 
		
		List<Chamado> chamadosBusca = chamadoDao.buscaAvancada(chamado, script);
		
		
		if(chamadosBusca.size() == 0) {
			modelAndView.addObject("erros", "Não foi encontrado nenhum resultado para a busca.");
		}
		modelAndView.addObject("chamadosBusca", chamadosBusca);
				
		return modelAndView;	
	}
	
	@RequestMapping(value="/view/{id}", method=RequestMethod.GET)
	public ModelAndView visualizar(@PathVariable("id") int id){ //@PathVariable pega tudo que tá na URL
		Chamado chamado = chamadoDao.buscaChamadoId(id);
		
		List<Script> scripts = chamadoDao.buscaScriptsChamado(id);
		
		ModelAndView modelAndView = new ModelAndView("/chamados/view");
		modelAndView.addObject("chamado", chamado);
		modelAndView.addObject("scripts", scripts);
		
		return modelAndView;		
	}
	
	@InitBinder
	public void InitBinder(WebDataBinder binder){
		binder.addValidators(new ChamadosValidation());
	}	
}
