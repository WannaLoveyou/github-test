var moduleUrl = 'phoneRecord';

$(function () {
	 
	 document.getElementById('search').onclick = function() {
		 initDatagrid();
	 };

	 document.getElementById('callBack').onclick = function() {
		 callBack();
	 };
	 
	 document.getElementById('del').onclick = function() {
		 initDel();
	 };

	 document.getElementById('startRecordModule').onclick = function() {
		 readyTelSystem();
	 };
	 
	 document.getElementById('shutdownRecordModule').onclick = function() {
		 shutdownTelSystem();
	 };
	 
	 $('#recordStateTool').combobox({
		icons : [ {
			iconCls : 'icon-combo-clear',
			handler : function(e) {
				$(e.data.target).combobox('clear');
			}
		} ],
		editable : false,
		url : basePath+'/'+moduleUrl+'/getAllStates',
		valueField : 'id',
		textField : 'description',
		loadFilter: function(data){

            if (data.data!=null){
                return data.data;
            }
            return null;
        }
	});
});

function initDatagrid() {
	 var queryParams=queryParamByFormId("toolbarForm");
	 
	 $('#tb').datagrid({
         url: basePath+'/'+moduleUrl+'/getPageList',
         method:'post',
         queryParams:queryParams,
         pagination:true,
         pageSize:20,
         pageList:[20,50,100,200],
         rownumbers:true,
         striped:true,
         fitColumns:true,
         toolbar: '#toolbar',
         width:'auto',
         height:'auto',

         columns:[[
             {field:'ck',checkbox:true},
             {field:'phoneNumber',title:'电话号码',align:'center',width:100,},
             {field:'callInTime',title:'拨入时间',align:'center',width:100,formatter:getCallInTime},
             {field:'callBackTime',title:'回访时间',align:'center',width:100,formatter:getCallBackTime},
             {field:'phoneRecordState',title:'记录状态',align:'center',width:100,formatter:getPhoneRecordState},
         ]],

         loadFilter: function(data){

             if (data.data!=null){
                 return data.data;
             }
             return null;
         }
     });
}

function callBack() {
	 var selectRows = $("#tb").datagrid("getSelections"); 
	 if(selectRows.length != 1){
		 $.messager.show({
			 title:'提示信息',
            msg:'请选择一条记录'
        });
        return;
     }
	 var id = selectRows[0].id;
	 $.ajax({
	        type : 'post',
	        data : {recordId:id},
	        url : basePath+'/'+moduleUrl+'/callBack',
	        success : function(data) {
	        	if (data.code == 200) {
	        		$.messager.show({
	        			title:'提示信息',
	        			msg:'操作成功'
	        		});					
				}else{
					$.messager.show({
	        			title:'提示信息',
	        			msg:data.msg
	        		});		
				}
	            $("#tb").datagrid("reload");
	        }
	   });
}

function initDel(){
	
	var selectRows = $("#tb").datagrid("getSelections");
    if(selectRows.length<1){
        $.messager.show({
            title:'提示信息',
            msg:'请选择要删除的记录'
        });
        return;
    }
    
    var ids="";
    for(var i=0;i<selectRows.length-1;i++){
        ids+=selectRows[i].id+",";
    }
    ids+=selectRows[selectRows.length-1].id;
    
    $.ajax({
         type : 'post',
         data : {ids:ids},
         url : basePath+'/'+moduleUrl+'/del',
         success : function(data) {
             $("#tb").datagrid("reload");
         }
    });
    
}

var interval;

function readyTelSystem() {
	$.ajax({
		type : 'get',
		url : 'http://127.0.0.1:4454/ACD?Action=ready&encode=json',
		success : function(data) {
			interval = window.setInterval("getEventTelSystem()",2000);
			document.getElementById('recordModuleDisplay').innerHTML = "记录未接来电模式已开启";
		}
	});
}

function shutdownTelSystem() {
	clearInterval(interval);
	document.getElementById('recordModuleDisplay').innerHTML = "";
}

function getEventTelSystem() {
	
	$.ajax({
		type : 'get',
		url : 'http://127.0.0.1:4454/System?Action=GetEvent&encode=json',
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			if(obj.event == 1){
				add(obj.content.author);
			}
		}
	});
}

function add(phoneNumber) {
	$.ajax({
		type : 'post',
		url : basePath+'/'+moduleUrl+'/add',
		data : {phoneNumber : phoneNumber},
		success : function(data) {
			 $("#tb").datagrid("reload");
		}
	});
}

function getCallInTime(value,rec) {
	if (rec.callInTime != null) {
		return formatDate(new Date(rec.callInTime));
	}
	return null;
}

function getCallBackTime(value,rec) {
	if (rec.callBackTime != null) {
		return formatDate(new Date(rec.callBackTime));
	}
	return null;
}

function getPhoneRecordState(value,rec) {
	if (rec.phoneRecordState != null) {
		return rec.phoneRecordState.description;
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