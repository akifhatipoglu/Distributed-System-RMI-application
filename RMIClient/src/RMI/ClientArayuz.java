package RMI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientArayuz extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIP;
	private JTextField textField_port;
	private JTextField textField_sl;
	private JTextField textField_sw;
	private JTextField textField_pl;
	private JTextField textField_pw;
	private JLabel label_sinifEtiketi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientArayuz frame = new ClientArayuz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientArayuz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnHesapla = new JButton("Hesapla");
		btnHesapla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					// Kullanıcıdan gerekli değerler alınır.
					String ip = textFieldIP.getText();
					int port = Integer.parseInt(textField_port.getText());
					double sl = Double.parseDouble(textField_sl.getText());
					double sw = Double.parseDouble(textField_sw.getText());
					double pl = Double.parseDouble(textField_pl.getText());
					double pw = Double.parseDouble(textField_pw.getText());
					
					//Registry ile ip ve port ile bağlantı olur
					//Registry ile uzaktaki objeye bağlantı sağlama
					Registry registry = LocateRegistry.getRegistry(ip,port);
					
					//findDistance ile lookup yapılır ve bulunan stup olur
					//stub'ın serialize edilmiş halini döner
					FindDistance stub = (FindDistance) registry.lookup("FindDistance");
					
					//ilgili değerler objeye yüklenir.
					KNNObject obje = new KNNObject(sl,sw,pl,pw);
					
					//RMI dan bir obje döndürülür.
					KNNObject returnObje = stub.findDistance(obje);
					
					System.out.println("Response: "+returnObje.getSinifEtiketi());
					
					//label a gelen değerin sınıf etiketi bastırılır.
					label_sinifEtiketi.setText(returnObje.getSinifEtiketi());
					
				} catch (Exception e) {
					System.err.println("Error:"+e.toString());
					e.printStackTrace();
				}
				
			}
		});
		btnHesapla.setBounds(124, 219, 134, 23);
		contentPane.add(btnHesapla);
		
		JLabel lblServerIp = new JLabel("Server IP :");
		lblServerIp.setBounds(50, 36, 76, 14);
		contentPane.add(lblServerIp);
		
		JLabel lblServerPort = new JLabel("Server Port:");
		lblServerPort.setBounds(50, 63, 76, 14);
		contentPane.add(lblServerPort);
		
		JLabel lblNewLabel = new JLabel("Sepal length:");
		lblNewLabel.setBounds(50, 88, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sepal width:");
		lblNewLabel_1.setBounds(50, 113, 64, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblKNearest = new JLabel("K Nearest Neighbors");
		lblKNearest.setBounds(133, 11, 116, 14);
		contentPane.add(lblKNearest);
		
		JLabel lblNewLabel_2 = new JLabel("Petal length:");
		lblNewLabel_2.setBounds(50, 138, 64, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblPetalWidth = new JLabel("Petal width:");
		lblPetalWidth.setBounds(50, 163, 64, 14);
		contentPane.add(lblPetalWidth);
		
		textFieldIP = new JTextField();
		textFieldIP.setBounds(154, 36, 134, 20);
		contentPane.add(textFieldIP);
		textFieldIP.setColumns(10);
		
		textField_port = new JTextField();
		textField_port.setBounds(154, 60, 134, 20);
		contentPane.add(textField_port);
		textField_port.setColumns(10);
		
		textField_sl = new JTextField();
		textField_sl.setBounds(154, 85, 134, 20);
		contentPane.add(textField_sl);
		textField_sl.setColumns(10);
		
		textField_sw = new JTextField();
		textField_sw.setBounds(154, 110, 134, 20);
		contentPane.add(textField_sw);
		textField_sw.setColumns(10);
		
		textField_pl = new JTextField();
		textField_pl.setBounds(154, 135, 134, 20);
		contentPane.add(textField_pl);
		textField_pl.setColumns(10);
		
		textField_pw = new JTextField();
		textField_pw.setBounds(154, 160, 134, 20);
		contentPane.add(textField_pw);
		textField_pw.setColumns(10);
		
		JLabel lblSnfEtiketi = new JLabel("Class tag:");
		lblSnfEtiketi.setBounds(50, 264, 76, 14);
		contentPane.add(lblSnfEtiketi);
		
		label_sinifEtiketi = new JLabel("---------------------------");
		label_sinifEtiketi.setBounds(155, 264, 133, 14);
		contentPane.add(label_sinifEtiketi);
	}
}
