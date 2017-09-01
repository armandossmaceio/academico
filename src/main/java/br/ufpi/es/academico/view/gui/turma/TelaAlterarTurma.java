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
import br.ufpi.es.academico.system.exception.turma.TurmaNaoExistenteException;
import br.ufpi.es.academico.system.util.ExpressaoRegular;

/**
 * Classe que monta a Tela para Alterar turma
 * @author armandosoaressousa
 *
 */
public class TelaAlterarTurma extends JDialog {
	
	private static final long serialVersionUID = 1L;

	private Fachada fachada;

	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;

	private JPanel painelForm;

	// Labels dos campos
	private JPanel painelEsquerda;
	private JLabel labelDisciplinaBusca;
	private JLabel labelDepartamento;
	private JLabel labelDisciplina;
	private JLabel labelCargaHoraria;

	// Campos de texto
	private JPanel painelDireita;
	private JPanel painelBusca;
	private JTextField txtDisciplinaBusca;
	private JButton buttonBuscar;
	private JTextField txtDepartamento;
	private JTextField txtDisciplina;
	private JTextField txtCargaHoraria;

	// Botões
	private JPanel painelInferior;
	private JButton botaoLimpar;
	private JButton botaoAlterar;

	/**
	 * Monta a Tela de alterar turma
	 * @param f fachada do sistema
	 */
	public TelaAlterarTurma(Fachada f) {
		// Configurações do dialog
		setTitle("Alterar Turma");
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
		labelTitulo = new JLabel("Alterar Turma");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);

		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		painelEsquerda = new JPanel(new GridLayout(4, 1, 10, 10));
		labelDisciplinaBusca = new JLabel("Informe o identificador:");
		labelDisciplinaBusca.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelDepartamento = new JLabel("Departamento:");
		labelDepartamento.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelDisciplina = new JLabel("Disciplina:");
		labelDisciplina.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelCargaHoraria = new JLabel("Carga Horária:");
		labelCargaHoraria.setFont(new Font("sans-serif", Font.BOLD, 12));
		painelEsquerda.add(labelDisciplinaBusca);
		painelEsquerda.add(labelDepartamento);
		painelEsquerda.add(labelDisciplina);
		painelEsquerda.add(labelCargaHoraria);

		painelDireita = new JPanel(new GridLayout(4, 1, 10, 10));
		painelBusca = new JPanel(new BorderLayout(10, 0));
		txtDisciplinaBusca = new JTextField();
		buttonBuscar = new JButton("Buscar");
		buttonBuscar.setFont(new Font("sans-serif", Font.BOLD, 13));
		// Adiciona o listener ao botão "Buscar"
		buttonBuscar.addActionListener(new ActionListener() {
			//TO DO acrescentar o campo para mostrar o id da turma
			
			public void actionPerformed(ActionEvent e) {
				String idTurma = txtDisciplinaBusca.getText();
				if (idTurma.compareTo("") != 0) { // verifica se o usuário o id da turma
					try {
						Turma turma = fachada.buscarTurma(Integer.valueOf(idTurma));
						txtDepartamento.setText(turma.getDepartamento());
						txtDisciplina.setText(turma.getDisciplina());
						txtCargaHoraria.setText(String.valueOf(turma.getCargaHoraria()));
					} catch (TurmaNaoExistenteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage()
								+ ".", "Turma Não Existente",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Você deve informar o identificador da turma.",
							"Campo obrigatório", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		painelBusca.add(txtDisciplinaBusca, BorderLayout.CENTER);
		painelBusca.add(buttonBuscar, BorderLayout.EAST);
		txtDepartamento = new JTextField();
		txtDisciplina = new JTextField();
		txtCargaHoraria = new JTextField();
		painelDireita.add(painelBusca);
		painelDireita.add(txtDepartamento);
		painelDireita.add(txtDisciplina);
		painelDireita.add(txtCargaHoraria);

		painelForm.add(painelEsquerda, BorderLayout.WEST);
		painelForm.add(painelDireita, BorderLayout.CENTER);

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

		botaoAlterar = new JButton("Alterar");
		botaoAlterar.setFont(new Font("sans-serif", Font.BOLD, 13));

		// Adiciona listener do botão "Alterar"
		botaoAlterar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Turma turma = new Turma("","",0);
				
					String idTurma = txtDisciplinaBusca.getText();
					if (idTurma.compareTo("") != 0) { // verifica id da turma
						try {
							turma = fachada.buscarTurma(Integer.valueOf(idTurma));
						} catch (TurmaNaoExistenteException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage()
									+ ".", "Turma Não Existente",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(),
									"Erro", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Você deve informar o identificador da turma.",
								"Campo obrigatório", JOptionPane.ERROR_MESSAGE);
					}
				

				

				try {	
					if (isDadosValidos()) {
						Turma tAux = new Turma(txtDepartamento.getText(),txtDisciplina.getText(), Integer.valueOf(txtCargaHoraria.getText()));
						fachada.alterarTurma(turma.getIdTurma(), tAux);
						JOptionPane.showMessageDialog(null,
							"Turma alterada com sucesso.",
							"Turma Alterada",
							JOptionPane.INFORMATION_MESSAGE);
						}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Não foi possível alterar a turma.", "Erro",
							JOptionPane.ERROR_MESSAGE);
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
					erro += "- CArga Horaria.\n";
					dadosValidos = false;
				}
				if (!expressao.apenasNumeros(txtCargaHoraria.getText())){
					erro += "- Carga horária deve conter apenas numeros.\n";
					dadosValidos = false;					
				}

				if (!dadosValidos){
				JOptionPane.showMessageDialog(null, erro, "Dados Inválidos",
						JOptionPane.ERROR_MESSAGE);
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
