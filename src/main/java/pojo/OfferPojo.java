package pojo;

import java.util.Objects;

public class OfferPojo {

	private int offerId;
	private int itemId;
	private int userId;
	private int offerPrice;
	private boolean offerAccepted;
//	private boolean paymentComplete;

	public OfferPojo() {
		super();
	}

	public OfferPojo(int offerId, int itemId, int userId, int offerPrice, boolean offerAccepted) {
		super();
		this.offerId = offerId;
		this.itemId = itemId;
		this.userId = userId;
		this.offerPrice = offerPrice;
		this.offerAccepted = offerAccepted;

	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(int offerPrice) {
		this.offerPrice = offerPrice;
	}

	public boolean isOfferAccepted() {
		return offerAccepted;
	}

	public void setOfferAccepted(boolean offerAccepted) {
		this.offerAccepted = offerAccepted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemId;
		result = prime * result + offerId;
		result = prime * result + offerPrice;
		result = prime * result + (offerAccepted ? 1231 : 1237);
		result = prime * result + userId;
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
		OfferPojo other = (OfferPojo) obj;
		if (itemId != other.itemId)
			return false;
		if (offerId != other.offerId)
			return false;
		if (offerPrice != other.offerPrice)
			return false;
		if (offerAccepted != other.offerAccepted)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OfferPojo [offerId=" + offerId + ", itemId=" + itemId + ", userId=" + userId + ", offerPrice="
				+ offerPrice + ", offerAccepted=" + offerAccepted + "]";
	}


}