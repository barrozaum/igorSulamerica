package br.com.sulamerica.igor.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sulamerica.igor.controller.dao.UsuarioFacadeImplDao;
import br.com.sulamerica.igor.controller.dto.UsuarioDTO;
import br.com.sulamerica.igor.controller.form.AtualizacaoUsuarioForm;
import br.com.sulamerica.igor.controller.form.UsuarioForm;
import br.com.sulamerica.igor.model.Usuario;
import br.com.sulamerica.igor.repository.ICargoRepository;
import br.com.sulamerica.igor.repository.IPerfilRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private static final Logger logger = LogManager.getLogger(UsuarioController.class);

	// usando para abrir conultar banco de dados
	@Autowired
	JdbcTemplate jdbcTemplate;

	// usado para consultar dados do cargo
	@Autowired
	private ICargoRepository cargoRepository;

	// usados para consultar dados do perfil
	@Autowired
	private IPerfilRepository perilRepository;

	// Classe onde implementamos os métodos de consulta do usuário
	// passamos como parametro JdbcTemplate
	@Autowired
	private UsuarioFacadeImplDao usuarioFacadeImplDao;

	@GetMapping
	public List<UsuarioDTO> lista() {
		logger.info("Inicio processamento requisição Listar Usuarios");
		try {
			return usuarioFacadeImplDao.ListarUsuario(jdbcTemplate);
		} catch (Exception e) {
			logger.error("Erro ao realizar busca por todos os Usuarios cadastrados -> " + e.getMessage());
		} finally {
			logger.info("Fim processamento requisição Listar Usuarios");
		}
		return null;
	}
	@GetMapping("/buscarPeloNome/{nome}")
	public List<UsuarioDTO> buscarUsuarioPorNome(@PathVariable String nome) {

		logger.info("Inicio processamento requisição buscarUsuarioPorNome ");
		try {
			//
			return usuarioFacadeImplDao.buscarUsuarioPorNome(jdbcTemplate , nome);
		} catch (Exception e) {
			logger.error("Erro ao realizar busca buscarUsuarioPorNome -> " + e.getMessage());
		} finally {
			logger.info("Fim processamento requisição buscarUsuarioPorNome");
		}
		return null;
	}
	
	@GetMapping("/buscarPeloCpf/{cpf}")
	public List<UsuarioDTO> buscarUsuarioPorCpf(@PathVariable String cpf) {

		logger.info("Inicio processamento requisição buscarUsuarioPorCpf ");
		try {
			//
			return usuarioFacadeImplDao.buscarUsuarioPorCpf(jdbcTemplate , cpf);
		} catch (Exception e) {
			logger.error("Erro ao realizar busca buscarUsuarioPorCpf -> " + e.getMessage());
		} finally {
			logger.info("Fim processamento requisição buscarUsuarioPorCpf");
		}
		return null;
	}

	@GetMapping("/buscarPeloCargo/{cargo}")
	public List<UsuarioDTO> buscarUsuarioPorCargo(@PathVariable String cargo) {

		logger.info("Inicio processamento requisição buscarUsuarioPorCpf ");
		try {
			//
			return usuarioFacadeImplDao.buscarUsuarioPorCargo(jdbcTemplate , cargo);
		} catch (Exception e) {
			logger.error("Erro ao realizar busca buscarUsuarioPorCpf -> " + e.getMessage());
		} finally {
			logger.info("Fim processamento requisição buscarUsuarioPorCpf");
		}
		return null;
	}

	@GetMapping("/buscarPeloPerfil/{perfil}")
	public List<UsuarioDTO> buscarUsuarioPorPerfil(@PathVariable String perfil) {

		logger.info("Inicio processamento requisição buscarUsuarioPorCpf ");
		try {
			//
			return usuarioFacadeImplDao.buscarUsuarioPorPerfil(jdbcTemplate , perfil);
		} catch (Exception e) {
			logger.error("Erro ao realizar busca buscarUsuarioPorCpf -> " + e.getMessage());
		} finally {
			logger.info("Fim processamento requisição buscarUsuarioPorCpf");
		}
		return null;
	}

	@GetMapping("/retornarUsuariosSexoFemininoMaioresDe18")
	public List<UsuarioDTO> buscarUsuariosSexoFeniminoMaiorDe18() {

		logger.info("Inicio processamento requisição buscarUsuariosSexoFeniminoMaiorDe18 ");
		try {
			//
			return usuarioFacadeImplDao.buscarUsuariosSexoFeniminoMaiorDe18(jdbcTemplate);
		} catch (Exception e) {
			logger.error("Erro ao realizar busca buscarUsuariosSexoFeniminoMaiorDe18 -> " + e.getMessage());
		} finally {
			logger.info("Fim processamento requisição buscarUsuariosSexoFeniminoMaiorDe18");
		}
		return null;
	}

	@GetMapping("/retornarCpfIniciaComZero")
	public List<UsuarioDTO> buscarCpfIniciadoComZero() {
		logger.info("Inicio processamento requisição buscarCpfIniciadoComZero ");
		
		try {
			return usuarioFacadeImplDao.buscarCpfIniciadoComZero(jdbcTemplate);
		} catch (Exception e) {
			logger.error("Erro ao realizar busca buscarCpfIniciadoComZero -> " + e.getMessage());
		} finally {
			logger.info("Fim processamento requisição buscarCpfIniciadoComZero");
		}
		return null;
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {

		try {
			Usuario u = usuarioFacadeImplDao.findByCode(id);
			if (u != null) {
				usuarioFacadeImplDao.excluir(id);
				return ResponseEntity.status(HttpStatus.OK).body("Usuário Excluído com Sucesso");
			}

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum Usuário Excluído");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir usuario-> " + e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> cadastra(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
		try {
			Usuario u = form.converter(cargoRepository, perilRepository);

			usuarioFacadeImplDao.salvar(u);

			return ResponseEntity.status(HttpStatus.CREATED).body("Usuario Incluído com Sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("Erro ao inserir cadastro -> " + e.getMessage());
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarDadosUsuario(@PathVariable Long id,
			@RequestBody @Valid AtualizacaoUsuarioForm form) {
		try {
			Usuario u = usuarioFacadeImplDao.findByCode(id);
			if (u != null) {
				Usuario uAlteracao = form.converter(cargoRepository, perilRepository);
				uAlteracao.setId(id);
				usuarioFacadeImplDao.atualizar(uAlteracao);
				return ResponseEntity.status(HttpStatus.OK).body("Usuario Alterado com Sucesso");
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário não encontrado");
			}
		} catch (Exception e) {

			if (e.getMessage().contains("Unique")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Verificar se nome ou cpf já existem na base ");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao alterar usuario-> " + e);
			}
		}

	}

	@PutMapping("/inativar/{id}")
	public ResponseEntity<?> inativarUsuario(@PathVariable Long id) {
		try {
			Usuario u = usuarioFacadeImplDao.findByCode(id);
			if (u != null) {
				if (u.isAtivo() == true) {
					usuarioFacadeImplDao.inativarUsuario(u);
					return ResponseEntity.status(HttpStatus.OK).body("Usuario inativado");
				} else {
					return ResponseEntity.status(HttpStatus.OK).body("Usuario já inativado");
				}
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário não encontrado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao inativar usuario-> " + e.getMessage());
		}

	}
	
	
	
	
	
}
