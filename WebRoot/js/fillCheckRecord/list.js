var headers;
$(function() {
	 getHeaders();
	 initStateCombobox();
	 initShowItemsDatagrid();
});

function initStateCombobox(){

    $('#stateTool').combobox({    
    	data: [{
    		id: '0',
			value: '正常'
		},{
			id: '1',
			value: '异常'
		}],
    	 valueField:'id',    
    	 textField:'value',
    	 onLoadSuccess: function () { // 加载完成后,设置选中第一项
             var val = $(this).combobox("getData");
             $(this).combobox("select", val[0].id);
 	    }
    }); 
}

function initShowItemsDatagrid(){
		 
	 $('#showItemsTable').datagrid({
        url: basePath+'/fillCheckInfo/getPageList',
        method:'get',
        pagination:true,
        pageSize:5,
        pageList:[5,10,20,50],
        rownumbers:true,
        striped:true,
        fitColumns:true,
        width:'auto',
        height:'auto',

        columns:[[
            {field:'ck',checkbox:true},
            {field:'fill_check_code',title:'检查项目代码',align:'center'},
            {field:'fill_check_description',title:'检查项目描述',align:'center'},
            {field:'before_or_after_fill',title:'检查类型',align:'center',formatter:getBefore_or_after_fill},
        ]],
    });
}

function getHeaders(){
	$.ajax({
		type : 'post',
		url : basePath+'/fillCheckRecord/getFillCheckRecordHeader',
		success : function(data) {
			for(var j in data){
		        var d = data[j];
		        for(var i in d){
		            if(d[i].formatter =="undefined"){
		                delete d[i].formatter;
		            }else{
		                d[i].formatter  = window[d[i].formatter];
		            }                           
		        }
		    }          
			headers = data;
		}
	});
}

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

$("#showItems").click(function() {
	$("#showItemsDialog").dialog({
		title:"检查项目展示"
		});
	$("#showItemsDialog").dialog("open");
});

$("#exportExcel").click(function() {
	outToFile();
});

function outToFile() {

	document.getElementById("toolbarForm").action = basePath
			+ '/fillCheckRecord/exportFillCheckRecordInfo';

	document.getElementById("toolbarForm").submit();
};

function setReportTable() {

	var queryParams = queryParamByFormId("toolbarForm");

	$('#reportTable').datagrid({

		url: basePath+'/fillCheckRecord/getFillCheckRecordInfo',
		queryParams:queryParams,
		method : 'post',
		remoteSort : false,
		frozenColumns : [ [ {
			field : 'fill_date',
			title : '充装日期',
			align : 'center',
		}, {
			field : 'air_bottle_seal_code',
			title : '瓶号',
			align : 'center',
		}, {
			field : 'temperature',
			title : '室温(℃)',
			align : 'center',
		}, {
			field : 'volume',
			title : '气瓶标准容积（L）',
			align : 'center',
		} ] ],
		columns : headers,

		onLoadSuccess : function(data) {

		},

	});

}

function getBeforeCheckResult(value, rec) {
	
	if(value == 1){
		return "×";
	}
	
	return "√";
}

function getAfterCheckResult(value, rec) {
	
	if(rec.before_or_after_fill == 0){
		return "";
	}
	
	if(value == 1){
		return "×";
	}
	
	return "√";
}

function getYYYYMMDDDate(value, rec) {

	if (value == null) {
		return null;
	}

	var now = new Date(value);

	var year = now.getFullYear();
	var M = now.getMonth() + 1;
	var MM = (M < 10) ? "0" + M : M;
	var D = now.getDate();
	var DD = (D < 10) ? "0" + D : D;
	return year + "-" + MM + "-" + DD;
}

function getYYYYMMDate(value, rec) {

	if (value == null) {
		return null;
	}

	var now = new Date(value);

	var year = now.getFullYear();
	var M = now.getMonth() + 1;
	var MM = (M < 10) ? "0" + M : M;
	return year + "-" + MM;
}

function getBefore_or_after_fill(value, rec){
	if (value == 0) {
        return '充装前检查';
    }
	if (value == 1) {
		return '充装后检查';
	}
    return null;
}