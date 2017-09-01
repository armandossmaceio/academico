package br.ufpi.es.academico.view.gui;

import javax.swing.JFrame;

/**
 * Classe que monta a aplicacao GUI
 * @author armandosoaressousa
 *
 */
public class MainGui {

	public static void main(String[] args) {
		TelaPrincipalGui tp = new TelaPrincipalGui();
		tp.setSize(400, 375);
		tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//tp.setLocationRelativeTo(null);
		tp.setResizable(false);
		tp.setVisible(true);
	}

}
