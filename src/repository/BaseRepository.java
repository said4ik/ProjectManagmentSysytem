package repository;

import model.BaseModel;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseRepository<T extends BaseModel> {
    protected final ArrayList<T> data = new ArrayList<>();
    public ArrayList<T> getAll(){
        return data;
    }
    public boolean add(T t){
        data.add(t);
        return true;
    }
    public void update(UUID id,T t){
        int i = 0;
        for (T datum : data) {
            if(Objects.equals(datum.getId(),id)){
                t.setId(id);
                data.set(i,t);
                return;
            }
            i++;
        }
    }
    public void delete(UUID id){
        for (T t : data) {
            if(Objects.equals(t.getId(),id)){
                t.setActive(false);
                return;
            }
        }
    }
    public ArrayList<T> getActives(){
        ArrayList<T> list = new ArrayList<>();
        for (T t : data) {
            if(t.isActive()){
                list.add(t);
            }
        }
        return list;
    }
    public Optional<T> findById(UUID id){
        for (T t : data) {
            if(Objects.equals(t.getId(),id)){
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }
}
