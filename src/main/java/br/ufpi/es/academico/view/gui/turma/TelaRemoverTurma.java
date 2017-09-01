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
import br.ufpi.es.academico.system.exception.turma.TurmaNaoExistenteException;

/**
 * Classe que monta a Tela remover turma
 * @author armandosoaressousa
 *
 */
public class TelaRemoverTurma extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private Fachada fachada;
	
	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;
	
	private JPanel painelForm;
	
	// Labels dos campos
	private JPanel painelEsquerda;
	private JLabel labelDisciplinaRemover;
	
	// Campos de texto
	private JPanel painelDireita;
	private JPanel painelRemover;
	private JTextField txtDisciplinaRemover;
	private JButton buttonRemover;
		
	/**
	 * Monta a Tela remover turma
	 * @param f fachada do sistema
	 */
	public TelaRemoverTurma(Fachada f) {
		// Configurações do dialog
		setTitle("Remover Turma");
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
		labelTitulo = new JLabel("Remover Turma");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);
		
		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		painelEsquerda = new JPanel(new GridLayout(1, 1, 10, 10));
		labelDisciplinaRemover = new JLabel("Informe o identificador:");
		labelDisciplinaRemover.setFont(new Font("sans-serif", Font.BOLD, 12));
		painelEsquerda.add(labelDisciplinaRemover);
		
		painelDireita = new JPanel(new GridLayout(1, 1, 10, 10));
		painelRemover = new JPanel(new BorderLayout(10, 0));
		txtDisciplinaRemover = new JTextField();
		buttonRemover = new JButton("Remover");
		buttonRemover.setFont(new Font("sans-serif", Font.BOLD, 13));
		// Adiciona o listener do botão "Remover"
		buttonRemover.addActionListener(new ActionListener() {
			//TO DO acrescentar o campo id da turma
			
			public void actionPerformed(ActionEvent e) {
				String idTurma = txtDisciplinaRemover.getText();
				if (idTurma.trim().length() != 0) {
					try {
						fachada.removerTurma(Integer.valueOf(idTurma));
						JOptionPane.showMessageDialog(
								null,"Turma removida",
								"Turma removida com sucesso.",
								JOptionPane.INFORMATION_MESSAGE);
						txtDisciplinaRemover.setText("");
					} catch (TurmaNaoExistenteException e1) {
						JOptionPane.showMessageDialog(
								null,
								e1.getMessage() + ".",
								"Turma Não Existente",
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
							"Você deve informar o identificador da turma.",
							"Campo obrigatório",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		painelRemover.add(txtDisciplinaRemover, BorderLayout.CENTER);
		painelRemover.add(buttonRemover, BorderLayout.EAST);
		
		painelDireita.add(painelRemover);
		
		painelForm.add(painelEsquerda, BorderLayout.WEST);
		painelForm.add(painelDireita, BorderLayout.CENTER);
		
		add(painelSuperior, BorderLayout.NORTH);
		add(painelForm, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
}