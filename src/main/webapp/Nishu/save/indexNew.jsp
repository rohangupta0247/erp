<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
</head>
<body class="container">
	<form action="#">
		<div class="col-5">
			<label>NAME:</label> <input type="text" placeholder="enter your name"
				class="form-control" id="name">
		</div>

		<div class="col-5">
			<label>ADDRESS:</label> <input type="text"
				placeholder="enter your address" class="form-control" id="address">
		</div>

		<div class="col-5">
			<label>DOB:</label> <input type="date"
				placeholder="enter your date of birth" class="form-control"
				id="date">
		</div>

		<div class="col-5">
			<label>PHONE:</label> <input type="contact"
				placeholder="enter your contact number" class="form-control"
				id="number">
		</div>

		<div class="dropdown">
			<label>SELECT A LINK: </label>
			<button type="button" class="btn btn-primary dropdown-toggle"
				data-toggle="dropdown">Select....</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="#">Link 1</a>
				<a class="dropdown-item" href="#">Link 2</a>
				<a class="dropdown-item" href="#">Link 3</a>
				<a class="dropdown-item" href="#">Link 4</a>
			</div>
		</div>

		<br>

		<div class="form-check">
			<label class="form-check-label"> <input type="checkbox"
				class="form-check-input" value="">Option 1
			</label>
		</div>
		<div class="form-check">
			<label class="form-check-label"> <input type="checkbox"
				class="form-check-input" value="">Option 2
			</label>
		</div>
		<div class="form-check disabled">
			<label class="form-check-label"> <input type="checkbox"
				class="form-check-input" value="">Option 3
			</label>
		</div>

		<br>

		<div class="form-check">
			<label class="form-check-label"> <input type="radio"
				class="form-check-input" name="optradio">Option 1
			</label>
		</div>
		<div class="form-check">
			<label class="form-check-label"> <input type="radio"
				class="form-check-input" name="optradio">Option 2
			</label>
		</div>
		<div class="form-check disabled">
			<label class="form-check-label"> <input type="radio"
				class="form-check-input" name="optradio">Option 3
			</label>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>

	</form>

	<!-- jQuery first, then Tether, then Bootstrap JS. -->
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
		integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
		integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
		integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
		crossorigin="anonymous"></script>
</body>
</html>