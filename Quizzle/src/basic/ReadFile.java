package basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {//taken from http://www.homeandlearn.co.uk/java/read_a_textfile_in_java.html

	private String path;

	public ReadFile(String file_path) {

		path = file_path;

	}

	public String[] OpenFile() throws IOException {

		FileReader reader = new FileReader(path);

		BufferedReader bReader = new BufferedReader(reader);

		int numberOfLines = readLines();

		String[] words = new String[numberOfLines];

		for (int i = 0; i < numberOfLines; i++) {

			words[i] = bReader.readLine();

		}

		bReader.close();

		return words;

	}

	public int readLines() throws IOException {

		FileReader file_to_read = new FileReader(path);

		BufferedReader bf = new BufferedReader(file_to_read);

		String aLine;

		int numberOfLines = 0;

		while ((aLine = bf.readLine()) != null) {

			numberOfLines++;

		}

		bf.close();

		return numberOfLines;

	}

}
