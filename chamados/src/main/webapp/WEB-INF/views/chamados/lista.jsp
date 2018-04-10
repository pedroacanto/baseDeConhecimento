<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
		      <a class="navbar-brand" href="/chamados/chamados/">Gerênciar Chamado</a>
		    </div>
		    
			<!-- Collect the nav links, forms, and other content for toggling -->
		   <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li><a href="/chamados/chamados/form">Cadastrar</a></li>
		        <li><a href="">Busca Personalizada</a></li>
		      </ul>
		      <form class="navbar-form navbar-right" 
		            action="/chamados/chamados/busca" method="get">
		        <div class="form-group">
		          <input type="text" class="form-control" name="numero_chamado" placeholder="Número Chamado">
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
				<c:forEach items="${chamados}" var="chamado">
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
</div>

<c:import url="../../footer.jsp" />
