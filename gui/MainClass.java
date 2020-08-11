package gui;
import javax.swing.SwingUtilities;

public class MainClass 
{
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                GUIWindow guiWindow = new GUIWindow("-");
                GUIController controller = new GUIController(guiWindow);
                controller.controll();
            }
        });
    }
}