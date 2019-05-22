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
			+ '/deliveryManDeliveryFeeReport/exportDeliveryManDeliveryFeeReportInfo';

	document.getElementById("toolbarForm").submit();
};

function setReportTable() {

	var queryParams = queryParamByFormId("toolbarForm");

	$('#reportTable')
			.datagrid(
					{

						url : basePath
								+ '/deliveryManDeliveryFeeReport/getDeliveryManDeliveryFeeReportInfo',
						queryParams : queryParams,
						method : 'post',
						showFooter : true,
						remoteSort : false,
						columns : [ [ {
							field : 'delivery_man_full_name',
							title : '送气工',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'air_bottle_specifications',
							title : '气瓶规格',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'order_num',
							title : '订单数',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'order_bottle_num',
							title : '订单气瓶数',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'delivery_fee',
							title : '送气费',
							align : 'center',
							width : 100,
							sortable : true
						}, {
							field : 'floor_subsidies_money',
							title : '楼层补贴',
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
												order_num :  footers.order_num,
												order_bottle_num :  footers.order_bottle_num,
												delivery_fee : footers.delivery_fee,
												floor_subsidies_money : footers.floor_subsidies_money,
											} ]);

						},

					});

}
