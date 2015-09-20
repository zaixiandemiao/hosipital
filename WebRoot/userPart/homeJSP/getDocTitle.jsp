<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="department.departmentBean"%>
<%@page import="department.departControl"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<%
  departControl dc = new departControl(commondb);
  Collection<departmentBean> arrayList=dc.getDepartmentBeansByName(request.getParameter("department"));
  out.print("...#");  
  Iterator<departmentBean> iterator = arrayList.iterator();
  Set departNames =new HashSet();
  while(iterator.hasNext()){
      departmentBean tmp = iterator.next();
      departNames.add(tmp.getDocTitle()); 
  }
  Iterator it = departNames.iterator();
  while(it.hasNext()){
      out.print(it.next().toString()+"#");
  }
%>