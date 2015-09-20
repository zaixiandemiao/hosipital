<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="userPart.UserCheck" %>
<% request.setCharacterEncoding("GB2312"); %>
<jsp:useBean id="user" scope="session" class="userPart.User"></jsp:useBean>
<jsp:setProperty name="user" property="*"/>
<%
    UserCheck uc=new UserCheck(user);
    /* out.println("<script type=\"text/javascript\">alert("+uc.validate()+")</script>"); */
   if(uc.validate())
    {
         session.setAttribute("user",user.getUsername());
         response.sendRedirect("../home.html");
     }else{
        out.println("用户名或密码错误，请<a href=\"logIn.html\">重新登陆</a>");
     } 
  %>
