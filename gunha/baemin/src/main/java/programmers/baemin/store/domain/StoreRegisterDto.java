package programmers.baemin.store.domain;

import lombok.Data;

@Data
public class StoreRegisterDto {
	private String name;

	public StoreRegisterDto(String name) {
		this.name = name;
	}
}
