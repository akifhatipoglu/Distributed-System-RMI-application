package RMI;
import java.rmi.Remote;
import java.rmi.RemoteException;

//interface
public interface FindDistance extends Remote{

	public KNNObject findDistance(KNNObject knnObject) throws RemoteException;
	
}
