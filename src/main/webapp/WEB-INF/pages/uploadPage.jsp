<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>Carga Archivo</title>
</head>

<body>
	<h2>Carga Archivo - Insert en base de datos</h2>


	<form action="uploadFile" method="POST" enctype="multipart/form-data">
		 <input type="file" name="file"value="Browse File" />
		 <br /> <br /> Guardar archivo en directorio: <input type="submit" value="Guardar" /> <br /> <br />

		<h4 style="color: green">${message}</h4>
		<br />

		<h3>Guardar datos de Excel en la base de datos?</h3>
		<a href="saveData"><b>SI</b></a> &nbsp &nbsp <a href="/"><b>NO</b></a>
	</form>
</body>
</html>