<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<jsp:useBean id="registor" scope="page" class="worker.registorBean"></jsp:useBean>
<jsp:setProperty property="*" name="registor"/>
<%
    registorControl dc = new registorControl(commondb);
    if(dc.addRegistor(registor)){
        out.println("添加成功");    
    }else{
        out.println("添加失败 ");
    }
%>
