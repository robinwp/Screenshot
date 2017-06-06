package com.Screen.util;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil {

	public final static ImageIcon backImg = new ImageIcon("ico/bg.jpg");

	public static BufferedImage getScreenShot(int width, int height) {
		BufferedImage bfImage = null;
		try {
			Robot robot = new Robot();
			bfImage = robot.createScreenCapture(new Rectangle(width, height));
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return bfImage;
	}

	
	public static String saveImg(BufferedImage bfImage) throws IOException {
		File file = new File("img/" + new Date().getTime() + ".png");
		File dir = file.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		ImageIO.write(bfImage, "png", file);
		return file.getAbsolutePath();
	}

	/**
	 * 
	 * @param bfImage 被截的图片
	 * @param bounds 截图时的数据
	 * @return 截图后的图片
	 */
	public static BufferedImage screenImg(BufferedImage bfImage,Rectangle bounds){
		return bfImage.getSubimage(bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	/**
	 * 重新调整图片的大小
	 * @param sourceImage
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage resetImgSize(BufferedImage sourceImage, int width, int height) {
		BufferedImage zoomImage = new BufferedImage(width, height, sourceImage.getType());
		Graphics2D gc = zoomImage.createGraphics();
		gc.drawImage(sourceImage, 0, 0, width, height, null);
		gc.dispose();
		return zoomImage;
	}
}
