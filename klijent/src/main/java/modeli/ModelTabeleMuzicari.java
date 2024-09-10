package modeli;

import domen.Muzicar;
import kontroler.Komunikacija;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


/**
 * Klasa ModelTabeleMuzicari predstavlja model tabele za prikaz muzičara u okviru JTable komponente.
 * Nasleđuje AbstractTableModel i implementira Runnable za periodično osvežavanje podataka.
 * 
 * @author Ranko
 */
public class ModelTabeleMuzicari extends AbstractTableModel implements Runnable {

    /** Lista muzičara koja se prikazuje u tabeli */
    private ArrayList<Muzicar> lista;
    /** Nazivi kolona tabele */
    private String[] kolone = {"ID", "Ime", "Instrument", "Zanr", "Kontakt"};
    /** Parametar za pretragu muzičara */
    private String parametar = "";

    /**
     * Vraća listu muzičara u tabeli.
     * 
     * @return Lista muzičara
     */
    public ArrayList<Muzicar> getLista() {
        return lista;
    }

    /**
     * Podrazumevani konstruktor koji preuzima sve muzičare i prikazuje ih u tabeli.
     */
    public ModelTabeleMuzicari() {
        try {
            lista = Komunikacija.getInstance().vratiMuzicare();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleMuzicari.class.getName()).log(Level.SEVERE, null, ex);
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
        Muzicar m = lista.get(row);

        switch (column) {
            case 0:
                return m.getMuzicarID();
            case 1:
                return m.getIme();
            case 2:
                return m.getInstrument();
            case 3:
                return m.getZanr();
            case 4:
                return m.getKontakt();
            default:
                return null;
        }
    }

    /**
     * Vraća muzičara koji je selektovan u tabeli.
     * 
     * @param row Red u kome se nalazi selektovani muzičar
     * @return Selektovani muzičar
     */
    public Muzicar getSelectedMuzicar(int row) {
        return lista.get(row);
    }

    /**
     * Periodično osvežava tabelu muzičara svakih 10 sekundi.
     */
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTabеle();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleMuzicari.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Postavlja parametar za pretragu i osvežava tabelu.
     * 
     * @param parametar Parametar za pretragu
     */
    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTabеle();
    }

    /**
     * Osvežava tabelu muzičara sa novim podacima iz baze i primenjuje filter pretrage.
     */
    public void refreshTabеle() {
        try {
            lista = Komunikacija.getInstance().vratiMuzicare();
            if (!parametar.equals("")) {
                ArrayList<Muzicar> novaLista = new ArrayList<>();
                for (Muzicar m : lista) {
                    if (m.getIme().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(m);
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
