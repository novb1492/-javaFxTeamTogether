package login;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import common.commonService;
import dto.RegisterDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Controller;
import memberDao.MemberDAO;
import reservation.reservationService;

public class LoginService {
	
	public void LoginProc(Parent root) {
	
		MemberDAO memberDao = new MemberDAO();
		TextField idText = (TextField)root.lookup("#idText");
		PasswordField pwText = (PasswordField)root.lookup("#pwText");
		String id = idText.getText();
		String pw = pwText.getText();
		RegisterDTO member = memberDao.selectId(id);
		
		if(member != null) {
			if(pw.equals(member.getPw())) {
				reservationService reservationService=new reservationService();
				reservationService.closeWindow(root);
				reservationService.showDatePage(member,0);
			}else {
				commonService.ErrorMsg("패스워드가 일치하지 않습니다.");
			}
		}else {
			commonService.ErrorMsg("등록되지 않은 회원입니다.");
		}

		
	}

	public void RegisterOpenProc() {
		Stage registerStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/register/registerForm.fxml"));
		
		Parent registerForm = null;
		try {
			registerForm = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Controller control = loader.getController();
		control.setRegisterForm(registerForm);
		
		registerStage.setScene(new Scene(registerForm));
		registerStage.show();
	} 
	public void adminOpenProc() {
		Stage adminStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/manager/ManagerForm.fxml"));
		Parent adminForm = null;
		try {
			adminForm = loader.load();
		} catch (Exception e) {
			// TODO: handle exception
		}
		adminStage.setScene(new Scene(adminForm));
		adminStage.show();
	}

}
