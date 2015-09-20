package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.PreparedStatement;

import secureUtil.makeMD5;
import worker.registorBean;

import DBbean.dbBean;

public class managerControl {
	
	private dbBean db=null;
	
	public managerControl() {
		// TODO Auto-generated constructor stub
	}

	public managerControl(dbBean d){
		db=d;
	}
	/**
	 *  返回表中所有管理员的bean
	 * @return managerBean集合
	 * @throws SQLException
	 */
	public Collection<managerBean> getManagerBeans() throws SQLException{
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		ArrayList<managerBean> managerList=new ArrayList<managerBean>();
		
		try{
			conn=db.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from manager");
			while(rs.next()){
				managerBean mbBean=new managerBean(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
				managerList.add(mbBean);
			}
			return managerList;
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
	public managerBean getManagerBean(String user)throws SQLException{
		Connection conn=null;
		PreparedStatement  stmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			stmt=conn.prepareStatement("select * from manager where managerName = ?");
			stmt.setString(1, user);
			rs=stmt.executeQuery();
			managerBean mbBean=null;
			if(rs.next()){
			   mbBean=new managerBean(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
			}
			return mbBean;
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
	public boolean update(managerBean man)throws SQLException {
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("update manager set canSelect = ?,canUpdate = ?,canInsert = ?,canDel = ?  where managerName = ?");
			int b1=(man.isCanSelect()?1:0);
			int b2=(man.isCanUpdate()?1:0);
			int b3=(man.isCanInsert()?1:0);
			int b4=(man.isCanDel()?1:0);
			pstmt.setInt(1,b1);
			pstmt.setInt(2,b2);
			pstmt.setInt(3,b3);
			pstmt.setInt(4,b4);
			pstmt.setString(5, man.getManagerName());
			if(pstmt.executeUpdate()>0){
				return true;
			}else{
				return false;	
			}
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * 添加一个管理员
	 * @param man
	 * @return
	 */
	public boolean addManager(managerBean man){
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("insert into manager VALUES(?,?,?,?,?,?)");
			pstmt.setString(1,man.getManagerName());
			pstmt.setString(2,makeMD5.Md5(makeMD5.Md5(man.getManagerPass())));
			int b1=(man.isCanSelect()?1:0);
			int b2=(man.isCanUpdate()?1:0);
			int b3=(man.isCanInsert()?1:0);
			int b4=(man.isCanDel()?1:0);
			pstmt.setInt(3,b1);
			pstmt.setInt(4,b2);
			pstmt.setInt(5,b3);
			pstmt.setInt(6,b4);
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
	
}
