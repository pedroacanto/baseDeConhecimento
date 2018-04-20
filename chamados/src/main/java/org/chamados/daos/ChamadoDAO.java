package org.chamados.daos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.chamados.models.Chamado;
import org.chamados.models.Script;
import org.chamados.models.Sistema;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ChamadoDAO {

	@PersistenceContext
	EntityManager manager;
	
	public void gravar(Chamado chamado, String[] script_list){
		ArrayList<Script> attachedScripts = new ArrayList<>();
	    
	    for(String script: script_list) {
	    	Script scriptNovo = new Script();
			scriptNovo.setChamado(chamado);
			scriptNovo.setScript_usado(script);	    
			attachedScripts.add(scriptNovo);
	    }
	    chamado.setScripts(attachedScripts);
		
	    manager.merge(chamado);
	}
	
	public List<Chamado> listar(){
		return manager.createQuery("select c from Chamado c").getResultList();
	}

	public List<Chamado> buscaChamadoCod(String numero_chamado) {
		return manager.createQuery("select c from Chamado c where numero_chamado = '"+numero_chamado+"'").getResultList();
	}

	public Chamado buscaChamadoId(int id) {
		return manager.find(Chamado.class, id);
	}

	public List<Chamado> buscaAvancada(Chamado chamado, String script) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct c from Chamado c left join c.scripts s ");
		/*if("".equalsIgnoreCase(script)) {
			sql.append(" left join c.scripts ");
		}*/
		sql.append(" where ");
		if(script != null && !"".equalsIgnoreCase(script)) {
			sql.append(" s.script_usado like'%"+script+"%' and ");
		}
		if(chamado.getNumero_chamado() != null && chamado.getNumero_chamado() != ""){
			sql.append("c.numero_chamado = '"+chamado.getNumero_chamado()+"' and ");
		}
		if(chamado.getTitulo() != null && chamado.getTitulo() != ""){
			sql.append("c.titulo like '%"+chamado.getTitulo()+"%' and ");
		}
		if(chamado.getSistema() != null){
			sql.append("c.sistema.id = "+chamado.getSistema().getId()+" and ");
		}
		if(chamado.getAberto_funcionario() != null && chamado.getAberto_funcionario() != ""){
			sql.append("c.aberto_funcionario like '%"+chamado.getAberto_funcionario()+"%' and ");
		}
		if(chamado.getFuncionario_responsavel() != null){
			sql.append("c.funcionario_responsavel.id = "+chamado.getFuncionario_responsavel().getId()+" and ");
		}
		if(chamado.getData_abertura() != null){
			Date date = chamado.getData_abertura().getTime();
			String dataFinal = new SimpleDateFormat("yyyy-MM-dd").format(date);
			sql.append("cast(c.data_abertura as date) = '"+dataFinal+"' and ");
		}
		if(chamado.getTipo_chamado() != null){
			sql.append("c.tipo_chamado = '"+chamado.getTipo_chamado()+"' and ");
		}
		if(chamado.getDescricao() != null && chamado.getDescricao() != ""){
			sql.append("c.descricao like '%"+chamado.getDescricao()+"%' and ");
		}
		sql.append("1 = 1 order by c.data_abertura desc");
		
		return manager.createQuery(sql.toString()).getResultList();		
	}

	public List<Script> buscaScriptsChamado(int id) {
		return manager.createQuery("select s from Script s where chamado_id = "+id).getResultList();
	}
	
	/*public List<Chamado> buscaAvancadaScriptChamado(String script){
		List<Script> scripts = manager.createQuery("select s from Script s where s.sql_usado like'%"+script+"%'").getResultList();
		List<Chamado> chamadosListResult = new ArrayList<Chamado>();
		
		for(Script scriptResult : scripts) {
			Chamado chamadoResult = scriptResult.getChamado();
			chamadosListResult.add(chamadoResult);
		}
		return chamadosListResult;
	}*/
	
}
