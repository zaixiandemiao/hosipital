package worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import secureUtil.makeMD5;

import DBbean.dbBean;


public class registorControl {
private dbBean db=null;
	
	public registorControl() {
		// TODO Auto-generated constructor stub
	}

	public registorControl(dbBean d){
		db=d;
	}
	/**
	 *  ���ر������й���Ա��bean
	 * @return managerBean����
	 * @throws SQLException
	 */
	public Collection<registorBean> getRegistorBeans() throws SQLException{
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		ArrayList<registorBean> registorBeans=new ArrayList<registorBean>();
		
		try{
			conn=db.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from registor");
			while(rs.next()){
				registorBean mbBean=new registorBean(rs.getString(1),rs.getString(2));
				registorBeans.add(mbBean);
			}
			return registorBeans;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	
	/**
	 * ͨ�������������в��ң�����mangerBean
	 * @param user
	 * @return  ��user��Ӧ��mangerBean
	 * @throws SQLException
	 */
	public registorBean getRegistorBean(String user)throws SQLException{
		Connection conn=null;
		PreparedStatement  stmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			stmt=conn.prepareStatement("select * from registor where RegistName = ?");
			stmt.setString(1, user);
			rs=stmt.executeQuery();
			registorBean mbBean=null;
			if(rs.next()){
			   mbBean=new registorBean(rs.getString(1),rs.getString(2));
			}
			return mbBean;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * �޸Ĺ���ԱȨ��
	 * @param man
	 * @throws SQLException
	 */
	public void changePass(registorBean man)throws SQLException {
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("update registor set RegistPass = ? where RegistName = ?");
			pstmt.setString(1, makeMD5.Md5(man.getRegistorPass()));
			pstmt.setString(2, man.getRegistorName());
			pstmt.executeQuery();
			return ;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * ���һ���Һ���Ա
	 * @param man
	 * @return
	 */
	public boolean addRegistor(registorBean man){
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("insert into registor VALUES(?,?)");
			pstmt.setString(1,man.getRegistorName());
			pstmt.setString(2,makeMD5.Md5(man.getRegistorPass()));
			if(pstmt.executeUpdate()>0)
				return true;
			else {
				return false;
			}
		}catch(SQLException ex){
			return false;
		}
		finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * ���һ��registorAssign
	 * @param ra
	 * @return
	 */
	public boolean addRegistorAssign(registorAssign ra){
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("insert into registorassign(registorName,assignId,cost,ClassTh,DateTime) VALUES(?,?,?,?,?)");
			pstmt.setString(1,ra.getRegistorName());
			pstmt.setInt(2, ra.getAssignId());
			pstmt.setDouble(3, ra.getCost());
			pstmt.setString(4, ra.getClassTh());
			pstmt.setDate(5, ra.getDate());
			if(pstmt.executeUpdate()>0)
				return true;
			else {
				return false;
			}
		}catch(SQLException ex){
			return false;
		}
		finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * ���һ�����յİ���
	 * @param name
	 * @param assId
	 * @param cost
	 * @param clth
	 * @return
	 */
	public boolean insertRegistorAssign(String name,int assId,double cost,String clth){
		registorAssign rAssign = new registorAssign(name, assId, cost, clth);
		return addRegistorAssign(rAssign);	
	}
}
