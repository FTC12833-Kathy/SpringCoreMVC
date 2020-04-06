package guru.springframework.services;

import guru.springframework.domain.DomainID;

import java.util.*;

/**
 * Created by jt on 11/14/15.
 */
public abstract class MapService<T extends DomainID>  {
    protected Map<Integer, T> domainMap;

    public MapService() {
        domainMap = new HashMap<>();
        loadDomainObjects();
    }

    public List<T> listAll() {
        return new ArrayList<>(domainMap.values());
    }

    public DomainID getByID(Integer id) {
        return domainMap.get(id);
    }

    public DomainID createOrUpdate(T domainObject) {
        if (domainObject != null){

            if (domainObject.getId() == null){
                domainObject.setId(getNextKey());
            }
            domainMap.put(domainObject.getId(), domainObject);

            return domainObject;
        } else {
            throw new RuntimeException("Object Can't be null");
        }
    }

    public void delete(Integer id) {
        domainMap.remove(id);
    }

    private Integer getNextKey(){
        return Collections.max(domainMap.keySet()) + 1;
    }

    protected abstract void loadDomainObjects();

}
