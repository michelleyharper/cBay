package service;

import java.util.List;

import exception.ApplicationException;
import pojo.ItemPojo;
import pojo.OfferPojo;

public interface OfferService {
	
	OfferPojo addOffer(OfferPojo offerPojo) throws ApplicationException; // same as add item (IN USE)
	boolean offerAccepted(int offerId) throws ApplicationException; // false = rejected (IN USE)
	List<OfferPojo> getAllOffers() throws ApplicationException; // same as get all items (IN USE)
	OfferPojo manageOffer(OfferPojo offerPojo) throws ApplicationException; // same as update item (IN USE)
	OfferPojo getAnOffer(int offerId) throws ApplicationException; // same as get an item (IN USE)
	OfferPojo deleteOffer(OfferPojo offerPojo) throws ApplicationException; // same as delete item
	List<OfferPojo> getWonOffers(int offerId) throws ApplicationException;
	void exitApplication();

}