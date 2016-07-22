package com.importsource.util.classloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassLoad {
	static URLClassLoader loader = null;

	/**
	 * 在默认的目录加载jar
	 * 
	 * @return
	 */
	public static URLClassLoader getClassLoad() {
		/*
		 * Configuration config = new
		 * Configuration(Configuration.getRoot()+File.separator +
		 * "classpath.properties");
		 */
		if (loader == null) {
			URLClassLoaderUtil urlClass = new URLClassLoaderUtil(config.getValue("classpath1"), false);
			loader = urlClass.getClassLoader();
		}
		return loader;
	}

	/**
	 * 在给定的路径加载jar文件
	 * 
	 * @param url
	 *            指定路径
	 * @param isFile
	 *            true 文件 false 目录
	 * @return
	 */
	public static URLClassLoader getClassLoad(String url, boolean isFile) {
		URLClassLoaderUtil urlClass = new URLClassLoaderUtil(url, false);
		URLClassLoader loader = urlClass.getClassLoader();
		return loader;
	}
}

class URLClassLoaderUtil {
	URLClassLoader classLoader = null;// URLClassLoader类载入器
	private String jarFileName;
	private boolean isFile = true;
	List<String> jars = new ArrayList<String>(0);

	/**
	 * 加载具体的某一jar包
	 * 
	 * @param jarFileName
	 */
	public URLClassLoaderUtil(String jarFileName) {
		this.setJarFileName(jarFileName);
		this.inti();
	}

	/**
	 * 加载jar包 当isFile为false是加载文件夹下所有jar
	 * 
	 * @param jarFileName
	 *            路径
	 * @param isFile
	 */
	public URLClassLoaderUtil(String jarFileName, boolean isFile) {
		this.setJarFileName(jarFileName);
		this.setFile(isFile);
		this.inti();
	}

	/**
	 * 初始化，读取文件信息，并将jar文件路径加入到classpath
	 */
	private void inti() {
		// 添加jar文件路径到classpath
		if (this.isFile) {
			File f = new File(jarFileName);
			addPath(f.toURI().toString());
			jars.add(f.getAbsolutePath());
		} else {
			ReadJarFile df = new ReadJarFile(jarFileName, new String[] { "jar", "zip" });
			this.jars = df.getFiles();
			List<String> jarURLs = df.getFilesURL();
			Object[] o = jarURLs.toArray();
			addPath(o);
		}
	}

	/**
	 * 回叫方法，class操作
	 * 
	 * @paramcallBack
	 */
	public void callBack() {
		for (String s : this.jars) {
			loadClass(s);
		}
	}

	/**
	 * 添加单个jar路径到classpath
	 * 
	 * @paramjarURL
	 */
	private void addPath(String jarURL) {
		try {
			classLoader = new URLClassLoader(new URL[] { new URL(jarURL) });
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加jar路径到classpath
	 * 
	 * @paramjarURLs
	 */
	private void addPath(Object[] jarURLs) {
		URL[] urls = new URL[jarURLs.length];
		for (int i = 0; i < jarURLs.length; i++) {
			try {
				System.out.println(jarURLs[i].toString());
				urls[i] = new URL(jarURLs[i].toString());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		classLoader = new URLClassLoader(urls);
	}

	/**
	 * 动态载入class
	 * 
	 * @paramjarFileName
	 * @paramcallBack
	 */
	// private void loadClass(String jarFileName, ClassCallBack callBack) {
	private void loadClass(String jarFileName) {
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(jarFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Enumeration<JarEntry> en = jarFile.entries();
		while (en.hasMoreElements()) {
			JarEntry je = en.nextElement();
			String name = je.getName();
			String s5 = name.replace('/', '.');
			if (s5.lastIndexOf(".class") > 0) {
				String className = je.getName().substring(0, je.getName().length() - ".class".length()).replace('/',
						'.');
				Class c = null;
				try {
					c = this.classLoader.loadClass(className);
					System.out.println(className);
				} catch (ClassNotFoundException e) {
					System.out.println("NO CLASS: " + className);
					// continue;
				} catch (NoClassDefFoundError e) {
					System.out.println("NO CLASS: " + className);
					// continue;
				}
				// callBack.operate(c);
			}
		}
	}

	public String getJarFileName() {
		return jarFileName;
	}

	/**
	 * 设置jar路径
	 * 
	 * @param jarFileName
	 */
	public void setJarFileName(String jarFileName) {
		this.jarFileName = jarFileName;
	}

	public boolean isFile() {
		return isFile;
	}

	public URLClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(URLClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}
}

/**
 * 读取jarwenjian
 * 
 * @author hml
 *
 */
class ReadJarFile {
	List<String> jarList = new ArrayList<String>();
	List<String> filesURL = new ArrayList<String>();

	/**
	 * 读取指定文件夹的文件
	 * 
	 * @param jarFileName
	 *            路径
	 * @param strings
	 *            后缀
	 */
	public ReadJarFile(String jarFileName, String[] strings) {
		// TODO Auto-generated constructor stub
		File f = new File(jarFileName);
		File[] fl = f.listFiles();
		for (File file : fl) {
			for (String str : strings) {
				if (file.getName().endsWith(str)) {
					jarList.add(file.getName());
					filesURL.add(file.toURI().toString());
				}
			}

		}
	}

	/**
	 * 获取文件名
	 * 
	 * @return
	 */
	public List<String> getFiles() {
		// TODO Auto-generated method stub
		return filesURL;
	}

	/**
	 * 获取文件路径
	 * 
	 * @return
	 */
	public List<String> getFilesURL() {
		// TODO Auto-generated method stub
		return filesURL;
	}

}
