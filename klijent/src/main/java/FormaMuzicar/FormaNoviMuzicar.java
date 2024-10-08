package FormaMuzicar;

import domen.Muzicar;
import kontroler.Komunikacija;
import forme.KlijentskaForma;
import javax.swing.JOptionPane;

/**
 * FormaNoviMuzicar je dijalog forma za unos novog muzičara.
 * Omogućava korisniku da unese podatke o muzičaru i doda ga u sistem.
 * 
 * @author Ranko
 */
public class FormaNoviMuzicar extends javax.swing.JDialog {
	/**
     * Kreira novu formu za unos muzičara.
     * 
     * @param parent Nadređeni prozor
     * @param modal Da li je dijalog modalni
     */
    public FormaNoviMuzicar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Unos muzicara");
    }

    /**
     * Inicijalizacija komponenti forme.
     * Ovaj metod je automatski generisan.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtIme = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtInstrument = new javax.swing.JTextField();
        btnOtkazi = new javax.swing.JButton();
        btnDodaj = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtZanr = new javax.swing.JTextField();
        txtKontakt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Ime:");

        txtIme.setText("Ed Sheeran");
        txtIme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImeActionPerformed(evt);
            }
        });

        jLabel3.setText("Instrument:");

        txtInstrument.setText("Vokali, Gitara");
        txtInstrument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInstrumentActionPerformed(evt);
            }
        });

        btnOtkazi.setBackground(new java.awt.Color(204, 255, 255));
        btnOtkazi.setText("Zatvori");
        btnOtkazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOtkaziActionPerformed(evt);
            }
        });

        btnDodaj.setBackground(new java.awt.Color(51, 255, 51));
        btnDodaj.setText("Dodaj muzičara");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        jLabel8.setText("Žanr:");

        jLabel9.setText("Kontakt:");

        txtZanr.setText("Pop");
        txtZanr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtZanrActionPerformed(evt);
            }
        });

        txtKontakt.setText("esmgmt@universal.com");
        txtKontakt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKontaktActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOtkazi, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIme, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .addComponent(txtInstrument)
                            .addComponent(txtZanr)
                            .addComponent(txtKontakt))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInstrument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtZanr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKontakt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOtkazi)
                    .addComponent(btnDodaj))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        
    /**
     * Zatvara dijalog bez unosa novog muzičara.
     * 
     * @param evt Akcioni događaj
     */
    private void btnOtkaziActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.dispose();
    }                                         
    /**
     * Dodaje novog muzičara u sistem.
     * Unosi validiraju prazna polja i dodaje unete informacije u bazu.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            if (txtIme.getText().isEmpty() || txtInstrument.getText().isEmpty()
                    || txtZanr.getText().isEmpty()
                    || txtKontakt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da kreira novog "
                        + "muzicara.");
                return;
            }

            String ime = txtIme.getText();
            String instrument = txtInstrument.getText();
            String zanr = txtZanr.getText();
            String kontakt = txtKontakt.getText();

            Muzicar muzicar = new Muzicar(null, ime, instrument, zanr, kontakt);

            Komunikacija.getInstance().dodajMuzicara(muzicar);
            KlijentskaForma mf = (KlijentskaForma) getParent();
            mf.popuniMuzicare();
            JOptionPane.showMessageDialog(this, "Sistem je uspesno kreirao "
                    + "novog muzičara.");
            this.dispose();

        } catch (Exception ex) {
          //  JOptionPane.showMessageDialog(this, "Sistem ne moze da kreira novog "
           //         + "muzičara.");
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }                                        

    private void txtInstrumentActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void txtZanrActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void txtKontaktActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void txtImeActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnOtkazi;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtInstrument;
    private javax.swing.JTextField txtKontakt;
    private javax.swing.JTextField txtZanr;
    // End of variables declaration                   
}
