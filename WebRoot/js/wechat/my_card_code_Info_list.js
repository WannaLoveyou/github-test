$(function() {

	initQRCode();
});

function initQRCode() {

	$.ajax({
		type : 'post',
		data : {
			clientId : $("#clientId").val()
		},
		url : basePath + '/mobile/weiXin/getClinetCardCodeByNowTime',
		success : function(data) {

			if (data.code == 200) {
				$("#qr_code").html("");
				$("#qr_code").qrcode({
					render : "canvas", // table方式 canvas渲染方式
					width : 200, // 宽度
					height : 200, // 高度
					text : data.data
				});
			} else {
				alert(data.msg, '温馨提示');
			}

		}
	});
}

$("#refeshQRCode").click(function() {
	initQRCode();
});
