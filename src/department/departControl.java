package department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import worker.registorBean;

import DBbean.dbBean;

public class departControl {

   private dbBean db=null;
	
	public departControl() {
		// TODO Auto-generated constructor stub
	}

	public departControl(dbBean d){
		db=d;
	}
	/**
	 * 根据科室和职称,获得诊疗费
	 * @param depart
	 * @param docTitle
	 * @return
	 * @throws SQLException
	 */
	public double getTreat(String depart,int docTitle) throws SQLException{
		Connection conn=null;
		PreparedStatement  stmt=null;
		ResultSet rs=null;
		ArrayList<departmentBean> departmentBeansList=new ArrayList<departmentBean>();
		try{
			conn=db.getConnection();
			stmt=conn.prepareStatement("select TreatCost from department where Name = ? AND DocTitle = ?");
			stmt.setString(1,depart);
			stmt.setInt(2, docTitle);
			rs=stmt.executeQuery();
			if(rs.next()){
			   double cost = rs.getDouble(1);
			   return cost;
			}
			return 0.0;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
		
	}
	/**
	 *  返回表中所有管理员的bean
	 * @return managerBean集合
	 * @throws SQLException
	 */
	public Collection<departmentBean> getDepartmentBeans() throws SQLException{
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		ArrayList<departmentBean> departmentBeansList=new ArrayList<departmentBean>();
		
		try{
			conn=db.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from department");
			while(rs.next()){
				departmentBean mbBean=new departmentBean(rs.getInt(4),rs.getString(1),rs.getInt(2),rs.getDouble(3));
				departmentBeansList.add(mbBean);
			}
			return departmentBeansList;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	
	/**
	 * 通过主键，到表中查找，返回mangerBean
	 * @param user
	 * @return  该user对应的mangerBean
	 * @throws SQLException
	 */
	public Collection<departmentBean> getDepartmentBeansByName(String user)throws SQLException{
		Connection conn=null;
		PreparedStatement  stmt=null;
		ResultSet rs=null;
		ArrayList<departmentBean> departmentBeansList=new ArrayList<departmentBean>();
		try{
			conn=db.getConnection();
			stmt=conn.prepareStatement("select * from department where Name = ?");
			stmt.setString(1,user);
			rs=stmt.executeQuery();
			departmentBean mbBean=null;
			while(rs.next()){
			   mbBean=new departmentBean(rs.getInt(4),rs.getString(1),rs.getInt(2),rs.getDouble(3));
			   departmentBeansList.add(mbBean);
			}
			return departmentBeansList;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * 修改管理员权限
	 * @param man
	 * @throws SQLException
	 */
	public boolean update(departmentBean man)throws SQLException {
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("update department set Name = ?,DocTitle = ?,TreatCost = ? where DepartmentId = ?");
			pstmt.setString(1, man.getName());
			pstmt.setInt(2, man.getDocTitle());
			pstmt.setDouble(3, man.getTreatcost());
			pstmt.setInt(4, man.getId());
			if(pstmt.executeUpdate()>0){
				  return true;
			}
			else{
			    	  return false;
			}
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	public boolean insertDepart(departmentBean  man) throws SQLException{
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("insert into  department VALUES(?,?,?,?)");
			int num = getNewId();
			pstmt.setString(1, man.getName());
			pstmt.setInt(2, man.getDocTitle());
			pstmt.setDouble(3, man.getTreatcost());
			pstmt.setInt(4,num);
			if(pstmt.executeUpdate()>0){
				  return true;
			}
			else{
			    return false;
			}
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * 获得新添加department的id
	 * @return
	 * @throws SQLException
	 */
	public int getNewId() throws SQLException{
		Connection conn=null;
		Statement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.createStatement();
			rs = pstmt.executeQuery("select MAX(DepartmentId) from department ");
			if(rs.next()){
				 int num=rs.getInt(1);
				 num++;
				  return num;
			}
			else{
			      throw new SQLException();  //若无结果集，抛出异常
			}
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
}
