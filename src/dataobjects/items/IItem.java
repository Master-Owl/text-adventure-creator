package dataobjects.items;

import dataobjects.Exit;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import model.Model;

public abstract class IItem extends ListCell<IItem> {
    public String itemName;
    public String itemDescription;

    public IItem(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    };

    @Override
    protected void updateItem(IItem item, boolean empty) {
        super.updateItem(item, empty);
        String displayText = null;

        if (item != null && !empty) {
            displayText = item.toString();
        }

        this.setText(displayText);
        this.setGraphic(null);
    }

    @Override
    public String toString() { return itemName; }
}
