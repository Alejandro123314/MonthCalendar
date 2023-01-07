import dad.controller.CalendarController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

	private CalendarController calendar;
	@Override
	public void start(Stage primaryStage) throws Exception {
		calendar = new CalendarController();
		Scene scene = new Scene(calendar.getRoot());
		primaryStage.setTitle("Calendario");
		primaryStage.getIcons().add(new Image("/image/calendar-16x16.png"));
		primaryStage.getIcons().add(new Image("/image/calendar-32x32.png"));
		primaryStage.getIcons().add(new Image("/image/calendar-64x64.png"));
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
		
	}

}
