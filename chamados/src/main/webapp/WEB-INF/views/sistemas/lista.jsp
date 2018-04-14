<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../../header.jsp" />

<div class="container">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="/chamados/sistemas/">Gerência Sistemas</a>
		    </div>
		    
			<!-- Collect the nav links, forms, and other content for toggling -->
		   <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li><a href="/chamados/sistemas/cadastrar">Cadastrar</a></li>
		      </ul>
		      <form class="navbar-form navbar-right" 
		            action="/chamados/sistemas/busca" method="get">
		        <div class="form-group">
		          <input type="text" class="form-control" name="nome" placeholder="Nome Sistema">
		        </div>
		        <button type="submit" class="btn btn-default">Buscar</button>
		      </form>
		    </div>
		</div>	
	</nav>
	
	<c:if test="${sucesso != null}">
		<div class="alert alert-success">
			<strong>${sucesso}</strong>
		</div>
	</c:if>
	
	<c:if test="${erro != null}">
		<div class="alert alert-danger">
			<strong>${erro}</strong>
		</div>
	</c:if>		
	<div>
		<table class="table table-bordered">
			<thead>
			    <tr>
			      <th scope="col">Nome</th>
			      <th scope="col">Descrição</th>
			      <th scope="col">Editar</th>
			      <th scope="col">Excluir</th>
			    </tr>
		  	</thead>
		  	<tbody>			
				<c:forEach items="${sistemas}" var="sistema">
					<input type="hidden" name="id" value="${sistema.id}">
					<tr>
						<td>${sistema.nome}</td>
						<td>${sistema.descricao}</td> 
						<td>
							<a type="button" class="btn btn-primary" 
							   href="/chamados/sistemas/editar/${sistema.id}">Editar</a>
						</td> 
						<td> 
						    <button type="button" class="btn btn-primary" 
						             data-toggle="modal" data-target="#exampleModalCenter"
						             data-entidade="sistema"
						             data-sistema="${sistema.nome}"
						             data-sistemaid="${sistema.id}"
						             id="excluir">Excluir
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<!-- Modal --> 
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" 
            aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h3 class="modal-title" id="exampleModalLongTitle"></h3>
      </div>
      <div class="modal-body">
        	Deseja realmente continuar com a operação?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
        <a type="button" class="btn btn-danger btn-ok">Confirmar</a>
      </div>
    </div>
  </div>
</div>

<c:import url="../../footer.jsp" />
