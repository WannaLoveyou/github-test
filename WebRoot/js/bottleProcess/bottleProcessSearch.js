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
    	    formValues = formValues.replace(/\+/g," ");   // g表示对整个字符串中符合条件的都进行替换
    	    var temp = JSON.stringify(conveterParamsToJson(formValues));  
    	    var queryParam = JSON.parse(temp);  
    	    return queryParam;  
    	}
    	  


$("#btn").click(function(){
	
	
	if(!$("#toolbarForm").form('validate')){
		return false;
	}
	
	   $('#tb').datagrid({  
		   queryParams:queryParamByFormId("toolbarForm"),
		   url:basePath+'/bottleProcess/getBottleProcess',
   		   method:'post', 
		   columns:[[    
               {field:'time',title:'时间',width:150,align:'center',formatter:getTime},
               {field:'state',title:'事件',width:200,align:'center',formatter:getState},
               {field:'operator',title:'操作人',width:200,align:'center',formatter:getOperator},
               {field:'secondCategory',title:'对应门店',width:150,align:'center',formatter:getSecondCategory},
               {field:'deliveryMan',title:'对应配送工',width:150,align:'center',formatter:getDeliveryMan},
               {field:'warehouseInfo',title:'对应仓库',width:150,align:'center',formatter:getWarehouseInfo},
               {field:'clientInfo',title:'对应客户',width:150,align:'center',formatter:getClientInfo},
               {field:'clientCode',title:'对应客户编码',width:150,align:'center',formatter:getClientCode},
		    ]],
		    rowStyler:function(index,row){   
		    	
		    	if(row.state!=null){
				if(row.state.id == '100' || row.state.id == '180' || row.state.id == '10000'){
						return 'background-color:#6decb9;';   
				}}
		    }
            }); 
  });  


$("#dyManExHandleBtn").click(function(){
	
	
	if(!$("#toolbarForm").form('validate')){
		return false;
	}
	
	var bottleCode = $('#bottleCode').val();

	
	$.ajax({
		type : 'post',
		url : basePath+'/bottleProcess/deliveryManExceptionBottleHandle',
		data :{bottleCode:bottleCode},
		success : function(data) {
			if(data.code == 200){
				$.messager.show({
					title : '提示信息',
					msg : '操作成功'
				});
			}else{
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}
		}
	});
});



     function getState(value,rec){
       if(rec.state!=null){
	       return rec.state.state_description;
        }
          return null;
     }

     function getTime(value,rec){
	    if(rec.create_time!=null){
		   return formatDate(new Date(rec.create_time));
	     }
	     return null;
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
    
    	function getSecondCategory(value,rec){
    		if(rec.secondCategory!=null){
    			return rec.secondCategory.second_category_name;
    		}
    		return null;
    	}
    	
    	function getDeliveryMan(value,rec){
    		if(rec.deliveryMan!=null){
    			return rec.deliveryMan.full_name;
    		}
    		return null;
    	}
    	
    	function getOperator(value,rec){
    		if(rec.operator!=null){
    			return rec.operator.full_name;
    		}
    		return null;
    	}
    	
    	function getWarehouseInfo(value,rec){
    		if(rec.warehouseInfo!=null){
    			return rec.warehouseInfo.warehouse_name;
    		}
    		return null;
    	}
    	
    	function getClientInfo(value,rec){
    		if(rec.clientInfo!=null){
    			return rec.clientInfo.client_name;
    		}
    		return null;
    	}
    	
    	
    	function getClientCode(value,rec){
    		if(rec.clientInfo!=null){
    			return rec.clientInfo.client_code;
    		}
    		return null;
    	}
    	