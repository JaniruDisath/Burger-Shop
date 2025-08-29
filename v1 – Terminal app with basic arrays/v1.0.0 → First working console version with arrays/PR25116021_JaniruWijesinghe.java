import java.util.*;
class PR25116021_JaniruWijesinghe{
	final static double BURGERPRICE=500; 
	//Profile
	public static int[] custID =new int[]{715684260};				//Updating and In Use
	public static double[] custTotal =new double[]{2500};			//Updating and In Use
	public static String[] custName =new String[]{"Example"};		//Updating and In Use
	//OrderProfile
	public static String[] orderIDString =new String[]{"B0001"};	//Updating and In Use 		
	public static int[] burgurNumber =new int[]{5};					//Updating and In Use
	public static int[] orderStatus =new int[]{0};					//Updating and In Use
	public static int[] oprofileID =new int[]{715684260};			//Updating and In Use
	//Latest Profile Added and Latest Order added
	public static int LatestProfile =custID.length;
	public static int LatestOrder=orderIDString.length; 
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
		int size = custID.length;
		int[] tempCustID = new int[size + 1];
		String[] tempCustName = new String[size + 1];
		double[] tempCustTotal = new double[size + 1];
		for (int i = 0; i < size; i++) {
			tempCustID[i] = custID[i];
			tempCustName[i] = custName[i];
			tempCustTotal[i] = custTotal[i];
		}
		custID = tempCustID;
		custName = tempCustName;
		custTotal = tempCustTotal;
	}
	public static void extendOrderArrays() {
    int size = orderIDString.length;
		String[] tempOrderIDString = new String[size + 1];
		int[] tempBurgurNumber = new int[size + 1];
		int[] tempOrderStatus = new int[size + 1];
		int[] tempOprofileID = new int[size + 1];
		for (int i = 0; i < size; i++) {
			tempOrderIDString[i] = orderIDString[i];
			tempBurgurNumber[i] = burgurNumber[i];
			tempOrderStatus[i] = orderStatus[i];
			tempOprofileID[i] = oprofileID[i];
		}
		orderIDString = tempOrderIDString;
		burgurNumber = tempBurgurNumber;
		orderStatus = tempOrderStatus;
		oprofileID = tempOprofileID;
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
				if (custID.length==0){
				}else if (custID[i]==phoneno){
					gotit=1;
					prof=i;
				}
			}
			if(gotit==1){
				System.out.print("\nCustomer Name : "+custName[prof]);
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
					burgurNumber[LatestOrder]=burgurno;		//Burgur number added 		: OrderProfile
					orderIDString[LatestOrder]=ordernox;	//Burgur ID (added string)	: OrderProfile
					orderStatus[LatestOrder]=PREPARING;		//Burgur status added		: OrderProfile
					oprofileID[LatestOrder]=phoneno;		//Linkage with profile and order profile 
					switch(gotit){
						case 0:
							extendCustomerArrays();
							custID[LatestProfile]=phoneno;		//User ID added			: User Profile
							custName[LatestProfile] =username;	//User Name added 		: User Profile
							custTotal[LatestProfile]+=totalburg;//User Total increased	: User Profile
							LatestProfile++;
						break;
						case 1:
							custTotal[prof]+=totalburg;//User Total increased	: User Profile
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
            values[i] = custTotal[i];
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
            System.out.printf(" %-1s | %-15s   %-1s  %-20s %-1s  %-20s|\n", "", "0"+custID[r], "", custName[r],"",custTotal[r]);
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
				if (orderIDString[i].equals(orderxid)) {
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
				switch (orderStatus[id]){
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
					if(custID[i]==oprofileID[id]){
						profid=i;
					}
				}
				printextra(3,2);
				printextra(3,3);
				printextra(92,1);
				System.out.printf(" %-1s | %-10s   %-15s  %-15s %-10s  %-15s %-15s|\n", "", "Order ID", "Customer ID", "Name","Quantity","Order Value","Order Statue");
				printextra(3,3);
				printextra(92,1);
				System.out.printf(" %-1s | %-10s   %-15s  %-15s %-10s  %-15s %-15s|\n", "", orderIDString[id],"0"+custID[profid], custName[profid],burgurNumber[id],(BURGERPRICE*burgurNumber[id]),y);
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
				if(orderxid==oprofileID[i]){
					values[gh] = i;
					gh++;
				}
			}	
			for(int i=0;i<LatestProfile;i++){
				if (custID[i]==orderxid){
					gotit=1;
					prof=i;
				}
			}
			System.out.println("\n\n  Customer ID found");
			printextra(2,3);
			printextra(62,1);
			System.out.println("\n   Customer ID - "+orderxid);
			System.out.println("   Name        - "+custName[prof]);
			System.out.println("\n\nCustomer Order List");
			System.out.println("=====================\n");
			printextra(3,3);
			printextra(62,1);
			System.out.printf(" %-1s | %-10s   %-1s  %-20s %-1s  %-20s|\n", "", "Order ID", "", "Order Quantity","","Total Value");
			printextra(3,3);
			printextra(62,1);
			for(int i =0;i<gh;i++){
				nn=values[i];
				System.out.printf(" %-1s | %-10s   %-1s  %-20s %-1s  %-20s|\n", "",orderIDString[nn], "", burgurNumber[nn],"",(BURGERPRICE*burgurNumber[nn]));
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
				if(ordertypex==orderStatus[i]){
					values[gh] = i;
					gh++;
				}
			}	
			for(int i=0;i<n;i++){
				gotit=values[i];
				for(int j=0;j<LatestProfile;j++){
					if (oprofileID[gotit]==custID[j]){
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
					System.out.printf(" %-1s | %-10s %-15s %-1s  %-20s %-12s %-20s|\n", "",orderIDString[nn],"0"+oprofileID[nn],"",custName[np],burgurNumber[nn],(BURGERPRICE*burgurNumber[nn]));
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
				if (orderIDString[i].equals(orderxidstring)) {
					gotit=1;
					orderxid=i;
					break;
				}
			}
			while (gotit==0) {
				System.out.print("\n\nInvalid Value. Enter a valid value : ");
				orderxidstring =input.nextLine().trim().toUpperCase();
				for (int i = 0; i < LatestOrder; i++) {
					if (orderIDString[i].equals(orderxidstring)) {
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
			if(0==orderStatus[orderxid]){
				loop=0;
			}else if (1==orderStatus[orderxid]){
				System.out.println("\n   ->This Order is alredy DELIVERED. You can not update this ORDER.");
				System.out.println("\n\nDo you want to update another ORDER DETAIL ? (Y/N) : ");
				pit=again();
			}else if (2==orderStatus[orderxid]){
				System.out.println("\n   ->This Order is CANCELED. You can not update this ORDER.");
				System.out.println("\n\nDo you want to update another ORDER DETAIL ? (Y/N) : ");
				pit=again();
			}
			for(int i=0;i<LatestProfile;i++){
				if(custID[i]==oprofileID[orderxid]){
					np=i;
				}
			}
			if(loop==0 & pit==2){
				System.out.printf("\n\n %-2s %-15s   %-5s  %-15s\n", "", "Order ID", ""," : "+ orderIDString[orderxid]);
				System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Customer ID", ""," : "+ "0"+oprofileID[orderxid]);
				System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Name", ""," : "+ custName[np]);	
				System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Quantity", ""," : "+ burgurNumber[orderxid]);
				System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Order value", ""," : "+ (BURGERPRICE*burgurNumber[orderxid]));
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
					System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Order ID", ""," : "+ orderIDString[orderxid]);
					System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Customer ID", ""," : "+ oprofileID[orderxid]);
					System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Name", ""," : "+ custName[np]);
					System.out.printf(" %-2s %-19s   %-1s  %-15s\n", "", "Current Quantity", ""," : "+ burgurNumber[orderxid]);	
					System.out.println("\n---------------------------------------------------------\n");
					System.out.print("\n\nEnter your Quantity update Value : ");
					int newupdate = input.nextInt();
					do{
						if (newupdate<0){
							System.out.print("\n\nInvalid Value. Enter a valid value : ");
							newupdate = input.nextInt();
						}
					}while(newupdate<0);
					burgurNumber[orderxid]=newupdate;
					System.out.printf("\n\n%-5s %-15s \n", "", "-------------------------------------------");
					System.out.printf("%-5s %-15s  \n", "", "| Updated the Order Quantity Successfully |");
					System.out.printf("%-5s %-15s  \n", "", "-------------------------------------------\n\n");
					System.out.printf(" %-2s %-25s   %-5s  %-15s\n", "", "New Order Quantity", ""," : "+ burgurNumber[orderxid]);
					System.out.printf(" %-2s %-25s   %-5s  %-15s\n", "", "Order value", ""," : "+ (BURGERPRICE*burgurNumber[orderxid]));
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
					System.out.printf("\n %-2s %-15s   %-5s  %-15s\n", "", "Order ID", ""," : "+ orderIDString[orderxid]);
					System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Customer ID", ""," : "+ oprofileID[orderxid]);
					System.out.printf(" %-2s %-15s   %-5s  %-15s\n", "", "Name", ""," : "+ custName[np]);	
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
					orderStatus[orderxid]=newupdate;
					System.out.printf("\n\n%-5s %-15s  \n", "", "-----------------------------------------");
					System.out.printf("%-5s %-15s  \n", "", "| Updated the Order Status Successfully |");
					System.out.printf("%-5s %-15s  \n", "", "-----------------------------------------\n\n");
					String stats="";
					switch (orderStatus[orderxid]){
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
