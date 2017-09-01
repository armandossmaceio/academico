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
import br.ufpi.es.academico.model.Professor;

public class TelaInserirProfessor extends JDialog {

	private static final long serialVersionUID = 1L;

	private Fachada fachada;

	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTituloMenu;

	private JPanel painelForm;

	// Labels dos campos
	private JPanel painelEsquerda;
	private JLabel labelCpf;
	private JLabel labelNome;
	private JLabel labelLotacao;
	private JLabel labelTitulo;

	// Campos de texto
	private JPanel painelDireita;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtLotacao;
	private JTextField txtTitulo;

	// Botões
	private JPanel painelInferior;
	private JButton botaoLimpar;
	private JButton botaoInserir;

	public TelaInserirProfessor(Fachada f) {
		// Configurações do dialog
		setTitle("Inserir Professor");
		setModal(true);
		setSize(500, 290);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout()); // Altera gerenciador de layout padrão

		fachada = f;

		// Insere os componentes no dialog
		painelSuperior = new JPanel();
		painelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		labelTituloMenu = new JLabel("Inserir Professor");
		labelTituloMenu.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTituloMenu.setForeground(Color.BLUE);
		painelSuperior.add(labelTituloMenu);

		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		painelEsquerda = new JPanel(new GridLayout(4, 1, 10, 10));
		labelCpf = new JLabel("Informe o CPF:");
		labelCpf.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelNome = new JLabel("Informe o nome:");
		labelNome.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelLotacao = new JLabel("Informe a lotacao:");
		labelLotacao.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelTitulo = new JLabel("Informe o titulo:");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 12));
		painelEsquerda.add(labelCpf);
		painelEsquerda.add(labelNome);
		painelEsquerda.add(labelLotacao);
		painelEsquerda.add(labelTitulo);

		painelDireita = new JPanel(new GridLayout(4, 1, 10, 10));
		txtCpf = new JTextField();
		txtNome = new JTextField();
		txtLotacao = new JTextField();
		txtTitulo = new JTextField();
		painelDireita.add(txtCpf);
		painelDireita.add(txtNome);
		painelDireita.add(txtLotacao);
		painelDireita.add(txtTitulo);

		painelInferior = new JPanel();
		painelInferior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		botaoLimpar = new JButton("Limpar");
		botaoLimpar.setFont(new Font("sans-serif", Font.BOLD, 13));

		// Adiciona listener do botão "limpar"
		botaoLimpar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				txtCpf.setText("");
				txtNome.setText("");
				txtLotacao.setText("");
				txtTitulo.setText("");
			}
		});

		botaoInserir = new JButton("Inserir");
		botaoInserir.setFont(new Font("sans-serif", Font.BOLD, 13));

		// Adiciona listener do botão "Inserir"
		botaoInserir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (isDadosValidos()) {
					Professor professor = new Professor(txtCpf.getText(),
							txtNome.getText(), txtLotacao.getText(), txtTitulo
									.getText());
					try {
						fachada.inserirProfessor(professor);

						JOptionPane.showMessageDialog(null,
								"Professor inserido com sucesso.",
								"Professor Inserido",
								JOptionPane.INFORMATION_MESSAGE);
						
						txtCpf.setText("");
						txtNome.setText("");
						txtLotacao.setText("");
						txtTitulo.setText("");
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Professor Nao Inserido",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			/**
			 * Valida o formulário
			 * 
			 * @return true se os dados do formulário forem válidos. false caso
			 *         contrário.
			 */
			public boolean isDadosValidos() {
				boolean dadosValidos = true;
				String erro = "Os seguintes campos apresentam erros:\n";

				if (txtCpf.getText().trim().length() == 0) {
					erro += "- CPF.\n";
					dadosValidos = false;
				}
				if (txtNome.getText().trim().length() == 0) {
					erro += "- Nome.\n";
					dadosValidos = false;
				}
				if (txtLotacao.getText().trim().length() == 0) {
					erro += "- Lotacao.\n";
					dadosValidos = false;
				}
				if (txtTitulo.getText().trim().length() == 0) {
					erro += "- Titulo.\n";
					dadosValidos = false;
				}

				if(!dadosValidos){
					JOptionPane.showMessageDialog(null, erro, "Dados Inválidos",
						JOptionPane.ERROR_MESSAGE);
				}
				return dadosValidos;
			}
		});

		painelInferior.add(botaoLimpar);
		painelInferior.add(botaoInserir);

		painelForm.add(painelEsquerda, BorderLayout.WEST);
		painelForm.add(painelDireita, BorderLayout.CENTER);

		add(painelSuperior, BorderLayout.NORTH);
		add(painelForm, BorderLayout.CENTER);
		add(painelInferior, BorderLayout.SOUTH);

		setVisible(true); // Exibe o dialog

	}
}
