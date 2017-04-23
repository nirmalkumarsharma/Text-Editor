package com.nks.editor;


import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Editor extends JFrame
{
	private JTextArea jTextArea=new JTextArea(20,60);
	private JFileChooser jFileChooser=new JFileChooser();
	
	public Editor()
	{
		JScrollPane jScrollPane=new JScrollPane(jTextArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		/*FileFilter fileFilter=new FileNameExtensionFilter("Plain Text", "txt");
		jFileChooser.setFileFilter(fileFilter);*/
		
		add(jScrollPane);
		JMenuBar jMenuBar=new JMenuBar();
		setJMenuBar(jMenuBar);
		JMenu file=new JMenu("File");
		jMenuBar.add(file);
		file.add(Open);
		file.add(Save);
		file.addSeparator();
		file.add(Exit);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	AbstractAction Open=new AbstractAction("Open FIle") {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(jFileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				try {
					openFile(jFileChooser.getSelectedFile().getAbsolutePath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
	AbstractAction Save=new AbstractAction("Save File") {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				saveFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
	AbstractAction Exit=new AbstractAction("Exit") {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	
	public void openFile(String filename) throws IOException
	{
		FileReader fileReader=new FileReader(filename);
		jTextArea.read(fileReader, null);
		fileReader.close();
		setTitle(filename);
	}
	
	public void saveFile() throws IOException
	{
		if(jFileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			FileWriter fileWriter=new FileWriter(jFileChooser.getSelectedFile().getAbsolutePath());
			jTextArea.write(fileWriter);
		}
	}
	
	public static void main(String []args) {
		new Editor();
	}
}
