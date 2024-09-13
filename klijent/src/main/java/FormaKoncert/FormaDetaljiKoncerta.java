package FormaKoncert;

import domen.Izvodjac;
import domen.Koncert;
import domen.Muzicar;
import domen.Sponzor;
import kontroler.Komunikacija;
import forme.KlijentskaForma;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import modeli.ModelTabeleIzvodjaci;
import java.sql.Timestamp;

/**
 * FormaDetaljiKoncerta je dijalog forma koja prikazuje detalje o izabranom koncertu.
 * Omogućava pregled, izmenu i brisanje koncerta, kao i rad sa izvođačima koncerta.
 * 
 * @author Ranko
 */
public class FormaDetaljiKoncerta extends javax.swing.JDialog {
    
    private Koncert koncert;
    /**
     * Kreira novu formu za prikaz detalja koncerta.
     * Inicijalizuje polja forme sa podacima o koncertu i onemogućava izmenu ako je koncert završen.
     * 
     * @param parent Nadređeni dijalog
     * @param modal Da li je dijalog modalni
     * @param koncert Koncert čiji se detalji prikazuju
     */
    public FormaDetaljiKoncerta(JDialog parent, boolean modal, Koncert koncert) {
        super(parent, modal);
        initComponents();
        this.koncert = koncert;
        setLocationRelativeTo(null);
        setTitle("Detalji koncerta");
        txtKapacitet.setText(String.valueOf(koncert.getKapacitetKoncerta()));
        cmbBina.getModel().setSelectedItem(koncert.getBina());
        cmbBina.setEnabled(false);
        popuniSponzore();
        popuniMuzicare();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        txtDatumKraja.setValue(koncert.getDatumZavrsetka());
        txtDatumPocetka.setValue(koncert.getDatumPocetka());

         
       txtDatumKraja.repaint();
        txtDatumPocetka.repaint();

         
        // txtDatumPocetka.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
        // new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))));
        // txtDatumKraja.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
        // new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))));
        
        txtDatumPocetka.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
         new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))));

         txtDatumKraja.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
         new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))));
        
        tblIzvodjaci.setModel(new ModelTabeleIzvodjaci(koncert));
        
        if (!koncert.getDatumZavrsetka().after(new Date())) {
            cmbSponzor.setEnabled(false);
            txtDatumKraja.setEditable(false);
            txtDatumPocetka.setEditable(false);
            txtNapomena.setEditable(false);
            cmbMuzicar.setEnabled(false);
            btnDodajMuzicara.setEnabled(false);
            btnIzmeni.setEnabled(false);
            btnObrisiMuzicara.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Ovaj koncert je završen!");
        }
        
    }


    /**
     * Inicijalizacija komponenti forme.
     * Ovaj metod se automatski generiše i ne treba ga ručno menjati.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbBina = new javax.swing.JComboBox();
        cmbSponzor = new javax.swing.JComboBox();
        txtDatumPocetka = new javax.swing.JFormattedTextField();
        txtDatumKraja = new javax.swing.JFormattedTextField();
        txtKapacitet = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbMuzicar = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNapomena = new javax.swing.JTextArea();
        btnDodajMuzicara = new javax.swing.JButton();
        btnObrisiMuzicara = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblIzvodjaci = new javax.swing.JTable();
        btnZatvori = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Koncert"));

        jLabel1.setText("Bina:");

        jLabel2.setText("Sponzor:");

        jLabel4.setText("Vreme pocetka:");

        jLabel5.setText("Vreme zavrsetka:");

        jLabel6.setText("Maksimalni kapacitet:");

        cmbBina.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbSponzor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtDatumPocetka.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd.MM.yyyy"))));
        txtDatumPocetka.setText("2025-12-12 20:00:00");
        txtDatumPocetka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatumPocetkaActionPerformed(evt);
            }
        });

        txtDatumKraja.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd.MM.yyyy"))));
        txtDatumKraja.setText("2025-12-12 20:00:00");

        txtKapacitet.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Izvođači"));

        jLabel7.setText("Muzičar:");

        jLabel8.setText("Napomena:");

        cmbMuzicar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtNapomena.setColumns(20);
        txtNapomena.setRows(5);
        jScrollPane1.setViewportView(txtNapomena);

        btnDodajMuzicara.setBackground(new java.awt.Color(102, 255, 102));
        btnDodajMuzicara.setText("Dodaj izvođača");
        btnDodajMuzicara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajMuzicaraActionPerformed(evt);
            }
        });

        btnObrisiMuzicara.setBackground(new java.awt.Color(252, 77, 77));
        btnObrisiMuzicara.setText("Obriši izvođača");
        btnObrisiMuzicara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiMuzicaraActionPerformed(evt);
            }
        });

        tblIzvodjaci.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblIzvodjaci);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1))
                            .addComponent(cmbMuzicar, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnDodajMuzicara, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(122, 122, 122)
                                .addComponent(btnObrisiMuzicara, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbMuzicar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDodajMuzicara)
                    .addComponent(btnObrisiMuzicara))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnZatvori.setBackground(new java.awt.Color(204, 255, 255));
        btnZatvori.setText("Zatvori");
        btnZatvori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZatvoriActionPerformed(evt);
            }
        });

        btnObrisi.setBackground(new java.awt.Color(255, 51, 51));
        btnObrisi.setText("Obrisi koncert");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnIzmeni.setBackground(new java.awt.Color(153, 255, 102));
        btnIzmeni.setText("Izmeni koncert");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnZatvori, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbBina, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbSponzor, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDatumKraja)
                            .addComponent(txtDatumPocetka)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtKapacitet, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(312, 312, 312)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnIzmeni, btnObrisi, btnZatvori});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(cmbBina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDatumPocetka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDatumKraja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmbSponzor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtKapacitet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnZatvori)
                    .addComponent(btnObrisi)
                    .addComponent(btnIzmeni))
                .addContainerGap(18, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        
    /**
     * Metoda koja se poziva prilikom klika na dugme za zatvaranje forme.
     * Zatvara trenutnu formu.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnZatvoriActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.dispose();
    }                                          
    /**
     * Metoda koja se poziva prilikom klika na dugme za brisanje koncerta.
     * Briše selektovani koncert i osvežava tabelu koncerata u formi pretrage.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
        int result = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da "
                + "obrisete ovaj koncert?", "Konfirmacija", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.NO_OPTION) {
            return;
        }
        
        if (result == JOptionPane.YES_OPTION) {
            try {
                Komunikacija.getInstance().obrisiKoncert(koncert);
                FormaPretragaKoncerta fp = (FormaPretragaKoncerta) getParent();
                fp.refreshTable();
                JOptionPane.showMessageDialog(this, "Sistem je obrisao koncert");
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise koncert");
                Logger.getLogger(FormaDetaljiKoncerta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }                                         
    /**
     * Metoda koja se poziva prilikom klika na dugme za izmenu koncerta.
     * Menja podatke o koncertu i osvežava prikaz u formi pretrage.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
        try {
            
            if (txtDatumKraja.getText().isEmpty() || txtDatumPocetka.getText().isEmpty()
                    || txtKapacitet.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena!");
                return;
            }
            
            Sponzor sponzor = (Sponzor) cmbSponzor.getSelectedItem();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = sdf.parse(txtDatumPocetka.getText());
            Timestamp datumPocetka = new Timestamp(parsedDate.getTime());
            Date parsedAgain = sdf.parse(txtDatumKraja.getText());
            Timestamp datumZavrsetka = new Timestamp(parsedAgain.getTime());
            int kapacitet = Integer.parseInt(txtKapacitet.getText());
            
            ModelTabeleIzvodjaci tm = (ModelTabeleIzvodjaci) tblIzvodjaci.getModel();
            
            koncert.setSponzor(sponzor);
            koncert.setDatumPocetka(datumPocetka);
            koncert.setDatumZavrsetka(datumZavrsetka);
            koncert.setIzvodjaci(tm.getLista());
            koncert.setKapacitetKoncerta(kapacitet);
            
            Komunikacija.getInstance().izmeniKoncert(koncert);
            FormaPretragaKoncerta fp = (FormaPretragaKoncerta) getParent();
            fp.refreshTable();
            JOptionPane.showMessageDialog(this, "Sistem je izmenio koncert.");
            this.dispose();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da izmeni koncert.");
            JOptionPane.showMessageDialog(this, ex.getMessage());
//            Logger.getLogger(FormDetaljiKoncerta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }                                         
    /**
     * Metoda koja se poziva prilikom klika na dugme za dodavanje muzičara.
     * Dodaje muzičara u listu izvođača koncerta.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnDodajMuzicaraActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        
        Muzicar muzicar = (Muzicar) cmbMuzicar.getSelectedItem();
        String napomena = txtNapomena.getText();
        
        if (napomena.isEmpty()) {
            napomena = "/";
        }
        
        Izvodjac i = new Izvodjac(koncert, -1, napomena, muzicar);
        
        ModelTabeleIzvodjaci tm = (ModelTabeleIzvodjaci) tblIzvodjaci.getModel();
        
        if (tm.postojiIzvodjac(muzicar)) {
            JOptionPane.showMessageDialog(this, "Vec ste uneli ovog izvođača!");
            return;
        }
        
        tm.dodajIzvodjaca(i);
    }                                                
    /**
     * Metoda koja se poziva prilikom klika na dugme za brisanje muzičara.
     * Briše selektovanog muzičara iz liste izvođača koncerta.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnObrisiMuzicaraActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        
        int row = tblIzvodjaci.getSelectedRow();
        
        if (row >= 0) {
            ModelTabeleIzvodjaci tm = (ModelTabeleIzvodjaci) tblIzvodjaci.getModel();
            tm.obrisiIzvodjaca(row);
        }
    }                                                 

    private void txtDatumPocetkaActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnDodajMuzicara;
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnObrisiMuzicara;
    private javax.swing.JButton btnZatvori;
    private javax.swing.JComboBox cmbBina;
    private javax.swing.JComboBox cmbMuzicar;
    private javax.swing.JComboBox cmbSponzor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblIzvodjaci;
    private javax.swing.JFormattedTextField txtDatumKraja;
    private javax.swing.JFormattedTextField txtDatumPocetka;
    private javax.swing.JFormattedTextField txtKapacitet;
    private javax.swing.JTextArea txtNapomena;
    // End of variables declaration                   

    private void popuniSponzore() {
        try {
            ArrayList<Sponzor> sponzori = Komunikacija.getInstance().vratiSponzore();
            
            cmbSponzor.removeAllItems();
            
            for (Sponzor sponzor : sponzori) {
                cmbSponzor.addItem(sponzor);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(KlijentskaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void popuniMuzicare() {
        try {
            ArrayList<Muzicar> muzicari = Komunikacija.getInstance().vratiMuzicare();
            
            cmbMuzicar.removeAllItems();
            
            for (Muzicar muzicar : muzicari) {
                cmbMuzicar.addItem(muzicar);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(KlijentskaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
