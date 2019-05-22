$(document).ready(function() {

	var familyCheckId = $("#familyCheckId").val();

	initContentHead(familyCheckId);
	initContent(familyCheckId);
});

$("#save").click(function() {
	$.ajax({
		type : 'post',
		data : $('#user_check_form').serialize(),
		url : basePath + '/familyCheck/fillInFamilyCheckOrder',
		success : function(data) {
			alert("保存成功");
		}
	});
});

function initContentHead(familyCheckId) {
	$.ajax({
		type : 'post',
		data : {
			familyCheckId : familyCheckId
		},
		url : basePath + '/familyCheck/getFamilyCheckOrderById',
		success : function(data) {
			$("#customer_name").val(data.data.clientInfo.client_name);
			$("#customer_number").val(data.data.clientInfo.client_code);
			$("#customer_address").val(data.data.family_check_address);
			$("#customer_phone").val(data.data.family_check_tel_number);
			$("#family_check_code").val(data.data.family_check_code);
		}
	});
}

function initContent(familyCheckId) {
	$.ajax({
		type : 'post',
		data : {
			familyCheckId : familyCheckId
		},
		url : basePath + '/familyCheck/getFamilyCheckOrderContentById',
		success : function(data) {
			pushValueInToForm(data.data);
		}
	});
}

function pushValueInToForm(valueMap) {
	console.log(valueMap);
	for (dataKey in valueMap) {
		var dataValue = valueMap[dataKey];
		var key = dataKey;
		
		$(":checkbox[name=" + key + "]").each(function() {
			
			var checkbox = $(this);
			var thisVal = checkbox.val();
			if (dataValue != null) {
				dataValue = dataValue.replace("[", "");
				dataValue = dataValue.replace("]", "");
				var dataValues = dataValue.split(",");
				$(dataValues).each(function() {
					if (this == thisVal) {
						checkbox.attr("checked", "checked");
					}
				});
			}
		});
		$(":radio[name=" + key + "]").each(function() {
			var radio = $(this);
			var thisVal = radio.val();
			if (dataValue == thisVal) {
				radio.attr("checked", "checked");
			}
		});
		$("input:text[name=" + key + "]").each(function() {
			$(this).val(dataValue);
		});
		$("textarea[name=" + key + "]").each(function() {
			$(this).val(dataValue);
		});
		$("input[name=" + key + "]").filter("[type='date']").each(
				function() {
					$(this).val(dataValue);
		});
		$("input[name=" + key + "]").filter("[type='time']").each(
				function() {
					$(this).val(dataValue);
		});
		$("input[name=" + key + "]").filter("[type='datetime-local']").each(
				function() {
					$(this).val(dataValue);
		});
	}
}
