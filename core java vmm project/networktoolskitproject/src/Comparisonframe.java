
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author simrat kaur gill
 */
public class Comparisonframe extends javax.swing.JFrame {

    mytablemodel tm;
    ArrayList<PCinfo> al2;
        ArrayList<PCinfo> alsel=new ArrayList<>();
    public Comparisonframe(ArrayList<PCinfo> al2) {
        initComponents();
        this.al2 = al2;
        setSize(700,700);
        
        
        tm = new mytablemodel();
        jt1.setModel(tm);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jt1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        bt1 = new javax.swing.JButton();
        bt2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

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
        jScrollPane1.setBounds(116, 81, 452, 402);

        jLabel1.setText("Select systems to Compare");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(116, 33, 326, 20);

        bt1.setText("select all");
        bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt1ActionPerformed(evt);
            }
        });
        getContentPane().add(bt1);
        bt1.setBounds(150, 550, 140, 30);

        bt2.setText("deselect all");
        bt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt2ActionPerformed(evt);
            }
        });
        getContentPane().add(bt2);
        bt2.setBounds(350, 550, 140, 30);

        jButton1.setText("Continue");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(253, 620, 110, 29);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt1ActionPerformed
        for (int i = 0; i < al2.size(); i++) {
            al2.get(i).cb = true;
              tm.fireTableDataChanged();
        }
    }//GEN-LAST:event_bt1ActionPerformed

    private void bt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt2ActionPerformed
        for (int i = 0; i < al2.size(); i++) {
            al2.get(i).cb = false;
              tm.fireTableDataChanged();
        }
    }//GEN-LAST:event_bt2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        alsel.clear();
        for(int i=0; i< al2.size(); i++)
        {
            if(al2.get(i).cb==true)
            {
             alsel.add(al2.get(i));
            }
            
    }
        if(alsel.size()<2||alsel.size()>=5)
        {
            JOptionPane.showMessageDialog(this,alsel.size()+"systems are selected which is out of range ");
        }
        else
        {
            int val = JOptionPane.showConfirmDialog(this,alsel.size()+ "systems selected doyou wish to continue");
            if(val == JOptionPane.OK_OPTION){
                Comparison2 obj = new Comparison2(alsel);
            }
            
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Comparisonframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comparisonframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comparisonframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comparisonframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //              new Comparisonframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt1;
    private javax.swing.JButton bt2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt1;
    // End of variables declaration//GEN-END:variables
  class mytablemodel extends AbstractTableModel {

        String c[] = {"Select", "ip", "pcname"};

        @Override
        public int getRowCount() {

            return al2.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public String getColumnName(int column) {

            return c[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            PCinfo pc = al2.get(rowIndex);
            if (columnIndex == 0) {
                return al2.get(rowIndex).cb;
            } else if (columnIndex == 1) {
                return pc.ip;
            } else {
                return pc.pcname;
            }
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public void setValueAt(Object value, int row, int col) {
            al2.get(row).cb = (Boolean) (value);
            fireTableCellUpdated(row, col);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return true;
            } else {
                return false;

            }
        }
    }
}
