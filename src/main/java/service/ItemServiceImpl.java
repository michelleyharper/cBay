package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ItemDao;
import dao.ItemJdbcDaoImpl;
import dao.DBUtil;
import exception.ApplicationException;
import pojo.ItemPojo;
import presentation.ShopMain;

public class ItemServiceImpl implements ItemService{

	private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);
	
	ItemDao itemDao;
	
	public ItemServiceImpl() {
		this.itemDao = new ItemJdbcDaoImpl();
	}
  
	@Override
	public ItemPojo addItem(ItemPojo itemPojo) throws ApplicationException {
		logger.info("Entered addItem() in service.");
		ItemPojo returnItemPojo = this.itemDao.addItem(itemPojo);
		logger.info("Exited addItem() in service.");
		return returnItemPojo;
	}

	@Override
	public ItemPojo updateItem(ItemPojo itemPojo) throws ApplicationException {
		logger.info("Entered updateItem() in service.");
		ItemPojo returnItemPojo = this.itemDao.updateItem(itemPojo);
		logger.info("Exited updateItem() in service.");
		return returnItemPojo;
	}

	@Override
	public boolean deleteItem(int itemId) throws ApplicationException {
		logger.info("Entered deleteItem() in service.");
		boolean returnFlag = this.itemDao.deleteItem(itemId);
		logger.info("Exited deleteItem() in service.");
		return returnFlag;
	}

	@Override
	public List<ItemPojo> getAllItems() throws ApplicationException {
		logger.info("Entered getAllItems() in service.");
		List<ItemPojo> allItems = this.itemDao.getAllItems();
		logger.info("Exited getAllItems() in service.");
		return allItems;
	}

	@Override
	public ItemPojo getAnItem(int itemId) throws ApplicationException {
		logger.info("Entered getAnItem() in service.");
		ItemPojo returnItemPojo = this.itemDao.getAnItem(itemId);
		logger.info("Exited getAnItem() in service.");
		return returnItemPojo;
	}
	
	@Override
	public void exitApplication() {
		logger.info("Entered exitApplication() in service.");
		itemDao.exitApplication();
		logger.info("Exited exitApplication() in service.");
	}

}