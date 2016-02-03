import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JScrollPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.filechooser.*;

//We "extend" JFrame, meaning this class inherits from JFrame and can be thought of as a JFrame itself
//We then "implement" ActionListener, which is an interface (essentially a set of requirements for a class if it needs to use a certain tool) 
public class Control extends JFrame implements ActionListener{
	
	//Used for error messages
	JFrame frame;
	
	//Holds the buttons
	JPanel panelButton = new JPanel();
	
	//Choose File button
	JButton buttonChooseFile;
	
	//Total Charts button
	JButton buttonTotalCharts;
	
	//Charts compiler object declaration (this handles all the charts operations)
	ChartsCompiler charts;
	
	public Control()
	{
		//This is us calling our parent constructor (since it inherits from JFrame)
		super("KJHK Music Logs App");
		
		setLayout( new FlowLayout() );      // set the layout manager

		//Create the buttons
	    buttonChooseFile = new JButton("Choose File"); 
	    buttonTotalCharts = new JButton("Total Charts");
	    
	    //Action Listener is an outside class that lets us know when a button is pressed
	    //This is why we have to implement the "Action Listener"
	    //It requires us to have an ActionPerformed(ActionEvent) method
	    //Action Listener calls this when something happens, so it needs to know that we have it so it doesn't trigger an exception
	    buttonChooseFile.addActionListener(this);
	    buttonTotalCharts.addActionListener(this);
	    
	    //Add the buttons to our JFrame
	    add(buttonChooseFile);
	    add(buttonTotalCharts);
        	
        // JFrame properties
	    
	    //Size
        setSize(300, 150);
        
        //Put in the middle of the screen
        setLocationRelativeTo(null);
        
        //Exit the application when the "X" button is pressed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Reveal ourselves to the world
        setVisible(true);
        
        //This is for error messages
        frame = new JFrame("Dialogue");
	}
	
	//Called when "Choose File" is pressed
	//Returns: A string of the filepath (or "Error" if no file selected)
	public String chooseFile()
	 {
		System.out.println("In chooseFile");
		
		//Choose the file
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
		chooser.setFileFilter(filter);
		int val = chooser.showOpenDialog(this);
		if(val == JFileChooser.APPROVE_OPTION) 
		{
			return chooser.getSelectedFile().getPath();
		}
		
		//This is in case there is no file selected
		return "Error";
	 }
	
	//This is called by ActionListener every time something happens
	//We check for "Choose File" and "Total Charts"
	public void actionPerformed(ActionEvent event)
	{
		switch(event.getActionCommand())
		{
		case "Choose File":
			String filepath = chooseFile();
			if(!filepath.equals("Error"))
			{
				charts = new ChartsCompiler(filepath);
			}
			break;
		case "Total Charts":
			if(charts!=null)
			{
				printCharts();
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "Error: You must select a file first");
			}
			break;			
		}
	}
	
	//Precondition: ChartsCompiler charts has been instantiated
	private void printCharts()
	{
		System.out.println("In printCharts");
		//chartsStr is used for exporting to a .txt file
		String chartsStr = "";
		//htmlStr is used for printing to a window (it likes HTML markup)
		String htmlStr = "<html>";
		//Vector is a data structure that we use to hold the charts
		//Think of it like an array with a variable length
		Vector<Album> albums = charts.totalCharts();
		
		//Print to file
		//Get the current date
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy");
		Date myDate = new Date();
		try
		{
			//Open the output file and name it
			PrintWriter writer = new PrintWriter("Totalled Charts for " + dateFormat.format(myDate) + ".txt", "UTF-8");
			//Add the albums (in descending order of number of plays) to the both strings
			for(int i=0; i<albums.size(); i++)
			{
				chartsStr += ("\n" + albums.get(i).getAlbum() + " by " + albums.get(i).getArtist() + " has " + albums.get(i).getPlays() + " plays");
				htmlStr += ("<br>"+ albums.get(i).getAlbum() + " by " + albums.get(i).getArtist() + " has " + albums.get(i).getPlays() + " plays");
			}
			//Close the htmlStr tag
			htmlStr += "</html>";
			//Print to the file and close it
			writer.print(chartsStr);
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(chartsStr);
		printToWindow(htmlStr);		
	}
	
	private void printToWindow(String message)
	 {
		System.out.println("In printToWindow");
		//Make a new container to print the charts in
		JLabel label = new JLabel("Charts");
		//Set properties like font, add the message (in html markup)
		label.setFont(new Font("SansSerif", Font.PLAIN, 14));
		label.setText(message);
		//Make it scrollable
		JScrollPane scroll = new JScrollPane(label);
		//Make a new window to put the scrollable charts in
		JFrame newFrame = new JFrame("Totalled Charts");
		//Set the size
		newFrame.setSize(500, 800);
		//Set the window to close (but not the program) when X button is pressed
	    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    newFrame.add(scroll);
	    newFrame.setVisible(true);
	 }
	
}
