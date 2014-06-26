<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>
<%
	String account = request.getParameter("account");
	String email="";
	if(account.equals("kevin")){
		email = "kevin@gmail.com";
	}else if(account.equals("jimmy")){
		email = "jimmy@gmail.com";
	}
%>
	<body>
		<style type="text/css">
			pre { text-align: left; }
			label.error { color: red; margin-left: 0.5em; width: 20em; }
		</style>

		<script id="demo" type="text/javascript">
		$(document).ready(function() {
			// validate signup form on keyup and submit
			var validator = $("#modifyform").validate({
				rules: {

					account: {
						required: true,
						minlength: 2,
						remote: "jsp/userconfirm.jsp"
					},
					old_password: {
						required: true,
						minlength: 5
					},
					new_password: {
						required: true,
						minlength: 5
					},
					password_confirm: {
						required: true,
						minlength: 5,
						equalTo: "#new_password"
					},
					email: {
						required: true,
						email: true,
						remote: "jsp/emailconfirm.jsp"
					},

				},
				messages: {

					account: {
						required: "Enter a username",
						minlength: jQuery.format("Enter at least {0} characters"),
						remote: jQuery.format("{0} is already in use")
					},
					old_password: {
						required: "Provide a old password",
						rangelength: jQuery.format("Enter at least {0} characters")
					},
					new_password: {
						required: "Provide a new password",
						rangelength: jQuery.format("Enter at least {0} characters")
					},
					password_confirm: {
						required: "Repeat your new password",
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
					alert("submitted!");
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

			$("#logoutbt").click(function(){

			});
			
			$("#modify").click(function(){
			
				
					
			});
			
		});
		</script>
	
		<div id="main">
		<h1 id="banner">Modify Personal Info.</h1>
			<div id="content">

				<div id="header">

				</div>
				<div style="clear: both;"><div></div></div>


				<div class="content">
					<div id="modifybox">
					   <div id="modifytab">
						<ul>
						  <li id="logoutcurrent"><a id="loginbt">Logout</a></li>
						</ul>
					  </div>
					  <div id="modifywrap">
							<form id="modifyform" autocomplete="off" method="get" action="">
							  <table style="width: 400px" cellspacing="0" cellpadding="0">
								<tr>
									<td colspan="2" class="blue">

									編修個人資料送出前請確認資料無誤.

									</td>
								</tr>
								<tr>
									<td colspan="2" style="width: 80%" ><table style="width: 100%" cellspacing="0" cellpadding="0" class="table_3border">

										<tr>
											<td class="inlist" style="width: 200px"><label id="lbaccount" for="account">Account</label></td>
											<td class="inlist3" style="width: 200px"><input id="account" name="account" type="text" value="<%=account%>" maxlength="50" class="input"></th></td>
											<td class="status"></td>
										</tr>
										<tr>
											<td class="inlist"><label id="lpassword" for="old_password">Old Password</label></td>
											<td class="inlist3"><input id="old_password" name="old_password" type="password" maxlength="50" value="" class="input" /></td>
											<td class="status"></td>
										</tr>
										<tr>
											<td class="inlist"><label id="lpassword" for="new_password">New Password</label></td>
											<td class="inlist3"><input id="new_password" name="new_password" type="password" maxlength="50" value="" class="input" /></td>
											<td class="status"></td>
										</tr>
										<tr>
											<td class="inlist"><label id="lpassword_confirm" for="password_confirm">Confirm Password</label></td>
											<td class="inlist3"><input id="password_confirm" name="password_confirm" type="password" maxlength="50" value="" class="input"/></td>
											<td class="status"></td>
										</tr>
										<tr>
											<td class="inlist"><label id="lemail" for="email">Email Address</label></td>
											<td class="inlist3"><input id="email" name="email" type="text" value="<%=email%>" maxlength="150" class="input"></th></td>
											<td class="status"></td>
										</tr>
										  

									</table></td>
								</tr>
								<tr>
									<td style="height: 18px" colspan="2"></td>
								</tr>
								<tr>
									<td class="label"><label id="lmodifysubmit" for="modifysubmit"></label></td>
									<td class="field" colspan="2">
									<input id="modifysubmit" name="modify" type="submit" value="Modify" class="button" />
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
</html>
