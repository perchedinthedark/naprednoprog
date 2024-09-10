package modeli;

import domen.Izvodjac;
import domen.Koncert;
import domen.Muzicar;
import kontroler.Komunikacija;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


/**
 * Klasa ModelTabeleIzvodjaci predstavlja model tabele za prikaz izvođača u okviru JTable komponente.
 * Nasleđuje AbstractTableModel i omogućava manipulaciju sa podacima o izvođačima.
 * 
 * @author Ranko
 */
public class ModelTabeleIzvodjaci extends AbstractTableModel {

    /** Lista izvođača koji se prikazuju u tabeli */
    private ArrayList<Izvodjac> lista;
    /** Nazivi kolona tabele */
    private String[] kolone = {"Rb", "Muzičar", "Napomena"};
    /** Redni broj izvođača */
    private int rb = 0;

    /**
     * Podrazumevani konstruktor koji inicijalizuje praznu listu izvođača.
     */
    public ModelTabeleIzvodjaci() {
        lista = new ArrayList<>();
    }

    /**
     * Konstruktor koji inicijalizuje listu izvođača na osnovu koncerta.
     * 
     * @param koncert Koncert za koji se preuzimaju izvođači
     */
    public ModelTabeleIzvodjaci(Koncert koncert) {
        try {
            lista = Komunikacija.getInstance().vratiIzvodjace(koncert);
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleIzvodjaci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Vraća broj redova u tabeli.
     * 
     * @return Broj izvođača u tabeli
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
        Izvodjac i = lista.get(row);

        switch (column) {
            case 0:
                return i.getRbIzvodjaca();
            case 1:
                return i.getMuzicar();
            case 2:
                return i.getNapomena();
            default:
                return null;
        }
    }

    /**
     * Dodaje novog izvođača u tabelu.
     * 
     * @param i Izvođač koji se dodaje
     */
    public void dodajIzvodjaca(Izvodjac i) {
        rb = lista.size();
        i.setRbIzvodjaca(++rb);
        lista.add(i);
        fireTableDataChanged();
    }

    /**
     * Proverava da li izvođač već postoji u tabeli na osnovu muzičara.
     * 
     * @param muzicar Muzičar koji se proverava
     * @return true ako izvođač već postoji, false u suprotnom
     */
    public boolean postojiIzvodjac(Muzicar muzicar) {
        for (Izvodjac izvodjac : lista) {
            if (izvodjac.getMuzicar().getMuzicarID().equals(muzicar.getMuzicarID())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Briše izvođača iz tabele na osnovu reda.
     * 
     * @param row Red u kome se nalazi izvođač koji se briše
     */
    public void obrisiIzvodjaca(int row) {
        lista.remove(row);

        rb = 0;
        for (Izvodjac izvodjac : lista) {
            izvodjac.setRbIzvodjaca(++rb);
        }

        fireTableDataChanged();
    }

    /**
     * Vraća listu izvođača u tabeli.
     * 
     * @return Lista izvođača
     */
    public ArrayList<Izvodjac> getLista() {
        return lista;
    }
}