package org.chamados.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="chamado")
public class Chamado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String numero_chamado;
	
	private String titulo;
	
	@Column(columnDefinition = "text")
	private String descricao;
	
	private String aberto_funcionario;
	
	@DateTimeFormat
	private Calendar data_abertura;
	
	@Enumerated(EnumType.STRING)
	private TipoChamado tipo_chamado;
	
	@Column(columnDefinition = "text")
	private String resolucao;
	
	private String notacoes_alteracao;	

	@ManyToOne
	private Sistema sistema;
	
	@ManyToOne
	private Funcionario funcionario_responsavel;
	
	@OneToMany(mappedBy="chamado") //nome do atributo da classe no mappedBy
	private List<Script> scripts;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero_chamado() {
		return numero_chamado;
	}

	public void setNumero_chamado(String numero_chamado) {
		this.numero_chamado = numero_chamado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(Calendar data_abertura) {
		this.data_abertura = data_abertura;
	}

	public String getResolucao() {
		return resolucao;
	}

	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Funcionario getFuncionario_responsavel() {
		return funcionario_responsavel;
	}

	public void setFuncionario_responsavel(Funcionario funcionario_responsavel) {
		this.funcionario_responsavel = funcionario_responsavel;
	}

	public String getNotacoes_alteracao() {
		return notacoes_alteracao;
	}

	public void setNotacoes_alteracao(String notacoes_alteracao) {
		this.notacoes_alteracao = notacoes_alteracao;
	}

	public TipoChamado getTipo_chamado() {
		return tipo_chamado;
	}

	public void setTipo_chamado(TipoChamado tipo_chamado) {
		this.tipo_chamado = tipo_chamado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAberto_funcionario() {
		return aberto_funcionario;
	}

	public void setAberto_funcionario(String aberto_funcionario) {
		this.aberto_funcionario = aberto_funcionario;
	}
	
	public List<Script> getScripts() {
		return scripts;
	}

	public void setScripts(List<Script> scripts) {
		this.scripts = scripts;
	}
}
