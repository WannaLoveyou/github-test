$(function() {

	$('#secondCategoryToolId').combobox({
		icons : [ {
			iconCls : 'icon-combo-clear',
			handler : function(e) {
				$(e.data.target).combobox('clear');
			}
		} ],
		url : basePath + '/secondCategory/getMyList',
		method : 'get',
		valueField : 'id',
		textField : 'second_category_name',

	});

	$('#airBottleTypeToolId').combobox({
		icons : [ {
			iconCls : 'icon-combo-clear',
			handler : function(e) {
				$(e.data.target).combobox('clear');
			}
		} ],
		value : "",
		editable : false,
		method : 'get',
		url : basePath + '/airbottleType/getAllList',
		valueField : 'id',
		textField : 'air_bottle_specifications',
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
	formValues = formValues.replace(/\+/g, " "); // g表示对整个字符串中符合条件的都进行替换
	var temp = JSON.stringify(conveterParamsToJson(formValues));
	var queryParam = JSON.parse(temp);
	return queryParam;
}

$("#search").click(function() {
	setReportTable();
});

$("#exportExcel").click(function() {
	outToFile();
});

function outToFile() {
	
    document.getElementById("toolbarForm").action = basePath + '/bottleProcess/exportAirBottleBackLongTimeDetailReportInfo';  
	
	document.getElementById("toolbarForm").submit();    
};

function setReportTable() {

	var queryParams = queryParamByFormId("toolbarForm");
	
	var nowTime = new Date();

	$('#reportTable')
			.datagrid(
					{

						url : basePath + '/bottleProcess/getAirBottleBackLongTimeDetailReportInfo',
			    		view: detailview,
						queryParams : queryParams,
						method : 'post',
						showFooter : true,
						remoteSort : false,
						columns : [ [ {
							field : 'create_time',
							title : '时间',
							align : 'center',
							width : 100,
							sortable : true,
							formatter:getCreateTime
						}, {
							field : 'operator_full_name',
							title : '操作员',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'operator_second_category_name',
							title : '操作员所在门店',
							align : 'center',
							width : 140,
							sortable : true
						},{
							field : 'air_bottle_code',
							title : '气瓶编码',
							align : 'center',
							width : 120,
							sortable : true
						}, {
							field : 'air_bottle_seal_code',
							title : '气瓶钢印码',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'air_bottle_specifications',
							title : '气瓶规格',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'second_category_name',
							title : '对应门店',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'delivery_man_full_name',
							title : '对应配送工',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'warehouse_name',
							title : '对应仓库',
							align : 'center',
							width : 100,
							sortable : true
						},{
							field : 'client_code',
							title : '对应客户编码',
							align : 'center',
							width : 100,
							sortable : true
						},{
							field : 'client_name',
							title : '对应客户名',
							align : 'center',
							width : 100,
							sortable : true
						},{
							field : 'clinet_last_order_time',
							title : '最后订气时间',
							align : 'center',
							width : 100,
							sortable : true,
							formatter:getClinetLastOrderTime
						},{
							field : 'state_description',
							title : '轨迹状态',
							align : 'center',
							width : 100,
							sortable : true
						} ] ],
						rowStyler:function(index,row){   
						var order_cycle =  $("#order_cycle").val();
						if(row.clinet_last_order_time != null && order_cycle != ""){
							var cTime = new Date(row.clinet_last_order_time);
							var diff = nowTime.valueOf() - cTime.valueOf();
							var diff_day = parseInt(diff/(1000*60*60*24));
							if (diff_day >= order_cycle){   
								return 'background-color:#FF4040;';   
							}   
						}
						},
					    detailFormatter:function(index,row){// 注意2
			                return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';  
			            },
			            onExpandRow:function(parentIndex,row){// 注意3
			                $('#ddv-'+parentIndex).datagrid({  
			                	url:basePath+'/bottleProcess/getBottleProcess?bottleCode=' + row.air_bottle_code,
			                    fitColumns:true,
			                    singleSelect:true,
			                    rownumbers:true,
			                    height:'auto',
			                    columns:[[    
			                              {field:'time',title:'时间',width:150,align:'center',formatter:getTime},
			                              {field:'state',title:'事件',width:200,align:'center',formatter:getState},
			                              {field:'operator',title:'操作人',width:200,align:'center',formatter:getOperator},
			                              {field:'secondCategory',title:'对应门店',width:150,align:'center',formatter:getSecondCategory},
			                              {field:'deliveryMan',title:'对应配送工',width:150,align:'center',formatter:getDeliveryMan},
			                              {field:'warehouseInfo',title:'对应仓库',width:150,align:'center',formatter:getWarehouseInfo},
			                              {field:'clientInfo',title:'对应客户',width:150,align:'center',formatter:getClientInfo},
			               		    ]],
			            		    rowStyler:function(index,row){   
			            		    	
			            		    	if(row.state!=null){
			            				if(row.state.id == '100' || row.state.id == '180' || row.state.id == '10000'){
			            						return 'background-color:#6decb9;';   
			            				}}
			            		    },
			                    onResize:function(){  
			                        $('#reportTable').datagrid('fixDetailRowHeight',parentIndex);  
			                    },
			                    onLoadSuccess:function(){
			                        setTimeout(function(){
			                          $('#reportTable').datagrid('fixDetailRowHeight',parentIndex);
			                        },0);
			                      }
		    	  
					});  
		    	   $('#reportTable').datagrid('fixDetailRowHeight',parentIndex); 
		    	   }

					});

}


function getCreateTime(value,rec)
{
	 if(rec.create_time!=null){
	    return formatDate(new Date(rec.create_time));
	 }
	 return null;
}

function getClinetLastOrderTime(value,rec)
{
	 if(rec.clinet_last_order_time!=null){
	    return formatDate(new Date(rec.clinet_last_order_time));
	 }
	 return null;
}

function getState(value,rec){
    if(rec.state!=null){
	       return rec.state.state_description;
     }
       return null;
  }

  function getTime(value,rec){
	    if(rec.create_time!=null){
		   return formatDate(new Date(rec.create_time));
	     }
	     return null;
  }


 function formatDate(now) { 
 		var year=now.getFullYear(); 
 		var M=now.getMonth()+1;  
 	    var MM=(M<10)?"0"+M:M;  
 		var D= now.getDate();  
 	    var DD=(D<10)?"0"+D:D; 
 	    var h=now.getHours();  
 	    var hh=(h<10)?"0"+h:h;  
 	    var m=now.getMinutes();  
 	    var mm=(m<10)?"0"+m:m; 
 	    var s=now.getSeconds();  
 	    var ss=(s<10)?"0"+s:s;  
 		return year+"-"+MM+"-"+DD+" "+hh+":"+mm+":"+ss; 
 }
 
 	function getSecondCategory(value,rec){
 		if(rec.secondCategory!=null){
 			return rec.secondCategory.second_category_name;
 		}
 		return null;
 	}
 	
 	function getDeliveryMan(value,rec){
 		if(rec.deliveryMan!=null){
 			return rec.deliveryMan.full_name;
 		}
 		return null;
 	}
 	
 	function getOperator(value,rec){
 		if(rec.operator!=null){
 			return rec.operator.full_name;
 		}
 		return null;
 	}
 	
 	function getWarehouseInfo(value,rec){
 		if(rec.warehouseInfo!=null){
 			return rec.warehouseInfo.warehouse_name;
 		}
 		return null;
 	}
 	
 	function getClientInfo(value,rec){
 		if(rec.clientInfo!=null){
 			return rec.clientInfo.client_name;
 		}
 		return null;
 	}