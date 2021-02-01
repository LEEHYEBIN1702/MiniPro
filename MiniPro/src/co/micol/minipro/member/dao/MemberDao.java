package co.micol.minipro.member.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.micol.minipro.common.DAO;
import co.micol.minipro.common.DbInterface;
import co.micol.minipro.member.service.MemberVo;


public class MemberDao extends DAO implements DbInterface<MemberVo> {
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public ArrayList<MemberVo> selectList() {
		// 회원 전체 리스트를 돌려주는 메소드
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		MemberVo vo;
		String sql= "SELECT * FROM MEMBER ORDER BY MID";
		try {
			psmt = conn.prepareStatement(sql);
			rs=psmt.executeQuery(); //결과를 레코드셋으로 돌려주니까 리절트셋으로 받음.
			while(rs.next()) {
				vo = new MemberVo();
				vo.setmId(rs.getString("mid"));
				vo.setmName(rs.getString("mname"));
				//vo.setmPassword(rs.getString("mpassword")); 패스워드는 관리자도 보면 안되기 때문에 안 담아준다.
				vo.setmAuth(rs.getString("mauth"));
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}

	@Override
	public MemberVo select(MemberVo vo) {
		//한명의 레코드를 찾아오는 메소드
		String sql = "SELECT * FROM MEMBER WHERE MID =? AND MPASSWORD =?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getmId());
			psmt.setString(2, vo.getmPassword());
			rs=psmt.executeQuery();
			if(rs.next()) {
				vo.setmAuth(rs.getString("mauth"));
				vo.setmName(rs.getString("mname"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return vo;
	}

	@Override
	public int insert(MemberVo vo) {
		String sql = "INSERT INTO MEMBER(MID,MNAME,MPASSWORD) VALUES(?, ?, ?)";
		int n = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getmId());
			psmt.setString(2, vo.getmName());
			psmt.setString(3, vo.getmPassword());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	@Override
	public int update(MemberVo vo) {
		//여기에 변경(수정) 작업을 쓴다. 권한, 패스워드만 변경한다. 앞으로는 업데이트 구문을 나누어서 써준다.
		int n = 0;
		String sql = null;
		if(vo.getmPassword() != null) {
			sql = "UPDATE MEMBER SET MPASSWORD=? WHERE MID=?";  //패스워드 변경
		}else if(vo.getmAuth() != null) {
			sql = "UPDATE MEMBER SET MAUTH=? WHERE MID=?"; //권한 변경
		}
		try { // sql구문이 두개이기 때문에 try catch 구문도 두개.
			psmt =  conn.prepareStatement(sql);
			if(vo.getmPassword() != null)
			    psmt.setString(1, vo.getmPassword());  // 패스워드 변경 될 때
			else
				psmt.setString(1, vo.getmAuth());  // 권한 변경 될 때 
			    psmt.setString(2, vo.getmId());
			    n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public int delete(MemberVo vo) {
		int n = 0;
		String sql = "DELETE FROM MEMBER WHERE MID = ?";
		//여기에 삭제 작업을 쓴다.
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getmId());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	public boolean isidCheck(String id) { //아이디 중복체크 메소드
		boolean bool = true;
	    String sql = "SELECT MID FROM MEMBER WHERE MID = ?"; //아이디 값이 존재하면 false를 리턴 (중복체크니까..존재하면 사용할 수 없어야 함) 존재하지 않으면 true (사용가능)
	    try {
	    	psmt = conn.prepareStatement(sql);
	    	psmt.setString(1, id);
	    	rs = psmt.executeQuery();
	    	if(rs.next() ) {
	    		bool = false;
	    	}
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }finally {
	    	close();
	    }
		return bool;
	}
	
	
	public MemberVo login(MemberVo vo) { // 한명의 데이터를 검색한다.
		String sql = "SELECT * FROM MEMBER WHERE MID =? ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getmId());
			rs=psmt.executeQuery();
			if(rs.next()) {
				vo.setmAuth(rs.getString("mauth"));
				vo.setmName(rs.getString("mname"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return vo;
	}

	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}
	
}
