<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:import url="../../header.jsp" />
	<div class="container">
		<form:form action="/chamados/sistemas" method="post" modelAttribute="sistema">
			<input type="hidden" name="id" value="${sistema.id}">
			<div class="form-group">
				<label>Nome: </label>
				<form:errors path="nome" style="color:red" />
				<form:input  class ="form-control" path="nome" placeholder="Nome Sistema"
				 value="${sistema.nome}"/>
			</div>
			<div class="form-group">
				<label>Descrição: </label>
				<form:errors path="descricao" style="color:red"/>
				<form:textarea class ="form-control"  path="descricao" placeholder="Sistema Descrição"
					value="${sistema.descricao}"/>
			</div>

			<button class="btn btn-primary" type="submit">Editar</button>
			<a class="btn btn-primary" href="/chamados/sistemas/">Voltar</a>
		</form:form>
	</div>
<c:import url="../../footer.jsp" />