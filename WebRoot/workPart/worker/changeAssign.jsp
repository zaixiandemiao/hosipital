<%@page import="doctor.Doctor"%>
<%@page import="doctor.docControl"%>
<%@page import="department.departControl"%>
<%@page import="assign.assignControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>
<%
   assignControl  aControl = new assignControl(commondb); 
   
   String name,depart;
   int title,waitnum,hcid;
   name=request.getParameter("name");
    depart=request.getParameter("department");
    title=Integer.valueOf(request.getParameter("title"));
    waitnum = Integer.valueOf(request.getParameter("waitnum"));
    hcid= Integer.valueOf(request.getParameter("hcid"));
    //out.println(name+"#"+depart+"#"+title+" "+waitnum+" "+hcid); 
    
    Collection<assignBean> assignBeans =  aControl.getAssignBeansByHCid(hcid);
    Iterator<assignBean> it=assignBeans.iterator();
    if(it.hasNext()){
        assignBean  assBean =it.next();
        assBean.setDocTitle(title);
        assBean.setDepartName(depart);
        assBean.setWaitNum(waitnum);
        departControl  dc = new departControl(commondb);
        double cost = dc.getTreat(depart, title);
        docControl  docControl = new docControl(commondb);
        Doctor doctor =  docControl.getDoctorByName(name);
        assBean.setTreat(cost);
        assBean.setDocId(doctor.getDocId());
        aControl.update(assBean);    //修改诊疗卡相应的信息，只需要修改变动的选项 
    }
       
    
 %>
