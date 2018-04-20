<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="../../header.jsp" />
<h2 align="center">Busca Personalizada</h2>
	<div class="container">
		<div class="jumbotron jumbotron-fluid" style="padding-top: 20px; padding-bottom: 20px;">
			<form:form action="/chamados/chamados/buscaAvancada" method="post" modelAttribute="chamado">
				<div class = "row">
					<div class="form-group col-md-2">
					<label for="numero_chamado">Número Chamado: </label>
						<form:input  class ="form-control" placeholder="Nº do Chamado" path="numero_chamado"/>
					</div>
					<div class="form-group col-md-7">
						<label for="numero_chamado">Título Chamado: </label>
						<form:input  class ="form-control" placeholder="Título do Chamado" path="titulo"/>
					</div>
					<div class="form-group col-md-3">
						<label for="sistema" >Sistema: </label>			
			            <select class="form-control form-control-lg" name="idSistema">
			  				<option value="0">Escolha Sistema</option>
			  				<c:forEach items="${sistemas}" var="sistema">
			  					<option value="${sistema.id}">${sistema.nome}</option>	
			  				</c:forEach>
						</select>				
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-3">
						<label for="sistema-aberto_funcionario" >Aberto Por: </label>
						<form:input class ="form-control" placeholder="Funcionário do Banco" path="aberto_funcionario" />
					</div>
					<div class="form-group col-md-3">
		            	<label for="funcionario-responsavel" >Funcionario Responsável: </label>
			            <select class="form-control form-control-lg" name="idFuncionario">
			  				<option value="0">Escolha Funcionario</option>
			  				<c:forEach items="${funcionarios}" var="funcionario">
			  					<option value="${funcionario.id}">${funcionario.nome}</option>
			  				</c:forEach>
						</select>				
					</div>			
		            <div class="form-group col-md-3">
		                <label for="sistema-descricao" >Data Abertura: </label>
		                <div class="input-group">              
							<div class="input-group-addon">--/--/----</div>
							<form:input class ="form-control" placeholder="Data Abertura" path="data_abertura"/>
						</div>
		            </div>
		            <div class="form-group col-md-3">
		            	<label for="sistema-descricao" >Tipo Chamado: </label>
			            <select class="form-control form-control-lg" name="tipoChamado">
			            	<option value="">Tipo Chamado</option>
			            	<c:forEach items="${tipos}" var="tipoChamado">
			  					<option value="${tipoChamado}">${tipoChamado.descricao}</option>	
			  				</c:forEach>
						</select>				
					</div>					
				</div>
				
				<div class="row">
					<div class="form-group col-md-5">
						<label for="sistema-descricao" >Descrição: </label>
						<form:textarea class ="form-control" placeholder="Descricao Chamado" path="descricao" />
					</div>
					
					<div class="form-group col-md-6">
						<label for="sistema-descricao" >Script: </label>
						<textarea class ="form-control" placeholder="Script Utilizado" name="script"></textarea>
					</div>
					
					<div class="form-group col-md-1">
						<button class="btn btn-primary" type="submit" style="margin-top: 45px;">Buscar</button>
					</div>
				</div>				
						
			</form:form>
		</div>
		<c:if test="${erros != null}">
			<div class="alert alert-warning" role="alert">
				${erros}
			</div>
		</c:if>
		<div>
			<table class="table table-bordered" style="width: 100%">
				<thead>
				    <tr>
				      <th scope="col">Nº Chamado</th>
				      <th scope="col">Título</th>
				      <th scope="col">Data Abertura</th>
				      <th scope="col">Aberto Por</th>
				      <th scope="col">Fechado Por</th>
				      <th scope="col">Sistema</th>
				      <th scope="col">Tipo Chamado</th>
				      <th scope="col">Visualização</th>
				    </tr>
			  	</thead>
			  	<tbody>			
					<c:forEach items="${chamadosBusca}" var="chamado">
						<tr>
							<td>${chamado.numero_chamado}</td>
							<td>${chamado.titulo}</td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${chamado.data_abertura.time}"/></td>
							<td>${chamado.aberto_funcionario}</td>
							<td>${chamado.funcionario_responsavel.nome}</td>
							<td>${chamado.sistema.nome}</td>
							<td>${chamado.tipo_chamado.descricao}</td>
							<td>
								<a type="button" class="btn btn-primary" 
								   href="/chamados/chamados/view/${chamado.id}">Visualizar</a>
							</td> 
						</tr>
					</c:forEach>
				</tbody>
			</table>		
		</div>
		<a class="btn btn-primary" href="/chamados/chamados/">Voltar</a>
	</div>
<c:import url="../../footer.jsp" />	