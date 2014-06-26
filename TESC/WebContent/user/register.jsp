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
			var validator = $("#signupform").validate({
				//setup jquery.validate rules
				rules: {

					account: {
						required: true,
						minlength: 2,
						remote: "/TESC/login.do?action=checkaccount"
					},
					password: {
						required: true,
						minlength: 5
					},
					password_confirm: {
						required: true,
						minlength: 5,
						equalTo: "#password"
					},
					email: {
						required: true,
						email: true,
						remote: "/TESC/login.do?action=checkemail"
					},

				},
				//set up jquery.validate alert messages
				messages: {

					account: {
						required: "Enter a username",
						minlength: jQuery.format("Enter at least {0} characters"),
						remote: jQuery.format("{0} is already in use")
					},
					password: {
						required: "Provide a password",
						rangelength: jQuery.format("Enter at least {0} characters")
					},
					password_confirm: {
						required: "Repeat your password",
						minlength: jQuery.format("Enter at least {0} characters"),
						equalTo: "Enter the same password as above"
					},
					email: {
						required: "Please enter a valid email address",
						minlength: "Please enter a valid email address",
						remote: jQuery.format("{0} is already in use")
					},

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
					$.ajax({ 				
		                 type:"POST",  
		                 url:"kaptcha.jsp",  
		                 data:{account:$("#account").val(),  
		                       password:$("#password").val(),
							   email:$("#email").val(),
							   kaptchafield:$("#kaptchafield").val(),    
		                      },  
		                 dataType:"json",  
		                 cache:false,  
		                 success:function(json){  
		                     
							 if(json.success=='true'){  
		                          alert(" login seccessed!");
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
				
					alert(" qqqqqq");	
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

			$("#loginbt").click(function(){
				$("#main").load("login.html",function(){
					$("#main").fadeIn('slow');}
				);
			});
			
		});
		</script>
	
		<div id="main">
		<h1 id="banner">NTNU Teoc Register</h1>
			<div id="content">

				<div id="header">

				</div>
				<div style="clear: both;"><div></div></div>


				<div class="content">
					<div id="signupbox">
					   <div id="signuptab">
						<ul>
						  <li id="logincurrent"><a id="loginbt">Login</a></li>
						</ul>
					  </div>
					  <div id="signupwrap">
							<form id="signupform" autocomplete="off" method="get" action="">
								<table style="width: 400px" cellspacing="0" cellpadding="0">
								<tr>
									<td colspan="2" class="blue">

									個人資料請詳細填寫.

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
										<tr>
											<td class="inlist"><label id="lpassword_confirm" for="password_confirm">Confirm Password</label></td>
											<td class="inlist3"><input id="password_confirm" name="password_confirm" type="password" maxlength="50" value="" class="input" /></td>
											<td class="status"></td>
										</tr>
										<tr>
											<td class="inlist"><label id="lemail" for="email">Email Address</label></td>
											<td class="inlist3"><input id="email" name="email" type="text" value="" maxlength="150" class="input" /></td>
											<td class="status"></td>
										</tr>
										<tr>
											<td class="inlist">
												<img src="kaptcha.jpg" width="200px" id="kaptchaImage"/>
												
												<script type="text/javascript">
												$(function(){
													$('#kaptchaImage').click(function () { 
														$(this).hide()
														  .attr('src', 'kaptcha.jpg?' + Math.floor(Math.random()*100) )
														  .fadeIn(); 
														})
												});
												</script>
											</td>
											<td class="inlist3" valign="top">
												<input type="text" name="kaptchafield" id="kaptchafield" value="security code" class="input"></br>
												<br /><small>Click image to get a new one.</small>
											</td>
										</tr>
										
									</table></td>
								</tr>
								<tr>
									<td style="height: 18px" colspan="2"></td>
								</tr>
								<tr>
									<td class="label"><label id="lsignupsubmit" for="signupsubmit"></label></td>
									<td class="field" colspan="2">
									<input id="signupsubmit" name="signup" type="submit" value="Signup" class="button" />
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
