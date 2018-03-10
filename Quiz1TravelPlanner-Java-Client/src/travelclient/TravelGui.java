/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelclient;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author 1795661
 */
public class TravelGui extends javax.swing.JFrame {

    DefaultListModel<String> modelTravelList = new DefaultListModel<>();
   // LibraryInt proxy;
    TravelProxy proxy;
    public TravelGui() {
        try {
            proxy = new TravelProxy();
            initComponents();
            reloadTravels();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection error: " + ex.getMessage());
            System.exit(1);
        }
    }
 private void reloadTravels() {
        try {
            proxy = new TravelProxy();
            String[] bookArray = proxy.getAllBooks();
            // clear all items in JList
            modelTravelList.clear();
            // put shouts into JList
            for (String s : bookArray) {
                modelTravelList.addElement(s);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "API error: " + ex.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jlTravel = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfPassport = new javax.swing.JTextField();
        tfCost = new javax.swing.JTextField();
        btAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlTravel.setModel(modelTravelList);
        jScrollPane1.setViewportView(jlTravel);

        jLabel1.setText("Name");

        jLabel2.setText("Passport");

        jLabel3.setText("Cost");

        btAdd.setText("Add");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfName)
                    .addComponent(tfPassport)
                    .addComponent(tfCost)
                    .addComponent(btAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfPassport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btAdd))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        try{
            String name = tfName.getText();
            if (name.isEmpty() || name.length()>50){
                JOptionPane.showMessageDialog(null, "Name must be between 0 and 50 chars");
                return;
            }
            String regex ="[a-zA-Z]{2}\\d{6}";
            Pattern pattern = Pattern.compile(regex);
            
            String passport = tfPassport.getText();
            if (!pattern.matcher(passport).matches()){
                JOptionPane.showMessageDialog(null, "Passport format AA123456");
                return;
            }
            double cost=-1;
            try{
                cost = Double.parseDouble(tfCost.getText());
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "enter numeric value", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(cost<0 || cost>1000000){
                JOptionPane.showMessageDialog(null, "Cost 0-1000000");
                return;
            }
            proxy.addTravel(name, passport, cost);
            reloadTravels();
        }catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "API error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btAddActionPerformed

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
            java.util.logging.Logger.getLogger(TravelGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TravelGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TravelGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TravelGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TravelGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> jlTravel;
    private javax.swing.JTextField tfCost;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPassport;
    // End of variables declaration//GEN-END:variables
}
