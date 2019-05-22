var moduleUrl = "airBottleRentInfo";

$(function () {
	
	 document.getElementById('search').onclick = function() {
		 initDatagrid();
	 };
	 
	 document.getElementById('back').onclick = function() {
		 backAirBottleInfo();
	 };
	
});

$("#printOrderBtn").click(function() {
	customPrintFlag = false;
	initPrint();
});
function initPrint(){
    var orderInfoRow = $('#tb').datagrid('getSelected');
	if (orderInfoRow ==null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}
	initOrderDialog(orderInfoRow);
}
var LODOP;
function table(){
	
	 LODOP=getLodop();
		 
	 if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
    	 if(window.confirm('打印需要控件，请点击确认下载，下载安装后需要重新登录系统')){
    		 window.location.href='http://106.75.137.43:8078/files/lodop/CLodop_Setup_for_Win32NT_2.068.exe';
          }else{
         }
    	 return false;
     }
	
	LODOP.PRINT_INIT(PRINT_INIT_NAME);// 首先一个初始化语句
	// SET_PRINT_PAGESIZE(intOrient,intPageWidth,intPageHeight,strPageName)设定纸张大小
	LODOP.SET_PRINT_PAGESIZE(1,intPageWidth,intPageHeight,"");
	// ADD_PRINT_TABLE(intTop,intLeft,intWidth,intHeight,strHtml)增加表格项
	LODOP.ADD_PRINT_TABLE(intTop,intLeft,intWidth,intHeight,document.getElementById("print_div").innerHTML);// 然后多个ADD语句及SET语句
	LODOP.PREVIEW(); // 打印预览
	// LODOP.PRINT();
	
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

function backAirBottleInfo(){
	
	 var selecteRow = $('#tb').datagrid('getSelected');
		
	 if (selecteRow ==null) {
		$.messager.show({
				title : '提示信息',
				msg : '请先选中一条记录'
		});
		return;
	}
	 
	if(selecteRow.airBottleRentInfoState.id != 1){
		$.messager.show({
			title : '提示信息',
			msg : '该记录不能进行操作'
		});
		return;
	}
	
	var airBottleRentInfoId = selecteRow.id;
	 
	$
	.ajax({
		type : 'post',
		data : {airBottleRentInfoId:airBottleRentInfoId},
		  url: basePath+'/'+moduleUrl+'/getBackInfo',
		success : function(data) {
			var rentMoney = data.data;
			
			if(!confirm('总费用为' + rentMoney + '元,是否确认退瓶')){
				return;
			}
			
			backAirBottleSumbit(airBottleRentInfoId,rentMoney);
		}
	});
	
}

function backAirBottleSumbit(airBottleRentInfoId,rentMoney){
	
	$
	.ajax({
		type : 'post',
		data : {airBottleRentInfoId:airBottleRentInfoId,rentMoney:rentMoney},
		url: basePath+'/'+moduleUrl+'/back',
		success : function(data) {
			$("#tb").datagrid("reload");
			$.messager.show({
				title : '提示信息',
				msg : '操作成功'
			});
		}
	});
}

function initDatagrid(){
	
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
         singleSelect:true,

         columns:[[
             {field:'ck',checkbox:true},
             {field:'clientInfo.client_code',title:'客户编号',align:'center',formatter:getClientCode},
             {field:'clientInfo.client_name',title:'客户姓名',align:'center',formatter:getClientName},
             {field:'rent_time',title:'租瓶时间',align:'center',formatter:getRentTime},
             {field:'expire_time',title:'到期时间',align:'center',formatter:getExpireTime},
             {field:'airBottleType',title:'气瓶类型',align:'center',formatter:getAieBottleType},
             {field:'rent_num',title:'租瓶数量',align:'center'},
             {field:'rent_deposit',title:'租瓶押金',align:'center'},
             {field:'rent_money_for_month',title:'每月租金',align:'center'},
             {field:'rent_money_for_year',title:'每年租金',align:'center'},
             {field:'rent_operator.full_name',title:'租瓶操作人',align:'center',formatter:getRentOperator},
             {field:'total_rent_money',title:'总租金',align:'center'},
             {field:'back_deposit',title:'退还押金',align:'center'},
             {field:'back_time',title:'退瓶时间',align:'center',formatter:getBackTime},
             {field:'back_operator.full_name',title:'退瓶操作人',align:'center',formatter:getBackOperator},
             {field:'airBottleRentInfoState.state_description',title:'租瓶状态',align:'center',formatter:getAirBottleRentInfoState},
         ]],

         loadFilter: function(data){

             if (data.data!=null){
                 return data.data;
             }
             return null;
         }
     });
}



function getAieBottleType(value, rec) {
	if (rec.airBottleType != null) {
		return rec.airBottleType.air_bottle_specifications;
	}
	return null;
}

function getClientCode(value, rec) {
	if (rec.clientInfo != null) {
		return rec.clientInfo.client_code;
	}
	return null;
}

function getClientName(value, rec) {
	if (rec.clientInfo != null) {
		return rec.clientInfo.client_name;
	}
	return null;
}

function getRentOperator(value, rec) {
	if (rec.rent_operator != null) {
		return rec.rent_operator.full_name;
	}
	return null;
}

function getBackOperator(value, rec) {
	if (rec.back_operator != null) {
		return rec.back_operator.full_name;
	}
	return null;
}

function getRentTime(value, rec) 
{
	if (rec.rent_time != null) {
		return formatDate(new Date(rec.rent_time));
	}
	return null;
}

function getBackTime(value, rec) 
{
	if (rec.back_time != null) {
		return formatDate(new Date(rec.back_time));
	}
	return null;
}

function getExpireTime(value, rec) 
{
	if (rec.expire_time != null) {
		return new Date(rec.expire_time).toLocaleDateString() ;
	}
	return null;
}


function getAirBottleRentInfoState(value, rec) 
{
	if (rec.airBottleRentInfoState != null) {
		return rec.airBottleRentInfoState.state_description;
	}
	return null;
}

function getSecondCategoryName(value,rec)
{
	return rec.second_category_name;
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