$(function() {
	$('#airBottleImportInfoStateTool').combobox({
		icons:[{
			iconCls:'icon-combo-clear',
			handler:function(e){
				$(e.data.target).combobox('clear');
			}
		}],
        value: "",
        editable: false,
        method: 'get',
        url: basePath+'/airBottleImportInfoState/getAllList',
        valueField: 'id',
        textField: 'state_description'
    });
});
  		


    var flag;
    $("#search").click(function () {
        var queryParams = queryParamByFormId("toolbarForm");
        $('#tb').datagrid({
            url: basePath+'/airBottleImportInfo/getPageList',
            method: 'post',
            queryParams: queryParams,
            rownumbers: true,
            striped: true,
            fitColumns: true,
            pagination: true,
            pageSize: 20,
            pageList: [20,50,100],
            nowrap: false,
            toolbar: '#toolbar',
            width: 'auto',
            height: 'auto',


            columns: [[
                {field: 'ck', checkbox: true,},
                {field: 'air_bottle_blong_code', title: '所属单位编码', align: 'center'},
                {field: 'air_bottle_seal_code', title: '气瓶钢印码', align: 'center'},
                {field: 'air_bottle_type_code', title: '气瓶类型编码', align: 'center'},
                {field: 'factory_number', title: '出厂编号', align: 'center'},
                {
                    field: 'production_unit_code',
                    title: '生产单位编码',
                    align: 'center',
                },
                {
                    field: 'detection_unit_code',
                    title: '检测单位编码',
                    align: 'center',
                },
                {field: 'check_time', title: '检修时间', align: 'center'},
                {field: 'next_check_time', title: '下次检修时间', align: 'center'},
                {field: 'produce_time', title: '生产时间', align: 'center'},
                {field: 'use_cycle', title: '设计使用年限', align: 'center'},
                {field: 'volume', title: '容积', align: 'center'},
                {field: 'bottle_weight', title: '气瓶重量', align: 'center'},
                {
                    field: 'remark',
                    title: '备注信息',
                    align: 'center',
                }, {
                    field: 'create_time',
                    title: '导入时间',
                    align: 'center',
                    formatter: getCreateTime
                }, {
                    field: 'operator',
                    title: '创建人',
                    align: 'center',
                    formatter: getOperator
                }, {
                    field: 'state',
                    title: '状态',
                    align: 'center',
                    formatter: getState
                },{
                    field: 'import_remark',
                    title: '导入备注',
                    align: 'center',
                }
            ]],
        });

    });

    function conveterParamsToJson(paramsAndValues) {
        var jsonObj = {};

        var param = paramsAndValues.split("&");
        for (var i = 0; param != null && i < param.length; i++) {
            var para = param[i].split("=");
            jsonObj[para[0]] = para[1];
        }

        return jsonObj;
    }

    function queryParamByFormId(form) {
        var formValues = $("#" + form).serialize();

        // 关于jquery的serialize方法转换空格为+号的解决方法
        formValues = formValues.replace(/\+/g, " ");   // g表示对整个字符串中符合条件的都进行替换
        var temp = JSON.stringify(conveterParamsToJson(formValues));
        var queryParam = JSON.parse(temp);
        return queryParam;
    }
    

    $("#transform").click(function () {
    	
    	 var r = confirm("请确认是否把筛选条件下的记录导入正式气瓶档案库?(已经导入成功的记录不会重复导入)");

         if (r == false) {
             return;
         }
    	
    	 $.ajax({
    	        type : 'post',
    	        data : $('#toolbarForm').serialize(),
    	        url : basePath+'/airBottleImportInfo/transform',
    	        success : function(data) {
    	            $("#mydialog").dialog("close");
    	            $("#tb").datagrid("reload");
    	            $.messager.show({
    	                title: '提示信息',
    	                msg: '操作成功'
    	            });
    	        }
    	    });
    });
    
    
    $("#batchDel").click(function () {
    	
   	 var r = confirm("请确认是否删除筛选条件下的记录?(已经导入成功的记录不会被删除)");

        if (r == false) {
            return;
        }
   	
   	 $.ajax({
   	        type : 'post',
   	        data : $('#toolbarForm').serialize(),
   	        url : basePath+'/airBottleImportInfo/batchDel',
   	        success : function(data) {
   	            $("#mydialog").dialog("close");
   	            $("#tb").datagrid("reload");
   	            $.messager.show({
   	                title: '提示信息',
   	                msg: '操作成功'
   	            });
   	        }
   	    });
   });
    
    $("#import").click(function () {
    	
    	 $("#uploadDialog").dialog({
             title: "上传数据"
         });
    	 
    	  $('#uploadform').form('clear');
    	 
    	  $("#uploadDialog").dialog("open");
    });
    
    
    $("#uploadSubmitBtn").click(function () {
    	
    	$.ajaxFileUpload({
    	    url : basePath+'/airBottleImportInfo/upLoadExcel' ,
    	    secureuri : false,
    	    data : $('#uploadform').serialize(),//需要传递的数据 json格式
    	    fileElementId :['uploadfile1'],
    	    dataType : 'HTML', // 返回值类型 一般设置为json
    	    success : function(data, status) // 服务器成功响应处理函数
    		{
    			$("#uploadDialog").dialog("close");
    			$("#tb").datagrid("reload");
    			$.messager.show({
    				title:'提示信息',
    				msg:'操作成功'
    			});
    		},
    		error : function(data, status, e)// 服务器响应失败处理函数
    		{
    			alert(e);
    		}
    	});
   	 
   });
    
    $("#downloadTemplate").click(function() {
    	    	
    	document.getElementById("toolbarForm").action = basePath
		+ '/airBottleImportInfo/downloadTemplate';

    	document.getElementById("toolbarForm").submit();
    });

    
    $("#add").click(function () {
        $("#mydialog").dialog({
            title: "新增信息"
        });
        $('#myform').form('clear');
        $("#mydialog").dialog("open");
        
        flag = 'add';

    });

    $("#edit").click(function () {
    	
        $("#mydialog").dialog({
            title: "修改信息"
        });
        
        var selectRows = $("#tb").datagrid("getSelections");
        if (selectRows.length != 1) {
            $.messager.show({
                title: '提示信息',
                msg: '只能选择一行记录'
            });
            return;
        }
        
        $('#myform').form('clear');
          
            $("#myform").form("load", {
            	entityId: selectRows[0].id,
            	air_bottle_blong_code: selectRows[0].air_bottle_blong_code,
            	air_bottle_seal_code: selectRows[0].air_bottle_seal_code,
            	air_bottle_type_code: selectRows[0].air_bottle_type_code,
            	factory_number: selectRows[0].factory_number,
            	production_unit_code: selectRows[0].production_unit_code,
            	detection_unit_code: selectRows[0].detection_unit_code,
            	remark: selectRows[0].remark,
            	produce_time: selectRows[0].produce_time,
            	check_time: selectRows[0].check_time,
            	next_check_time: selectRows[0].next_check_time,
            	use_cycle: selectRows[0].use_cycle,
            	volume: selectRows[0].volume,
            	bottle_weight: selectRows[0].bottle_weight,
            });

            $("#mydialog").dialog("open");
            flag = 'edit';
        

    });

    function FormatDate(date) {
        return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    }

    $("#btn2").click(function () {
        $("#mydialog").dialog("close");
    });

    $("#btn1").click(function () {
    	 $.ajax({
    	        type : 'post',
    	        data : $('#myform').serialize(),
    	        url : basePath+'/airBottleImportInfo/'+flag,
    	        success : function(data) {
    	            $("#mydialog").dialog("close");
    	            $("#tb").datagrid("reload");
    	            $.messager.show({
    	                title: '提示信息',
    	                msg: '操作成功'
    	            });
    	        }
    	    });
    });


    $("#del").click(function () {
        var selectRows = $("#tb").datagrid("getSelections");
        if (selectRows.length < 1) {
            $.messager.show({
                title: '提示信息',
                msg: '请选择要删除的行数'
            });
        } else {

            var r = confirm("是否确认删除数据");

            if (r == false) {
                return;
            }

            var ids = "";
            for (var i = 0; i < selectRows.length; i++) {
                ids += selectRows[i].id + ",";
            }
            $
                .ajax({
                    type: 'post',
                    data: {ids: ids},
                    url: basePath+'/airBottleImportInfo/del',
                    success: function (data) {
                        if (data.msg != null) {
                            $.messager.show({
                                title: '提示信息',
                                msg: data.msg
                            });
                        }
                        $("#tb").datagrid("reload");
                    }
                });
        }
    });


    function getCreateTime(value, rec) {

        if (rec.create_time != null) {
            return formatDate(new Date(rec.create_time));
        }
        return null;
    }
  
    function getOperator(value, rec) {

        if (rec.operator != null) {
            return rec.operator.full_name;
        }
        return null;
    }
    
    function getState(value, rec) {

        if (rec.state != null) {
            return rec.state.state_description;
        }
        return null;
    }
    

    function formatDate(now) {
        var year = now.getFullYear();
        var M = now.getMonth() + 1;
        var MM = (M < 10) ? "0" + M : M;
        var D = now.getDate();
        var DD = (D < 10) ? "0" + D : D;
        var h = now.getHours();
        var hh = (h < 10) ? "0" + h : h;
        var m = now.getMinutes();
        var mm = (m < 10) ? "0" + m : m;
        var s = now.getSeconds();
        var ss = (s < 10) ? "0" + s : s;
        return year + "-" + MM + "-" + DD + " " + hh + ":" + mm + ":" + ss;
    }
    