<%@page import="doctor.docWork"%>
<%@page import="doctor.docControl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="department.departmentBean"%>
<%@page import="department.departControl"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<%
  docControl dc = new docControl(commondb);
  docWork dw=dc.getDocWork(request.getParameter("docName"));
  if(dw!=null){
       out.println("<form action=\"changeDocWork.jsp\" method=\"post\">");
       out.println("<input type=\"hidden\" name=\"id\" value=\""+dw.getId()+"\"/>");
       out.println("<span>ҽ������</span> <input type=\"text\" name=\"docName\" value=\""+dw.getDocName()+"\"/> <br>");
       out.println("<span>����һ </span> <input type=\"text\" name=\"mon\" value=\""+dw.getMon()+"\"/> <br>");
       out.println("<span>���ڶ�</span> <input type=\"text\" name=\"tues\" value=\""+dw.getTues()+"\"/> <br>");
       out.println("<span>������</span> <input type=\"text\" name=\"wed\" value=\""+dw.getWed()+"\"/> <br>");
       out.println("<span>������</span> <input type=\"text\" name=\"thurs\" value=\""+dw.getThurs()+"\"/> <br>");
       out.println("<span>������</span> <input type=\"text\" name=\"fri\" value=\""+dw.getFri()+"\"/> <br>");
       out.println("<input type=\"submit\"  value=\"ȷ���޸� \"/> </form>");       
  }else{
       out.println("��ѯʧ��");
  }
%>
