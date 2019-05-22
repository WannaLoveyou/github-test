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
	
	document.getElementById("toolbarForm").action = basePath + '/familyCheckReport/exportStoreFamilyCheckInfo';  
	
	document.getElementById("toolbarForm").submit();    
};

function setReportTable() {

	var queryParams = queryParamByFormId("toolbarForm");

	$('#reportTable')
			.datagrid(
					{

						url : basePath + '/familyCheckReport/getStoreFamilyCheckInfo',
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
							field : 'check_client_num',
							title : '已检客户数量',
							align : 'center',
							width : 100,
							sortable : true,
							sorter: true
						}, {
							field : 'total_client_num',
							title : '总客户数量',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'check_per',
							title : '已检比例',
							align : 'center',
							width : 100,
							sortable : true,
							formatter : getCheckPer
						} ] ],

						onLoadSuccess : function(data) {

							var footers = $('#reportTable').datagrid(
									'getFooterRows');

							$('#reportTable')
									.datagrid(
											'reloadFooter',
											[ {
												second_category_name : '总计',
												check_client_num : footers.check_client_num,
												total_client_num : footers.total_client_num,
												check_per : footers.check_per,
											} ]);

						},


					});

}

function getCheckPer(value, rec) {

	if (rec.getBottleNum == 0) {
		return 0.0 + "%";
	}
	var str = Number((rec.check_per * 100)).toFixed(2);
	return str += "%";
}

