<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<p align="center">Totvs - 2018</p>
<script src="<c:url value='/resources/js/jquery.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>

<script type="text/javascript">
function mascara(o,f){
    v_obj=o
    v_fun=f
    setTimeout("execmascara()",1)
}
function execmascara(){
    v_obj.value=v_fun(v_obj.value)
}

function mdata(v){
    v=v.replace(/\D/g,"");                    //Remove tudo o que n�o � d�gito
    v=v.replace(/(\d{2})(\d)/,"$1/$2");       
    v=v.replace(/(\d{2})(\d)/,"$1/$2");       
                                             
    v=v.replace(/(\d{2})(\d{2})$/,"$1$2");
    return v;
}


$(document).ready(function(){
	var divPai = $('.Linha');
	var btnCriar = $('#criarLinha');
	var contDivs = 0;
	btnCriar.click(function(){
		contDivs = contDivs + 1;
    	divPai.append("<div class='form-group'><div class='custom-file'></div><textarea class ='form-control' placeholder='Cole o SQL Utilizado' name='script_list'/></div>");
	});
});


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
	   modal.find('.modal-title').text('Excluir Funcion�rio: ' + funcionario)
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
