
package com.cms;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.jfinal.kit.PathKit;
import com.jfinal.log.Log;

public class ClassScaner {

	private static final Log log = Log.getLog(ClassScaner.class);

	public static <T extends Annotation> List<Class<T>> scanAnnotationClass(Class<T> annotationClass) {
		if (annotationClass == null) {
			log.error("annotationClass: annotationClass is null");
			return null;
		}

		List<File> classFileList = new ArrayList<File>();
		scanClass(classFileList, PathKit.getRootClassPath());

		List<Class<T>> classList = new ArrayList<Class<T>>();
		for (File file : classFileList) {

			int start = PathKit.getRootClassPath().length();
			int end = file.toString().length() - 6; // 6 == ".class".length();

			String classFile = file.toString().substring(start + 1, end);
			Class<T> clazz = classForName(classFile.replace(File.separator, "."));
			
			if (clazz != null && clazz.getAnnotation(annotationClass)!=null) {
				classList.add(clazz);
			}
		}
		return classList;
	}
	
	public static <T> List<Class<T>> scanSubClass(Class<T> pclazz, boolean mustbeCanNewInstance,boolean scanLib) {
		if (pclazz == null) {
			log.error("scanClass: parent clazz is null");
			return null;
		}
		List<File> classFileList = new ArrayList<File>();
		scanClass(classFileList, PathKit.getRootClassPath());

		List<Class<T>> classList = new ArrayList<Class<T>>();
		for (File file : classFileList) {

			int start = PathKit.getRootClassPath().length();
			int end = file.toString().length() - 6; // 6 == ".class".length();

			String classFile = file.toString().substring(start + 1, end);
			Class<T> clazz = classForName(classFile.replace(File.separator, "."));

			if (clazz != null && pclazz.isAssignableFrom(clazz)) {
				if (mustbeCanNewInstance) {
					if (clazz.isInterface())
						continue;

					if (Modifier.isAbstract(clazz.getModifiers()))
						continue;
				}
				classList.add(clazz);
			}
		}
		//扫描lib目录
		if(scanLib){
			File jarsDir = new File(PathKit.getWebRootPath() + "/WEB-INF/lib");
			if (jarsDir.exists() && jarsDir.isDirectory()) {
				File[] jarFiles = jarsDir.listFiles(new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						String name = pathname.getName().toLowerCase();
						return name.endsWith(".jar") && name.startsWith("jpress");
					}
				});
				
				if (jarFiles != null && jarFiles.length > 0) {
					for (File f : jarFiles) {
						try {
							classList.addAll(scanSubClass(pclazz, new JarFile(f), mustbeCanNewInstance));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

		return classList;
	}

	public static <T> List<Class<T>> scanSubClass(Class<T> pclazz, JarFile jarFile, boolean mustbeCanNewInstance) {
		if (pclazz == null) {
			log.error("scanClass: parent clazz is null");
			return null;
		}

		List<Class<T>> classList = new ArrayList<Class<T>>();
		Enumeration<JarEntry> entries = jarFile.entries();

		while (entries.hasMoreElements()) {
			JarEntry jarEntry = entries.nextElement();
			String entryName = jarEntry.getName();
			if (!jarEntry.isDirectory() && entryName.endsWith(".class")) {
//				String className = entryName.replace(File.separator, ".").substring(0, entryName.length() - 6);
				String className = entryName.replace("/", ".").substring(0, entryName.length() - 6);
				Class<T> clazz = classForName(className);
				if (clazz != null && pclazz.isAssignableFrom(clazz)) {
					if (mustbeCanNewInstance) {
						if (clazz.isInterface())
							continue;

						if (Modifier.isAbstract(clazz.getModifiers()))
							continue;
					}
					classList.add(clazz);
				}
			}
		}

		try {
			jarFile.close();
		} catch (IOException e) {
			log.error("jarFile.close is error",e);
		}

		return classList;
	}

	@SuppressWarnings("unchecked")
	private static <T> Class<T> classForName(String className) {
		Class<T> clazz = null;
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			clazz = (Class<T>) Class.forName(className, false, cl);
		} catch (Throwable e) {
			log.error("classForName is error，className:"+className);
		}
		return clazz;
	}

	private static void scanClass(List<File> fileList, String path) {
		File files[] = new File(path).listFiles();
		if (null == files || files.length == 0)
			return;
		for (File file : files) {
			if (file.isDirectory()) {
				scanClass(fileList, file.getAbsolutePath());
			} else if (file.getName().endsWith(".class")) {
				fileList.add(file);
			}
		}
	}

}
