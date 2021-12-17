package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.OfferDao;
import dao.OfferJdbcDaoImpl;
import exception.ApplicationException;
import pojo.OfferPojo;

public class OfferServiceImpl implements OfferService {

	public static final Logger logger = LogManager.getLogger(OfferServiceImpl.class);

	OfferDao offerDao;

	public OfferServiceImpl() {
		this.offerDao = new OfferJdbcDaoImpl();
	}

	public OfferPojo addOffer(OfferPojo offerPojo) throws ApplicationException {
		logger.info("Entered addOffer() in service.");
		OfferPojo returnedOfferPojo = this.offerDao.addOffer(offerPojo);
		logger.info("Exited addOffer() in service.");
		return returnedOfferPojo;
	}

	public boolean offerAccepted(int offerId) throws ApplicationException {
		logger.info("Entered offerAccepted() in service.");
		boolean returnFlag = this.offerDao.offerAccepted(offerId);
		logger.info("Exited offerAccepted() in service.");
		return returnFlag;
	}

	public List<OfferPojo> getAllOffers() throws ApplicationException {
		logger.info("Entered getAllOffers() in service.");
		List<OfferPojo> allOffers = this.offerDao.getAllOffers();
		logger.info("Exited getAllOffers() in service.");
		return allOffers;
	}

	public OfferPojo manageOffer(OfferPojo offerPojo) throws ApplicationException {
		logger.info("Entered manageOffer() in service.");
		OfferPojo returnedOfferPojo = this.offerDao.manageOffer(offerPojo);
		logger.info("Exited manageOffer() in service.");
		return returnedOfferPojo;
	}

	public OfferPojo getAnOffer(int offerId) throws ApplicationException {
		logger.info("Entered getAnOffer() in service.");
		OfferPojo returnedOfferPojo = this.offerDao.getAnOffer(offerId);
		logger.info("Exited getAnOffer() in service.");
		return returnedOfferPojo;
	}

	public OfferPojo deleteOffer(OfferPojo offerPojo) throws ApplicationException {
		logger.info("Entered deleteOffer() in service.");
		OfferPojo returnedOfferPojo = this.offerDao.deleteOffer(offerPojo);
		logger.info("Exited deleteOffer() in service.");
		return returnedOfferPojo;
		
	}

	public List<OfferPojo> getWonOffers(int offerId) throws ApplicationException {
		logger.info("Entered getWonOffers() in service.");
		List<OfferPojo> allWonOffers = this.offerDao.getWonOffers(offerId);
		logger.info("Exited getAnOffer() in service.");
		return allWonOffers;
	
	}

	public void exitApplication() {
		logger.info("Entered exitApplication() in service.");
		offerDao.exitApplication();
		logger.info("Exited exitApplication() in service.");

	
	}

}