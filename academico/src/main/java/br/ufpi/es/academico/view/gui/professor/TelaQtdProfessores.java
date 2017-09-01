package br.ufpi.es.academico.view.gui.professor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.ufpi.es.academico.controller.Fachada;

public class TelaQtdProfessores extends JDialog {

	private static final long serialVersionUID = 1L;

	private Fachada fachada;

	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;

	private JPanel painelForm;

	private JLabel labelQtd;

	public TelaQtdProfessores(Fachada f) {
		// Configurações do dialog
		setTitle("Quantidade de Professores");
		setModal(true);
		setSize(500, 125);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout()); // Altera gerenciador de layout padrão

		fachada = f;

		// Insere os componentes no dialog
		painelSuperior = new JPanel();
		painelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		labelTitulo = new JLabel("Quantidade de Professores");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);

		painelForm = new JPanel();

		try {
			int qtd = fachada.quantidadeProfessores(); // conculta a quantidade de
													// alunos
			labelQtd = new JLabel("Quantidade de professores: " + qtd);
			labelQtd.setFont(new Font("sans-serif", Font.BOLD, 12));
			painelForm.add(labelQtd);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}

		add(painelSuperior, BorderLayout.NORTH);
		add(painelForm, BorderLayout.CENTER);

		setVisible(true);
	}
}
