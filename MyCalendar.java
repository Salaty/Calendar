


/** 
 * Class that creates a TreeSet to hold the events in a sorted manner
 */
import java.util.TreeSet;

public class MyCalendar {

	private TreeSet<Event> eventList;
	
	/** 
	 * Constructor to construct MyCalendar which initializes the treeSet
	 */
	public MyCalendar ()
	{ 
		eventList= new TreeSet<Event>();
	}
	
	/** 
	 * method return the treeSet
	 * @return eventlList
	 */
	public TreeSet<Event> getEventList()
	{
		return eventList;
	}


	
	
	
	
	
	
}
