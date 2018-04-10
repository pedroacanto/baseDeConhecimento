package org.chamados.models;

public enum TipoChamado {
	ANALISE("Análise"), RELATORIO("Relatório"), JAVA("Alteração via java"), SQL("Alteração via SQL");
	//Análise, Relatório,  Alteração_Via_Java, Alteração_Via_SQL
	private final String descricao;
	
	TipoChamado(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
