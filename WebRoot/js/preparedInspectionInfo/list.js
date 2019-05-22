var moduleUrl = "preparedInspectionInfo";
$(function () {
	 initDatagrid();
	
	 document.getElementById('toolbarSearch').onclick = function() {
		 initDatagrid();
	 };
	 
	 document.getElementById('createInspectionOrder').onclick = function() {
		 var queryParams=queryParamByFormId("toolbarForm");
		 createInspectionOrder(queryParams);
	 };
	 
	 document.getElementById('del').onclick = function() {
		 initDel();
	 };
	 
	
	
	$('#airBottleTypeTool').combobox({
		icons:[{
			iconCls:'icon-combo-clear',
			handler:function(e){
				$(e.data.target).combobox('clear');
			}
		}],
		url : basePath + '/airbottleType/getAllList',
		method : 'get',
		valueField : 'id',
		textField : 'air_bottle_specifications',

	});

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
             {field:'air_bottle_code',title:'气瓶编码',align:'center',
            	formatter:getAirBottleCode},
             {field:'air_bottle_seal_code',title:'气瓶钢印码',align:'center',
            	formatter:getAirBottleSealCode},
             {field:'materialType',title:'气瓶类型',align:'center',
            	formatter:getAirBottleType},
             {field:'air_bottle_producer_name',title:'生产单位',align:'center',
                formatter:getProductionUnitName},
             {field:'check_time',title:'检修时间',align:'center',
            	formatter:getCheckTime},
             {field:'next_check_time',title:'下次检修时间',align:'center',
            	formatter:getNextCheckTime},
             {field:'produce_time',title:'生产时间',align:'center',
            	formatter:getProduceTime},
             {field:'remark',title:'备注信息',align:'center',
            	formatter:getRemark},
        	 {field:'warehouseInfo',title:'所属仓库',align:'center',
        		formatter:getWarehouseInfo},
        	 {field:'operationUser',title:'操作员',align:'center',
            	formatter:getOperationUser},
         ]],
         
         loadFilter: function(data){

             if (data.data!=null){
                 return data.data;
             }
             return null;
         }
     });
}

function createInspectionOrder(queryParams) {
	
	$.ajax({
        type : 'post',
        url : basePath+'/'+moduleUrl+'/createInspectionOrder',
        data : queryParams,
        success : function(data) {
       	 	if (data.code == 200) {				
	       		 $.messager.show({
	       			 title: '提示信息',
	       			 msg: '操作成功'
	       		 });
			 }else{
				 $.messager.show({
	       			 title: '提示信息',
	       			 msg: data.msg
	       		 });
			 }
            $("#tb").datagrid("reload");
        }
   });
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
         url : basePath+'/'+moduleUrl+'/delByIds',
         success : function(data) {
        	 if (data.code == 200) {				
        		 $.messager.show({
        			 title: '提示信息',
        			 msg: '操作成功'
        		 });
			 }
             $("#tb").datagrid("reload");
         }
    });
}

function getAirBottleCode(value, rec) {
	if (rec.airBottleInfo != null) {
		return rec.airBottleInfo.air_bottle_code;
	}
	return null;
}

function getAirBottleSealCode(value, rec) {
	if (rec.airBottleInfo != null) {
		return rec.airBottleInfo.air_bottle_seal_code;
	}
	return null;
}


function getProductionUnitName(value, rec) {
	if (rec.airBottleInfo != null) {
		return rec.airBottleInfo.productionUnit.production_unit_name;
	}
	return null;
}

function getRemark(value, rec) {
	if (rec.airBottleInfo != null) {
		return rec.airBottleInfo.remark;
	}
	return null;
}

function getAirBottleType(value, rec) {
	if(rec.airBottleInfo != null && rec.airBottleInfo.airBottleType != null){
		return rec.airBottleInfo.airBottleType.air_bottle_specifications;
	}
	return null;
}

function getOperationUser(value, rec) {
	if(rec.operationUser != null){
		return rec.operationUser.full_name;
	}
	return null;
}

function getWarehouseInfo(value, rec) {
	if(rec.warehouseInfo != null){
		return rec.warehouseInfo.warehouse_name;
	}
	return null;
}

function getCheckTime(value, rec) {
    if (rec.airBottleInfo != null && rec.airBottleInfo.check_time != null) {
        return formatDateByYYMMDD(new Date(rec.airBottleInfo.check_time));
    }
    return null;
}

function getNextCheckTime(value, rec) {
    if (rec.airBottleInfo != null && rec.airBottleInfo.next_check_time != null) {
        return formatDateByYYMMDD(new Date(rec.airBottleInfo.next_check_time));
    }
    return null;
}

function getProduceTime(value, rec) {
    if (rec.airBottleInfo != null && rec.airBottleInfo.produce_time != null) {
        return formatDateByYYMMDD(new Date(rec.airBottleInfo.produce_time));
    }
    return null;
}


function getCreateTime(value, rec) {
    if (rec.createTime != null) {
        return formatDateByYYMMDD(new Date(rec.createTime));
    }
    return null;
}
