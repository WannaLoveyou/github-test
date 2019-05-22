var moduleUrl = "airBottleBelong";

$(function () {
	
	 document.getElementById('search').onclick = function() {
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
	 
	 $("#upload_cloud_platform").combobox({
		 data : [
	         {value:1,description:'是'},
	         {value:0,description:'否'},
		 ],
		 editable : false,
		 valueField : 'value',
		 textField : 'description',
	 });
});


function conveterParamsToJson(paramsAndValues) {  
    var jsonObj = {};  
  
    var param = paramsAndValues.split("&");  
    for ( var i = 0; param != null && i < param.length; i++) {  
        var para = param[i].split("=");  
        jsonObj[para[0]] = para[1];  
    }  
  
    return jsonObj;  
}  
  
function queryParamByFormId(form) {  
    var formValues = $("#" + form).serialize();  
      
    // 关于jquery的serialize方法转换空格为+号的解决方法
    formValues = formValues.replace(/\+/g," ");   // g表示对整个字符串中符合条件的都进行替换
    var temp = JSON.stringify(conveterParamsToJson(formValues));  
    var queryParam = JSON.parse(temp);  
    return queryParam;  
} 

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
             {field:'air_bottle_blong_code',title:'气瓶归属单位编码',align:'center',width:100},
             {field:'blong_name',title:'气瓶归属单位名称',align:'center',width:100},
             {field:'upload_cloud_platform',title:'是否上传云平台',align:'center',width:100,formatter:getUpload_cloud_platform},
         ]],

         loadFilter: function(data){

             if (data.data!=null){
                 return data.data;
             }
             return null;
         }
     });
}

var flag;

function initAdd(){
	
	$('#myform').form('clear');
	$("#mydialog").dialog("open");
	
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
        entityId:selectRows[0].id,
        air_bottle_blong_code:selectRows[0].air_bottle_blong_code,
        blong_name:selectRows[0].blong_name,
        upload_cloud_platform:selectRows[0].upload_cloud_platform,
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
    for(var i=0;i<selectRows.length;i++){
        ids+=selectRows[i].id+",";
    }
    
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

function getUpload_cloud_platform(value){
	if (value == 0) {
		return '否';
	}
	if (value == 1) {
		return '是';
	}
	return null;
}
