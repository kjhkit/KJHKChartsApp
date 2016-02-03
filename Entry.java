//note: these are for adding a date field in the future
import java.util.Date;
import java.text.ParseException;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;

public class Entry 
{
	SimpleDateFormat myFormat = new SimpleDateFormat("\"yyyy-MM-dd HH:mm:ss\""); //This should be the right format, see the Java doc for SimpleDateFormat for more information
	Date m_date;
	String m_artist;
	String m_album;
	String m_type;
	
	//Returns a string representation of this Entry object
	public String toString()
	{
		if(m_date!=null) return m_album + " by " + m_artist + " is " + m_type + " and was played on " + myFormat.format(m_date);
		return m_album + " by " + m_artist + " is " + m_type;
	}
	
	//Constructor from 3 parameters (no date)
	Entry(String type, String artist, String album)
	{
		m_artist = artist;
		m_album = album;
		m_type = type;
	}
	
	//***********************
	//Erick's code goes here
	//TODO: make a constructor for Entry
	//Note: use myFormat.parse(string) (which returns a date) to get m_date of Date type from the date input String
	//
	//Parameters: String type, String artist, String album, String date
	//Precondition: type, artist, album, and date are all valid Strings.  The date String is formatted like: yyyy-MM-dd HH:mm:ss
	//Postcondition: a new Entry has been instantiated with variables values according to the input
	//Throws: InvalidParameterException if the date parameter is not formatted properly
	//Returns: none
	//***********************'
	Entry(String type, String artist, String album, String date)
	{
		m_artist = artist;
		m_album = album;
		m_type = type;
		try{
			m_date = myFormat.parse(date);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	boolean hasDate()
	{
		return m_date!=null;
	}
	
	//Get methods
	Date getDate()
	{
		return m_date;
	}
	
	String getDateString()
	{
		return myFormat.format(m_date);
	}
	
	String getArtist()
	{
		return m_artist;
	}
	
	String getAlbum()
	{
		return m_album;
	}
	
	String getType()
	{
		return m_type;
	}
}
