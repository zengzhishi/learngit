package myplayer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class MouseListen extends MouseAdapter
{
	MediaPlayer media;
	public MouseListen(MediaPlayer media){
		this.media = media;
	}
	public void mouseClicked(MouseEvent e){
		if(e.getClickCount() == 1){
			media.text.setText("");
			int index = media.L.locationToIndex(e.getPoint());
			String yy = (String) media.mingcheng.get(index);
			String ss = (String) media.vector.get(index);
			File file = new File(ss,yy+".txt");
			if(file.exists()){
				FileReader in = null;
				BufferedReader br = null;
				try {
					in = new FileReader(ss + yy +".txt");
					br = new BufferedReader(in);
				} 
				catch (FileNotFoundException e1) {
					media.text.append("找不到指定wenjian");
				}
				try {
					String s = br.readLine();
					while(s != null){
						media.text.append(s+"\n");
						s = br.readLine();
					}
					br.close();
					media.text.append("\n");
				} 
				catch (IOException e2) {
					media.text.append("文件读取错误");
				}
			}
			else {
				media.text.append("找不到指点文件");
			}
		}
	}
}
