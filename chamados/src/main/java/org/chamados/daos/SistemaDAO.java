package org.chamados.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.chamados.models.Funcionario;
import org.chamados.models.Sistema;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SistemaDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Sistema> listar(){
		return manager.createQuery("select s from Sistema s order by nome").getResultList();
	}
	
	public void gravar(Sistema sistema){
		manager.persist(sistema);
	}
	
	public List<Sistema> buscarSistemaNome(String nome) {
		return manager.createQuery("select s from Sistema s where nome like '"+nome+"%'").getResultList();
	}
	
	public Sistema buscaSistemaId(Long id){
		return manager.find(Sistema.class, id);
	}
	
	public void editar(Sistema sistema){
		manager.merge(sistema);
	}
	
	public void excluirSistema(Long id){
		Sistema sistema = manager.find(Sistema.class, id);
		manager.remove(sistema);	
	}
	
}
