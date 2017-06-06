package com.Screen.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ClipBoardUtil {

	public final static Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	
	/**
	 * 将图片设置到系统剪切板
	 * @param image
	 */
	public static void setImageToClipBoard(BufferedImage image){
		Transferable transferable = new Transferable() {
			@Override
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] { DataFlavor.imageFlavor };
			}
			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return DataFlavor.imageFlavor.equals(flavor);
			}

			public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
				if (isDataFlavorSupported(flavor))
					return image;
				throw new UnsupportedFlavorException(flavor);
			}
		};
		systemClipboard.setContents(transferable, null);
	}
}
