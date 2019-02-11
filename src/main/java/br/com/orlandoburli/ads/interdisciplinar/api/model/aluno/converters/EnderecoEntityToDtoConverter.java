package br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.dto.EnderecoDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.entities.Endereco;

@Component
public class EnderecoEntityToDtoConverter implements Converter<Endereco, EnderecoDTO> {

	@Override
	public EnderecoDTO convert(Endereco source) {
		final EnderecoDTO destino = new EnderecoDTO();

		destino.setId(source.getId());
		destino.setTipoEndereco(source.getTipoEndereco());
		destino.setCep(source.getCep());
		destino.setLogradouro(source.getLogradouro());
		destino.setComplemento(source.getComplemento());
		destino.setBairro(source.getBairro());
		destino.setCidade(source.getCidade());
		destino.setUf(source.getUf());

		return destino;
	}

}
