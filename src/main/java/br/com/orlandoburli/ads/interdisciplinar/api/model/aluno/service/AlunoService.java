package br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.service;

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

import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.dto.AlunoDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.entities.Aluno;
import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.repository.AlunoRepository;
import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.requests.AlunoConsultaRequest;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.ConsultaResponse;
import br.com.orlandoburli.ads.interdisciplinar.api.utils.ValidatorUtils;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private ValidatorUtils utils;

	private Aluno insert(Aluno aluno) throws BusinessException {
		this.utils.validate(aluno);

		return this.repository.save(aluno);
	}

	public AlunoDTO insert(AlunoDTO aluno) throws BusinessException {
		return this.conversionService.convert(this.insert(this.conversionService.convert(aluno, Aluno.class)),
				AlunoDTO.class);
	}

	private Aluno update(Aluno aluno) throws BusinessException {
		this.utils.validate(aluno);

		return this.repository.save(aluno);
	}

	public AlunoDTO update(AlunoDTO aluno) throws BusinessException {
		return this.conversionService.convert(this.update(this.conversionService.convert(aluno, Aluno.class)),
				AlunoDTO.class);
	}

	public void delete(Integer id) {
		this.repository.findById(id).ifPresent(u -> this.repository.delete(u));
	}

	public Optional<AlunoDTO> get(Integer id) {
		final Optional<Aluno> op = this.repository.findById(id);

		if (op.isPresent()) {
			return Optional.of(this.conversionService.convert(op.get(), AlunoDTO.class));
		}

		return Optional.empty();
	}

	public ConsultaResponse<AlunoDTO> pesquisar(AlunoConsultaRequest request) {
		final String filtroString = ("%" + (request.getFiltro() == null ? "" : request.getFiltro().trim()) + "%")
				.replace(" ", "%").toUpperCase();

		final Direction direction = StringUtils.isEmpty(request.getDirecao()) ? Direction.ASC
				: Direction.valueOf(request.getDirecao().toUpperCase());

		final String ordenar = StringUtils.isEmpty(request.getOrdenar()) ? "nome" : request.getOrdenar();

		final Page<Aluno> page = this.repository.findByNomeOrEmailOrCpfOrRg(filtroString,
				PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(direction, ordenar)));

		return new ConsultaResponse<>(page.getContent().stream().map(a -> {
			final AlunoDTO dto = this.conversionService.convert(a, AlunoDTO.class);
			dto.setSenha(null);
			dto.setEnderecos(null);
			return dto;
		}).collect(Collectors.toList()), page.getTotalElements(), page.getTotalPages());
	}
}
