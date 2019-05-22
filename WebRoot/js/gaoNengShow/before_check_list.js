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
						url:basePath +'/js/gaoNengShow/before_check_list.json',
						remoteSort : false,
						columns : [ [            
						{
							field : '检查日期',
							title : '检查日期',
							align : 'center',
							sortable : true
						},{
							field : '充装介质',
							title : '充装介质',
							align : 'center',
							sortable : true
						},{
							field : '原始编号',
							title : '原始编号',
							align : 'center',
							sortable : true
						}, {
							field : '有效检验期',
							title : '有效检验期',
							align : 'center',
							sortable : true,
						}, {
							field : '制造单位',
							title : '制造单位',
							align : 'center',
							sortable : true
						}, {
							field : '原始标记充装介质',
							title : '原始标记充装介质',
							align : 'center',
							sortable : true
						}, {
							field : '外表面颜色',
							title : '外表面颜色',
							align : 'center',
							sortable : true
						}, {
							field : '外表面有无裂痕、严重、腐蚀、损伤、明显变形',
							title : '外表面有无裂痕、严重、腐蚀、损伤、明显变形',
							align : 'center',
							sortable : true
						}, {
							field : '外表是否沾染油污或其他可燃物',
							title : '外表是否沾染油污或其他可燃物',
							align : 'center',
							sortable : true
						}, {
							field : '安全附件是否齐全',
							title : '安全附件是否齐全',
							align : 'center',
							sortable : true
						}, {
							field : '瓶口螺纹',
							title : '瓶口螺纹',
							align : 'center',
							sortable : true
						}, {
							field : '警示标签',
							title : '警示标签',
							align : 'center',
							sortable : true
						}, {
							field : '自有',
							title : '自有',
							align : 'center',
							sortable : true
						}, {
							field : '托管',
							title : '托管',
							align : 'center',
							sortable : true
						}, {
							field : '禁充',
							title : '禁充',
							align : 'center',
							sortable : true
						}, {
							field : '可充',
							title : '可充',
							align : 'center',
							sortable : true
						}, {
							field : '备注',
							title : '备注',
							align : 'center',
							sortable : true
						} ] ],

					});

}

