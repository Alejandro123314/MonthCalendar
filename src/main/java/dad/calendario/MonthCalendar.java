package dad.calendario;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
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
		
		Label l = new Label("L");
		l.getStyleClass().add("weekday");
		
		Label m = new Label("M");
		m.getStyleClass().add("weekday");
		
		Label x = new Label("X");
		x.getStyleClass().add("weekday");
		
		Label j1 = new Label("J");
		j1.getStyleClass().add("weekday");
		
		Label v = new Label("V");
		v.getStyleClass().add("weekday");
		
		Label s = new Label("S");
		s.getStyleClass().add("weekday");
		
		Label d = new Label("D");
		d.getStyleClass().addAll("weekday","sunday");
		
		
		add(l, 0, 1);
		add(m, 1, 1);
		add(x, 2, 1);
		add(j1, 3, 1);
		add(v, 4, 1);
		add(s, 5, 1);
		add(d, 6, 1);
		
		for(int i = 0, j = 1; i < ultimoDia; i++, j++, primerDia++) {

			int col = primerDia%7;
			if(col == 0) {
				fila++;
			}
			
			Label dia = new Label(j+"");
			dia.getStyleClass().add("day");
			
			if(col==6) {
				dia.getStyleClass().add("sunday");
			}
			
			if(LocalDate.of(anio.get(), mes.get(), j).equals(LocalDate.now())) {
				dia.getStyleClass().add("today");
			}
			
			add(dia, col, fila);
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
