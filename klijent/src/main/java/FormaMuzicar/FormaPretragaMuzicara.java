package FormaMuzicar;

import domen.Muzicar;
import forme.KlijentskaForma;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modeli.ModelTabeleMuzicari;

/**
 * FormaPretragaMuzicara je dijalog forma za pretragu muzičara.
 * Omogućava korisniku da unese parametar pretrage i prikaže rezultate u tabeli.
 * 
 * @author Ranko
 */
public class FormaPretragaMuzicara extends javax.swing.JDialog {

    /**
     * Kreira novu formu za pretragu muzičara.
     * 
     * @param parent Nadređeni prozor
     * @param modal Da li je dijalog modalni
     */
    public FormaPretragaMuzicara(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        ModelTabeleMuzicari model = new ModelTabeleMuzicari();
        Thread th = new Thread(model);
        th.start();
        tblMuzicari.setModel(model);
        setTitle("Pretraga muzicara");
    }

    /**
     * Inicijalizacija komponenti forme.
     * Ovaj metod je automatski generisan.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMuzicari = new javax.swing.JTable();
        txtPretraga = new javax.swing.JTextField();
        btnDetalji = new javax.swing.JButton();
        btnPretraga = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pretraga (ime):");

        tblMuzicari.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblMuzicari);

        btnDetalji.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnDetalji.setForeground(new java.awt.Color(102, 0, 51));
        btnDetalji.setText("Detalji muzičara");
        btnDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetaljiActionPerformed(evt);
            }
        });

        btnPretraga.setForeground(new java.awt.Color(51, 0, 51));
        btnPretraga.setText("Pretraga muzičara");
        btnPretraga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretragaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPretraga))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(btnDetalji, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnPretraga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDetalji, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        
    /**
     * Prikazuje detalje o izabranom muzičaru.
     * Otvara dijalog za prikaz i izmenu informacija.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {                                           

        int row = tblMuzicari.getSelectedRow();

        if (row >= 0) {
            Muzicar m = ((ModelTabeleMuzicari) tblMuzicari.getModel()).getSelectedMuzicar(row);
            JOptionPane.showMessageDialog(this, "Sistem je ucitao muzicara.");
            new FormaDetaljiMuzicara(this, true, m).setVisible(true);
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita muzicara.");
        }

    }                                          
    /**
     * Pretražuje muzičare na osnovu unetog parametra.
     * Ažurira tabelu sa rezultatima pretrage.
     * 
     * @param evt Akcioni događaj klika na dugme
     */
    private void btnPretragaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String param = txtPretraga.getText();
        ((ModelTabeleMuzicari) tblMuzicari.getModel()).setParametar(param);
         JOptionPane.showMessageDialog(this, "Podaci o trazenim muzicarima su uspesno ucitani.");
        
    }                                           

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnDetalji;
    private javax.swing.JButton btnPretraga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMuzicari;
    private javax.swing.JTextField txtPretraga;
    // End of variables declaration                   
    /**
     * Osvežava tabelu muzičara nakon izvršene izmene ili brisanja.
     */
    void refreshTable() {
        ModelTabeleMuzicari tm = (ModelTabeleMuzicari) tblMuzicari.getModel();
        tm.refreshTabеle();
    }
    /**
     * Popunjava tabelu muzičarima iz baze podataka.
     */
    void popuniMuzicare() {
        KlijentskaForma mf = (KlijentskaForma) getParent();
        mf.popuniMuzicare();
    }

}
