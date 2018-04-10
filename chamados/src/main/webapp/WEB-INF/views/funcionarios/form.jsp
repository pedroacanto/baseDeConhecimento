<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:import url="../../header.jsp" />
	<div class="container">
		<form:form action="/chamados/funcionarios" method="post" commandName="funcionario">
			<div class="form-group">
				<label for="funcionario-nome" >Nome: </label>
				<form:errors path="nome" style="color:red" />
				<form:input  class ="form-control" placeholder="Nome Funcionário" path="nome"/>
			</div>
			<div class="form-group">
				<label for="funcionario-email" >Email: </label>
				<form:errors path="email" style="color:red"/>
				<div class="input-group">
					<div class="input-group-addon">@</div>
					<form:input class ="form-control" placeholder="Email Funcionário" path="email"/>
				</div>
			</div>
			<div class="form-group">
				<label for="funcionario-funcao" >Função: </label>
				<form:errors path="funcao" style="color:red"/>
				<form:input class ="form-control"  placeholder="Função Funcionário" path="funcao" />
			</div>
			
			<div class="form-group">
				<label for="funcionario-sistemas">Atribuir Sistemas: </label>
				<input type="checkbox" id="checkTodos" name="checkTodos">Marcar/Desmarcar Todos
				<div>
					<table class="table table-bordered" style="width: 50%">
					  	<tbody>			
							<c:forEach items="${sistemas}" var="sistema">
								<input type="hidden" name="id" value="${sistema.id}">
								<tr>
									<td>
										<div class="form-check">
										  <input class="form-check-input" type="checkbox" 
										         value="${sistema.id}" id="defaultCheck1" name="idSistema">
										  <label class="form-check-label" for="defaultCheck1">
										  		${sistema.nome}
										  </label>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
									
			<button class="btn btn-primary" type="submit">Cadastro</button>
			<a class="btn btn-primary" href="/chamados/funcionarios/">Voltar</a>
		</form:form>
	</div>
<c:import url="../../footer.jsp" />