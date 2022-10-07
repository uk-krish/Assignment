package ATM_Process;

import java.sql.*;
import java.util.Scanner;



public class options {
public static void main(String[] args) throws Exception {
	Scanner sc=new Scanner(System.in);
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con;
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","Unni8664");
	
	Statement cs=con.createStatement();
	PreparedStatement ps;
	
	int n;
	
	
	
	do {
		System.out.println("\n1.Load Cash to ATM\n2.Show Customer Details\n3.Show ATM Operations");
		n=sc.nextInt();
		int n1 = 0,n2=0,n3=0;
		int five= 500,hun=100,two=2000,total=0,sub;
		switch (n) {
		case 1:
			System.out.print("Enter the 2000 rupee number : ");
			 n1=sc.nextInt();
			System.out.print("Enter the 500 rupee number : ");
			 n2=sc.nextInt();
			System.out.print("Enter the 100 rupee number : ");
			 n3=sc.nextInt();
			 ps=con.prepareStatement("insert into money value(? ,?, ?)");
			 ps.setInt(1, two);
			 ps.setInt(2, n1);
			 ps.setInt(1, n1*two);
			 ResultSet rs4=ps.executeQuery();

			 ps=con.prepareStatement("insert into money value(? ,?, ?)");
			 ps.setInt(1,five);
			 ps.setInt(2, n2);
			 ps.setInt(1, n2*five);
			 rs4=ps.executeQuery();
			 
			 ps=con.prepareStatement("insert into money value(? ,?, ?)");
			 ps.setInt(1, hun);
			 ps.setInt(2, n3);
			 ps.setInt(1, n2*hun);
			
			rs4=ps.executeQuery();
			System.out.printf("Denomination \t Number \t Value");
			System.out.printf("\n  2000      \t    %d 	  \t   %d",n1,(n1*2000));
			System.out.printf("\n  500       \t    %d    \t   %d",n2,(n2*1000));
			System.out.printf("\n  100       \t    %d    \t   %d\n",n3,(n3*500));
			System.out.println("Successfully add Money in ATM machine");
			break;
			
			
			
			
		case 2:
			System.out.println("Press 1 to confrim to view Customer Details ");		
			int c2=sc.nextInt();
			if(c2==1) {
				String sql2="select * from atm";
				ResultSet rs=cs.executeQuery(sql2);
				System.out.println("Acc No\t"+"Account Holder\t"+"Pin Number\t"+"Account Balance\t");
				while(rs.next()) {
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"	"+"\t"+rs.getString(3)+"\t"+"	"+rs.getString(4)+"\t");
				}
			}
			else {
				System.out.println("Try Again");
			}
			break;
			
			
			
			
			
			
			
			
			
			
		case 3:
			System.out.println("Enter Your Pin");
			int user_pin=sc.nextInt(),check = 0;
			String up=String.valueOf(user_pin);
			ps=con.prepareStatement("select * from atm where pin_number=?");
			ps.setInt(1, user_pin);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				check=Integer.valueOf(rs.getString(3));
			}
			String ck=String.valueOf(check);
			if(up.equals(ck)) {
				System.out.println("\n1.Check Balance\n2.Withdraw Money\n3.Transfer Money\n4.Back");
				int ca3=sc.nextInt();
				int user;
				switch (ca3) {
				case 1:
					ps=con.prepareStatement("Select * from atm where pin_number=?");
					ps.setInt(1, user_pin);
					System.out.println("Your balane is");
					ResultSet rs1=ps.executeQuery();
					
					while(rs1.next()) {
						System.out.println((rs1.getString(4)));
					}
					break;

					
			
				case 2:
					System.out.println("Enter the Amount");
					int w=sc.nextInt();
					String ac_balance = null;
					ResultSet rs2=ps.executeQuery("");
					while(rs2.next()) {
						ac_balance=rs2.getString(4);
					}
					int ac_b=Integer.parseInt(ac_balance);	
					if(ac_b<w) {
						System.out.println("Your don't have money");
					}
					int bb=ac_b-w;
					if(w>=100 && w<=10000) {
						
						if(w<=1000 || w==500) {
							if(w%500==0) {
								
								total=five;
							}
							else {
								w=w/five;
								total=w*five;
								sub=w-total;
							}
						}
						
						if(w<500) {
							if(w%100==0) {
								w/=hun;
								total=w*hun;
								sub=w-total;
							}
						}
						
						 if(w<=3000){
							 w%=two;
								if(w>=five) {
									w=w%five;
								}
								if(w<=hun) {
									w=w%hun;
								}
						}
						
					}
					else {
						System.out.println("Max withdrawal limit for a single transaction is10,000₹ and minimum is100₹");
					}
					System.out.println("Your balane is \n"+bb);
					
				break;
				case 3:
					System.out.println("Transition limit should be 1000 to 10,000 only");
					System.out.println("Enter the Amount");
					int money=sc.nextInt();
					System.out.println("Enter the User Account No: ");
					int acc=sc.nextInt();
					ps=con.prepareStatement("Select * from atm where acc=?");
					ps.setInt(1, acc);
					System.out.println("Your balane is");
					ResultSet rs3=ps.executeQuery();
					String user_bb = null,current_bb = null;
					while(rs3.next()) {
						user_bb=rs3.getString(4);
					}
					user_bb+=current_bb;
					ps=con.prepareStatement("update atm set account_balance=? where acc_no=?");
					ps.setString(1, user_bb);
					ps.setInt(2, acc);
					rs3=ps.executeQuery();
					break;
			}
			}
			break;
			
			
			
			
			
			
			
			
			
			
		case 4:
			System.out.printf("Denomination \t Number \t Value");
			System.out.printf("\n  2000      \t    %d 	  \t   %d",n1,(n1*2000));
			System.out.printf("\n  500       \t    %d    \t   %d",n2,(n2*1000));
			System.out.printf("\n  100       \t    %d    \t   %d\n",n3,(n3*500));
			System.out.println("Successfully add Money in ATM machine");
			break;
		
		}
		
	} 
	while (n!=3);
	
}
}
