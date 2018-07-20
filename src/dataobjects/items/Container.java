package dataobjects.items;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Container extends BaseItem {
    private static final long serialVersionUID = -4670927727628926183L;

    private TreeMap<String, ArrayList<BaseItem>> items;
    private int maxSize = -1;

    public Container(String itemName, String itemDescription) {
        super(itemName, itemDescription);
        items = new TreeMap<>();
    }

    public Container(String itemName, String itemDescription, int maxSize) {
        super(itemName, itemDescription);
        this.maxSize = maxSize;
        items = new TreeMap<>();
    }

    public void setMaxSize(int maxSize) { this.maxSize = maxSize; }

    public BaseItem getItem(String itemName) {
        BaseItem item = items.get(itemName).get(0);
        items.get(itemName).remove(0);
        if (items.get(itemName).size() == 0)
            items.remove(itemName);
        return item;
    }

    public boolean addItem(BaseItem item) {
        if (getTotalItemCount() >= maxSize)
            return false;

        if (!items.containsKey(item.itemName)) {
            ArrayList<BaseItem> itemList = new ArrayList<>();
            itemList.add(item);
            items.put(item.itemName, itemList);
        }
        else {
            items.get(item.itemName).add(item);
        }

        return true;
    }

    private int getTotalItemCount() {
        int count = 0;
        for (Map.Entry<String, ArrayList<BaseItem>> entry : items.entrySet()) {
            count += entry.getValue().size();
        }
        return count;
    }

}
