package programmers.baemin.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import programmers.baemin.store.domain.Store;

@Repository
@RequiredArgsConstructor
public class MyBatisStoreRepository implements StoreRepository {

	private final StoreMapper storeMapper;

	@Override
	public List<Store> findAll() {
		return storeMapper.findAll();
	}

	@Override
	public Store findById(int id) {
		return storeMapper.findById(id);
	}

	@Override
	public Store save(Store store) {
		storeMapper.save(store);
		return store;
	}
}
