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
    private JButton stopButton;
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
            Manager manager = new Manager();
            IManager im = (IManager) UnicastRemoteObject.exportObject(manager, 0);
//            reg.rebind("Manager",manager);
            System.out.println("Manager is ready");
            ic.subscribe(im);


            manager.setContentPane(manager.panelGlowny);
            manager.setBounds(600,300,600,300);
            manager.setDefaultCloseOperation(EXIT_ON_CLOSE);
            manager.setVisible(true);
        } catch (RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    @Override
    public void notify(ISite iSite, Boolean add) throws RemoteException {

        if(add == true)
        ((ISiteTableModel) iSiteTable.getModel()).add(iSite);
        else
            if(add == false)
                ((ISiteTableModel) iSiteTable.getModel()).remove(iSite);

        System.out.println("notify(ISite,Boolean) was called");

    }

    @Override
    public void notify(Mixer mixer, Boolean add) throws RemoteException {
        if (add == true) {
            ((MixerTableModel) (mixerTable.getModel())).add(mixer);
        } else if (add == false) {
            ((MixerTableModel) (mixerTable.getModel())).remove(mixer);
        }
        System.out.println("notify(Mixer,Boolean)was called");

    }



}
