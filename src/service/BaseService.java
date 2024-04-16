package service;

import model.BaseModel;
import repository.BaseRepository;

public abstract class BaseService<T extends BaseModel , R extends BaseRepository<T>> {
    protected R repository;
    public BaseService (R repository){
        this.repository = repository;
    }
    public BaseService(){

    }
}
