<%@page import="HLcard.healCardControl"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="doctor.Doctor"%>
<%@page import="doctor.docControl"%>
<%@page import="department.departControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>
<%
    String workerName="";
    if(request.getSession().getAttribute("worker")!=null){
        workerName=request.getSession().getAttribute("worker").toString();
    }else{
        out.println("挂号人员需要认证 ");
        return;
    }
    String name,depart;
    int title,waitnum,hcid;
    name=request.getParameter("name");
    depart=request.getParameter("department");
    title=Integer.valueOf(request.getParameter("title"));
    waitnum = Integer.valueOf(request.getParameter("waitnum"));
    hcid= Integer.valueOf(request.getParameter("hcid"));
    //out.println(name+"#"+depart+"#"+title+" "+waitnum+" "+hcid); 
    assignControl ac = new assignControl(commondb);
    departControl dc = new departControl(commondb);
    double treat = dc.getTreat(depart, title);
    String classTh = "";
    Date date = new Date();
    if(date.getHours()<12){
        classTh = "morning";
    }else{
        classTh ="afternoon";
    }

    docControl  docC = new docControl(commondb);
    Doctor doctor =   docC.getDoctorByName(name); 
    healCardControl hc = new healCardControl(commondb);
    int result = ac.assignAman(treat, classTh, waitnum, depart, title,doctor.getDocId());
    if(result>0){
       registorControl rc = new registorControl(commondb);
       if(rc.insertRegistorAssign(workerName,result,treat, classTh))
          if(hc.insertHealCardAssign(hcid,result))
             out.println("挂号成功"); 
    }else{
       out.println("挂号失败 ");
    }
 %>
