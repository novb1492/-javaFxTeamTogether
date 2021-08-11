package update;

import java.net.URL;
import java.util.ResourceBundle;

import common.commonService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class UpdateController implements Initializable{
	private Parent memberForm;
	private Parent instructorForm;
	private Parent memberShipForm;
	
	public void setMemberForm(Parent memberForm) {
		this.memberForm = memberForm;
	}
	public void setInstructorForm(Parent instructorForm) {
		this.instructorForm = instructorForm;
	}
	public void setMemberShipForm(Parent memberShipForm) {
		this.memberShipForm = memberShipForm;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void CancleProc(ActionEvent event) {
		commonService.windowClose(event);
	}
	
}
