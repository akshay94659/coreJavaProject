
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Test
{
      Test()
     {
         try
          {
            ServerSocket sersock=new ServerSocket(9000);
              System.out.println("9000");
              
             while(true)
             {
            Socket sock=sersock.accept();
              System.out.println("accepted");
               Clienthandler obj=new Clienthandler(sock);
               Thread t=new Thread(obj);
               t.start();
             }
              
          }
           catch(Exception ex)
          {
          ex.printStackTrace();
          }
        
     }
      class Clienthandler implements Runnable
      {
          Socket sock;
          Clienthandler(Socket sock)
          {
               this.sock=sock;
                     
          } 

        @Override
        public void run() 
        {
              
              try 
              {
               DataOutputStream  dos = new DataOutputStream( sock.getOutputStream());
              DataInputStream dis=new DataInputStream(sock.getInputStream());
              
             
              dos.writeBytes("hello client\r\n");
              String s=dis.readLine();
              System.out.println(s);
              }
              catch(Exception ex)
              {
                  ex.printStackTrace();
              }
        }
          
      }
      public static void main(String[] args) 
      {
        Test ts=new Test();
        
      }



}