package org.chamados.daos;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.chamados.models.Funcionario;
import org.chamados.models.Sistema;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class FuncionarioDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Funcionario funcionario){		
		ArrayList<Sistema> attachedSistemas = new ArrayList<>();

	    
	    for(Sistema sistema: funcionario.getSistemas()) {
	    	Sistema sistemaNovo = new Sistema();
			sistemaNovo.setId(sistema.getId());
			sistemaNovo.setNome(sistema.getNome());
			sistemaNovo.setDescricao(sistema.getDescricao());
	    
	        attachedSistemas.add(sistemaNovo);
	    }
	    funcionario.setSistemas(attachedSistemas);
		
		manager.merge(funcionario);
	}
	
	public List<Funcionario> listar(){
		return manager.createQuery("select distinct f from Funcionario f left join f.sistemas order by f.nome").getResultList();
	}

	public List<Funcionario> buscarFuncionarioNome(String nome) {
		return manager.createQuery("select f from Funcionario f where nome like '"+nome+"%'").getResultList();
	}
	
	public void excluirFuncionario(int id){
		Funcionario funcionario = manager.find(Funcionario.class, id);
		manager.remove(funcionario);		
	}
	
	public Funcionario buscaFuncionarioId(int id){
		return manager.find(Funcionario.class, id);
	}
	
	public void editar(Funcionario funcionario){
		ArrayList<Sistema> attachedSistemas = new ArrayList<>();

	    
	    for(Sistema sistema: funcionario.getSistemas()) {
	    	Sistema sistemaNovo = new Sistema();
			sistemaNovo.setId(sistema.getId());
			sistemaNovo.setNome(sistema.getNome());
			sistemaNovo.setDescricao(sistema.getDescricao());
	    
	        attachedSistemas.add(sistemaNovo);
	    }
	    funcionario.setSistemas(attachedSistemas);
		
		manager.merge(funcionario);
	}
		
}
