<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="manager.managerchkBean" %>
<% request.setCharacterEncoding("GB2312"); %>
<%@ include file="/common.jsp" %>
<jsp:useBean id="manager" scope="session" class="manager.managerBean"></jsp:useBean>
<jsp:setProperty name="manager" property="*"></jsp:setProperty>
<%
    managerchkBean uc=new managerchkBean(manager);
     /* out.println("<script type=\"text/javascript\">alert("+uc.validate()+")</script>");  */
   if(uc.validate(commondb))
    {
         session.setAttribute("manager",manager.getManagerName());
         response.sendRedirect("mangerInfro/mangerInfro.html");
     }else{
        out.println("用户名或密码错误，请<a href=\"login.htm\">重新登陆</a>");
     } 
  %>