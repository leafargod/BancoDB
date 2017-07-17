package com.beeva.app.Form;

import javax.swing.JApplet;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Label;

public class FormBanco extends JApplet {

	/**
	 * Create the applet.
	 */
	public FormBanco() {
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JButton btnNewButton = new JButton("New button");
		mnNewMenu.add(btnNewButton);
		
		JButton btnNuevo = new JButton("Nuevo");
		mnNewMenu.add(btnNuevo);
		
		JButton btnSalir = new JButton("Salir");
		mnNewMenu.add(btnSalir);
		
		Label Guardar = new Label("New label");
		mnNewMenu.add(Guardar);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(getContentPane(), popupMenu);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
