package br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.converters;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.dto.AlunoDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.entities.Aluno;

@Component
public class AlunoDtoToEntityConverter implements Converter<AlunoDTO, Aluno> {

	@Autowired
	private EnderecoDtoToEntityConverter enderecoConverter;

	@Override
	public Aluno convert(AlunoDTO source) {
		final Aluno destino = new Aluno();

		destino.setId(source.getId());
		destino.setNome(source.getNome());
		destino.setEmail(source.getEmail());
		destino.setCpf(source.getCpf());
		destino.setRg(source.getRg());
		destino.setEmissorRg(source.getEmissorRg());
		destino.setSenha(source.getSenha());
		destino.setResetarSenha(source.getResetarSenha());
		destino.setDataNascimento(source.getDataNascimento());

		// @formatter:off
		destino.setEnderecos(
				source
					.getEnderecos()
					.stream()
					.map((a)-> this.enderecoConverter.convert(a))
					.collect(Collectors.toList()));
		// @formatter:on

		return destino;
	}

}
