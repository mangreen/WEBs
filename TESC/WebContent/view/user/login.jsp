<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>

	<body>
		<style type="text/css">
			pre { text-align: left; }
			label.error { color: red; margin-left: 0.5em; width: 20em; }
		</style>
		<script id="demo" type="text/javascript">
		$(document).ready(function() {
			// validate signup form on keyup and submit
			var validator = $("#loginform").validate({
				rules: {
				
					account: {
						required: true,
						minlength: 2
						//remote: "userconfirm.jsp"
					},
					password: {
						required: true,
						minlength: 5
					}
									
				},
				messages: {

					account: {
						required: "Enter a username",
						minlength: jQuery.format("Enter at least {0} characters"),
						remote: jQuery.format("{0} is already in use")
					},
					password: {
						required: "Provide a password",
						rangelength: jQuery.format("Enter at least {0} characters")
					}
					
				},
				// the errorPlacement has to take the table layout into account
				errorPlacement: function(error, element) {
					if ( element.is(":radio") )
						error.appendTo( element.parent().next().next() );
					else if ( element.is(":checkbox") )
						error.appendTo ( element.next() );
					else
						error.appendTo( element.parent().next() );
				},
				// specifying a submitHandler prevents the default submit, good for the demo
				submitHandler: function() {
					
					/*USE AJAX SEND FORM*/ 
		            $.ajax({  
		                 type:"POST",  
		                 url:"/TESC/login.do?action=login",  
		                 data:{account:$("#account").val(),  
		                       password:$("#password").val()
		                      },  
		                 dataType:"json",  
		                 cache:false,  
		                 success:function(json){  
		                     
							 if(json.success=='true'){  
		                          alert(json.account+" login seccessed!");
								  //$("#reginfo").html("login seccessed");  
		                          //$("#reginfo").css("color","green");  
		                          //setTimeout(sendIndex,1500);  
		                     }else{  
		                          alert("login failed!");
								  //$("#reginfo").html("login failed");  
		                          //$("#reginfo").css("color","red");  
		                     }  
		                 }  
		             });//form1.submit(); 
				
					//alert("submitted!");
				},
				// set this class to error-labels to indicate valid fields
				success: function(label) {
					// set &nbsp; as text for IE
					label.html("&nbsp;").addClass("checked");
				}
			});
			
			// propose username by combining first- and lastname
			//$("#username").focus(function() {
			//	var firstname = $("#firstname").val();
			//	var lastname = $("#lastname").val();
			//	if(firstname && lastname && !this.value) {
			//		this.value = firstname + "." + lastname;
			//	}
			//});
			
			$("#signupbt").click(function(){
				$("#main").load("user/register.jsp",function(){
					$("#main").fadeIn('slow');}
				);
			});

		});
		</script>
		
		<div id="main">
		<h1 id="banner">NTNU Teoc Login</h1>
			<div id="content">

				<div id="header">

				</div>
				<div style="clear: both;"><div></div></div>


				<div class="content">
					<div id="loginbox">
					   <div id="logintab">
							<tr>
								<ul>
								  <li id="signupcurrent"><a id="signupbt">Signup</a></li>
								</ul>
								<ul>
								  <li id="emailpassword"><a href=" ">Forgot Password?</a></li>
								</ul>
							</tr>
						</div>
						<div id="lgoinwrap">
							<form id="loginform" autocomplete="off" method="get" action="">
								<table style="width: 400px" cellspacing="0" cellpadding="0">
								<tr>
									<td colspan="2" class="blue">

									進入師培網前,請先登入.

									</td>
								</tr>
								<tr>
									<td colspan="2" style="width: 80%" ><table style="width: 100%" cellspacing="0" cellpadding="0" class="table_3border">

									
										<tr>
											<td class="inlist" style="width: 200px"><label id="lbaccount" for="account">Account</label></td>
											<td class="inlist3" style="width: 200px"><input id="account" name="account" type="text" value="" maxlength="50" class="input" /></td>
											<td class="status"></td>
										</tr>
										<tr>
											<td class="inlist"><label id="lpassword" for="password">Password</label></td>
											<td class="inlist3"><input id="password" name="password" type="password" maxlength="50" value="" class="input" /></td>
											<td class="status"></td>
										</tr>
								  								  
									</table></td>
								</tr>
								<tr>
									<td style="height: 18px" colspan="2"></td>
								</tr>
								 
								  <tr>
									<td class="label"><label id="lbloginsubmit" for="lgoinsubmit"></label></td>
									<td class="field" colspan="2">
									<input id="loginsubmit" name="login" type="submit" value="Login" class="button" />
									</td>
								  </tr>

								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
