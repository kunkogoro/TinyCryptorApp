package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Base64;

import javax.swing.JFileChooser;

public class OpenDialogFile {

	private static OpenDialogFile openDialogFile;
	private JFileChooser fs;
	private int result;

	private OpenDialogFile() {
		fs = new JFileChooser(new File("C:\\"));
		fs.setFileFilter(new FileTypeFilter(".txt", "Text File"));
		fs.setFileFilter(new FileTypeFilter(".docx", "Word File"));
		fs.setFileFilter(new FileTypeFilter(".PNG", "PNG File"));
		fs.setFileFilter(new FileTypeFilter(".key", "Key File"));
		fs.setFileFilter(new FileTypeFilter(".encrypt", "Encrypt File"));
		
	}

	public static OpenDialogFile getInstantce() {

		if (openDialogFile == null) {
			openDialogFile = new OpenDialogFile();
		}
		return openDialogFile;
	}

	public void openDialog() {
		fs.setDialogTitle("Open Key File");
		result = fs.showOpenDialog(null);
	}

	public void saveDialog() {
		fs.setDialogTitle("Save Key File");
		result = fs.showSaveDialog(null);
	}

	public String filePath() {
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fs.getSelectedFile();
			return file.getPath();
		}
		return "";
	}

	public String printBytetoString(byte[] key) {
		return Base64.getEncoder().encodeToString(key);
	}

	public byte[] printStringtoByte(String key) {
		return Base64.getDecoder().decode(key);
	}

	public boolean saveFile(String message, String path) throws IOException {

		File file = new File(path);

		try {
			PrintWriter bufferedInputStream = new PrintWriter(
					new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file))));

			bufferedInputStream.print(message);

			bufferedInputStream.close();
			
			return true;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public String readFile(String path) throws IOException {
		File file = new File(path);
		byte[] data = new byte[1024];
		String result = "";
		try {
			BufferedReader bufferedInputStream = new BufferedReader(
					new InputStreamReader(new BufferedInputStream(new FileInputStream(file))));

			result = bufferedInputStream.readLine();

			System.out.println(result);

			bufferedInputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

//	public static void main(String[] args) throws IOException {
//		OpenDialogFile openDialogFile = OpenDialogFile.getInstantce();
//
//		openDialogFile.saveFile("huhu", "C:\\Users\\Admin\\Desktop\\key5.txt");
//		System.out.println(openDialogFile.readFile("C:\\Users\\Admin\\Desktop\\key5.txt"));
//
//	}

}
