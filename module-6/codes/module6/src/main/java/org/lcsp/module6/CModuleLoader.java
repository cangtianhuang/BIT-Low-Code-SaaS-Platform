package org.lcsp.module6;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class CModuleLoader {

	private CModuleLoader(String libraryPath) {
		try {
			// 是否是网络库
			if (libraryPath.startsWith("http://") || libraryPath.startsWith("https://")) {
				// 下载库文件
				String libraryFileName = downloadLibrary(libraryPath);
				System.load(libraryFileName);
			} else {
				// 否则，尝试本地文件路径
				File libraryFile = new File(libraryPath);
				if (libraryFile.exists() && libraryFile.isFile()) {
					System.load(libraryPath);
				} else {
					throw new Exception("Library file not found");
				}
			}
			// 调用JNI方法
			callLibraryMethod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String downloadLibrary(String libraryUrl) throws IOException {
		URL url = new URL(libraryUrl);
		URLConnection connection = url.openConnection();

		// 获取文件名
		String[] parts = libraryUrl.split("/");
		String libraryFileName = parts[parts.length - 1];

		try (InputStream in = connection.getInputStream();
		     FileOutputStream out = new FileOutputStream(libraryFileName)) {

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}
		return libraryFileName;
	}

	public native void callLibraryMethod();
}
