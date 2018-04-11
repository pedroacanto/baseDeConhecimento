<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:import url="../../header.jsp" />
<h2 align="center">Cadastro de Sistemas</h2>
<div class="row">
	<div class="container">
		<form:form action="/chamados/sistemas" method="post" commandName="sistema">
			<div class="form-group">
				<label for="sistema-nome" >Nome: </label>
				<form:errors path="nome" style="color:red" />
				<form:input  class ="form-control" placeholder="Nome Sistema" path="nome"/>
			</div>
			<div class="form-group">
				<label for="sistema-descricao" >Descrição: </label>
				<form:errors path="descricao" style="color:red"/>
				<form:textarea class ="form-control" placeholder="Descricao Sistema" path="descricao" />
			</div>
			
			<button class="btn btn-primary" type="submit">Cadastro</button>
			<a class="btn btn-primary" href="/chamados/sistemas/">Voltar</a>
		</form:form>
	</div>
</div>
<c:import url="../../footer.jsp" />