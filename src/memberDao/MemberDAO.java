package memberDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.RegisterDTO;

public class MemberDAO {
	private Connection con;
	
	public MemberDAO () {
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
	
	public RegisterDTO selectId(String id) {
		String sql = "SELECT * FROM exercise WHERE id=?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps= con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				RegisterDTO reg = new RegisterDTO();
				reg.setName(rs.getString("name"));
				reg.setId(rs.getString("id"));
				reg.setPw(rs.getString("pw"));
				reg.setAge(rs.getString("age"));
				reg.setGender(rs.getNString("gender"));
				reg.setPhoneNumber(rs.getString("phone"));
				reg.setRoute(rs.getString("route"));
				reg.setEmail(rs.getString("email"));
				reg.setEnrollment(rs.getString("enrollment"));
				return reg;	
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void insert (RegisterDTO reg) {
		System.out.println( reg.getName());
		System.out.println( reg.getId());
		System.out.println( reg.getPw());
		System.out.println(  reg.getAge());
		System.out.println(  reg.getGender());
		System.out.println( reg.getPhoneNumber());
		System.out.println( reg.getRoute());
		System.out.println( reg.getEmail());
		System.out.println(reg.getEnrollment());
		String sql = "INSERT INTO exercise VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, reg.getName());
			ps.setString(2, reg.getId());
			ps.setString(3, reg.getPw());
			ps.setString(4, reg.getAge());
			ps.setString(5, reg.getGender());
			ps.setString(6, reg.getPhoneNumber());
			ps.setString(7, reg.getRoute());
			ps.setString(8, reg.getEmail());
			ps.setString(9, reg.getEnrollment());
			ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
