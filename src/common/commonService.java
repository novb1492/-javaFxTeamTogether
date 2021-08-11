package common;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class commonService {

	public static void windowClose(ActionEvent event) {
		Parent parent = (Parent)event.getSource();
		Stage stage = (Stage) parent.getScene().getWindow();
		stage.close();
	}
	public static void ErrorMsg(String title, String headerText, String contentText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.show();
	}
	public static void ErrorMsg(String headerText, String contentText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.show();
	}
	public static void ErrorMsg(String contentText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("오류 발생");
		alert.setContentText(contentText);
		alert.show();
	}
	public static void info(String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(contentText);
		alert.show();
	}
		
	
}