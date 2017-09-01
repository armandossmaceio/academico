package br.ufpi.es.academico.view.gui.turma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.ufpi.es.academico.controller.Fachada;
import br.ufpi.es.academico.model.Aluno;
import br.ufpi.es.academico.model.Turma;

import br.ufpi.es.academico.system.exception.turma.TurmaNaoExistenteException;

/**
 * Classe que monta a Tela matricular aluno em turma
 * @author armandosoaressousa
 *
 */
public class TelaMatricularAluno extends JDialog {

	private static final long serialVersionUID = 1L;

	private Fachada fachada;

	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;

	private JPanel painelForm;

	// Labels dos campos
	private JPanel painelEsquerda;
	private JLabel labelMatricula;
	private JLabel labelIdentificador;

	// Campos de texto
	private JPanel painelDireita;
	private JTextField txtmatricula;


	// Botões
	private JPanel painelInferior;
	private JButton botaoLimpar;
	private JButton botaoMatricular;

	// Combobox
	private JPanel painelComboBoxTurmas;
	private JComboBox comboTurmas;  
	private String idSelecionadoComboBox;
	
	/**
	 * Monta a Tela Matricular Aluno em Turma
	 * @param f fachada do sistema
	 */
	public TelaMatricularAluno(Fachada f) {
		// Configurações do dialog
		setTitle("Matricular aluno em Turma");
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
		labelTitulo = new JLabel("Matricular aluno");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);

		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		painelEsquerda = new JPanel(new GridLayout(3, 1, 10, 10));
		labelMatricula = new JLabel("Informe o matricula:");
		labelMatricula.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelIdentificador = new JLabel("Selecione a turma:");
		labelIdentificador.setFont(new Font("sans-serif", Font.BOLD, 12));
		painelEsquerda.add(labelMatricula);
		painelEsquerda.add(labelIdentificador);

		painelDireita = new JPanel(new GridLayout(3, 1, 10, 10));
		//new GridLayout(rows, cols, hgap, vgap)
		txtmatricula = new JTextField();

		painelDireita.add(txtmatricula);

		painelInferior = new JPanel();
		painelInferior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		botaoLimpar = new JButton("Limpar");
		botaoLimpar.setFont(new Font("sans-serif", Font.BOLD, 13));
		
		

		// Adiciona listener do botão "limpar"
		botaoLimpar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				txtmatricula.setText("");
				
			}
		});

		botaoMatricular = new JButton("Matricular");
		botaoMatricular.setFont(new Font("sans-serif", Font.BOLD, 13));
		
		Vector<String> idTurmasCombo = new Vector<String>();
		
		
		
		try {
			if(fachada.quantidadeTurma() != 0){
					
				try{

				
						List<Turma> turmas = fachada.listarTurmas();
						
								for (int i = 0; i < turmas.size(); i++) {
									idTurmasCombo.add(i, Integer.toString(turmas.get(i).getIdTurma()));
								}
								 comboTurmas = new JComboBox(idTurmasCombo);
								// evento que permite obter a fonte selecionada
								    comboTurmas.addActionListener(
								      new ActionListener(){
								        public void actionPerformed(ActionEvent e){
								          JComboBox cb = (JComboBox)e.getSource();
								         idSelecionadoComboBox = (String)cb.getSelectedItem();
								          //System.out.println(idSelecionadoComboBox);
											 
										       }
										      }
										    );
								 
						
								
							}catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
								
							}
					
			}else{
				JOptionPane.showMessageDialog(null,
						"Não é possivel matricular aluno."
						+ "\nNão há turmas cadastradas.",
						"Não há turmas cadastradas",
						JOptionPane.INFORMATION_MESSAGE);			
							
			}
		} catch (HeadlessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		
		// Adiciona listener do botão "Matricular"
		botaoMatricular.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (isDadosValidos()) {
					
					try {
						 boolean flagAlunoJaMatriculado = false;
						/*
						 * pre-condicoes:
						 * o aluno deve existir em um repositorio valido, buscar o aluno valido
						 * a turma deve existir em um repositorio valido, buscar a turma valida
						 */
						Turma turma = fachada.buscarTurma(Integer.valueOf(idSelecionadoComboBox));
						//colocar as informacoes em uma lista com os dados dos alunos da turma						
						List<Aluno> listaAlunosAux;
						listaAlunosAux = fachada.buscarTurma(turma.getIdTurma()).getAlunos();
						 for (int i = 0; i < listaAlunosAux.size(); i++) {
							if( listaAlunosAux.get(i).getMatricula().compareTo(txtmatricula.getText().toString()) == 0 ){
							   flagAlunoJaMatriculado = true;
								break;
							}
						 }
						 if(flagAlunoJaMatriculado == false){
							fachada.matricularAlunoTurma(fachada.buscarAluno(txtmatricula.getText()), fachada.buscarTurma(Integer.valueOf(idSelecionadoComboBox)));
							JOptionPane.showMessageDialog(null,
									"Aluno matriculado com sucesso.",
									"Aluno matriculado",
									JOptionPane.INFORMATION_MESSAGE);

							//txtIdentificador.setText("");
							txtmatricula.setText("");
						 }else{
							 JOptionPane.showMessageDialog(null,"Aluno já está matriculado nesta turma.", "Já matriculado",JOptionPane.INFORMATION_MESSAGE);
						 }
							
					
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Aluno nao matriculado", JOptionPane.ERROR_MESSAGE);
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

				if (txtmatricula.getText().trim().length() == 0) {
					erro += "- matricula.\n";
					dadosValidos = false;
				}
				if (idSelecionadoComboBox == null) {
					erro += "- Selecione uma turma.\n";
					dadosValidos = false;
				}

				if (!dadosValidos) {
					JOptionPane.showMessageDialog(null, erro,
							"Dados Inválidos", JOptionPane.ERROR_MESSAGE);
				}
				return dadosValidos;
			}
			
		});
     
		if(comboTurmas != null){
			painelDireita.add(comboTurmas);	
			painelInferior.add(botaoLimpar);
			painelInferior.add(botaoMatricular);

			painelForm.add(painelEsquerda, BorderLayout.WEST);
			painelForm.add(painelDireita, BorderLayout.CENTER);

			add(painelSuperior, BorderLayout.NORTH);
			add(painelForm, BorderLayout.CENTER);
			add(painelInferior, BorderLayout.SOUTH);

			setVisible(true); // Exibe o dialog
		}else{
			
			setVisible(false);
		}
		
	
	}

}