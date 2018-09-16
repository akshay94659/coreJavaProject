
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class client implements Runnable 
{

    @Override
    public void run() 
    {
        try
        {
          Socket sock=new Socket("172.16.2.246",9000);
          DataOutputStream dos=new DataOutputStream(sock.getOutputStream());
          DataInputStream dis=new DataInputStream(sock.getInputStream());
  
  
          dos.writeBytes("hello server\r\n");
  
           String s=dis.readLine();
            System.out.println(s);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}