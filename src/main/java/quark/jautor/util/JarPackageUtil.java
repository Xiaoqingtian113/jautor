package quark.jautor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * 打Jar包工具类
 */
public class JarPackageUtil {
	/**
	 * 动态生成Jar包
	 */
	public static void main(String[] args){
		try {
			createJar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static File createJar() throws Exception {
//		String clsName = clazz.getName();
//		String base = clsName.substring(0, clsName.lastIndexOf("."));
//		base = base.replaceAll("\\.", Matcher.quoteReplacement("/"));
		String base = "quark/jautor/testcases";
//		URL root = clazz.getResource("");
//		System.out.println(root);
//		System.out.println(base);
		
		JarOutputStream out = null;
		final File jar = new File("target/lib/mytestcases.jar");
		System.out.println(jar.getAbsolutePath());
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			public void run() {
//				// 运行完成后删除jar包
//				jar.delete();
//			}
//		});
		
		try {
			File path = new File("target/classes/quark/jautor/testcases");
			Manifest manifest = new Manifest();
			manifest.getMainAttributes().putValue("Manifest-Version", "0");
			manifest.getMainAttributes().putValue("Created-By", "zhuchunqing");
			out = new JarOutputStream(new FileOutputStream(jar), manifest);
			writeBaseFile(out, path, base);
		} finally {
			out.flush();
			out.close();
		}
		return jar;
	}

	/**
	 * 递归添加.class文件
	 */
	private static void writeBaseFile(JarOutputStream out, File file, String base) throws IOException {
		if (file.isDirectory()) {
			File[] fl = file.listFiles();
			if (base.length() > 0) {
				base = base + "/";
			}
			for (int i = 0; i < fl.length; i++) {
				writeBaseFile(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new JarEntry(base));
			FileInputStream in = null;
			try {
				in = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int n = in.read(buffer);
				while (n != -1) {
					out.write(buffer, 0, n);
					n = in.read(buffer);
				}
			} finally {
				in.close();
			}
		}
	}
	
	public static void loadJar(String jarPath) {  
        File jarFile = new File(jarPath);  
        // 从URLClassLoader类中获取类所在文件夹的方法，jar也可以认为是一个文件夹  
        Method method = null;  
        try {  
            method = URLClassLoader.class  
                    .getDeclaredMethod("addURL", URL.class);  
        } catch (NoSuchMethodException | SecurityException e1) {  
            e1.printStackTrace();  
        }  
        // 获取方法的访问权限以便写回  
        boolean accessible = method.isAccessible();  
        try {  
            method.setAccessible(true);  
            // 获取系统类加载器  
            URLClassLoader classLoader = (URLClassLoader) ClassLoader  
                    .getSystemClassLoader();  
            URL url = jarFile.toURI().toURL();  
            method.invoke(classLoader, url);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            method.setAccessible(accessible);  
        }
	}
}