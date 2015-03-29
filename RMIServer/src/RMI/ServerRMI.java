package RMI;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public  class ServerRMI   implements FindDistance {
	
	
	public ServerRMI() {

	}
	
	//BAĞLANILAN FONKSİYON
	@Override
	public KNNObject findDistance(KNNObject knnObject) throws RemoteException {
		
		//BAĞLANILIP ÇAĞIRILAN SINIF 
		KNearestNeighbors knn=new KNearestNeighbors(7,"iris-training.txt");
		
		//=> ANA İSLEM BURADA GERÇEKLEŞİR
		String sinifEtiketi = knn.findDistance(knnObject.getSepalL(),knnObject.getSepalW(),knnObject.getPetalL(),knnObject.getSepalW());
		
		//SINIF ETİKETİ BURADA OBJEYE YÜKLENİR VER GERİ DÖNER
		knnObject.setSinifEtiketi(sinifEtiketi);
		
		return knnObject;
	}

	
	//RMI SERVER KURULUMU 
	public static void main(String[] args) {
		try {
			ServerRMI obj = new ServerRMI();
			
			//UNİCAST REMOTE OBJESİ
			FindDistance stub = (FindDistance) UnicastRemoteObject.exportObject(obj,0);
			try {
				
				//REGİSTRY YARATILAN YER
				Registry registry = java.rmi.registry.LocateRegistry.createRegistry(1081);
				
				//REGISTRY BAĞLANILACAK ŞEY
				
				registry.bind("FindDistance", stub);
				
				System.out.println("RMI registry hazır.");
			} catch (Exception e) {
				System.out.println("RMI regisrty başlarken hata oluştu:"+e.toString());
				e.printStackTrace();
			}
			LocateRegistry.getRegistry();
			System.out.println("RMI server hazır.");
			
			
			
		} catch (Exception e) {
			System.out.println("RMI servis başlarken hata oluştu:"+e.toString());
			e.printStackTrace();		}
	}
}
