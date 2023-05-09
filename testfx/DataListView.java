package testfx;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

class DataListView extends ListView<DataItem> {

	private DataDetailsPane detailsPane;
	private ObservableList<DataItem> dataItems;
	private DataItem current;

	private void itemSelectionChanged(DataItem prev,DataItem cur) {
		if (prev != null) detailsPane.storeDataItem(prev);
		if (cur != null) detailsPane.loadDataItem(cur);
		current = cur;
	}

	public DataItem getCurrentItem() {
		return current;
	}

	public void loadItems(ArrayList<DataItem> items,DataItem select) {
		dataItems.setAll(items);
		current = select;
		if (current != null) detailsPane.loadDataItem(current);
		if (!items.isEmpty())
		{
			int index = items.indexOf(select);
			getSelectionModel().clearAndSelect(index >= 0 ? index : 0);
		}
	}

	public DataListView(DataDetailsPane _detailsPane) {
		detailsPane = _detailsPane;
		dataItems = FXCollections.observableArrayList();
		setItems(dataItems);
		getSelectionModel().selectedItemProperty().addListener((v,prev,cur) -> itemSelectionChanged(prev,cur));
	}
}
