package service;

import java.util.List;

import exception.ApplicationException;
import pojo.ItemPojo;

public interface ItemService {
	ItemPojo addItem(ItemPojo itemPojo) throws ApplicationException;
	ItemPojo updateItem(ItemPojo itemPojo) throws ApplicationException;
	boolean deleteItem(int itemId) throws ApplicationException;
	List<ItemPojo> getAllItems() throws ApplicationException;
	ItemPojo getAnItem(int itemId) throws ApplicationException;
	void exitApplication(); 
}