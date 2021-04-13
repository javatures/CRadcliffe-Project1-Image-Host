import java.util.List;

public interface Dao<E>{
    public void create(E e);
    public E retrieve(E e);
    public void update(E e);
    public void delete(int id);

    public List<E> selectAll();

}
