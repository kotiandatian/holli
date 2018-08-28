package com.framework.loippi.job;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.framework.loippi.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * 统计用户定时器
 *
 * @author oukh
 */
@Component("MysqldumpJob")
/* @Lazy(false) */
public class MysqldumpJob {

	private static Logger log = LoggerFactory.getLogger(MysqldumpJob.class);

	// 获取系统
	private static String OS = System.getProperty("os.name").toLowerCase();

	// jdbc\:mysql\://139.199.175.39\:3306/shareflat?useUnicode\=true&characterEncoding\=UTF-8
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	private String ip;

	private String port;

	private String database;
	
	// 备份文件夹名称(默认 sql)
	private String backupName = "sql";
	
	// 是否压缩(默认 是) 0.否 1.是
	private int isGz = 1;

	// 备份文件保留天数(默认保留7天)
	private int holdDay = 7;

	@Scheduled(cron = "0 0 2 * * ?")
	public void build() {
		//<cron-expression>0 0/30 * * * ?</cron-expression>:每隔30分钟 
		//<cron-expression>0 0/15 * * * ?</cron-expression>每隔15分钟 
		//<cron-expression>0 0 0/1 * * ?</cron-expression>每隔1个小时
		log.info("每天凌晨2点执行数据库备份一次!");
		try {
			// ************win*****************
			// String[] command = { "cmd", "/c", "mysqldump -u root
			// -pzjhldevdb@2015 -h139.199.175.39 -P3306 ghost >D://1.sql"};
			//
			// Process ps = Runtime.getRuntime().exec(command );

			// ************linux*****************
			// String[] command = { "/bin/sh", "-c", "mysqldump -u root
			// -pzjhldevdb@2015 -h139.199.175.39 -P3306 ghost >/home/linux.sql"
			// };

			// 只在liunx环境下备份
			if (OS.indexOf("linux") >= 0) {
				// *********************备份流程start************************//
				// 获取数据库参数
				simplifyParam();
				// 创建备份文件
				String allFilepath = createFile();
				log.info("allFilepath=" + allFilepath);
				// 为创建的文件授权
				authorizeFile(allFilepath);
				// 开始备份申请了
				mysqldumpSql(allFilepath);
				// *********************备份流程end************************//

				// *********************删除文件流程start************************//
				deleteSqlByCreateTime(allFilepath);
				// *********************删除文件流程end************************//
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取数据库参数
	private void simplifyParam() {

		// String url =
		// "jdbc\\:mysql\\://139.199.175.39\\:3306/shareflat?useUnicode\\=true&characterEncoding\\=UTF-8";

		String ipStr = "jdbc:mysql://";
		int indexOf = url.indexOf(ipStr);
		System.out.println(indexOf);
		int indexOf2 = url.lastIndexOf(":");
		System.out.println(indexOf2);
		ip = url.substring(indexOf + ipStr.length(), indexOf2);
		System.out.println(ip);

		String portStr = url.substring(indexOf2 + 1);
		System.out.println("portStr=" + portStr);
		int indexOf3 = portStr.indexOf("/");
		System.out.println(indexOf3);
		port = portStr.substring(0, indexOf3);
		System.out.println(port);

		String databaseStr = portStr.substring(indexOf3 + 1);
		System.out.println("databaseStr=" + databaseStr);
		int indexOf4 = databaseStr.indexOf("?");
		System.out.println(indexOf4);
		database = databaseStr.substring(0, indexOf4);
		System.out.println(database);
	}

	// 创建备份文件
	private String createFile() throws IOException {

		// home/webdata/tomcat-nasa/webapps/nasa/WEB-INF/classes
		String filePath = new File(MysqldumpJob.class.getResource("/").getPath()).getAbsolutePath();

		log.info("filePath==" + filePath);
		int lastIndexOf = filePath.lastIndexOf(File.separator);
		String filePath1 = filePath.substring(0, lastIndexOf + 1);
		log.info("filePath1==" + filePath1);
		String filePath2 = filePath1 + backupName + File.separator;
		log.info("filePath2==" + filePath2);
		Date date = new Date();
		String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);
		String filePath3 = format + ".sql";
		if (isGz == 1)
			filePath3 = filePath3 + ".gz";
		File file2 = new File(filePath2);
		log.info("filePath3==" + filePath3);
		if (!file2.exists()) {
			file2.mkdirs();
		}

		File file = new File(filePath2, filePath3);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file.getAbsolutePath();
	}

	// 为备份文件授权
	private void authorizeFile(String allFilepath) throws IOException {
		String[] command = { "/bin/sh", "-c", "chmod -R 755 " + allFilepath };

		Process ps = Runtime.getRuntime().exec(command);
	}

	// 开始备份数据库
	private void mysqldumpSql(String allFilepath) throws IOException {
		// mysqldump -u root
		// -pzjhldevdb@2015 -h139.199.175.39 -P3306 ghost >D://1.sql
		StringBuffer sql = new StringBuffer();
		sql.append("mysqldump -u ").append(username).append(" -p" + password).append(" -h" + ip).append(" -P" + port)
				.append(" " + database);

		if (isGz == 1)
			sql.append(" | gzip ");
		sql.append(" > ").append(allFilepath);

		log.info(sql.toString());

		String[] command = { "/bin/sh", "-c", sql.toString() };

		Process ps = Runtime.getRuntime().exec(command);
	}

	// 删除旧备份文件
	private void deleteSqlByCreateTime(String dirName) {

		// 处理-传进来的是文件的路径
		if (dirName.contains(".sql") || dirName.contains(".gz")) {
			dirName = dirName.substring(0, dirName.lastIndexOf("/"));
		}

		File dir = new File(dirName);
		if (dir.isDirectory() && dir.exists()) {
			File[] listFiles = dir.listFiles();
			String fullFileName = "";
			for (int i = 0; i < listFiles.length; i++) {
				fullFileName = listFiles[i].getAbsolutePath();

				log.info(fullFileName);
				if (!StringUtil.isEmpty(fullFileName)) {
					Date fileCreateTime = getCreateTime(fullFileName);
					Calendar instance = Calendar.getInstance();
					instance.add(Calendar.DATE, holdDay * -1);
					Date lastsetTime = instance.getTime();
					if (fileCreateTime.compareTo(lastsetTime) <= 0) {
						new File(fullFileName).deleteOnExit();
					}
				}
			}
		}
	}

	// 获取文件创建时间
	private static Date getCreateTime(String fullFileName) {
		Path path = Paths.get(fullFileName);
		BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class,
				LinkOption.NOFOLLOW_LINKS);
		BasicFileAttributes attr;
		try {
			attr = basicview.readAttributes();
			Date createDate = new Date(attr.creationTime().toMillis());
			return createDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.set(1970, 0, 1, 0, 0, 0); 
		return cal.getTime();
	}

	public static void main(String[] args) throws IOException {
		// String property = System.getProperty("os.name");
		// System.out.println(property);
		// ********************************************************************//
		// // E:\wufen\project\shareflat\target\classes
		// String filePath = new
		// File(MysqldumpJob.class.getResource("/").getPath()).getAbsolutePath();
		//
		// log.info("filePath==" + filePath);
		// int lastIndexOf = filePath.lastIndexOf(File.separator);
		// String filePath1 = filePath.substring(0, lastIndexOf + 1);
		// log.info("filePath1==" + filePath1);
		// String filePath2 = filePath1 + "sql" + File.separator;
		// log.info("filePath2==" + filePath2);
		// Date date = new Date();
		// String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);
		// String filePath3 =format + ".sql";
		// String filePath4 = filePath2 + format + ".sql";
		// log.info("filePath3==" + filePath3);
		// File file = new File(filePath2,format);
		// if(!file.exists()){
		//// file.mkdirs();
		// file.createNewFile();
		// }
		// ********************************************************************//

		// //
		// jdbc\\:mysql\\://139.199.175.39\\:3306/shareflat?useUnicode\\=true&characterEncoding\\=UTF-8
		// String url =
		// "jdbc\\:mysql\\://139.199.175.39\\:3306/shareflat?useUnicode\\=true&characterEncoding\\=UTF-8";
		//
		// String ipStr = "jdbc\\:mysql\\://";
		// int indexOf = url.indexOf(ipStr);
		// System.out.println(indexOf);
		// int indexOf2 = url.lastIndexOf("\\:");
		// System.out.println(indexOf2);
		// String ip = url.substring(indexOf + ipStr.length(), indexOf2);
		// System.out.println(ip);
		//
		// String portStr = url.substring(indexOf2 + 2);
		// System.out.println("portStr=" + portStr);
		// int indexOf3 = portStr.indexOf("/");
		// System.out.println(indexOf3);
		// String port = portStr.substring(0, indexOf3);
		// System.out.println(port);
		//
		// String databaseStr = portStr.substring(indexOf3 + 1);
		// System.out.println("databaseStr=" + databaseStr);
		// int indexOf4 = databaseStr.indexOf("?");
		// System.out.println(indexOf4);
		// String database = databaseStr.substring(0, indexOf4);
		// System.out.println(database);

		// ********************************************************************//

		// deleteSqlByCreateTime("D:\\1\\");

		// Calendar instance = Calendar.getInstance();
		//
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.println(sdf.format(instance.getTime()));
		// instance.setTime(new Date());
		//
		// instance.add(Calendar.DATE, -1);
		// System.out.println(sdf.format(instance.getTime()));

		String dirName = "/home/webdata/tomcat-nasa/webapps/nasa/WEB-INF/sql/20180706_102618.sql.gz";
		if (dirName.contains(".sql") || dirName.contains(".gz")) {
			dirName = dirName.substring(0, dirName.lastIndexOf("/"));
		}
		System.out.println(dirName);
	}

}
