package com.dhcc.dcs.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;



@Configuration
public class PathUtil {
	private static String seperator = System.getProperty("file.separator");

	/**windows系统下系统文件存放基本路径 */
	private static String winPath;

	/**linux系统下系统文件存放基本路径 */
	private static String linuxPath;
	
	/**资格证附件相对路径 */
	private static String zgzPath;

	/**道路运输证相对路径 */
	private static String dlyszPath;

	/**行驶证相对路径 */
	private static String xszPath;

	/**驾驶证相对路径 */
	private static String jszPath;

	/**身份证相对路径 */
	private static String sfzPath;

	@Value("${win.base.path}")
	public void setWinPath(String winPath) {
		PathUtil.winPath = winPath;
	}

	@Value("${linux.base.path}")
	public void setLinuxPath(String linuxPath) {
		PathUtil.linuxPath = linuxPath;
	}

	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = winPath;
		} else {
			basePath = linuxPath;
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}

	@Value("${zgzPath.relevant.path}")
	public void setZgzPath(String zgzPath) {
		PathUtil.zgzPath = zgzPath;
	}
	@Value("${dlyszPath.relevant.path}")
	public void setDlyszPath(String dlyszPath) {
		PathUtil.dlyszPath = dlyszPath;
	}
	@Value("${xszPath.relevant.path}")
	public void setXszPath(String xszPath) {
		PathUtil.xszPath = xszPath;
	}
	@Value("${jszPath.relevant.path}")
	public void setJszPath(String jszPath) {
		PathUtil.jszPath = jszPath;
	}
	@Value("${sfzPath.relevant.path}")
	public void setSfzPath(String sfzPath) {
		PathUtil.sfzPath = sfzPath;
	}

	public static String getZgzPath(String id,String date) {
		String imagePath = zgzPath + id  + seperator + date + seperator;
		return imagePath.replace("/", seperator);
	}
	public static String getDlyszPath(String id,String date) {
		String imagePath = dlyszPath + id  + seperator + date + seperator;
		return imagePath.replace("/", seperator);
	}
	public static String getXszPath(String id,String date) {
		String imagePath = xszPath + id  + seperator + date + seperator;
		return imagePath.replace("/", seperator);
	}
	public static String getJszPath(String id,String date) {
		String imagePath = jszPath + id  + seperator + date + seperator;
		return imagePath.replace("/", seperator);
	}
	public static String getSfzPath(String id,String date) {
		String imagePath = sfzPath + id  + seperator + date + seperator;
		return imagePath.replace("/", seperator);
	}
}
