package presentation;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ItemDao;
import exception.ApplicationException;
import pojo.ItemPojo;
import pojo.OfferPojo;
import pojo.UserPojo;
import service.ItemService;
import service.ItemServiceImpl;
import service.OfferService;
import service.OfferServiceImpl;
import service.UserService;
import service.UserServiceImpl;

public class ShopMain {
	private static final Logger logger = LogManager.getLogger(ShopMain.class);

	public static void main(String[] args) throws ApplicationException {
		logger.info("User Management System Application started...");

		UserService userService = new UserServiceImpl();
		ItemService itemService = new ItemServiceImpl();
		OfferService offerService = new OfferServiceImpl();
//		PaymentService paymentService = new PaymentServiceImpl();

		Scanner scan = new Scanner(System.in);

		int option = 0;
		String ch = "y";
		
		while ("y".equals(ch)) {
			System.out.println("==========================================");
			System.out.println("|            Welcome to cBay             |");
			System.out.println("==========================================");                           
			System.out.println("|----------------／＞-- フ-----------------");
			System.out.println("|-------------- |   _　_ ----| SHOP. |----");
			System.out.println("|------------- ／` ミ＿xノ----| BID.  |----");
			System.out.println("|------------ /　　　　 | ----| BUY.  |----");
			System.out.println("|----------- /　 ヽ　　 ﾉ ----| meow! |----");
			System.out.println("|---------- │　　|　|　| ------------------");
			System.out.println("|-------／￣|　　 |　|　| ------------------");
			System.out.println("|-------| (￣ヽ＿_ヽ_)__) -----------------");
			System.out.println("|-------＼二つ-----------------------------");
			System.out.println("==========================================");
			System.out.println("|    SHOP    |     BID     |     BUY     |");
			System.out.println("==========================================");
			System.out.println(" [1] New user? Register now!              "); // CHECK (3 PTS)
			System.out.println(" [2] Already have an Account? Login here! "); // CHECK (2 PTS)
			System.out.println(" [3] Exit                                 "); // CHECK
			System.out.println("==========================================");
			System.out.println("|        Please select an option:        |");
			System.out.println("==========================================");
			option = Integer.parseInt(scan.nextLine());
			System.out.println("==========================================");

			switch (option) {
			case 1:
				UserPojo userPojo = new UserPojo();
				System.out.println("Enter first name:");
				userPojo.setUserFirstName(scan.nextLine());

				System.out.println("Enter last name:");
				userPojo.setUserLastName(scan.nextLine());

				System.out.println("Enter password:");
				userPojo.setUserPassword(scan.nextLine());

				System.out.println("==========================================");
				System.out.println("|        Choose type of account:         |");
				System.out.println("==========================================");
				System.out.println(" [1] Customer                             "); 
				System.out.println(" [2] Employee                             ");
				System.out.println(" [3] Manager                              ");
				System.out.println("==========================================");
				System.out.println("|        Please select an option:        |");
				System.out.println("==========================================");
				int typeOption = Integer.parseInt(scan.nextLine());
				System.out.println("==========================================");
				if (typeOption == 1) {
					userPojo.setUserType("customer");
				} else if (typeOption == 2) {
					userPojo.setUserType("employee");
				} else if (typeOption == 3) {
					userPojo.setUserType("manager");
				}

				userPojo.setUserRemoved(false);

				UserPojo returnedUserPojo = null;
				try {
					returnedUserPojo = userService.register(userPojo);
				} catch (ApplicationException e) {
					System.out.println("==========================================");
					System.out.println("      Unable to connect to database       ");
					System.out.println("         Please try again later...        ");
					System.out.println("==========================================");
					logger.error(e.getMessage());
					break;
				}
				System.out.println("==========================================");
				System.out.println("|      Account Created Successfully!     |");
				System.out.println("==========================================");
				System.out.println("Your User ID is : " + returnedUserPojo.getUserId());
				break;

			case 2:
				UserPojo userLoginPojo = new UserPojo();

				System.out.println("Enter user ID:");
				userLoginPojo.setUserId(Integer.parseInt(scan.nextLine()));

				System.out.println("Enter user password:");
				userLoginPojo.setUserPassword(scan.nextLine());

				UserPojo returnedLoginUserPojo = null;
				try {
					returnedLoginUserPojo = userService.validateUser(userLoginPojo);
				} catch (ApplicationException e) {
					System.out.println("==========================================");
					System.out.println("      Unable to connect to database       ");
					System.out.println("         Please try again later...        ");
					System.out.println("==========================================");
					logger.error(e.getMessage());
					break;
				}

				String userType = returnedLoginUserPojo.getUserType();
				if (userType != null && userType.equals("customer")) {
					System.out.println("==========================================");
					System.out.println("        Customer Login Successful!        ");
					System.out.println("==========================================");
					System.out.println("******************************************");
					System.out.println("==========================================");
					System.out.println("|       Welcome to Customer Portal       |");
					System.out.println("==========================================");
					System.out.println("  Options:                                ");
					System.out.println("                                          ");
					System.out.println("  [1] View Available Items                "); // CHECK (1 PT)
					System.out.println("  [2] Make an Offer                       "); // CHECK (3 PT)
					System.out.println("  [3] View Remaining Payments             "); // NEED TO IMPLEMENT // Option to pay for item selected (after shown)
					System.out.println("  [4] View Owned Items                    "); // PENDING
					System.out.println("  [5] Logout                              "); // CHECK
					System.out.println("==========================================");
					System.out.println("|        Please select an option:        |");
					System.out.println("==========================================");
					option = Integer.parseInt(scan.nextLine());
					System.out.println("==========================================");
					
					switch (option) {
					case 1:
						System.out.println("******************************************");
						System.out.println("==========================================");
						System.out.println("|         Viewing Available Items        |");
						System.out.println("==========================================");
						List<ItemPojo> allItems;
						try {
							allItems = itemService.getAllItems();
						} catch (ApplicationException e) {
							System.out.println("==========================================");
							System.out.println("      Unable to connect to database       ");
							System.out.println("         Please try again later...        ");
							System.out.println("==========================================");
							logger.error(e.getMessage());
							break;
						}
						
						System.out.println("ID\tName\tCondition\tPrice");
						
						allItems.forEach((myItemPojo) -> {
							System.out.print(myItemPojo.getItemId() + "\t" + myItemPojo.getItemName() + "\t\t" + myItemPojo.getItemCondition() + "\t\t" + myItemPojo.getItemPrice());
							System.out.println();
						});
						System.out.println();
						break;
					case 2:
						System.out.println("******************************************");
						System.out.println("==========================================");
						System.out.println("|       Welcome to Auction Portal        |");
						System.out.println("==========================================");
						System.out.println("List of Available items:                  ");
						System.out.println();
						List<ItemPojo> allItems2;
						try {
							allItems2 = itemService.getAllItems();
						} catch (ApplicationException e) {
							System.out.println("==========================================");
							System.out.println("      Unable to connect to database       ");
							System.out.println("         Please try again later...        ");
							System.out.println("==========================================");
							logger.error(e.getMessage());
							break;
						}
						
						System.out.println("ID\tName\tCondition\tPrice");
						
						allItems2.forEach((myItemPojo) -> {
							System.out.print(myItemPojo.getItemId() + "\t" + myItemPojo.getItemName() 
							+ "\t\t" + myItemPojo.getItemCondition() + "\t\t" + myItemPojo.getItemPrice());
							System.out.println();
						});
						// MAKE OFFER (SAME AS ADD ITEM)
						OfferPojo offerPojo = new OfferPojo();
						System.out.println();
						System.out.println("==========================================");
						System.out.println("Enter ID of item you would like to bid on:");
						offerPojo.setItemId(Integer.parseInt(scan.nextLine()));
						
						System.out.println("Enter your user ID:");
						offerPojo.setUserId(Integer.parseInt(scan.nextLine()));
						
						System.out.println("Enter your offer price:");
						offerPojo.setOfferPrice(Integer.parseInt(scan.nextLine()));
						
						OfferPojo returnedOfferPojo;
						try {
							returnedOfferPojo = offerService.addOffer(offerPojo);
						} catch (ApplicationException e) {
							System.out.println("==========================================");
							System.out.println("      Unable to connect to database       ");
							System.out.println("         Please try again later...        ");
							System.out.println("==========================================");
							logger.error(e.getMessage());
							break;
						}
						System.out.println("==========================================");
						System.out.println("        Offer placed successfully!        ");
						System.out.println("==========================================");
						System.out.println("New offer ID is: " + returnedOfferPojo.getOfferId());
						break;
						
					case 3:
//						System.out.println();
////						// this line is to show the offers that accepted with "item_id" in the output
//						System.out.print("THESE ARE YOUR ACCEPTED ITEMS");
////						select * from offer_details where user_id= getUSerID(); and offer_acceted=true;
////						//with the "item_id" above the user will have the option to choose to direct them to the next database
//						System.out.print("ENTER ITEM ID IF YOU WANT MORE INFORMATION");
//						int ITEMID1 = Integer.parseInt(scan.nextLine());
////						transactionPojo1.setItem_id(ITEMID1);
////						//this line will show the user the Item information
////						select * from item_details where item_id= getitemID();
						break;
					case 4:
						System.out.println("******************************************");
						System.out.println("==========================================");
						System.out.println("|           Viewing Owned Items          |");
						System.out.println("==========================================");
						List<OfferPojo> allWonOffers;
						try {
							allWonOffers = offerService.getAllOffers();
						} catch (ApplicationException e) {
							System.out.println("==========================================");
							System.out.println("      Unable to connect to database       ");
							System.out.println("         Please try again later...        ");
							System.out.println("==========================================");
							logger.error(e.getMessage());
							break;
						}
						
						System.out.println("ID\tName\tCondition\tPrice");
						
						allWonOffers.forEach((myOfferPojo) -> {
							System.out.print(myOfferPojo.getOfferId() + "\t" + myOfferPojo.getItemId() + "\t\t" + myOfferPojo.getUserId() + "\t\t" + myOfferPojo.getOfferPrice());
							System.out.println();
						});
						System.out.println();
						break;
					case 5:
						userService.exitApplication();
						System.out.println("******************************************");
						System.out.println("==========================================");
						System.out.println("              Logging out...              ");
						System.out.println("==========================================");
						System.out.println("|         You are now signed out!        |");
						System.out.println("==========================================");
						logger.info("User logged out...");
						break;
					}

				} else if (userType != null && userType.equals("employee")) {
						System.out.println("==========================================");
						System.out.println("        Employee Login Successful!        ");
						System.out.println("==========================================");
						System.out.println("******************************************");
						System.out.println("==========================================");
						System.out.println("|       Welcome to Employee Portal       |");
						System.out.println("==========================================");
						System.out.println("  Options:                                ");
						System.out.println("                                          ");
						System.out.println("  [1] Add Item                            "); // CHECK (3 PTS)
						System.out.println("  [2] Update Item                         "); // *** BONUS *** (CHECK)
						System.out.println("  [3] Remove Item                         "); // CHECK (2 PTS)
						System.out.println("  [4] View Storefront                     "); // CHECK
						System.out.println("  [5] View Pending Offers                 "); // CHECK (2 PTS)
						System.out.println("  [6] View Payments                       "); // NEED TO IMPLEMENT
						System.out.println("  [7] Logout                              "); // CHECK
						System.out.println("==========================================");
						System.out.println("|        Please select an option:        |");
						System.out.println("==========================================");
						option = Integer.parseInt(scan.nextLine());
						System.out.println("==========================================");
							
						switch (option) {
						case 1:
							ItemPojo itemPojo = new ItemPojo();
							System.out.println("Enter item name:");
							itemPojo.setItemName(scan.nextLine());
							
							System.out.println("Enter item condition:");
							itemPojo.setItemCondition(scan.nextLine());
							
							System.out.println("Enter item price:");
							itemPojo.setItemPrice(Integer.parseInt(scan.nextLine()));

							ItemPojo returnedItemPojo;
							try {
								returnedItemPojo = itemService.addItem(itemPojo);
							} catch (ApplicationException e) {
								System.out.println("==========================================");
								System.out.println("      Unable to connect to database       ");
								System.out.println("         Please try again later...        ");
								System.out.println("==========================================");
								logger.error(e.getMessage());
								break;
							}
							System.out.println("==========================================");
							System.out.println("|        Item Added Successfully!        |");
							System.out.println("==========================================");
							System.out.println("New item ID is : " + returnedItemPojo.getItemId());
							break;
						case 2:
							System.out.println("Enter item ID to be updated:");
							int itemId = Integer.parseInt(scan.nextLine());

							try {
								itemPojo = itemService.getAnItem(itemId);
							} catch (ApplicationException e) {
								System.out.println("==========================================");
								System.out.println("      Unable to connect to database       ");
								System.out.println("         Please try again later...        ");
								System.out.println("==========================================");
								logger.error(e.getMessage());
								break;
							}
							if (itemPojo != null) {
								System.out.println("==========================================");
								System.out.println("|      Item details to be updated:       |");
								System.out.println("==========================================");
								System.out.println("Item ID: " + itemPojo.getItemId());
								System.out.println("Item Name: " + itemPojo.getItemName());
								System.out.println("Item Condition: " + itemPojo.getItemCondition());
								System.out.println("Item Price: " + itemPojo.getItemPrice());
								System.out.println("==========================================");
								itemPojo = new ItemPojo();
								itemPojo.setItemId(itemId);

								System.out.println("Enter updated item price:");
								itemPojo.setItemPrice(Integer.parseInt(scan.nextLine()));

								try {
									returnedItemPojo = itemService.updateItem(itemPojo);
								} catch (ApplicationException e) {
									System.out.println("==========================================");
									System.out.println("      Unable to connect to database       ");
									System.out.println("         Please try again later...        ");
									System.out.println("==========================================");
									logger.error(e.getMessage());
									break;
								}
								System.out.println("==========================================");
								System.out.println("|     Item Price Updated Successfully!   |");
								System.out.println("==========================================");

							} else {
								System.out.println("==========================================");
								System.out.println("      Item with ID " + itemId + " does not exist");
								System.out.println("            Please try again...           ");
								System.out.println("==========================================");
							}
							break;
							
						case 3:

							System.out.println("==========================================");
							System.out.println("|      Enter item ID to be removed:      |");
							System.out.println("==========================================");
							itemId = Integer.parseInt(scan.nextLine());

							try {
								itemPojo = itemService.getAnItem(itemId);
							} catch (ApplicationException e1) {
								System.out.println("==========================================");
								System.out.println("      Unable to connect to database       ");
								System.out.println("         Please try again later...        ");
								System.out.println("==========================================");
								logger.error(e1.getMessage());
								break;
							}
							if (itemPojo != null) {
								System.out.println("==========================================");
								System.out.println("|    Enter item details to be removed:   |");
								System.out.println("==========================================");
								System.out.println("Item ID: " + itemPojo.getItemId());
								System.out.println("Item Name: " + itemPojo.getItemName());
								System.out.println("Item Condition: " + itemPojo.getItemCondition());
								System.out.println("Item Price: " + itemPojo.getItemPrice());
								System.out.println("==========================================");
								System.out.println("  Are you sure you want to remove? (y/n): ");
								String con = scan.nextLine();
								System.out.println("==========================================");

								if ("y".equals(con)) {
									try {
										itemService.deleteItem(itemId);
									} catch (ApplicationException e) {
										System.out.println("==========================================");
										System.out.println("      Unable to connect to database       ");
										System.out.println("         Please try again later...        ");
										System.out.println("==========================================");
										logger.error(e.getMessage());
										break;
									}
									System.out.println("==========================================");
									System.out.println("|       Item Removed Successfully!       |");
									System.out.println("==========================================");
								}
							} else {
								System.out.println("==========================================");
								System.out.println("      Item with ID " + itemId + " does not exist");
								System.out.println("            Please try again...           ");
								System.out.println("==========================================");
							}
							break;
						case 4:
							System.out.println("******************************************");
							System.out.println("==========================================");
							System.out.println("|           Viewing Storefront           |");
							System.out.println("==========================================");
							List<ItemPojo> allItems;
							try {
								allItems = itemService.getAllItems();
							} catch (ApplicationException e) {
								System.out.println("==========================================");
								System.out.println("      Unable to connect to database       ");
								System.out.println("         Please try again later...        ");
								System.out.println("==========================================");
								logger.error(e.getMessage());
								break;
							}
							
							System.out.println("ID\tName\tCondition\tPrice");
							
							allItems.forEach((myItemPojo) -> {
								System.out.print(myItemPojo.getItemId() + "\t" + myItemPojo.getItemName() + "\t\t" + myItemPojo.getItemCondition() + "\t\t" + myItemPojo.getItemPrice());
								System.out.println();
							});
							System.out.println();
							break;
						case 5:
							System.out.println("******************************************");
							System.out.println("==========================================");
							System.out.println("|             Pending Offers             |");
							System.out.println("==========================================");
							List<OfferPojo> allOffers;
							try {
								allOffers = offerService.getAllOffers();
							} catch (ApplicationException e) {
								System.out.println("==========================================");
								System.out.println("      Unable to connect to database       ");
								System.out.println("         Please try again later...        ");
								System.out.println("==========================================");
								logger.error(e.getMessage());
								break;
							}
							
							System.out.println("ID\tItem ID\tUser ID\tOffer Price\tOffer Accepted");
							
							allOffers.forEach((myOfferPojo) -> {
								System.out.print(myOfferPojo.getOfferId() + "\t" + 
										myOfferPojo.getItemId() + "\t\t" + myOfferPojo.getUserId() + "\t\t" + 
										myOfferPojo.getOfferPrice() + "\t\t" + myOfferPojo.isOfferAccepted());
								System.out.println();
							});
							System.out.println();
							// ACCEPT OR REJECT (DELETE) PENDING OFFERS
							OfferPojo offerPojo = new OfferPojo();
							
							System.out.println("Enter offer ID to be updated:");
							int offerId = Integer.parseInt(scan.nextLine());
						
							try {
								offerPojo = offerService.getAnOffer(offerId);
							} catch (ApplicationException e) {
								System.out.println("==========================================");
								System.out.println("      Unable to connect to database       ");
								System.out.println("         Please try again later...        ");
								System.out.println("==========================================");
								logger.error(e.getMessage());
								break;
							}
							if(offerPojo != null) {
								System.out.println("******************************************");
								System.out.println("==========================================");
								System.out.println("|              Accept Offer              |");
								System.out.println("==========================================");
								System.out.println("Offer ID: " + offerPojo.getItemId());
								System.out.println("Offer Price: " + offerPojo.getOfferPrice());
								System.out.println("==========================================");
								System.out.println("Are you sure you want to accept this offer?(y/n) : ");
								String con = scan.nextLine();
								System.out.println("==========================================");
								
								if("y".equals(con)) {
									try {
										offerPojo = offerService.manageOffer(offerPojo);
									} catch (ApplicationException e) {
										System.out.println("==========================================");
										System.out.println("      Unable to connect to database       ");
										System.out.println("         Please try again later...        ");
										System.out.println("==========================================");
										logger.error(e.getMessage());
										break;
									}
									System.out.println("==========================================");
									System.out.println("|       Offer Accepted Successfully!      |");
									System.out.println("==========================================");;
								}
								else if("n".equals(con)) {
									OfferPojo offerPojo2 = new OfferPojo();
									
									allOffers.forEach((present) -> {
										System.out.print(present.getOfferId() + "\t" + present.getOfferPrice());
										System.out.println();
									});
									System.out.println();
									
									System.out.println("Enter offer Id:");
									int offerremoveID = Integer.parseInt(scan.nextLine());
									offerPojo2 = offerService.getAnOffer(offerremoveID);
									
									offerPojo2 = offerService.deleteOffer(offerPojo2);
									System.out.println("==========================================");
									System.out.println("|       Offer Removed Successfully!      |");
									System.out.println("==========================================");
								}
							} else {
								System.out.println("==========================================");
								System.out.println("Offer with ID: " + offerId + " does not exist!");
							}
							break;
						case 6:
							break;
						case 7:
							userService.exitApplication();
							System.out.println("******************************************");
							System.out.println("==========================================");
							System.out.println("              Logging out...              ");
							System.out.println("==========================================");
							System.out.println("|         You are now signed out!        |");
							System.out.println("==========================================");
							logger.info("User logged out...");
							break;
						}
			
				} else if (userType != null && userType.equals("manager")) {
					System.out.println("==========================================");
					System.out.println("        Manager Login Successful!         ");
					System.out.println("==========================================");
					System.out.println("******************************************");
					System.out.println("==========================================");
					System.out.println("|       Welcome to Manager Portal        |");
					System.out.println("==========================================");
					System.out.println("  Options:                                ");
					System.out.println("                                          ");
					System.out.println("  [1] Add an Employee Account             "); // *** BONUS *** CHECK
					System.out.println("  [2] Terminate an Employee               "); // *** BONUS *** CHECK
					System.out.println("  [3] View All Users                      "); // CHECK
					System.out.println("  [4] Logout                              "); // CHECK
					System.out.println("==========================================");
					System.out.println("|        Please select an option:        |");
					System.out.println("==========================================");
					option = Integer.parseInt(scan.nextLine());
					System.out.println("==========================================");

					int userId;
					switch (option) {
					case 1:
						UserPojo userPojo2 = new UserPojo();
						System.out.println("Enter first name:");
						userPojo2.setUserFirstName(scan.nextLine());

						System.out.println("Enter last name:");
						userPojo2.setUserLastName(scan.nextLine());

						System.out.println("Enter password:");
						userPojo2.setUserPassword(scan.nextLine());

						userPojo2.setUserType("employee");

						userPojo2.setUserRemoved(false);

						UserPojo returnedUserPojo2 = null;
						try {
							returnedUserPojo2 = userService.register(userPojo2);
						} catch (ApplicationException e) {
							System.out.println("==========================================");
							System.out.println("      Unable to connect to database       ");
							System.out.println("         Please try again later...        ");
							System.out.println("==========================================");
							logger.error(e.getMessage());
							break;
						}
						System.out.println("==========================================");
						System.out.println("|      Account Created Successfully!     |");
						System.out.println("==========================================");
						System.out.println("Your User ID is : " + returnedUserPojo2.getUserId());
						break;
					case 2:
						System.out.println("Enter employee id to be removed:");
						userId = Integer.parseInt(scan.nextLine());
						
						try {
							userPojo = userService.getAUser(userId);
						} catch (ApplicationException e1) {
							System.out.println("==========================================");
							System.out.println("      Unable to connect to database       ");
							System.out.println("         Please try again later...        ");
							System.out.println("==========================================");
							logger.error(e1.getMessage());
							break;
						}
						if(userPojo != null) {
							System.out.println("==========================================");
							System.out.println("|  Enter employee details to be removed: |");
							System.out.println("==========================================");
							System.out.println("User ID: " + userPojo.getUserId());
							System.out.println("User First Name: " + userPojo.getUserFirstName());
							System.out.println("User Last Name: " + userPojo.getUserLastName());
							System.out.println("==========================================");
							System.out.println("Are you sure you want to remove this employee?(y/n) : ");
							String con = scan.nextLine();
							System.out.println("==========================================");
							
							if("y".equals(con)) {
								try {
									userService.deleteUser(userId);
								} catch (ApplicationException e) {
									System.out.println("==========================================");
									System.out.println("      Unable to connect to database       ");
									System.out.println("         Please try again later...        ");
									System.out.println("==========================================");
									logger.error(e.getMessage());
									break;
								}
								System.out.println("==========================================");
								System.out.println("|     Employee Removed Successfully!     |");
								System.out.println("==========================================");
							}
						} else {
							System.out.println("==========================================");
							System.out.println("Employee with ID " + userId + " does not exist!" );
							System.out.println("==========================================");
						}				
						break;
					case 3:
						List<UserPojo> allUsers;
						try {
							allUsers = userService.getAllUsers();
						} catch (ApplicationException e) {
							System.out.println("==========================================");
							System.out.println("      Unable to connect to database       ");
							System.out.println("         Please try again later...        ");
							System.out.println("==========================================");
							logger.error(e.getMessage());
							break;
						}
						
						System.out.println("==========================================");
						System.out.println("ID\tFirst Name\tLast Name\tUser Type");
						System.out.println("==========================================");
						
						allUsers.forEach((myUserPojo) -> {
							System.out.print(myUserPojo.getUserId() + "\t" + myUserPojo.getUserFirstName() + "\t\t" + myUserPojo.getUserLastName() + "\t\t" + myUserPojo.getUserType());
							System.out.println();
						});
						System.out.println();
						break;
					case 4:
						userService.exitApplication();
						System.out.println("******************************************");
						System.out.println("==========================================");
						System.out.println("              Logging out...              ");
						System.out.println("==========================================");
						System.out.println("|         You are now signed out!        |");
						System.out.println("==========================================");
						logger.info("User logged out...");
						break;
					}

				} else if (userType == null) {
					System.out.println("==========================================");
					System.out.println("               Login failed               ");
					System.out.println("             please try again...          ");
					System.out.println("==========================================");
				}
				break;
			case 3:
				userService.exitApplication();
				System.out.println("******************************************");
				System.out.println("==========================================");
				System.out.println("|       Thank you for using cBay!        |");
				System.out.println("==========================================");
				System.out.println("        Exiting the application...        ");
				System.out.println("==========================================");
				System.out.println("|  Application Terminated Successfully!  |");
				System.out.println("==========================================");
				logger.info("cBay application exited...");
				scan.close();
				System.exit(0);
				break;

			default:
				System.out.println("******************************************");
				System.out.println("==========================================");
				System.out.println("|      Please select a valid option:     |");
				System.out.println("==========================================");
				System.out.println("******************************************");
				continue;
			}

			System.out.println("******************************************");
			System.out.println("==========================================");
			System.out.println("|     Do you want to continue? (y/n):    |");
			System.out.println("==========================================");
			ch = scan.nextLine();

			if ("y".equalsIgnoreCase(ch)) {
				continue;
			} else {
				userService.exitApplication();
				System.out.println("******************************************");
				System.out.println("==========================================");
				System.out.println("|       Thank you for using cBay!        |");
				System.out.println("==========================================");
				System.out.println("        Exiting the application...        ");
				System.out.println("==========================================");
				System.out.println("|  Application Terminated Successfully!  |");
				System.out.println("==========================================");
				logger.info("cBay application exited...");
				scan.close();
				System.exit(0);
			}
		}
	}

}