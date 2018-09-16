
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author simrat kaur gill
 */
public class mainserver extends javax.swing.JFrame {
    
        SystemTray tray = SystemTray.getSystemTray();
         TrayIcon trayIcon;


 
    public mainserver() {
        
        initComponents();
        setSize(700, 700);
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
                mainserver.this.setExtendedState(NORMAL);
                mainserver.this.setVisible(true);
            }
        });
        mi2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                String r = JOptionPane.showInputDialog("Enter Password");
                
                if(r.equals("4444"))
                {
                    mainserver.this.dispose();
                    System.exit(0);
                }
                else
                {
                    JOptionPane.showMessageDialog(mainserver.this, "Incorrect password");
                }
                
            }
        });
        
        trayIcon = new TrayIcon(new ImageIcon(getClass().getResource("java.jpg")).getImage(), "mainserver", pop);

        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jButton1.setText("server start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(40, 30, 180, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Server obj = new Server();
        Thread t = new Thread(obj);
        t.start();
        PhotoServer pobj = new PhotoServer();
        Thread t1 = new Thread(pobj);
        t1.start();

    }//GEN-LAST:event_jButton1ActionPerformed

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


       public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainserver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainserver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainserver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainserver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainserver().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables

    class Server implements Runnable {
        
        @Override
        public void run() {
            try {
                ServerSocket sersock = new ServerSocket(9000);
                System.out.println("9000");
                
                while (true) {
                    Socket sock = sersock.accept();
                    System.out.println("accepted");
                    Clienthandler obj = new Clienthandler(sock);
                    Thread t = new Thread(obj);
                    t.start();
                }
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        class Clienthandler implements Runnable {
            
            Socket sock;
            
            Clienthandler(Socket sock
            ) {
                this.sock = sock;
                
            }
            
            @Override
            public void run() {
                
                try {
                    DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                    DataInputStream dis = new DataInputStream(sock.getInputStream());
                    while (true) {
                        String s = dis.readLine();
                        if (s.equals("hello server")) {
                            dos.writeBytes("hello client\r\n");
                            System.out.println(s);
                        } else if (s.equals("Send Configuration")) {
                            System.out.println("in the configuration");
                            dos.writeBytes(InetAddress.getLocalHost().getHostAddress() + "\r\n");           //lb_ip
                            dos.writeBytes(InetAddress.getLocalHost().getHostName() + "\r\n");              //lb_pcname
                            dos.writeBytes(System.getProperty("os.name") + "\r\n");                         //lb_os
                            dos.writeBytes(Runtime.getRuntime().availableProcessors() + "\r\n");       //lb_processor
                            long memorySize = (((com.sun.management.OperatingSystemMXBean) ManagementFactory
                                    .getOperatingSystemMXBean()).getTotalPhysicalMemorySize());
                            dos.writeBytes(memorySize + "\r\n");                                            //lb_ram
                        } else if (s.equals("Shutdown")) {
                            Process p = Runtime.getRuntime().exec("shutdown -s -t 0");
                            
                        } else if (s.equals("Restart")) {
                            Process p = Runtime.getRuntime().exec("shutdown -r -t 0");
                            
                        } else if (s.equals("Logoff")) {
                            Process p = Runtime.getRuntime().exec("shutdown -l ");
                            
                        } else if (s.equals("View Screen")) {
                            int sw = Toolkit.getDefaultToolkit().getScreenSize().width;
                            int sh = Toolkit.getDefaultToolkit().getScreenSize().height;
                            dos.writeInt(sw);
                            dos.writeInt(sh);
                            
                        } else if (s.equals("mouse move")) {
                            
                            int x = dis.readInt();
                            int y = dis.readInt();
                            Robot r = new Robot();
                            r.mouseMove(x, y);
                            
                        } else if (s.equals("mouse clicked")) {
                            
                            int x = dis.readInt();
                            int y = dis.readInt();
                            int button = dis.readInt();
                            
                            Robot r = new Robot();
                            
                            r.mouseMove(x, y);
                            if (button == 1) {
                                r.mousePress(MouseEvent.BUTTON1_MASK);
                                r.mouseRelease(MouseEvent.BUTTON1_MASK);
                                
                            } else if (button == 2) {
                                r.mousePress(MouseEvent.BUTTON2_MASK);
                                r.mouseRelease(MouseEvent.BUTTON2_MASK);
                                
                            } else if (button == 3) {
                                r.mousePress(MouseEvent.BUTTON3_MASK);
                                r.mouseRelease(MouseEvent.BUTTON3_MASK);
                                
                            }
                            
                        } else if (s.equals("double click")) {
                            
                            int x = dis.readInt();
                            int y = dis.readInt();
                            
                            Robot rb = new Robot();
                            rb.mouseMove(x, y);
                            
                            rb.mousePress(MouseEvent.BUTTON1_MASK);
                            rb.mouseRelease(MouseEvent.BUTTON1_MASK);
                            rb.mousePress(MouseEvent.BUTTON1_MASK);
                            rb.mouseRelease(MouseEvent.BUTTON1_MASK);
                            
                            
                        } else if (s.equals("mouse dragged")) {
                            int x = dis.readInt();
                            int y = dis.readInt();
                            Robot r = new Robot();
                            r.mousePress(MouseEvent.BUTTON1_MASK);
                            r.mouseMove(x, y);
                            
                        } else if (s.equals("mouse released")) {
                            int button = dis.readInt();
                            Robot r = new Robot();
                            if (button == 1) {
                                
                                r.mouseRelease(MouseEvent.BUTTON1_MASK);
                            } else if (button == 2) {
                                r.mouseRelease(MouseEvent.BUTTON2_MASK);
                            } else {
                                r.mouseRelease(MouseEvent.BUTTON3_MASK);
                            }
                            
                        }
                        else if(s.equals("key pressed"))
                        {
                            int code = dis.readInt();
                            Robot rb = new Robot();
                            rb.keyPress(code);
                        }
                        else if(s.equals("key released"))
                        {
                            int code = dis.readInt();
                            Robot rb = new Robot();
                            rb.keyPress(code);
                        }
                        
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }
    }
    
    class PhotoServer implements Runnable {
        
        @Override
        public void run() {
            try {
                ServerSocket psersock = new ServerSocket(8500);
                System.out.println("8500");
                
                while (true) {
                    Socket psock = psersock.accept();
                    System.out.println("accepted1");
                    PhotoClienthandler obj = new PhotoClienthandler(psock);
                    Thread t = new Thread(obj);
                    t.start();
                }
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        class PhotoClienthandler implements Runnable {
            
            Socket psock;
            
            PhotoClienthandler(Socket psock
            ) {
                this.psock = psock;
                
            }
            
            @Override
            public void run() {
                
                try {
                    DataOutputStream pdos = new DataOutputStream(psock.getOutputStream());
                    DataInputStream pdis = new DataInputStream(psock.getInputStream());
                    while (true) {
                        String s = pdis.readLine();
                        System.out.println(s);
                        
                        Robot r = new Robot();

                        // It saves screenshot to desired path
                        String path = "Shot.jpg";

                        // Used to get ScreenSize and capture image
                        Rectangle capture
                                = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                        BufferedImage Image = r.createScreenCapture(capture);
                        ImageIO.write(Image, "jpg", new File("Shot.jpg"));
                        System.out.println("Screenshot saved");
                        
                        File f = new File("shot.jpg");
                        FileInputStream pfis = new FileInputStream(f);
                        pdos.writeLong(f.length());
                        byte b[] = new byte[10000];
                        int count = 0;
                        
                        while (true) {
                            int p;
                            p = pfis.read(b, 0, 10000);
                            
                            count = count + p;
                            pdos.write(b, 0, p);
                            if (count == f.length()) {
                                break;
                                
                            }
                            
                        }
                        String j = pdis.readLine();
                        System.out.println(j);
                    }
                    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
        
    }
}
