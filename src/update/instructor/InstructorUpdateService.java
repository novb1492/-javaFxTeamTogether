package update.instructor;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import update.UpdateController;

public class InstructorUpdateService {
	public void instructorUpdateOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/update/instructor/instructorUp.fxml"));
		Parent instructorForm = null;
		Stage stage = new Stage();
		try {
			instructorForm = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UpdateController upCtrl = loader.getController();
		upCtrl.setInstructorForm(instructorForm);
		
		stage.setScene(new Scene(instructorForm));
		stage.show();
		
	}
}
