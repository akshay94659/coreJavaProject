
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class MainController extends javax.swing.JFrame 
{
    
    ArrayList<String> al = new ArrayList<>();
    mytablemodel tm;
    ArrayList<PCinfo> al2=new ArrayList<>();
    mytablemodel2 tm2;
    SystemTray tray = SystemTray.getSystemTray();
    TrayIcon trayIcon;
    
    
    public MainController()
    {
        
        initComponents();
        setSize(700,700);
        tm= new mytablemodel();
        jt1.setModel(tm);
        tm2=new mytablemodel2();
        jt2.setModel(tm2);
        pb.setString("25%complete");
        add(pb);
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }

        PopupMenu pop = new PopupMenu();

        MenuItem mi1 = new MenuItem("Open");
        MenuItem mi2 = new MenuItem("Exit");

        pop.add(mi1);
        pop.add(mi2);

        mi1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                MainController.this.setExtendedState(NORMAL);
                MainController.this.setVisible(true);
            }
        });
        mi2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                String r = JOptionPane.showInputDialog("Enter Password");
                
                if(r.equals("4444"))
                {
                    MainController.this.dispose();
                    System.exit(0);
                }
                else
                {
                    JOptionPane.showMessageDialog(MainController.this, "Incorrect password");
                }
                
            }
        });
        
        trayIcon = new TrayIcon(new ImageIcon(getClass().getResource("java.jpg")).getImage(), "MainController", pop);

        
        
    }
                  
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt1 = new javax.swing.JTable();
        lb = new javax.swing.JLabel();
        jb1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt2 = new javax.swing.JTable();
        jb3 = new javax.swing.JButton();
        jb4 = new javax.swing.JButton();
        pb = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        bt.setText("detect");
        bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActionPerformed(evt);
            }
        });
        getContentPane().add(bt);
        bt.setBounds(48, 42, 165, 55);

        jt1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jt1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 140, 320, 370);

        lb.setText("list");
        getContentPane().add(lb);
        lb.setBounds(50, 110, 240, 20);

        jb1.setText("filter");
        jb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb1ActionPerformed(evt);
            }
        });
        getContentPane().add(jb1);
        jb1.setBounds(530, 40, 150, 60);

        jt2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jt2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(462, 142, 370, 370);

        jb3.setText("Explore");
        jb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb3ActionPerformed(evt);
            }
        });
        getContentPane().add(jb3);
        jb3.setBounds(610, 549, 85, 30);

        jb4.setText("compare");
        jb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb4ActionPerformed(evt);
            }
        });
        getContentPane().add(jb4);
        jb4.setBounds(610, 600, 93, 29);

        pb.setForeground(new java.awt.Color(102, 255, 102));
        getContentPane().add(pb);
        pb.setBounds(120, 560, 160, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActionPerformed

        Detect obj=new Detect();
        Thread t=new Thread(obj);
        t.start();
        
        
    }//GEN-LAST:event_btActionPerformed

    private void jb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb1ActionPerformed
     for(int i=0;i<al.size();i++)
     {
         client cl=new client(al.get(i));
         System.out.println(al.get(i));
         Thread t=new Thread(cl);
         t.start();
     }
    }//GEN-LAST:event_jb1ActionPerformed

    private void jb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb3ActionPerformed
        Explore obj=new Explore(al2);
        obj.setVisible(true);
    }//GEN-LAST:event_jb3ActionPerformed

    private void jb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb4ActionPerformed
       Comparisonframe cm=new Comparisonframe(al2);
       cm.setVisible(true);
    }//GEN-LAST:event_jb4ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try
        {
        tray.add(trayIcon);
        }
        catch (AWTException e) 
        {
            System.out.println("TrayIcon could not be added.");
        }
        
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainController().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jb1;
    private javax.swing.JButton jb3;
    private javax.swing.JButton jb4;
    private javax.swing.JTable jt1;
    private javax.swing.JTable jt2;
    private javax.swing.JLabel lb;
    public javax.swing.JProgressBar pb;
    // End of variables declaration//GEN-END:variables

    class Detect implements Runnable
    {

        @Override
        public void run()
        {

            int c = 1;
//            for (int i = 1; i <= 17; i++) 
//            {
//                Thread t[] = new Thread[15];
//                for (int j = 0; j < 15; j++)
//                {
//                    Job obj = new Job(c);
//                    t[j] = new Thread(obj);
//
//                    t[j].start();
//                    c++;
//                }
//                for (int j = 0; j < 15; j++)
//                {
//                    try 
//                    {
//
//                        t[j].join();
//                    }
//                    catch (Exception ex)
//                    {
//                        ex.printStackTrace();
//                    }
//                }
//                System.out.println(i + "slot completed");
//            }

            for(int i=0; i<250; i++){
            

               
                Job obj = new Job(i);
                Thread th = new Thread(obj);
                th.start();
         
               pb. setValue(i);
               pb.setString(i+"%");
            }

        }

    }



     class Job implements Runnable
     {
        int ip;
        
        

        Job(int ip)
        {
           this.ip = ip;
        }

         public void run() 
         {

            try {
            Process p = Runtime.getRuntime().exec("ping " + credentials.Range + ip);
            DataInputStream dis = new DataInputStream(p.getInputStream());
            int count = 0;

            while (true) {
                String s = dis.readLine();
                if (s == null) {
                    break;
                } else if (s.contains("TTL")) {
                    count++;
                }

            }
            if (count == 4) {
                System.out.println(credentials.Range + ip + "system connectable");
                al.add(credentials.Range+ip);
                tm.fireTableDataChanged();
                int l=al.size();
                lb.setText(l+"system(s) connectable");
            } else {

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
     
     class mytablemodel extends AbstractTableModel
     {
         String name []= {"ip adress"};
         
        @Override
        public String getColumnName(int column) {
            return name[column];
        }

         
         
        @Override
        public int getRowCount() {
            return al.size();
        }
        @Override
        public int getColumnCount() {
            return 1;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return al.get(rowIndex);
        }
     }



class client implements Runnable 
{
    String ip;
    
   client(String ip)
   {
       this.ip=ip;
   }
    @Override
    public void run() 
    {
        try
        {
           
          Socket sock=new Socket(ip,9000);
          DataOutputStream dos=new DataOutputStream(sock.getOutputStream());
          DataInputStream dis=new DataInputStream(sock.getInputStream());
  
  
          dos.writeBytes("hello server\r\n");
  
           String s=dis.readLine();
            System.out.println(s);
            String pcname= sock.getInetAddress().getHostName();
            al2.add(new PCinfo(ip,pcname));
           tm2.fireTableDataChanged();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
class mytablemodel2 extends AbstractTableModel
{     
    String c[]={"ip","pcname"};
        @Override
        public int getRowCount() {
             int n= al2.size();
            return n;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }
        @Override
        public String getColumnName(int column)
        {
            
            return c[column];
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            PCinfo pc=al2.get(rowIndex);
            if(columnIndex==0)
                return pc.ip;
            else
                return pc.pcname;
            
        }
    
}


}
