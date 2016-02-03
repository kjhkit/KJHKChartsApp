/**
 * Created by johnm on 11/10/2015.
 */

public class KJHKChartsApp
{
    public static void main(String[] args)
    {
        //This starts the GUI which handles all necessary processes
        //We use a separate class in order to keep the GUI "thread safe," which basically means that multiple processes can access the GUI without causing error
        Control myGUI = new Control();
    }
}
