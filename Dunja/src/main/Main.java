package main;

import crud.Crud;
import gui.MainWindow;

public class Main {

	public static void main(String[] args) {
		Crud crud = new Crud();
		MainWindow mw = new MainWindow(crud);
		mw.setVisible1(true);
	}
}
