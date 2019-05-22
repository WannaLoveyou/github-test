<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}

#allmap {
	width: 100%;
	height: 100%;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=Hp2TDzajRUMVLnsHsTZbb4gGZo7Hp3mH"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/jquery/jquery.min.js"></script>
<title>用户钢瓶位置展示</title>
</head>
<body>
	<div id="allmap"></div>
	<script type="text/javascript">
	
		// 百度地图API功能
		// 道滘镇政府 东经113.6817269764  北纬23.0102640009
		var map = new BMap.Map("allmap");
		map.centerAndZoom(new BMap.Point(113.678653,23.043315), 15);
		map.enableScrollWheelZoom();
		
		// 点聚合
		var markerClusterer = new BMapLib.MarkerClusterer(map);
		
		// 加载气瓶坐标点
		initClientCoordinates();
		
		// 多少秒刷新一次
		var s = 600;
		
		var intervalId = setInterval(function() {
			// console.log("run");
			// 加载气瓶坐标点
			initClientCoordinates();
		}, s * 1000);
		
		function initClientCoordinates(){
			// 获取当前气瓶坐标及信息,并在图上渲染
			$.ajax({
				type : 'post',
				//async : false,
				data : {
					create_time_begin_time : '${create_time_begin_time}',
					create_time_end_time : '${create_time_end_time}',
				},
				url:basePath+'/distributionWorkerPositionShow/getWorkerPositions',
				success : function(data) {
					if(data.code == 200){
						initMapPoints(data.data);
					}
				}
			});
		}
		
		function initMapPoints(list){		

			var opts = {
				width : 300,     // 信息窗口宽度
				height: 150,     // 信息窗口高度
				title: "配送工信息",
			}	
			var markers = [];
			for (var i = 0; i < list.length; i++) {
				console.log(list[i]);
				var pt = new BMap.Point(list[i].longitude,list[i].latitude);
				var marker = new BMap.Marker(pt);  // 创建标注
				
				marker.content = "<div style='font-size:15px;'>" + 
					"姓名：" + list[i].workName + 
					"</br>员工编码：" + list[i].cardCode + 
					"</br>上传时间：" + list[i].createTime + 
					"</div>";
					
				marker.addEventListener("click",function(e){
					// var p = e.target;
					// var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);			
					var thisMarker = e.target;
					var point = thisMarker.point;			

					var infoWindow = new BMap.InfoWindow(thisMarker.content, opts);  // 创建信息窗口对象 
					map.openInfoWindow(infoWindow, point); //开启信息窗口
				});
				markers.push(marker);
			}

			// 点聚合清空标记
			markerClusterer.clearMarkers();
			
			// 点聚合加载
			markerClusterer.addMarkers(markers);
		}
	</script>
</body>
</html>
