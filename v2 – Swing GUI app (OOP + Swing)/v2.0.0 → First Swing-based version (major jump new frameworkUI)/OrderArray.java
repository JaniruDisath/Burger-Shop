class OrderArray {
	private String orderIDString;
	private int burgurNumber;
	private int orderStatus;
	private long oprofileID;

	OrderArray() {
		orderIDString = "";
		burgurNumber = 0;
		orderStatus = 0;
		oprofileID = 0;
	}

	OrderArray(String orderIDString, int burgurNumber, int orderStatus, long oprofileID) {
		this.orderIDString = orderIDString;
		this.burgurNumber = burgurNumber;
		this.orderStatus = orderStatus;
		this.oprofileID = oprofileID;
	}

	// Getters
	public String getOrderIDString() { 
		return orderIDString; 
		}
	public int getBurgurNumber() { 
		return burgurNumber; 
		}
	public int getOrderStatus() { 
		return orderStatus; 
		}
	public long getOprofileID() { 
		return oprofileID; 
		}

	// Setters
	public void setOrderIDString(String orderIDString) { 
		this.orderIDString = orderIDString; 
		}
	public void setBurgurNumber(int burgurNumber) { 
		this.burgurNumber = burgurNumber; 
		}
	public void setOrderStatus(int orderStatus) { 
		this.orderStatus = orderStatus; 
		}
	public void setOprofileID(long oprofileID) { 
		this.oprofileID = oprofileID; 
		}
}
