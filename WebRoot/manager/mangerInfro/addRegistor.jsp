<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<jsp:useBean id="registor" scope="page" class="worker.registorBean"></jsp:useBean>
<jsp:setProperty property="*" name="registor"/>
<%
    registorControl dc = new registorControl(commondb);
    if(dc.addRegistor(registor)){
        out.println("��ӳɹ�");    
    }else{
        out.println("���ʧ�� ");
    }
%>
