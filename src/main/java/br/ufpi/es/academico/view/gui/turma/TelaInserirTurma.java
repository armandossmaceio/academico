package br.ufpi.es.academico.view.gui.turma;

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
import br.ufpi.es.academico.model.Aluno;
import br.ufpi.es.academico.model.Turma;
import br.ufpi.es.academico.system.util.ExpressaoRegular;

/**
 * Classe que monta a Tela Inserir Turma
 * @author armandosoaressousa
 *
 */
public class TelaInserirTurma extends JDialog {

	private static final long serialVersionUID = 1L;

	private Fachada fachada;

	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;

	private JPanel painelForm;

	// Labels dos campos
	private JPanel painelEsquerda;
	private JLabel labelDepartamento;
	private JLabel labelDisciplina;
	private JLabel labelCargaHoraria;

	// Campos de texto
	private JPanel painelDireita;
	private JTextField txtDepartamento;
	private JTextField txtDisciplina;
	private JTextField txtCargaHoraria;

	// Botões
	private JPanel painelInferior;
	private JButton botaoLimpar;
	private JButton botaoInserir;

	/**
	 * Monta a Tela Inserir Turma
	 * @param f fachada do sistema
	 */
	public TelaInserirTurma(Fachada f) {
		// Configurações do dialog
		setTitle("Inserir Turma");
		setModal(true);
		setSize(500, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout()); // Altera gerenciador de layout padrão

		fachada = f;

		// Insere os componentes no dialog
		painelSuperior = new JPanel();
		painelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		labelTitulo = new JLabel("Inserir Turma");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);

		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		painelEsquerda = new JPanel(new GridLayout(3, 1, 10, 10));
		labelDepartamento = new JLabel("Informe o departamento:");
		labelDepartamento.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelDisciplina = new JLabel("Informe a disciplina:");
		labelDisciplina.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelCargaHoraria = new JLabel("Informe a carga horária:");
		labelCargaHoraria.setFont(new Font("sans-serif", Font.BOLD, 12));
		painelEsquerda.add(labelDepartamento);
		painelEsquerda.add(labelDisciplina);
		painelEsquerda.add(labelCargaHoraria);

		painelDireita = new JPanel(new GridLayout(3, 1, 10, 10));
		txtDepartamento = new JTextField();
		txtDisciplina = new JTextField();
		txtCargaHoraria = new JTextField();
		painelDireita.add(txtDepartamento);
		painelDireita.add(txtDisciplina);
		painelDireita.add(txtCargaHoraria);

		painelInferior = new JPanel();
		painelInferior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		botaoLimpar = new JButton("Limpar");
		botaoLimpar.setFont(new Font("sans-serif", Font.BOLD, 13));

		// Adiciona listener do botão "limpar"
		botaoLimpar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				txtDepartamento.setText("");
				txtDisciplina.setText("");
				txtCargaHoraria.setText("");
			}
		});

		botaoInserir = new JButton("Inserir");
		botaoInserir.setFont(new Font("sans-serif", Font.BOLD, 13));

		// Adiciona listener do botão "Inserir"
		botaoInserir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (isDadosValidos()) {
					Turma turma = new Turma(txtDepartamento.getText(), 
							txtDisciplina.getText(), Integer.parseInt(txtCargaHoraria.getText()));
					try {
						fachada.inserirTurma(turma);

						JOptionPane.showMessageDialog(null,
								"Turma inserida com sucesso.",
								"Turma Inserida",
								JOptionPane.INFORMATION_MESSAGE);

						txtDisciplina.setText("");
						txtDepartamento.setText("");
						txtCargaHoraria.setText("");

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Turma Não Inserido", JOptionPane.ERROR_MESSAGE);
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
				ExpressaoRegular expressao = new ExpressaoRegular();

				if (txtDepartamento.getText().trim().length() == 0) {
					erro += "- Departamento.\n";
					dadosValidos = false;
				}
				
				if (txtDisciplina.getText().trim().length() == 0) {
					erro += "- Disciplina.\n";
					dadosValidos = false;
				}
				if (txtCargaHoraria.getText().trim().length() == 0) {
					erro += "- Carga horária.\n";
					dadosValidos = false;
				}
				if (!expressao.apenasNumeros(txtCargaHoraria.getText())){
					erro += "- Carga horária deve conter apenas numeros.\n";
					dadosValidos = false;					
				}

				if (!dadosValidos) {
					JOptionPane.showMessageDialog(null, erro,
							"Dados Inválidos", JOptionPane.ERROR_MESSAGE);
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