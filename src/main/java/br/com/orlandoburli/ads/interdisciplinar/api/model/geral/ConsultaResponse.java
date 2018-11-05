package br.com.orlandoburli.ads.interdisciplinar.api.model.geral;

import java.util.List;

public class ConsultaResponse<T> {

	private List<T> lista;

	private Long total;

	private Integer paginas;

	public ConsultaResponse(List<T> lista, Long total, Integer paginas) {
		this.total = total;
		this.lista = lista;
		this.paginas = paginas;
	}

	public List<T> getLista() {
		return this.lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}

	public Long getTotal() {
		return this.total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPaginas() {
		return this.paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}
}
