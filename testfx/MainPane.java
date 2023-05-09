package testfx;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.animation.RotateTransition; 
import javafx.scene.Group; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Polygon; 
import javafx.util.Duration; 

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
		hbox.setStyle("-fx-background-color: pink;");
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
		items.add(new DataItem("Verjaardag mama","Wordt ze 37...",LocalDate.of(2023,5,4)));
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

		//Creating a hexagon 
		Polygon hexagon = new Polygon();        

		//Adding coordinates to the hexagon 
		hexagon.getPoints().addAll(new Double[]{        
		200.0, 50.0, 
		400.0, 50.0, 
		450.0, 150.0,          
		400.0, 250.0, 
		200.0, 250.0,                   
		150.0, 150.0, 
		}); 
		//Setting the fill color for the hexagon 
		hexagon.setFill(Color.MAGENTA); 

		//Creating a rotate transition    
		RotateTransition rotateTransition = new RotateTransition(); 

		//Setting the duration for the transition 
		rotateTransition.setDuration(Duration.millis(2000)); 

		//Setting the node for the transition 
		rotateTransition.setNode(hexagon);       

		//Setting the angle of the rotation 
		rotateTransition.setByAngle(360); 

		//Setting the cycle count for the transition 
		rotateTransition.setCycleCount(10); 

		//Setting auto reverse value to false 
		rotateTransition.setAutoReverse(true); 

		//Playing the animation 
		rotateTransition.play(); 

		Polygon triangle = new Polygon();        

		//Adding coordinates to the hexagon 
		triangle.getPoints().addAll(new Double[]{        
		100.0, 100.0, 
		250.0, 250.0, 
		100.0, 300.0 
		}); 

		//Creating a Group object   
		Group root = new Group(hexagon,triangle); 
		setBottom(root);
	}
}
