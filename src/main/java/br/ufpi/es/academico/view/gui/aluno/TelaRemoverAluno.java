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
import br.ufpi.es.academico.system.exception.aluno.AlunoNaoExistenteException;

/**
 * Classe que cria a Tela Remover Aluno
 * @author armandosoaressousa
 *
 */
public class TelaRemoverAluno extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private Fachada fachada;
	
	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;
	
	private JPanel painelForm;
	
	// Labels dos campos
	private JPanel painelEsquerda;
	private JLabel labelMatriculaRemover;
	
	// Campos de texto
	private JPanel painelDireita;
	private JPanel painelRemover;
	private JTextField txtMatriculaRemover;
	private JButton buttonRemover;
		
	/**
	 * Monta a tela remover aluno
	 * @param f fachada
	 */
	public TelaRemoverAluno(Fachada f) {
		// Configurações do dialog
		setTitle("Remover Aluno");
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
		labelTitulo = new JLabel("Remover Aluno");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);
		
		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		painelEsquerda = new JPanel(new GridLayout(1, 1, 10, 10));
		labelMatriculaRemover = new JLabel("Informe a matricula do aluno:");
		labelMatriculaRemover.setFont(new Font("sans-serif", Font.BOLD, 12));
		painelEsquerda.add(labelMatriculaRemover);
		
		painelDireita = new JPanel(new GridLayout(1, 1, 10, 10));
		painelRemover = new JPanel(new BorderLayout(10, 0));
		txtMatriculaRemover = new JTextField();
		buttonRemover = new JButton("Remover");
		buttonRemover.setFont(new Font("sans-serif", Font.BOLD, 13));
		// Adiciona o listener do botão "Remover"
		buttonRemover.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String matricula = txtMatriculaRemover.getText();
				if (matricula.trim().length() != 0) {
					try {
						fachada.removerAluno(matricula);
						JOptionPane.showMessageDialog(null,"Aluno removido com sucesso.","Aluno Removido",JOptionPane.INFORMATION_MESSAGE);
						txtMatriculaRemover.setText("");
					} catch (AlunoNaoExistenteException e1) {
						JOptionPane.showMessageDialog(
								null,
								e1.getMessage() + ".",
								"Aluno Nao Existente",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(
								null,
								e1.getMessage(),
								"Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(
							null,
							"Você deve informar a matrícula do aluno.",
							"Campo obrigatório",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		painelRemover.add(txtMatriculaRemover, BorderLayout.CENTER);
		painelRemover.add(buttonRemover, BorderLayout.EAST);
		
		painelDireita.add(painelRemover);
		
		painelForm.add(painelEsquerda, BorderLayout.WEST);
		painelForm.add(painelDireita, BorderLayout.CENTER);
		
		add(painelSuperior, BorderLayout.NORTH);
		add(painelForm, BorderLayout.CENTER);
		
		setVisible(true);
	}

}
