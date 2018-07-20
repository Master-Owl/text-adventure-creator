package dataobjects.items;

import javafx.scene.control.ListCell;
import model.Model;

import java.io.Serializable;

public class BaseItem extends ListCell<BaseItem> implements Serializable {
    private static final long serialVersionUID = 7887208527609585479L;

    protected String itemName;
    protected String itemDescription;
    protected int itemId;
    private static int idCounter = -1;

    public BaseItem(){}
    public BaseItem(String itemName, String itemDescription) {
        if (idCounter == -1)
            idCounter = Model.instance.getItemIdCounter();

        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemId = idCounter++;

        Model.instance.addItem(this);
    };

    public int itemId() { return itemId; }

    @Override
    protected void updateItem(BaseItem item, boolean empty) {
        super.updateItem(item, empty);
        this.setGraphic(null);

        String displayText = null;

        if (item != null && !empty) {
            displayText = item.toString();
        }

        this.setText(displayText);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return this.itemId == ((BaseItem)obj).itemId;
    }

    @Override
    public String toString() { return itemName; }
}
