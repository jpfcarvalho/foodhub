package br.edu.unicesumar.foodhub.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CrudRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
