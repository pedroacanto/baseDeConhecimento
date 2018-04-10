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
		      <a class="navbar-brand" href="/chamados/funcionarios/">Gerência Funcionário</a>
		    </div>
		    
			<!-- Collect the nav links, forms, and other content for toggling -->
		   <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li><a href="/chamados/funcionarios/form">Cadastrar</a></li>
		      </ul>
		      <form class="navbar-form navbar-right" 
		            action="/chamados/funcionarios/busca" method="get">
		        <div class="form-group">
		          <input type="text" class="form-control" name="nome" placeholder="Nome Funcionário">
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
	<div>
		<table class="table table-bordered">
			<thead>
			    <tr>
			      <th scope="col">Nome</th>
			      <th scope="col">Email</th>
			      <th scope="col">Função</th>
			      <th scope="col">Sistemas</th>
			      <th scope="col">Editar</th>
			      <th scope="col">Excluir</th>
			    </tr>
		  	</thead>
		  	<tbody>			
				<c:forEach items="${funcionarios}" var="funcionario">
					<input type="hidden" name="id" value="${funcionario.id}">
					<tr>
						<td>${funcionario.nome}</td>
						<td>${funcionario.email}</td>
						<td>${funcionario.funcao}</td>
						<td>
							<c:forEach items="${funcionario.sistemas}" var="sistema">
								${sistema.nome}
								<br> 
							</c:forEach>
						</td>
						<td>
							<a type="button" class="btn btn-primary" 
							   href="/chamados/funcionarios/editar/${funcionario.id}">Editar</a>
						</td> 
						<td> 
						    <button type="button" class="btn btn-primary" 
						             data-toggle="modal" data-target="#exampleModalCenter"
						             data-entidade="funcionario"
						             data-funcionario="${funcionario.nome}"
						             data-funcionarioid="${funcionario.id}"
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
