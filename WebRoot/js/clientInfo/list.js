
$(function(){
	
	 document.onkeydown=function(event){
         var e = event || window.event || arguments.callee.caller.arguments[0];
          if(e && e.keyCode==13){ // enter 键
        	  initTable();
         }
     }; 
     
	$('#firstCategoryTool').combobox({
		icons:[{
			iconCls:'icon-combo-clear',
			handler:function(e){
				$(e.data.target).combobox('clear');
			}
		}],
	    url:basePath+'/firstCategory/getNewAllList', 
	    method:'get',
	    valueField:'id',    
	    textField:'first_category_name',
	    onLoadSuccess: function () { // 加载完成后,设置选中第一项
            var val = $(this).combobox("getData");
            $(this).combobox("select", val[0].id);
            
	    },
	    
	    onSelect:function(rec){
	    	$('#secondCategoryTool').combobox({
	    		icons:[{
	    			iconCls:'icon-combo-clear',
	    			handler:function(e){
	    				$(e.data.target).combobox('clear');
	    			}
	    		}],
	    	    url:basePath+'/secondCategory/getNewListByFirstId?firstId='+rec.id,
	    	    method:'get',
	    	    valueField:'id',    
	    	    textField:'second_category_name',
	    	    
	    	    onSelect:function(rec){
	    	    },
	    	    
	    	    loadFilter: function(data){
	    			
	    			if (data.data!=null){
	    				return data.data;
	    			}
	    			return null;
	    		}
	    	});  
	    },
	    
        loadFilter: function(data){
			
			if (data.data!=null){
				return data.data;
			}
			return null;
		}
	});
	
	var list = new Array();
	list.push({"code": "", "name": "全部"});
	list.push({"code": "0", "name": "在用"});
	list.push({"code": "1", "name": "注销"});
	$('#disabled_state_tool').combobox({
		data:list, 
	    valueField: 'code',
	    textField: 'name',
	    onLoadSuccess: function () { // 加载完成后,设置选中第一项
            var val = $(this).combobox("getData");
            $(this).combobox("select", val[1].code);
            
	    },
	    
	});
	
	$('#rentAirBottleType').combobox({
		value : "",
		editable : false,
		method:'get',
		url : basePath+'/airbottleType/getAllList',
		valueField : 'id',
		textField : 'air_bottle_specifications',
	});
	
	
});

$("#search").click(function() {
	initTable();	
});


$("#clear").click(function() {
	clearToolbar();	
});

function clearToolbar(){
	
	$('#secondCategoryTool').combobox('clear');
	
	$('#create_time_begin_time_tool').datebox('clear');
	$('#create_time_end_time_tool').datebox('clear');
	
	$("#card_code_tool").val("");
	$("#client_code_tool").val("");
	$("#old_client_code_tool").val("");
	$("#client_name_tool").val("");
	$("#tel_number_tool").val("");
	
	$('#tb').datagrid('loadData', {"data":{ total: 0, rows: [] }}); 
	
}

function initTable(){
	
	var queryParams=queryParamByFormId("toolbarForm"); 
	$('#tb').datagrid({ 
		
		url: basePath+'/clientInfo/getNewPageList',
		method:'get',
		queryParams:queryParams,
		rownumbers:true,
		striped:true,
		fitColumns:true,
		pagination:true,
		pageSize:5,
		pageList:[5,10,15,20],
		nowrap:false,
		toolbar:'#toolbar',
		width:'auto',
		height:'auto',
		
		columns:[[    
			{field:'ck',checkbox:true}, 
			{field:'client_address',title:'客户详细地址',align:'center'},
			{field:'card_code',title:'卡号',align:'center'},
			{field:'secondCategory.firstCategory.first_category_name',title:'所属区域',align:'center',formatter:geFirstCategoryName},
			{field:'secondCategory.second_category_name',title:'所属门店',align:'center',formatter:geSecondCategoryName},
			{field:'floorSubsidies.floor_subsidies_money',title:'楼层补贴',align:'center',formatter:geFloorSubsidiesMoney},
			{field:'client_code',title:'客户编号',align:'center'},
			{field:'client_name',title:'客户名称',align:'center'},
			{field:'clientType.client_type_name',title:'客户类型',align:'center',formatter:getClientTypeName},
			{field:'mobile_tel_number_1',title:'移动电话1',align:'center'},
			{field:'mobile_tel_number_2',title:'移动电话2',align:'center'},
			{field:'fixed_tel_number_1',title:'固定电话1',align:'center'},
			{field:'fixed_tel_number_2',title:'固定电话2',align:'center'},
			{field:'remark',title:'备注',align:'center'},
			{field:'create_time',title:'注册时间',align:'center',formatter:getCreateTime},
			{field:'last_order_time',title:'最后订气时间',align:'center',formatter:getLastOrderTime},
			{field:'disabled_state',title:'用户状态',align:'center',formatter:getDisabledState},

		]],
		
		loadFilter: function(data){
			
			if (data.data!=null){
				return data.data;
			}
			return null;
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
    formValues = formValues.replace(/\+/g," ");   // g表示对整个字符串中符合条件的都进行替换
    var temp = JSON.stringify(conveterParamsToJson(formValues));  
    var queryParam = JSON.parse(temp);  
    return queryParam;  
} 


$("#rentAirBottle").click(function() {
	
	$("#rentAirBottleDialog").dialog({
		title : "租瓶信息"
	});
	
	$('#rentAirBottleForm').form('clear');
	
	initRentAirBottleDialog();
});

function initRentAirBottleDialog(){
	
	var selectRows = $("#tb").datagrid("getSelections");
	if (selectRows.length != 1) {
		$.messager.show({
			title : '提示信息',
			msg : '只能选择一行记录'
		});
		return;
	}
	
	$("#rentAirBottleForm").form("load", {
		'clientInfo.id' : selectRows[0].id,
		rent_num : 1,
	});
	
	$("#rentAirBottleDialog").dialog("open");
}

$("#rentAirBottleSumbitBtn").click(function() {
	
	if(!$('#rentAirBottleForm').form('validate')){
		$.messager.show({
			title : '提示信息',
			msg : '信息不完整'
		});
		return;
	}
	
	var rent_money_for_month = $("#rent_money_for_month").val();
	
	var rent_money_for_year = $("#rent_money_for_year").val();
	
	if(rent_money_for_month == "" && rent_money_for_year == ""){
		$.messager.show({
			title : '提示信息',
			msg : '请填写押金收取方式'
		});
		return;
	}
	
	if(rent_money_for_month != "" && rent_money_for_year != ""){
		$.messager.show({
			title : '提示信息',
			msg : '押金收取方式不能重复'
		});
		return;
	}
	

	
	$.ajax({
		type : 'post',
		data : $('#rentAirBottleForm').serialize(),
		url : basePath+'/airBottleRentInfo/add' ,
		success : function(data) {
			
			if(data.code != 200){
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}else{
				$("#rentAirBottleDialog").dialog("close");
				$.messager.show({
					title : '提示信息',
					msg : '操作成功'
				});
			}
			
		}
	});
});

var flag;
		$("#add").click(function() {
			$("#mydialog").dialog({
				title : "新增客户资料"
			});
			$('#myform').form('clear');
			$("#mydialog").dialog("open");
			flag = 'add';
			
		    var clientSexData = [['男', '男'], ['女', '女']];  
			 $('#client_sex').combobox({    
				    
			    	data: clientSexData,
					valueField:0,    
		    	    textField:1,
			    	
			});  
			 
			$('#clientType').combobox({
				editable : false,
				method:'get',
				url : basePath+'/clientType/getAllList',
				valueField : 'id',
				textField : 'client_type_name',
					onLoadSuccess: function () { // 加载完成后,设置选中第一项		                
				    }
			});

			
			$('#floorSubsidies').combobox({
				editable : false,
				method:'get',
				url : basePath+'/floorSubsidies/getAllList',
				valueField : 'id',
				textField : 'floor_subsidies_name',
				formatter: function(row){
		        return row.floor_subsidies_name + ' ' +  row.floor_subsidies_money + '元';
	        }
			});
			
			
			
			$('#firstCategory').combobox({
			    url:basePath+'/firstCategory/getAllList', 
			    method:'get',
			    editable : false,
			    valueField:'id',    
			    textField:'first_category_name',
			    onLoadSuccess: function () { // 加载完成后,设置选中第一项
	                var val = $(this).combobox("getData");
	                $(this).combobox("select", val[0].id);
	                
			    },
			    onSelect:function(rec){
			    	$('#secondCategory').combobox({  
			    	    url:basePath+'/secondCategory/getListByFirstId?firstId='+rec.id,
			    	    method:'get',
			    	    valueField:'id',    
			    	    textField:'second_category_name',
			    	    onSelect:function(rec){
			    	      
			    	    },
			    	    
			    	});
			    }
			}); 
			
		});

		$("#edit").click(function() {
			$("#mydialog").dialog({
				title : "修改补贴方案"
			});
			$('#myform').form('clear');
			var selectRows = $("#tb").datagrid("getSelections");
			if (selectRows.length != 1) {
				$.messager.show({
					title : '提示信息',
					msg : '只能选择一行记录'
				});
			} else {
				
				var clientSexData = [['男', '男'], ['女', '女']];  
				 $('#client_sex').combobox({    
					    
				    	data: clientSexData,
						valueField:0,    
			    	    textField:1,
				    	
				});  
				 
			    $('#clientType').combobox({
				editable : false,
				url : basePath+'/clientType/getAllList',
				method:'get',
				valueField : 'id',
				textField : 'client_type_name',
			
			    });
			    
			    
			    $('#floorSubsidies').combobox({
					editable : false,
					url : basePath+'/floorSubsidies/getAllList',
					method:'get',
					valueField : 'id',
					textField : 'floor_subsidies_name',
					formatter: function(row){
			        return row.floor_subsidies_name + ' ' +  row.floor_subsidies_money + '元';
		            },
				});
			    
			    

			    $('#secondCategory').combobox({  
			    	 url:basePath+'/secondCategory/getMyList',
			    	 method:'get',
			    	 valueField:'id',    
			    	 textField:'second_category_name',
			    	 onSelect:function(rec){
			    	    }
			    	    
			    });  
			   
			    $('#firstCategory').combobox({
			    url:basePath+'/firstCategory/getAllList', 
			    method:'get',
			    editable : false,
			    valueField:'id',    
			    textField:'first_category_name',
			    onSelect:function(rec){
			    	$('#secondCategory').combobox({  
			    	    url:basePath+'/secondCategory/getListByFirstId?firstId='+rec.id,
			    	    method:'get',
			    	    valueField:'id',    
			    	    textField:'second_category_name',
			    	    onSelect:function(rec){
			    	    }
			    	});
			    	
			    }
			    }); 
			    
			  
			    $('#client_sex').combobox({    
			    	data: [{
			    		id: '男',
						value: '男'
					},{
						id: '女',
						value: '女'
					}],
			    	 valueField:'id',    
			    	 textField:'value',
			    });  
			    
				$("#myform").get(0).reset();
				$("#myform").form("load", {
					clientId : selectRows[0].id,
					client_code : selectRows[0].client_code,
					old_client_code : selectRows[0].old_client_code,
					card_code : selectRows[0].card_code,
					password : selectRows[0].password,
					client_name : selectRows[0].client_name,
					'client_sex' : selectRows[0].client_sex,
					mobile_tel_number_1 : selectRows[0].mobile_tel_number_1,
					mobile_tel_number_2 : selectRows[0].mobile_tel_number_2,
					fixed_tel_number_1 : selectRows[0].fixed_tel_number_1,
					fixed_tel_number_2 : selectRows[0].fixed_tel_number_2,
					client_address : selectRows[0].client_address,
					floorSubsidies : selectRows[0].floorSubsidies==null?null:selectRows[0].floorSubsidies.id,
					remark : selectRows[0].remark,
					temp_tips : selectRows[0].temp_tips,
					modify_people : selectRows[0].modify_people,
					create_people : selectRows[0].create_people,
					create_time_temp : selectRows[0].create_time,
					'clientType.id' : selectRows[0].clientType==null?null:selectRows[0].clientType.id,
					'floorSubsidies.id' : selectRows[0].floorSubsidies==null?null:selectRows[0].floorSubsidies.id,
					'firstCategory.id' : selectRows[0].secondCategory.firstCategory.id,
					'secondCategory.id' : selectRows[0].secondCategory.id,
				});
					   
				document.getElementById('create_time').innerHTML = new Date(selectRows[0].create_time).toLocaleString() ;
                document.getElementById('modify_time').innerHTML = new Date(selectRows[0].modify_time).toLocaleString() ;
				$("#mydialog").dialog("open");
				flag = 'edit';
			}

		});

		$("#btn2").click(function() {
			$("#mydialog").dialog("close");
		});

		$("#btn1").click(function() {
			
			if(!$('#myform').form('validate')){
				$.messager.show({
					title : '提示信息',
					msg : '信息不完整'
				});
				return;
			}
			
			$.ajax({
				type : 'post',
				data : $('#myform').serialize(),
				url : basePath+'/clientInfo/' + flag,
				success : function(data) {
					
					if(data.code != null && data.code != 200){
						$.messager.show({
							title : '提示信息',
							msg : data.msg
						});
					}else{
						$("#mydialog").dialog("close");
						$("#tb").datagrid("reload");
						$.messager.show({
							title : '提示信息',
							msg : '操作成功'
						});
					}
					
					
				}
			});
		});

		$("#del").click(function() {
			var selectRows = $("#tb").datagrid("getSelections");
			if (selectRows.length < 1) {
				$.messager.show({
					title : '提示信息',
					msg : '请先选中一条记录'
				});
			} else {
				var ids = "";
				for ( var i = 0; i < selectRows.length; i++) {
					ids += selectRows[i].id + ",";
				}
				$.ajax({
					type : 'post',
					data : {
						ids : ids
					},
					url : basePath+'/clientInfo/del',
					success : function(data) {
						$("#tb").datagrid("reload");
					}
				});
			}
		});
		
		$("#searchM1CardCodeInToolbar").click(function() {
			
			$("#toolbarForm").form("load",{
				'card_code_tool':document.myApplet.tyBy7(),
			});
		});
		
        $("#searchM1CardCodeInDialog").click(function() {
			
			$("#myform").form("load",{
				'card_code':document.myApplet.tyBy7(),
			});
		});
        
        $("#searchM1CardCodeInToolbarNew").click(function() {
			
			$("#toolbarForm").form("load",{
				'card_code':getCardContentByMiddleware(7,0),
			});
		});
        
        $("#searchM1CardCodeInDialogNew").click(function() {
			
			$("#myform").form("load",{
				'card_code':getCardContentByMiddleware(7,0),
			});
		});
        
        $("#editPassword").click(function() {
        	
        	var selectRows = $("#tb").datagrid("getSelections");
			if (selectRows.length != 1) {
				$.messager.show({
					title : '提示信息',
					msg : '请选中一条记录'
				});
				return;
			}
			
        	$.ajax({
				type : 'post',
				data : {
					clientId :  selectRows[0].id
				},
				url : basePath+'/clientInfo/queryPassword',
				success : function(data) {
					if(data.code == 200){
						editPassword(selectRows[0].id,data.data);
					}else{
						$.messager.show({
							title : '提示信息',
							msg : data.msg
						});
					}
				}
			});
		});
        
        function editPassword(clientId,password){
        	
        	if(password == null ||password == 'NULL'){
        		password = "";
        	}
        	
        	var psw = prompt("请输入要修改的密码",password);
        	
       	 	if(psw == null){
       	 		return;
       	 	}
       	 	

        	$.ajax({
				type : 'post',
				data : {
					clientId :  clientId,
					password : psw,
				},
				url : basePath+'/clientInfo/editPassword',
				success : function(data) {
					if(data.code == 200){
						$.messager.show({
							title : '提示信息',
							msg : "操作成功"
						});
					}else{
						$.messager.show({
							title : '提示信息',
							msg : data.msg
						});
					}
				}
			});
       	 	
        }
        
		function getClientTypeName(value, rec) {
			if (rec.clientType != null) {
				return rec.clientType.client_type_name;
			}
			return null;
		}
		
		function geSecondCategoryName(value, rec) {
			if (rec.secondCategory != null) {
				return rec.secondCategory.second_category_name;
			}
			return null;
		}
		function geFirstCategoryName(value, rec) {
			if (rec.secondCategory.firstCategory != null) {
				return rec.secondCategory.firstCategory.first_category_name;
			}
			return null;
		}
		function geFloorSubsidiesMoney(value, rec) {
			if (rec.floorSubsidies!= null) {
				return rec.floorSubsidies.floor_subsidies_money;
			}
			return null;
		}
		function getCreateTime(value, rec) {
			if (rec != null) {
				return new Date(rec.create_time).toLocaleString() ;
			}
			return null;
		}
		
		function getLastOrderTime(value, rec) {
			if (rec.last_order_time != null) {
				return new Date(rec.last_order_time).toLocaleString() ;
			}
			return null;
		}
		
		function getDisabledState(value, rec) {
			if (rec.disabled_state != null) {
				switch (rec.disabled_state) {
				case 0:
					return "在用";
					break;
				case 1:
					return "注销";
					break;

				default:
					return "未知";
					break;
				}
			}
			return null;
		}
		
		
		function getFloorSubsidies(value, rec) {
			if (rec != null) {
				return  rec.floor_subsidies_name + rec.floor_subsidies_money;
			}
			return null;
		}
		
		$("#cancel").click(function() {
			cancel();	
		});
		
		//注销用户
		function cancel(){
			var selectRows = $("#tb").datagrid("getSelections");
			if (selectRows.length == 0) {
				$.messager.show({
					title : '提示信息',
					msg : '请选择至少一个用户'
				});
				return;
			}
			
			$.extend($.messager.defaults,{  
			    ok:"确定",  
			    cancel:"取消"  
			});
			$.messager.confirm('提示','确定要注销选中的用户吗？',function(r){
			    if (r){
			    	var ids="";
			        for(var i=0;i<selectRows.length-1;i++){
			            ids+=selectRows[i].id+",";
			        }
			        ids+=selectRows[selectRows.length-1].id;
			    	
			        $.ajax({
			            type : 'post',
			            data : {ids:ids},
			            url : basePath+'/clientInfo/cancel',
			            success : function(data) {
			                $("#tb").datagrid("reload");
			            }
			       });
			    }
			});
		}
