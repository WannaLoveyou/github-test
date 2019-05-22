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
	
	$('#airBottleBelongToolId').combobox({
		icons : [ {
			iconCls : 'icon-combo-clear',
			handler : function(e) {
				$(e.data.target).combobox('clear');
			}
		} ],
		value : "",
		editable : false,
		method : 'get',
		url : basePath + '/airBottleBelong/getAllList',
		valueField : 'id',
		textField : 'blong_name',
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

	if (!$("#toolbarForm").form('validate')) {
		$.messager.show({
			title : '提示信息',
			msg : '请填写必填参数'
		});
		return false;
	}
	setReportTable();
});

$("#exportExcel").click(function() {
	outToFile();
});

function outToFile() {

	document.getElementById("toolbarForm").action = basePath
			+ '/clientInventoryStatistics/exportClientInventoryStatisticsReport';

	document.getElementById("toolbarForm").submit();
};

function setReportTable() {

	var queryParams = queryParamByFormId("toolbarForm");

	$('#reportTable')
			.datagrid(
					{

						url : basePath + '/clientInventoryStatistics/getClientInventoryStatisticsReport',
						queryParams : queryParams,
						method : 'post',
						showFooter : true,
						remoteSort : false,
						columns : [ [ {
							field : 'secondCategoryName',
							title : '门店名称',
							align : 'center',
							width : 100,
						}, {
							field : 'clientName',
							title : '客户姓名',
							align : 'center',
							width : 100,
						}, {
							field : 'clientCode',
							title : '客户编码',
							align : 'center',
							width : 100,
						}, {
							field : 'clientTypeName',
							title : '客户类型',
							align : 'center',
							width : 100,
						}, {
							field : 'airBottleTypeName',
							title : '气瓶型号',
							align : 'center',
							width : 100,
						} ,{
							field : 'airBottleBelongName',
							title : '归属单位',
							align : 'center',
							width : 100,
						}, {
							field : 'count',
							title : '持瓶数',
							align : 'center',
							width : 100,
						}] ],

						onLoadSuccess : function(data) {

							var footers = $('#reportTable').datagrid(
									'getFooterRows');

							$('#reportTable')
									.datagrid(
											'reloadFooter',
											[ {
												airBottleBelongName : '总计',
												count : footers.count,
											} ]);

						},

					});

}

