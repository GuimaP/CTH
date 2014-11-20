package controller.events;

import java.time.LocalDate;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

public class EventsDate {
	public static void setBeforeDateDisable(DatePicker dt){
		
	        final Callback<DatePicker, DateCell> dayCellFactory = 
	                new Callback<DatePicker, DateCell>() {
	                    @Override
	                    public DateCell call(final DatePicker datePicker) {
	                        return new DateCell() {
	                            @Override
	                            public void updateItem(LocalDate item, boolean empty) {
	                                super.updateItem(item, empty);
	                                if (item.isBefore(
	                                		LocalDate.now().minusDays(0))
	                                    ) {
	                                        setDisable(true);
	                                        setStyle("-fx-background-color: #ffc0cb;");
	                                }   
	                        }
	                    };
	                }
	            };
			
			dt.setDayCellFactory(dayCellFactory);
	}
	
	
	public static void setAfterDateDisble(DatePicker dt){
		
	        final Callback<DatePicker, DateCell> dayCellFactory = 
	                new Callback<DatePicker, DateCell>() {
	                    @Override
	                    public DateCell call(final DatePicker datePicker) {
	                        return new DateCell() {
	                            @Override
	                            public void updateItem(LocalDate item, boolean empty) {
	                                super.updateItem(item, empty);
	                                if (item.isAfter(LocalDate.now().plusDays(0))){
	                                        setDisable(true);
	                                        setStyle("-fx-background-color: #ffc0cb;");
	                                }   
	                        }
	                    };
	                }
	            };
			
			dt.setDayCellFactory(dayCellFactory);
	}
	
	
}
