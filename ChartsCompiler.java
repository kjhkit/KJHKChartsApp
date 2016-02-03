import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Vector;
import java.util.Comparator;


public class ChartsCompiler 
{
	Vector<Entry> entries;
	
	//parses the .csv specified by filepath and stores them in the Vector entries
	public ChartsCompiler(String filepath)
	{
		System.out.println("In ChartsCompiler");
		try
		{
			BufferedReader br = null;
			String line = "";
			String csvSplitBy = ",";
			
			entries = new Vector<Entry>();
			
			//fills entries with entries parsed from the rawCharts file (ASSUMES 3 ITEMS PER ROW)
			//TODO: add check for whether there are 3 or 4 items per row (was Entry Date included?), add code to handle parsing of 4 items per row
			//Note: order for 4 items is "What Type of Music Is This?","Artist","Album","Entry Date"
			//		order for 3 items is "What Type of Music Is This?","Artist","Album"
			//Approach: add boolean includesDate, true for 4 items per row, false for 3
			//			this will serve as the indicator for 3 item vs 4 item code to run
			//			the value of this boolean can be determined by checking the value of myEntry.length()
			try
			{
				br = new BufferedReader(new FileReader(filepath));
				String[] myEntry = line.split(csvSplitBy);
				boolean includesDate = myEntry.length == 4;
				while((line = br.readLine()) != null)
				{
					myEntry = line.split(csvSplitBy);
					if(!includesDate) entries.add(new Entry(myEntry[0], myEntry[1], myEntry[2]));
					else entries.add(new Entry(myEntry[0], myEntry[1], myEntry[2], myEntry[3]));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(br != null)
					{
						br.close();
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			//for testing
			for(int i=0; i<entries.size(); i++)
			{
				System.out.println(entries.get(i).toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//TODO: Finish this
	//returns a vector of Strings with the information of how many songs were logged in a given hour, and how many of those were rotation	
	
	//returns a Vector of Album objects sorted by number of plays
	public Vector<Album> totalCharts()
	{
		System.out.println("In totalCharts");
		Vector<Album> albums = new Vector<Album>(0);
		for(int i=0; i<entries.size(); i++)
		{
			if(entries.get(i).getType().equals("\"Rotation\""))
			{
				boolean isRepeat = false;
				for(int j=0; j<albums.size(); j++)
				{
					if(albums.get(j).equals(entries.get(i)))
					{
						isRepeat = true;
						albums.get(j).addPlay();
					}
				}
				if(!isRepeat)
				{
					albums.add(new Album(entries.get(i)));
				}
			}
		}
		
		//Account for DJ's being silly (marking rotation as non-rotation)
		for(int i=0; i<entries.size(); i++)
		{
			if(entries.get(i).getType().equals("\"Everything Other Than Rotation\""))
			{
				for(int j=0; j<albums.size(); j++)
				{
					if(albums.get(j).equals(entries.get(i)))
					{
						albums.get(j).addPlay();
					}
				}
			}
		}
		
		//Sort albums by plays
		sort(albums);
		
		return albums;
	}
	
	public void sort(Vector<Album> albums)
	{
		System.out.println("In sort");
		Collections.sort(albums, new Comparator<Album>(){
			@Override
			public int compare(Album o1, Album o2)
			{
				return o2.getPlays()-o1.getPlays();
			}
		});
	}

}
