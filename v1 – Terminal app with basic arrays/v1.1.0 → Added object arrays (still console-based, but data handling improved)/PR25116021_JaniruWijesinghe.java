import java.util.*;
class ProfileArraysX{
	//Profile
	int custID;				//Updating and In Use
	double custTotal;		//Updating and In Use
	String custName;		//Updating and In Use
	
	ProfileArraysX(){
		custID=0;
		custTotal=0.0;
		custName="";
	}
}
class OrderArray{
	//OrderProfile
	String orderIDString;	//Updating and In Use 		
	int burgurNumber ;		//Updating and In Use
	int orderStatus ;		//Updating and In Use
	int oprofileID ;		//Updating and In Use
	
	OrderArray(){
		orderIDString="";
		burgurNumber=0;
		orderStatus=0;
		oprofileID=0;
		}
}
class PR25116021_JaniruWijesinghe{
	final static double BURGERPRICE=500; 
	static ProfileArraysX[] profilex = new ProfileArraysX[]{};
	static OrderArray[] orderx = new OrderArray[]{};
	//Latest Profile Added and Latest Order added
	public static int LatestProfile =profilex.length;
	public static int LatestOrder=orderx.length; 
	//Scanner
	public static Scanner input = new Scanner(System.in);
	//Order status
	public static final int PREPARING=0; 
	public static final int DELIVERED=1; 
	public static final int CANCEL=2;
	


	public final static void clear() {  
     try { 
           final String os = System.getProperty("os.name");  
           if (os.contains("Windows")) { 
               new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
           } else { 
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
              } 
       } catch (final Exception e) { 
            e.printStackTrace(); 
        } 
	}
	public static void exit(){ 
		clear(); 
		System.out.println("\n\t\tYou left the program...\n"); 
		System.exit(0); 
	} 	
	public static int invalidinput(){
		int invalid=0,loop=0;
		do{
			System.out.println("Invalid input...\n");
			System.out.print("Do you want to input number again (Y/N) -> ");
			String i = input.nextLine().trim().toUpperCase();
			switch(i){
				case "Y":
				invalid =0;
				loop =1;
				break;
				case "N":
				invalid =1;
				loop =1;
				break;
				default:
				System.out.print("Please Enter Either Y or N : ");
				loop=0;
			}
		}while(loop==0);
		return invalid;
	}
	public static int again(){
		int invalid=0,loop=1;
		do{
		String i = input.nextLine().trim().toUpperCase();
		switch(i){
				case "Y":
				invalid =0;
				loop =1;
				break;
				case "N":
				invalid =1;
				loop =1;
				break;
				default:
				System.out.print("Please Enter Either Y or N : ");
				loop=0;
			}
		}while(loop==0);
		return invalid;
	}
	public static void box(String box){
		int width=65;
		int paddingLeft = (width - box.length()) / 2;
		int paddingRight = width - box.length() - paddingLeft;
		System.out.print("   -");
		for(int i=0; i<(paddingLeft+paddingRight+box.length());i++){
			System.out.print("-");	
		}
		System.out.println("-"); 
		System.out.printf("%-2s |%" + paddingLeft + "s%s%" + paddingRight + "s|\n","", "", box, "");
		System.out.print("   -");
		for(int i=0; i<(paddingLeft+paddingRight+box.length());i++){
			System.out.print("-");	
		}
		System.out.println("-\n\n"); 
	}
	public static int head(){
		clear();
		int x;
		String title ="iHungry Burgur";
		box(title);
		System.out.printf(" %-2s %-22s   %-5s  %-10s\n", "", "  [1] Place Order ", "", "[2]Search Best Customer");
		System.out.printf(" %-2s %-22s   %-5s  %-10s\n", "", "  [3] Search Order ", "", "[4]Search Customer");
		System.out.printf(" %-2s %-22s   %-5s  %-10s\n", "", "  [5] View Orders ", "", "[6]Update Order Details");
		System.out.printf(" %-2s %-22s   %-5s  %-10s\n", "", "  [7] Exit ", "", "");
		System.out.print("\n\nEnter an Option to continue -> ");
		x =input.nextInt();
		return x;
	}
	public static void printxy(int dashes,String x){
		for(int i=0;i<dashes;i++){
			System.out.print(x);
		}
	}
	public static void printextra(int amount, int selection){
		switch(selection){
			case 1:
			printxy(amount,"-");
			System.out.print("\n");
			break;
			case 2:
			printxy(amount,"\n");
			break;
			case 3:
			printxy(amount," ");
			break;
			default:
			System.out.println("Wrong selection");
		}
	}
	public static void extendCustomerArrays() {
		int size = profilex.length;
		ProfileArraysX[] tempprofilex = new ProfileArraysX[size + 1];
		for (int i = 0; i < size; i++) {
			tempprofilex[i] = profilex[i];  
		}
		tempprofilex[size] = new ProfileArraysX(); 
		profilex = tempprofilex;
	}
	public static void extendOrderArrays() {
		int size = orderx.length;
		OrderArray[] temporderx = new OrderArray[size + 1];
		for (int i = 0; i < size; i++) {
			temporderx[i] = orderx[i];  
		}
		temporderx[size] = new OrderArray(); 
		orderx = temporderx;
	}

	public static String generateOrderId() {
		return String.format("B%04d", LatestOrder + 1);
	}

	public static void placeorder(){
		int loop=0;
		do{
			clear();
			String username = "";
			int burgurno=0;
			box("Place Order");
			String zz ="";
			if(LatestOrder>99){
				zz+="0";
			}else if(LatestOrder>9){
				zz+="00";
			}else if(LatestOrder>=0){
				zz+="000";
			}
			String ordernox = "B"+zz+(LatestOrder+1);
			System.out.println("ORDER ID - "+ordernox+"\n=================\n\n");
			System.out.print("Enter Customer ID (phone no.) : "); 
			int phoneno =input.nextInt(),gotit=0,prof=0;
			phoneno=custidcheck(phoneno);
			input.nextLine();
			for(int i=0;i<LatestProfile;i++){
				if (profilex.length==0){
				}else if (profilex[i].custID==phoneno){
					gotit=1;
					prof=i;
				}
			}
			if(gotit==1){
				System.out.print("\nCustomer Name : "+profilex[prof].custName);
				System.out.print("\n");
				
			}else {
				System.out.print("\nCustomer Name : ");
				username= input.nextLine();
				username=custnamecheck(username, "Customer Name");
			}
			System.out.print("\nEnter Burgue Quantity : ");
			burgurno= input.nextInt();
			burgurno=burgurcount(burgurno);
			input.nextLine();
			double totalburg = BURGERPRICE*burgurno;
			printextra(1,2);
			printextra(90,1);
			System.out.println("\tTotal Value - "+totalburg);
			printextra(90,1);
			System.out.print("\nAre You Confirm the Order (Y/N) : ");
			int confirm = again();
			switch(confirm){
				case 0:
					extendOrderArrays();
					orderx[LatestOrder].burgurNumber=burgurno;		//Burgur number added 		: OrderProfile
					orderx[LatestOrder].orderIDString=ordernox;	//Burgur ID (added string)	: OrderProfile
					orderx[LatestOrder].orderStatus=PREPARING;		//Burgur status added		: OrderProfile
					orderx[LatestOrder].oprofileID=phoneno;		//Linkage with profile and order profile 
					switch(gotit){
						case 0:
							extendCustomerArrays();
							profilex[LatestProfile].custID=phoneno;		//User ID added			: User Profile
							profilex[LatestProfile].custName =username;	//User Name added 		: User Profile
							profilex[LatestProfile].custTotal+=totalburg;//User Total increased	: User Profile
							profilex[LatestProfile].custTotal+=totalburg;//User Total increased	: User Profile
							LatestProfile++;
						break;
						case 1 :
							profilex[LatestProfile].custTotal+=totalburg;//User Total increased	: User Profile
						break;
					}
					LatestOrder++;
				break;
			}
			System.out.print("\nDo you want to place another Order (Y/N) : ");
			loop = again();
		}while(loop==0);
	}
	public static void bestcustomer(){
		clear();
		box("Best Customer");
		printextra(3,3);
		printextra(68,1);
		System.out.printf(" %-1s | %-15s   %-1s  %-20s %-1s  %-20s|\n", "", "Customer ID", "", "Name","","Total");
		printextra(3,3);
		printextra(68,1);
		int n = LatestProfile;
		double[] values = new double[n];     // for arranged custTotal values
        int[] positions = new int[n];  // to save profile id in decending order
        for (int i = 0; i < n; i++) {
            values[i] = profilex[i].custTotal;
            positions[i] = i; 
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (values[i] < values[j]) {
                    double temp = values[i];
                    values[i] = values[j];
                    values[j] = temp;
                    int tempPos = positions[i];
                    positions[i] = positions[j];
                    positions[j] = tempPos;
                }
            }
        }
        for (int i = 0; i < n; i++) {
			int r = positions[i];
            System.out.printf(" %-1s | %-15s   %-1s  %-20s %-1s  %-20s|\n", "", "0"+profilex[r].custID, "", profilex[r].custName,"",profilex[r].custTotal);
            printextra(3,3);
			printextra(68,1);
        }
        System.out.print("\n\tDo you want to Go Back to Main Menu ? (Y/N): ");
        input.nextLine();
        int action = again();
        if (action==1){
			exit();
		}		
	}
	public static void searchorder(){
		int invalid=0;
		input.nextLine();
		do{
			clear();
			box("SEARCH ORDER");
			System.out.print("Enter Order ID : ");
			String orderxid =input.nextLine().trim().toUpperCase();
			int gotit =0,id=0;
			for (int i = 0; i < LatestOrder; i++) {
				if (orderx[i].orderIDString.equals(orderxid)) {
					gotit=1;
					id=i;
					break;
				}
			}
			if (gotit == 0) {
				System.out.print("\n\nInvalid Input. Do you Want to enter again ? (Y/N) :");
				invalid =again();
			}	
			if(gotit==1){
				String y = "";
				switch (orderx[id].orderStatus){
					case 0:
					y="PREPARING";
					break;
					case 1:
					y="DELIVERED";
					break;		
					case 2:
					y="CANCEL";		
					break;
				}
				int profid =0;
				for(int i=0;i<LatestProfile;i++){
					if(profilex[i].custID==orderx[id].oprofileID){
						profid=i;
					}
				}
				printextra(3,2);
				printextra(3,3);
				printextra(92,1);
				System.out.printf(" %-1s | %-10s   %-15s  %-15s %-10s  %-15s %-15s|\n", "", "Order ID", "Customer ID", "Name","Quantity","Order Value","Order Statue");
				printextra(3,3);
				printextra(92,1);
				System.out.printf(" %-1s | %-10s   %-15s  %-15s %-10s  %-15s %-15s|\n", "", orderx[id].orderIDString,"0"+profilex[profid].custID, profilex[profid].custName,orderx[id].burgurNumber,(BURGERPRICE*orderx[id].burgurNumber),y);
				printextra(3,3);
				printextra(92,1);
				System.out.print("\n\n Do you want to search another order detail ? (Y/N) :");
				invalid=again();
			}
		}while(invalid==0);
	}
	public static void searchcustomer(){
		int invalid=0;
		input.nextLine();
		do{
			clear();
			box("SEARCH CUSTOMER DETAILS");
			System.out.print("Enter Customer ID : ");
			int orderxid =input.nextInt();
			orderxid=custidcheck(orderxid);
			input.nextLine();
			int n = LatestOrder,gh=0,prof=0,gotit,nn;
			int[] values = new int[n];     // for selected order numbers
			for (int i = 0; i <n; i++) {
				if(orderxid==orderx[i].oprofileID){
					values[gh] = i;
					gh++;
				}
			}	
			for(int i=0;i<LatestProfile;i++){
				if (profilex[i].custID==orderxid){
					gotit=1;
					prof=i;
				}
			}
			System.out.println("\n\n  Customer ID found");
			printextra(2,3);
			printextra(62,1);
			System.out.println("\n   Customer ID - "+"0"+orderxid);
			System.out.println("   Name        - "+profilex[prof].custName);
			System.out.println("\n\nCustomer Order List");
			System.out.println("=====================\n");
			printextra(3,3);
			printextra(62,1);
			System.out.printf(" %-1s | %-10s   %-1s  %-20s %-1s  %-20s|\n", "", "Order ID", "", "Order Quantity","","Total Value");
			printextra(3,3);
			printextra(62,1);
			for(int i =0;i<gh;i++){
				nn=values[i];
				System.out.printf(" %-1s | %-10s   %-1s  %-20s %-1s  %-20s|\n", "",orderx[nn].orderIDString, "", orderx[nn].burgurNumber,"",(BURGERPRICE* orderx[nn].burgurNumber));
			}		
			printextra(3,3);
			printextra(62,1);
			System.out.print("\nDo you want to search another customer details ? (Y/N) :");
			invalid=again();
		}while(invalid==0);
	}
	public static void vieworders(){
		clear();
		box("VIEW ORDERS");
		System.out.println("   [1] Delivered Order");
		System.out.println("   [2] Preparing Order");
		System.out.println("   [3] Canceled  Order");
		System.out.print("\n\n Enter an Option to continue ->");
		int selectx =input.nextInt();
		input.nextLine();
		switch (selectx){
			case 1:
			viewordersplus("DELIVERED ORDERS",1);
			break;
			case 2:
			viewordersplus("PREPARING ORDERS",0);
			break;
			case 3:
			viewordersplus("CANCELED ORDERS",2);
			break;
		}
	} 
	public static void viewordersplus (String boxtext, int ordertypex ){
		int loop=1;
		do{
			clear();
			box(boxtext);
			int n = LatestOrder,gh=0,prof=0,gotit,nn,np;
			int[] values = new int[n];     // for selected order numbers
			int[] valuesp = new int[n];    // for selected profile numbers
			for (int i = 0; i<n; i++) {
				if(ordertypex==orderx[i].orderStatus){
					values[gh] = i;
					gh++;
				}
			}	
			for(int i=0;i<n;i++){
				gotit=values[i];
				for(int j=0;j<LatestProfile;j++){
					if (orderx[gotit].oprofileID==profilex[j].custID){
						valuesp[i]=j;
					}
				}
			}
			printextra(2,2);
			printextra(3,3);
			printextra(87,1);
			System.out.printf(" %-1s | %-10s %-15s %-1s  %-20s %-12s %-20s|\n", "", "Order ID", "Customer ID","","Name","Quantity","Order Value");
			printextra(3,3);
			printextra(87,1);
			if(gh>0){
				for(int i =0;i<gh;i++){
					nn=values[i];
					np=valuesp[i];
					System.out.printf(" %-1s | %-10s %-15s %-1s  %-20s %-12s %-20s|\n", "",orderx[nn].orderIDString,"0"+orderx[nn].oprofileID,"",profilex[np].custName,orderx[nn].burgurNumber,(BURGERPRICE*orderx[nn].burgurNumber));
								printextra(3,3);
								printextra(87,1);
				}
			}else if(gh==0){
				noordersfounf(boxtext);
			}else{
				noordersfounf(boxtext);
			}
			System.out.print("\nDo you want to go to HOMEPAGE (Y/N) : ");
			loop = again();
			if(loop==1){
				int ut;
				System.out.print("\nYou are about to exit the Program. Please Confirm (Y/N) : ");
				ut=again();
				if(ut==0){
					exit();
				}else{
					loop=0;
				}
			}
		}while(loop==1);	
	}
	public static void noordersfounf(String boxtext){
		System.out.printf(" %-1s | %-10s %-10s %-4s  %-40s %-2s %-12s|\n", "","", "","","","","");
		System.out.printf(" %-1s | %-10s %-10s %-4s  %-40s %-2s %-12s|\n", "","", "","","No "+boxtext+" Found","","");
		System.out.printf(" %-1s | %-10s %-10s %-4s  %-40s %-2s %-12s|\n", "","", "","","","","");
		printextra(3,3);
		printextra(87,1);
	}
	public static void updateorder(){
		int loop=0,pit=2;
		input.nextLine();
		boolean bool;
		do{
			clear();
			box("UPDATE ORDER");
			System.out.print("\n\nEnter ORDER ID : ");
			String orderxidstring =input.nextLine().trim().toUpperCase();
			int gotit =0,orderxid=0;
			for (int i = 0; i < LatestOrder; i++) {
				if (orderx[i].orderIDString.equals(orderxidstring)) {
					gotit=1;
					orderxid=i;
					break;
				}
			}
			while (gotit==0) {
				System.out.print("\n\nInvalid Value. Enter a valid value : ");
				orderxidstring =input.nextLine().trim().toUpperCase();
				for (int i = 0; i < LatestOrder; i++) {
					if (orderx[i].orderIDString.equals(orderxidstring)) {
						gotit=1;
						orderxid=i;
						break;
					}
				}
			}
			System.out.println("\n\n  Order ID found");
			printextra(2,3);
			printextra(62,1);
			int n = LatestOrder,gh=0,prof=0,np=0;
			if(0==orderx[orderxid].orderStatus){
				loop=0;
			}else if (1==orderx[orderxid].orderStatus){
				System.out.println("\n   ->This Order is alredy DELIVERED. You can not update this ORDER.");
				System.out.print("\n\nDo you want to update another ORDER DETAIL ? (Y/N) : ");
				pit=again();
			}else if (2==orderx[orderxid].orderStatus){
				System.out.println("\n   ->This Order is CANCELED. You can not update this ORDER.");
				System.out.print("\n\nDo you want to update another ORDER DETAIL ? (Y/N) : ");
				pit=again();
			}
			for(int i=0;i<LatestProfile;i++){
				if(profilex[i].custID==orderx[orderxid].oprofileID){
					np=i;
				}
			}
			if(loop==0 & pit==2){
				System.out.printf("\n\n %-2s %-15s   %-5s  %-15s\n", "", "Order ID", ""," : "+ orderx[orderxid].orderIDString);
				System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Customer ID", ""," : "+ "0"+orderx[orderxid].oprofileID);
				System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Name", ""," : "+ profilex[np].custName);	
				System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Quantity", ""," : "+ orderx[orderxid].burgurNumber);
				System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Order value", ""," : "+ (BURGERPRICE*orderx[orderxid].burgurNumber));
				System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Order Status", ""," : "+"PREPARING");
				System.out.println("\n\nWhat do you want to Update? ");
				System.out.printf("\n %-5s %-15s  \n", "", "[01] Quantity");
				System.out.printf(" %-5s %-15s  \n", "", "[02] Status");
				System.out.print("\n\nEnter your Option : ");
				int updatestat =input.nextInt();
				switch (updatestat){
					case 1 :
					clear();
					{
					System.out.println("\n\nQuantity Update");
					System.out.println("=====================");
					System.out.println("\n---------------------------------------------------------\n");
					System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Order ID", ""," : "+ orderx[orderxid].orderIDString);
					System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Customer ID", ""," : "+ "0"+orderx[orderxid].oprofileID);
					System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Name", ""," : "+ profilex[np].custName);
					System.out.printf(" %-2s %-19s   %-1s  %-15s\n", "", "Current Quantity", ""," : "+ orderx[orderxid].burgurNumber);	
					System.out.println("\n---------------------------------------------------------\n");
					System.out.print("\n\nEnter your Quantity update Value : ");
					int newupdate = input.nextInt();
					do{
						if (newupdate<0){
							System.out.print("\n\nInvalid Value. Enter a valid value : ");
							newupdate = input.nextInt();
						}
					}while(newupdate<0);
					orderx[orderxid].burgurNumber=newupdate;
					System.out.printf("\n\n%-5s %-15s \n", "", "-------------------------------------------");
					System.out.printf("%-5s %-15s  \n", "", "| Updated the Order Quantity Successfully |");
					System.out.printf("%-5s %-15s  \n", "", "-------------------------------------------\n\n");
					System.out.printf(" %-2s %-25s   %-5s  %-15s\n", "", "New Order Quantity", ""," : "+ orderx[orderxid].burgurNumber);
					System.out.printf(" %-2s %-25s   %-5s  %-15s\n", "", "Order value", ""," : "+ (BURGERPRICE*orderx[orderxid].burgurNumber));
					input.nextLine();
					System.out.print("\n\nDo you want to update another order DETAIL (Y/N) :");
					loop=again();
					}
					break;
					case 2 :
					clear();{
					System.out.println("\n\nStatus Update");
					System.out.println("=====================");
					System.out.println("\n---------------------------------------------------------\n");
					System.out.printf("\n %-2s %-15s   %-5s  %-15s\n", "", "Order ID", ""," : "+ orderx[orderxid].orderIDString);
					System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Customer ID", ""," : "+ "0"+orderx[orderxid].oprofileID);
					System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Name", ""," : "+ profilex[np].custName);	
					System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Order Status", ""," : "+"PREPARING");
					System.out.println("\n---------------------------------------------------------\n");
					System.out.printf(" %-2s %-15s\n", "", "[0]PREPARING");	
					System.out.printf(" %-2s %-15s\n", "", "[1]DELIVERED");	
					System.out.printf(" %-2s %-15s\n", "", "[2]CANCEL");	
					System.out.print("\n\n Enter New Order status (0/1/2) : ");
					int newupdate = input.nextInt();
					do{
						if (newupdate<0 || newupdate>3){
							System.out.print("\n\nInvalid Value. Enter a valid value : ");
							newupdate = input.nextInt();
						}
					}while(newupdate<0 || newupdate>3);
					orderx[orderxid].orderStatus=newupdate;
					System.out.printf("\n\n%-5s %-15s  \n", "", "-----------------------------------------");
					System.out.printf("%-5s %-15s  \n", "", "| Updated the Order Status Successfully |");
					System.out.printf("%-5s %-15s  \n", "", "-----------------------------------------\n\n");
					String stats="";
					switch (orderx[orderxid].orderStatus){
						case 0:
							stats="PREPARING";
						break;
						case 1:
							stats="DELIVERED";
						break;
						case 2:
							stats="CANCEL";
						break;
					}
					System.out.printf(" %-2s %-25s   %-5s  %-15s\n", "", "New Order status", ""," : "+ stats);
					input.nextLine();
					System.out.print("\n\nDo you want to update another order DETAIL (Y/N) :");
					loop=again();
					}
					break;
					
				}
			}
			if(pit==0){
				pit=2;
			}
		}while (loop==0 & pit==2);
	}
	public static int custidcheck(int id){
		String ids = Integer.toString(id);
		while (id <= 0 || 9!=ids.length()){
			ids = Integer.toString(id);
			if(id <= 0){
				printextra(1,2);
				printextra(90,1);
				System.out.print("\nInvalid Value. Enter a valid Phone Number : ");
				id = input.nextInt();
				printextra(1,2);
				printextra(90,1);
			}else if(9!=ids.length()){
				printextra(1,2);
				printextra(90,1);
				System.out.println("\nA phone number Must have 10 digits");
				System.out.print("\nEnter a valid Phone Number : ");
				id = input.nextInt();
				printextra(1,2);
				printextra(90,1);
			}
		}	
		return id;
	}
	public static String custnamecheck(String id, String something){
		while ( 0==id.length()){
			if( 0==id.length()){
				printextra(1,2);
				printextra(90,1);
				System.out.println("\nYou can't leave a space here");
				System.out.print("\nEnter a "+something+" : ");
				id = input.nextLine();
				printextra(1,2);
				printextra(90,1);
			}
		}	
		return id;
	}
	public static int burgurcount(int id){
		while (id<0){
			if( id<0){
				printextra(1,2);
				printextra(90,1);
				System.out.println("\nInvalid Number");
				System.out.print("\nEnter a a Valid Amount :");
				id = input.nextInt();
				printextra(1,2);
				printextra(90,1);
			}
		}	
		return id;
	}
	public static void main(String args[]){
		int exit=0;

		do{
			int menu = head();
			switch (menu){
				case 1:
					placeorder();
				break;
				case 2:
					bestcustomer();
				break;
				case 3:
					 searchorder();
				break;
				case 4:
					searchcustomer();
				break;
				case 5:
					vieworders();
				break;
				case 6:
					updateorder();
				break;
				case 7:
					exit();
				break;
				
			}
		}while(exit==0);
	}
}

//I Might have finished the whole coursework. I forogot that i only needed to do certain parts. Once i went to submit i remembered.
