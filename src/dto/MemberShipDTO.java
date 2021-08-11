package dto;

public class MemberShipDTO {
	private String memberShipName = "pt";
	private String memberId;
	private String memberEmail;
	private String memberShipDate;
	private int memberShipCount;
	public String getMemberShipName() {
		return memberShipName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberShipDate() {
		return memberShipDate;
	}
	public void setMemberShipDate(String memberShipDate) {
		this.memberShipDate = memberShipDate;
	}
	public int getMemberShipCount() {
		return memberShipCount;
	}
	public void setMemberShipCount(int memberShipCount) {
		this.memberShipCount = memberShipCount;
	}
}
