
$(function() {

	$('#secondCategoryTool').combobox({
		icons:[{
			iconCls:'icon-combo-clear',
			handler:function(e){
				$(e.data.target).combobox('clear');
			}
		}],
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
	setStoreInventoryTable();
});

function setStoreInventoryTable() {

	var queryParams = queryParamByFormId("toolbarForm");

	$('#storeInventoryTable').datagrid({

		url : basePath + '/storeInventory/getStoreInventoryInfo',
		queryParams : queryParams,
		method : 'post',
		view: detailview,
		columns : [ [ {
			field : 'store_name',
			title : '所属门店',
			align : 'center',
			width : 100
		}, {
			field : 'air_bottle_type_name',
			title : '气瓶规格',
			align : 'center',
			width : 100
		}, {
			field : 'air_bottle_state_description',
			title : '气瓶状态',
			align : 'center',
			width : 100
		}, {
			field : 'inventory_num',
			title : '库存量',
			align : 'center',
			width : 100
		}, ] ], detailFormatter: function (index, row) {// 注意2
            return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
        },
        onExpandRow: function (parentIndex, row) {// 注意3
            $('#ddv-' + parentIndex).datagrid({
                url: basePath + '/storeInventory/getDetailsInventoryInfo?storeId='+row.store_id+'&airBottleStateId='+row.air_bottle_state_id+'&airBottleTypeId='+row.air_bottle_type_id,
                fitColumns: true,
                singleSelect: true,
                rownumbers: true,
                height: 'auto',
                columns: [[
                    {field: 'air_bottle_code', title: '气瓶编码', width: 150, align: 'center'},
                    {field: 'air_bottle_seal_code', title: '气瓶钢印码', width: 200, align: 'center'},
                    {field: 'create_time', title: '入库时间', width: 200, align: 'center'},
                    {field: 'operator', title: '入库人', width: 200, align: 'center'},

                ]],
                onResize: function () {
                    $('#storeInventoryTable').datagrid('fixDetailRowHeight', parentIndex);
                },
                onLoadSuccess: function () {
                    setTimeout(function () {
                        $('#storeInventoryTable').datagrid('fixDetailRowHeight', parentIndex);
                    }, 0);
                }

            });
            $('#tb').datagrid('fixDetailRowHeight', parentIndex);
        },

		loadFilter : function(data) {

			if (data.data != null) {
				return data.data;
			}
			return null;
		}
	});

}
