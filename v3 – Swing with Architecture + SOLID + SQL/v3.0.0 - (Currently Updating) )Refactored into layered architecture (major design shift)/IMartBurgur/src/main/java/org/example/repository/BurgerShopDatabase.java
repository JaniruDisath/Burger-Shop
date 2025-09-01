package org.example.repository;

import org.example.model.Order;
import org.example.model.Customer;

public class BurgerShopDatabase{
	//Object Arrays
	private Customer[] customerObjectArray = new Customer[]{};
	private Order[] orderObjectArray = new Order[]{};

		
	//Array Lengths
	public int getLatestProfile() {
		return customerObjectArray.length;
	}
	public int getLatestOrder() {
		return orderObjectArray.length;
	}
	//Extending Profile Arrays
	public void extendProfileArrays() {
		int size = customerObjectArray.length;
		Customer[] tempprofilexes = new Customer[size + 1];
		for (int i = 0; i < size; i++) {
			tempprofilexes[i] = customerObjectArray[i];
		}
		tempprofilexes[size] = new Customer();
		customerObjectArray = tempprofilexes;
	}
	public void extendOrderArrays() {
		int size = orderObjectArray.length;
		Order[] temporderx = new Order[size + 1];
		for (int i = 0; i < size; i++) {
			temporderx[i] = orderObjectArray[i];  
		}
		temporderx[size] = new Order();
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
		long longSOD = customerObjectArray[i].getCustID();
		return longSOD;
	}
	public void setProfCustIDSOD(long longSOD, int i){
		customerObjectArray[i].setCustID(longSOD);
	}
	//Cust Name - Profile Array
	public String getCustNameSOD(int profid){
		String intSOD = customerObjectArray[profid].getCustName();
		return intSOD;
	}
	public String setCustNameSOD(String intSOD,int profid){
		customerObjectArray[profid].setCustName(intSOD);
		return intSOD;
	}
	//Cust Total - Profile Array
	public double getCustTotalSOD(int profid){
		double intSOD = customerObjectArray[profid].getCustTotal();
		return intSOD;
	}
	public void setCustTotalSOD(double intSOD,int profid){
		customerObjectArray[profid].setCustTotal(intSOD);
	}

	
}

