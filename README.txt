---------------------------------------------------------------------------------------------------
Summary
---------------------------------------------------------------------------------------------------
ApparelDiscount program does the following.
* Loads the all types of discounts stored in the following file into memory.
	ApparelDiscount.txt
	BrandDiscount.txt
	GenderDiscount.txt
* Reads shop inventory from input file and calculates discount price and displays to user. 
* Accepts the serial numbers of purchased items from standard input.
* Adds the discounted price for each item and displays to user.

Improvement areas:
* Applying discount logic can be written using Chain of Responsibility pattern where we can have handler for applying each discount
* This project can be written in Maven and use Junit for unit testing.
* Some of the negative scenarios are not handled due time constraint.

---------------------------------------------------------------------------------------------------
Environment: Windows
---------------------------------------------------------------------------------------------------
Steps to run the program
---------------------------------------------------------------------------------------------------
java -jar ApparelDiscount.jar <inputFileName>

Sample input/output:

java -jar ApparelDiscount.jar ShopInventory.txt

UserInput [serialNum=1, brand=Arrow, apparel=Shirts, price=800.0]
UserInput [serialNum=2, brand=Vero Moda, apparel=Dresses, price=1400.0]
UserInput [serialNum=3, brand=Provogue, apparel=Footwear, price=1800.0]
UserInput [serialNum=4, brand=Wrangler, apparel=Jeans, price=2200.0]
UserInput [serialNum=5, brand=UCB, apparel=Shirts, price=1500.0]

DiscountedPrice = 640.0
DiscountedPrice = 560.0
DiscountedPrice = 900.0
DiscountedPrice = 1760.0
DiscountedPrice = 1500.0

Enter the serial number of purchased items:
1, 2
discountedPrice: 1200.0
