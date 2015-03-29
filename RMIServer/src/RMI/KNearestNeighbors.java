package RMI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KNearestNeighbors {

	/** En yakın komşu sayımız */
	private int K;

	private double[] sepal_length, sepal_width, petal_length, petal_width;

	/** Iris name SINIF ETİKETİ */
	private String[] Iris_class;

	private double[] Distance;
	private String[] Distance_name;

	public KNearestNeighbors() {
		K = 3;
	}

	int line_number;

	//BAŞLANGIÇ DEĞERLERİ ATANIR.
	public KNearestNeighbors(int k, String path) {
		K = k;
		line_number = 1;
		try {
			line_number = linereader(path);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		System.out.println("Line Number: " + line_number);
		sepal_length = new double[line_number];
		sepal_width = new double[line_number];
		petal_length = new double[line_number];
		petal_width = new double[line_number];
		Iris_class = new String[line_number];

		Distance = new double[line_number];
		Distance_name = new String[line_number];
		try {
			datareader(path, line_number);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//linereader : Dosyanın satır numarasını okur. => Line number
	private int linereader(String path) throws FileNotFoundException {
		Scanner scannerm = new Scanner(new File(path));
		int line_numberm = 0;
		while (scannerm.hasNextLine()) {
			System.out.println(scannerm.nextLine());
			line_numberm++;
		}
		scannerm.close();
		return line_numberm - 1;
	}

	/* Dosyadan tüm  verileri okur (DATASET) */
	private void datareader(String path, int line_number)
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(path));
		scanner.useDelimiter(System.getProperty("line.separator"));
		int row = 0;
		String input = "";
		while (scanner.hasNextLine()) {
			input = scanner.nextLine();
			if (line_number == row)
				break;
			sepal_length[row] = Double.parseDouble(input.substring(0,
					input.indexOf(",")));
			input = input.substring(input.indexOf(",") + 1, input.length());

			System.out.print(sepal_length[row] + "*");

			sepal_width[row] = Double.parseDouble(input.substring(0,
					input.indexOf(",")));
			input = input.substring(input.indexOf(",") + 1, input.length());
			System.out.print(sepal_width[row] + "*");

			petal_length[row] = Double.parseDouble(input.substring(0,
					input.indexOf(",")));
			input = input.substring(input.indexOf(",") + 1, input.length());
			System.out.print(petal_length[row] + "*");

			petal_width[row] = Double.parseDouble(input.substring(0,
					input.indexOf(",")));
			input = input.substring(input.indexOf(",") + 1, input.length());
			System.out.print(petal_width[row] + "*");

			Iris_class[row] = input;
			System.out.println(Iris_class[row]);

			row++;

		}
		scanner.close();
	}

	//DATASETTEKİ NOKTALAR İLE GELEN VERİ ARASINDAKİ UZAKLIKLARI BULUYORs
	public String findDistance(double sepalL, double sepalW, double petalL,double petalW) {
		
		//Öklid bağıntısı ile iki nokta arası uzaklık hesaplanır.
		
		double x1 = 0;
		for (int i = 0; i < line_number; i++) {
			x1 = Math.pow((sepal_length[i] - sepalL), 2);
			x1 += Math.pow((sepal_width[i] - sepalW), 2);
			x1 += Math.pow((petal_length[i] - petalL), 2);
			x1 += Math.pow((petal_width[i] - petalW), 2);
			x1 = Math.pow(x1, 0.5);

			Distance[i] = x1;
			Distance_name[i] = Iris_class[i];
		}
		
		// Sonucu belirleyecek fonksiyon hesaplanır.
		String returnSinifEtiketi =  kNearestNeighbor();
		
		return returnSinifEtiketi;
	}

	public String kNearestNeighbor() {
		
		/*
		 * BubbleSort
		 * 
		 * */
		
		boolean needNextPass = true;
		// 
		for (int k = 1; k < line_number && needNextPass; k++) {
			needNextPass = false;
			for (int i = 0; i < line_number - k; i++) {
				
				if (Distance[i] > Distance[i + 1]) {
					
					// distance ın sınıf adı yani distance name i swap yapar
					//distance ın değerlerini swap yapar.
					
					double temp = Distance[i];
					Distance[i] = Distance[i + 1];
					Distance[i + 1] = temp;
					
					String tempn_name=Distance_name[i];
					Distance_name[i] = Distance_name[i + 1];
					Distance_name[i + 1] = tempn_name;
					
					needNextPass = true;
				}
			}
		}

		
		 int setosa_count=0;
		 int versicolor_count=0;
		 int virginica_count=0;
		
		 // en yakın K konşuluğundaki sınıf etiketlerinin sayısını bulur.
		 for (int i = 0; i < K; i++) {
			 if(Distance_name[i].equals("Iris-setosa")){setosa_count++;}
				 if(Distance_name[i].equals("Iris-versicolor")){versicolor_count++;}
					 if(Distance_name[i].equals("Iris-virginica")){virginica_count++;}
		 System.out.println(Distance[i]+"->"+Distance_name[i]);
		 }
		 String sinifEtiketi = "";
		 
		 //FİNAL olarak verilen değerin sınıf etiketini belirler.
		 if( (setosa_count>versicolor_count)&&(setosa_count>virginica_count)  ){System.out.println("Iris-setosa"); sinifEtiketi ="Iris-setosa"; }
		 if( (versicolor_count>setosa_count)&&(versicolor_count>virginica_count)  ){System.out.println("Iris-versicolor"); sinifEtiketi ="Iris-versicolor"; } 
		 if( (virginica_count>setosa_count)&&(virginica_count>versicolor_count)  ){System.out.println("Iris-virginica"); sinifEtiketi ="Iris-virginica";} 
		 
		 return sinifEtiketi;
	}
	
}

	

	

