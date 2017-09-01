package br.ufpi.es.academico.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufpi.es.academico.controller.Fachada;
import br.ufpi.es.academico.controller.IFachada;
import br.ufpi.es.academico.model.Aluno;
import br.ufpi.es.academico.system.exception.aluno.AlunoNaoExistenteException;

public class AlunosTest {
	IFachada fachada;
	
	/**
	 * Inicializa a fachada unica para todo o sistema
	 * @throws SQLException 
	 */
	@Before
	public void setup() throws SQLException{
		this.fachada = new Fachada();
	}

	/**
	 * Testa de os alunos foram inseridos no repositorio
	 * @throws SQLException
	 */
	@Test
	public void addAluno() throws SQLException {
		// Instancia de alunos
		Aluno aluno1 = new Aluno("201100000", "Francisco Wermeson", "Ciencia da Computacao");
		Aluno aluno2 = new Aluno("201149100", "Joao", "Ciancia da Computacoo");
		Aluno aluno3 = new Aluno("201149101", "Pedro", "Matematica");
		Aluno aluno4 = new Aluno("201149102", "Maria", "Matematica");
		Aluno aluno5 = new Aluno("201149103", "Jose", "Ciencia da Computacoo");

		// Salvando alunos no repositorio
		try {
			fachada.inserirAluno(aluno1);
			fachada.inserirAluno(aluno2);
			fachada.inserirAluno(aluno3);
			fachada.inserirAluno(aluno4);
			fachada.inserirAluno(aluno5);
			assertEquals(5, fachada.quantidadeAlunos());
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	/**
	 * Insere dois alunos e checa se os dois alunos ao serem buscados correspondem as informacoes armazenadas
	 * @throws Exception
	 */
	@Test
	public void buscarAluno() throws Exception {
		Aluno aluno1 = new Aluno("201100000", "Francisco Wermeson", "Ciencia da Computacao");
		Aluno aluno2 = new Aluno("201149101", "Pedro", "Matematica");

		fachada.inserirAluno(aluno1);
		fachada.inserirAluno(aluno2);
		try {
			aluno1 = fachada.buscarAluno("201100000");
			aluno2 = fachada.buscarAluno("201149101");

			assertNotNull(aluno1);
			assertNotNull(aluno2);

			assertEquals("201100000", aluno1.getMatricula()); 
			assertEquals("Francisco Wermeson", aluno1.getNome());
			assertEquals("Ciencia da Computacao", aluno1.getCurso());

			assertEquals("201149101", aluno2.getMatricula());
			assertEquals("Pedro", aluno2.getNome());
			assertEquals("Matematica", aluno2.getCurso());
		} catch (AlunoNaoExistenteException e) {
			assertTrue(false);
		}
	}

	/**
	 * Insere dois alunos e checa a listagem
	 * @throws Exception
	 */
	@Test
	public void listaAlunos() throws Exception {
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		Aluno aluno1 = new Aluno("201100000", "Francisco Wermeson", "Ciencia da Computacao");
		Aluno aluno2 = new Aluno("201149101", "Pedro", "Matematica");

		fachada.inserirAluno(aluno1);
		fachada.inserirAluno(aluno2);

		listaAlunos.add(aluno1);
		listaAlunos.add(aluno2);

		assertEquals(listaAlunos, fachada.listarAlunos());
		assertEquals(2, listaAlunos.size());

	}
}