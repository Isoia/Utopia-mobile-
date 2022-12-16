<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form id="form1" action="./utopia_main.jsp" method="post" >
		<input id="lat" type="hidden" name="lat" value="0"/>
		<input id="lon" type="hidden" name="lon" value="0"/>
	</form>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=037f0db7b0826896716a2885521b36ab&libraries=services"></script>
	<script type="text/javascript">
	<%
		request.setCharacterEncoding("utf-8");
		String addr = (String)session.getAttribute("memaddr");
		if(addr == null){
		addr = "서울특별시 중구 세종대로 110 서울특별시청";
	}%>
		var addr = "<%=addr%>";
		const geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(addr, function(result, status) {
		    document.getElementById("lat").value = result[0].y;
		    document.getElementById("lon").value = result[0].x;
		    
		    document.getElementById("form1").submit();
		});
	</script>
	<!-- 인터넷 안될시 -->
</body>
</html>