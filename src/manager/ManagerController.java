package manager;

import update.instructor.InstructorUpdateService;
import update.member.MemberUpdateService;
import update.membership.MembershipUpdateService;

public class ManagerController {
	private MemberUpdateService memberUpSvc = new MemberUpdateService();
	private MembershipUpdateService membershipUpSvc = new MembershipUpdateService();
	private InstructorUpdateService instructorUpSvc = new InstructorUpdateService();
	
	public void openMember() {
		memberUpSvc.memberUpdateOpen();
	}
	public void openInstructor() {
		instructorUpSvc.instructorUpdateOpen();
	}
	public void openMembership() {
		membershipUpSvc.membershipUpdateOpen();
	}

}
