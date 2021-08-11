package update.member;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import update.UpdateController;

public class MemberUpdateService {
	public void memberUpdateOpen() {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/update/member/memberUp.fxml"));
		Parent memberForm = null;
		try {
			memberForm = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UpdateController upCtrl = loader.getController();
		upCtrl.setMemberForm(memberForm);
		
		stage.setScene(new Scene(memberForm));
		stage.show();
		
	}

}
