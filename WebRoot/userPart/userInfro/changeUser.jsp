<%@page import="userPart.userControl"%>
<%@page import="userPart.User"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="userPart.UserCheck" %>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<%
    User  user=(User)session.getAttribute("user");
    String realName=request.getParameter("realName");
    String idCard=request.getParameter("idCard");
    String tel =request.getParameter("tel");
    user.setRealName(realName);user.setIdCard(idCard);user.setTel(tel);
    userControl uc=new userControl(commondb);
    if(uc.update(user)){
        out.println("�޸���Ϣ�ɹ�������<a href=\"userInfro.html\">����</a>");
    }
    else{
       out.println("�޸���Ϣʧ�ܣ�����<a href=\"userInfro.html\">����</a>");     
    }
    /* out.println("<script type=\"text/javascript\">alert("+uc.validate()+")</script>"); */
   
  %>
