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

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.DisciplinaDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Disciplina;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.repositories.DisciplinaRepository;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.requests.DisciplinaConsultaRequest;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.ConsultaResponse;
import br.com.orlandoburli.ads.interdisciplinar.api.utils.ValidatorUtils;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository repository;

	@Autowired
	private ValidatorUtils utils;

	@Autowired
	private ConversionService conversionService;

	private Disciplina insert(Disciplina d) throws BusinessException {
		this.utils.validate(d);

		return this.repository.save(d);
	}

	public DisciplinaDTO insert(DisciplinaDTO d) throws BusinessException {
		return this.conversionService.convert(this.insert(this.conversionService.convert(d, Disciplina.class)),
				DisciplinaDTO.class);
	}

	private Disciplina update(Disciplina d) throws BusinessException {
		this.utils.validate(d);

		return this.repository.save(d);
	}

	public DisciplinaDTO update(DisciplinaDTO d) throws BusinessException {
		return this.conversionService.convert(this.update(this.conversionService.convert(d, Disciplina.class)),
				DisciplinaDTO.class);
	}

	public void delete(Integer id) {
		this.repository.findById(id).ifPresent(d -> this.repository.delete(d));
	}

	public Optional<DisciplinaDTO> get(Integer id) {
		final Optional<Disciplina> op = this.repository.findById(id);

		if (op.isPresent()) {
			return Optional.of(this.conversionService.convert(op.get(), DisciplinaDTO.class));
		}

		return Optional.empty();
	}

	public ConsultaResponse<DisciplinaDTO> pesquisar(DisciplinaConsultaRequest request) {
		final String filtroString = ("%" + (request.getFiltro() == null ? "" : request.getFiltro().trim()) + "%")
				.replace(" ", "%").toUpperCase();

		final Direction direction = StringUtils.isEmpty(request.getDirecao()) ? Direction.ASC
				: Direction.valueOf(request.getDirecao().toUpperCase());

		final String ordenar = StringUtils.isEmpty(request.getOrdenar()) ? "nome" : request.getOrdenar();

		if (request.getPageNumber() == null || request.getPageNumber() < 0) {
			request.setPageNumber(0);
		}

		if (request.getPageSize() == null || request.getPageSize() <= 0) {
			request.setPageSize(Integer.MAX_VALUE);
		}

		final Page<Disciplina> page = this.repository.findByNomeLikeIgnoreCase(filtroString,
				PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(direction, ordenar)));

		return new ConsultaResponse<>(page.getContent().stream()
				.map(a -> this.conversionService.convert(a, DisciplinaDTO.class)).collect(Collectors.toList()),
				page.getTotalElements(), page.getTotalPages());
	}
}
