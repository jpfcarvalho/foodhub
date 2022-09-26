package br.edu.unicesumar.foodhub.base;

public abstract class CrudService<T extends BaseEntity> extends LoadService<T> {

	public T save(T entity) {

		if (entity.isNew()) {
			beforeInsert(entity);
		} else {
			beforeUpdate(entity);
		}
		beforeSave(entity);
		return getRepository().save(entity);
	}

	public void deleteById(Long id) {
		beforeDelete(getRepository().getById(id));
		getRepository().deleteById(id);
	}

	protected void beforeDelete(T entity) {

	}

	protected void beforeUpdate(T entity) {

	}

	protected void beforeInsert(T entity) {

	}

	protected void beforeSave(T entity) {

	}

}
