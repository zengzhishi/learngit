package myplayer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


class WindowListen extends WindowAdapter
{
	MediaPlayer media;
	public WindowListen(MediaPlayer media){
		this.media = media;
	}
	public void windowClosing(WindowEvent e){
		media.dispose();
	}
	public void windowClosed(WindowEvent e){
		if(media.player != null)
		{
			media.player.close();
			media.player.deallocate();//�ͷ���ռ���ⲿ��Դ
			System.exit(0);
		}
	}
}

