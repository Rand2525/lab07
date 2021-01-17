import communication.IControlCenter;
import communication.IManager;
import communication.ISite;
import support.Mixer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Site implements ISite {

    private Mixer mixer;
    private String name;
    private static IControlCenter ic;

    public Site(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost",3000);
            ic = (IControlCenter) reg.lookup("ControlCenter");

        } catch (RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void start() throws RemoteException {

    }

    @Override
    public void stop() throws RemoteException {

    }

    @Override
    public void setMixer(Mixer m) throws RemoteException {
        this.mixer=m;
    }

    @Override
    public String getName() throws RemoteException {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }


}
