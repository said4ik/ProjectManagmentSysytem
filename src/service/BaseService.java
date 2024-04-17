package service;

import exception.DataNotFoundException;
import model.BaseModel;
import repository.BaseRepository;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Supplier;

public abstract class BaseService<T extends BaseModel, R extends BaseRepository<T>> {
    protected R repository;

    public BaseService(R repository) {
        this.repository = repository;
    }

    public BaseService() {
    }

    public boolean add(T t) {
        if (check(t)) {
            return false;
        }
        repository.add(t);
        return true;
    }

    public void delete(UUID id) {
        repository.delete(id);
    }

    public void update(UUID id, T t) {
        repository.update(id, t);
    }

    public T findById(UUID id) throws DataNotFoundException {
        return repository.findById(id).orElseThrow(new Supplier<DataNotFoundException>() {
            @Override
            public DataNotFoundException get() {
                return new DataNotFoundException("data not found ");
            }
        });
    }

    public ArrayList<T> getActives() {
        return repository.getActives();
    }

    public abstract boolean check(T t);
}
