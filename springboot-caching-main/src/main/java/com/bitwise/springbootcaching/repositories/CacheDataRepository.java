package com.bitwise.springbootcaching.repositories;

import com.bitwise.springbootcaching.models.CacheData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheDataRepository extends CrudRepository<CacheData, String> {

}
