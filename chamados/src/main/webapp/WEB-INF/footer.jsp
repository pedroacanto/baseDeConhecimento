<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<p align="center">Totvs - 2018</p>
<script src="<c:url value='/resources/js/jquery.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>

<script type="text/javascript">

$("#checkTodos").click(function(){
    $('input:checkbox').not(this).prop('checked', this.checked);
});

$('#exampleModalCenter').on('shown.bs.modal', function (event) {
   var button = $(event.relatedTarget) 
   
   $('#excluir').trigger('focus')
   
   var entidade = button.data('entidade')
   
   if(entidade == 'funcionario'){
	   var funcionario = button.data('funcionario')
	   var id = button.data('funcionarioid')

	   var modal = $(this)
	   modal.find('.modal-title').text('Excluir Funcionário: ' + funcionario)
	   modal.find('.modal-footer .btn-ok').attr('href','/chamados/funcionarios/excluir/' + id)			   
   }
   
   if(entidade == 'sistema'){
	   var sistema = button.data('sistema')
	   var id = button.data('sistemaid')

	   var modal = $(this)
	   modal.find('.modal-title').text('Excluir Sistema: ' + sistema)
	   modal.find('.modal-footer .btn-ok').attr('href','/chamados/sistemas/excluir/' + id)	 
   }

})
</script>

</body>
</html>
