
import java.io.*;
        
        
        
        

class Job implements Runnable {

    int ip;

    Job(int ip)
    {
        this.ip = ip;
    }

    @Override
    public void run() 
    {

        try 
        {
            Process p = Runtime.getRuntime().exec("ping "+credentials.Range + ip);
            DataInputStream dis = new DataInputStream(p.getInputStream());
            int count = 0;

            while (true) 
            {
                String s = dis.readLine();
                if (s == null) {
                    break;
                } 
                else if (s.contains("TTL")) 
                {
                    count++;
                }

            }
            if (count == 4)
            {
                System.out.println(credentials.Range + ip + "system connectable");

            } 
              else
            {
                

            }

        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception
    {
       int c=1;
        for (int i =1; i <= 17; i++)
        {
            Thread t[]=new Thread[15];
            for(int j=0;j<15;j++)
            {
            Job obj = new Job(c);
             t[j] = new Thread(obj);
             
            t[j].start();
             c++;
            }
            for(int j=0; j<15; j++)
            {
                try
                {
                    
                
              t[j].join();
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            System.out.println(i+"slot completed");
        }
        
    }

}
