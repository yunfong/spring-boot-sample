<%--
  User: yunfeng.liu
  Date: 2017/4/13
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Multiple File Upload</title>
    <link type="text/css" rel="stylesheet"  href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <form action="/file/upload/multiple" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label class="control-label">Select File</label>
        </div>
        <div class="form-group">
            <input type="file" class="form-control"  name="file">
            <input type="file" class="form-control"  name="file">
            <input type="file" class="form-control"  name="file">
            <input type="file" class="form-control"  name="file">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-success">Upload</button>
        </div>
    </form>
</div>
</body>
</html>