<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="department.departmentBean"%>
<%@page import="department.departControl"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<%
  departControl dc = new departControl(commondb);
  Collection<departmentBean> arrayList=dc.getDepartmentBeans();
  if(!arrayList.isEmpty()){
       out.println("<h1>科室表</h1>");
       out.println("<span>医生姓名</span><span>医生职称</span><span>诊疗费用</span>");
  }  
  Iterator<departmentBean> iterator = arrayList.iterator();
  while(iterator.hasNext()){
      departmentBean tmpBean =iterator.next();
      out.println("<form method=\"post\" action=\"changeDepart.jsp\">"+"<input type=\"hidden\" name=\"id\" value=\""+tmpBean.getId()+"\"/><input type=\"text\" name=\"Name\" value=\""+tmpBean.getName()+"\""+"/><input type=\"text\" name=\"docTitle"+"\"value=\""+tmpBean.getDocTitle()+"\"/>"+"<input type=\"text\" name=\"treatcost\" value=\""+tmpBean.getTreatcost()+"\"/>"
        +"<input type=\"submit\" value=\"确认修改\" /></form>");   
  }
  out.println("添加一个科室 <br>");
  out.println("<form method=\"post\" style=\"display:block;\" action=\"addDepart.jsp\">"+"<input type=\"hidden\" name=\"id\" value=\"0\"/><input type=\"text\" name=\"Name\" value=\"\" />"+"<input type=\"text\" name=\"docTitle"+"\"value=\"\"/>"+"<input type=\"text\" name=\"treatcost\" value=\"\"/>"
        +"<input type=\"submit\" value=\"确认\" /></form>");
%>