package forme;

import FormaKoncert.FormaPretragaKoncerta;
import FormaMuzicar.FormaNoviMuzicar;
import FormaMuzicar.FormaPretragaMuzicara;
import kontroler.Komunikacija;
import domen.Administrator;
import domen.Bina;
import domen.Izvodjac;
import domen.Koncert;
import domen.Muzicar;
import domen.Sponzor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeli.ModelTabeleIzvodjaci;
import sesija.Sesija;
import java.sql.Timestamp;

/**
 * KlijentskaForma predstavlja glavnu formu koja se otvara nakon prijavljivanja.
 * Omogućava rad sa koncertima, muzičarima, i sponzorima.
 * Takođe omogućava dodavanje i pretragu izvođača na koncertima.
 * 
 * @author Ranko
 */
public class KlijentskaForma extends javax.swing.JFrame {

    Administrator ulogovani;

    /**
     * Kreira novu klijentsku formu.
     * Postavlja podatke o prijavljenom administratoru i inicijalizuje listu muzičara, bina i sponzora.
     */
    public KlijentskaForma() {
        initComponents();
        setLocationRelativeTo(null);
        this.ulogovani = Sesija.getInstance().getUlogovani();
        lblUlogovani.setText("Ulogovani administrator: " + ulogovani);
        setTitle("Klijentska forma");
        popuniBine();
        popuniSponzore();
        popuniMuzicare();
        
        txtVremePocetka.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))));
        txtVremeKraja.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))));
        
        tblIzvodjaci.setModel(new ModelTabeleIzvodjaci());

    }

    /**
     * Inicijalizacija komponenti forme.
     * Ovaj metod se automatski generiše i ne treba ga ručno menjati.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lblUlogovani = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbBina = new javax.swing.JComboBox();
        cmbSponzor = new javax.swing.JComboBox();
        txtVremePocetka = new javax.swing.JFormattedTextField();
        txtVremeKraja = new javax.swing.JFormattedTextField();
        txtKapacitet = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbMuzicar = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNapomena = new javax.swing.JTextArea();
        btnDodajUcenika = new javax.swing.JButton();
        btnObrisiUcenika = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblIzvodjaci = new javax.swing.JTable();
        btnSacuvaj = new javax.swing.JButton();
        btnOdjava = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        miNoviMuzičar = new javax.swing.JMenuItem();
        miPretragaMuzičara = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        miPretragaKoncerta = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(228, 228, 237));

        lblUlogovani.setText("Ulogovani");

        jPanel1.setBackground(new java.awt.Color(232, 232, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Koncert"));

        jLabel1.setText("Bina:");

        jLabel2.setText("Sponzor:");

        jLabel4.setText("Vreme pocetka:");

        jLabel5.setText("Vreme zavrsetka:");

        jLabel6.setText("Maksimalni kapacitet:");

        cmbBina.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbSponzor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtVremePocetka.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd.MM.yyyy"))));
        txtVremePocetka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVremePocetkaActionPerformed(evt);
            }
        });

        txtVremeKraja.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd.MM.yyyy"))));

        txtKapacitet.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtKapacitet.setText("3");

        jPanel2.setBackground(new java.awt.Color(198, 206, 215));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Izvođači"));

        jLabel7.setText("Muzičar:");

        jLabel8.setText("Napomena:");

        cmbMuzicar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbMuzicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMuzicarActionPerformed(evt);
            }
        });

        txtNapomena.setColumns(20);
        txtNapomena.setRows(5);
        jScrollPane1.setViewportView(txtNapomena);

        btnDodajUcenika.setBackground(new java.awt.Color(102, 255, 102));
        btnDodajUcenika.setText("Dodaj izvođača");
        btnDodajUcenika.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajUcenikaActionPerformed(evt);
            }
        });

        btnObrisiUcenika.setBackground(new java.awt.Color(255, 53, 53));
        btnObrisiUcenika.setText("Obriši izvođača");
        btnObrisiUcenika.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiUcenikaActionPerformed(evt);
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
                        .addGap(109, 109, 109)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnDodajUcenika, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnObrisiUcenika, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(btnObrisiUcenika)
                    .addComponent(btnDodajUcenika))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnSacuvaj.setBackground(new java.awt.Color(102, 255, 102));
        btnSacuvaj.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnSacuvaj.setText("Sacuvaj koncert");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(41, 41, 41)
                                        .addComponent(cmbBina, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbSponzor, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(61, 61, 61)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtVremePocetka, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVremeKraja, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(177, 177, 177)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtKapacitet, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(258, 258, 258))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(cmbBina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVremePocetka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVremeKraja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmbSponzor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKapacitet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnOdjava.setBackground(new java.awt.Color(195, 250, 250));
        btnOdjava.setText("Odjavi se");
        btnOdjava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOdjavaActionPerformed(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(216, 216, 242));

        jMenu6.setBackground(new java.awt.Color(255, 255, 204));
        jMenu6.setText("Muzičar");

        miNoviMuzičar.setText("Novi muzičar");
        miNoviMuzičar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNoviMuzičarActionPerformed(evt);
            }
        });
        jMenu6.add(miNoviMuzičar);

        miPretragaMuzičara.setText("Pretraga muzičara");
        miPretragaMuzičara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPretragaMuzičaraActionPerformed(evt);
            }
        });
        jMenu6.add(miPretragaMuzičara);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Koncert");

        miPretragaKoncerta.setText("Pretraga koncerta");
        miPretragaKoncerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPretragaKoncertaActionPerformed(evt);
            }
        });
        jMenu7.add(miPretragaKoncerta);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblUlogovani)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOdjava)
                        .addGap(78, 78, 78))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUlogovani)
                    .addComponent(btnOdjava))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void miNoviMuzičarActionPerformed(java.awt.event.ActionEvent evt) {                                              
        new FormaNoviMuzicar(this, true).setVisible(true);
    }                                             

    private void miPretragaMuzičaraActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        new FormaPretragaMuzicara(this, true).setVisible(true);
    }                                                  

    private void miPretragaKoncertaActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        new FormaPretragaKoncerta(this, true).setVisible(true);
    }                                                  
    /**
     * Metoda koja se poziva prilikom klika na dugme za dodavanje izvođača.
     * Dodaje izvođača u tabelu izvođača.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnDodajUcenikaActionPerformed(java.awt.event.ActionEvent evt) {                                                

        Muzicar muzicar = (Muzicar) cmbMuzicar.getSelectedItem();
        String napomena = txtNapomena.getText();

        if (napomena.isEmpty()) {
            napomena = "/";
        }

        Izvodjac i = new Izvodjac(null, -1, napomena, muzicar);

        ModelTabeleIzvodjaci tm = (ModelTabeleIzvodjaci) tblIzvodjaci.getModel();

        if (tm.postojiIzvodjac(muzicar)) {
            JOptionPane.showMessageDialog(this, "Vec ste uneli ovog izvođača!");
            return;
        }

        tm.dodajIzvodjaca(i);

    }                                               
    /**
     * Metoda koja se poziva prilikom klika na dugme za brisanje izvođača.
     * Briše selektovanog izvođača iz tabele.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnObrisiUcenikaActionPerformed(java.awt.event.ActionEvent evt) {                                                 

        int row = tblIzvodjaci.getSelectedRow();

        if (row >= 0) {
            ModelTabeleIzvodjaci tm = (ModelTabeleIzvodjaci) tblIzvodjaci.getModel();
            tm.obrisiIzvodjaca(row);
        }

    }                                                
    /**
     * Metoda koja se poziva prilikom klika na dugme za čuvanje koncerta.
     * Proverava popunjene podatke i pokušava sačuvati koncert.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {                                           

        try {
            if (txtVremeKraja.getText().isEmpty() || txtVremePocetka.getText().isEmpty()
                    || txtKapacitet.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena!");
                return;
            }

            Bina bina = (Bina) cmbBina.getSelectedItem();
            Sponzor sponzor = (Sponzor) cmbSponzor.getSelectedItem();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = sdf.parse(txtVremePocetka.getText());
            Timestamp vremePocetka = new Timestamp(parsedDate.getTime());
            Date parsedAgain = sdf.parse(txtVremeKraja.getText());
            Timestamp vremeZavrsetka = new Timestamp(parsedAgain.getTime());
            int kapacitet = Integer.parseInt(txtKapacitet.getText());

            ModelTabeleIzvodjaci tm = (ModelTabeleIzvodjaci) tblIzvodjaci.getModel();

            Koncert k = new Koncert(null, vremePocetka, vremeZavrsetka, kapacitet,
                    sponzor, bina, ulogovani, tm.getLista());


            Komunikacija.getInstance().dodajKoncert(k);
            txtVremeKraja.setText("");
            txtVremePocetka.setText("");
            txtKapacitet.setText("");
            txtNapomena.setText("");
            tm.getLista().clear();
            tm.fireTableDataChanged();
            JOptionPane.showMessageDialog(this, "Sistem je kreirao koncert.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da kreira koncert.");
            JOptionPane.showMessageDialog(this, ex.getMessage());
//            Logger.getLogger(KlijentskaForma.class.getName()).log(Level.SEVERE, null, ex);
        }

    }                                          
    /**
     * Metoda koja se poziva prilikom klika na dugme za odjavu.
     * Odjavljuje korisnika i vraća na login formu.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnOdjavaActionPerformed(java.awt.event.ActionEvent evt) {                                          
          int result = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da "
            + "se odjavite?", "Konfirmacija", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.NO_OPTION) {
            return;
        }

        if (result == JOptionPane.YES_OPTION) {
            new LoginForma().setVisible(true);
            Sesija.getInstance().setUlogovani(null);
            this.dispose();
        }
    }                                         

    private void cmbMuzicarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void txtVremePocetkaActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnDodajUcenika;
    private javax.swing.JButton btnObrisiUcenika;
    private javax.swing.JButton btnOdjava;
    private javax.swing.JButton btnSacuvaj;
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
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblUlogovani;
    private javax.swing.JMenuItem miNoviMuzičar;
    private javax.swing.JMenuItem miPretragaKoncerta;
    private javax.swing.JMenuItem miPretragaMuzičara;
    private javax.swing.JTable tblIzvodjaci;
    private javax.swing.JFormattedTextField txtKapacitet;
    private javax.swing.JTextArea txtNapomena;
    private javax.swing.JFormattedTextField txtVremeKraja;
    private javax.swing.JFormattedTextField txtVremePocetka;
    // End of variables declaration                   

    private void popuniBine() {
        try {
            ArrayList<Bina> bine = Komunikacija.getInstance().vratiBine();

            cmbBina.removeAllItems();

            for (Bina bina : bine) {
                cmbBina.addItem(bina);
            }

        } catch (Exception ex) {
            Logger.getLogger(KlijentskaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    public void popuniMuzicare() {
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

