import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
 
public class Screenshot
{
    public static final long serialVersionUID = 1L;
    public static void main(String[] args)
    {
        try {
            
            Robot r = new Robot();
 
            // It saves screenshot to desired path
            String path = "D:// Shot.jpg";
 
            // Used to get ScreenSize and capture image
            Rectangle capture = 
            new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage Image = r.createScreenCapture(capture);
            ImageIO.write(Image, "jpg", new File("D://sshot.jpg"));
            System.out.println("Screenshot saved");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
