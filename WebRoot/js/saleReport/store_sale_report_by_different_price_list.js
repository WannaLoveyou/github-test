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
	
	document.getElementById("toolbarForm").action = basePath + '/saleReport/exportSaleReportInfoByDifferentPrice';  
	
	document.getElementById("toolbarForm").submit();    
};

function setReportTable() {

	var queryParams = queryParamByFormId("toolbarForm");

	$('#reportTable')
			.datagrid(
					{

						url : basePath + '/saleReport/getSaleReportInfoByDifferentPrice',
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
							field : 'air_bottle_specifications',
							title : '气瓶规格',
							align : 'center',
							width : 100,
							sortable : true,
							sorter: sortByAirBottleType
						}, {
							field : 'price',
							title : '单价',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'orderBottleNumber',
							title : '销售瓶数',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'orderNumber',
							title : '销售订单数',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'total_amount',
							title : '销售总金额',
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
												air_bottle_specifications : '总计',
												orderBottleNumber : footers.orderBottleNumber,
												orderNumber : footers.orderNumber,
												total_amount : footers.total_amount,
											} ]);

						},


					});

}

function sortByAirBottleType(a,b){
	
	var x = parseInt(a.substring(0,a.indexOf("KG")));
	var y = parseInt(b.substring(0,b.indexOf("KG")));
	
	 return (x > y ? 1 : -1); 
	
}
