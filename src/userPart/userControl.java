package userPart;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.PreparedStatement;

import secureUtil.makeMD5;
import worker.registorAssign;

import DBbean.dbBean;

public class userControl {
	
	private dbBean db=null;
	
	public userControl() {
		// TODO Auto-generated constructor stub
	}

	public userControl(dbBean d){
		db=d;
	}
	/**
	 *  返回表中所有用户的bean
	 * @return userBean集合
	 * @throws SQLException
	 */
	public Collection<User> getUsers() throws SQLException{
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		ArrayList<User> UserList=new ArrayList<User>();
		
		try{
			conn=db.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from user");
			while(rs.next()){
				User mbBean=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				UserList.add(mbBean);
			}
			return UserList;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	
	/**
	 * 通过主键，到表中查找，返回userBean
	 * @param user
	 * @return  该user对应的UserrBean
	 * @throws SQLException
	 */
	public User getUserBean(String user)throws SQLException{
		Connection conn=null;
		PreparedStatement  stmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			stmt=conn.prepareStatement("select * from user where UserName = ?");
			stmt.setString(1, user);
			rs=stmt.executeQuery();
			User mbBean=null;
			if(rs.next()){
			   mbBean=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}
			return mbBean;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * 改变用户信息
	 * @param man
	 * @return
	 * @throws SQLException
	 */
	public boolean update(User man)throws SQLException {
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("update user set PassWord = ?,RealName = ?,IDcard = ?,Tel = ?  where UserName = ?");
			pstmt.setString(1,makeMD5.Md5(man.getPasswd()));
			pstmt.setString(2,man.getRealName());
			pstmt.setString(3,man.getIdCard());
			pstmt.setString(4,man.getTel());
			pstmt.setString(5,man.getUsername());
			if(pstmt.executeUpdate()>0)
				return true;
			else {
				return false;
			}
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * 向表中插入一个用户项，用于用户注册
	 * @param user
	 * @return 
	 * @throws SQLException
	 */
	public boolean addUser(User user){
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("insert into `user` VALUES(?,?,?,?,?)");
			pstmt.setString(1,user.getUsername());
			pstmt.setString(2,makeMD5.Md5(user.getPasswd()));
			pstmt.setString(3,user.getRealName());
			pstmt.setString(4,user.getIdCard());
			pstmt.setString(5,user.getTel());
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
	 * 添加一个registorAssign
	 * @param ra
	 * @return
	 */
	public boolean addUserAssign(userAssign ua){
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("insert into userassign(username,assignId,idcard) VALUES(?,?,?)");
			pstmt.setString(1,ua.getUsername());
			pstmt.setInt(2, ua.getAssignId());
			pstmt.setString(3, ua.getIdcard());
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
	 * 添加一个当日的安排
	 * @param username
	 * @param assId
	 * @return
	 */
	public boolean insertUserAssign(String username,int assId,String idcard){
		userAssign rAssign = new userAssign(username,assId,idcard);
		return addUserAssign(rAssign);	
	}
	/**
	 * 获得用户username 数据库中大于datetime时间的预约记录
	 * @param username
	 * @param datetime
	 * @return
	 * @throws SQLException
	 */
	public Collection<userAppoint>  getUserAppoints(String username,String datetime) throws SQLException{
		Connection conn=null;
		PreparedStatement  stmt=null;
		ResultSet rs=null;
		ArrayList<userAppoint> UserList=new ArrayList<userAppoint>();
		
		try{
			conn=db.getConnection();
			stmt=conn.prepareStatement("select a.username,a.assignid,b.datetime,b.ClassTh,b.DepartName,b.DocTitle,b.waitNum,b.Treat from userassign a,assignment b where a.assignid = b.AssignId AND a.username = ? AND b.datetime > ?");
			stmt.setString(1, username);
			Date date = Date.valueOf(datetime);
			stmt.setDate(2, date);
			rs=stmt.executeQuery();
			while(rs.next()){
				userAppoint mbBean=new userAppoint(rs.getString(1),rs.getInt(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getDouble(8));
				UserList.add(mbBean);
			}
			return UserList;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	
}
