<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>

<title>Home</title>
</head>
<body>

<p>Hello ${user.name}!</p>
<p>Your password is  ${user.password}!</p>
<p>Admin ${user.admin}!</p>
<p>Locale  ${locale}!</p>

 можно использовать обычную форму
<form method="POST" action="uploadFile" enctype="multipart/form-data">
	File to upload: <input type="file" name="file"><br /> 
	<input type="submit" value="Upload"> 
	Press here to upload the file!
</form>

</body>
</html>