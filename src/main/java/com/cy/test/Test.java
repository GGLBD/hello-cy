package com.cy.test;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ClassPathUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * @ClassName: Test
 * @Description: (这里用一句话描述这个类的作用)
 * @author cy
 * @date 2018年1月23日 下午3:38:16
 */
public class Test {

	public static void main(String[] args) throws Exception {
		
		Map<String,Object> map = new HashMap<>();
		map.put("a", "one");
		map.put("b", 89.92);
		map.put("c", 38);
		map.put("d", true);
		
		String a = StringUtils.abbreviate("abcdefghijklmn", 6);
		System.out.println(a);
		
		System.out.println(StringUtils.capitalize(StringUtils.appendIfMissing("abc", "xyz")));
		
		System.out.println(StringUtils.compare("a", "a"));
		System.out.println(StringUtils.equals(null, null));
		System.out.println(ClassPathUtils.toFullyQualifiedPath(Test.class, "Test"));
		System.out.println(ClassUtils.getClass("com.cy.test.Test"));
		System.out.println(DateFormatUtils.format(DateUtils.addDays(new Date(), 2), "yyyy-MM-dd HH:mm:ss"));
		System.out.println(DateUtils.parseDate("2019-04-05 17:32:51", "yyyy-MM-dd HH:mm:ss"));
		System.out.println(MapUtils.getString(map, "a"));
		System.out.println(MapUtils.getIntValue(map, "c"));
		System.out.println(MapUtils.getDoubleValue(map, "b"));
		System.out.println(MapUtils.getBoolean(map, "b"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// readFile();
		// testEncode();
		// testDecode();
	}

	public static void readFile() throws IOException {
		try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

			/* 读入TXT文件 */
			String pathname = "d:\\111.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
			File filename = new File(pathname); // 要读取以上路径的input。txt文件
			FileInputStream fileInputStream = new FileInputStream(filename);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer sb = new StringBuffer();
			String text = null;
			while ((text = bufferedReader.readLine()) != null) {
				sb.append(text);
			}
			System.out.println(sb.toString());

			/* 写入Txt文件 */
			File writename = new File("D:\\222.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
			writename.createNewFile(); // 创建新文件
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write(sb.toString()); // \r\n即为换行
			out.flush(); // 把缓存区内容压入文件
			out.close(); // 最后记得关闭文件
			inputStreamReader.close();
			fileInputStream.close();
			bufferedReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void testEncode() throws WriterException, IOException {
		String filePath = "D://";
		String fileName = "zxing.png";
		String content = "测试zxing生成二维码";
		int width = 300; // 图像宽度
		int height = 300; // 图像高度
		String format = "png";// 图像类型
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
		Path path = FileSystems.getDefault().getPath(filePath, fileName);
		MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像
		System.out.println("输出成功.");
	}

	/**
	 * 解析二维码
	 */
	public static void testDecode() {
		String filePath = "D://zxing.png";
		BufferedImage image;
		try {
			image = ImageIO.read(new File(filePath));
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
			System.out.println("图片中内容：  ");
			System.out.println("author： " + result.getText());
			System.out.println("图片中格式：  ");
			System.out.println("encode： " + result.getBarcodeFormat());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
}
