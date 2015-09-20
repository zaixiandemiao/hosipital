<%@ page contentType="text/html; charset=GB2312"%>
<%@ page isErrorPage="true" %>
<html>
     <head><title>错误界面</title></head>
     <body>
         <h1>应用程序发生错误</h1>
                                错误原因<%=exception.toString() %>       
     </body>
</html>

