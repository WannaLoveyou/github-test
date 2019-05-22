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
	
	document.getElementById("toolbarForm").action = basePath + '/saleReport/exportSaleReportInfoByAirBottleTypeInfo';  
	
	document.getElementById("toolbarForm").submit();    
};

function setReportTable() {

	var queryParams = queryParamByFormId("toolbarForm");

	$('#reportTable')
			.datagrid(
					{

						url : basePath + '/saleReport/getSaleReportInfoByAirBottleTypeInfo',
						queryParams : queryParams,
						method : 'post',
						showFooter : true,
						remoteSort : false,
						columns : [ [ {
							field : 'second_category_name',
							title : '门店名称',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'num_for_50KG',
							title : '50KG',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'num_for_15KG',
							title : '15KG',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'num_for_5KG',
							title : '5KG',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'num_for_2KG',
							title : '2KG',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'num_for_total',
							title : '总计',
							align : 'center',
							width : 100,
							sortable : true
						}, ] ],

						onLoadSuccess : function(data) {

							var footers = $('#reportTable').datagrid(
									'getFooterRows');

							$('#reportTable')
									.datagrid(
											'reloadFooter',
											[ {
												second_category_name : '总计',
												num_for_5KG : footers.num_for_5KG,
												num_for_15KG : footers.num_for_15KG,
												num_for_50KG : footers.num_for_50KG,
												num_for_2KG : footers.num_for_2KG,
												num_for_total : footers.num_for_total,
											} ]);

						},


					});

}
