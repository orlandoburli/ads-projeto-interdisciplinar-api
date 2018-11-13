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

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.ProfessorDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Professor;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.repositories.ProfessorRepository;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.requests.ProfessorConsultaRequest;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.ConsultaResponse;
import br.com.orlandoburli.ads.interdisciplinar.api.utils.ValidatorUtils;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository repository;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private ValidatorUtils utils;

	private Professor insert(Professor u) throws BusinessException {
		this.utils.validate(u);

		return this.repository.save(u);
	}

	public ProfessorDTO insert(ProfessorDTO usuario) throws BusinessException {
		return this.conversionService.convert(this.insert(this.conversionService.convert(usuario, Professor.class)),
				ProfessorDTO.class);
	}

	private Professor update(Professor u) throws BusinessException {
		this.utils.validate(u);

		return this.repository.save(u);
	}

	public ProfessorDTO update(ProfessorDTO usuario) throws BusinessException {
		return this.conversionService.convert(this.update(this.conversionService.convert(usuario, Professor.class)),
				ProfessorDTO.class);
	}

	public void delete(Integer id) {
		this.repository.findById(id).ifPresent(u -> this.repository.delete(u));
	}

	public Optional<ProfessorDTO> get(Integer id) {
		final Optional<Professor> op = this.repository.findById(id);

		if (op.isPresent()) {
			return Optional.of(this.conversionService.convert(op.get(), ProfessorDTO.class));
		}

		return Optional.empty();
	}

	public ConsultaResponse<ProfessorDTO> pesquisar(ProfessorConsultaRequest request) {
		final String filtroString = ("%" + (request.getFiltro() == null ? "" : request.getFiltro().trim()) + "%")
				.replace(" ", "%").toUpperCase();

		final Direction direction = StringUtils.isEmpty(request.getDirecao()) ? Direction.ASC
				: Direction.valueOf(request.getDirecao().toUpperCase());

		final String ordenar = StringUtils.isEmpty(request.getOrdenar()) ? "nome" : request.getOrdenar();

		final Page<Professor> page = this.repository.findByNomeOrEmailOrCpf(filtroString,
				PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(direction, ordenar)));

		return new ConsultaResponse<>(page.getContent().stream().map(a -> {
			final ProfessorDTO dto = this.conversionService.convert(a, ProfessorDTO.class);
			dto.setSenha(null);
			return dto;
		}).collect(Collectors.toList()), page.getTotalElements(), page.getTotalPages());
	}

}
