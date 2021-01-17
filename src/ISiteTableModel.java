import communication.ISite;

import javax.swing.table.AbstractTableModel;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ISiteTableModel extends AbstractTableModel {

    private List<ISite> listaISite = new ArrayList<>();
    private String[] columnNames = {"ISite"};

    public ISiteTableModel(List<ISite> listaISite) {
        this.listaISite=listaISite;
    }

    public void add(ISite dane) {
        this.listaISite.add(dane);
        this.fireTableRowsInserted(listaISite.size() - 1, listaISite.size());
    }

    public void remove(ISite dane) {
        this.listaISite.remove(dane);
        this.fireTableRowsInserted(listaISite.size() - 1, listaISite.size());

    }

    @Override
    public int getRowCount() {
        return listaISite.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if(col==0) {
            try {
                return listaISite.get(row).getName();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
       return null;
    }
}
