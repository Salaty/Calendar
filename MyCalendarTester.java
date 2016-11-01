

/**
 * Programming assignments 2
 * @author Achraf Derdak
 * copyright 2016
 * @version 1.8.0_102
 */

/**
 * main class that handles all classes populates event data structure 
 * and creates a file and writes to it
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MyCalendarTester {
	
	public static int day;
	public static int month;
	public static int year;
	/**
	 * main method which has a scanner to take user's input and call required methods
	 * @param args for main method
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		GregorianCalendar cal = new GregorianCalendar();
		Scanner scan = new Scanner(System.in);
		File file = new File("events.txt");
		MyCalendar  myCal= new MyCalendar();
		printCalendar(cal);
		
        String choice ="";
        		
        while (scan.hasNext())
        {
        pl("\n \nSelect one of the following options: ");
		pl("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
        	choice = scan.nextLine();
        	if (choice.equalsIgnoreCase("Q"))
        	{
        		scan.close();
    			writingToFile(myCal.getEventList());
    			return;
        		
        	}
        	if (choice.equalsIgnoreCase("C"))
        	{
        		Date adate = null;
        		pl("please enter the title");
        		String atitle = scan.nextLine();
        		pl("please enter the date");
        		String datee = scan.nextLine();
        		
        		pl("please enter the starting time");
        		String ast = scan.nextLine();
        		
        		pl("please enter the end time");
        		String aet;
        		if (scan.hasNextLine()) {
                    aet = scan.nextLine();
                } else {
                    aet = null;
                }
        		
        		try {
					 adate= new SimpleDateFormat("MM/dd/yyyy kk:mm").parse(datee+" "+ast);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
        		
        	Event thisEvent = new Event (atitle, adate, ast, aet);	
        	myCal.getEventList().add(thisEvent);
        	
        		
        	}
        	else if (choice.equalsIgnoreCase("E"))
        	 {
        		try{
        		int year = myCal.getEventList().first().getDate().getYear();
        		pl (year+1900);
        		for (Event ev: myCal.getEventList() )
        		{
        			
        		
        pl(FULLDAYS.values()[ev.getDate().getDay()]+" "+MONTHS.values()[ev.getDate().getMonth()]+" "+ev.getDate().getDate()+" "+ev.getST()+" - "+ev.getET()+" "+ev.getTitle());	

        			
        			
        			
        			if (ev.getDate().getYear() != year )
        			{
        				pl(ev.getDate().getYear()+1900 );
        				year = ev.getDate().getYear() ;
        			}
        			
        		}}
        		catch (NoSuchElementException e)
        		{
        			pl("No events scheduled");
        		}
        	 }
        	
        	
        	else if (choice.equalsIgnoreCase("V"))
        	{
  
        		pl("please select [D]ay or [M]onth view");
        		String pick = scan.nextLine();
        		if (pick.equalsIgnoreCase("D"))
        		{
        			MONTHS[] arrayOfMonths = MONTHS.values();
        		    FULLDAYS[] arrayOfDays = FULLDAYS.values();
        			 System.out.print(arrayOfDays[cal.get(Calendar.DAY_OF_WEEK)-1]);
        			 System.out.print(", ");
        				System.out.print(arrayOfMonths[cal.get(Calendar.MONTH)]);
        				System.out.print(", ");
        				System.out.print(cal.get(Calendar.DAY_OF_MONTH));
        				System.out.print(", ");
        				System.out.println(cal.get(Calendar.YEAR));
        				 day = cal.get(Calendar.DAY_OF_MONTH);
        				 month = cal.get(Calendar.MONTH);
        				 year = cal.get(Calendar.YEAR);
        				
        				for (Event ev: myCal.getEventList() )
        				{
        					if ((ev.getDate().getDate()==day)&& (ev.getDate().getMonth()==month)&&(ev.getDate().getYear()+1900==year))
        					{
        						
        						
        						pl(ev.getTitle()+" "+ev.getST()+" - "+ev.getET());
        						
        					}
        				}
        		pl("");	
        		
        		String chooce ="";
        		while (!chooce.equalsIgnoreCase("M"))
        		{
        			pl("");
        			pl("please select [M]ain , [N]ext or [P]revious ");
        			chooce = scan.nextLine();
        			if (chooce.equalsIgnoreCase("N"))
        				{
        				cal.add(Calendar.DATE,1);
        		       printCal(cal);
        		       for (Event ev: myCal.getEventList() )
       				{
       					if ((ev.getDate().getDate()==day)&& (ev.getDate().getMonth()==month)&&(ev.getDate().getYear()+1900==year))
       					{
       						
       						
       						pl(ev.getTitle()+" "+ev.getST()+" - "+ev.getET());
       						
       					}
       				}
        		       }
        			else if (chooce.equalsIgnoreCase("P"))
        			{
        				cal.add(Calendar.DATE,-1);
         		       printCal(cal);
         		      for (Event ev: myCal.getEventList() )
      				{
      					if ((ev.getDate().getDate()==day)&& (ev.getDate().getMonth()==month)&&(ev.getDate().getYear()+1900==year))
      					{
      						
      						
      						pl(ev.getTitle()+" "+ev.getST()+" - "+ev.getET());
      						
      					}
      				}
        			}
        	
        		}	
        		 
        		
        		}
        		else if (pick.equalsIgnoreCase("M"))
        		{
        			int lastDate = cal.getActualMaximum(Calendar.DATE);
        			printCalendarEvent(cal, myCal,lastDate);
        		
        			String chooce ="";
            		while (!chooce.equalsIgnoreCase("M"))
            		{
            			pl("");
            			pl("please select [M]ain , [N]ext or [P]revious ");
            			chooce = scan.nextLine();
            			
            			if (chooce.equalsIgnoreCase("N"))
            			{
            				cal.add(Calendar.MONTH,1);
            			
							int lastDat = cal.getActualMaximum(Calendar.DATE);

            				printCalendarEvent(cal, myCal, lastDat);
            				
            			}
            			else if (chooce.equalsIgnoreCase("P"))
            			{
            				cal.add(Calendar.MONTH,-1);
            				int lastDat = cal.getActualMaximum(Calendar.DATE);
            				printCalendarEvent(cal, myCal,lastDat);
            			}
            			
            			
            		}
        			
        		}
        		
        		}
        	else if (choice.equalsIgnoreCase("G"))
        	{
        		Date d = null;
        		pl("please enter date in the MM/DD/YYYY");
        		String searchDate = scan.nextLine();
        		try {
					 d = new SimpleDateFormat("MM/dd/yyyy").parse(searchDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
        		
        		for (Event eve : myCal.getEventList())
        		{
        			if (eve.getDate().getDate()==d.getDate()&&eve.getDate().getMonth()==d.getMonth()&&eve.getDate().getYear()==d.getYear())
        			{
        				pl(eve.getTitle()+" "+eve.getST()+" - "+eve.getET());
        			}
        		}
        	}
        	
        	else if (choice.equalsIgnoreCase("D"))
        	{
        		pl("please enter [S]elected or [A]ll");
        		String deleteChoice = scan.nextLine();
        		if (deleteChoice.equalsIgnoreCase("S"))
        		{
        			Date d = null;
            		pl("please enter date in the MM/DD/YYYY");
            		String searchDate = scan.nextLine();
            		try {
    					 d = new SimpleDateFormat("MM/dd/yyyy").parse(searchDate);
    				} catch (ParseException e) {
    					e.printStackTrace();
    				}
            		Set<Event> removeUs = new HashSet<Event>();

            		for (Event eve : myCal.getEventList())
            		{
            			if (eve.getDate().getDate()==d.getDate()&&eve.getDate().getMonth()==d.getMonth()&&eve.getDate().getYear()==d.getYear())
            			{
            				removeUs.add(eve);
            			}
            		}
            		
            		myCal.getEventList().removeAll(removeUs);
            	}
        		else if (deleteChoice.equalsIgnoreCase("A"))
        		{
        			myCal.getEventList().clear();
        		}
        		}
        	
        	else if (choice.equalsIgnoreCase("L"))
        	{
        		if (file.exists())
        		{
        			TreeSet<Event> trial =null;
        			 try {
        			        ObjectInputStream in = new ObjectInputStream(new FileInputStream("events.txt"));
        			        trial = (TreeSet<Event>) in.readObject(); 
        			        in.close();
        			    
        			 }
        			    catch(Exception e) {}
        			 
        			 myCal.getEventList().addAll(trial);
        		     
        		      }
        		
        		else 
        		{
        			try {
        				pl("This is the first run. No file at this point.");

						file.createNewFile();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
        		}
        	}
        		
        		       
        		
        		      }  
        	
        	
        		}
        		
        	


	/**
	 * method to write a serialized event to file
	 * @param even which is the treeSet that holds the event
	 */   
        

	public static void writingToFile(TreeSet<Event> even)
	{
		try {
		FileOutputStream fileOut = new FileOutputStream("events.txt");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(even);
        out.close();
        fileOut.close();
    } catch (IOException ex) {
    }
		
	}


	/**
	 * method to print Calendar 
	 * @param c gregorian Calendar
	 */  


	public static void printCalendar(Calendar c)
	{   
	MONTHS[] arrayOfMonths = MONTHS.values();
	DAYS[] arrayOfDays = DAYS.values();
	GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
	pl("     "+arrayOfMonths[c.get(Calendar.MONTH)]+" "+c.get(Calendar.YEAR));

	for (int i =0; i< arrayOfDays.length; i++ )
	{
		p(arrayOfDays[i]+" ");

	}
	pl("");
	int maxDay= c.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
	
	for (int i =0; i< arrayOfDays.length; i++ )
	{
		if (arrayOfDays[temp.get(Calendar.DAY_OF_WEEK)-1]==arrayOfDays[i])
		{
			for (int j =0; j<i; j++)
			{
				p("   ");
			}
		}

	}
	
	for (int i =0 ; i< maxDay; i++)
	{
		temp.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1+i);
		if (temp.get(Calendar.DAY_OF_MONTH)>=10)
		{
			if (temp.get(Calendar.DAY_OF_MONTH)==c.get(Calendar.DAY_OF_MONTH))
			{
				p("["+temp.get(Calendar.DAY_OF_MONTH)+"]");
				p(""); 
			}
			else
				{
				p( temp.get(Calendar.DAY_OF_MONTH));
			     p(" ");
			     }
		}
		else 
		{
			if (temp.get(Calendar.DAY_OF_MONTH)==c.get(Calendar.DAY_OF_MONTH))
			{
				p(""+"[" +temp.get(Calendar.DAY_OF_MONTH)+"]");
				p("");
			}
			else
			{
			p(" "+ temp.get(Calendar.DAY_OF_MONTH));
			p(" ");
			}
		}

		if( arrayOfDays[temp.get(Calendar.DAY_OF_WEEK)-1]==DAYS.Sa)
		{
			pl("");
		}
	}
c.get(Calendar.DAY_OF_MONTH);

	}

	/**
	 * method to print Calendar for the event view
	 * @param c gregorian Calendar
	 * @param myC treeSet that holds the events
	 * @param lastDate the last day of the month
	 */  

	public static void printCalendarEvent(Calendar c, MyCalendar myC, int lastDate)
	{   
	MONTHS[] arrayOfMonths = MONTHS.values();
	DAYS[] arrayOfDays = DAYS.values();
	GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
	pl("     "+arrayOfMonths[c.get(Calendar.MONTH)]+" "+c.get(Calendar.YEAR));

	for (int i =0; i< arrayOfDays.length; i++ )
	{
		p(arrayOfDays[i]+" ");

	}
	pl("");
	int maxDay= lastDate;
	
	for (int i =0; i< arrayOfDays.length; i++ )
	{
		if (arrayOfDays[temp.get(Calendar.DAY_OF_WEEK)-1]==arrayOfDays[i])
		{
			for (int j =0; j<i; j++)
			{
				p("   ");
			}
		}

	}
	
	if (myC.getEventList().size()==0)
	{
		for (int i =0 ; i< maxDay; i++)
		{
			temp.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1+i);
			if (temp.get(Calendar.DAY_OF_MONTH)>=10)
			{
				p( temp.get(Calendar.DAY_OF_MONTH));
			     p(" ");
			}
			else
			{
				p(" "+ temp.get(Calendar.DAY_OF_MONTH));
				p(" ");
				
			}
			if( arrayOfDays[temp.get(Calendar.DAY_OF_WEEK)-1]==DAYS.Sa)
			{
				pl("");
			}
			
		}
	}
	
	for (int i =0 ; i< maxDay; i++)
	{
		temp.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1+i);
		for (Event ev : myC.getEventList())
		{
		
		if (temp.get(Calendar.DAY_OF_MONTH)>=10)
		{
			if (temp.get(Calendar.DAY_OF_MONTH)==ev.getDate().getDate()&&temp.get(Calendar.MONTH)==ev.getDate().getMonth()&&temp.get(Calendar.YEAR)==ev.getDate().getYear()+1900)
			{
				p("["+temp.get(Calendar.DAY_OF_MONTH)+"]");
				p(""); 
				break;
			}
			else
				{
				if (ev== myC.getEventList().last())
				{
				p( temp.get(Calendar.DAY_OF_MONTH));
			     p(" ");
				}
			     }
		}
		else 
		{
			if (temp.get(Calendar.DAY_OF_MONTH)==ev.getDate().getDate()&&temp.get(Calendar.MONTH)==ev.getDate().getMonth()&&temp.get(Calendar.YEAR)==ev.getDate().getYear()+1900)
			{
				p(""+"[" +temp.get(Calendar.DAY_OF_MONTH)+"]");
				p("");
				break;
			}
			else
			{
				if (ev== myC.getEventList().last())
				{
			p(" "+ temp.get(Calendar.DAY_OF_MONTH));
			p(" ");
				}
			}
		}

		
	}
		if( arrayOfDays[temp.get(Calendar.DAY_OF_WEEK)-1]==DAYS.Sa)
		{
			pl("");
		}
		}
c.get(Calendar.DAY_OF_MONTH);



	}
	/**
	 * method to print a simple calendar
	 * @param c gregorian Calendar
	 */  
	public static void printCal(Calendar c)
	{   
		
		MONTHS[] arrayOfMonths = MONTHS.values();
	    FULLDAYS[] arrayOfDays = FULLDAYS.values();
	    
	    System.out.print(arrayOfDays[c.get(Calendar.DAY_OF_WEEK)-1]);
	    System.out.print(" ");
		System.out.print(arrayOfMonths[c.get(Calendar.MONTH)]);
		System.out.print(" ");
		System.out.print(c.get(Calendar.DAY_OF_MONTH));
		System.out.println(" ");
		day = c.get(Calendar.DAY_OF_MONTH);
		 month = c.get(Calendar.MONTH);
		  year= c.get(Calendar.YEAR);
	}
	
	public static void pl(Object b)
	{
		System.out.println(b);
	}
	public static void p(Object b)
	{
		System.out.print(b);
	}
}

