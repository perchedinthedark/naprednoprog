package modeli;

import domen.Izvodjac;
import domen.Koncert;
import domen.Muzicar;
import kontroler.Komunikacija;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class ModelTabeleIzvodjaci extends AbstractTableModel {

    private ArrayList<Izvodjac> lista;
    private String[] kolone = {"Rb", "Muzičar", "Napomena"};
    private int rb = 0;

    public ModelTabeleIzvodjaci() {
        lista = new ArrayList<>();
    }

    public ModelTabeleIzvodjaci(Koncert koncert) {
        try {
            lista = Komunikacija.getInstance().vratiIzvodjace(koncert);
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleIzvodjaci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

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

    public void dodajIzvodjaca(Izvodjac i) {
        rb = lista.size();
        i.setRbIzvodjaca(++rb);
        lista.add(i);
        fireTableDataChanged();
    }

    public boolean postojiIzvodjac(Muzicar muzicar) {
        for (Izvodjac izvodjac : lista) {
            if (izvodjac.getMuzicar().getMuzicarID().equals(muzicar.getMuzicarID())) {
                return true;
            }
        }
        return false;
    }

    public void obrisiIzvodjaca(int row) {
        lista.remove(row);
        
        rb = 0;
        for (Izvodjac izvodjac : lista) {
            izvodjac.setRbIzvodjaca(++rb);
        }
        
        fireTableDataChanged();
    }

    public ArrayList<Izvodjac> getLista() {
        return lista;
    }

}
