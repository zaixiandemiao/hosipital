<%@page import="worker.registorControl"%>
<%@page import="worker.registorchk"%>
<%@ include file="/common.jsp" %>
<%@ page contentType="text/html; charset=GB2312"%>
<% request.setCharacterEncoding("GB2312"); %>
<jsp:useBean id="worker" scope="session" class="worker.registorBean"></jsp:useBean>
<jsp:setProperty name="worker" property="*"></jsp:setProperty>
<%
    registorControl rgc = new registorControl(commondb);
    registorchk rc=new registorchk(worker);
    /* out.println("<script type=\"text/javascript\">alert("+uc.validate()+")</script>"); */
   if(rc.validate(rgc))
    {
         session.setAttribute("worker",worker.getRegistorName());
         response.sendRedirect(response.encodeRedirectURL("worker/register.html"));
     }else{
        out.println("用户名或密码错误，请<a href=\"login.html\">重新登陆</a>");
     } 
  %>
