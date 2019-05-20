<%/* %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERP</title>
</head>
<body>
Signup
<form action="signup/success.jsp" method="POST">
Username<input type="text" name="username">
Email<input type="text" name="email">
Name<input type="text" name="name">
Phone<input type="text" name="phone">
Password<input type="password" name="password">
<input type="submit">
</form>
</body>
</html>
<%*/ %>


<!doctype html>
<html lang="en">
<head>
<title>ERP</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<!-- link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"-->
<!-- link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous"-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<jsp:include page="/favicon.jsp" />

</head>
<body>

  
  <script type="text/javascript">
window.onload=function() {
	if(navigator.cookieEnabled==false){
		document.getElementById("cookie-message").innerHTML="<h3>Cookies not enabled, please enable cookies first</h3>";
		//document.write("<h3>Cookies not enabled, please enable cookies first</h3>");
		//document.close();
	}
}
</script>
<div id="cookie-message"></div>
  
  <div class="container">
  
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Sign Up</h5>
            <form class="form-signin"  action="signup/success.jsp" method="post">
              <div class="form-label-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="enter username here" required autofocus>
              </div>
              <div class="form-label-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" class="form-control" placeholder="enter email here" required>
              </div>
              <div class="form-label-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" class="form-control" placeholder="enter name here" required>
              </div>
              <div class="form-label-group">
                <label for="phone">Phone</label>
                <input type="text" id="phone" name="phone" class="form-control" placeholder="enter phone here" required>
              </div>
              <div class="form-label-group">
                <label for="pwd">Password</label>
                <input type="password" id="pwd" name="password" class="form-control" placeholder="enter password here" required>
              </div>

				<div><br></div>
              <!--div class="custom-control custom-checkbox mb-3">
                <input type="checkbox" class="custom-control-input" id="customCheck1">
                <label class="custom-control-label" for="customCheck1">Remember password</label>
              </div-->
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign Up</button>
              <!--hr class="my-4">
              <button class="btn btn-lg btn-google btn-block text-uppercase" type="submit">
              		<i class="fab fa-google mr-2"></i> Sign in with Google</button>
              <button class="btn btn-lg btn-facebook btn-block text-uppercase" type="submit">
              		<i class="fab fa-facebook-f mr-2"></i> Sign in with Facebook</button-->
		<!--a href="file:///C:/Users/HP/Desktop/2.html>!After you SIGN UP</a-->
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  </body>
  </html>