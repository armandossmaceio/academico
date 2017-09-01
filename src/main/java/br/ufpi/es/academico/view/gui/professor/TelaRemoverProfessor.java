package br.ufpi.es.academico.view.gui.professor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufpi.es.academico.controller.Fachada;
import br.ufpi.es.academico.system.exception.professor.ProfessorNaoExistenteException;
import br.ufpi.es.academico.system.exception.professor.RemoverProfessorException;;

public class TelaRemoverProfessor extends JDialog {

	private static final long serialVersionUID = 1L;

	private Fachada fachada;

	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;

	private JPanel painelForm;

	// Labels dos campos
	private JPanel painelEsquerda;
	private JLabel labelCpfRemover;

	// Campos de texto
	private JPanel painelDireita;
	private JPanel painelRemover;
	private JTextField txtCpfRemover;
	private JButton buttonRemover;

	public TelaRemoverProfessor(Fachada f) {
		// Configurações do dialog
		setTitle("Remover Professor");
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
		labelTitulo = new JLabel("Remover Professor");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);

		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		painelEsquerda = new JPanel(new GridLayout(1, 1, 10, 10));
		labelCpfRemover = new JLabel("Informe o CPF do professor:");
		labelCpfRemover.setFont(new Font("sans-serif", Font.BOLD, 12));
		painelEsquerda.add(labelCpfRemover);

		painelDireita = new JPanel(new GridLayout(1, 1, 10, 10));
		painelRemover = new JPanel(new BorderLayout(10, 0));
		txtCpfRemover = new JTextField();
		buttonRemover = new JButton("Remover");
		buttonRemover.setFont(new Font("sans-serif", Font.BOLD, 13));
		// Adiciona o listener do botão "Remover"
		buttonRemover.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String cpf = txtCpfRemover.getText();
				if (cpf.trim().length() != 0) {
					try {
						fachada.removerProfessor(cpf);
						JOptionPane.showMessageDialog(null, "Professor removido com sucesso!","Informacao",
								JOptionPane.INFORMATION_MESSAGE);
						txtCpfRemover.setText("");
					} catch (ProfessorNaoExistenteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage()
								+ ".", "Professor Nao Existente",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (RemoverProfessorException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage()
								,"Erro ao remover Professor",
								JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Você deve informar o CPF do professor.",
							"Campo Obrigatório", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		painelRemover.add(txtCpfRemover, BorderLayout.CENTER);
		painelRemover.add(buttonRemover, BorderLayout.EAST);

		painelDireita.add(painelRemover);

		painelForm.add(painelEsquerda, BorderLayout.WEST);
		painelForm.add(painelDireita, BorderLayout.CENTER);

		add(painelSuperior, BorderLayout.NORTH);
		add(painelForm, BorderLayout.CENTER);

		setVisible(true);
	}

}
