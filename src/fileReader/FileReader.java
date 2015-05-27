package fileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FileReader {
	
	private String filename;
	
	public FileReader(String filename) {
		// TODO Auto-generated constructor stub
		this.filename = filename;
	}

	public ArrayList<ArrayList<Double>> read()
	{
		RandomAccessFile File;

		ArrayList<ArrayList<Double>> outer = new ArrayList<ArrayList<Double>>();

		try {
			File = new RandomAccessFile(filename, "r");
			String line;
			while ((line = File.readLine()) != null) {


				String[] split = line.split(",");
				ArrayList<Double> inner = new ArrayList<Double>();
				for (int i = 0; i<split.length;i++){
					Double value = Double.parseDouble(split[i]);
					inner.add(value);
				}
				outer.add(inner);
			}
			
			File.close();
			return outer;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		return null;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	
	
	

}
