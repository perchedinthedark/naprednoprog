package modeli;

import domen.Koncert;
import kontroler.Komunikacija;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


/**
 * Klasa ModelTabeleKoncerti predstavlja model tabele za prikaz koncerata u okviru JTable komponente.
 * Nasleđuje AbstractTableModel i implementira Runnable za periodično osvežavanje podataka.
 * 
 * @author Ranko
 */
public class ModelTabeleKoncerti extends AbstractTableModel implements Runnable {

    /** Lista koncerata koja se prikazuje u tabeli */
    private ArrayList<Koncert> lista;
    /** Nazivi kolona tabele */
    private String[] kolone = {"ID", "Bina", "Sponzor", "Vreme pocetka", "Vreme zavrsetka", "Kapacitet"};
    /** Parametar za pretragu koncerata */
    private String parametar = "";

    /**
     * Podrazumevani konstruktor koji preuzima sve koncerte i prikazuje ih u tabeli.
     */
    public ModelTabeleKoncerti() {
        try {
            lista = Komunikacija.getInstance().vratiKoncerte();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleKoncerti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća broj redova u tabeli.
     * 
     * @return Broj redova u tabeli
     */
    @Override
    public int getRowCount() {
        return lista.size();
    }

    /**
     * Vraća broj kolona u tabeli.
     * 
     * @return Broj kolona u tabeli
     */
    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    /**
     * Vraća naziv kolone u tabeli.
     * 
     * @param i Indeks kolone
     * @return Naziv kolone
     */
    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    /**
     * Vraća vrednost za određenu ćeliju tabele.
     * 
     * @param row Red u tabeli
     * @param column Kolona u tabeli
     * @return Vrednost u ćeliji
     */
    @Override
    public Object getValueAt(int row, int column) {
        Koncert k = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        switch (column) {
            case 0:
                return k.getKoncertID();
            case 1:
                return k.getBina();
            case 2:
                return k.getSponzor();
            case 3:
                return sdf.format(k.getDatumPocetka());
            case 4:
                return sdf.format(k.getDatumZavrsetka());
            case 5:
                return k.getKapacitetKoncerta();
            default:
                return null;
        }
    }

    /**
     * Vraća koncert koji je selektovan u tabeli.
     * 
     * @param row Red u kome se nalazi selektovani koncert
     * @return Selektovani koncert
     */
    public Koncert getSelectedKoncert(int row) {
        return lista.get(row);
    }

    /**
     * Periodično osvežava tabelu koncerata svakih 10 sekundi.
     */
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleKoncerti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Postavlja parametar za pretragu i osvežava tabelu.
     * 
     * @param parametar Parametar za pretragu
     */
    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    /**
     * Osvežava tabelu koncerata sa novim podacima iz baze i primenjuje filter pretrage.
     */
    public void refreshTable() {
        try {
            lista = Komunikacija.getInstance().vratiKoncerte();
            if (!parametar.equals("")) {
                ArrayList<Koncert> novaLista = new ArrayList<>();
                for (Koncert k : lista) {
                    if (k.getBina().getNaziv().toLowerCase().contains(parametar.toLowerCase())
                            || k.getSponzor().getNaziv().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(k);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}