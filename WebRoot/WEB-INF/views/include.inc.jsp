<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<fmt:setBundle basename="properties/project" var="project"/>
<fmt:message key="project.name" var="projectName" bundle="${project}" />
<fmt:setBundle basename="properties/${projectName}" var="projectDetail"/>
<fmt:message key="${projectName}.view.path" var="baseViewPath" bundle="${projectDetail}" />
<fmt:message key="${projectName}.company.name" var="companyName" bundle="${projectDetail}" />

<c:set var="viewAirBottleInfoPath" value="${baseViewPath}airBottleInfo/" />
<c:set var="viewFamilyCheckOrderPhotoPath" value="${baseViewPath}familyCheckOrderPhoto/" />

<!-- 更改图标 -->
<%-- <link rel="icon" href="${pageContext.request.contextPath}/styles/images/knitimages/directsell_logo.ico" type="image/x-icon" /> --%>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/styles/images/knitimages/directsell_logo.ico" type="image/x-icon" />
<%--<link rel="bookmark" href="${pageContext.request.contextPath}/styles/images/knitimages/directsell_logo.ico" type="image/x-icon" /> --%>
 <script type="text/javascript">
 var basePath = '${contextPath }';
 
 function queryParamByFormId(form) {
		var formValues = $("#" + form).serialize();

		// 关于jquery的serialize方法转换空格为+号的解决方法
		formValues = formValues.replace(/\+/g, " "); // g表示对整个字符串中符合条件的都进行替换
		var temp = JSON.stringify(conveterParamsToJson(formValues));
		var queryParam = JSON.parse(temp);
		return queryParam;
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

	function formatDateByYYMMDD(now) {
		var year = now.getFullYear();
		var M = now.getMonth() + 1;
		var MM = (M < 10) ? "0" + M : M;
		var D = now.getDate();
		var DD = (D < 10) ? "0" + D : D;
		return year + "-" + MM + "-" + DD;
	}
</script>



