package RMI;
import java.rmi.Remote;
import java.rmi.RemoteException;

			//INTERFACE
public interface FindDistance extends Remote{

	public KNNObject findDistance(KNNObject knnObject) throws RemoteException;
	
}
