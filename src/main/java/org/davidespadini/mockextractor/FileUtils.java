package org.davidespadini.mockextractor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {

	public static String[] getAllDirs(String path) {
		ArrayList<String> dirs = new ArrayList<String>();
		getAllDirs(path, dirs);
		
		String[] ar = new String[dirs.size()];
		ar = dirs.toArray(ar);
		return ar;
	}
	
	private static void getAllDirs(String path, ArrayList<String> dirs) {
		
		File f = new File(path);
		if(f.getName().equals(".git")) return;
		
		for(File inside : f.listFiles()) {
			if(inside.isDirectory()) {
				String newDir = inside.getAbsolutePath();
				dirs.add(newDir);
				getAllDirs(newDir, dirs);
			}
		}
	}

	public static String[] getAllJavaFiles(String path) {
		ArrayList<String> files = new ArrayList<String>();
		getAllJavaFiles(path, files);
		
		String[] ar = new String[files.size()];
		ar = files.toArray(ar);
		return ar;
	}
	
	private static void getAllJavaFiles(String path, ArrayList<String> files) {
		
		File f = new File(path);
		if(f.getName().equals(".git")) return;
		
		for(File inside : f.listFiles()) {
			if(inside.isDirectory()) {
				String newDir = inside.getAbsolutePath();
				getAllJavaFiles(newDir, files);
			} else if(isJava(inside)) {
				files.add(inside.getAbsolutePath());
			}
		}
	}

	private static boolean isJava(File inside) {
		return inside.getAbsolutePath().toLowerCase().endsWith(".java");
	}

	public static String[] getAllJarFiles(String dir) {
		File f = new File(dir);
		List<String> files = new ArrayList<String>();
		
		for(File inside : f.listFiles()) {
			if(inside.getAbsolutePath().toLowerCase().endsWith("jar")) {
				files.add(inside.getAbsolutePath());
			}
		}
		
		return files.toArray(new String[files.size()]);
	}
	
}