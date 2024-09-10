package forme;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import niti.PokreniServer;

/**
 * Klasa ServerskaForma predstavlja GUI formu za upravljanje serverom aplikacije.
 * Korisnik može pokrenuti ili zaustaviti server putem ove forme. Takođe, omogućava
 * pristup dijalogu za podešavanje baze podataka.
 * 
 * @author Ranko
 */
public class ServerskaForma extends javax.swing.JFrame {

	 /** Nit koja pokreće server */
    private PokreniServer threadServer;

    /**
     * Kreira novu formu ServerskaForma i inicijalizuje GUI.
     */
    public ServerskaForma() {
        initComponents();
        setLocationRelativeTo(null);
        lblServerStatus.setText("Server je trenutno ugasen!");
        btnPokreniServer.setEnabled(true);
        btnUgasiServer.setEnabled(false);
        setTitle("Serverska forma");
    }

    /**
     * Metoda za inicijalizaciju komponenti GUI-a.
     * Ova metoda se automatski generiše od strane Form Editora i ne sme se 
     * ručno menjati.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lblServerStatus = new javax.swing.JLabel();
        btnPokreniServer = new javax.swing.JButton();
        btnUgasiServer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miKonfiguracija = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 102));

        lblServerStatus.setBackground(new java.awt.Color(102, 0, 51));
        lblServerStatus.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        lblServerStatus.setForeground(new java.awt.Color(0, 51, 51));
        lblServerStatus.setText("Server status");

        btnPokreniServer.setBackground(new java.awt.Color(0, 255, 0));
        btnPokreniServer.setText("Pokreni server");
        btnPokreniServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokreniServerActionPerformed(evt);
            }
        });

        btnUgasiServer.setBackground(new java.awt.Color(255, 56, 56));
        btnUgasiServer.setText("Ugasi server");
        btnUgasiServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUgasiServerActionPerformed(evt);
            }
        });

        jLabel1.setText("Klikom mozete pokrenuti i ugasiti server.");

        jMenuBar1.setBackground(new java.awt.Color(0, 102, 51));
        jMenuBar1.setForeground(new java.awt.Color(102, 0, 0));

        jMenu1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jMenu1.setText("Konfiguracija baze");

        miKonfiguracija.setBackground(new java.awt.Color(153, 255, 51));
        miKonfiguracija.setText("Izmeni konfiguraciju");
        miKonfiguracija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miKonfiguracijaActionPerformed(evt);
            }
        });
        jMenu1.add(miKonfiguracija);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPokreniServer, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(btnUgasiServer, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(lblServerStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPokreniServer)
                    .addComponent(btnUgasiServer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblServerStatus)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        
    /**
     * Akcija koja se izvršava kada korisnik pritisne dugme "Pokreni server".
     * Pokreće novu nit servera.
     * 
     * @param evt Event koji pokreće akciju
     */
    private void btnPokreniServerActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        if (threadServer == null || !threadServer.isAlive()) {
            threadServer = new PokreniServer();
            threadServer.start();
            lblServerStatus.setText("Server je trenutno pokrenut!");
            btnUgasiServer.setEnabled(true);
            btnPokreniServer.setEnabled(false);
        }
    }                                                
    /**
     * Akcija koja se izvršava kada korisnik pritisne meni stavku "Izmeni konfiguraciju".
     * Otvara dijalog za podešavanje baze podataka.
     * 
     * @param evt Event koji pokreće akciju
     */
    private void miKonfiguracijaActionPerformed(java.awt.event.ActionEvent evt) {                                                
        new KonfiguracijaBaze(this, true).setVisible(true);
    }                                               
    /**
     * Akcija koja se izvršava kada korisnik pritisne dugme "Ugasi server".
     * Zaustavlja rad servera i zatvara aplikaciju.
     * 
     * @param evt Event koji pokreće akciju
     */
    private void btnUgasiServerActionPerformed(java.awt.event.ActionEvent evt) {                                               

        if (threadServer.getServerSocket() != null && threadServer.getServerSocket().isBound()) {
            try {
                threadServer.getServerSocket().close();
                JOptionPane.showMessageDialog(this, "Zaustavili ste server, gasi se aplikacija.");
                this.dispose();
                System.exit(0);
            } catch (IOException ex) {
                Logger.getLogger(ServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }                                              

    /**
     * Glavna metoda aplikacije koja pokreće serversku formu.
     * 
     * @param args Komandni argumenti
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
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerskaForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnPokreniServer;
    private javax.swing.JButton btnUgasiServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblServerStatus;
    private javax.swing.JMenuItem miKonfiguracija;
    // End of variables declaration                   
}
