<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>雪花飘落</title>
    <script src="<%=path %>/js/jquery-1.11.1.min.js"></script>
    <script src="<%=path %>/js/snow/snow.js"></script>
     <style type="text/css">
        .snow-container 
        { 
	        position: fixed;
	        top: 0; 
	        left: 0; 
	        width: 100%; 
	        height: 100%; 
	        pointer-events: none; 
	        z-index: 10001; 
	        background: url(../images/snow/gg.jpg) center top no-repeat; 
	        background-size: 100% auto; 
        }
    </style>
</head>
<body>
	 <div class="snow-container"></div>
</body>
</html>