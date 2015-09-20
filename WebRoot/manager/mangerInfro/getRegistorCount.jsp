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
    
    Collection<registorCounter> rCounters =  ac.getRegistorCounters(ClassTh, mydate);
    Iterator<registorCounter>  iterator =rCounters.iterator();
    boolean flag =true;
    while(iterator.hasNext()){
        registorCounter  reg = iterator.next();
        if(flag){
           flag = false;
           out.println("<table><tr><td>挂号人员账号 </td><td>总挂号费 </td></tr>");
        }
        out.println("<tr><td>"+reg.getRegistor()+"</td><td>"+reg.getCost()+"</td></tr>");
    }
    if(!flag){
        out.println("</table>");
    }else{
        out.println("无信息 ");
    }
 %>
