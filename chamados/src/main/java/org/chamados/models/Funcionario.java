package org.chamados.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="funcionario")
public class Funcionario {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	

	private String nome;
	private String email;
	private String funcao;
	
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name="sistema_funcionario",
	           joinColumns={@JoinColumn(name="funcionario_id")},
	           inverseJoinColumns={@JoinColumn(name="sistema_id")})
	private List<Sistema> sistemas = new ArrayList<Sistema>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Sistema> getSistemas() {
		return sistemas;
	}
	public void setSistemas(List<Sistema> sistemas) {
		this.sistemas = sistemas;
	}
	
}
