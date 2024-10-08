package FormaMuzicar;

import domen.Muzicar;
import kontroler.Komunikacija;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * FormaDetaljiMuzicara je dijalog forma koja prikazuje i omogućava izmenu ili brisanje
 * podataka o muzičaru. Prikazuje informacije poput imena, instrumenta, žanra i kontakta.
 * 
 * @author Ranko
 */
public class FormaDetaljiMuzicara extends javax.swing.JDialog {

    private Muzicar m;
    /**
     * Kreira novu formu za prikaz detalja o muzičaru.
     * 
     * @param parent Nadređeni prozor
     * @param modal Da li je dijalog modalni
     * @param m Muzičar čiji se detalji prikazuju
     */
    public FormaDetaljiMuzicara(JDialog parent, boolean modal, Muzicar m) {
        super(parent, modal);
        initComponents();
        this.m = m;
        setLocationRelativeTo(null);
        setTitle("Detalji muzicara");
        txtIme.setText(m.getIme());
        txtInstrument.setText(m.getInstrument());
        txtZanr.setText(m.getZanr());
        txtKontakt.setText(m.getKontakt());
        txtIme.setEnabled(false);
    }

    /**
     * Inicijalizacija komponenti forme.
     * Ovaj metod je automatski generisan.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIme = new javax.swing.JTextField();
        txtInstrument = new javax.swing.JTextField();
        btnZatvori = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtZanr = new javax.swing.JTextField();
        txtKontakt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel4.setText("Ime:");

        jLabel5.setText("Instrument:");

        txtIme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImeActionPerformed(evt);
            }
        });

        btnZatvori.setBackground(new java.awt.Color(204, 255, 255));
        btnZatvori.setText("Zatvori");
        btnZatvori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZatvoriActionPerformed(evt);
            }
        });

        btnObrisi.setBackground(new java.awt.Color(255, 85, 85));
        btnObrisi.setText("Obrisi muzičara");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnIzmeni.setBackground(new java.awt.Color(102, 255, 102));
        btnIzmeni.setText("Izmeni muzičara");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        jLabel8.setText("Zanr:");

        txtZanr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtZanrActionPerformed(evt);
            }
        });

        txtKontakt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKontaktActionPerformed(evt);
            }
        });

        jLabel9.setText("Kontakt:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(48, 48, 48)
                        .addComponent(txtIme))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtZanr)
                            .addComponent(txtKontakt)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(12, 12, 12)
                            .addComponent(txtInstrument))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnZatvori, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnIzmeni, btnObrisi, btnZatvori});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInstrument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtZanr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKontakt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnObrisi)
                    .addComponent(btnZatvori)
                    .addComponent(btnIzmeni))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        
    /**
     * Zatvara dijalog bez promene podataka.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnZatvoriActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.dispose();
    }                                          
    /**
     * Briše izabranog muzičara iz sistema.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {                                          

        int result = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da "
                + "obrisete ovog muzičara?", "Konfirmacija", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.NO_OPTION) {
            return;
        }

        if (result == JOptionPane.YES_OPTION) {
            try {
                Komunikacija.getInstance().obrisiMuzicara(m);
                FormaPretragaMuzicara fp = (FormaPretragaMuzicara) getParent();
                fp.refreshTable();
                fp.popuniMuzicare();
                JOptionPane.showMessageDialog(this, "Sistem je obrisao polaznika.");
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ne mozete da obrisete ovog polaznika!");
                Logger.getLogger(FormaDetaljiMuzicara.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }                                         
    /**
     * Izmenjuje podatke o muzičaru.
     * Ažurira bazu podataka sa novim informacijama.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {                                          

        try {

            if (txtZanr.getText().isEmpty() || txtKontakt.getText().isEmpty() || txtInstrument.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena!");
                return;
            }

            String instrument = txtInstrument.getText();
            String zanr = txtZanr.getText();
            String kontakt = txtKontakt.getText();

            m.setInstrument(instrument);
            m.setZanr(zanr);
            m.setKontakt(kontakt);

            Komunikacija.getInstance().izmeniMuzicara(m);
            FormaPretragaMuzicara fp = (FormaPretragaMuzicara) getParent();
            fp.refreshTable();
            fp.popuniMuzicare();
            JOptionPane.showMessageDialog(this, "Sistem je izmenio podatke "
                    + "muzicara.");
            this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
//            Logger.getLogger(FormaDetaljiPolaznika.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnZatvori;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtInstrument;
    private javax.swing.JTextField txtKontakt;
    private javax.swing.JTextField txtZanr;
    // End of variables declaration                   

}
