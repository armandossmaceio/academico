package br.ufpi.es.academico.view.gui.turma;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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

import javax.swing.JTextField;

import br.ufpi.es.academico.controller.Fachada;
import br.ufpi.es.academico.model.Professor;
import br.ufpi.es.academico.model.Turma;
import br.ufpi.es.academico.system.exception.professor.ProfessorNaoExistenteException;

public class TelaMatricularProfessor extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID1 = 1L;

	/**
	 * 
	 */
	
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
	private JTextField txtCPF;

	// Botões
	private JPanel painelInferior;
	private JButton botaoLimpar;
	private JButton botaoAssociar;

	// Combobox
	private JPanel painelComboBoxTurmas;
	private JComboBox comboTurmas;  
	private JLabel label;
	private String idSelecionadoComboBox;

	 public TelaMatricularProfessor(Fachada f) {
		// TODO Auto-generated constructor stub
		 
		   setTitle("Associar professor a Turma");
			setModal(true);
			setSize(500, 250);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			setResizable(false);
			setLayout(new BorderLayout()); 
			
			fachada = f;

			// Insere os componentes no dialog
			painelSuperior = new JPanel();
			painelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
			labelTitulo = new JLabel("Associar professor");
			labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
			labelTitulo.setForeground(Color.BLUE);
			painelSuperior.add(labelTitulo);

			painelForm = new JPanel(new BorderLayout(10, 10));
			painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			painelEsquerda = new JPanel(new GridLayout(3, 1, 10, 10));
			labelMatricula = new JLabel("Informe o CPF:");
			labelMatricula.setFont(new Font("sans-serif", Font.BOLD, 12));
			labelIdentificador = new JLabel("Selecione a turma:");
			labelIdentificador.setFont(new Font("sans-serif", Font.BOLD, 12));
			painelEsquerda.add(labelMatricula);
			painelEsquerda.add(labelIdentificador);

			painelDireita = new JPanel(new GridLayout(3, 1, 10, 10));
			//new GridLayout(rows, cols, hgap, vgap)
			txtCPF = new JTextField();
		

			painelDireita.add(txtCPF);
//			painelDireita.add(txtIdentificador);

			painelInferior = new JPanel();
			painelInferior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
			botaoLimpar = new JButton("Limpar");
			botaoLimpar.setFont(new Font("sans-serif", Font.BOLD, 13));
			
			// Adiciona listener do botão "limpar"
			botaoLimpar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					txtCPF.setText("");
				
				}
			});

			botaoAssociar = new JButton("Associar");
			botaoAssociar.setFont(new Font("sans-serif", Font.BOLD, 13));
			
			Vector<String> idTurmasCombo = new Vector<String>();
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
					 
		
				
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Não é possivel fazer esta operação", JOptionPane.INFORMATION_MESSAGE);
				}
			
			// Adiciona listener do botão "Matricular"
			botaoAssociar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					if (isDadosValidos()) {
						
						try {
				
							/*
							 * pre-condicoes:
							 * o aluno deve existir em um repositorio valido, buscar o aluno valido
							 * a turma deve existir em um repositorio valido, buscar a turma valida
							 */
							Turma turma = fachada.buscarTurma(Integer.valueOf(idSelecionadoComboBox));
							//colocar as informacoes em uma lista com os dados dos alunos da turma						
							Professor professorAssociado,professorExiste;
							try{
									professorExiste = fachada.buscarProfessor(txtCPF.getText());
								
											if(turma.getProfessor() == null || turma.getProfessor().getCpf().compareTo(txtCPF.getText().toString()) != 0){
												 try{
		 
												       fachada.associarProfessorTurma(fachada.buscarProfessor(txtCPF.getText()), fachada.buscarTurma(Integer.valueOf(idSelecionadoComboBox)));
												     
														       	JOptionPane.showMessageDialog(null,
																"Professor associado com sucesso.",
																"Professor associado",
																JOptionPane.INFORMATION_MESSAGE);
														       	txtCPF.setText("");
												 }catch (ProfessorNaoExistenteException e1){
													 JOptionPane.showMessageDialog(null, e1.getMessage(),
																"Erro ao associar", JOptionPane.ERROR_MESSAGE);
												 }
										
									 }else{
										 JOptionPane.showMessageDialog(null,"professor já está associado a turma.", "Já Associado",JOptionPane.INFORMATION_MESSAGE);
									 }
							}catch (ProfessorNaoExistenteException e1){
								 JOptionPane.showMessageDialog(null, e1.getMessage(),
											"Professor nao existe", JOptionPane.INFORMATION_MESSAGE);
							 }
								
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(),
									"Erro para associar", JOptionPane.ERROR_MESSAGE);
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
		
							if (txtCPF.getText().trim().length() == 0) {
								erro += "- CPF.\n";
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
					painelInferior.add(botaoAssociar);
		
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
