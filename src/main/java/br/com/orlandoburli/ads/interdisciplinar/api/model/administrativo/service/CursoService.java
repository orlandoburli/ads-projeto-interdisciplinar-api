package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.CursoDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.DadosDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.DisciplinaDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Curso;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.repositories.CursoRepository;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.requests.CursoConsultaRequest;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.requests.DisciplinaConsultaRequest;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.ConsultaResponse;
import br.com.orlandoburli.ads.interdisciplinar.api.utils.ValidatorUtils;

@Service
public class CursoService {

	@Autowired
	private CursoRepository repository;

	@Autowired
	private DisciplinaService disciplinaService;

	@Autowired
	private ValidatorUtils utils;

	@Autowired
	private ConversionService conversionService;

	private Curso insert(Curso u) throws BusinessException {
		this.utils.validate(u);

		return this.repository.save(u);
	}

	public CursoDTO insert(CursoDTO Curso) throws BusinessException {
		return this.conversionService.convert(this.insert(this.conversionService.convert(Curso, Curso.class)),
				CursoDTO.class);
	}

	private Curso update(Curso u) throws BusinessException {
		this.utils.validate(u);

		return this.repository.save(u);
	}

	public CursoDTO update(CursoDTO Curso) throws BusinessException {
		return this.conversionService.convert(this.update(this.conversionService.convert(Curso, Curso.class)),
				CursoDTO.class);
	}

	public void delete(Integer id) {
		this.repository.findById(id).ifPresent(u -> this.repository.delete(u));
	}

	public Optional<CursoDTO> get(Integer id) {
		final Optional<Curso> op = this.repository.findById(id);

		if (op.isPresent()) {
			return Optional.of(this.conversionService.convert(op.get(), CursoDTO.class));
		}

		return Optional.empty();
	}

	public ConsultaResponse<CursoDTO> pesquisar(CursoConsultaRequest request) {
		final String filtroString = ("%" + (request.getFiltro() == null ? "" : request.getFiltro().trim()) + "%")
				.replace(" ", "%").toUpperCase();

		final Direction direction = StringUtils.isEmpty(request.getDirecao()) ? Direction.ASC
				: Direction.valueOf(request.getDirecao().toUpperCase());

		final String ordenar = StringUtils.isEmpty(request.getOrdenar()) ? "nome" : request.getOrdenar();

		final Page<Curso> page = this.repository.findByNomeAndStatus(filtroString, request.getStatus(),
				PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(direction, ordenar)));

		return new ConsultaResponse<>(page.getContent().stream()
				.map(a -> this.conversionService.convert(a, CursoDTO.class)).collect(Collectors.toList()),
				page.getTotalElements(), page.getTotalPages());
	}

	public DadosDTO getDados() {
		final DadosDTO dados = new DadosDTO();

		final DisciplinaConsultaRequest request = new DisciplinaConsultaRequest();
		final ConsultaResponse<DisciplinaDTO> response = this.disciplinaService.pesquisar(request);

		dados.setDisciplinas(response.getLista());

		return dados;
	}
}
