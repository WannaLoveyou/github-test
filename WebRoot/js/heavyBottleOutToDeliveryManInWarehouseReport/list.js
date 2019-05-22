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
		onSelect:function(rec){
 	    	$('#deliveryManToolId').combobox({
 	    		icons:[{
 	    			iconCls:'icon-combo-clear',
 	    			handler:function(e){
 	    				$(e.data.target).combobox('clear');
 	    			}
 	    		}],
				url:basePath+'/user/getDeliveryManListBySecondCategoryId?secondCategoryId='+rec.id,
 	    	    method:'get',
 	    	    valueField:'id',    
 	    	    textField:'full_name',
 	    	    
 	    	});  
 	    },
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

	document.getElementById("toolbarForm").action = basePath
			+ '/heavyBottleOutToDeliveryManInWarehouseReport/exportReport';

	document.getElementById("toolbarForm").submit();
};

function setReportTable() {

	var queryParams = queryParamByFormId("toolbarForm");

	$('#reportTable')
			.datagrid(
					{

						url : basePath
								+ '/heavyBottleOutToDeliveryManInWarehouseReport/getListData',
						queryParams : queryParams,
						method : 'post',
						showFooter : true,
						remoteSort : false,
						columns : [ [ {
							field : 'name',
							title : '送气工',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'total',
							title : '数量',
							align : 'center',
							width : 100,
							sortable : true
						} ] ],

						onLoadSuccess : function(data) {

							var footers = $('#reportTable').datagrid(
									'getFooterRows');

							$('#reportTable')
									.datagrid(
											'reloadFooter',
											[ {
												name : '总计',
												total :  footers.total,
											} ]);

						},

					});

}
