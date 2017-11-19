package com.dinakara;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ApparelDiscount {
	public static final String RESOURCES_PATH = "/resources/";
	public static HashMap<String, String> apparelGenderMap = new HashMap<>();
	public static HashMap<String, Integer> genderDiscountMap = new HashMap<>();
	public static HashMap<String, Integer> apparelDiscountMap = new HashMap<>();
	public static HashMap<String, Integer> brandDiscountMap = new HashMap<>();
	
	static {
	    initalizeMaps();
	}
	
	public static void main(String[] args) {
		String inputFile = "";
		if (args.length > 0) {
			inputFile = args[0];
		} else {
			logInfo("Usage: java -jar ApparelDiscount.jar <inputFileName>");
			exit(-1);
		}
		
		try {
			List<String> shopInventoryList = readUserInput(inputFile);
			ShopInventory[] shopInventory = processUserInput(shopInventoryList.toArray(new String[0]));
			try (Scanner scanner = new Scanner(System.in).useDelimiter("\\r\\n")) {
				System.out.println("Enter the serial number of purchased items:");
				String purchasedItems = scanner.next();
				String[] purchasedItemTokens = purchasedItems.split(",");
				purchasedItemTokens = Arrays.stream(purchasedItemTokens).map(string -> string.trim())
						.toArray(String[]::new);
				float discountedPrice = 0;
				for (ShopInventory userInput : shopInventory) {

					if (purchasedItems.contains(String.valueOf(userInput.getSerialNum()))) {
						discountedPrice += userInput.getDiscountedPrice();
					}

				}
				System.out.println("discountedPrice: " + discountedPrice);
			}
		} catch (FileNotFoundException e) {
			logWarn("FileNotFoundException in main() ", e);
		} catch (Exception e) {
			logWarn("Exception in main() ", e);
		}
	}



	private static List<String> readUserInput(String inputFile) throws FileNotFoundException {
		Scanner shopInventoryScanner = getScannerForInputFile(inputFile);
		List<String> shopInventoryList = new ArrayList<String>();
		shopInventoryScanner.next();
		while (shopInventoryScanner.hasNextLine()) {
			shopInventoryList.add(shopInventoryScanner.next());
		}
		return shopInventoryList;
	}


	
	private static ShopInventory[] processUserInput(String[] items) {
		ShopInventory[] userInputs = Arrays.stream(items).map(item -> item.split(","))
			.map(item -> new ShopInventory(item))
			.toArray(ShopInventory[]::new);
		
		Arrays.stream(userInputs).forEach(userInput -> logInfo(userInput.toString()));
		logInfo("");
		float finalDiscount = 0;
		float genderDiscount = 0;
		float brandDiscount = 0;
		for (ShopInventory userInput: userInputs) {
			// Note:- The following logic can be implemented using Chain of Responsibility pattern where we
			// can have handler for each discount calculation
			finalDiscount = apparelDiscountMap.get(userInput.getApparel());
			genderDiscount = genderDiscountMap.get(apparelGenderMap.get(userInput.getApparel()));
			finalDiscount = (genderDiscount > finalDiscount) ? genderDiscount : finalDiscount;
			brandDiscount = brandDiscountMap.get(userInput.getBrand());
			finalDiscount = (brandDiscount > finalDiscount) ? brandDiscount : finalDiscount;
			if ((100 - finalDiscount) * userInput.getPrice() > 0) {
				float discountedPrice = ((100 - finalDiscount) * userInput.getPrice()) / 100;
				logInfo("DiscountedPrice = " + discountedPrice);
				userInput.setDiscountedPrice(discountedPrice);
			}
		}
		logInfo("");
		
		return userInputs;
	}
	
	private static void initalizeMaps() {
		try (Scanner apparelScanner = getScanner("ApparelDiscount.txt");
				Scanner genderScanner = getScanner("GenderDiscount.txt");
				Scanner brandScanner = getScanner("BrandDiscount.txt");) {

			apparelScanner.next();
			while (apparelScanner.hasNextLine()) {
				String apparel = apparelScanner.next();
				String[] apparelTokens = apparel.split(",");
				apparelGenderMap.put(apparelTokens[0].trim(), apparelTokens[2].trim());
				apparelDiscountMap.put(apparelTokens[0].trim(), Integer.parseInt(apparelTokens[1].trim()));
			}

			genderScanner.next();
			while (genderScanner.hasNextLine()) {
				String gender = genderScanner.next();
				String[] genderTokens = gender.split(",");
				genderDiscountMap.put(genderTokens[0].trim(), Integer.parseInt(genderTokens[1].trim()));
			}

			brandScanner.next();
			while (brandScanner.hasNextLine()) {
				String gender = brandScanner.next();
				String[] genderTokens = gender.split(",");
				brandDiscountMap.put(genderTokens[0].trim(), Integer.parseInt(genderTokens[1].trim()));
			}

		} catch (Exception e) {
			logWarn("Failed to load initial data", e);
			exit(-1);
		}
	}
	
	private static void logWarn(String errMsg, Exception e) {
		System.err.println(errMsg + ": "+ e.getMessage());
	}
	
	private static void logInfo(String msg) {
		System.out.println(msg);
	}
	
	private static void exit(int exitCode) {
		System.exit(exitCode);
	}
	
	private static Scanner getScanner(String fileName) throws FileNotFoundException {
		return new Scanner(ApparelDiscount.class.getResourceAsStream(RESOURCES_PATH + fileName)).useDelimiter("\\r\\n");
	}
	
	private static Scanner getScannerForInputFile(String inputFile) throws FileNotFoundException {
		return new Scanner(new FileInputStream("./" + inputFile)).useDelimiter("\\r\\n");
	}

}

