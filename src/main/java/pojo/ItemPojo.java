package pojo;

import java.util.Objects;

public class ItemPojo {

	private int itemId;
	private String itemName;
	private String itemCondition;
	private int itemPrice;
	private boolean itemOwned;
	private boolean itemRemoved;
	
	public ItemPojo() {
		super();
	}

	public ItemPojo(int itemId, String itemName, String itemCondition, int itemPrice, boolean itemOwned, boolean itemRemoved) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemCondition = itemCondition;
		this.itemPrice = itemPrice;
		this.itemOwned = itemOwned;
		this.itemRemoved = itemRemoved;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCondition() {
		return itemCondition;
	}

	public void setItemCondition(String itemCondition) {
		this.itemCondition = itemCondition;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public boolean isItemOwned() {
		return itemOwned;
	}
	
	public void setItemOwned(boolean itemOwned) {
		this.itemOwned = itemOwned;
	}

	public boolean isItemRemoved() {
		return itemRemoved;
	}

	public void setItemRemoved(boolean itemRemoved) {
		this.itemRemoved = itemRemoved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemCondition == null) ? 0 : itemCondition.hashCode());
		result = prime * result + itemId;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + itemPrice;
		result = prime * result + (itemOwned ? 1231 : 1237);
		result = prime * result + (itemRemoved ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPojo other = (ItemPojo) obj;
		if (itemCondition == null) {
			if (other.itemCondition != null)
				return false;
		} else if (!itemCondition.equals(other.itemCondition))
			return false;
		if (itemId != other.itemId)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemPrice != other.itemPrice)
			return false;
		if (itemOwned != other.itemOwned)
			return false;
		if (itemRemoved != other.itemRemoved)
			return false;
		return true;
	
	}

	@Override
	public String toString() {
		return "ItemPojo [itemId=" + itemId + ", itemName=" + itemName + ", itemCondition=" + itemCondition
				+ ", itemPrice=" + itemPrice + ", itemOwned=" + itemOwned + ", itemRemoved=" + itemRemoved + "]";
	}

}
