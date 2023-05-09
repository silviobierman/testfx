package testfx;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainPane extends BorderPane {

	private HBox topBox;
	private Button addButton;
	private Button deleteButton;
	private Button storeButton;
	private DataListView listView;
	private DataDetailsPane detailsPane;
	private ArrayList<DataItem> dataItems;

	private Button createAddButton() {
		Button button = new Button("Toevoegen");
		button.setOnAction(e -> addButtonClicked());
		return button;
	}
	
	private Button createDeleteButton() {
		Button button = new Button("Verwijderen");
		button.setOnAction(e -> deleteButtonClicked());
		return button;
	}
	
	private Button createStoreButton() {
		Button button = new Button("Opslaan");
		button.setOnAction(e -> storeButtonClicked());
		return button;
	}
	
	private HBox createTopBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15,12,15,12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #336699;");
		addButton = createAddButton();
		deleteButton = createDeleteButton();
		storeButton = createStoreButton();
		hbox.getChildren().add(addButton);
		hbox.getChildren().add(deleteButton);
		hbox.getChildren().add(storeButton);
		return hbox;
	}
	
	private DataListView createDataList(DataDetailsPane detailsPane) {
		return new DataListView(detailsPane);
	}

	private DataDetailsPane createDetailsPane() {
		return new DataDetailsPane();
	}
	
	private void addButtonClicked() {
		DataItem add = new DataItem("nieuw","",LocalDate.now());
		dataItems.add(add);
		dataItems.sort((a,b) -> a.compareTo(b));
		listView.loadItems(dataItems,add);
	}
	
	private void deleteButtonClicked() {
		DataItem current = listView.getCurrentItem();
		if (current != null)
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Verwijderen");
			alert.setContentText("Weet je zeker dat je \"" + current.getLabel() + "\" wilt verwijderen?");
			if (alert.showAndWait().get() == ButtonType.OK) {
				dataItems.remove(current);
				listView.loadItems(dataItems,null);
			}
		}
	}
	
	private void storeButtonClicked() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Opslaan");
		alert.setContentText("Er zijn " + dataItems.size() + " items opgeslagen.");
		alert.showAndWait();
	}
	
	private ArrayList<DataItem> loadDataItems() {
		ArrayList<DataItem> items = new ArrayList<>();
		items.add(new DataItem("Verjaardag mama","Wordt ze 37...",LocalDate.of(2023,5,5)));
		items.add(new DataItem("Verjaardag Lana","Papa's kleine meid wordt groot",LocalDate.of(2023,8,14)));
		items.add(new DataItem("Verjaardag papa","Wordt ie nog ouder!",LocalDate.of(2023,10,7)));
		items.add(new DataItem("Tandarts","Halfjaarlijkse controle",LocalDate.of(2023,10,17)));
		return items;
	}
	
	public MainPane() {
		topBox = createTopBox();
		detailsPane = createDetailsPane();
		listView = createDataList(detailsPane);
		dataItems = loadDataItems();
		setTop(topBox);
		setLeft(listView);
		setCenter(detailsPane);
		listView.loadItems(dataItems,null);
	}
}
