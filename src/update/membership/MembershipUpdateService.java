package update.membership;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import update.UpdateController;

public class MembershipUpdateService {
	public void membershipUpdateOpen() {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/update/membership/membershipUp.fxml"));
		Parent membershipForm = null;
		try {
			membershipForm = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UpdateController upCtrl = loader.getController();
		upCtrl.setMemberShipForm(membershipForm);
		
		stage.setScene(new Scene(membershipForm));
		stage.show();
		
	}
}
