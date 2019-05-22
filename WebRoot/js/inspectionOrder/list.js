var moduleUrl = "inspectionOrder";
$(function () {
	
	 initDatagrid();
	 initInspectionDetailsResultTable();
	
	 document.getElementById('toolbarSearch').onclick = function() {
		 initDatagrid();
	 };
	 
	 document.getElementById('sendInspection').onclick = function() {
		 sendInspection();
	 };
	 
	 document.getElementById('refreshInspectionOrder').onclick = function() {
		 refreshInspectionOrder();
	 };
	 
	 document.getElementById('confirmInspectionOrder').onclick = function() {
		 confirmInspectionOrder();
	 };
	 
	 document.getElementById('initInspectionOrderData').onclick = function() {
		 initInspectionOrderData();
	 };
	 
	$('#homeLayout').layout('collapse','east');
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
         height:'500',
	     view: detailview,

         columns:[[
             {field:'ck',checkbox:true},
             {field:'createTime',title:'送检单生成时间',align:'center',
            	formatter:getCreateTime},
    		 {field:'inspectionOrderNumber',title:'送检单号',align:'center',},
    		 {field:'inspectionState',title:'送检状态',align:'center',
    			formatter:getInspectionState},
			 {field:'operationUser',title:'操作员',align:'center',
				formatter:getOperationUser},
	    	{field:'inspectionSystemOrderNumber',title:'检测站系统单号',align:'center',},
	    	{field:'inspectionSystemState',title:'检测站系统订单状态',align:'center',},
	    	{field:'inspectionSystemRefreshTime',title:'检测站系统订单状态刷新时间',align:'center',formatter:getInspectionSystemRefreshTime},
	    	{field:'inspectionOrderInitDataTime',title:'检测单钢瓶数据同步时间',align:'center',formatter:getInspectionOrderInitDataTime},

         ]],detailFormatter:function(index,row){// 注意2
	            return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';  
	     	},
	     	
     	 onExpandRow:function(parentIndex,row){// 注意3
            $('#ddv-'+parentIndex).datagrid({  
            	url:basePath+'/'+moduleUrl+'/getItemsInOrder?orderId=' + row.id,
                fitColumns:true,
                singleSelect:true,
                rownumbers:true,
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
		             {field:'check_time',title:'检修日期',align:'center',
		            	formatter:getCheckTime},
		             {field:'next_check_time',title:'下次检修日期',align:'center',
		            	formatter:getNextCheckTime},
		             {field:'produce_time',title:'生产时间',align:'center',
		            	formatter:getProduceTime},
		             {field:'remark',title:'备注信息',align:'center',
		            	formatter:getRemark},
				     {field:'checkDate',title:'检测时间(检测站)',align:'center',
			            	formatter:getCheckDate},
			         {field:'nextCheckDate',title:'下次检测日期(检测站)',align:'center',
				            	formatter:getNextCheckDate},
				     {field:'lastUseDate',title:'截止使用日期(检测站)',align:'center',
					            	formatter:getLastUseDate},
					 {field:'endUseDate',title:'终止使用日期(检测站)',align:'center',
						            	formatter:getEndUseDate},
			         {field:'newGasNumber',title:'新钢码',align:'center'},
		             {field:'isscrap',title:'是否报废',align:'center'},
		             {field:'scrapText',title:'报废原因',align:'center'},
			         {field:'result',title:'检测结果',align:'center'},
					 {field:'itemid1',title:'查询详情',align:'center',formatter: function(value,row,index){
							var str = '<a onClick="showInspectionDetailsResultTable('+row.id+')" class="showInspectionDetailsResultTableCls" ></a>';  
					        return str;  }},
           		]],
           		
                onResize:function(){  
                    $('#tb').datagrid('fixDetailRowHeight',parentIndex);  
                },
                onLoadSuccess:function(){
                	
		            $('.showInspectionDetailsResultTableCls').linkbutton({text:'',plain:true,iconCls:'icon-search'});
		            
                    setTimeout(function(){
                      $('#tb').datagrid('fixDetailRowHeight',parentIndex);
                    },0);
                },
            });
            
            $('#tb').datagrid('fixDetailRowHeight',parentIndex); 
     	 },
         
         loadFilter: function(data){
        	
             if (data.data!=null){
                 return data.data;
             }
             return null;
         }
     });
}

function showInspectionDetailsResultTable(orderDetailsId){
	
	$.ajax({
		type : 'post',
		data : {
			orderDetailsId :  orderDetailsId
		},
		url:basePath+'/inspectionOrder/queryInspectionOrderDetailsResult?orderDetailsId='+orderDetailsId,
		success : function(data) {
			if(data.code == 200){
				$('#inspectionDetailsResultTable').datagrid('loadData', data.data);  
				$('#homeLayout').layout('expand','east');
			}else{
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}
		}
	});


}

function initInspectionDetailsResultTable(){
	$('#inspectionDetailsResultTable').datagrid({ 
        striped:true,
        fitColumns:true,
        width:'auto',
        height:'auto',
		columns:[[    
			{field:'checkItem',title:'检测项目',width:150,align:'center'},
			{field:'result',title:'检测结果',width:150,align:'center'},
		]],
	});
}

function sendInspection(){
	
	var selectedRow = $('#tb').datagrid('getSelected');
	 
	 if (selectedRow ==null) {
			$.messager.show({
				title : '提示信息',
				msg : '请先选中一条记录'
			});
			return;
	}
	 
 	$.ajax({
		type : 'post',
		data : {
			orderId :  selectedRow.id
		},
		url : basePath+'/inspectionOrder/sendInspection',
		success : function(data) {
			if(data.code == 200){
				$('#tb').datagrid("reload");
				$.messager.show({
					title : '提示信息',
					msg : "操作成功"
				});
			}else{
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}
		}
	});
}


function refreshInspectionOrder(){
	
	var selectedRow = $('#tb').datagrid('getSelected');
	 
	 if (selectedRow ==null) {
			$.messager.show({
				title : '提示信息',
				msg : '请先选中一条记录'
			});
			return;
	}
	 
 	$.ajax({
		type : 'post',
		data : {
			orderId :  selectedRow.id
		},
		url : basePath+'/inspectionOrder/refreshInspectionOrder',
		success : function(data) {
			if(data.code == 200){
				refreshInspectionOrderDetails(selectedRow.id);
				$('#tb').datagrid("reload");
			}else{
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}
		}
	});
}


function refreshInspectionOrderDetails(){
	
	var selectedRow = $('#tb').datagrid('getSelected');
	 
	 if (selectedRow ==null) {
			$.messager.show({
				title : '提示信息',
				msg : '请先选中一条记录'
			});
			return;
	}
	 
 	$.ajax({
		type : 'post',
		data : {
			orderId :  selectedRow.id
		},
		url : basePath+'/inspectionOrder/refreshInspectionOrderDetails',
		success : function(data) {
			if(data.code == 200){
				$.messager.show({
					title : '提示信息',
					msg : "操作成功"
				});
			}else{
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}
		}
	});
}

function confirmInspectionOrder(){
	
	var selectedRow = $('#tb').datagrid('getSelected');
	 
	 if (selectedRow ==null) {
			$.messager.show({
				title : '提示信息',
				msg : '请先选中一条记录'
			});
			return;
	}
	 
 	$.ajax({
		type : 'post',
		data : {
			orderId :  selectedRow.id
		},
		url : basePath+'/inspectionOrder/confirmInspectionOrder',
		success : function(data) {
			if(data.code == 200){
				$('#tb').datagrid("reload");
				$.messager.show({
					title : '提示信息',
					msg : "操作成功"
				});
			}else{
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}
		}
	});
}


function initInspectionOrderData(){
	
	var selectedRow = $('#tb').datagrid('getSelected');
	 
	 if (selectedRow ==null) {
			$.messager.show({
				title : '提示信息',
				msg : '请先选中一条记录'
			});
			return;
	}
	 
 	$.ajax({
		type : 'post',
		data : {
			orderId :  selectedRow.id
		},
		url : basePath+'/inspectionOrder/initInspectionOrderData',
		success : function(data) {
			if(data.code == 200){
				$('#tb').datagrid("reload");
				$.messager.show({
					title : '提示信息',
					msg : "操作成功"
				});
			}else{
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}
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

function getInspectionState(value, rec) {
	if(rec.inspectionState != null){
		return rec.inspectionState.description;
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
        return formatDate(new Date(rec.createTime));
    }
    return null;
}

function getInspectionSystemRefreshTime(value, rec) {
    if (rec.inspectionSystemRefreshTime != null) {
        return formatDate(new Date(rec.inspectionSystemRefreshTime));
    }
    return null;
}

function getInspectionOrderInitDataTime(value, rec) {
    if (rec.inspectionOrderInitDataTime != null) {
        return formatDate(new Date(rec.inspectionOrderInitDataTime));
    }
    return null;
}


function getCheckDate(value, rec) {
    if (rec.checkDate != null) {
        return formatDateByYYMMDD(new Date(rec.checkDate));
    }
    return null;
}

function getNextCheckDate(value, rec) {
    if (rec.nextCheckDate != null) {
        return formatDateByYYMMDD(new Date(rec.nextCheckDate));
    }
    return null;
}

function getLastUseDate(value, rec) {
    if (rec.lastUseDate != null) {
        return formatDateByYYMMDD(new Date(rec.lastUseDate));
    }
    return null;
}

function getEndUseDate(value, rec) {
    if (rec.endUseDate != null) {
        return formatDateByYYMMDD(new Date(rec.endUseDate));
    }
    return null;
}
