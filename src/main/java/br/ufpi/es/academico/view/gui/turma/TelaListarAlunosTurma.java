package br.ufpi.es.academico.view.gui.turma;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.ufpi.es.academico.controller.Fachada;
import br.ufpi.es.academico.model.Aluno;
import br.ufpi.es.academico.model.Turma;
import br.ufpi.es.academico.system.exception.turma.TurmaNaoExistenteException;

/**
 * Classe que monta a Tela para listar alunos de uma turma
 * @author armandosoaressousa
 *
 */
public class TelaListarAlunosTurma extends JDialog {

	private static final long serialVersionUID = 1L;

	private Fachada fachada;

	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;

	private JPanel painelForm;

	// Labels dos campos
	//private JPanel painelEsquerda;
	private JLabel labelIDBusca;
	private JLabel labelProfessorTurma;
	static private JLabel labelDadosProfessor;




	// Combobox
	private JPanel painelComboBoxTurmas;
	private JComboBox<String> comboTurmas;  

	private String idSelecionadoComboBox = null;

	//botao Listar
	private JButton botaoListarAlunos;


	private JPanel painelCentral;
	private JPanel painelDadosProfessor;
	private JPanel painelTabela;


	//para jtable
	private JTable tabela;
	private Vector<String> modelo = new Vector<String>();
	private Vector<Vector> dados = new Vector<Vector>();
	private Vector<String> subDado;

	/**
	 * Monta a Tela de Listar turma
	 * @param f fachada do sistema
	 */
	public TelaListarAlunosTurma(Fachada f)  {
		// Configurações do dialog
		setTitle("Listar Turma");
		setModal(true);
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout()); // Altera gerenciador de layout padrão

		fachada = f;
		//dessenhando tela
		desenhandoPainel();

		//insercao de colunas da tabela
		Vector<String> idTurmasCombo = new Vector<String>();
		modelo.add("Nome");
		modelo.add("Matricula");



		try {
			List<Turma> turmas = fachada.listarTurmas();
			//				populando combobox
			idTurmasCombo.removeAllElements();
			for (int i = 0; i < turmas.size(); i++) {

				idTurmasCombo.add(i, Integer.toString(turmas.get(i).getIdTurma()));
			}
			comboTurmas = new JComboBox<String>(idTurmasCombo);
			// evento que permite obter a fonte selecionada
			comboTurmas.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){
							JComboBox cb = (JComboBox)e.getSource();
							idSelecionadoComboBox  = (String)cb.getSelectedItem();


						}
					}
					);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),"Não é possivel fazer esta operação", JOptionPane.INFORMATION_MESSAGE);
		}
		botaoListarAlunos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				painelCentral.removeAll();
				if(idSelecionadoComboBox != null){

					try {


						Turma turma = fachada.buscarTurma(Integer.valueOf(idSelecionadoComboBox));
						//insere os dados do professor para serem exibidos
						String  stringPRofessor;
						if(turma.getProfessor() != null){

							stringPRofessor = "["+ turma.getProfessor().getCpf() + "]  -  " + turma.getProfessor().getNome()  ;
						}else{
							stringPRofessor= "Nenhum professor associado a turma";

						}
						//setando os dados a serem apresentados do professor da turma
						apresentaProfessorLabel(labelDadosProfessor, stringPRofessor);
						List<Aluno> listaAlunosAux;
						listaAlunosAux = fachada.buscarTurma(turma.getIdTurma()).getAlunos();
						if (listaAlunosAux == null || listaAlunosAux.size() == 0){
							JOptionPane.showMessageDialog(null, "Nao existem alunos","Nao existem alunos matriculados nesta turma.",JOptionPane.INFORMATION_MESSAGE);
						}else{
							dados = new Vector<Vector>();
							for (Aluno aluno : listaAlunosAux) {
								subDado = new Vector<String>();
								subDado.addElement(aluno.getNome());
								subDado.addElement(aluno.getMatricula());
								dados.addElement(subDado);


							}
							tabela = new JTable(dados,modelo);
							tabela.setEnabled(false);
							tabela.getTableHeader().setReorderingAllowed(false);

							JScrollPane barraRolagem = new JScrollPane(tabela);
							//redesenhando painel com dados atualizados


							painelCentral.add(painelDadosProfessor,BorderLayout.NORTH);
							painelCentral.add(painelTabela,BorderLayout.CENTER);
							painelCentral.add(barraRolagem); 





						}

					} catch (TurmaNaoExistenteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage()+ ".", "Turma Não Existente",JOptionPane.INFORMATION_MESSAGE);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
					}


				}
				painelCentral.revalidate();

			}

			
		});


		if(comboTurmas != null){

			painelComboBoxTurmas.add(labelIDBusca, BorderLayout.WEST);
			painelComboBoxTurmas.add(comboTurmas, BorderLayout.CENTER);
			painelComboBoxTurmas.add(botaoListarAlunos,BorderLayout.EAST);

			add(painelCentral,BorderLayout.CENTER);
			add(painelComboBoxTurmas, BorderLayout.PAGE_END);

			setVisible(true);
		}else{
			setVisible(false);
		}


	}

	private void desenhandoPainel() {
		// TODO Auto-generated method stub
		// Insere os componentes no dialog
		painelCentral = new JPanel(new BorderLayout());
		painelCentral.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		labelIDBusca = new JLabel("Selecione a turma ");
		labelIDBusca.setFont(new Font("sans-serif", Font.BOLD, 12));

		//A ser usado futuramente para vizualizar o professor da turma
		labelProfessorTurma = new JLabel("Professor : ");
		labelProfessorTurma.setFont(new Font("sans-serif", Font.BOLD, 12));				

		labelDadosProfessor = new JLabel("");
		labelDadosProfessor.setFont(new Font("sans-serif", Font.ITALIC, 12));

		painelDadosProfessor = new JPanel(new GridLayout(1,2, 10, 10));
		painelDadosProfessor.add(labelProfessorTurma);
		painelDadosProfessor.add(labelDadosProfessor);

		painelTabela = new JPanel(new GridLayout(1, 1, 10,10));
		painelTabela.add(new JScrollPane(tabela));

		botaoListarAlunos= new JButton("Pesquisar");
		botaoListarAlunos.setFont(new Font("sans-serif", Font.BOLD, 13));
		painelComboBoxTurmas = new JPanel(new BorderLayout(10, 0));
	}
	// metodo usando uma thread para atulizar o JLabel
		public void apresentaProfessorLabel(final JLabel label1, final String dadosProfString){
		    try{
			     EventQueue.invokeLater(new Runnable() {   
			     public void run() {   
			        label1.setText(dadosProfString.toString());
			     }  
			    });
		        Thread.sleep(10);
		   } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		}
}