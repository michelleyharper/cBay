package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.ApplicationException;
import pojo.ItemPojo;
import presentation.ShopMain;

public class ItemJdbcDaoImpl implements ItemDao {

	private static final Logger logger = LogManager.getLogger(ItemJdbcDaoImpl.class);
	
	// ADD ITEM
	public ItemPojo addItem(ItemPojo itemPojo) throws ApplicationException {
		logger.info("Entered addItem() in dao.");
		
		// this bookPojo does not have a book id set in it.
		//set the book_removed to false
		itemPojo.setItemOwned(false);
		itemPojo.setItemRemoved(false);
		
		// jdbc steps 3 and 4
		Connection conn = DBUtil.makeConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "insert into item_details(item_name, item_condition, item_price, item_owned, item_removed)" 
					+ "values('"+itemPojo.getItemName()+"','"+itemPojo.getItemCondition()
					+"',"+itemPojo.getItemPrice()+","+itemPojo.isItemOwned()+","+itemPojo.isItemRemoved()+") returning item_id";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			itemPojo.setItemId(rs.getInt(1));
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		
		logger.info("Exited addItem() in dao.");
		return itemPojo;
	}
	
	// UPDATE ITEM
	public ItemPojo updateItem(ItemPojo itemPojo) throws ApplicationException {
		logger.info("Entered updateItem() in dao.");
		
		// jdbc step 3 and 4
		Connection conn = DBUtil.makeConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "update item_details set item_price="+itemPojo.getItemPrice()
							+" where item_id="+itemPojo.getItemId();

			int rowsAffected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());		
		}
		
		logger.info("Exited updateItem() in dao.");
		return itemPojo;
	}
	
	// DELETE ITEM
	public boolean deleteItem(int itemId) throws ApplicationException {
		logger.info("Entered deleteItem() in dao.");
		
		boolean flag = true;
		Connection conn = DBUtil.makeConnection();
		int rowsAffected = 0;
		try {
			Statement stmt = conn.createStatement();
			// here we are not going to do a hard delete, we are going 
			// for a soft delete.
			String query1 = "update item_details set item_removed=true where item_id="+itemId;
			String query2 = "update item_details set item_owned=true where item_id="+itemId;
			rowsAffected = stmt.executeUpdate(query1);
			rowsAffected = stmt.executeUpdate(query2);
			System.out.println(rowsAffected);
			
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		if(rowsAffected == 0)
			flag = false;
		
		logger.info("Exited deleteItem() in dao.");
		return flag;
	}
	
	// LIST ALL ITEMS
	public List<ItemPojo> getAllItems() throws ApplicationException {
		logger.info("Entered getAllItems() in dao.");
		
		// create a empty collection which is going to hold all the records from the DB
		// as pojo Object
		List<ItemPojo> allItems = new ArrayList<ItemPojo>();

		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select * from item_details where item_removed=false";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				// here as we iterate through the rs we should
				// each record in a pojo object and
				// add it to the collection
				// and at the end we return the collection

				// as we iterate we are taking each record and storing it in a bookPojo object
				ItemPojo itemPojo = new ItemPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getBoolean(5), rs.getBoolean(6));

				// add the bookPojo obj to a collection
				allItems.add(itemPojo);

			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getAllItems() in dao.");
		return allItems;
	}

	public ItemPojo getAnItem(int itemId) throws ApplicationException {
		logger.info("Entered getAnItem() in dao.");
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		ItemPojo itemPojo = null;
		try {
			stmt = conn.createStatement();
			String query = "select * from item_details where item_id="+itemId
							+ "and item_removed=false";
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				itemPojo = new ItemPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getBoolean(5), rs.getBoolean(6));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getAnItem() in dao.");
		return itemPojo;
	}

	public void exitApplication() {
		logger.info("Entered exitApplication() in dao.");
		DBUtil.closeConnection();
		logger.info("Exited exitApplication() in dao.");
	}

}