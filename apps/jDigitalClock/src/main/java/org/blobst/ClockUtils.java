package org.blobst;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ClockUtils {
	private String value;
	private String file;

	public void set(String value) {
		this.value = value;
	}

	public String get() {
		return value;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void writeFile(String value) {
		if (file == null || value == null) return;

		try (FileWriter writer = new FileWriter(file)) {
			writer.write(value);
		} catch (IOException e) {
			System.err.println("[ERROR]: " + e.getMessage());
		}
	}

	public String readFile() {
		File f = new File(file);
		StringBuilder content = new StringBuilder();

		try (Scanner scanner = new Scanner(f)) {
			while (scanner.hasNextLine()) {
				content.append(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			return null;
		}

		return content.toString();
	}
}