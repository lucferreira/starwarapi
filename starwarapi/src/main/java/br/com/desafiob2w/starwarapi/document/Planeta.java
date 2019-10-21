package br.com.desafiob2w.starwarapi.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Document
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Planeta {
	
	@Id
	@Getter @Setter
	private Long idplaneta = 0L;
	@Getter @Setter
	@NonNull
	private String nome;
	@Getter @Setter
	@NonNull
	private String clima;
	@Getter @Setter
	@NonNull
	private String terreno;
	@Nullable @Getter @Setter
	private Integer qtdplanetas = 0;
	
	public Planeta(String nome, String clima, String terreno) {
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idplaneta == null) ? 0 : idplaneta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planeta other = (Planeta) obj;
		if (idplaneta == null) {
			if (other.idplaneta != null)
				return false;
		} else if (!idplaneta.equals(other.idplaneta))
			return false;
		return true;
	}
	
	

}
