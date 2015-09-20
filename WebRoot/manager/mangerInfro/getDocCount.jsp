<%@page import="java.text.SimpleDateFormat"%>
<%@page import="assign.assignControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>
<%
    assignControl ac = new assignControl(commondb);
    java.util.Date test =new java.util.Date();
	String ClassTh = "";
	if(test.getHours()<12){
	    ClassTh = "morning";
	}else{
	    ClassTh ="afternoon";
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String sDate = sdf.format(test);
    java.sql.Date mydate = java.sql.Date.valueOf(sDate);
    
    Collection<DocCounter> rCounters =  ac.getDocCounters(ClassTh, mydate);
    Iterator<DocCounter>  iterator =rCounters.iterator();
    boolean flag =true;
    while(iterator.hasNext()){
        DocCounter  reg = iterator.next();
        if(flag){
           flag = false;
           out.println("<table><tr><td>科室  </td><td>职称  </td><td>医师姓名 </td><td>总金额</td></tr>");
        }
        out.println("<tr><td>"+reg.getDepart()+"</td><td>"+reg.getDocTitle()+"</td><td>"+reg.getDocName()+"</td><td>"+reg.getTotalMoney()+"</td></tr>");
    }
    if(!flag){
        out.println("</table>");
    }else{
        out.println("无信息 ");
    }
 %>
