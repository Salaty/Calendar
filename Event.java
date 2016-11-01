

/** 
 * Class that handles event object and its attributes and implements comparable
 */
import java.util.Date;

public class Event implements Comparable<Event>, java.io.Serializable  {
	
	
	private String title;
	private Date date;
	private String startTime;
	private String endTime;
	
	/**
    Constructor construct an event object with the following attributes 
    @param title the title of the event.
    @param date the date of the event
    @param startTime the starting time of event
    @param endTime the ending time of the event
   */
	public Event(String title, Date date, String startTime, String endTime)
	{
		this.title = title;
		this.date= date;
		this.startTime= startTime;
		this.endTime= endTime;
	}
	/**
	 * returns the title of the event
	 * @return title
	 */
	
	public String getTitle()
	{
		return title;
	}
	/**
	 * returns the date of the event
	 * @return date
	 */
	public Date getDate()
	{
		return date;
	}
	/**
	 * returns the starting time of the event
	 * @return startTime
	 */
	public String getST()
	{
		return startTime;
	}
	
	/**
	 * returns the ending time of the event
	 * @return endTime
	 */
	public String getET()
	{
		return endTime;
	}
	
	/**
	 * returns comparison of two events
	 * @return int results of comparison
	 */
	public int compareTo(Event that) {
		
	int comp =	this.date.compareTo(that.date);
	return comp;
	}
	/**
	 * returns comparison of two events
	 * @return boolean results of comparison
	 */
	public boolean equals(Object x)
	{
		return this.compareTo((Event)x)==0;
	}


	
	
}
