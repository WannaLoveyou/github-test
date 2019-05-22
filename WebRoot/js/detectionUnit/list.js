var moduleUrl = "detectionUnit";

$(function () {
	 initDatagrid();	
	 
	 document.getElementById('toolbarSearch').onclick = function() {
		 initDatagrid();
	 };
	 
	 document.getElementById('add').onclick = function() {
		 initAdd();
	 };
	 
	 document.getElementById('edit').onclick = function() {
		 initEdit();
	 };
	 
	 document.getElementById('del').onclick = function() {
		 initDel();
	 };
	 
	 document.getElementById('sumbitBtn').onclick = function() {
		 initSumbit();
	 };
	 
	 document.getElementById('closeBtn').onclick = function() {
		 $("#mydialog").dialog("close");
	 };
});

function initDatagrid(){
	
	 var queryParams=queryParamByFormId("toolbarForm");
	 	 
	 $('#tb').datagrid({
         url: basePath+'/'+moduleUrl+'/getPageList',
         method:'post',
         queryParams:queryParams,
         pagination:true,
         pageSize:20,
         pageList:[20,50,100,200],
         rownumbers:true,
         striped:true,
         fitColumns:true,
         toolbar: '#toolbar',
         width:'auto',
         height:'auto',

         columns:[[
             {field:'ck',checkbox:true},
             {field:'detection_unit_code',title:'检测单位编号',align:'center',width:100},
             {field:'detection_unit_name',title:'检测单位名称',align:'center',width:100},
         ]],

     });
}

var flag;

function initAdd(){
	
	$('#myform').form('clear');
	$("#mydialog").dialog("open");
	$("#myform").form("load",{
        id : 0,
    });
	flag='add';
}

function initEdit(){

    $('#myform').form('clear');

    var selectRows = $("#tb").datagrid("getSelections");
    if(selectRows.length!=1){
        $.messager.show({
            title:'提示信息',
            msg:'请选择一条记录'
        });
        return;
    }

    $("#myform").form("load",{
        id:selectRows[0].id,
        detection_unit_code:selectRows[0].detection_unit_code,
        detection_unit_name:selectRows[0].detection_unit_name,
    });
        
    $("#mydialog").dialog("open");
        
    flag='edit';
    
}

function initDel(){
	
	var selectRows = $("#tb").datagrid("getSelections");
    if(selectRows.length<1){
        $.messager.show({
            title:'提示信息',
            msg:'请选择要删除的记录'
        });
        return;
    }
    
    
    var ids="";
    for(var i=0;i<selectRows.length-1;i++){
        ids+=selectRows[i].id+",";
    }
    ids+=selectRows[selectRows.length-1].id;
    
    $.ajax({
         type : 'post',
         data : {ids:ids},
         url : basePath+'/'+moduleUrl+'/del',
         success : function(data) {
             $("#tb").datagrid("reload");
         }
    });
    
}

function initSumbit(){
	
	$.ajax({
	   type : 'post',
	   data : $('#myform').serialize(),
	   url : basePath+'/'+moduleUrl+'/'+flag,
	   success : function(data) {
	       $("#mydialog").dialog("close");
	       $("#tb").datagrid("reload");
	   }
	});
}