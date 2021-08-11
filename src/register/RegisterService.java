package register;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import common.commonService;
import dto.MemberShipDTO;
import dto.RegisterDTO;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import memberDao.MemberDAO;
import memberDao.MemberShipDAO;

public class RegisterService {

	public void RegisterProc(Parent registerForm) {
		RegisterDTO red = new RegisterDTO();
		MemberShipDTO mDto = new MemberShipDTO();
		
		
		TextField nameText = (TextField)registerForm.lookup("#nameText");
		TextField idText = (TextField)registerForm.lookup("#idText");
		PasswordField pwText = (PasswordField)registerForm.lookup("#pwText");
		PasswordField confirmText = (PasswordField)registerForm.lookup("#confirmText");
		TextField phoneText = (TextField)registerForm.lookup("#phoneText");
		TextField age = (TextField)registerForm.lookup("#ageText");
		RadioButton man = (RadioButton)registerForm.lookup("#manRadio");
		RadioButton woman = (RadioButton)registerForm.lookup("#womanRadio");
		TextField email = (TextField)registerForm.lookup("#emailText");
		String gender = "";
		if(man.isSelected())
			gender = "남";
		else if(woman.isSelected())
			gender = "여";
		else
			gender = "선택 없음.";
		
			
		CheckBox naverCheck = (CheckBox)registerForm.lookup("#naverCheck");
		CheckBox instarCheck = (CheckBox)registerForm.lookup("#instarCheck");
		CheckBox nearCheck = (CheckBox)registerForm.lookup("#nearCheck");
		CheckBox adCheck = (CheckBox)registerForm.lookup("#adCheck");
		String checks = "";
		if(naverCheck.isSelected())
			checks += "네이버 ";
		if(instarCheck.isSelected())
			checks += "인스타 ";
		if(nearCheck.isSelected())
			checks += "지인추천";
		if(adCheck.isSelected())
			checks += "전단지";
		
		RadioButton registration = (RadioButton)registerForm.lookup("#registration");
		RadioButton unregistered = (RadioButton)registerForm.lookup("#unregistered");
		String enrollment = "";
		if(registration.isSelected()) {
			enrollment += "등록";
		}else if(unregistered.isSelected()) {
			enrollment += "미등록";
		}
		
		
		red.setName(nameText.getText());
		red.setId(idText.getText());
		red.setPw(pwText.getText());
		red.setGender(gender);
		red.setPhoneNumber(phoneText.getText());
		red.setAge(age.getText());
		red.setRoute(checks);
		red.setEmail(email.getText());
		red.setEnrollment(enrollment);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, +1);
		Date currentTime = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String memberShipDate = formatter.format(currentTime);
		
		mDto.setMemberId(idText.getText());
		mDto.setMemberEmail(email.getText());
		mDto.setMemberShipDate(memberShipDate);
		mDto.setMemberShipCount(5);
		
		
		if(pwText.getText().equals(confirmText.getText() ) ){
			MemberDAO dao = new MemberDAO();
			MemberShipDAO mDao = new MemberShipDAO();
			RegisterDTO check = dao.selectId(idText.getText());
			if(check == null) {
				dao.insert(red);
				commonService.info("등록 완료.");
				if(enrollment.equals("등록")) {
					mDao.insert(mDto);
				}
			}else {
				commonService.ErrorMsg("등록된 계정입니다.");
			}
		}else {
			commonService.ErrorMsg("입력하신 비밀번호가 일치하지 않습니다.");
		}

	}

}