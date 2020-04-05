package guru.springframework.springmvc.domain;

public abstract class DomainID {
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
