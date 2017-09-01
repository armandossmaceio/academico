package br.ufpi.es.academico.view.gui.aluno;

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
import br.ufpi.es.academico.model.Aluno;
import br.ufpi.es.academico.system.exception.aluno.AlunosNaoCadastradosException;

public class TelaListarAlunos extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private Fachada fachada;
	
	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;
	
	private JPanel painelCentral;
	private JTextArea txtAlunos;
	
	//para jtable
	private JTable tabela;
	private Vector<String> modelo = new Vector<String>();
	private Vector<Vector> dados = new Vector<Vector>(); 
	
	public TelaListarAlunos(Fachada f) {
		// Configurações do dialog
		setTitle("Listar Alunos");
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
		
		painelCentral = new JPanel(new BorderLayout());
		painelCentral.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		txtAlunos = new JTextArea();
		txtAlunos.setEditable(false);
		txtAlunos.setSelectedTextColor(Color.BLACK);
		txtAlunos.setSelectionColor(Color.WHITE);
		txtAlunos.setFont(new Font("monospaced", Font.PLAIN, 12));
		painelCentral.add(new JScrollPane(tabela));
							
		modelo.add("Nome");
		modelo.add("Matricula");
		modelo.add("Curso");
		
		Vector<String> subDado;
		
		
		try {
			List<Aluno> listaAlunos = fachada.listarAlunos(); // obtém a lista de alunos
			for (Aluno aluno : listaAlunos) {
				//para jtable
				subDado = new Vector<String>();
				
				subDado.addElement(aluno.getNome());
				subDado.addElement(aluno.getMatricula());
				subDado.addElement(aluno.getCurso());
				
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
    		
		} catch (AlunosNaoCadastradosException e) {
			JOptionPane.showMessageDialog(
					null,
					e.getMessage(),
					"Não há Alunos Cadastros",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					null,
					e.getMessage(),
					"Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
