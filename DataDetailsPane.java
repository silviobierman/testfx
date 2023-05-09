package testfx;

import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

class DataDetailsPane extends GridPane {

	private Label labelLabel;
	private TextField labelValue;
	private Label descriptionLabel;
	private TextField descriptionValue;
	private Label dateLabel;
	private DatePicker dateValue;

  public void loadDataItem(DataItem item)
	{
		labelValue.setText(item.getLabel());
		descriptionValue.setText(item.getDescription());
		dateValue.setValue(item.getDate());
	}

  public void storeDataItem(DataItem item)
	{
		item.set(labelValue.getText(),descriptionValue.getText(),dateValue.getValue());
	}

	public DataDetailsPane() {
		labelLabel = new Label("Label");
		labelValue = new TextField();
		labelLabel.setMinWidth(140);
		labelValue.setPrefWidth(1000);
		descriptionLabel = new Label("Omschrijving");
		descriptionValue = new TextField();
		dateLabel = new Label("Datum");
		dateValue = new DatePicker();
		add(labelLabel,0,0,1,1);
		add(labelValue,1,0,1,1);
		add(descriptionLabel,0,1,1,1);
		add(descriptionValue,1,1,1,1);
		add(dateLabel,0,2,1,1);
		add(dateValue,1,2,1,1);
		setPadding(new Insets(15,12,15,12));
		setHgap(10);
		setVgap(10);
	}
}
