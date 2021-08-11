package main;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;

import common.commonService;
import dto.RegisterDTO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import login.LoginService;
import register.RegisterService;
import reservation.reservationService;
import test.test;


public class Controller implements Initializable{
	private Parent loginForm;
	private Parent registerForm;
	private Parent reservationForm;
	private Parent setShowTimePageForm;
	
	private LoginService loginSvc;
	private RegisterService regSvc;
	private reservationService reservationService;
	private RegisterDTO member;
	
	private int day;
	
	public void setLoginForm(Parent loginForm) {
		this.loginForm = loginForm;
	}
	public void setRegisterForm(Parent registerForm) {
		this.registerForm = registerForm;
	}
	public void setReservationForm(Parent reservationForm,RegisterDTO member) {
		this.reservationForm=reservationForm;
		this.member=member;
	}
	public void setShowTimePageForm(Parent reservationForm,Parent setShowTimePageForm,int day,RegisterDTO member) {
		this.reservationForm=reservationForm;
		this.setShowTimePageForm=setShowTimePageForm;
		this.day=day;
		this.member=member;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginSvc = new LoginService();
		regSvc = new RegisterService();
		reservationService=new reservationService();
	}
	public void click() {
		System.out.println("click1");
		day=1-getDayGap()+1;
	}
	public void click2() {
		System.out.println("clcik2");
		day=2-getDayGap()+1;
	}public void click3() {
		System.out.println("click3");
		day=3-getDayGap()+1;
	}
	public void click4() {
		System.out.println("click4");
		day=4-getDayGap()+1;
	}
	public void click5() {
		System.out.println("click5");
		day=5-getDayGap()+1;
	}
	public void click6() {
		System.out.println("click6");
		day=6-getDayGap()+1;
	}
	public void click7() {
		System.out.println("click7");
		day=7-getDayGap()+1;
	}
	public void click8() {
		System.out.println("click8");
		day=8-getDayGap()+1;
	}
	public void click9() {
		System.out.println("click9");
		day=9-getDayGap()+1;
	}
	public void click10() {
		System.out.println("click10");
		day=10-getDayGap()+1;
	}
	public void click11() {
		System.out.println("click11");
		day=11-getDayGap()+1;
	}
	public void click12() {
		System.out.println("click12");
		day=12-getDayGap()+1;
	}
	public void click13() {
		System.out.println("click13");
		day=13-getDayGap()+1;
	}
	public void click14() {
		System.out.println("click14");
		day=14-getDayGap()+1;
	}
	public void click15() {
		System.out.println("click15");
		day=15-getDayGap()+1;
	}
	public void click16() {
		System.out.println("click16");
		day=16-getDayGap()+1;
	}
	public void click17() {
		System.out.println("click17");
		day=17-getDayGap()+1;
	}
	public void click18() {
		System.out.println("click18");
		day=18-getDayGap()+1;
	}
	public void click19() {
		System.out.println("click19");
		day=19-getDayGap()+1;
	}
	public void click20() {
		System.out.println("click20");
		day=20-getDayGap()+1;
	}
	public void click21() {
		System.out.println("click21");
		day=21-getDayGap()+1;
	}
	public void click22() {
		System.out.println("click22");
		day=22-getDayGap()+1;
	}
	public void click23() {
		System.out.println("click23");
		day=23-getDayGap()+1;
	}
	public void click24() {
		System.out.println("click24");
		day=24-getDayGap()+1;
	}
	public void click25() {
		System.out.println("click25");
		day=25-getDayGap()+1;
	}
	public void click26() {
		System.out.println("click26");
		day=26-getDayGap()+1;
	}
	public void click27() {
		System.out.println("click27");
		day=27-getDayGap()+1;
	}
	public void click28() {
		System.out.println("click28");
		day=28-getDayGap()+1;
	}
	public void click29() {
		System.out.println("click29");
		day=29-getDayGap()+1;
	}
	public void click30() {
		System.out.println("click30");
		day=30-getDayGap()+1;
	}
	public void click31() {
		System.out.println("click31");
		day=31-getDayGap()+1;
	}
	public void click32() {
		System.out.println("click32");
		day=32-getDayGap()+1;
	}
	public void click33() {
		System.out.println("click33");
		day=33-getDayGap()+1;
	}
	public void click34() {
		System.out.println("click34");
		day=34-getDayGap()+1;
	}
	public void click35() {
		System.out.println("click35");
		day=35-getDayGap()+1;
	}
	public void click36() {
		System.out.println("click36");
		day=36-getDayGap()+1;
	}
	public void click37() {
		System.out.println("click37");
		day=37-getDayGap()+1;
	}
	public void LoginProc() {
		loginSvc.LoginProc(loginForm);
	}
	public void RegisterOpenProc() {
		loginSvc.RegisterOpenProc();
	}
	public void RegisterProc() {
		regSvc.RegisterProc(registerForm);
	}
	public void CancelProc(ActionEvent event) {
		commonService.windowClose(event);
	}
	public void adminOpenProc() {
		loginSvc.adminOpenProc();
	}
	public void show() {
		reservationService.showTimePage(this.reservationForm,this.day,this.member);
	}
	public void insert() {
		reservationService.insert(this.reservationForm,this.setShowTimePageForm,this.day,this.member);
	}
	public void goToMypage() {
		System.out.println("goToMypage"+member.getId());
	}
	public void showRankOfSeason() {
		reservationService.showRankOfMonth();
	}
	private int getDayGap() {
		System.out.println("getDayGap");
		LocalDate date = LocalDate.of(2021,8,1);
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		return dayOfWeek.getValue();
	}
}
