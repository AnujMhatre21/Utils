package com.learn.Utils;

import java.io.*;
import java.nio.file.*;

public class FileUtils {
	// Write a string to a file
	public static void writeToFile(String filePath, String content) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
			writer.write(content);
		}
	}

	// Read a file and return the content as a string
	public static String readFromFile(String filePath) throws IOException {
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}

	// Check if a file exists
	public static boolean fileExists(String filePath) {
		return Files.exists(Paths.get(filePath));
	}

	// Delete a file
	public static boolean deleteFile(String filePath) {
		try {
			Files.delete(Paths.get(filePath));
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	// Get the size of a file in bytes
	public static long getFileSize(String filePath) throws IOException {
		return Files.size(Paths.get(filePath));
	}

	// Create a directory if it doesn't exist
	public static void createDirectory(String dirPath) throws IOException {
		Path path = Paths.get(dirPath);
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
	}

	// Copy a file to a new location
	public static void copyFile(String sourcePath, String destinationPath) throws IOException {
		Files.copy(Paths.get(sourcePath), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
	}

	// Move a file to a new location
	public static void moveFile(String sourcePath, String destinationPath) throws IOException {
		Files.move(Paths.get(sourcePath), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
	}
}
