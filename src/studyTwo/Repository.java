package studyTwo;

public interface Repository<T> {
    void add(T item);
    T read(int id);
    void update(int id, T item);
    void delete(int id);
}