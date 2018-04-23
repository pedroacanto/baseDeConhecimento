<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:import url="../../header.jsp" />
<div class="container">
	<h2 align="center">Chamado: ${chamado.numero_chamado}</h2>
	<div class="panel panel-default">
	  <div class="panel-heading">
	    	<h3 class="panel-title">Principais Informa��es</h3>
	  </div>
  		<div class="panel-body">
  			<h3>T�tulo: <small>${chamado.titulo}</small></h3>
  			<h4>Aberto por: <small>${chamado.aberto_funcionario}</small></h4>
  			<h4>Data Abertura: <small><fmt:formatDate pattern="dd/MM/yyyy" value="${chamado.data_abertura.time}" /></small></h4>
  			<h4>Tipo Chamado: <small>${chamado.tipo_chamado.descricao}</small></h4>
  			<h4>Sistema: <small>${chamado.sistema.nome}</small></h4>
  			<h4>Fechado Por: <small>${chamado.funcionario_responsavel.nome}</small></h4>
  		</div>
	</div>
	<div class="panel panel-default">
	  <div class="panel-heading">
	    	<h3 class="panel-title">Descri��o do Chamado</h3>
	  </div>
  		<div class="panel-body">
    		${chamado.descricao}
  		</div>
	</div>
	<div class="panel panel-default">
	  <div class="panel-heading">
	    	<h3 class="panel-title">Resolu��o</h3>
	  </div>
  		<div class="panel-body">
    		${chamado.resolucao}
  		</div>
	</div>
	<c:choose>
		<c:when test="${scripts.size() > 0}">
			<c:forEach items="${scripts}" var="script">
				<div class="panel panel-default">
		  			<div class="panel-heading">
		    			<h3 class="panel-title">SQL Utilizado</h3>
		  			</div>
	  				<div class="panel-body">
	  					${script.script_usado}
	  				</div>
				</div>	
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="alert alert-warning" role="alert">N�o foi enviado scripts para o chamado!</div>	
		</c:otherwise>			
	</c:choose>
	<a class="btn btn-primary" href="/chamados/chamados/">Voltar</a>		
</div>
<c:import url="../../footer.jsp" />