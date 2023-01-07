package dad.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dad.calendario.MonthCalendar;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class CalendarController implements Initializable {
	
	private IntegerProperty anio = new SimpleIntegerProperty();
	private ArrayList<MonthCalendar> meses = new ArrayList<>();

    @FXML
    private Label anioLabel;

    @FXML
    private Button derechaButton;

    @FXML
    private Button izquierdaButton;

    @FXML
    private GridPane mesGridPane;

    @FXML
    private BorderPane root;
    
    public CalendarController() {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalendarView.fxml"));
        	loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(int i = 0; i < 12; i++) {
			MonthCalendar calendar = new MonthCalendar();
			calendar.setMes(i+1);
			calendar.anioProperty().bind(anio);
			meses.add(calendar);
			mesGridPane.add(calendar, i%4, i/4);
		}
		anioLabel.textProperty().bind(anio.asString());
		anio.set(LocalDate.now().getYear());
		
		izquierdaButton.setOnAction(e -> bajarAnio(e));
		derechaButton.setOnAction(e -> subirAnio(e));
	}
	
	private void subirAnio(ActionEvent e) {
		anio.set(anio.get()+1);
	}

	private void bajarAnio(ActionEvent e) {
		anio.set(anio.get()-1);
	}
	
	

	public BorderPane getRoot() {
		return root;
	}
	
	

}
