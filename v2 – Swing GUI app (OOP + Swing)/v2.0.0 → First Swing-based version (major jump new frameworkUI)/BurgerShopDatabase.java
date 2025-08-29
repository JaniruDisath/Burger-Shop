class BurgerShopDatabase{
	//Object Arrays
	private ProfileArrays[] profileObjectArray = new ProfileArrays[]{};
	private OrderArray[] orderObjectArray = new OrderArray[]{};

		
	//Array Lengths
	public int getLatestProfile() {
		return profileObjectArray.length;
	}
	public int getLatestOrder() {
		return orderObjectArray.length;
	}
	//Extending Profile Arrays
	public void extendProfileArrays() {
		int size = profileObjectArray.length;
		ProfileArrays[] tempprofilex = new ProfileArrays[size + 1];
		for (int i = 0; i < size; i++) {
			tempprofilex[i] = profileObjectArray[i];  
		}
		tempprofilex[size] = new ProfileArrays(); 
		profileObjectArray = tempprofilex;
	}
	public void extendOrderArrays() {
		int size = orderObjectArray.length;
		OrderArray[] temporderx = new OrderArray[size + 1];
		for (int i = 0; i < size; i++) {
			temporderx[i] = orderObjectArray[i];  
		}
		temporderx[size] = new OrderArray(); 
		orderObjectArray = temporderx;
	} 
	//Order ID - Order Array
	public String getOrderIDStringSOD(int i){
		String stringSOD = orderObjectArray[i].getOrderIDString();
		return stringSOD;
	}
	public void setOrderIDStringSOD(String stringSOD, int i){
		orderObjectArray[i].setOrderIDString(stringSOD);
	}
	//Burger Number - Order Array
	public int getBurgerNumberSOD(int id){
		int intSOD = orderObjectArray[id].getBurgurNumber();
		return intSOD;
	}
	public void setBurgerNumberSOD(int intSOD,int id){
		 orderObjectArray[id].setBurgurNumber(intSOD);
	}
	//Order Status - Order Array
	public int getOrderStatusSOD(int id){
		int intSOD = orderObjectArray[id].getOrderStatus();
		return intSOD;
	}
	public void setOrderStatusSOD(int intSOD ,int id){
		orderObjectArray[id].setOrderStatus(intSOD);
	}
	//Order Cust ID - Order Array
	public long getOrderCustIDSOD(int id){
		long longSOD = orderObjectArray[id].getOprofileID();
		return longSOD;
	}
	public void setOrderCustIDSOD(long longSOD,int id){
		orderObjectArray[id].setOprofileID(longSOD);
	}
	//Cust ID - Profile Array 
	public long getProfCustIDSOD(int i){
		long longSOD = profileObjectArray[i].getCustID();
		return longSOD;
	}
	public void setProfCustIDSOD(long longSOD, int i){
		profileObjectArray[i].setCustID(longSOD);
	}
	//Cust Name - Profile Array
	public String getCustNameSOD(int profid){
		String intSOD = profileObjectArray[profid].getCustName();
		return intSOD;
	}
	public String setCustNameSOD(String intSOD,int profid){
		profileObjectArray[profid].setCustName(intSOD);
		return intSOD;
	}
	//Cust Total - Profile Array
	public double getCustTotalSOD(int profid){
		double intSOD = profileObjectArray[profid].getCustTotal();
		return intSOD;
	}
	public void setCustTotalSOD(double intSOD,int profid){
		profileObjectArray[profid].setCustTotal(intSOD);
	}

	
}
class UpdateDatabase {
	private BurgerShopDatabase database;

	public UpdateDatabase(BurgerShopDatabase db) {
		this.database = db;
	}
	public void placeNewOrder(long CustID, String CustName, int BurgerQTY, String orderNumberString) {
		int gotIt = 0, profileID = 0;
		double totalBurgerPrice = BurgerQTY * FoodMenu.BURGERPRICE;

		for (int i = 0; i < database.getLatestProfile(); i++) {
			if (database.getProfCustIDSOD(i) == CustID) {
				gotIt = 1;
				profileID = i;
				break;
			}
		}
		database.extendOrderArrays();
		int cOI = database.getLatestOrder() - 1;
		database.setBurgerNumberSOD(BurgerQTY, cOI);
		database.setOrderIDStringSOD(orderNumberString, cOI);
		database.setOrderStatusSOD(OrderStatus.PREPARING, cOI);
		database.setOrderCustIDSOD(CustID, cOI);
		switch (gotIt) {
			case 0:
				database.extendProfileArrays();
				int cPI = database.getLatestProfile() - 1;
				database.setProfCustIDSOD(CustID, cPI);
				database.setCustNameSOD(CustName, cPI);
				database.setCustTotalSOD(totalBurgerPrice, cPI);
				break;
			case 1:
				database.setCustTotalSOD(totalBurgerPrice, profileID);
				break;
		}
		System.out.println("New Order Added : " + CustID + " : " + CustName + " : " + BurgerQTY + " : " + orderNumberString);
	}
}
class OurDataBase {
	//The Common Object - One Database to Rule Them All ;)
		public static final BurgerShopDatabase SHARED_DB = new BurgerShopDatabase();
		public static final UpdateDatabase SHARED_UPDATE_DB = new UpdateDatabase(SHARED_DB);
		public static final MainWindow MAIN_WINDOW = new MainWindow();
		public static SearchOrderDetails SEARCH_ORDER = new SearchOrderDetails();
}
