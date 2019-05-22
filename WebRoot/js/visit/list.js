$(document).ready(
function(){
	
	var secondCategoryTableSelectFlag = -1;
	var thirdCategoryTableSelectFlag = -1;
	$('#secondCategoryTable').datagrid({ 
		 
		onClickRow:function(rowIndex, rowData){
			
			if(secondCategoryTableSelectFlag == rowIndex){
				$(this).datagrid('unselectRow', rowIndex);
				secondCategoryTableSelectFlag = -1;
			}else{
				secondCategoryTableSelectFlag = rowIndex;
				$('#thirdCategoryTable').datagrid({ 
					url:basePath+'/thirdCategory/getListBySecondId?secondtId='+rowData.id,
					method:'get',
					columns:[[    
					          {field:'third_category_name',title:'地段',width:100,align:'center'},
					]],
					onClickRow:function(rowIndex, rowData){
						if(thirdCategoryTableSelectFlag == rowIndex){
							$(this).datagrid('unselectRow', rowIndex);
							thirdCategoryTableSelectFlag = -1;
						}else{
							thirdCategoryTableSelectFlag = rowIndex;
						}
					}
				});
			}			
		}
	});
	
});

$("#search").click(function() {
	setVisitTable();	
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
  
    //关于jquery的serialize方法转换空格为+号的解决方法  
    formValues = formValues.replace(/\+/g," ");   // g表示对整个字符串中符合条件的都进行替换  
    var temp = JSON.stringify(conveterParamsToJson(formValues));  
    var queryParam = JSON.parse(temp);  
    return queryParam;  
}  

function setVisitTable(){	
	
	var queryParams=queryParamByFormId("toolbarForm"); 
	var secondCategoryTableRow = $('#secondCategoryTable').datagrid('getSelected');

	var visitInfoTableUrl = basePath+'/visit/getVisitInfoAllList?';
	
	if(secondCategoryTableRow!=null){
		visitInfoTableUrl+='secondCategory.id='+secondCategoryTableRow.id+'&';
	}
	
	$('#visitInfoTable').datagrid({ 
		url: visitInfoTableUrl,
		queryParams:queryParams,
		method:'post',
		pageSize:20,
		columns:[[    
		 
            {field:'visit_code',title:'回访编号',width:100,align:'center'},
            {field:'visit_address',title:'地址',align:'center'},
		    {field:'clientInfo.client_name',title:'用户名称',align:'center',formatter:function(value,row,index){
		    	return row.clientInfo.client_name;
		    }},
		    
		    {field:'visit_tel_number',title:'客户电话号码',align:'center',
		    },
			{field:'create_time',title:'回访时间',align:'center',formatter: function(value,row,index){
				if(row.create_time!=null){
				return formatDate(new Date(row.create_time));
				}
				return null;
			}},
	        {field:'visit_note',title:'回访备注',align:'center',},
	        {field:'visitType',title:'回访类型',align:'center',formatter: function(value,row,index){
				return row.visitType.visit_type_name;
			}},
	        {field:'visit_content',title:'回访内容',align:'center'},
	        
			{field:'full_name',title:'操作员',align:'center',formatter:function(value,row,index){
		    	return row.operator.full_name;
		    }},
		]]    

	});
}
function formatDate(now) { 
	var year=now.getFullYear(); 
	var M=now.getMonth()+1;  
    var MM=(M<10)?"0"+M:M;  
	var D= now.getDate();  
    var DD=(D<10)?"0"+D:D; 
    var h=now.getHours();  
    var hh=(h<10)?"0"+h:h;  
    var m=now.getMinutes();  
    var mm=(m<10)?"0"+m:m; 
    var s=now.getSeconds();  
    var ss=(s<10)?"0"+s:s;  
	return year+"-"+MM+"-"+DD+" "+hh+":"+mm+":"+ss; 
	} 



