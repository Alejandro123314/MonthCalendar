package dad.calendario;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MonthCalendar extends GridPane implements Initializable {

	private IntegerProperty anio = new SimpleIntegerProperty();
	private IntegerProperty mes = new SimpleIntegerProperty();

	@FXML
	private Label month;

	public MonthCalendar() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		anio.addListener((o,ov,nv)->cambiar(o,ov,nv));
		mes.addListener((o,ov,nv)->cambiar(o,ov,nv));
	}

	private void cambiar(ObservableValue<? extends Number> o, Number ov, Number nv) {
		int primerDia = DiasCalendar.obtenerPrimerDia(getAnio(), getMes()-1);
		int ultimoDia = DiasCalendar.obtenerUltimoDia(getAnio(), getMes()) ;				
		int fila = 2;
		
		getChildren().clear();
		add(new Label("L"), 0, 1);
		add(new Label("M"), 1, 1);
		add(new Label("X"), 2, 1);
		add(new Label("J"), 3, 1);
		add(new Label("V"), 4, 1);
		add(new Label("S"), 5, 1);
		add(new Label("D"), 6, 1);
		
		for(int i = 0, j = 1; i < ultimoDia; i++, j++, primerDia++) {

			int col = primerDia%7;
			if(col == 0) {
				fila++;
			}
			add(new Label(j+""), col, fila);
		}

		Date date = DiasCalendar.crearFecha(getAnio(), getMes(), 1);
		month.setText(new SimpleDateFormat("MMM").format(date));
		add(month, 3, 0);
	}

	public final IntegerProperty anioProperty() {
		return this.anio;
	}
	

	public final int getAnio() {
		return this.anioProperty().get();
	}
	

	public final void setAnio(final int anio) {
		this.anioProperty().set(anio);
	}
	

	public final IntegerProperty mesProperty() {
		return this.mes;
	}
	

	public final int getMes() {
		return this.mesProperty().get();
	}
	

	public final void setMes(final int mes) {
		this.mesProperty().set(mes);
	}
	

}
