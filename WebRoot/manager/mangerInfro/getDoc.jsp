<%@page import="doctor.Doctor"%>
<%@page import="doctor.docControl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<%
  docControl dc = new docControl(commondb);
  Collection<Doctor> arrayList=dc.getDoctors();
  if(!arrayList.isEmpty()){
       out.println("<h1>医生表</h1>");
       out.println("<span>医生姓名</span><span>上午限制人数</span><span>下午限制人数</span><span>职称 </span><span>科室 </span>");
  }else{
       out.println("<h1>表为空 </h1>");
  }  
  Iterator<Doctor> iterator = arrayList.iterator();
  while(iterator.hasNext()){
      Doctor tmpBean =iterator.next();
      out.println("<form method=\"post\" action=\"changeDoc.jsp\">"+"<input type=\"hidden\" name=\"docId\" value=\""+tmpBean.getDocId()+"\"/><input type=\"text\" name=\"docName\" value=\""+tmpBean.getDocName()+"\""+"/><input type=\"text\" name=\"morLimit"+"\"value=\""+tmpBean.getMorLimit()+"\"/>"+"<input type=\"text\" name=\"aftLimit\" value=\""+tmpBean.getAftLimit()+"\"/>"
        +"<input type=\"text\" name=\"docTitle\" value=\""+tmpBean.getDocTitle()+"\"/>"+"<input type=\"text\" name=\"departName\" value=\""+tmpBean.getDepartName()+"\"/>"+"<input type=\"submit\" value=\"确认修改\" /></form>");   
  }
  out.println("添加一个医生  <br>");
  out.println("<form method=\"post\" action=\"addDoc.jsp\">"+"<input type=\"text\" name=\"docName\" value=\"\""+" /><input type=\"text\" name=\"morLimit"+"\"value=\"\"/>"+"<input type=\"text\" name=\"aftLimit\" value=\"\" />"
        +"<input type=\"text\" name=\"docTitle\" value=\"\"/>"+"<input type=\"text\" name=\"departName\" value=\"\"/>"+" <input type=\"submit\" value=\"确认添加 \" /></form>");   
  
%>