package testfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestFX extends Application {

	private MainPane main;

	@Override
	public void start(Stage stage) {
		main = new MainPane();
		Scene scene = new Scene(main, 1000, 800);
		stage.setTitle("Lana Agenda Systeem");
		stage.setScene(scene);
		stage.setMinWidth(300);
		stage.setMinHeight(200);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}