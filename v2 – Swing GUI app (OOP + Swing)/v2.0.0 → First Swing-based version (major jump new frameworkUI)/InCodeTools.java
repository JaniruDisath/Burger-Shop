import java.util.*;
class InCodeTools{
private static Scanner input = new Scanner(System.in);
	//To Clear the Screen
	public final static void clearScreen() {  
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
	//To Exit the Application
	public static void exit(){ 
		clearScreen(); 
		System.out.println("\n\t\tYou left the program...\n"); 
		System.exit(0); 
	} 
	//Invalid Input
	public static int invalidInput(){
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
	//Try Again
	public static int tryAgain(){
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
	//Box Creation With Dashes
	public static void dashesBox(String box){
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
	//Printing Dashes, Spaces or Skipping Lines
	public static void finalPrint(int dashes,String x){
		for(int i=0;i<dashes;i++){
			System.out.print(x);
		}
	}
	public static void printExtra(int amount, int selection){
		switch(selection){
			case 1:
			finalPrint(amount,"-");
			System.out.print("\n");
			break;
			case 2:
			finalPrint(amount,"\n");
			break;
			case 3:
			finalPrint(amount," ");
			break;
			default:
			System.out.println("Wrong selection");
		}
	}
	//Customer ID nputMismatchException Error Check Check
	public static int custIDError(){
	int phoneNumber=0;
		do {
			try {
				System.out.print("Enter Customer ID (phone no.) : "); 
				phoneNumber =input.nextInt();
				InCodeTools.printExtra(1,2);
				InCodeTools.printExtra(90,1);
				} 
			catch (InputMismatchException e) {
				InCodeTools.printExtra(1,2);
				InCodeTools.printExtra(90,1);
				System.out.println("Invalid Phone Number ");
				InCodeTools.printExtra(90,1);
				InCodeTools.printExtra(1,2);
			}
			input.nextLine();
		} while (phoneNumber <= 0);	
		return phoneNumber;
	}
	//Cust Id Length Check
		public static int custID(){
			int phoneNumber=0;
			Boolean isANumber=false,isInLength=false;
			do{
				isANumber=false;isInLength=false;
				phoneNumber=custIDError();
				String ids = Integer.toString(phoneNumber);
				do {
					ids = Integer.toString(phoneNumber);
					if(phoneNumber <= 0){
						printExtra(1,2);
						printExtra(90,1);
						System.out.print("\nInvalid Value. Enter a valid Phone Number : ");
						phoneNumber=custIDError();
						printExtra(1,2);
						printExtra(90,1);
						ids = Integer.toString(phoneNumber);
					}else if(9!=ids.length()){
						printExtra(1,2);
						printExtra(90,1);
						System.out.println("\nA phone number Must have 10 digits");
						System.out.print("\nEnter a valid Phone Number : ");
						phoneNumber=custIDError();
						printExtra(1,2);
						printExtra(90,1);
						ids = Integer.toString(phoneNumber);
					}
					if(phoneNumber > 0 || 9==ids.length()){
						isInLength=false;
					}
				}while(isInLength);
				isANumber=true;
		}while(isANumber & isInLength);
		return phoneNumber;
	}
	//Checking Customer Name
	public static String custNameCheck(String id, String something){
		while ( 0==id.length()){
			if( 0==id.length()){
				printExtra(1,2);
				printExtra(90,1);
				System.out.println("\nYou can't leave a space here");
				System.out.print("\nEnter a "+something+" : ");
				id = input.nextLine();
				printExtra(1,2);
				printExtra(90,1);
			}
		}	
		return id;
	}
	//Checking Burgur Count
	public static int burgerCount(int id){
		while (id<0){
			if( id<0){
				printExtra(1,2);
				printExtra(90,1);
				System.out.println("\nInvalid Number");
				System.out.print("\nEnter a a Valid Amount :");
				id = input.nextInt();
				input.nextLine();
				printExtra(1,2);
				printExtra(90,1);
			}
		}	
		return id;
	}
	//No Orders Found Massege
		public static void noOrdersFound(String boxtext){
		System.out.printf(" %-1s | %-10s %-10s %-4s  %-40s %-2s %-12s|\n", "","", "","","","","");
		System.out.printf(" %-1s | %-10s %-10s %-4s  %-40s %-2s %-12s|\n", "","", "","","No "+boxtext+" Found","","");
		System.out.printf(" %-1s | %-10s %-10s %-4s  %-40s %-2s %-12s|\n", "","", "","","","","");
		InCodeTools.printExtra(3,3);
		InCodeTools.printExtra(87,1);
	}
}
