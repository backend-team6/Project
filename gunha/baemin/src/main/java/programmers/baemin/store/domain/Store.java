package programmers.baemin.store.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Store {
	private int id;

	private String name;

	public Store(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Store from(StoreRegisterDto storeRegisterDto) {
		return Store.builder()
			.name(storeRegisterDto.getName())
			.build();
	}
}
