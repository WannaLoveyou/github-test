$(function() {

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
	
	document.getElementById("toolbarForm").action = basePath + '/saleReport/exportSaleReportInfo';  
	
	document.getElementById("toolbarForm").submit();    
};

function setReportTable() {

	$('#reportTable')
			.datagrid(
					{
						url:basePath +'/js/gaoNengShow/gas_sale_list.json',
						remoteSort : false,
						columns : [ [            
						{
							field : '交货时间',
							title : '交货时间',
							align : 'center',
							sortable : true
						},{
							field : '交货地址',
							title : '交货地址',
							align : 'center',
							sortable : true
						},{
							field : '客户名称',
							title : '客户名称',
							align : 'center',
							sortable : true
						}, {
							field : '生产企业',
							title : '生产企业',
							align : 'center',
							sortable : true,
						}, {
							field : '商品编码',
							title : '商品编码',
							align : 'center',
							sortable : true
						}, {
							field : '品名规格',
							title : '品名规格',
							align : 'center',
							sortable : true
						}, {
							field : '钢瓶编码',
							title : '钢瓶编码',
							align : 'center',
							sortable : true
						}, {
							field : '生产批号',
							title : '生产批号',
							align : 'center',
							sortable : true
						}, {
							field : '有效期',
							title : '有效期',
							align : 'center',
							sortable : true
						}, {
							field : '单价',
							title : '单价',
							align : 'center',
							sortable : true
						}, {
							field : '数量',
							title : '数量',
							align : 'center',
							sortable : true
						}, {
							field : '金额',
							title : '金额',
							align : 'center',
							sortable : true
						}] ],

					});

}

