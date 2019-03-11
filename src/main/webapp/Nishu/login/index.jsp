<!DOCTYPE html>
<html lang="en">
<head>
  <title>ERP</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
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
  <h2>Login</h2>
  <form action="welcome.jsp?<%=/*"from="+request.getRequestURI()+"?"+*/request.getQueryString() %>" method="post">
    <div class="form-group">
      <label for="username">Username:</label>
      <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
    </div>
    <!--div class="form-group form-check">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="remember"> Remember me
      </label>
    </div-->
    <button type="submit" class="btn btn-primary">Login</button>
  </form>
</div>

</body>
</html>
