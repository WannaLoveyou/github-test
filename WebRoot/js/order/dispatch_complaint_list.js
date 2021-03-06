$(document).ready(
function(){
	$('#complaintInfoState').combobox({
		value : "",
		editable : false,
		method:'get',
		url : basePath+'/complaintInfoState/getAllList',
		valueField : 'id',
		textField : 'complaint_state_description',
	});
	
	
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


$("#deal").click(function() {
	$("#dealcomplaintform").get(0).reset();
  		var selectRows = $("#complaintInfoTable").datagrid("getSelections");
  		$("#complaintId").val(selectRows[0].id);
  		if(selectRows.length!=1){
  			$.messager.show({
  				title:'提示信息',
  				msg:'要先选择一行记录'
  			});
  		}else{
  			$.ajax({
  				type : 'post',
  				data:{"complaintId":selectRows[0].id},
  				url : basePath+'/complaint/checkComplaintState',
  				success : function(data) {
  					if(data){
  						$("#dealcomplaintdiv").dialog("open");
  					}else{
  						$.messager.show({
  		    				title:'提示信息',
  		    				msg:'改投诉单已处理！'
  		    			});	
  					}
  				}
  			});
  		}
  		
      });
$("#dealBtn").click(function(){
	$.ajax({
			type : 'post',
			data:{complaintId:$("#complaintId").val(),dealcomplaintNote:$("#dealcomplaintNote").val()},
			url : basePath+'/complaint/addDealComplaintNote',
			success : function(data) {
				if(data==false){
					$.messager.show({
	    				title:'提示信息',
	    				msg:'操作成功'
	    			});	
					$("#dealcomplaintdiv").dialog("close");
					$("#complaintInfoTable").datagrid("reload");
				}
			}
		});
});

$("#dealclose").click(function(){//关闭弹出框
		$("#dealcomplaintdiv").dialog("close");
	});

$("#search").click(function() {
	setcomplaintTable();	
});

$("#cancel").click(function() {
	
	var orderInfoRow = $('#complaintInfoTable').datagrid('getSelected');
	
	if (orderInfoRow ==null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}

	$.ajax({
		type : 'post',
		url : basePath+'/order/cancelOrder?orderId='+orderInfoRow.id,
		success : function(data) {
			$('#homeLayout').layout('collapse','east');
			$('#complaintInfoTable').datagrid('reload'); 
		}
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
  
    //关于jquery的serialize方法转换空格为+号的解决方法  
    formValues = formValues.replace(/\+/g," ");   // g表示对整个字符串中符合条件的都进行替换  
    var temp = JSON.stringify(conveterParamsToJson(formValues));  
    var queryParam = JSON.parse(temp);  
    return queryParam;  
}  

function setcomplaintTable(){	
	
	var queryParams=queryParamByFormId("toolbarForm"); 
	var secondCategoryTableRow = $('#secondCategoryTable').datagrid('getSelected');
	var thirdCategoryTableRow = $('#thirdCategoryTable').datagrid('getSelected');

	var complaintInfoTableUrl = basePath+'/complaint/getcomplaintInfoAllList?';
	
	if(secondCategoryTableRow!=null){
		complaintInfoTableUrl+='secondCategory.id='+secondCategoryTableRow.id+'&';
	}
	
	if(thirdCategoryTableRow!=null){
		complaintInfoTableUrl+='thirdCategory.id='+thirdCategoryTableRow.id;
	}
	
	$('#complaintInfoTable').datagrid({ 
		url: complaintInfoTableUrl,
		queryParams:queryParams,
		method:'post',
		columns:[[    
		 
            {field:'complaint_address',title:'地址',align:'center'},
		    {field:'clientInfo.client_name',title:'用户名称',align:'center',formatter:function(value,row,index){
		    	return row.clientInfo.client_name;
		    }},
		    
		    {field:'complaint_tel_number',title:'订单电话号码',align:'center',
		    },
		    {field:'complaint_state_description',title:'投诉状态',align:'center',formatter: function(value,row,index){
				return row.complaintInfoState.complaint_state_description;
			}},
		    
		    
		    {field:'complaint_time',title:'受理时间',align:'center',formatter: function(value,row,index){
				return formatDate(new Date(row.complaint_time));
			}},    
	        
			{field:'complaint_appointment_time1',title:'预约时间1',align:'center',formatter: function(value,row,index){
				if(row.complaint_appointment_time1!=null){
				return formatDate(new Date(row.complaint_appointment_time1));
				}
				return null;
			}},
			{field:'complaint_appointment_time2',title:'预约时间2',align:'center',formatter: function(value,row,index){
				if(row.complaint_appointment_time2!=null){
				return formatDate(new Date(row.complaint_appointment_time2));
				}
				return null;
			}},
	        {field:'complaint_note',title:'受理备注',align:'center',},
	        {field:'complaintType',title:'投诉类型',align:'center',formatter: function(value,row,index){
				return row.complaintType.complaint_type_name;
			}},
	        {field:'complaint_content',title:'投诉内容',align:'center'},
	        
			
	        {field:'dealcomplaintNote',title:'处理备注',align:'center',formatter: function(value,row,index){
				return row.dealcomplaintNote;
			}},
			{field:'full_name',title:'操作员',align:'center',formatter:function(value,row,index){
		    	return row.operator.full_name;
		    }},
			   {field:'complaint_code',title:'投诉编号',width:100,align:'center'},
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



