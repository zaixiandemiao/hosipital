<%@page import="userPart.userControl"%>
<%@page import="userPart.User"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="userPart.UserCheck" %>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<%
    String user=session.getAttribute("user").toString();
   
    userControl uc=new userControl(commondb);
    User userBean =  uc.getUserBean(user);
    String originPass = request.getParameter("originPass");
    userBean.setPasswd(originPass);
    UserCheck  ucCheck = new UserCheck(userBean);
    if(!ucCheck.validate()){
        out.println("原始密码验证错误 ");
        return ;
    }
    userBean.setPasswd(request.getParameter("changePass"));
    if(uc.update(userBean))
       out.println("修改成功");
    else{
       out.println("修改失败");
    }
    /* out.println("<script type=\"text/javascript\">alert("+uc.validate()+")</script>"); */
   
  %>