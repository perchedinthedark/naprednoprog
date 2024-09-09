package modeli;

import domen.Koncert;
import kontroler.Komunikacija;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class ModelTabeleKoncerti extends AbstractTableModel implements Runnable {

    private ArrayList<Koncert> lista;
    private String[] kolone = {"ID", "Bina", "Sponzor",
        "Vreme pocetka", "Vreme zavrsetka", "Kapacitet"};
    private String parametar = "";

    public ModelTabeleKoncerti() {
        try {
            lista = Komunikacija.getInstance().vratiKoncerte();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleKoncerti.class.getName()).log(Level.SEVERE, null, ex);
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

    public Koncert getSelectedKoncert(int row) {
        return lista.get(row);
    }

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

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

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
