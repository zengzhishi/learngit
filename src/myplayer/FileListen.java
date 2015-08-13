package myplayer;

import java.awt.FileDialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class FileListen extends WindowAdapter
{
	FileDialog f;
	public FileListen(FileDialog f){
		this.f = f;
	}
	public void windowClosing(WindowEvent e){
		f.dispose();
	}
}