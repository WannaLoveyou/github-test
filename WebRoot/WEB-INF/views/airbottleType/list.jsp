<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>气瓶类型</title>
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



</head>
<body>
	<div id="toolbar">
		<table>
			<tr>
				<td><a id="add" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add'">新增</a> <a id="del" href="#"
					class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
					<a id="edit" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-edit'">编辑</a>
					<a id="editSpecial" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-edit'">编辑（特殊）</a></td>
			</tr>
		</table>
	</div>

	<table id="tb" class="easyui-datagrid" toolbar="#toolbar"
		title="气瓶类型管理" style="height:auto;width:2000px;"
		data-options="fitColumns:true,striped:true,nowrap:true,rownumbers:true,
		url:'${contextPath }/airbottleType/getPageList',method:'get',pagination:true,pageSize:5,pageList:[5,10,15,20]">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'air_bottle_specifications',align:'center'">气瓶规格</th>
				<th data-options="field:'air_bottle_type_code',align:'center'">气瓶类型编码</th>
				<th data-options="field:'measurement_unit',align:'center'">计量单位</th>
				<th data-options="field:'price',align:'center'">价格</th>
				<th data-options="field:'weixin_discounty_fee',align:'center'">微信价格</th>
				<th data-options="field:'delivery_fee',align:'center'">送气费</th>
				<th data-options="field:'inspection_fee',align:'center'">检测费</th>
				<th data-options="field:'supply_delivery_fee',align:'center'">免运费应补运费</th>
				<th
					data-options="field:'weixin_buy',align:'center',formatter:getWeiXinBuy">是否允许微信购买</th>
				<th data-options="field:'air_bottle_product_name',align:'center'">气瓶产品名称</th>
				<th data-options="field:'has_gas_weight',align:'center'">含气重量</th>
				<th data-options="field:'air_bottle_length_width',align:'center'">气瓶直径</th>
				<th data-options="field:'volume',align:'center'">容积(L)</th>
				<th data-options="field:'bottle_weight',align:'center'">气瓶重量(kg)</th>
				<th data-options="field:'total_weight',align:'center'">含气总重(kg)</th>
				<th data-options="field:'airBottleVariety',align:'center',formatter:getAirBottleVariety">气瓶品种</th>
				<th data-options="field:'bottle_model',align:'center'">气瓶型号</th>
				<th data-options="field:'nominal_working_pressure',align:'center'">公称工作压力(MPa)</th>
				<th data-options="field:'water_test_pressure',align:'center'">水试验压力(MPa)</th>
				<th data-options="field:'wall_thickness',align:'center'">设计壁厚(mm)</th>
				<th data-options="field:'inspectionSystemCode',align:'center',formatter:getInspectionSystemCode">气瓶类型（检测站）</th>
				<th data-options="field:'publicServiceMaterialType',align:'center',formatter:getPublicServiceMaterialType">公共服务平台气瓶类型</th>				
				<th data-options="field:'fillingMedium',align:'center',formatter:getFillingMedium">充装介质</th>				
				<th data-options="field:'default_detection_cycle',align:'center'">默认检测周期</th>				
				<th data-options="field:'default_use_cycle',align:'center'">默认设计使用年限</th>				
			</tr>
		</thead>
	</table>

	<div id="mydialog" class="easyui-dialog" title="新增气瓶类型" modal="true"
		closed="true" style="width:600px;height:500px;top:30px">
		<center>
			<form id="myform" action="">
				<input type="hidden" name="airBottleTypeId" value="0" />
				<table>
					<tr>
						<td>气瓶规格：</td>
						<td><input name="air_bottle_specifications" required="true" />
						</td>
					</tr>
					<tr>
						<td>气瓶类型编码：</td>
						<td><input name="air_bottle_type_code" required="true" />
						</td>
					</tr>
					<tr>
						<td>计量单位：</td>
						<td><input name="measurement_unit" required="true" />
						</td>
					</tr>

					<tr>
						<td>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</td>
						<td><input name="price" required="true" />
						</td>
					</tr>
					<tr>
						<td>微信价格：</td>
						<td><input name="weixin_discounty_fee" required="true" /></td>
					</tr>
					<tr>
						<td>送&nbsp;气&nbsp;费：</td>
						<td><input name="delivery_fee" required="true" />
						</td>
					</tr>

					<tr>
						<td>检&nbsp;测&nbsp;费：</td>
						<td><input name="inspection_fee" required="true" />
						</td>
					</tr>

					<tr>
						<td>免运费应补运费：</td>
						<td><input name="supply_delivery_fee" required="true" /></td>
					</tr>

					<tr>
						<td>是否允许微信购买：</td>
						<td><input id="weixin_buy_combobox" name="weixin_buy"
							required="true" />
						</td>
					</tr>

					<tr>
						<td>气瓶产品名称(微信显示)：</td>
						<td><input name="air_bottle_product_name" style="width:180px;" />
						</td>
					</tr>
					
					<tr>
						<td>含气重量(微信显示)：</td>
						<td><input name="has_gas_weight" style="width:180px;" />
						</td>
					</tr>

					<tr>
						<td>气瓶直径(微信显示)：</td>
						<td><input name="air_bottle_length_width"  style="width:180px;" />
						</td>
					</tr>

					<tr>
						<td>容积(L)(充装记录/省云平台)：</td>
						<td><input name="volume" />
						</td>
					</tr>

					<tr>
						<td>气瓶重量(kg)(充装记录/省云平台)：</td>
						<td><input name="bottle_weight" />
						</td>
					</tr>

					<tr>
						<td>含气总重(kg)(充装记录)：</td>
						<td><input name="total_weight" />
						</td>
					</tr>

					<tr>
						<td>气瓶品种(省云平台)：</td>
						<td><input id="airBottleVariety" name="airBottleVariety.id"
							class="easyui-combobox" style="width:200px;" />
						</td>
					</tr>

					<tr>
						<td>气瓶型号(省云平台)：</td>
						<td><input name="bottle_model" />
						</td>
					</tr>

					<tr>
						<td>公称工作压力(MPa)(省云平台)：</td>
						<td><input name="nominal_working_pressure" />
						</td>
					</tr>

					<tr>
						<td>水试验压力(MPa)(省云平台)：</td>
						<td><input name="water_test_pressure" />
						</td>
					</tr>

					<tr>
						<td>设计壁厚(mm)(省云平台)：</td>
						<td><input name="wall_thickness" />
						</td>
					</tr>
					<tr>
						<td>充装介质：</td>
						<td><input id="fillingMedium" name="fillingMedium.id"
							class="easyui-combobox" style="width:150px;" />
						</td>
					</tr>
					<tr>
						<td>默认检测周期：</td>
						<td><input id="default_detection_cycle" name="default_detection_cycle"
							class="easyui-numberbox" style="width:150px;" />
						</td>
					</tr>
					<tr>
						<td>默认设计使用年限：</td>
						<td><input id="default_use_cycle" name="default_use_cycle"
							class="easyui-numberbox" style="width:150px;" />
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
	
	<div id="editSpecialDialog" class="easyui-dialog" title="编辑（特殊）" modal="true"
		closed="true" style="width:400px;height:auto;top:30px">
		<center>
			<form id="editSpecialForm" action="">
				<input type="hidden" name="airBottleTypeId" value="0" />
				<table>
					<tr>
						<td>气瓶类型(检测站)：</td>
						<td><input id="inspectionSystemCode" name="inspectionSystemCode.id"
							class="easyui-combobox" style="width:150px;" />
						</td>
					</tr>
					<tr>
						<td>公共服务平台气瓶类型：</td>
						<td><input id="publicServiceMaterialType" name="publicServiceMaterialType.id"
							class="easyui-combobox" style="width:150px;" />
						</td>
					</tr>
					<tr align="center">
						<td colspan="2"><a id="submitEditSpecial" class="easyui-linkbutton">确认</a>
							<a id="closeEditSpecial" class="easyui-linkbutton">关闭</a>
						</td>
					</tr>
				</table>
			</form>
		</center>
	</div>
	
	<script>
		var weiXinBuyData = [ [ '1', '允许购买' ], [ '0', '不允许购买' ] ];
		$('#weixin_buy_combobox').combobox({

			data : weiXinBuyData,
			valueField : 0,
			textField : 1,

		});

		$('#airBottleVariety').combobox({
			editable : false,
			method : 'get',
			url : basePath + '/airBottleVariety/getAllList',
			valueField : 'id',
			textField : 'air_bottle_variety_name',
			onLoadSuccess : function() {
			}
		});
		
		$('#inspectionSystemCode').combobox({
			editable : false,
			method : 'get',
			url : basePath + '/inspectionSystemCode/getAllList',
			valueField : 'id',
			textField : 'inspection_system_code_name',
			onLoadSuccess : function() {
			}
		});
		
		$('#publicServiceMaterialType').combobox({
			editable : false,
			method : 'get',
			url : basePath + '/publicServiceMaterialType/getAllList',
			valueField : 'id',
			textField : 'specification',
		});
		
		$('#fillingMedium').combobox({
			editable : false,
			method : 'get',
			url : basePath + '/fillingMedium/getAllList',
			valueField : 'id',
			textField : 'filling_medium_name',
		});

		var flag;

		$("#add").click(function() {
			$("#mydialog").dialog({
				title : "新增气瓶类型"
			});
			$('#myform').form('clear');
			$("#mydialog").dialog("open");
			flag = 'add';
		});

		$("#edit")
				.click(
						function() {
							$("#mydialog").dialog({
								title : "修改气瓶类型"
							});
							$('#myform').form('clear');
							var selectRows = $("#tb").datagrid("getSelections");
							if (selectRows.length != 1) {
								$.messager.show({
									title : '提示信息',
									msg : '请选择一行记录'
								});
							} else {
								$("#myform").get(0).reset();
								$("#myform")
										.form(
												"load",
												{
													airBottleTypeId : selectRows[0].id,
													air_bottle_specifications : selectRows[0].air_bottle_specifications,
													air_bottle_type_code : selectRows[0].air_bottle_type_code,
													measurement_unit : selectRows[0].measurement_unit,
													price : selectRows[0].price,
													delivery_fee : selectRows[0].delivery_fee,
													inspection_fee : selectRows[0].inspection_fee,
													supply_delivery_fee : selectRows[0].supply_delivery_fee,
													weixin_discounty_fee : selectRows[0].weixin_discounty_fee,
													'weixin_buy' : selectRows[0].weixin_buy,
													'has_gas_weight' : selectRows[0].has_gas_weight,
													'air_bottle_product_name' : selectRows[0].air_bottle_product_name,
													'air_bottle_length_width' : selectRows[0].air_bottle_length_width,
													'volume' : selectRows[0].volume,
													'bottle_weight' : selectRows[0].bottle_weight,
													'total_weight' : selectRows[0].total_weight,
													'airBottleVariety.id' : selectRows[0].airBottleVariety == null ? null
															: selectRows[0].airBottleVariety.id,
													'bottle_model' : selectRows[0].bottle_model,
													'nominal_working_pressure' : selectRows[0].nominal_working_pressure,
													'water_test_pressure' : selectRows[0].water_test_pressure,
													'wall_thickness' : selectRows[0].wall_thickness,
													'fillingMedium.id' : selectRows[0].fillingMedium == null ? null 
															: selectRows[0].fillingMedium.id,
													'default_detection_cycle' : selectRows[0].default_detection_cycle,
													'default_use_cycle' : selectRows[0].default_use_cycle,
												});
								$("#mydialog").dialog("open");
								flag = 'edit';
							}

						});
		
		$("#editSpecial").click(function() {
			var selectRows = $("#tb").datagrid("getSelections");
			if (selectRows.length != 1) {
				$.messager.show({
					title : '提示信息',
					msg : '请选择一行记录'
				});
				return;	
			}
			$('#editSpecialForm').form('clear');
			$("#editSpecialForm").form("load",{
				airBottleTypeId : selectRows[0].id,
				'inspectionSystemCode.id' : selectRows[0].inspectionSystemCode == null ? null
						: selectRows[0].inspectionSystemCode.id,
				'publicServiceMaterialType.id' : selectRows[0].publicServiceMaterialType == null ? null 
						: selectRows[0].publicServiceMaterialType.id,
			});
			$("#editSpecialDialog").dialog("open");
		});
		
		$("#closeEditSpecial").click(function() {
			$("#editSpecialDialog").dialog("close");
		});

		$("#submitEditSpecial").click(function() {
			$.ajax({
				type : 'post',
				data : $('#editSpecialForm').serialize(),
				url : '${contextPath}/airbottleType/editSpecial',
				success : function(data) {
					$("#editSpecialDialog").dialog("close");
					$("#tb").datagrid("reload");
				}
			});
		});
		
		$("#btn2").click(function() {
			$("#mydialog").dialog("close");
		});

		$("#btn1").click(function() {
			$.ajax({
				type : 'post',
				data : $('#myform').serialize(),
				url : '${contextPath}/airbottleType/' + flag,
				success : function(data) {
					$("#mydialog").dialog("close");
					$("#tb").datagrid("reload");
				}
			});
		});

		$("#del").click(function() {
			var selectRows = $("#tb").datagrid("getSelections");
			if (selectRows.length < 1) {
				$.messager.show({
					title : '提示信息',
					msg : '请先选中一条记录'
				});
			} else {
				var ids = "";
				for ( var i = 0; i < selectRows.length; i++) {
					ids += selectRows[i].id + ",";
				}
				$.ajax({
					type : 'post',
					data : {
						ids : ids
					},
					url : '${contextPath}/airbottleType/del',
					success : function(data) {
						$("#tb").datagrid("reload");
					}
				});
			}
		});

		function getWeiXinBuy(value, rec) {

			if (rec.weixin_buy == 0) {
				return "不允许购买";
			}

			if (rec.weixin_buy == 1) {
				return "允许购买";
			}

			return null;
		}
		
		function getAirBottleVariety(value, rec){
			if(rec.airBottleVariety != null){
				return rec.airBottleVariety.air_bottle_variety_name;
			}
			return null;
		}
		
		function getInspectionSystemCode(value, rec){
			if(rec.inspectionSystemCode != null){
				return rec.inspectionSystemCode.inspection_system_code_name;
			}
			return null;
		}
		
		function getPublicServiceMaterialType(value, rec){
			if(rec.publicServiceMaterialType != null){
				return rec.publicServiceMaterialType.specification;
			}
			return null;
		}
		
		function getFillingMedium(value, rec){
			if(rec.fillingMedium != null){
				return rec.fillingMedium.filling_medium_name;
			}
			return null;
		}
	</script>

</body>
</html>