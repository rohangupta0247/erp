<!doctype html>
<html lang="en">
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="bootstrap.min.css"> -->
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>MAJOR PROJECT</title>
<!-- <link rel="stylesheet" type="text/css" href="bootstrap.css"> -->

</head>
<body class="container">



	<form action="#">
		<div class="form-group">
			<label>NAME:</label> <input type="text" placeholder="enter your name"
				class="form-control" id="name">
		</div>

		<div class="form-group">
			<label>ADDRESS:</label> <input type="text"
				placeholder="enter your address" class="form-control" id="address">
		</div>

		<div class="form-group">
			<label>DOB:</label> <input type="date"
				placeholder="enter your date of birth" class="form-control"
				id="date">
		</div>

		<div class="form-group">
			<label>PHONE:</label> <input type="contact"
				placeholder="enter your contact number" class="form-control"
				id="number">
		</div>

		<div class="dropdown">
			<label>SELECT A LINK: </label>
			<button type="button" class="btn btn-primary dropdown-toggle"
				data-toggle="dropdown">Select....</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="#">Link 1</a> <a
					class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item"
					href="#">Link 3</a> <a class="dropdown-item" href="#">Link 4</a>
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



	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<!-- <script src="jquery-3.3.1.slim.min.js"></script>
    <script src="popper.min.js"></script>
    <script src="bootstrap.min.js"></script>  -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script
		src="https://cdn.rawgit.com/twbs/bootstrap/v4-dev/dist/js/bootstrap.js"></script>
</body>
</html>