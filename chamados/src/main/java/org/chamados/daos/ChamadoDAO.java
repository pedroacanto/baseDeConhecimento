package org.chamados.daos;

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
}
