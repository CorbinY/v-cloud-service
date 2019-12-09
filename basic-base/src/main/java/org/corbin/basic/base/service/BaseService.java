package org.corbin.basic.base.service;

import org.corbin.basic.base.entity.AbstractEntity;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author xiesu / Corbin
 * @version 1.0
 * @date 19-12-5
 */
public interface BaseService<T extends AbstractEntity, ID extends Serializable> {
  /**
   * select a union domain by PK if not found ,would return null
   *
   * @param id must not be {@literal null}
   * @return Retrieves an entity by its id.
   */
  T findById(ID id);

  /**
   * select a union domain by PK if not found ,would return null
   *
   * @param id id is the pk
   * @return
   */
  T findByPK(ID id);

  /**
   * find a union a intact domain by a un-intact domain, if not find ,will return null
   *
   * @param domain
   * @return
   */
  T findOne(T domain);

  /**
   * this founction always return instance like hibernate used get ,if not fount will be throws an
   * exception , if you want to use 'null' to replace this exception ,please use 'findOne ' function
   *
   * <p>Returns a reference to the entity with the given identifier. Depending on how the JPA
   * persistence provider is implemented this is very likely to always return an instance and throw
   * an {@link EntityNotFoundException} on first access. Some of them will reject invalid
   * identifiers immediately.
   *
   * @param id must not be {@literal null}.
   * @return a reference to the entity with the given identifier.
   * @see EntityManager#getReference(Class, Object) for details on when an exception is thrown.
   */
  T getOne(ID id);

  /**
   * Returns a single entity matching the given {@link Example} or {@literal null} if none was
   * found.
   *
   * @param example must not be {@literal null}.
   * @return a single entity matching the given {@link Example} or {@link Optional#empty()} if none
   *     was found.
   * @throws IncorrectResultSizeDataAccessException if the Example yields more than one result.
   */
  Optional<T> findOne(Example<T> example);

  /**
   * find all domain elements
   *
   * @return
   */
  List<T> findAll();

  /**
   * find all domain elements with sort * @see
   * org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Sort)
   *
   * @return
   */
  List<T> findAll(Sort sort);

  /**
   * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code
   * Pageable} object.
   *
   * @param pageable
   * @return a page of entities
   */
  Page<T> findAll(Pageable pageable);

  /**
   * (non-Javadoc)
   *
   * @see
   *     org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example,
   *     org.springframework.data.domain.Sort)
   */
  <S extends T> List<S> findAll(Example<S> example, Sort sort);
  /**
   * (non-Javadoc)
   *
   * @see
   *     org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example)
   */
  <S extends T> List<S> findAll(Example<S> example);

  /**
   * Returns a {@link Page} of entities matching the given {@link Example}. In case no match could
   * be found, an empty {@link Page} is returned.
   *
   * @param example must not be {@literal null}.
   * @param pageable can be {@literal null}.
   * @return a {@link Page} of entities matching the given {@link Example}.
   */
  Page<T> findAll(Example<T> example, Pageable pageable);

  /**
   * returns a list domains which found by given ids
   *
   * @param ids
   * @return
   */
  List<T> findAllById(Iterable<ID> ids);

  /**
   * Returns the number of instances matching the given {@link Example}.
   *
   * @param example the {@link Example} to count instances for. Must not be {@literal null}.
   * @return the number of instances matching the {@link Example}.
   */
  long count(Example<T> example);

  /**
   * Deletes the entity with the given id.
   *
   * @param id must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
   */
  void deleteById(ID id);

  /**
   * Deletes a given entity.
   *
   * @param entity
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  void delete(T entity);
  /**
   * Deletes the given entities.
   *
   * @param entities
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  void deleteAll(Iterable<? extends T> entities);

  /** Deletes all entities managed by the repository. */
  void deleteAll();

  /**
   * Deletes the given entities in a batch . Assume that we will clear the {@link EntityManager}
   * after the call.
   *
   * @param entities
   */
  void deleteInBatch(Iterable<T> entities);
  /** Deletes all entities in a batch call. */
  void deleteAllInBatch();

  /**
   * save a instance
   *
   * @throws NullPointerException if {@code reference} is null
   * @param domain
   * @return
   */
  T insert(T domain);

  /**
   * @param domain
   * @throws NullPointerException if {@code reference} is null
   * @author xiesu / Corbin
   * @date 19-12-9
   */
  T update(T domain);

  /**
   * (non-Javadoc)
   *
   * @throws NullPointerException if {@code reference} is null
   * @see org.springframework.data.repository.CrudRepository
   */
  <S extends T> List<S> insertAll(Iterable<S> entities);
  /**
   * @throws NullPointerException if {@code reference} is null
   * @param entities
   * @author xiesu / Corbin
   * @date 19-12-9
   */
  <S extends T> List<S> updateAll(Iterable<S> entities);

  List<T> getContent(Page<T> page);

  Page<T> pageImpl(List<T> list, Pageable pageable);
}
