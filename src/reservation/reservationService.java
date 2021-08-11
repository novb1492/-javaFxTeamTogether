package reservation;



import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.commonService;
import dto.MemberShipDTO;
import dto.RegisterDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import main.Controller;
import memberDao.MemberShipDAO;


public class reservationService  {
	
	private final int openTime=9;
	private final int closeTime=18;
	private final int maxOfDay=60;
	private final int maxPeopleByTime=6;
	private final int lastDayIdNumber=37;
	private final String error="error";
	private reservationDao reservationDao=new reservationDao();


	public void showDatePage(RegisterDTO member,int plusMonth) {
		System.out.println("showDatePage");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("reservationPage2.fxml"));
		Parent reservationForm=loadPageAndGetParent(loader);
		LocalDate today=LocalDate.now().plusMonths(plusMonth);


		getMonth(reservationForm,today);
		getDays(reservationForm,plusMonth);
		showId(reservationForm, member.getId());
		showMemberShip(reservationForm,member.getId());
		showStage(reservationForm, "reservationPage");
		
		Controller mainController=loader.getController();
		mainController.setReservationForm(reservationForm,member);
		
	}
	public void showMemberShip(Parent reservationForm,String id) {
		System.out.println("showMemberShip");
		MemberShipDTO memberShipDTO=findMemberShip(id);
		System.out.println(memberShipDTO);
		if(memberShipDTO!=null) {
			Label memberShipName=(Label) reservationForm.lookup("#memberShipName");
			Label memberShipDate=(Label) reservationForm.lookup("#memberShipDate");
			memberShipName.setText(memberShipDTO.getMemberShipName());
			memberShipDate.setText(memberShipDTO.getMemberShipDate());
			showMemberShipCount(reservationForm, memberShipDTO.getMemberShipCount());
		}
	}
	private MemberShipDTO findMemberShip(String id) {
		System.out.println("findMemberShip");
		MemberShipDAO memberShipDAO=new MemberShipDAO();
		return memberShipDAO.selectMemberShipById(id);
	}
	private void showMemberShipCount(Parent reservationForm,int count) {
		Label memberShipCount=(Label) reservationForm.lookup("#memberShipCount");
		memberShipCount.setText(count+"");
		
	}
	public void showId(Parent root,String name) {
		System.out.println("showname");
		Label showEmail=(Label)root.lookup("#id");
		showEmail.setText(name);
	}
	private Parent loadPageAndGetParent(FXMLLoader loader) {
		System.out.println("loadPageAndGetParent");
		try {
		Parent	root = loader.load();
		return root;
		} catch (IOException e) {
			System.out.println("parent 로드중 오류발생");
			e.printStackTrace();
		}
		return null;
	}
	private void getMonth(Parent reservationForm,LocalDate today) {
		System.out.println("getMonth");
		Label month=(Label) reservationForm.lookup("#month");
		month.setText(Integer.toString(today.getMonthValue()));
	}
	private void getDays(Parent reservationForm,int plusMonth) {
		System.out.println("getDays");
		LocalDate today=LocalDate.now().plusMonths(plusMonth);
		YearMonth yearMonth=YearMonth.from(today);
		int lastDay=yearMonth.lengthOfMonth();
		int start=0;
		LocalDate date = LocalDate.of(today.getYear(),today.getMonthValue(),1);
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		int temp=1;
		start=dayOfWeek.getValue();
		int endDayIdOfMonth=lastDay+start;
		
		for(int i=1;i<start;i++) {
			Button button=(Button) reservationForm.lookup("#day"+i);
			button.setText("x");
			button.setDisable(true);
		}
		for(int i=start;i<endDayIdOfMonth;i++) {
			Button button=(Button) reservationForm.lookup("#day"+i);
			button.setText(Integer.toString(temp));
			if(checkFullDay(stringToTimestamp(today.getMonthValue()+"",temp))||compareDate(stringToTimestamp(today.getMonthValue()+"", temp),LocalDateTime.now())) {
				System.out.println(i+"일은 예약이 다 찼거나 지난 요일입니다");
				button.setDisable(true);
			}
			temp+=1;
		}
		if(endDayIdOfMonth<lastDayIdNumber) {
			for(int i=endDayIdOfMonth;i<=lastDayIdNumber;i++) {
				Button button=(Button) reservationForm.lookup("#day"+i);
				button.setText("x");
				button.setDisable(true);
			}
		}
	}

	public void checkCloseHour(Parent reservationForm) {
		System.out.println("checkCloseHour");
		if(LocalDateTime.now().getHour()>=closeTime) {
			System.out.println("영업이 종료 되었습니다");
			Button button=(Button) reservationForm.lookup("#day"+LocalDateTime.now().getDayOfMonth());
			button.setDisable(true);
		}
	}
	private void showStage(Parent root,String pageName ) {
		System.out.println("showStage");
		Stage stage=new Stage(); 
		stage.setTitle(pageName);
		stage.setScene(new Scene(root));
		stage.show();
	}
	public boolean checkFullDay(Timestamp timestamp) {
		System.out.println("checkFullDay"+timestamp);
		List<reservationDto>array=findByDate(timestamp);
		System.out.println(array.size()+"예약개수");
		if(array.size()>=maxOfDay) {
			return true;
		}
		return false;
	}
	public List<reservationDto> findByDate(Timestamp timestamp) {
		System.out.println("findByDate");
		return reservationDao.findAllByDate(timestamp);
	}
	public boolean compareDate(Timestamp timestamp,LocalDateTime localDateTime) {
		LocalDateTime loaDateTime2=timestamp.toLocalDateTime();
		
		if(localDateTime.getDayOfMonth()==loaDateTime2.getDayOfMonth()){
			return false;
		}
		else if(localDateTime.isAfter(loaDateTime2)){
           return true;
        }
        return false;
	}
	public void showTimePage(Parent reservationForm,int day,RegisterDTO member) {
		System.out.println("showTimePage");
		String id=member.getId();
		if(findMemberShip(id)==null) {
			System.out.println("멤버십 기간이 만료되었거나 멤버십이 없습니다");
			commonService.ErrorMsg(error,"멤버십 기간이 만료되었거나 멤버십이 없습니다");
			return;
		}
		FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("showTimePage.fxml"));
		Parent setShowTimePageForm=loadPageAndGetParent(fxmlLoader);
	
		Label getReservationPageMonth=(Label) reservationForm.lookup("#month");
		System.out.println(getReservationPageMonth.getText()+"달");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Label showSelectDate=(Label) setShowTimePageForm.lookup("#labelDate");
		showSelectDate.setText(sdf.format(stringToTimestamp(getReservationPageMonth.getText(), day)));
		
		getTimes(setShowTimePageForm,reservationForm,day);
		showStage(setShowTimePageForm,"showTimePage");
		
		Controller mainController=fxmlLoader.getController();
		mainController.setShowTimePageForm(reservationForm,setShowTimePageForm,day,member);
	}
	private void getTimes(Parent ShowTimePageForm,Parent reservationForm,int day) {
		System.out.println("getTimes"+day);
		LocalDateTime localDateTime=LocalDateTime.now();
		Label month=(Label)reservationForm.lookup("#month");
		
		for(int i=openTime;i<=closeTime;i++) {
			RadioButton radioButton=(RadioButton) ShowTimePageForm.lookup("#rdaio"+i);
			radioButton.setText(i+"시~"+(i+1)+"시");
			Label maxOfTime=(Label)ShowTimePageForm.lookup("#maxOfTime"+i);
			maxOfTime.setText(maxPeopleByTime+"");
			int alreadyPeople=compareTime(month, day,i);
			if(alreadyPeople==maxPeopleByTime) {
				radioButton.setDisable(true);
			}
			Label timeOfPeople=(Label)ShowTimePageForm.lookup("#timeOfPeople"+i);
			timeOfPeople.setText(alreadyPeople+"");
			if(day==localDateTime.getDayOfMonth()) {
				if(i<=localDateTime.getHour()) {
					radioButton.setDisable(true);
				}
			}
		}
	}
	public int compareTime(Label month,int day,int time) {
		System.out.println("compareTime");
		List<reservationDto>array= findByDate(stringToTimestamp(month.getText(),day));
		int temp=0;
		for(reservationDto r:array) {
			if(r.getTime()==time) {
				temp++;
			}
			if(temp==maxPeopleByTime) {
				System.out.println(time+"시는 모든인원이 다찼습니다");
				return temp;
			}
		}
		return temp;
	}
	public void insert(Parent reservationForm,Parent ShowTimePageForm,int day,RegisterDTO member) {
		try {
			String id=member.getId();
			
			MemberShipDAO memberShipDAO=new MemberShipDAO();
			MemberShipDTO memberShipDTO=memberShipDAO.selectMemberShipById(id);
		
			Label month=(Label)reservationForm.lookup("#month");
			int time=getSelectTime(ShowTimePageForm);
			LocalDateTime localDateTime=LocalDateTime.now(); 
			LocalDateTime reservationDate=Timestamp.valueOf(localDateTime.getYear()+"-"+month.getText()+"-"+day+" 00:00:00").toLocalDateTime();
			
			System.out.println(reservationDate+"예약시도일");
			if(localDateTime.isAfter(Timestamp.valueOf(memberShipDTO.getMemberShipDate()+" 00:00:00").toLocalDateTime())) {
				System.out.println("멤버십 기간이 만료되었습니다");
				commonService.ErrorMsg(error, "멤버십 기간이 만료되었습니다");
				return;
			}
			if(reservationDate.isAfter(Timestamp.valueOf(memberShipDTO.getMemberShipDate()+" 00:00:00").toLocalDateTime())) {
				System.out.println("멤버십 기간내 일자를 선택해 주세요");
				commonService.ErrorMsg(error, "멤버십 기간내 일자를 선택해 주세요");
				return;
			}
			if(memberShipDTO.getMemberShipCount()<1) {
				System.out.println("수강 횟수를 소진 하였습니다");
				commonService.ErrorMsg(error, "수강 횟수를 소진 하였습니다");
				return;
			}
			if(day==0||day>31) {
				System.out.println("잘못된 날짜 선택입니다");
				commonService.ErrorMsg(error,"잘못된 날짜 선택입니다");
				return;
			}
			if(checkFullDay(stringToTimestamp(month.getText(),day))||compareDate(stringToTimestamp(month.getText(), day),LocalDateTime.now())) {
				System.out.println(day+"일은 예약이 다 찼거나 지난 요일 예약시도 입니다");
				commonService.ErrorMsg(error, "일은 예약이 다 찼거나 지난 요일 예약시도 입니다");
				return;
			}
			if(compareTime(month,day,time)==6) {
				System.out.println(time+"시는 모든인원이 다찼습니다");
				commonService.ErrorMsg(error,time+"시는 모든인원이 다찼습니다");
				return;
			}
			if(localDateTime.getDayOfMonth()==day) {
				if(time<=localDateTime.getHour()) {
					System.out.println(time+"시는 지난 시간입니다");
					commonService.ErrorMsg(error,time+"시는 지난 시간입니다");
					return;
				}
			}
			if(id==null||id.isEmpty()) {
				System.out.println("회원정보가 없는 예약 시도 입니다");
				commonService.ErrorMsg(error, "회원정보가 없는 예약 시도 입니다");
				return;
			}
			if(month.getText().isEmpty()||month.getText()==null||time==0) {
				System.out.println("예약 정보가 없는 예약 시도입니다");
				commonService.ErrorMsg(error, "예약 정보가 없는 예약 시도입니다");
				return;
			}

			System.out.println("예약월"+month.getText());
			System.out.println("예약일"+day);
		
			Timestamp rDate=stringToTimestamp(month.getText(),day);
			System.out.println("사용일자 "+rDate);
			
			Timestamp created=Timestamp.valueOf(LocalDateTime.now());
			System.out.println("예약일자 "+created);
			
			reservationDto reservationDto=new reservationDto();
			reservationDto.setEmail(id);
			reservationDto.setName(member.getName());
			reservationDto.setCreated(created);
			reservationDto.setTime(time);
			reservationDto.setrDate(rDate);
			reservationDao.insert(reservationDto);
			System.out.println("예약성공");
			closeWindow(ShowTimePageForm);
			int count=memberShipDTO.getMemberShipCount();
			memberShipDAO.updateMemberShipCount(id,count-=1);
			showMemberShipCount(reservationForm, count);
		} catch (Exception e) {
			System.out.println("예약중 오류 발생");
			e.printStackTrace();
		}
			
	}
	public int getSelectTime(Parent ShowTimePageForm) {
		List<RadioButton>array=new ArrayList<RadioButton>();
		int time=0;
		for(int i=openTime;i<=closeTime;i++) {
			array.add((RadioButton)ShowTimePageForm.lookup("#rdaio"+i));
		}
		for(RadioButton r:array) {
			if(r.isSelected()) {
				String sTime=r.getText();
				time=Integer.parseInt(sTime.split("~")[0].replace("시",""));
			}
		}
		System.out.println("사용 시간 "+time);
		return time;
	}
	public Timestamp stringToTimestamp(String month,int day) {
		return Timestamp.valueOf(LocalDate.now().getYear()+"-"+month+"-"+day+" 00:00:00");
	}
	public void closeWindow(Parent parent) {
		System.out.println("closeWindow");
		Stage stage=(Stage) parent.getScene().getWindow();
		stage.close();
	}
	public void showRankOfMonth() {
		int nine=0,ten=0,eleven=0,twelve=0,thirteen=0,fourteen=0,fifteen=0,sixteen=0,seventeen=0,eightteen=0;
		Map<Integer, Integer>countByTime=new HashMap<Integer, Integer>();
		List<reservationDto>allRerservations=reservationDao.findAllRerservation();
		for(reservationDto r:allRerservations) {
			Timestamp timestamp=r.getrDate();
			System.out.println(timestamp+" "+r.getTime());
			switch (r.getTime()) {
			case 9:
				nine+=1;
				break;
				case 10:
					ten+=1;
					break;
				case 11:
					eleven+=1;
					break;
				case 12:
					twelve+=1;
					break;
				case 13:
					thirteen+=1;
					break;
				case 14:
					fourteen+=1;
					break;
				case 15:
					fifteen+=1;
					break;
				case 16:
					sixteen+=1;
					break;
				case 17:
					seventeen+=1;
					break;
				case 18:
					eightteen+=1;
					break;

			default:
				break;
			}
		}
		for(int i=openTime;i<=closeTime;i++) {
			int value=0;
			switch (i) {
			case 9:
				value=nine;
				break;
				case 10:
					value=ten;
					break;
				case 11:
					value=eleven;
					break;
				case 12:
					value=twelve;
					break;
				case 13:
					value=thirteen;
					break;
				case 14:
					value=fourteen;
					break;
				case 15:
					value=fifteen;
					break;
				case 16:
					value=sixteen;
					break;
				case 17:
					value=seventeen;
					break;
				case 18:
					value=eightteen;
					break;

			default:
				break;
			}
			countByTime.put(i, value);
		}
		List<Integer> keySetList = new ArrayList<>(countByTime.keySet());
		   Collections.sort(keySetList, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					  return countByTime.get(o2).compareTo(countByTime.get(o1));
				}
	        });
		for(Integer key : keySetList) {
            System.out.println(String.format("Key : %s, Value : %s", key, countByTime.get(key)));
        }
			
		
	}


}
