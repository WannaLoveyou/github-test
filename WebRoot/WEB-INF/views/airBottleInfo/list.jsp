<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>气瓶信息</title>

<link href="${contextPath}/styles/index/default.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/styles/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/styles/easyui/themes/icon.css" />
<script type="text/javascript"
	src="${contextPath}/styles/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/uplod/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/easyui/extension/datagrid-detailview.js"></script>


</head>
<body>
	<div id="toolbar">
		<form id="toolbarForm">
			<table>
				<tr>
					<td>创建日期：</td>
					<td><input id="begin_create_time" class="easyui-datebox"
						editable="false" name="begin_create_time" />
					</td>

					<td>至</td>
					<td><input id="end_create_time" class="easyui-datebox"
						editable="false" name="end_create_time" />
					</td>

					<td>气瓶归属</td>
					<td><input id="airBottleBelongTool" name="airBottleBelong.id"
						editable="false" class="easyui-combobox" />
					</td>

					<td>气瓶规格</td>
					<td><input id="airBottleTypeTool" name="airBottleType.id"
						editable="false" class="easyui-combobox" style="width: 180px" />
					</td>
				</tr>
				<tr>
					<td>下次检测日期：</td>
					<td><input id="begin_next_check_time" class="easyui-datebox"
						editable="false" name="begin_next_check_time" />
					</td>

					<td>至</td>
					<td><input id="end_next_check_time" class="easyui-datebox"
						editable="false" name="end_next_check_time" />
					</td>
					<td>出厂编码</td>
					<td><input id="factory_number1" name="factory_number1" /></td>
					<td>二维码瓶</td>
					<td><input id="air_bottle_code_status_tool" name="air_bottle_code_status"
						editable="false" class="easyui-combobox" style="width: 180px" /></td>
				</tr>
				<tr>
					<td>气瓶编码</td>
					<td><input id="air_bottle_code1" name="air_bottle_code1"
						style="width: 150px" /></td>
					<td>气瓶钢印码</td>
					<td><input id="air_bottle_seal_code1"
						name="air_bottle_seal_code1" /></td>
					

					<td colspan="8"><a id="search" href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						<a id="add" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'">新增</a> <a id="del" href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
						<a id="edit" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-edit'">编辑</a> <a id="viewAirBottlePic"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-edit'">查看图片</a> <a id="forceScrap"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel'">强制报废</a> <a
						id="cancelForceScrap" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-undo'">撤销报废</a></td>

				</tr>
				<tr>
					
				</tr>
			</table>
		</form>
	</div>

	<table id="tb" class="easyui-datagrid" toolbar="#toolbar"
		title="气瓶信息管理" style="height:auto;width:auto;">
		<thead>
			<!-- <tr>
			    <th field="ck" checkbox="true"></th>
			    <th data-options="field:'air_bottle_code',width:100">气瓶编码</th>
			     <th data-options="field:'air_bottle_seal_code',width:100">气瓶钢印码</th>
			    <th data-options="field:'airBottleType.air_bottle_specifications',width:100,formatter:getAirBottleInfo">气瓶类型</th>
			    <th data-options="field:'create_time',width:100,formatter:getCreateTime">出厂时间</th>
			    <th data-options="field:'check_time',width:100,formatter:getCheckTime">检修时间</th>
			</tr> -->
		</thead>
	</table>



	<div id="mydialog" class="easyui-dialog" modal="true" closed="true"
		style="width:420px;height:auto;top:50px">
		<center>
			<form id="myform" action="">
				<input type="hidden" name="airBottleId" value="0" />
				<table>
					<tr>
						<td>气瓶编码：</td>
						<td><input name="air_bottle_code" required="true"
							style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>气瓶钢印码：</td>
						<td><input name="air_bottle_seal_code" required="true"
							style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>出厂编码：</td>
						<td><input name="factory_number" required="true"
							style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>气瓶类型：</td>
						<td><input id="airBottleType" name="airBottleType.id"
							class="easyui-combobox" style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>气瓶归属：</td>
						<td><input id="airBottleBelong" name="airBottleBelong.id"
							class="easyui-combobox" style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>设计使用年限：</td>
						<td><input name="use_cycle" required="true"
							style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>容积(L)：</td>
						<td><input name="volume" required="true" style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>气瓶重量(kg)：</td>
						<td><input name="bottle_weight" required="true"
							class="easyui-numberbox" precision=2 style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>生产单位：</td>
						<td><input id="productionUnit" name="productionUnit.id"
							class="easyui-combobox" style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>检修单位：</td>
						<td><input id="detectionUnit" name="detectionUnit.id"
							class="easyui-combobox" style="width:230px;" />
						</td>
					</tr>
					<tr>
						<td>出厂日期：</td>
						<td><input id="produce_time" type="text"
							class="easyui-datebox" editable="false" name="produce_time"
							required="required" style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>检修日期：</td>
						<td><input id="check_time" type="text" class="easyui-datebox"
							editable="false" name="check_time" required="required"
							style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>下次检修日期：</td>
						<td><input id="next_check_time" type="text"
							class="easyui-datebox" editable="false" name="next_check_time"
							required="required" style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>图一：</td>
						<td><input name="uploadfile1" type="file" id="uploadfile1" />
						</td>
					</tr>

					<tr>
						<td>图二：</td>
						<td><input name="uploadfile2" type="file" id="uploadfile2" />
						</td>
					</tr>
					<tr align="center">
						<td colspan="2"><a id="btn1" class="easyui-linkbutton">确认</a>
							<a id="btn2" class="easyui-linkbutton">关闭</a>
						</td>
					</tr>
				</table>
			</form>
		</center>
	</div>


	<div id="viewAirBottlePicDiv" title="查看图片" class="easyui-dialog"
		modal="true" closed="true" style="width:650px;height:450px;top:1px">
		<table>
			<tr>
				<td><img id="photo1" alt="暂时无图片存在" width="300px" height="400px"
					style="background-size: 400px 600px"></img></td>
				<td><img id="photo2" alt="暂时无图片存在" width="300px" height="400px"
					style="background-size: 400px 600px"></img></td>
			</tr>
		</table>
	</div>


	<script>

$(function(){
	$('#airBottleBelongTool').combobox({
		icons : [ {
			iconCls : 'icon-combo-clear',
			handler : function(e) {
				$(e.data.target).combobox('clear');
			}
		} ],
		url : basePath + '/airBottleBelong/getAllList',
		method : 'get',
		valueField : 'id',
		textField : 'blong_name',

	});
	
	$('#airBottleTypeTool').combobox({
		icons:[{
			iconCls:'icon-combo-clear',
			handler:function(e){
				$(e.data.target).combobox('clear');
			}
		}],
		value : "",
		editable : false,
		method:'get',
		url : basePath+'/airbottleType/getAllList',
		valueField : 'id',
		textField : 'air_bottle_specifications',
	});
	
	$("#air_bottle_code_status_tool").combobox({
		icons:[{
			iconCls:'icon-combo-clear',
			handler:function(e){
				$(e.data.target).combobox('clear');
			}
		}],
		 data : [
	         {value:1,description:'二维码瓶',selected:true},
	         {value:2,description:'非二维码瓶'},
		 ],
		 editable : false,
		 valueField : 'value',
		 textField : 'description',
	 });
});

var flag;
$("#search").click(function(){
	var queryParams=queryParamByFormId("toolbarForm"); 
	$('#tb').datagrid({ 
		url: '${contextPath }/airBottleInfo/getPageList',
		method:'get',
		queryParams:queryParams,
		rownumbers:true,
		striped:true,
		fitColumns:true,
		pagination:true,
		pageSize:10,
		pageList:[10,20,50,100],
		nowrap:true,
		toolbar:'#toolbar',
		width:'auto',
		height:500,
		view: detailview,
		
		columns:[[    
			{field:'ck',checkbox:true,}, 
			{field:'cloudId',title:'云平台ID',align:'center'},
			{field:'air_bottle_code',title:'气瓶二维码',align:'center'},
			{field:'air_bottle_seal_code',title:'气瓶钢印码',align:'center'},
			{field:'factory_number',title:'出厂编号',align:'center'},
			{field:'airBottleType.air_bottle_specifications',title:'气瓶规格',align:'center',formatter:getAirBottleSpecifications,},
			{field:'airBottleType.bottle_model',title:'气瓶型号',align:'center',formatter:getBottleModel,},
			{field:'airBottleBelong.blong_name',title:'气瓶归属',align:'center',formatter:getBlongName,},
			{field:'use_cycle',title:'设计使用年限',width:100,align:'center'},
			{field:'productionUnit.production_unit_name',title:'制造单位',align:'center',formatter:getProductionUnit,},
			{field:'detectionUnit.detection_unit_name',title:'检验机构',align:'center',formatter:getDetectionUnit,},
			{field:'produce_time',title:'出厂日期',align:'center',formatter:getProduceTime,},
			{field:'check_time',title:'检验日期',align:'center',formatter:getCheckTime,},
			{field:'next_check_time',title:'下次检验日期',align:'center',formatter:getNextCheckTime,},
			{field:'create_time',title:'创建时间',align:'center',formatter:getCreateTime,},
			{field:'create_people',title:'创建人',align:'center'},
			{field:'state',title:'气瓶使用状态',align:'center',formatter:getState},
			{field:'volume',title:'容积(L)',align:'center',formatter:getVolume},
			{field:'bottle_weight',title:'钢瓶重量(kg)',align:'center',formatter:getBottleWeight},
			{field:'airBottleType.airBottleVariety.air_bottle_variety_name',title:'气瓶品种',align:'center',formatter:getAirBottleVarietyName,},
			{field:'airBottleType.nominal_working_pressure',title:'公称工作压力(MPa)',align:'center',formatter:getNominalWorkingPressure,},
			{field:'airBottleType.water_test_pressure',title:'水压试验压力(MPa)',align:'center',formatter:getWaterTestPressure,},
			{field:'airBottleType.wall_thickness',title:'设计壁厚(mm)',align:'center',formatter:getWallThickness,},
						
		]],detailFormatter:function(index,row){// 注意2
	            return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';  
	     	},
        onExpandRow: function (parentIndex, row) {// 注意3
            $('#ddv-' + parentIndex).datagrid({
                url: basePath + '/bottleProcess/getBottleProcess?bottleCode=' + row.air_bottle_code,
                fitColumns: true,
                singleSelect: true,
                rownumbers: true,
                height: 'auto',
                columns: [[
                    {field: 'time', title: '时间', width: 150, align: 'center', formatter: getTime},
                    {field: 'state', title: '事件', width: 200, align: 'center', formatter: getProcessState},
                    {field: 'operator', title: '操作人', width: 200, align: 'center', formatter: getOperator},
                    {field: 'secondCategory', title: '对应门店', width: 150, align: 'center', formatter: getSecondCategory},
                    {field: 'deliveryMan', title: '对应配送工', width: 150, align: 'center', formatter: getDeliveryMan},
                    {field: 'warehouseInfo', title: '对应仓库', width: 150, align: 'center', formatter: getWarehouseInfo},
                    {field: 'clientInfo', title: '对应客户', width: 150, align: 'center', formatter: getClientInfo},
                ]],
                onResize: function () {
                    $('#tb').datagrid('fixDetailRowHeight', parentIndex);
                },
                onLoadSuccess: function () {
                    setTimeout(function () {
                        $('#tb').datagrid('fixDetailRowHeight', parentIndex);
                    }, 0);
                }

            });
            $('#tb').datagrid('fixDetailRowHeight', parentIndex);
        }
	     	
		/* loadFilter: function(data){
			
			if (data.data!=null){
				return data.data;
			}
			return null;
		} */
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

$("#add").click(function(){
	$("#mydialog").dialog({
	title:"新增气瓶"
	});
	$('#myform').form('clear');
	$("#mydialog").dialog("open");
	flag='add';
	
	$('#airBottleType').combobox({    
		value:"请选择",
		editable:false,
		method:'get',
		url:'${contextPath }/airbottleType/getAllList', 
	    valueField:'id',
	    textField:'air_bottle_specifications'   
	}); 
	
	$('#airBottleBelong').combobox({    
		value:"请选择",
		editable:false,
		method:'get',
		url : basePath + '/airBottleBelong/getAllList',
		method : 'get',
		valueField : 'id',
		textField : 'blong_name', 
	}); 
	
	$('#productionUnit').combobox({    
		value:"请选择",
		editable:false,
		method:'get',
		url:'${contextPath }/productionUnit/getAllList', 
	    valueField:'id',
	    textField:'production_unit_name'   
	}); 
	
	$('#detectionUnit').combobox({    
		value:"请选择",
		editable:false,
		method:'get',
		url:'${contextPath }/detectionUnit/getAllList', 
	    valueField:'id',
	    textField:'detection_unit_name'   
	}); 
});

$("#edit").click(function(){
	$("#mydialog").dialog({
	title:"修改气瓶"
	});
	$('#myform').form('clear');
	var selectRows = $("#tb").datagrid("getSelections");
	if(selectRows.length!=1){
		$.messager.show({
			title:'提示信息',
			msg:'只能选择一行记录'
		});
	}else{
		$("#myform").get(0).reset();
		$("#airBottleType").combobox({    
			value:"请选择",
			editable:false,
			method:'get',
			url:'${contextPath }/airbottleType/getAllList', 
		    valueField:'id',
		    textField:'air_bottle_specifications'   
		});
		$('#airBottleBelong').combobox({    
			value:"请选择",
			editable:false,
			method:'get',
			url : basePath + '/airBottleBelong/getAllList',
			method : 'get',
			valueField : 'id',
			textField : 'blong_name', 
		}); 
		$('#productionUnit').combobox({    
		value:"请选择",
		editable:false,
		method:'get',
		url:'${contextPath }/productionUnit/getAllList', 
	    valueField:'id',
	    textField:'production_unit_name'   
		}); 
		$('#detectionUnit').combobox({    
		value:"请选择",
		editable:false,
		method:'get',
		url:'${contextPath }/detectionUnit/getAllList', 
	    valueField:'id',
	    textField:'detection_unit_name'   
		
		}); 
		var produce_time_value =  new Date(selectRows[0].produce_time);
		var produce_time_str = FormatDate(produce_time_value);
		
		var check_time_value = new Date(selectRows[0].check_time);
		var check_time_str = FormatDate(check_time_value);
		
		var next_check_time_value = new Date(selectRows[0].next_check_time);
		var next_check_time_str = FormatDate(next_check_time_value);
		
		$("#myform").form("load",{
			airBottleId:selectRows[0].id,
			air_bottle_code:selectRows[0].air_bottle_code,
			air_bottle_seal_code:selectRows[0].air_bottle_seal_code,
			factory_number:selectRows[0].factory_number,
			use_cycle:selectRows[0].use_cycle,
			volume:selectRows[0].volume == null?selectRows[0].airBottleType.volume:selectRows[0].volume,
			bottle_weight:selectRows[0].bottle_weight == null?selectRows[0].airBottleType.bottle_weight:selectRows[0].bottle_weight,
			'airBottleType.id':selectRows[0].airBottleType.id,
			'productionUnit.id':selectRows[0].productionUnit == null?null:selectRows[0].productionUnit.id,
			'detectionUnit.id':selectRows[0].detectionUnit == null?null:selectRows[0].detectionUnit.id,
			'airBottleBelong.id':selectRows[0].airBottleBelong  == null?null:selectRows[0].airBottleBelong.id,		
			
			produce_time:produce_time_str,
			check_time:check_time_str,
			next_check_time:next_check_time_str,
		});
		
		$("#produce_time").datebox("setValue",produce_time_str);  
		$("#check_time").datebox("setValue",check_time_str);
		$("#next_check_time").datebox("setValue",next_check_time_str);
		$.parser.parse($("#produce_time"));
		$.parser.parse($("#check_time"));
		$.parser.parse($("#next_check_time"));
		$("#mydialog").dialog("open");
		flag='edit';
	}
	
});

function FormatDate (date) {
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}

$("#btn2").click(function(){
	$("#mydialog").dialog("close");
});


$("#checkBtn").click(function(){
	
	var selectRows = $("#tb").datagrid("getSelections");
	
	if(selectRows.length!=1){
		$.messager.show({
			title:'提示信息',
			msg:'只能选择一行记录'
		});
		return;
	}
	
	$("#checkdialog").dialog({
	title:"检测"
	});
	
	$("#checkdialog").dialog("open");
		
});

function closeCheckDialog(){
	$("#checkdialog").dialog("close");
}


/*
$("#btn1").click(function(){
	$.ajax({
		type : 'post',
		data : $('#myform').serialize(),
		url : '${contextPath}/airBottleInfo/'+flag,
		success : function(data) {
			$("#mydialog").dialog("close");
			$("#tb").datagrid("reload");
		}
	});
});
*/

function serializeNotNull(serStr){
     return serStr.split("&").filter(function(str){return !str.endsWith("=")}).join("&");
    //return serStr.split("&").filter(str => !str.endsWith("=")).join("&");
}

$("#btn1").click(function(){
	$.ajaxFileUpload({
    url : '${contextPath}/airBottleInfo/'+flag + '?' + serializeNotNull($("form").serialize()),
    secureuri : false,
    fileElementId :['uploadfile1','uploadfile2'],
    dataType : 'HTML', // 返回值类型 一般设置为json
    success : function(data, status) // 服务器成功响应处理函数
	{
		$("#mydialog").dialog("close");
		$("#tb").datagrid("reload");
		$.messager.show({
			title:'提示信息',
			msg:'操作成功'
		});
	},
	error : function(data, status, e)// 服务器响应失败处理函数
	{
		alert(e);
	}
});
});



$("#del").click(function(){
	var selectRows = $("#tb").datagrid("getSelections");
	if(selectRows.length<1){
		$.messager.show({
			title:'提示信息',
			msg:'请选择要删除的行数'
		});
	}else{
	
	    var r=confirm("是否确认删除数据");
	    
	    if (r==false){
	    return;
	    }
	     
		var ids="";
		for(var i=0;i<selectRows.length;i++){
			ids+=selectRows[i].id+",";
		}
		$
		.ajax({
			type : 'post',
			data : {ids:ids},
			url : '${contextPath}/airBottleInfo/del',
			success : function(data) {
			    if(data.msg!=null){
			    $.messager.show({
					title:'提示信息',
					msg:data.msg
				});
				}
				$("#tb").datagrid("reload");
			}
		});
	}
});

$("#viewAirBottlePic").click(function(){

    var selectRow = $("#tb").datagrid("getSelected");
	if(selectRow==null){
		$.messager.show({
			title:'提示信息',
			msg:'请选择一条记录'
		});
		return;
	}
	

    document.querySelector('#photo1').src='${viewAirBottleInfoPath}'+selectRow.img1;
    document.querySelector('#photo2').src='${viewAirBottleInfoPath}'+selectRow.img2;


	$("#viewAirBottlePicDiv").dialog("open");
});



$("#forceScrap").click(function(){

    var selectRows = $("#tb").datagrid("getSelections");
	if(selectRows==null){
		$.messager.show({
			title:'提示信息',
			msg:'请选择一条记录'
		});
		return;
	}
	
	var bottleIds="";
    for(var i=0;i<selectRows.length-1;i++){
    	bottleIds+=selectRows[i].id+",";
    }
    bottleIds+=selectRows[selectRows.length-1].id;
	
	$.ajax({
		type : 'post',
		data : {bottleIds:bottleIds},
		url : '${contextPath}/warehouseInventory/forceScrapBottleStorage',
		success : function(data) {
		    if(data.msg!=null){
		    $.messager.show({
				title:'提示信息',
				msg:data.msg
			});
			}else{
			$.messager.show({
				title:'提示信息',
				msg:'操作成功'
			});
			}
			$("#tb").datagrid("reload");
		}
	});
	
});

$("#cancelForceScrap").click(function(){

    var selectRows = $("#tb").datagrid("getSelections");
	if(selectRows==null){
		$.messager.show({
			title:'提示信息',
			msg:'请选择一条记录'
		});
		return;
	}
	
	var bottleIds="";
    for(var i=0;i<selectRows.length-1;i++){
    	bottleIds+=selectRows[i].id+",";
    }
    bottleIds+=selectRows[selectRows.length-1].id;
	
	$.ajax({
		type : 'post',
		data : {bottleIds:bottleIds},
		url : '${contextPath}/warehouseInventory/cancelForceScrapBottleStorage',
		success : function(data) {
		    if(data.msg!=null){
		    $.messager.show({
				title:'提示信息',
				msg:data.msg
			});
			}else{
			$.messager.show({
				title:'提示信息',
				msg:'操作成功'
			});
			}
			$("#tb").datagrid("reload");
		}
	});
	
});

function getState(value, rec) {

	if(rec.isscrap == 1){
		return "报废";
	}
	return "在用";
}


function getCreateTime(value, rec) {

	if (rec.create_time != null) {
		return formatDate(new Date(rec.create_time));
	}
	return null;
}


function getCheckTime(value, rec) {
	if (rec.check_time != null) {
		return new Date(rec.check_time).toLocaleDateString();
	}
	return null;
}

function getProduceTime(value, rec) {
	
	if (rec.produce_time != null) {
		return new Date(rec.produce_time).toLocaleDateString();
	}
	return null;
}

function getNextCheckTime(value, rec) {
	if (rec.next_check_time != null) {
		return new Date(rec.next_check_time).toLocaleDateString();
	}
	return null;
}

function getAirBottleSpecifications(value,rec)
{
	if(rec.airBottleType!=null){
		return rec.airBottleType.air_bottle_specifications;
	}
	return null;
}

function getAirBottleLengthWidth(value,rec)
{
	if(rec.airBottleType!=null){
		return rec.airBottleType.air_bottle_length_width;
	}
	return null;
}

function getTotalWeight(value,rec)
{
	if(rec.airBottleType!=null){
		return rec.airBottleType.total_weight;
	}
	return null;
}

function getBottleModel(value,rec)
{
	if(rec.airBottleType!=null){
		return rec.airBottleType.bottle_model;
	}
	return null;
}

function getNominalWorkingPressure(value,rec)
{
	if(rec.airBottleType!=null){
		return rec.airBottleType.nominal_working_pressure;
	}
	return null;
}

function getWaterTestPressure(value,rec)
{
	if(rec.airBottleType!=null){
		return rec.airBottleType.water_test_pressure;
	}
	return null;
}

function getWallThickness(value,rec)
{
	if(rec.airBottleType!=null){
		return rec.airBottleType.wall_thickness;
	}
	return null;
}

function getAirBottleVarietyName(value,rec)
{
	if(rec.airBottleType!=null && rec.airBottleType.airBottleVariety!=null){
		return rec.airBottleType.airBottleVariety.air_bottle_variety_name;
	}
	return null;
}


function getProductionUnit(value,rec)
{
	if(rec.productionUnit!=null){
		return rec.productionUnit.production_unit_name;
	}
	return null;
}

function getDetectionUnit(value,rec)
{
	if(rec.detectionUnit!=null){
		return rec.detectionUnit.detection_unit_name;
	}
	return null;
}

function getBlongName(value,rec)
{
	if(rec.airBottleBelong!=null){
		return rec.airBottleBelong.blong_name;
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

function getProcessState(value,rec){
    if(rec.state!=null){
	       return rec.state.state_description;
     }
       return null;
  }
  
function getTime(value, rec) {
    if (rec.create_time != null) {
        return formatDate(new Date(rec.create_time));
    }
    return null;
}


function getSecondCategory(value, rec) {
    if (rec.secondCategory != null) {
        return rec.secondCategory.second_category_name;
    }
    return null;
}

function getDeliveryMan(value, rec) {
    if (rec.deliveryMan != null) {
        return rec.deliveryMan.full_name;
    }
    return null;
}

function getWarehouseInfo(value, rec) {
    if (rec.warehouseInfo != null) {
        return rec.warehouseInfo.warehouse_name;
    }
    return null;
}

function getClientInfo(value, rec) {
    if (rec.clientInfo != null) {
        return rec.clientInfo.client_name;
    }
    return null;
}

function getOperator(value,rec){
	
	return rec.operator.full_name;
}


function getVolume(value,rec)
{
	if(rec.volume!=null){
		return rec.volume;
	}

	if(rec.airBottleType!=null){
		return rec.airBottleType.volume;
	}
	return null;
}

function getBottleWeight(value,rec)
{

	if(rec.bottle_weight!=null){
		return rec.bottle_weight;
	}

	if(rec.airBottleType!=null){
		return rec.airBottleType.bottle_weight;
	}
	return null;
}
</script>

</body>
</html>