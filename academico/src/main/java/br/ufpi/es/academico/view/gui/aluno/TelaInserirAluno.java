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
import br.ufpi.es.academico.model.Professor;

import br.ufpi.es.academico.system.util.ExpressaoRegular;

/**
 * Classe que monta a Tela Inserir Aluno
 * @author Neto Araújo
 *
 */
public class TelaInserirAluno extends JDialog { 
	private static final long serialVersionUID = 1L;
	
	private Fachada fachada;
	
	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;
	
	private JPanel painelForm;
	
	// Labels dos campos
	private JPanel painelEsquerda;
	private JLabel labelNome;
	private JLabel labelMatricula;
	private JLabel labelCurso;
	
	// Campos de texto
	private JPanel painelDireita;
	private JTextField txtNome;
	private JTextField txtMatricula;
	private JTextField txtCurso;
	
	// Botões
	private JPanel painelInferior;
	private JButton botaoLimpar;
	private JButton botaoInserir;
	
	/**
	 * Monta a Tela inserir aluno
	 * @param f fachada unica do sistema
	 */
	public TelaInserirAluno(Fachada f) {
		// Configurações do dialog
		setTitle("Inserir Aluno");
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
		labelTitulo = new JLabel("Inserir Aluno");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);
		
		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		painelEsquerda = new JPanel(new GridLayout(3, 1, 10, 10));
		labelNome = new JLabel("Informe o nome:");
		labelNome.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelMatricula = new JLabel("Informe a matricula:");
		labelMatricula.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelCurso = new JLabel("Informe o curso:");
		labelCurso.setFont(new Font("sans-serif", Font.BOLD, 12));
		painelEsquerda.add(labelNome);
		painelEsquerda.add(labelMatricula);
		painelEsquerda.add(labelCurso);
		
		painelDireita = new JPanel(new GridLayout(3, 1, 10, 10));
		txtNome = new JTextField();
		txtMatricula = new JTextField();
		txtCurso = new JTextField();
		painelDireita.add(txtNome);
		painelDireita.add(txtMatricula);
		painelDireita.add(txtCurso);
		
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
		
		botaoInserir = new JButton("Inserir");
		botaoInserir.setFont(new Font("sans-serif", Font.BOLD, 13));
		
		// Adiciona listener do botão "Inserir"
		botaoInserir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (isDadosValidos()) {
					Aluno aluno = new Aluno(txtMatricula.getText(), txtNome.getText(), txtCurso.getText());
					try {
						fachada.inserirAluno(aluno);
						
						JOptionPane.showMessageDialog(
								null,
								"Aluno inserido com sucesso.",
								"Aluno Inserido",
								JOptionPane.INFORMATION_MESSAGE);
						
						txtMatricula .setText("");
						txtNome.setText("");
						txtCurso.setText("");
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Aluno Nao Inserido",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
			/**
			 * Valida o formulário
			 * @return true se os dados do formulário forem válidos. false caso contrário.
			 */
			public boolean isDadosValidos() {
				boolean dadosValidos = true;
				String erro = "Os seguintes campos apresentam erros:\n";
				ExpressaoRegular expressao = new ExpressaoRegular();
				
				if (txtNome.getText().trim().length() == 0) {
					erro += "- Nome.\n";
					dadosValidos = false;
				}
				if (!expressao.apenasLetras(txtNome.getText())){
					erro += "- Nome deve conter apenas letras.\n";
					dadosValidos = false;
				}
				if (txtMatricula.getText().trim().length() == 0) {
					erro += "- Matricula.\n";
					dadosValidos = false;
				}
				if (!expressao.apenasNumeros(txtMatricula.getText())){
					erro += "- Matricula deve conter apenas numeros.\n";
					dadosValidos = false;					
				}
				if (txtCurso.getText().trim().length() == 0) {
					erro += "- Curso.\n";
					dadosValidos = false;
				}
				if (!expressao.apenasLetras(txtCurso.getText())){
					erro += "- Curso deve conter apenas letras.\n";
					dadosValidos = false;
				}
				
				if (!dadosValidos){
					JOptionPane.showMessageDialog(null, erro, "Dados Invalidos", JOptionPane.ERROR_MESSAGE);
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
			
