
import java.io.DataInputStream;

class Ping 
{
    public static void main(String[] args) 
    {
          try
         {
             for(int i=230;i<=255;i++)
             {
               Process p = Runtime.getRuntime().exec("ping 172.16.2."+i);
               DataInputStream dis=new DataInputStream(p.getInputStream());
                  int count=0;
        
                     while(true)
                     {
                       String s=dis.readLine();
                        if(s==null)
                         break;
            
           
                       else if(s.contains("TTL"))
                      {
                        count++;
                      }
            
                    }  
                    if(count==4)
                    {
                     System.out.println("172.16.2."+ i+ "system connectable");
            
                     }
                     else
                    {
                         System.out.println("172.16.2."+ i+ "system not connectable");
            
                    }
            }
        }
        catch(Exception ex)
        {
            
        }
               
    }
}