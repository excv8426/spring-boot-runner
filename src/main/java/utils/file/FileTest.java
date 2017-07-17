package utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileTest {
	public static void writetxt() throws IOException{
		/* 写入Txt文件 */
		File writename = new File("/dzdayx/siea_data/output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
		writename.createNewFile(); // 创建新文件
		BufferedWriter out = new BufferedWriter(new FileWriter(writename));
		out.write("**********test wirte file********\r\n"); // \r\n即为换行
		out.flush(); // 把缓存区内容压入文件
		out.close(); // 最后记得关闭文件
	}
	
    public static void readtxt() throws IOException{
		/* 读入TXT文件 */
		String pathname = "/dzdayx/siea_data/output.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
		File filename = new File(pathname); // 要读取以上路径的input。txt文件
		if (!filename.exists()) {
			filename.createNewFile();
		}
		InputStreamReader reader = new InputStreamReader(
				new FileInputStream(filename)); // 建立一个输入流对象reader
		BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
		String line = "";
		line = br.readLine();
		System.out.println(line);
		while (line != null) {
			line = br.readLine(); // 一次读入一行数据
			System.out.println(line);
		}
	}
    
    public static void deletetxt(){
    	String pathname = "/dzdayx/siea_data/output.txt";
    	File filename = new File(pathname);
    	if (filename.delete()) {
			System.out.println("*****************delete successed**************");
		}else {
			System.out.println("*****************delete failed**************");
		}
    }
}
