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

function setReportTable() {

	var queryParams = queryParamByFormId("toolbarForm");

	$('#tb')
			.datagrid(
					{

						url : basePath + '/moduleConfigurationInfo/getPageList',
						queryParams : queryParams,
						method : 'post',
						showFooter : true,
						remoteSort : false,
						columns : [ [  {
							field : 'module_name',
							title : '模块名称',
							align : 'center',
							width : 100,
							sortable : true
						},{
							field : 'is_open',
							title : '是否开启',
							align : 'center',
							width : 100,
							formatter:getIsOpen,
							sortable : true
						}, ] ],

						onLoadSuccess : function(data) {

						},
						loadFilter: function(data){
							
							if (data.data!=null){
								return data.data;
							}
							return null;
						}


					});

}

function getIsOpen(value,rec){
	
	if(value == 0){
		return "关闭";
	}
	
	if(value == 1){
		return "开启";
	}
	
	return "";
}

$("#open").click(function() {
	initModuleConfigurationState(1);
});

$("#close").click(function() {
	initModuleConfigurationState(0);
});

function initModuleConfigurationState(isOpen){
	
	 var selecteRow = $('#tb').datagrid('getSelected');
		
		if (selecteRow ==null) {
			$.messager.show({
				title : '提示信息',
				msg : '请先选中一条记录'
			});
			return;
		}
		

		$.ajax({
			type : 'post',
			url : basePath+'/moduleConfigurationInfo/editModuleConfigurationInfo?moduleId='+selecteRow.id+'&isOpen='+isOpen,
			success : function(data) {
				if(data.code == 200){
					 $.messager.show({
							title : '提示信息',
							msg : '操作成功'
						});
				    $('#tb').datagrid('reload'); 
				}else{
					$.messager.show({
						title : '提示信息',
						msg : data.msg
					});
				}
			}
		});
}
