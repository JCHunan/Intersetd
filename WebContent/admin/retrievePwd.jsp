<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 String path = request.getContextPath(); 
%>
<%
String userName=request.getParameter("userName");
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/verify.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/validform/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/skins/idialog.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/helpcenter/zw_pay.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/verify.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/zw_pay_login.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/helpcenter/zw_style.css">

<title>输入账号</title>
</head>
<body>
	<div class="wrapper">
        
<div class="header2">
    <div class="center">
         <!-- 需要一个变量  显示银行LOGO图片或银行商户平台LOGO图片地址 -->
            <div class="logo"><a href="<%=path %>/"><img id="jq_login_logo_img" src="<%=path %>/images/logo2.png" width="68" height="68"></a></div>
        
        <div class="fast_link">                
        	<ul>
               <!--  <li><a target="_blank">帮助中心</a></li>
                <li><a target="_blank">公告中心</a></li> -->
                <%-- <li class="last"><a target="_blank">开发文档</a></li> --%>
            </ul>
        </div>
    </div>
</div>
        <!--password_bg atsrt-->
        <div class="password_bg">
            <div class="title">找回密码</div>
            <div class="num_box">
            	<p class="img"><span class="span1 on">1</span><span class="span2">2</span><span class="span3">3</span><span class="span4">4</span></p><!--end为已经过步骤的颜色，on为当前步骤颜色-->
                <p class="num"><span class="span1 on">填写账号</span><span class="span2">身份验证</span><span class="span3">设置新密码</span><span class="span4">完成</span></p><!--on为当前步骤颜色-->
            </div>
            <div class="step_1"><!--step_1 atsrt-->
                <div class="input_box">
                     <div class="">您正在使用重置密码功能，请在以下输入您的账户信息</div>
	                 <form id="passwordForm"  method="post">
	                  <div class="input_bg"><div id="passwordForm_error" class="error_tip"></div>
	                  <input id="userN" type="hidden" value=""/>
	                  <input type="text" placeholder="用户账号" name="username" id="uname" value="<%=userName %>" data-nullmsg="您输入的账号有误" ></div>
	                    <div class="input_bg code_input">
	                    <div id="randCode_error" class="error_tip"></div>
	                     <!--  <input id="Txtidcode" placeholder="验证码" type="text" name="randCode" data-nullmsg="您输入的验证码有误"  /> -->
	                      <div id="mpanel4"></div>
	                    </div>
							<span id="err"><font id="code" color='red'></font> </span>
	                    <input type="button" value="确认" class="password_button password_button2" id="butn" onclick="check();">
<!-- 	                    <input type="submit" value="确认" class="password_button password_button2" id="butn"> -->
	                    <!--<p class="agent"><a href="#">代理商申请</a></p>-->
	                </form>
                </div>
                <div class="tip">温馨提示：该功能仅支持找回本帐号登录密码</div>
            </div><!--step_1 end-->
            <div class="step_2 none"><!--step_2 atsrt-->
            	<div class="step_tit">请确认您的预留信息，并选择验证方式，我们将发送验证短信至您的联系方式进行验证</div>
                <div class="input_box">
                    <div id="message_button" class="password_button message_button"><span>短信验证</span></div>
                    <div class="input_bg code_input"><div id="step_2_error" class="error_tip"></div></div>
                   <!--  <div id="email_button" class="password_button email_button" type="2"><span>邮箱验证</span></div> -->
                </div>
            </div><!--step_2 end-->   
            <div id="step_message_sms" class="step_message none"><!--step_message_sms atsrt-->
            	<div class="step_tit">请确认您的预留信息，并选择验证方式，我们将发送验证短信至您的联系方式进行验证</div>
                <div class="input_box">
                	<form id="step_sms_form" action="/help/pwd/checkVerify" method="POST">
                		<input type="hidden" name="type" value="1" />
	                    <div class="input_bg code_input"><div id="phoneNumber_error" class="error_tip"></div><input type="text" id="phone" name="phoneNumber" placeholder="请输入预留手机号" data-nullmsg="请输入预留手机号"><input style="padding-left:0px;" type="button" id="send_sms_code" class="code_num" value="点击发送"/><!--点击后class="disable">60S后重新获取--></div>
	                    <div class="input_bg"><div id="verify_code_error" class="error_tip"></div><input id="SmsCheckCode"  type="text" name="verifyCode" placeholder="输入验证码" data-nullmsg="请输入短信验证码"></div>
	                    <input type="button" id="send" class="password_button password_button2" onclick="checkForm();" value="确认" disabled/>
                    </form>
                </div>
            </div><!--step_message_sms end-->     
           <!--  <div id="step_message_email" class="step_message none">step_message_email atsrt
            	<div class="step_tit">请确认您的预留信息，并选择验证方式，我们将发送验证短信至您的联系方式进行验证</div>
                <div class="input_box">
                	<form id="step_email_form" action="/help/pwd/checkVerify" method="POST">
                		<input type="hidden" name="type" value="2" />
	                    <div class="input_bg code_input"><div id="email_error" class="error_tip"></div><input type="text" name="email" placeholder="请输入预留邮箱" data-nullmsg="请输入预留邮箱"><input type="button" id="send_email_code" class="code_num" value="点击发送" />点击后class="disable">60S后重新获取</div>
	                    <div class="input_bg"><div id="verify_code_error" class="error_tip"></div><input type="text" name="verifyCode" placeholder="输入验证码" data-nullmsg="请输入邮箱验证码"></div>
	                    <input type="submit" class="password_button password_button2" value="确认" />
                    </form>
                </div>
            </div> -->
            <!--step_message_email end--> 
            <div class="step_3 none"><!--step_3 atsrt-->
                <div class="input_box">
                    <div class="">请输入您的新密码</div>
<!--                     <form id="step3_update_password_form" action="/help/pwd/updatePwd" method="POST"> -->
	                    <div class="input_bg">
	                    <input type="password" style="visible:hidden;width:0;height:0;position: absolute;z-index: -1;">
	                    <div id="password_error" class="error_tip"></div>
	                    <input type="password" name="password" id="newPlainPassword" autocomplete="off" placeholder="新密码为8~16位字母和数字组成" data-nullmsg="请输入新密码">
	                    </div>
	                    <div class="input_bg">
	                    <input type="password" style="visible:hidden;width:0;height:0;position: absolute;z-index: -1;">
	                    <div id="password2_error" class="error_tip"></div>
	                    <input type="password" name="password2" id="newPswd" autocomplete="off" placeholder="确认新密码" data-nullmsg="请输入确认密码">
	                    </div>
	                    <input type="password" style="display: none;width:0;height:0;position: absolute;">
	                    <span><font id="error" color='red'></font> </span>
	                    <input type="button" class="password_button password_button2" onclick="checkpwd();" value="确认" />
                   <!--  </form> -->
                </div>
            </div><!--step_3 end-->   
            
            <div class="step_4 none"><!--step_4 atsrt-->
            	<div class="step_ok"><img src="<%=path %>/images/helpcenter/success.png" width="100" height="100"></div>
            	<div class="ok_txt">你的密码已经重置</div>
                <div class="ok_tip">请妥善保管好您的相关信息，以免泄露</div>
                <div class="input_box">
                    <div class="password_button" onclick="ReturnLogin()">立即登录
                    	<%-- <input type="button" onclick="window.location.href('<%=path %>/')" value="立即登录"/> --%>
                    </div>
                    <%-- <div class="password_button"><a href="<%=path %>/">立即登录</a></div> --%>
                </div>
            </div><!--step_4 end-->
            
        </div><!--password_bg end-->
        <!-- <div class="footer_fixed2"></div> -->
	<div class="login_footer_fixed">
		<!-- <p></p> -->
	</div>
    </div>
<script type="text/javascript">
function ReturnLogin(){
	window.location.href="<%=path %>/admin/login.jsp";
}

</script>
<script type="text/javascript">
var InterValObj; //timer变量，控制时间    
var count = 60; //间隔函数，1秒执行   
var curCount;//当前剩余秒数    
var code = ""; //产生验证码    
var codeLength = 6;//验证码长度
$("#uname").blur(function(){
	 var username=$("#uname").val();
	 var reg = /^\d{4,16}$/gi;
	 if(reg.test(username)){
	 $.ajax({
		 	cache: true,
			type:"post",
			url:"<%=path %>/admin/admin_gainUsera.action",
		 	data:{"username":username},
			dataType:"html",
			success:function(data){
				if(data!="success"){
					$("#code").text("渠道编号输入有误!").css("color","red");
					$("#Txtidcode").attr('disabled',true);
				}else{
					$("#userN").val(username);
					$("#code").text("");
					$("#Txtidcode").attr('disabled',false);
				}
			}
		});
	 }else{
		 $("#uname").val("");
		 $("#code").text("渠道编号输入有误!").css("color","red");
	 }
});
$("#Txtidcode").blur(function(){
	 var IsBy = $.idcode.validateCode();
	 if(IsBy){
		 $("#code").text("");
	 }else{
		 $("#Txtidcode").val(""); // 将验证码清空
		 $.idcode.setCode(); 
		 /* reload();  //验证失败后需要更换验证码 */
	 }
});
function check(){
	var username=$("#uname").val();
	var txtid=$("#Txtidcode").val();
	var code=$("#code").val();
	var IsBy = $.idcode.validateCode();
	if(IsBy){
		$.ajax({
		 	cache: true,
			type:"post",
			url:"<%=path %>/admin/admin_gainUsera.action",
		 	data:{"username":username},
			dataType:"html",
			success:function(data){
				if(data!="success"){
					$("#code").text("渠道编号输入有误!").css("color","red");
					$("#Txtidcode").attr('disabled',true);
				}else{
					if(((username&&txtid)!='')&&code==''){
						$(".img .span1 on").removeClass('on');
						$(".num .span1 on").removeClass('on');
						$(".step_1").addClass('none');
			            $(".img .span2").addClass('on');
			            $(".num .span2").addClass('on');
			            $(".step_2").removeClass('none');
			            $("#userN").val(username);
			            $("#uname").val("");
			            $("#Txtidcode").val("");
					}
				}
			}
		});
	
	}else{
		$("#code").text("验证码输入有误!").css("color","red");
	}
}
$("#message_button").on("click",function(){
	$(".step_2").addClass('none');
	$(".step_message").removeClass('none');
});
/* function click(){
	$(".step_2").addClass('none');
	$(".step_message").removeClass('none');
} */
$("#send_sms_code").on("click",function(){
	 var username=$("#userN").val();
	 curCount = count;  
	 var mobile = $.trim($("#phone").val());
	 var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	 if(!myreg.test($("#phone").val())){
		 alert('请输入有效的手机号码!'); 
		 return false;
	 }else if(mobile !="" && mobile!=null){ 
		//发送验证码到手机  
	     $.ajax({ 
	   	    url: '<%=path%>/admin/send_sendCodeUser.action',
	        async: false,  
	        method:'get', 
	        dataType:"html",
	        data:{'mobile':mobile,'username':username}, 
	        success: function (data){
	        	if(data =="fail"){
	        		alert("您输入的手机号码有误，请联系客服进行解决");
	        	}else if(data == "success"){ 
	        		 $("#send_sms_code").attr("disabled","true");
	        	     $("#send_sms_code").val(curCount + "秒后重试");    
	        	     InterValObj = window.setInterval(SetRemainTime,1000); //启动计时器，1秒执行一次    
	        		$("#send").removeAttr("disabled");
	        		alert('短信验证码已发到您的手机,请注意查收!');  
	            }else{
	            	alert('短信验证码发送失败,请稍后再试!');
	            }  
	         },  
	         error: function (data){
	           alert('与服务器通讯失败，请检查通讯状态!');  
	         }  
	     });
	  }else{
		  alert("手机号码不能为空");
	  }
});
//timer处理函数    
function SetRemainTime(){  
   if(curCount == 0){  
       //验证码失效,重新发送  
       window.clearInterval(InterValObj);// 停止计时器    
       $("#send_sms_code").removeAttr("disabled");// 启用按钮    
       $("#send").removeAttr("disabled");//确定按钮    
       $("#send_sms_code").val("重新发送");
       code = ""; // 清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
   }else{
       curCount--;    
       $("#send_sms_code").val(curCount + "秒后重试");    
   }    
} 

function checkForm(){
	var code = $.trim($("#SmsCheckCode").val());
	var flat = false;
	  $.ajax({  
       url : "<%=path%>/admin/send_checkCode.action",   
       data:{'code':code},    
       type : "POST",
       async:false,
       dataType : "text",   
       success : function(data){
           if(data == "fail"){
          	alert('您输入的验证码不正确,请重新输入');
          	document.getElementById("SmsCheckCode").value="";
          	flat = false;
           }else if(data == "out"){
          	 alert("验证码已超时");
          	 document.getElementById("SmsCheckCode").value="";
          	 flat = false;
           }else{
          	 flat = true; 
           }
       }  
    });
	  if(flat) {
          $("#phone").val("");
          $("#SmsCheckCode").val("");
		  $(".img .span2 on").removeClass('on');
		  $(".num .span2 on").removeClass('on');
		  $(".step_message").addClass('none');
          $(".img .span3").addClass('on');
          $(".num .span3").addClass('on');
          $(".step_3").removeClass('none');
    } else {
        $("#SmsCheckCode").val("");
   }
}
$("#newPlainPassword").blur(function(){
	var reg = /^[0-9a-zA-Z]{8,20}$/gi;
	if(!reg.test($("#newPlainPassword").val())){
		$("#error").text("密码格式输入有误!").css("color","red");
		$("#newPlainPassword").val("");
	}else{
		$("#error").text("");
	}
});
$("#newPswd").blur(function(){
	var plpwd=$("#newPlainPassword").val();
	var newPswd=$("#newPswd").val();
	if(plpwd!=newPswd){
		$("#newPswd").val("");
		$("#newPlainPassword").val("");
		$("#error").text("新旧密码输入不一致!").css("color","red");
	}else{
		$("#error").text("");
	}
});
function checkpwd(){
	var plpwd=$("#newPlainPassword").val();
	var newPswd=$("#newPswd").val();
	var text=$("#error").val();
	var username=$("#userN").val();
	if((plpwd==newPswd)&&text==''&&newPswd!=''){
		 $.ajax({ 
		   	    url: '<%=path %>/admin/admin_updatePwd.action',
		        async: false,  
		        type:'post',
		        dataType:'html',
		        data:{'username':username,'newPwd':newPswd,'newPlainPassword':plpwd},
		        success: function (data){
		         if(data=="success"){
		        	 $(".img .span3 on").removeClass('on');
			   		 $(".num .span3 on").removeClass('on');
			   		 $(".step_3").addClass('none');
		             $(".img .span4").addClass('on');
		             $(".num .span4").addClass('on');
		             $(".step_4").removeClass('none');
		             $("newPswd").val("");
		     		 $("newPlainPassword").val("");
					}else if(data=="error"){
						$("#error").text("新旧密码输入不一致!").css("color","red");
					}
				},  
		         error: function (data){
		           alert('服务超时，请检查当前状态!');
		         }
		     });
	}else{
		$("#error").text("密码输入有误!").css("color","red");
	}
}
$('#mpanel4').slideVerify({
	type : 2,		//类型
	vOffset : 5,	//误差量，根据需求自行调整
	vSpace : 5,	//间隔
	imgName : ['dota.png', 'pishen.png'],
	imgSize : {
		width: '400px',
		height: '200px',
	},
	blockSize : {
		width: '40px',
		height: '40px',
	},
	barSize : {
		width : '400px',
		height : '40px',
	},
	ready : function() {
	},
	success : function() {
		//alert('验证成功，添加你自己的代码！');
		//......后续操作
	},
	error : function() {
//	        	alert('验证失败！');
	}
	
});
</script>
</body>
</html>

