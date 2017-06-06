package com.Screen.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.Screen.util.ClipBoardUtil;
import com.Screen.util.ImageUtil;
import com.Screen.util.OpenUrlOnDefBs;

public class SwingWin {

	private JFrame frame;
	private JLabel background;
	private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	private JLabel info;
	private boolean isScreen = false;
	private JLabel lblEsc;
	private JLabel lab_v;
	private JLabel lab_h;
	private JLabel lab_screen;
	private int x;
	private int y;
	private Rectangle bounds;
	private BufferedImage screenShot;
	private BufferedImage screenimg;
	private JLabel saveinfo1;
	private JLabel saveinfo2;
	private JLabel openBs;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingWin window = new SwingWin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "设置Windows界面风格失败！");
		}
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("ico/screenshot.png"));
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lab_v = new JLabel("");
		lab_v.setBackground(Color.RED);
		lab_v.setOpaque(true);
		lab_v.setBounds(0, 0, 1, 0);
		lab_v.setVisible(false);
		frame.getContentPane().add(lab_v);

		openBs = new JLabel("<html><u>by 墨迹</u><html>");
		openBs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					OpenUrlOnDefBs.opUrl(null);
				}

			}
		});
		openBs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		openBs.setFont(new Font("微软雅黑", Font.PLAIN, 11));
		openBs.setForeground(Color.WHITE);
		openBs.setBounds(336, 275, 54, 15);
		frame.getContentPane().add(openBs);

		lab_screen = new JLabel("");
		lab_screen.setBorder(new LineBorder(Color.RED));
		lab_screen.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(lab_screen);

		lab_h = new JLabel("");
		lab_h.setOpaque(true);
		lab_h.setBackground(Color.RED);
		lab_h.setBounds(0, 0, 0, 1);
		lab_h.setVisible(false);

		saveinfo2 = new JLabel();
		saveinfo2.setHorizontalTextPosition(SwingConstants.LEFT);
		saveinfo2.setHorizontalAlignment(SwingConstants.LEFT);
		saveinfo2.setVerticalTextPosition(SwingConstants.TOP);
		saveinfo2.setVerticalAlignment(SwingConstants.TOP);
		saveinfo2.setVisible(false);
		saveinfo2.setForeground(Color.WHITE);
		saveinfo2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		saveinfo2.setBounds(74, 94, 205, 79);
		frame.getContentPane().add(saveinfo2);

		saveinfo1 = new JLabel("截图已复制到系统剪切板");
		saveinfo1.setVisible(false);

		label = new JLabel("<html><u>打开截图保存目录</u></html>");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					Runtime cmd = Runtime.getRuntime();
					try {
						cmd.exec("explorer /open, img");
					} catch (IOException e1) {
						StringBuffer path = new StringBuffer(new File("img").getAbsolutePath());
						if (path.length() > 12) {
							path.insert(12, "<br>");
						}
						JOptionPane.showMessageDialog(null, "<html>打开失败，请手动打开！<br>" + path.toString() + "</html>");
					}
				}
			}
		});
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.setForeground(Color.WHITE);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(87, 207, 113, 24);
		frame.getContentPane().add(label);
		saveinfo1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		saveinfo1.setForeground(Color.WHITE);
		saveinfo1.setBounds(74, 73, 177, 24);
		frame.getContentPane().add(saveinfo1);
		frame.getContentPane().add(lab_h);
		lblEsc = new JLabel("右键 或 Esc取消截图");
		lblEsc.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEsc.setHorizontalAlignment(SwingConstants.CENTER);
		lblEsc.setOpaque(true);
		lblEsc.setBackground(Color.PINK);
		lblEsc.setForeground(Color.WHITE);
		lblEsc.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblEsc.setBounds(44, 113, 126, 24);
		lblEsc.setVisible(false);
		frame.getContentPane().add(lblEsc);
		info = new JLabel("双击左键 或 按Enter进行截图， Esc 退出程序");
		info.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		info.setForeground(Color.WHITE);
		info.setBounds(87, 183, 263, 24);
		frame.getContentPane().add(info);
		background = new JLabel("");
		background.setBounds(0, 0, 400, 300);
		background.setHorizontalTextPosition(SwingConstants.CENTER);
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setIcon(ImageUtil.backImg);
		frame.getContentPane().add(background);
		background.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (isScreen) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						x = e.getX();
						y = e.getY();
						lab_screen.setBounds(x, y, 1, 1);
						if (!lab_screen.isVisible()) {
							lab_screen.setVisible(true);
						}
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						clenScreen(false);
						frame.setVisible(true);
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (isScreen) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						screenimg = ImageUtil.screenImg(screenShot, bounds = lab_screen.getBounds());
					}
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && e.getButton() == 1) {
					if (!isScreen) {
						screenshot();
					}

				}
			}
		});
		background.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (isScreen) {
					mouseMovedEvent(e);
				}
			}

			public void mouseDragged(MouseEvent e) {
				if (isScreen) {
					screenshoting(e);
				}
			}
		});
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (isScreen) {
						if (bounds != null && bounds.width > 1 && bounds.height > 1) {
							clenScreen(true);
							new Thread(new Runnable() {
								public void run() {
									ClipBoardUtil.setImageToClipBoard(screenimg);
									StringBuffer sb = null;
									try {
										sb = new StringBuffer(ImageUtil.saveImg(screenimg));
									} catch (IOException e) {
										JOptionPane.showMessageDialog(null, "<html>"+e.getMessage()+"</html>");
										return;
									}
									int len = sb.length();
									if (len > 12) {
										sb.insert(12, "<br>");
									}
									saveinfo2.setText("<html>并保存在" + sb.toString() + "</html>");
								}
							}).start();
						}
					} else {
						screenshot();
					}
				} else if (key == KeyEvent.VK_ESCAPE) {
					if (isScreen) {
						clenScreen(false);
						return;
					}
					frame.dispose();
				}
			}
		});
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 400, 300);
	}

	/**
	 * 鼠标移动
	 * 
	 * @param e
	 */
	private void mouseMovedEvent(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int newx = 0;
		int newy = 0;
		if (x > width - 131) {
			newx = x - 131;
		} else {
			newx = x + 15;
		}

		if (y > height - 44) {
			newy = y - 29;
		} else {
			newy = y + 10;
		}
		lab_h.setBounds(0, y, width, 1);
		lab_v.setBounds(x, 0, 1, height);
		if (!lblEsc.getText().equals("右键 或 Esc取消截图")) {
			lblEsc.setText("Enter键保存，右键 或 Esc取消截图");
			lblEsc.setBounds(newx, newy, 200, 24);
		} else {
			lblEsc.setLocation(newx, newy);
		}
		if (!lblEsc.isVisible()) {
			lblEsc.setVisible(true);
		}
		if (!lab_h.isVisible()) {
			lab_h.setVisible(true);
		}
		if (!lab_v.isVisible()) {
			lab_v.setVisible(true);
		}
		frame.repaint(0);

	}

	/**
	 * 取消截图
	 */
	private void clenScreen(boolean isSave) {
		openBs.setVisible(true);
		isScreen = false;
		lab_screen.setVisible(false);
		lab_h.setVisible(false);
		lab_v.setVisible(false);
		lblEsc.setVisible(false);
		label.setVisible(true);
		background.setBounds(0, 0, 400, 300);
		background.setIcon(ImageUtil.backImg);
		if (isSave) {
			saveinfo1.setVisible(true);
			saveinfo2.setVisible(true);
		}
		info.setVisible(true);
		frame.setBounds(100, 100, 400, 300);
	}

	private void screenshoting(MouseEvent e) {
		if (lab_h.isVisible()) {
			lab_h.setVisible(false);
			lab_v.setVisible(false);
		}
		int xx = e.getX();
		int yy = e.getY();
		int newx = 0;
		int newy = 0;
		if (xx > width - 131) {
			newx = xx - 131;
		} else {
			newx = xx + 15;
		}

		if (yy > height - 44) {
			newy = yy - 29;
		} else {
			newy = yy + 10;
		}
		int w = xx - x;
		int h = yy - y;
		if (w > 0 && h > 0) {
			lab_screen.setBounds(x, y, w, h);
			lblEsc.setText(w + "," + h);
		} else if (w > 0 && h < 0) {
			lab_screen.setBounds(x, y + h, w, -h);
			lblEsc.setText(w + "," + -h);
		} else if (w < 0 && h > 0) {
			lab_screen.setBounds(x + w, y, -w, h);
			lblEsc.setText(-w + "," + h);
		} else if (w < 0 && h < 0) {
			lab_screen.setBounds(x + w, y + h, -w, -h);
			lblEsc.setText(-w + "," + -h);
		}

		lblEsc.setBounds(newx, newy, 100, 24);
		frame.repaint(0);
	}

	/**
	 * 截图
	 */
	private void screenshot() {
		bounds = null;
		isScreen = true;
		frame.setVisible(false);
		openBs.setVisible(false);
		info.setVisible(false);
		label.setVisible(false);
		saveinfo1.setVisible(false);
		saveinfo2.setVisible(false);
		frame.setBounds(0, 0, width, height);
		background.setBounds(0, 0, width, height);
		screenShot = ImageUtil.getScreenShot(width, height);
		background.setIcon(new ImageIcon(screenShot));
		frame.setVisible(true);
	}
}
