var moduleUrl = "fillCheckInfo";

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
		 $.messager.confirm('是否删除', '删除之后无法恢复，是否删除?', function(r){
			 if (r){
				 del();
			 }
		 });
	 };
	 
	 document.getElementById('sumbitBtn').onclick = function() {
		 sumbit();
	 };
	 
	 document.getElementById('closeBtn').onclick = function() {
		 $("#mydialog").dialog("close");
	 };
	 
	 $("#before_or_after_fill").combobox({
		 data : [{
			 value : 0,
			 text : '充装前检查',
		 },{
			 value : 1,
			 text : '充装后检查',
		 }],
		 valueField : 'value',
		 textField : 'text',
	 });	
});

function initDatagrid(){
	
	 var queryParams=queryParamByFormId("toolbarForm");
	 	 
	 $('#tb').datagrid({
         url: basePath+'/'+moduleUrl+'/getPageList',
         method:'get',
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
             {field:'fill_check_code',title:'检查项目代码',align:'center'},
             {field:'fill_check_description',title:'检查项目描述',align:'center'},
             {field:'before_or_after_fill',title:'检查类型',align:'center',formatter:getBefore_or_after_fill},
         ]],
     });
}

var flag;

function initAdd(){
	
	$('#myform').form('clear');
	$("#myform").form("load",{
		entityId : 0
	});
	$("#mydialog").dialog("open");
	
	flag='add';
}

function initEdit(){
    var selectRows = $("#tb").datagrid("getSelections");
    if(selectRows.length!=1){
        $.messager.show({
            title:'提示信息',
            msg:'请选择一条记录'
        });
        return;
    }

    $('#myform').form('clear');
    $("#myform").form("load",{
    	entityId:selectRows[0].id,
        fill_check_code : selectRows[0].fill_check_code,
        fill_check_description : selectRows[0].fill_check_description,
        before_or_after_fill : selectRows[0].before_or_after_fill,
    });
    $("#mydialog").dialog("open");
        
    flag='edit';
    
}

function del(){
	
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
        	 $.messager.show({
  			   title:'提示信息',
  			   msg: data.code == 200 ? '操作成功' : data.msg,
  		     });
             $("#tb").datagrid("reload");
         }
    });
    
}

function sumbit(){
	if (!$("#myform").form('validate')) {
		$.messager.show({
			title:'提示信息',
			msg:'请填写完整信息'
		});
		return;
	}
	$.ajax({
	   type : 'post',
	   data : $('#myform').serialize(),
	   url : basePath+'/'+moduleUrl+'/'+flag,
	   success : function(data) {
		   $.messager.show({
			   title:'提示信息',
			   msg: data.code == 200 ? '操作成功' : data.msg,
		   });
	       $("#mydialog").dialog("close");
	       $("#tb").datagrid("reload");
	   }
	});
}

function getBefore_or_after_fill(value, rec){
	if (value == 0) {
        return '充装前检查';
    }
	if (value == 1) {
		return '充装后检查';
	}
    return null;
}