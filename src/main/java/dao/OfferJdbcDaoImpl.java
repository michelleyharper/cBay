package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.ApplicationException;
import pojo.ItemPojo;
import pojo.OfferPojo;

public class OfferJdbcDaoImpl implements OfferDao {

	private static final Logger logger = LogManager.getLogger(OfferJdbcDaoImpl.class);

	public OfferPojo addOffer(OfferPojo offerPojo) throws ApplicationException {
		logger.info("Entered addOffer() in dao.");

		offerPojo.setOfferAccepted(false);

		Connection conn = DBUtil.makeConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "insert into offer_details(item_id, user_id, offer_price, offer_accepted)" + "values("
					+ offerPojo.getItemId() + "," + offerPojo.getUserId() + "," + offerPojo.getOfferPrice() + ","
					+ offerPojo.isOfferAccepted() + ") returning offer_id";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			offerPojo.setOfferId(rs.getInt(1));
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		logger.info("Exited addOffer() in dao.");
		return offerPojo;
	}

	public boolean offerAccepted(int offerId) throws ApplicationException {
		logger.info("Entered offerAccepted() in dao.");

		boolean flag = true;
		Connection conn = DBUtil.makeConnection();
		int rowsAffected = 0;
		try {
			Statement stmt = conn.createStatement();
			String query = "update offer_details set offer_accepted=true where offer_id=" + offerId;
			rowsAffected = stmt.executeUpdate(query);
			System.out.println(rowsAffected);

		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		if (rowsAffected == 0)
			flag = false;

		logger.info("Exited offerAccepted() in dao.");
		return flag;
	}

	public List<OfferPojo> getAllOffers() throws ApplicationException {
		logger.info("Entered getAllOffers() in dao.");

		List<OfferPojo> allOffers = new ArrayList<OfferPojo>();

		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select * from offer_details where offer_accepted=false";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				OfferPojo offerPojo = new OfferPojo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
						rs.getBoolean(5));

				allOffers.add(offerPojo);
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getAllOffers() in dao.");
		return allOffers;
	}

	public OfferPojo manageOffer(OfferPojo offerPojo) throws ApplicationException {
		logger.info("entered manageOffer() in dao.");

		Connection conn = DBUtil.makeConnection();
		try {
			Statement stmt = conn.createStatement();
			String query1 = "update offer_details set offer_accepted=true where item_id=" + offerPojo.getItemId()
					+ "and user_id=" + offerPojo.getUserId();
			String query2 = "update item_details set item_removed=true where item_id=" + offerPojo.getItemId();

			stmt.executeUpdate(query1);
			stmt.executeUpdate(query2);

		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		logger.info("Exited manageOffer() in dao.");
		return offerPojo;
	}

	public OfferPojo getAnOffer(int offerId) throws ApplicationException {
		logger.info("Entered getAnOffer() in dao.");

		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		OfferPojo offerPojo = null;
		try {
			stmt = conn.createStatement();
			String query = "select * from offer_details where offer_id=" + offerId + "and offer_accepted=false";
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				offerPojo = new OfferPojo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getAnOffer() in dao.");
		return offerPojo;

	}
	
	public List<OfferPojo> getWonOffers(int offerId) throws ApplicationException {
		logger.info("Entered getWonOffers() in dao.");
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		List<OfferPojo> allWonOffers = new ArrayList<OfferPojo>();
		try {
			stmt = conn.createStatement();
			String query = "select * from offer_details where offer_id=" + offerId + "and offer_accepted=true";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				OfferPojo offerPojo = new OfferPojo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5));
				
				allWonOffers.add(offerPojo);
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getWonOffer() in dao.");
		return allWonOffers;
	}

	public OfferPojo deleteOffer(OfferPojo offerPojo) throws ApplicationException {
		logger.info("Entered deleteOffer() in dao.");

		Connection conn = DBUtil.makeConnection();
		int rowsAffected = 0;
		try {
			Statement stmt = conn.createStatement();
			String query = "delete from offer_details where offer_id=" + offerPojo.getOfferId();

			rowsAffected = stmt.executeUpdate(query);
			System.out.println(rowsAffected);

		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		if (rowsAffected == 0)
			return offerPojo;

		logger.info("Exited deleteOffer() in dao.");
		return offerPojo;

	}

	public void exitApplication() {
		logger.info("Entered exitApplication() in dao.");
		DBUtil.closeConnection();
		logger.info("Exited exitApplication() in dao.");

	}

}