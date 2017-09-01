package br.ufpi.es.academico.view.gui.turma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Vector;
import java.lang.*;

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
import br.ufpi.es.academico.model.Turma;
import br.ufpi.es.academico.system.exception.aluno.AlunosNaoCadastradosException;
import br.ufpi.es.academico.system.exception.turma.TurmasNaoCadastradasException;

/**
 * Classe que cria a Tela de Listagem de Turmas
 * @author armandosoaressousa
 *
 */
public class TelaListarTurmas extends JDialog {
	
private static final long serialVersionUID = 1L;
	
	private Fachada fachada;
	
	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;
	
	private JPanel painelCentral;
	private JTextArea txtTurmas;
	
	//para jtable
	private JTable tabela;
	private Vector<String> modelo = new Vector<String>();
	private Vector<Vector> dados = new Vector<Vector>(); 
	
	/**
	 * Monta a Tela de Listagem de Turmas
	 * @param f fachada do sistema
	 */
	public TelaListarTurmas(Fachada f) {
		// Configurações do dialog
		setTitle("Listar Turmas");
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
		labelTitulo = new JLabel("Listar Turmas");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);
		
		painelCentral = new JPanel(new BorderLayout());
		painelCentral.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		txtTurmas = new JTextArea();
		txtTurmas.setEditable(false);
		txtTurmas.setSelectedTextColor(Color.BLACK);
		txtTurmas.setSelectionColor(Color.WHITE);
		txtTurmas.setFont(new Font("monospaced", Font.PLAIN, 12));
//		painelCentral.add(new JScrollPane(txtTurmas));
		
		painelCentral.add(new JScrollPane(tabela));
		
		
		modelo.add("Id");
		modelo.add("Departamento");
		modelo.add("Disciplina");
		modelo.add("CH");
		
		Vector<String> subDado;

		try {
			List<Turma> listaTurmas = fachada.listarTurmas(); // obtém a lista de turmas
			for (Turma turma : listaTurmas) {
				
				//para jtable
				subDado = new Vector<String>();
				
				subDado.addElement(Integer.toString(turma.getIdTurma()));
				subDado.addElement(turma.getDepartamento());
				subDado.addElement(turma.getDisciplina());
				subDado.addElement(Integer.toString(turma.getCargaHoraria()));
				dados.addElement(subDado);
			
				
				// Adiciona a turma ao textArea
//				txtTurmas.append(String.format("%-20s%-20s%-20s\n",
//						"Departamento: " + turma.getDepartamento(), 
//						"Disciplina: " + turma.getDisciplina(), 
//						"CH: " + turma.getCargaHoraria()));
			}
			
			
			tabela = new JTable(dados,modelo);
			tabela.setEnabled(false);
			tabela.getTableHeader().setReorderingAllowed(false);
			JScrollPane barraRolagem = new JScrollPane(tabela);
        	painelCentral.add(barraRolagem); 
		
        	add(painelSuperior, BorderLayout.NORTH);
    		add(painelCentral, BorderLayout.CENTER);
    		
    		setVisible(true); // Exibe o dialog
    		
		} catch (TurmasNaoCadastradasException e) {
			JOptionPane.showMessageDialog(
					null,
					e.getMessage(),
					"Não há Turmas Cadastras",
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