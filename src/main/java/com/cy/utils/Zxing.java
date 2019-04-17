package com.cy.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

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
 * 
 * @ClassName: Zxing
 * @Description: (二维码工具类)
 * @author cy
 * @date 2018年1月25日 上午9:17:53
 *
 */
public class Zxing {

	/**
	 * 
	 * @Title: testEncode 
	 * @Description: (生成二维码) 
	 * @param filePath 	文件路径
	 * @param fileName	文件名称
	 * @param content	文件内容
	 * @param width		图像宽度
	 * @param height	图像高度
	 * @param format	图像类型 (例：png,jpg)
	 * @throws WriterException
	 * @throws IOException    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void testEncode(String filePath, String fileName,String content,int width,int height,String format) throws WriterException, IOException {
//		String filePath = "D://";
//		String fileName = "zxing.png";
//		String content = "测试zxing生成二维码";
//		int width = 300; // 图像宽度
//		int height = 300; // 图像高度
//		String format = "png";// 图像类型
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
