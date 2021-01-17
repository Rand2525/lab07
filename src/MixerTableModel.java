import support.Mixer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MixerTableModel extends AbstractTableModel {

    private String[] columnNames = {"Mixer"};
    private List<Mixer> listaMixer = new ArrayList<>();

    public MixerTableModel(List<Mixer> listaMixer) {
        this.listaMixer = listaMixer;
    }

    public void add(Mixer dane) {
        this.listaMixer.add(dane);
        this.fireTableRowsInserted(listaMixer.size() - 1, listaMixer.size());
    }

    public void remove(Mixer mixer) {

        for (Iterator<Mixer> it = listaMixer.iterator(); it.hasNext();) {
            Mixer next = it.next();
            if(next.getName().equals(mixer.getName()))
                it.remove();
        }

//        for (Mixer mix : listaMixer) {
//            if (mix.getName().equals(dane.getName())) {
//                listaMixer.remove(mix);
//            }
//        }
        this.fireTableRowsInserted(listaMixer.size() - 1, listaMixer.size());

    }

    @Override
    public int getRowCount() {
        return listaMixer.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if(col == 0)
            return listaMixer.get(row).getName();

        return null;
    }
}
