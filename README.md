
예약페이지를 담당했습니다

패키지 이름: reservation
사용중인 컨트롤러: Main(패키지)->Controller(클래스)

시나리오
로그인->날짜표시->날짜선택or월선택->시간표시->시간선택->예약버튼->검증->예약 성공or실패

1.날짜표시
private void getDays(Parent reservationForm,int plusMonth)
로그인이 완료되면 날짜 페이지를 보여줍니다
날짜는 하나하나 버튼으로 만들었고 
순서대로 아이디를 부여했습니다
선택한 월의 전체날짜를 불러옵니다
날짜를 불러올때 8/12일이면 1~11일은 즉 현재 보다 이전 날들은
예약에수에 상관없이 disalbed가 걸리게 했습니다
그후로는 예약수를 판단하여 하루 예약이 가득찬날은
disabled가 걸리게 했습니다

2.날짜선택
public void click() {
		System.out.println("click1");
		day=1-getDayGap()+1;
}
~
public void click37() {
		System.out.println("click37");
		day=37-getDayGap()+1;
}

요일에 날짜를 맞추기 위해 37번까지 버튼이 생겼습니다
첫 요일의 숫자를 구해서 day에서 빼준다음 1을 더해주면 됩니다
예를 들어 9월은 수요일 부터 시작하므로 day3이 1이 됩니다 
-getDayGap()+1 코드가 없다면 day을 누르면 3이라는 값이 들어가게됩니다
그래서 getDayGap로 각 달의 첫 시작요일의 값을 구해서 마이너스해주고 +1 해주면
day값이 올바르게 들어가는걸 확인했습니다

2-2월선택
월옆에 다음/이전 버튼을 만들었습니다
클릭시 아래 함수가 작동하게 했습니다
public void nextMonth() {
		Label month=(Label) reservationForm.lookup("#month");
		int nextMonth=Integer.parseInt(month.getText());
		if(nextMonth<12) {
			nextMonth+=1;
			reservationService.showDatePage(member,getMonthGap(nextMonth));
			reservationService.closeWindow(reservationForm);
		}else {
			commonService.ErrorMsg("error", "마지막 월입니다");
		}
	}
	public void minusMonth() {
		Label month=(Label) reservationForm.lookup("#month");
		LocalDate tdoay=LocalDate.now();
		int nextMonth=Integer.parseInt(month.getText());
		if(nextMonth>tdoay.getMonthValue()) {
			nextMonth-=1;
			reservationService.showDatePage(member,getMonthGap(nextMonth));
			reservationService.closeWindow(reservationForm);
		}else {
			commonService.ErrorMsg("error", "마지막 월입니다");
		}
}
다음 버튼을누르면
표시되고 있는 월값을 가져와서
Label month=(Label) reservationForm.lookup("#month");
12보다작으면
if(nextMonth<12) 
1을 늘려주고
접속 월과 차이를 찾아서
nextMonth+=1;
reservationService.showDatePage(member,getMonthGap(nextMonth));
다음 월을 표시해줍니다 

이전버튼은 그 반대입니다

3.시간표시
public void showTimePage(Parent reservationForm,int day,RegisterDTO member)
날짜를 선택하면
날에 맞는 예약시간을 검증후 뿌려줍니다
역시 12시면 12이전 시간은 예약수에 관계없이 disabled
예약이 다찬시간은 diabled
옆에 현재인원/최대인원을 표시해 놓았습니다

4.검증
예약권기간/횟수/로그인여부/예약정보 검증등을 해놓았습니다

기타 기능
회원권이 예약페이지에 출력되게 해놓았습니다

-추가 기능예정
1.영업종료가 되면
당일 예약수에 상관없이 disabled추가 
2.public->private
그다음 분이 딱히 사용하지 않으실거 같으셔서
함수들을 은닉하려고합니다
3.차트를만들어달라고해서
ex) 가장많이 사용하는시간/순위
관리자 페이지에서 볼수있는 통계페이지를 만들에정입니다


