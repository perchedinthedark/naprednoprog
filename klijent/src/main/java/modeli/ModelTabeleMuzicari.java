package modeli;

import domen.Muzicar;
import kontroler.Komunikacija;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class ModelTabeleMuzicari extends AbstractTableModel implements Runnable {

    private ArrayList<Muzicar> lista;
    private String[] kolone = {"ID", "Ime", "Instrument", "Zanr", "Kontakt"};
    private String parametar = "";

    public ArrayList<Muzicar> getLista() {
        return lista;
    }
    
    public ModelTabeleMuzicari() {
        try {
            lista = Komunikacija.getInstance().vratiMuzicare();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleMuzicari.class.getName()).log(Level.SEVERE, null, ex);
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

    public Muzicar getSelectedMuzicar(int row) {
        return lista.get(row);
    }

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

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTabеle();
    }

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

