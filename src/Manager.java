import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import communication.IControlCenter;
import communication.IManager;
import communication.ISite;
import support.Mixer;

import javax.swing.*;

public class Manager extends JFrame implements IManager {

    private List<ISite> listaISite = new ArrayList<>();
    private List<Mixer> listaMixer = new ArrayList<>();
    private static IControlCenter ic;
    private JPanel panelGlowny;
    private JButton startButton;
    private JButton startButton1;
    private JTable iSiteTable;
    private JTable mixerTable;

    public Manager() throws RemoteException {
        listaISite=ic.getAllSites();
        listaMixer=ic.getAllMixers();
        iSiteTable.setModel(new ISiteTableModel(listaISite));
        mixerTable.setModel(new MixerTableModel(listaMixer));
    }




    public static void main(String[] args) throws RemoteException {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost",3000);
            ic = (IControlCenter) reg.lookup("ControlCenter");
            Manager m = new Manager();
            IManager im = (IManager) UnicastRemoteObject.exportObject(m, 0);
            reg.rebind("Manager",m);
            System.out.println("Manager is ready");
            ic.subscribe(im);


            m.setContentPane(m.panelGlowny);
            m.setBounds(600,300,600,300);
            m.setVisible(true);
        } catch (RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//        JFrame manager = new JFrame("Manager");
//        manager.setContentPane(new Manager().panelGlowny);
//        manager.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        manager.setBounds(600,300,600,300);
//        manager.setVisible(true);
    }
    @Override
    public void notify(ISite is, Boolean s) throws RemoteException {

        ((ISiteTableModel) iSiteTable.getModel()).add(is);
        System.out.println("notify(ISite,Boolean) was called");

    }

    @Override
    public void notify(Mixer mi, Boolean s) throws RemoteException {
        if (s == true) {
            ((MixerTableModel) (mixerTable.getModel())).add(mi);
        } else if (s == false) {
            ((MixerTableModel) (mixerTable.getModel())).remove(mi);
        }
        System.out.println("notify(Mixer,Boolean)was called");

    }



}
