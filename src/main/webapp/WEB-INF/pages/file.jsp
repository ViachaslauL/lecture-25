<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Title</title></head>
<body>
<form method="post" enctype="multipart/form-data" action="/save">
    <input name="savingFile" type="file"/>
    <label>
        <input name="directory" type="text" placeholder="Directory"/>
        <input name="fileName" type="text" placeholder="File name"/>
    </label>
    <input type="submit" value="Add file"/>
</form>
</body>
</html>
