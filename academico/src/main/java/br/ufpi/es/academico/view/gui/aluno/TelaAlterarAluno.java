package br.ufpi.es.academico.view.gui.aluno;

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
import br.ufpi.es.academico.system.exception.aluno.AlunoNaoExistenteException;

/**
 * Classe que monta a Tala Alterar Aluno
 * @author armandosoaressousa
 *
 */
public class TelaAlterarAluno extends JDialog {

	private static final long serialVersionUID = 1L;

	private Fachada fachada;

	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;

	private JPanel painelForm;

	// Labels dos campos
	private JPanel painelEsquerda;
	private JLabel labelMatriculaBusca;
	private JLabel labelNome;
	private JLabel labelMatricula;
	private JLabel labelCurso;

	// Campos de texto
	private JPanel painelDireita;
	private JPanel painelBusca;
	private JTextField txtMatriculaBusca;
	private JButton buttonBuscar;
	private JTextField txtNome;
	private JTextField txtMatricula;
	private JTextField txtCurso;

	// Botões
	private JPanel painelInferior;
	private JButton botaoLimpar;
	private JButton botaoAlterar;

	/**
	 * Monta a tela alterar aluno
	 * - Dado um aluno existente, busca no repositorio, passa os dados alterados
	 * @param f fachada
	 */
	public TelaAlterarAluno(Fachada f) {
		// Configurações do dialog
		setTitle("Alterar Aluno");
		setModal(true);
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout()); // Altera gerenciador de layout padrão

		fachada = f;

		// Insere os componentes no dialog
		painelSuperior = new JPanel();
		painelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		labelTitulo = new JLabel("Alterar Aluno");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);

		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		painelEsquerda = new JPanel(new GridLayout(4, 1, 10, 10));
		labelMatriculaBusca = new JLabel("Informe a matrícula do aluno:");
		labelMatriculaBusca.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelNome = new JLabel("Nome:");
		labelNome.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelMatricula = new JLabel("Matrícula:");
		labelMatricula.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelCurso = new JLabel("Curso:");
		labelCurso.setFont(new Font("sans-serif", Font.BOLD, 12));
		painelEsquerda.add(labelMatriculaBusca);
		painelEsquerda.add(labelNome);
		painelEsquerda.add(labelMatricula);
		painelEsquerda.add(labelCurso);

		painelDireita = new JPanel(new GridLayout(4, 1, 10, 10));
		painelBusca = new JPanel(new BorderLayout(10, 0));
		txtMatriculaBusca = new JTextField();
		buttonBuscar = new JButton("Buscar");
		buttonBuscar.setFont(new Font("sans-serif", Font.BOLD, 13));
		// Adiciona o listener ao botão "Buscar"
		buttonBuscar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String matricula = txtMatriculaBusca.getText();
				if (matricula.compareTo("") != 0) { // verifica se o usuário
													// preencheu a matrícula
					try {
						Aluno aluno = fachada.buscarAluno(matricula);
						txtNome.setText(aluno.getNome());
						txtMatricula.setText(aluno.getMatricula());
						txtCurso.setText(aluno.getCurso());
					} catch (AlunoNaoExistenteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage()
								+ ".", "Aluno Nao Existente",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Você deve informar a matrícula do aluno.",
							"Campo obrigatario", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		painelBusca.add(txtMatriculaBusca, BorderLayout.CENTER);
		painelBusca.add(buttonBuscar, BorderLayout.EAST);
		txtNome = new JTextField();
		txtMatricula = new JTextField();
		txtCurso = new JTextField();
		painelDireita.add(painelBusca);
		painelDireita.add(txtNome);
		painelDireita.add(txtMatricula);
		painelDireita.add(txtCurso);

		painelForm.add(painelEsquerda, BorderLayout.WEST);
		painelForm.add(painelDireita, BorderLayout.CENTER);

		painelInferior = new JPanel();
		painelInferior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		botaoLimpar = new JButton("Limpar");
		botaoLimpar.setFont(new Font("sans-serif", Font.BOLD, 13));

		// Adiciona listener do botão "limpar"
		botaoLimpar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtMatricula.setText("");
				txtCurso.setText("");
			}
		});

		botaoAlterar = new JButton("Alterar");
		botaoAlterar.setFont(new Font("sans-serif", Font.BOLD, 13));

		// Adiciona listener do botão "Alterar"
		botaoAlterar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Aluno alunoAux = new Aluno("","","");
				if (isDadosValidos()) {
					String matricula = txtMatriculaBusca.getText();
					try {
						alunoAux = fachada.buscarAluno(matricula);
					} catch (AlunoNaoExistenteException e2) {
						JOptionPane.showMessageDialog(null,"Aluno nao existe",e2.getMessage(),JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null,"Erro ao buscar aluno",e2.getMessage(),JOptionPane.INFORMATION_MESSAGE);
					}
					Aluno a2 = new Aluno(txtMatricula.getText(), txtNome.getText(),txtCurso.getText());
					
					try {
						fachada.alterarAluno(a2);
						JOptionPane.showMessageDialog(null,"Aluno alterado com sucesso.","Aluno Alterado",JOptionPane.INFORMATION_MESSAGE);
						txtMatriculaBusca.setText("");
						txtMatricula.setText("");
						txtNome.setText("");
						txtCurso.setText("");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Nao foi possivel alterar o aluno.", "Erro",JOptionPane.ERROR_MESSAGE);
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

				if (txtNome.getText().equals("")) {
					erro += "- Nome.\n";
					dadosValidos = false;
				}
				if (txtMatricula.getText().equals("")) {
					erro += "- Matrícula.\n";
					dadosValidos = false;
				}
				if (txtCurso.getText().equals("")) {
					erro += "- Curso.\n";
					dadosValidos = false;
				}

				if (!dadosValidos){
					JOptionPane.showMessageDialog(null, erro, "Dados Invalidos", JOptionPane.ERROR_MESSAGE);
				}

				return dadosValidos;
			}
		});

		painelInferior.add(botaoLimpar);
		painelInferior.add(botaoAlterar);

		add(painelSuperior, BorderLayout.NORTH);
		add(painelForm, BorderLayout.CENTER);
		add(painelInferior, BorderLayout.SOUTH);

		setVisible(true);
	}

}
