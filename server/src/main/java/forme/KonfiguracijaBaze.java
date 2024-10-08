package forme;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Klasa KonfiguracijaBaze predstavlja dijalog za podešavanje konfiguracije baze podataka.
 * Omogućava korisnicima unos parametara kao što su naziv baze, korisničko ime i lozinka.
 * Podaci se čuvaju u fajlu dbconfig.properties.
 * 
 * @author Ranko
 */
public class KonfiguracijaBaze extends javax.swing.JDialog {

	/**
     * Kreira novu formu KonfiguracijaBaze i postavlja dijalog na sredinu ekrana.
     * 
     * @param parent Roditeljski okvir
     * @param modal Da li je dijalog modalan
     */
    public KonfiguracijaBaze(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Konfiguracija baze");
        popuniPolja();
    }

    /**
     * Metoda za inicijalizaciju komponenti GUI-a.
     * Ova metoda se automatski generiše od strane Form Editora i ne sme se 
     * ručno menjati.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNazivBaze = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        btnSacuvaj = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Unos konfiguracije"));

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        jLabel3.setText("Naziv baze:");

        btnSacuvaj.setBackground(new java.awt.Color(51, 255, 51));
        btnSacuvaj.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSacuvaj.setText("Sacuvaj konfiguraciju");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNazivBaze, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(txtUsername)
                    .addComponent(txtPassword))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNazivBaze, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnSacuvaj)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        
    /**
     * Akcija koja se izvršava kada korisnik pritisne dugme "Sačuvaj konfiguraciju".
     * Čuva unos u dbconfig.properties fajl.
     * 
     * @param evt Event koji pokreće akciju
     */
    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {                                           

        try {
            String nazivBaze = txtNazivBaze.getText();
            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());

            if (nazivBaze.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Morate uneti naziv baze!");
                return;
            }

            Properties prop = new Properties();
            FileOutputStream out = new FileOutputStream("dbconfig.properties");

            prop.setProperty("url", "jdbc:mysql://localhost:3306/" + nazivBaze);
            prop.setProperty("username", username);
            prop.setProperty("password", password);

            prop.store(out, null);

            JOptionPane.showMessageDialog(this, "Uspesno sacuvana konfiguracija.");
            this.dispose();

        } catch (IOException ex) {
            Logger.getLogger(KonfiguracijaBaze.class.getName()).log(Level.SEVERE, null, ex);
        }

    }                                          

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNazivBaze;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration                   
    /**
     * Metoda koja popunjava polja sa trenutnim podacima o bazi.
     */
    private void popuniPolja() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            txtNazivBaze.setText(url.substring(28));
            txtUsername.setText(username);
            txtPassword.setText(password);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(KonfiguracijaBaze.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KonfiguracijaBaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
