package org.chamados.daos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.chamados.models.Chamado;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ChamadoDAO {

	@PersistenceContext
	EntityManager manager;
	
	public void gravar(Chamado chamado){
		manager.persist(chamado);
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

	public List<Chamado> buscaAvancada(Chamado chamado) {
		StringBuilder sql = new StringBuilder();
		sql.append("select c from Chamado c where ");
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
}
