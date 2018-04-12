<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:import url="../../header.jsp" />
<h2 align="center">Cadastro de Chamados</h2>
	<div class="container">
		<form:form action="/chamados/chamados" method="post" commandName="chamado" enctype="multipart/form-data">
			<div class="row">
				<div class="form-group col-md-2">
					<label for="numero_chamado" >Número Chamado: </label>
					<form:errors path="numero_chamado" style="color:red" />
					<form:input  class ="form-control" placeholder="Número do Chamado" path="numero_chamado"/>
				</div>
				<div class="form-group col-md-7">
					<label for="numero_chamado" >Título Chamado: </label>
					<form:errors path="titulo" style="color:red" />
					<form:input  class ="form-control" placeholder="Título do Chamado" path="titulo"/>
				</div>
				<div class="form-group col-md-3">
					<label for="sistema" >Sistema: </label>			
		            <select class="form-control form-control-lg" name="idSistema">
		  				<option>Escolha Sistema</option>
		  				<c:forEach items="${sistemas}" var="sistema">
		  					<option value="${sistema.id}">${sistema.nome}</option>	
		  				</c:forEach>
					</select>				
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-3">
					<label for="sistema-aberto_funcionario" >Aberto Por: </label>
					<form:errors path="aberto_funcionario" style="color:red"/>
					<form:input class ="form-control" placeholder="Funcionário do Banco" path="aberto_funcionario" />
				</div>				
	            <div class="form-group col-md-3">
	            	<label for="funcionario-responsavel" >Funcionario Responsável: </label>
		            <select class="form-control form-control-lg" name="idFuncionario">
		  				<option>Escolha Funcionario</option>
		  				<c:forEach items="${funcionarios}" var="funcionario">
		  					<option value="${funcionario.id}">${funcionario.nome}</option>
		  				</c:forEach>
					</select>				
				</div>					
	            <div class="form-group col-md-3">
	                <label for="sistema-descricao" >Data Abertura: </label>
	                <form:errors path="data_abertura" style="color:red"/>
	                <div class="input-group">              
						<div class="input-group-addon">--/--/----</div>
						<form:input class ="form-control" placeholder="Data Abertura" path="data_abertura"/>
					</div>
	            </div>
	            <div class="form-group col-md-3">
	            	<label for="sistema-descricao" >Tipo Chamado: </label>
		            <select class="form-control form-control-lg" name="tipoChamado">
		            	<option>Tipo Chamado</option>
		            	<c:forEach items="${tipos}" var="tipoChamado">
		  					<option value="${tipoChamado}">${tipoChamado.descricao}</option>	
		  				</c:forEach>
					</select>				
				</div>
			</div>
			
			<div class="form-group">
				<label for="sistema-descricao" >Descrição: </label>
				<form:errors path="descricao" style="color:red"/>
				<form:textarea class ="form-control" placeholder="Descricao Chamado" path="descricao" />
			</div>
			
            
           <div class="form-group">
				<div class="custom-file">
					<label for="sql_usado" >Scripts Utilizado:</label>
					<form:errors path="scripts" style="color:red"/>
					<form:textarea class ="form-control" placeholder="Cole o SQL Utilizado" path="scripts" name="scripts"/>
					<br>
					<div class="Linha">
						<!-- RECEBE DIVS DINAMICAS -->
					</div>
					<a id='criarLinha' class="btn btn-primary">Script +</a>					
				</div>
			</div>
            
            <div class="row">
            	<div class="form-group col-md-6">
					<label for="sistema-descricao" >Resolução Técnica: </label>
					<form:errors path="resolucao" style="color:red"/>
					<form:textarea class ="form-control" placeholder="Resolução do Chamado" path="resolucao" />
				</div>
				<div class="form-group col-md-6">
					<label for="sistema-descricao" >Anotações de Alteração: </label>
					<form:errors path="notacoes_alteracao" style="color:red"/>
					<form:textarea class ="form-control" placeholder="Anotações de Alterações" path="notacoes_alteracao" />
				</div>
            </div>
			
			
			<button class="btn btn-primary" type="submit">Cadastro</button>
			<a class="btn btn-primary" href="/chamados/chamados/">Voltar</a>
		</form:form>
	</div>
<c:import url="../../footer.jsp" />