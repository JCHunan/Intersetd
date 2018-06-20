<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% String path=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.cookie.js"></script>

<link rel="stylesheet" type="text/css" href="<%=path%>/css/mycss.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/zw_pay_login.css">
<title>创想兴趣</title>

<script language='javascript' type='text/javascript'>
$(function () {
	 setTimeout(function () {
	        $(".loading").fadeToggle(3000);
	       /*  $(".B").fadeToggle(3000); */
	 }, 500);
    setTimeout(function () {
        $(".A").fadeToggle(2200);
        $(".login.on").fadeToggle(1000);
    }, 3000);
    setTimeout(function () {
        $(".D").fadeToggle(2000);
    }, 3500);
    $
});

</script>

</head>  
<body>
	<div class="layer loading"></div>
    <div class="layer A"></div>  
   <!--  <div class="layer B"></div>   -->
    <div class="layer D"></div>
    <div class="scribbling"><div></div></div>
    <div class="login on" id="userL">
			<div style="float: left;">
				<h4 class="userlogin" style="border-bottom: 3px solid #7bc6ff;">密码登录</h4>
			</div>
			<div style="clear: both;"></div>
			<p id="errMsgZone" class="error" style="display: none;">&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<p id="errMsgZone2" class="error">&nbsp;&nbsp;&nbsp;&nbsp;</p>
			<!--  <s:if test="%{#parameters.error[0]==1}">
				<p id="errMsgZone2" class="error">&nbsp;&nbsp;&nbsp;&nbsp;用户名或密码不正确</p>
			</s:if>
			<s:else>
				<p id="errMsgZone2" class="error">&nbsp;&nbsp;&nbsp;&nbsp;</p>
			</s:else> -->
			<form id="loginForm" class="loginForm1" action="<%=path%>/j_spring_security_check" method="post">
				<div class="user"><b class="ico"></b> 
					<input type="text" value="admin" class="hidden" style="visibility: hidden;width:0;height:0;position: absolute;z-index: -1;" disabled autocomplete = "off"/>
                    <input id="j_username" type="text" maxlength="32" name="j_username" style="background:transparent;" autocomplete="off" onfocus="this.style.color='#ffffff';" data-nullmsg="请输入用户名" class="input_key"/>
                    <b class="icox" id="clear" style="display: none;"></b>
				</div>
				<div class="pswd">
					<b class="ico"></b> 
					<input type="password"  value=" " style="visibility: hidden;width:0;height:0;position: absolute;z-index: -1;" disabled autocomplete = "off"/> 
                    <input id="j_password" type="password" style="background:transparent;" onfocus="this.style.color='#ffffff';" autocomplete="off" autocomplete="new-password" name="j_password" maxlength="40" data-nullmsg="请输入密码" class="j_password"/></div>
          			<p>
				   <label> 
					 <a href="javascript:void(0);" class="mobile"><span style="color: #4C8DEE; float: left;">手机验证码登录</span></a>
				   </label> 
					  <a href="javascript:void(0);" onclick="retrieve()" style="float: right; color: #ffffff; ">忘记密码？</a>
				<div style="clear: both;"></div>
				</p>
				<br> <input type="button" value="登录" class="send send1" id="send" onclick="bclick();" />
			</form>
		</div>
		<div class="login off" id="userL">
                <div style="float:left;"><h4 style="border-bottom: 3px solid #7bc6ff;">手机号登录</h4></div>	
                <div style="clear:both;"></div>
                <p id="errMsgZone3" class="error" style="display:none;" >&nbsp;&nbsp;&nbsp;&nbsp;</p>
					<p id="errMsgZone4" class="error">&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<!-- <s:if  test="%{#parameters.error[0]==1}">
					<p id="errMsgZone3" class="error">&nbsp;&nbsp;&nbsp;&nbsp;验证码错误</p>
				</s:if>
				<s:else><p id="errMsgZone4" class="error" >&nbsp;&nbsp;&nbsp;&nbsp;</p></s:else> -->
                <form id="loginForm" class="loginForm2" action="<%=path%>/j_spring_security_check" method="post" onsubmit="return checkForm(this)"> 
                    <div class="user">
                       <input id="phone" type="text" name="j_username"  placeholder="请输入手机号" maxlength="20" autocomplete="off" style="padding-left:10px;font-family:Microsoft YaHei;background:transparent;"/>
                    </div>
                    <div class="pswd" style="width: 208px; white-space:nowrap;">
                       <input id="smsCheckCode" type="text" name="j_password" placeholder="请输入手机验证码" maxlength="6" autocomplete="off" style="width:166px;heigth:26px;padding-left:10px;font-family:Microsoft YaHei;background:transparent;"></input>&nbsp;&nbsp;&nbsp;&nbsp; 
                       <input type="button" id="btnSendCode" name="code" value="获取验证码"  autocomplete="off" style="width:143px;heigth:26px;background: #4C8DEE;color: #fff;padding-left:10px;font-family:Microsoft YaHei;border-radius: 5px;" ></input>                   
                    </div>
                     <p style="text-align: left">
	                   <label><a id="pass" href="javascript:void(0);" ><span style="color:#4C8DEE;">密码登录</span></a></label>
                     </p> 
                     <br>
                    <input type="submit" value="登录" class="send send1" id="send"/>
                </form>
            </div>
   
</body>
<script type="text/javascript">  
var items = document.getElementsByClassName("layer");  
document.addEventListener('mousemove', function (evt){  
    var x = evt.clientX;  
    var y = evt.clientY;  
    //console.log(x);  
    var winWidth = window.innerWidth;  
    var winHeight = window.innerHeight;  
    var halfWidth = winWidth / 2;  
    var halfHeight = winHeight / 2;  
  
    var rx = x - halfWidth;  
    var ry = halfHeight - y;  
    var length = items.length;  
    var max = 120;  
    for (var i = 0 ; i < length ; i++) {  
         var dx = (items[i].getBoundingClientRect().width/max)*(rx / -halfWidth);  
        var dy = (items[i].getBoundingClientRect().height/max)*(ry / halfHeight);   
        items[i].style['transform'] = items[i].style['-webkit-transform'] = 'translate('+dx+'px,'+dy+'px)';  
    }  
       
}, false);  
  
</script>  
<script language='javascript' type='text/javascript'>
$(".mobile").click(function(){
	$(".login.on").slideUp(400);
	$(".login.off").fadeToggle(700);
});
$("#pass").click(function(){
	$(".login.off").slideUp(400);
	$(".login.on").fadeToggle(700);
});
$("#clear").click(function(){
	var id = $.trim($("#j_username").val());
    $("#j_username").val("");
    $("#j_username").focus();
    $(this).hide();
})
	$("#j_username").keydown(function(){
        if ($("#j_username").val().trim()!="") {
           $("#clear").show();
         }else{
           $("#clear").hide();
         }
	});
function retrieve(){
	var rpwd=$("#j_username")[0].value;
	window.location.href="<%=path %>/admin/retrievePwd.jsp?userName="+rpwd;
 }

$(function() {
	/* $("#j_username").focus(); */
	$("#j_username").keydown(function (event) {
	    if (event.which == "13") {//回车键,移动光标到密码框
	     $("#j_password").focus();
	    }
	   });
	$("#j_password").keydown(function (event) {
	    if (event.which == "13") {//回车键，用.ajax提交表单
	    }
	   });
});
	
$("#j_username").blur(function(){ 
  if($.trim($(this).val()) == ""){
	  /* $("#errMsgZone").css('display','block'); 
	  $("#errMsgZone").html("请输入用户名");
	  $("#errMsgZone2").text(""); */
  }else{
	  $("#errMsgZone2").text("");
	  $("#errMsgZone").html('&nbsp;&nbsp;&nbsp;&nbsp;');
	  $("#errMsgZone").show(); 
  }
});
 $("#j_password").blur(function(){
	   if($.trim($(this).val()) == ""){
		  /*  $("#errMsgZone").css('display','block');
		   $("#errMsgZone").html("请输入密码");
		   $("#errMsgZone2").text(""); */
	}else{
		 $("#errMsgZone2").text("");
		  $("#errMsgZone").html('&nbsp;&nbsp;&nbsp;&nbsp;');
		  $("#errMsgZone").show();
 	}
 }); 


//初始化登录输入
function initLoginForm(){ 
   var usr = document.getElementById("j_username").value; 
   var pwd = document.getElementById("j_password").value; 
   if( usr && pwd){ 
     $("#j_username").val(""); 
     $("#j_password").val("");
	} 
 } 

 function bclick(){
	 var userN=$("#j_username")[0].value;
	 var pswd=$("#j_password")[0].value;
	 if(userN==""||userN==null){
		 $("#errMsgZone").css('display','block');
		 $("#errMsgZone").html("请输入用户名");
		 $("#errMsgZone2").text("");
		 $("#j_username").focus();
		
	 }else if(pswd==""||pswd==null){
		 $("#errMsgZone").css('display','block');
		 $("#errMsgZone").html("请输入密码");
		 $("#errMsgZone2").text("");
		 $("#j_password").focus();
	 }else{
		 $(".loginForm1").submit();
	 }
 }
</script>
<script type="text/javascript"> 
 var InterValObj; //timer变量，控制时间    
 var count = 60; //间隔函数，1秒执行   
 var curCount ;//当前剩余秒数    
 var code = ""; //产生验证码    
 
 /*仿刷新：检测是否存在cookie*/  
 if($.cookie("captcha")){  
     var curCount = $.cookie("captcha");  
     $('#btnSendCode').val(curCount+'秒后重试').attr('disabled',true);  
     InterValObj = setInterval(setRemainTime,1000); 
 } 
 
//timer处理函数    
 function setRemainTime(){  
    if(curCount == 0){  
        window.clearInterval(InterValObj);// 停止计时器    
        $("#btnSendCode").removeAttr("disabled");// 启用按钮    
        $("#btnSendCode").val("重新发送验证码");  
        code = ""; // 清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
    }else{  
        curCount--;    
        $("#btnSendCode").val(curCount + "秒后重试");  
        $.cookie("captcha", curCount, {path: '/', expires: (1/86400)*curCount}); 
    }
 }

 //发送手机短信验证码  
 $("#btnSendCode").click(function() {
	 curCount = count;
	 var result = isPhoneNum();
	 if(result){
	     $("#btnSendCode").attr("disabled","true");
	     $("#btnSendCode").val(curCount + "秒后重试");  
	     InterValObj = window.setInterval(setRemainTime,1000);
	     var mobile = $.trim($("#phone").val());
	     //发送验证码到手机  
	     $.ajax({ 
	   	    url: '<%=path%>/admin/send_sendCode.action',
	        async: false,  
	        method:'get', 
	        dataType:"html",
	        data:{'mobile':mobile}, 
	        success: function (data){
	        	 if(data == "success"){  
		        	  /* alert('短信验证码已发到您的手机,请注意查收!'); */
		        	  $("#errMsgZone3").css('display','block');
		   		      $("#errMsgZone3").html("短信验证码已发到您的手机,请注意查收!");
		   		      $("#errMsgZone4").text("");
		            }else if(data =="fail" ){  
		              /* alert("您输入的手机号码有误，请联系客服进行解决");  */
		              $("#errMsgZone3").css('display','block');
		   		      $("#errMsgZone3").html("您输入的手机号码有误，请联系客服进行解决!");
		   		      $("#errMsgZone4").text("");
		            }else{
		              /* alert('短信验证码发送失败,请稍后再试!');  */
		              $("#errMsgZone3").css('display','block');
		   		      $("#errMsgZone3").html("短信验证码发送失败,请稍后再试!");
		   		      $("#errMsgZone4").text("");
		            }
	         },  
	         error: function (data){
	        	 /* alert('与服务器通讯失败，请检查通讯状态!');  */
		            $("#errMsgZone3").css('display','block');
	   		        $("#errMsgZone3").html("与服务器通讯失败，请检查通讯状态!");
	   		        $("#errMsgZone4").text("");
	         }  
	     });
	 }
}); 
 
 function isPhoneNum(){
	 var phoflag = false;
	 var mobile = $.trim($("#phone").val());
	 var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	 if(!myreg.test(mobile)){
		$(this).blur();
		/*  alert('请输入有效的手机号码!');  */
	    $("#errMsgZone3").css('display','block');
        $("#errMsgZone3").html("请输入有效的手机号码!");
        $("#errMsgZone4").text("");
	    phoflag = false; 
	 }else{
		 $.ajax({  
	         url : "<%=path%>/admin/send_isMobileExists.action",   
	         data:{'mobile':mobile},    
	         type : "POST",
	         async:false,
	         dataType : "text",   
	         success : function(data){ 
	            if(data == "fail"){
	            	/* alert("您输入的手机号码有误，请联系客服进行解决"); */
	            	$("#errMsgZone3").css('display','block');
	      		    $("#errMsgZone3").html("您输入的手机号码有误，请联系客服进行解决!");
	      		    $("#errMsgZone4").text("");
	            	document.getElementById("phone").value="";
	            	phoflag = false;
	            }else{
	            	phoflag = true;
	            	$("#errMsgZone4").text("");
	     		    $("#errMsgZone3").html('&nbsp;&nbsp;&nbsp;&nbsp;');
	     		    $("#errMsgZone3").show();
	            }
	         }  
	     });
		  if(phoflag) {
	          return true;
	      } else {
	         return false;
	     }
     }
 }
  
function checkForm(obj){
	var mobile = $.trim($("#phone").val());
	var code = $.trim($("#smsCheckCode").val());
	var flat = false;
	if(mobile!="" && mobile!=null){
		if(code!="" && code!=null){
		  $.ajax({  
	         url : "<%=path%>/admin/send_checkCode.action",   
	         data:{'code':code},    
	         type : "POST",
	         async:false,
	         dataType : "text",   
	         success : function(data){
	             if(data == "fail"){
	            	/* alert('您输入的验证码不正确,请重新输入'); */
	            	$("#errMsgZone3").css('display','block');
	     		    $("#errMsgZone3").html("您输入的验证码不正确,请重新输入!");
	     		    $("#errMsgZone4").text("");
	            	document.getElementById("smsCheckCode").value="";
	            	flat = false;
	             }else if(data == "out"){
	            	 /* alert("验证码已超时"); */
	            	 $("#errMsgZone3").css('display','block');
	      		     $("#errMsgZone3").html("验证码已超时!");
	      		     $("#errMsgZone4").text("");
	            	 document.getElementById("smsCheckCode").value="";
	            	 flat = false;
	             }else{
	            	 flat = true; 
	            	 $("#errMsgZone4").text("");
		     		 $("#errMsgZone3").html('&nbsp;&nbsp;&nbsp;&nbsp;');
		     		 $("#errMsgZone3").show();
	             }
	         }  
	      });
		}else{
			flat = false;
			/* alert("请输入手机号码"); */
			$("#errMsgZone3").css('display','block');
			$("#errMsgZone3").html("请输入验证码!");
			$("#errMsgZone4").text("");
		}
	}else{
		flat = false;
		/* alert("请输入手机号码"); */
		$("#errMsgZone3").css('display','block');
		$("#errMsgZone3").html("请输入手机号码!");
		$("#errMsgZone4").text("");
	}
	  if(flat) {
          return true;
      } else {
         return false;
     }
  }
</script>
</html> 