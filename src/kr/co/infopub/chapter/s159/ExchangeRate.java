package kr.co.infopub.chapter.s159;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class ExchangeRate extends Application {
 @Override
 public void start(Stage primaryStage) {
	try {
		primaryStage.setTitle("Exchange Rate");
		BorderPane root = (BorderPane)FXMLLoader.load(getClass()
				.getResource("ExchangeRateFx.fxml"));
		Scene scene = new Scene(root,800,400);
		scene.getStylesheets().add(getClass()
				.getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	} catch(Exception e) {
		e.printStackTrace();
	}
 }
 public static void main(String[] args) {
	launch(args);
 }
}
