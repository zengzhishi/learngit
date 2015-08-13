package myplayer;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Set;
import java.util.Vector;

import javax.media.ControllerListener;
import javax.media.Player;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;


class MediaPlayer extends JFrame implements ActionListener,ControllerListener,Runnable
{
	FileListen fL=null;
	WindowListen wL=null;
	MouseListen mL=null;
	JScrollPane p1,p2;
	JMenuBar menubar;
	JMenu File,Play,Help;
	JMenuItem Open,Add,Close,Exit,Stop,PlayPause,Repeat,about;
	JList L;
	JLabel stage;
	JPanel playerPanel;
	JTextArea text;
	FileDialog filedialog_open;
	boolean Loop=false,Pause=false;
	Player player;
	Component visualComponent,controlComponent;//可视部件，控制部件
	String str,currentDirectory,fileName,currentPath;
	int x=100,y=100,height=400,width=600;
	public Vector vector,mingcheng;
	ImageIcon icon,icon2;
	ImageIcon imageIcon = new ImageIcon("蝴蝶。jpg");//图片路径
	Thread thread;
	public MediaPlayer() 
	{
		wL = new WindowListen(this);
		mL = new MouseListen(this);
		vector = new Vector();  //构造一个JList，使其显示指定Vector中的元素
		mingcheng = new Vector();
		L = new JList(mingcheng);
		L.setFixedCellWidth(120);
		L.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		L.setForeground(new Color(149, 55, 164));
		L.setBackground(new Color(0, 130, 200));
		p1 = new JScrollPane(L);
		text = new JTextArea(20, 9);
		text.setForeground(new Color(145, 55, 164));
		text.setBackground(new Color(0, 130, 200));
		p2 = new JScrollPane(text);
		stage = new JLabel("    "+"媒体播放器");
		stage.setForeground(new Color(149, 55, 164));
		stage.setFont(new Font("华文行楷",Font.BOLD,30));
		playerPanel = new JPanel()
		{
			Image image1 = imageIcon.getImage();
			Image grayImage = GrayFilter.createDisabledImage(image1);
			{
				setOpaque(false);//设置不透明
			}
			public void paint(Graphics g){
				g.drawImage(imageIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
				super.paint(g);//Component
			}
		};
		playerPanel.setLayout(new BorderLayout());
		playerPanel.setBackground(Color.BLACK);
		add(playerPanel,BorderLayout.CENTER);
		playerPanel.add(stage,BorderLayout.CENTER);
		menubar = new JMenuBar();
		File = new JMenu("文件");
		Play = new JMenu("播放");
		Help = new JMenu("说明");
		Open = new JMenuItem("打开");
		Add = new JMenuItem("添加列表");
		Close = new JMenuItem("关闭");
		Exit = new JMenuItem("退出");
		PlayPause = new JMenuItem("开始/暂停");
		about = new JMenuItem("关于");
		Repeat = new JMenuItem("循环");
		filedialog_open = new FileDialog(this, "打开文件", FileDialog.LOAD);
		fL = new FileListen(filedialog_open);
		filedialog_open.addWindowListener(fL);
		setJMenuBar(menubar);
		menubar.add(File);
		menubar.add(Play);
		menubar.add(Help);
		File.add(Open);
		File.add(Add);
		File.add(Close);
		File.add(Exit);
		File.add(PlayPause);
		File.add(Stop);
		File.add(Repeat);
		File.add(about);
		add(p1, BorderLayout.WEST);
		add(p2,BorderLayout.EAST);
		Open.addActionListener(this);
		Add.addActionListener(this);
		Close.addActionListener(this);
		Exit.addActionListener(this);
		PlayPause.addActionListener(this);
		Repeat.addActionListener(this);
		Stop.addActionListener(this);
		about.addActionListener(this);
		L.addMouseListener(mL);
		addWindowListener(wL);
		validate();//保证组件有一种合法的布局，主要用在容器事件的处理上
		setTitle("简单媒体播放器");
		setBounds(x,y,width,height);
		setVisible(true);
	}
	
	
}
