package memberDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.MemberShipDTO;
import dto.RegisterDTO;

public class MemberShipDAO {
	private Connection con;
	
	public MemberShipDAO () {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kim";
		String password = "1111";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void insert (MemberShipDTO mDto) {
		String sql = "INSERT INTO membership VALUES(?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,mDto.getMemberShipName());
			ps.setString(2,mDto.getMemberId());
			ps.setString(3,mDto.getMemberEmail());
			ps.setString(4,mDto.getMemberShipDate());
			ps.setInt(5, mDto.getMemberShipCount());
			ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public MemberShipDTO selectMemberShipById(String id) {
		String sql = "SELECT * FROM membership WHERE MEMBERID=?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps= con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				MemberShipDTO MemberShipDTO = new  MemberShipDTO();
				MemberShipDTO.setMemberEmail(rs.getString("MEMBEREMAIL"));
				MemberShipDTO.setMemberId(rs.getString("MEMBERID"));
				MemberShipDTO.setMemberShipCount(rs.getInt("MEMBERSHIPCOUNT"));
				MemberShipDTO.setMemberShipDate(rs.getString("MEMBERSHIPDATE"));
				return MemberShipDTO;	
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void updateMemberShipCount(String id,int count) {
		String sql = "update membership set MEMBERSHIPCOUNT=? where MEMBERID=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,count);
			ps.setString(2,id);
			ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
