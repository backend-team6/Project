package programmers.baemin.store.repository;

import java.util.List;

import programmers.baemin.store.domain.Store;

public interface StoreRepository {

	public List<Store> findAll();

	public Store findById(int id);

	public Store save(Store store);
}
