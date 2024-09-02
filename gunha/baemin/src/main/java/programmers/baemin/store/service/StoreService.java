package programmers.baemin.store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import programmers.baemin.store.domain.Store;
import programmers.baemin.store.domain.StoreRegisterDto;
import programmers.baemin.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {

	private final StoreRepository storeRepository;

	public List<Store> getStoreList() {
		return storeRepository.findAll();
	}

	public Store getStore(int id) {
		return storeRepository.findById(id);
	}

	public Store save(StoreRegisterDto registerDto) {
		Store store = Store.from(registerDto);
		return storeRepository.save(store);
	}
}
