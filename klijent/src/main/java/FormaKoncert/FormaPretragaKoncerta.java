package FormaKoncert;

import domen.Koncert;
import kontroler.Komunikacija;

import javax.swing.JOptionPane;
import modeli.ModelTabeleKoncerti;

/**
 * FormaPretragaKoncerta je dijalog forma koja omogućava pretragu i pregled koncerata.
 * 
 * @author Ranko
 */
public class FormaPretragaKoncerta extends javax.swing.JDialog {
	  /**
     * Kreira novu formu za pretragu koncerata.
     * Inicijalizuje tabelu i pokreće nit za automatsko osvežavanje tabele.
     * 
     * @param parent Nadređena forma
     * @param modal Da li je dijalog modalni
     */
    public FormaPretragaKoncerta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        ModelTabeleKoncerti model = new ModelTabeleKoncerti();
        Thread th = new Thread(model);
        th.start();
        tblKoncerti.setModel(model);
        setTitle("Pretraga koncerta");
    }

    /**
     * Inicijalizacija komponenti forme.
     * Ovaj metod se automatski generiše i ne treba ga ručno menjati.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKoncerti = new javax.swing.JTable();
        txtPretraga = new javax.swing.JTextField();
        btnDetalji = new javax.swing.JButton();
        btnPretraga = new javax.swing.JButton();
        btnSacuvajJSON = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pretraga (bina/sponzor):");

        tblKoncerti.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblKoncerti);

        btnDetalji.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnDetalji.setForeground(new java.awt.Color(102, 0, 51));
        btnDetalji.setText("Detalji koncerta");
        btnDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetaljiActionPerformed(evt);
            }
        });

        btnPretraga.setForeground(new java.awt.Color(51, 0, 51));
        btnPretraga.setText("Pretraga koncerta");
        btnPretraga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretragaActionPerformed(evt);
            }
        });
        
        btnSacuvajJSON.setText("Sačuvaj u JSON");
        btnSacuvajJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajJSONActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(474, 474, 474)
                .addComponent(btnDetalji, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPretraga)
                        .addGap(18, 18, 18)
                        .addComponent(btnSacuvajJSON)
                        .addGap(0, 494, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
        	    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        	    .addGroup(layout.createSequentialGroup()
        	        .addContainerGap()
        	        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        	            .addComponent(txtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        	            .addComponent(jLabel1)
        	            .addComponent(btnPretraga)
        	            .addComponent(btnSacuvajJSON))
        	        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)                
        	        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
        	        .addGap(26, 26, 26)
        	        .addComponent(btnDetalji)
        	        .addGap(16, 16, 16))
        	);

        pack();
    }// </editor-fold>                        
    /**
     * Metoda koja se poziva prilikom klika na dugme za prikaz detalja koncerta.
     * Otvara formu sa detaljima selektovanog koncerta.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
        int row = tblKoncerti.getSelectedRow();
        
        if (row >= 0) {
            Koncert k = ((ModelTabeleKoncerti) tblKoncerti.getModel()).getSelectedKoncert(row);
            JOptionPane.showMessageDialog(this, "Sistem је ucitaо koncert.");
            new FormaDetaljiKoncerta(this, true, k).setVisible(true);
        }
        
    }                                          

    /**
     * Metoda koja se poziva prilikom klika na dugme za pretragu koncerata.
     * Filtrira prikaz koncerata na osnovu unetog parametra.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnPretragaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String param = txtPretraga.getText();
        ((ModelTabeleKoncerti) tblKoncerti.getModel()).setParametar(param);
        JOptionPane.showMessageDialog(this, "Sistem je nasao koncerte po zadatoj vrednosti");
    }      
    
    
    /**
     * Metoda za čuvanje izabranog koncerta u JSON fajl.
     */
    private void btnSacuvajJSONActionPerformed(java.awt.event.ActionEvent evt) {                                               
        int row = tblKoncerti.getSelectedRow();
        if (row >= 0) {
            Koncert koncert = ((ModelTabeleKoncerti) tblKoncerti.getModel()).getSelectedKoncert(row);
            try {
                Komunikacija.getInstance().sacuvajKoncertUJSON(koncert);
                JOptionPane.showMessageDialog(this, "Koncert je uspešno sačuvan u JSON fajl.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Greška prilikom čuvanja koncerta u JSON fajl.");
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Molimo izaberite koncert za čuvanje.");
        }
    }      

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnDetalji;
    private javax.swing.JButton btnPretraga;
    private javax.swing.JButton btnSacuvajJSON;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKoncerti;
    private javax.swing.JTextField txtPretraga;
    // End of variables declaration                   

    void refreshTable() {
        ModelTabeleKoncerti tm = (ModelTabeleKoncerti) tblKoncerti.getModel();
        tm.refreshTable();
    }

}

