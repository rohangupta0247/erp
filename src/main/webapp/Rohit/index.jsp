<jsp:include page="../header.jsp"/>
  
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
            <h5 class="card-title text-center">Sign In</h5>
            <form class="form-signin"  action="welcome.jsp?<%=/*"from="+request.getRequestURI()+"?"+*/request.getQueryString() %>" method="post">
              <div class="form-label-group">
                <label for="inputEmail">Email address</label>
                <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
              </div>

              <div class="form-label-group">
                <label for="inputPassword">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
              </div>

				<div><br></div>
              <!--div class="custom-control custom-checkbox mb-3">
                <input type="checkbox" class="custom-control-input" id="customCheck1">
                <label class="custom-control-label" for="customCheck1">Remember password</label>
              </div-->
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>
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
<jsp:include page="../footer.jsp"/>