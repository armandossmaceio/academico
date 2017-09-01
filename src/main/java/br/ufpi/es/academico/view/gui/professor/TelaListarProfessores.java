package br.ufpi.es.academico.view.gui.professor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import br.ufpi.es.academico.controller.Fachada;
import br.ufpi.es.academico.model.Professor;
import br.ufpi.es.academico.system.exception.professor.ProfessoresNaoCadastradosException;

public class TelaListarProfessores extends JDialog {

	private static final long serialVersionUID = 1L;

	private Fachada fachada;

	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;

	private JPanel painelCentral;
	private JTextArea txtProfessores;
	
	//para jtable
		private JTable tabela;
		private Vector<String> modelo = new Vector<String>();
		private Vector<Vector> dados = new Vector<Vector>(); 

	public TelaListarProfessores(Fachada f) {
		// Configurações do dialog
		setTitle("Listar Professores");
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
		labelTitulo = new JLabel("Listar Professores");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);

		painelCentral = new JPanel(new BorderLayout());
		painelCentral.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		txtProfessores = new JTextArea();
		txtProfessores.setEditable(false);
		txtProfessores.setSelectedTextColor(Color.BLACK);
		txtProfessores.setSelectionColor(Color.WHITE);
		txtProfessores.setFont(new Font("monospaced", Font.PLAIN, 12));
		painelCentral.add(new JScrollPane(tabela));
		
		
		modelo.add("CPF");
		modelo.add("Nome");
		modelo.add("Lotação");
		modelo.add("Título");
		
		Vector<String> subDado;
		
		
		
		

		try {
			List<Professor> listaProfessores = fachada.listarProfessores(); // obtém a lista de professores
			
			for (Professor professor : listaProfessores) {
				
				//para jtable
				subDado = new Vector<String>();
				
				subDado.addElement(professor.getCpf());
				subDado.addElement(professor.getNome());
				subDado.addElement(professor.getLotacao());
				subDado.addElement(professor.getTitulo());
				dados.addElement(subDado);
			
			}
			tabela = new JTable(dados,modelo);
			tabela.setEnabled(false);
			tabela.getTableHeader().setReorderingAllowed(false);
			JScrollPane barraRolagem = new JScrollPane(tabela);
        	painelCentral.add(barraRolagem); 
			
        	add(painelSuperior, BorderLayout.NORTH);
    		add(painelCentral, BorderLayout.CENTER);

    		setVisible(true); // Exibe o dialog
    		
		} catch (ProfessoresNaoCadastradosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Nao ha Professores Cadastrados", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
