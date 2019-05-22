var tb2SelectFlag = -1;
var tb3SelectFlag = -1;
$(function() {
	$('#secondCategory1').combobox({
		icons : [ {
			iconCls : 'icon-combo-clear',
			handler : function(e) {
				$(e.data.target).combobox('clear');
			}
		} ],
		url : basePath + '/secondCategory/getAllList',
		method : 'get',
		valueField : 'id',
		textField : 'second_category_name',

	});

	$('#userRole1').combobox({
		icons : [ {
			iconCls : 'icon-combo-clear',
			handler : function(e) {
				$(e.data.target).combobox('clear');
			}
		} ],
		url : basePath + '/role/getAllList',
		method : 'post',
		valueField : 'id',
		textField : 'name',

	});

	var checkUserIdflag;

	$.extend($.fn.validatebox.defaults.rules, {
		checkUserId : {
			validator : function(value, param) {
				$.ajax({
					async : false,
					cache : false,
					type : 'post',
					url : basePath + '/user/getUser?username=' + value + '&id='
							+ $("#id").val(),
					success : function(data) {
						checkUserIdflag = data;
					}
				});
				return checkUserIdflag;
			},
			message : '账号已存在'
		},
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

	var queryParams = queryParamByFormId("toolbarForm");
	$('#tb').datagrid({
		url : basePath + '/user/getList',
		method : 'get',
		queryParams : queryParams,
	});
});

$("#exportExcel").click(function() {
	outToFile();
});

function outToFile() {
	
	document.getElementById("toolbarForm").action = basePath + '/user/exportAllList';  
	
	document.getElementById("toolbarForm").submit();    
};

var flag;

$("#add").click(function() {
	$("#mydialog").dialog({
		title : "新增员工"
	});
	$("#myform").get(0).reset();

	$("#myform").get(0).reset();
	$("#myform").form("load", {
		'id' : 0,
		'secondCategory.id' : 0,
		'warehouseInfo.id' : 0,
	});

	$('#tb1').datagrid({
		url : basePath + '/role/getAllList',
		columns : [ [ {
			field : 'name',
			title : '角色名称',
			width : 100
		}, {
			field : 'description',
			title : '角色描述',
			width : 150
		} ] ],
		onLoadSuccess : function(data) {
			$(tb1).datagrid("unselectAll");
		}
	});

	$('#tb2').datagrid({
		singleSelect : true,
		url : basePath + '/secondCategory/getAllList',
		columns : [ [ {
			field : 'second_category_name',
			title : '门店名称',
			width : 100
		},

		] ],
		onLoadSuccess : function(data) {
			$(tb2).datagrid("unselectAll");
		},
		onClickRow : function(rowIndex, rowData) {
			if (tb2SelectFlag == rowIndex) {
				$(this).datagrid('unselectRow', rowIndex);
				tb2SelectFlag = -1;
			} else {
				tb2SelectFlag = rowIndex;
			}
		},
	});

	$('#tb3').datagrid({
		singleSelect : true,
		url : basePath + '/warehouseInfo/getAllList',
		columns : [ [ {
			field : 'warehouse_name',
			title : '仓库名称',
			width : 100
		},

		] ],
		onLoadSuccess : function(data) {
			$(tb3).datagrid("unselectAll");
		},
		onClickRow : function(rowIndex, rowData) {
			if (tb3SelectFlag == rowIndex) {
				$(this).datagrid('unselectRow', rowIndex);
				tb3SelectFlag = -1;
			} else {
				tb3SelectFlag = rowIndex;
			}
		},
	});
	
	$("#mydialog").dialog("open");
	flag = 'add';
});

$("#edit")
		.click(
				function() {

					$("#mydialog").dialog({
						title : "修改员工"
					});
					$("#myform").get(0).reset();
					$("#myform").form("load", {
						'secondCategory.id' : 0,
						'warehouseInfo.id' : 0,
					});
					var selectRows = $("#tb").datagrid("getSelections");
					if (selectRows.length != 1) {
						$.messager.show({
							title : '提示信息',
							msg : '只能选择一行记录'
						});
					} else {
						$("#myform").get(0).reset();
						var role;
						$("#myform").form("load", {
							id : selectRows[0].id,
							username : selectRows[0].username,
							password : selectRows[0].password,
							card_code : selectRows[0].card_code,
							full_name : selectRows[0].full_name,
							user_code : selectRows[0].user_code,
							contactNumber : selectRows[0].contactNumber,
						});
						var roleIds = selectRows[0].roleIds;
						$('#tb1')
								.datagrid(
										{
											url : basePath + '/role/getAllList',
											columns : [ [ {
												field : 'name',
												title : '角色名称',
												width : 100
											}, {
												field : 'description',
												title : '角色描述',
												width : 150
											} ] ],
											onLoadSuccess : function(data) {
												if (roleIds != null) {
													var roleIdArry = roleIds
															.split(",");
													$
															.each(
																	data.rows,
																	function(i,
																			value) {
																		for ( var i = 0; i < roleIdArry.length; i++) {
																			if (value.id == roleIdArry[i]) {
																				$(
																						tb1)
																						.datagrid(
																								'selectRow',
																								$(
																										tb1)
																										.datagrid(
																												'getRowIndex',
																												value));
																				break;
																			}
																		}
																	});
												}
											}
										});

						var secondCategory = selectRows[0].secondCategory;
						$('#tb2')
								.datagrid(
										{
											singleSelect : true,
											url : basePath
													+ '/secondCategory/getAllList',
											columns : [ [ {
												field : 'second_category_name',
												title : '门店名称',
												width : 100
											} ] ],
											onLoadSuccess : function(data) {
												if (secondCategory != null) {
													$
															.each(
																	data.rows,
																	function(i,
																			value) {
																		if (value.id == secondCategory) {
																			$(
																					tb2)
																					.datagrid(
																							'selectRow',
																							$(
																									tb2)
																									.datagrid(
																											'getRowIndex',
																											value));
																			tb2SelectFlag = i;
																		}
																	});
												}
											},
											onClickRow : function(rowIndex,
													rowData) {
												if (tb2SelectFlag == rowIndex) {
													$(this).datagrid(
															'unselectRow',
															rowIndex);
													tb2SelectFlag = -1;
												} else {
													tb2SelectFlag = rowIndex;
												}
											},
										});

						
						var warehouseInfo = selectRows[0].warehouseInfo;
						
						$('#tb3')
								.datagrid(
										{
											singleSelect : true,
											url : basePath
													+ '/warehouseInfo/getAllList',
											columns : [ [ {
												field : 'warehouse_name',
												title : '仓库名称',
												width : 100
											} ] ],
											onLoadSuccess : function(data) {
												if (warehouseInfo != null) {
													$
															.each(
																	data.rows,
																	function(i,
																			value) {
																		if (value.id == warehouseInfo) {
																			$(
																					tb3)
																					.datagrid(
																							'selectRow',
																							$(
																									tb3)
																									.datagrid(
																											'getRowIndex',
																											value));
																			tb3SelectFlag = i;
																		}
																	});
												}
											},
											onClickRow : function(rowIndex,
													rowData) {
												if (tb3SelectFlag == rowIndex) {
													$(this).datagrid(
															'unselectRow',
															rowIndex);
													tb3SelectFlag = -1;
												} else {
													tb3SelectFlag = rowIndex;
												}
											},
										});
						
						
						
						$("#mydialog").dialog("open");
						flag = 'edit';
					}

				});

$("#btn2").click(function() {
	$("#mydialog").dialog("close");
});

$("#btn1").click(function() {
	if (!$("#myform").form('validate')) {
		return false;
	}
	var selectRows = $("#tb1").datagrid("getSelections");
	var roldIds = "";
	for ( var i = 0; i < selectRows.length; i++) {
		roldIds += selectRows[i].id + ",";
	}
	$("#roleIds").val(roldIds);

	var selectRowsSecondCategoryNew = $("#tb2").datagrid("getSelections");
	if (selectRowsSecondCategoryNew.length != 0) {
		$("#secondCategoryNew").val(selectRowsSecondCategoryNew[0].id);
	}
	
	
	var selectRowsWarehouseInfoNew = $("#tb3").datagrid("getSelections");
	if (selectRowsWarehouseInfoNew.length != 0) {
		$("#warehouseInfoNew").val(selectRowsWarehouseInfoNew[0].id);
	}

	$.ajax({
		type : 'post',
		data : $('#myform').serialize(),
		url : basePath + '/user/' + flag,
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
			url : basePath + '/user/del',
			success : function(data) {
				$("#tb").datagrid("reload");
			}
		});
	}
});

$("#searchM1CardCode").click(function() {

	var cardCode = document.myApplet.tyBy8();

	$("#myform").form("load", {
		'card_code' : cardCode,
	});

	return true;

});

$("#searchM1CardCodeInToolbarNew").click(function() {

	$("#toolbarForm").form("load", {
		'card_code1' : getCardContentByMiddleware(8,0),
	});

	return true;

});

$("#searchM1CardCodeNew").click(function() {

	$("#myform").form("load", {
		'card_code' : getCardContentByMiddleware(8,0),
	});

	return true;

});


$("#setStoreDeliveryMan").click(function() {

	initStoreDeliveryMan(1);
});

$("#cancelStoreDeliveryMan").click(function() {

	initStoreDeliveryMan(0);
});

function initStoreDeliveryMan(op){
	
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
				
				ids : ids,
				op:op,
			},
			url : basePath + '/user/editStoreDeliveryMan',
			success : function(data) {
				$("#tb").datagrid("reload");
				$.messager.show({
					title : '提示信息',
					msg : '操作成功'
				});
			}
		});
	}
}



$("#setWHDeliveryMan").click(function() {

	initWHDeliveryMan(1);
});

$("#cancelWHDeliveryMan").click(function() {

	initWHDeliveryMan(0);
});

function initWHDeliveryMan(op){
	
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
				
				ids : ids,
				op:op,
			},
			url : basePath + '/user/editWHDeliveryMan',
			success : function(data) {
				$("#tb").datagrid("reload");
				$.messager.show({
					title : '提示信息',
					msg : '操作成功'
				});
			}
		});
	}
}


$("#setSafetyTechnologyDepartment").click(function() {

	initSafetyTechnologyDepartment(1);
});

$("#cancelSafetyTechnologyDepartment").click(function() {

	initSafetyTechnologyDepartment(0);
});

function initSafetyTechnologyDepartment(op){
	
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
				
				ids : ids,
				op:op,
			},
			url : basePath + '/user/editSafetyTechnologyDepartment',
			success : function(data) {
				$("#tb").datagrid("reload");
				$.messager.show({
					title : '提示信息',
					msg : '操作成功'
				});
			}
		});
	}
}


$("#limitLogin").click(function() {
	initLimitLogin(0);
});

$("#noLimitLogin").click(function() {
	initLimitLogin(1);
});


function initLimitLogin(op){
	
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
				
				ids : ids,
				limitLoginFlag:op,
			},
			url : basePath + '/user/editLimitLogin',
			success : function(data) {
				$("#tb").datagrid("reload");
				$.messager.show({
					title : '提示信息',
					msg : '操作成功'
				});
			}
		});
	}
}



$("#clearLimitLogin").click(function() {

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
				
				ids : ids,
			},
			url : basePath + '/user/clearLimitLogin',
			success : function(data) {
				$("#tb").datagrid("reload");
				$.messager.show({
					title : '提示信息',
					msg : '操作成功'
				});
			}
		});
	}

});

function getYesOrNo(value, rec) {
	
	if (value == 0) {
		return "否";
	}

	if (value == 1) {
		return "是";
	}

	return null;
}



function getLimitLogin(value, rec) {

	if (rec.limit_login == 0) {
		return "限制登录";
	}

	if (rec.limit_login == 1) {
		return "不限制登录";
	}

	return null;
}