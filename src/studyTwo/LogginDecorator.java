package studyTwo;

class LoggingDecorator<T> implements Repository<T> {
    private Repository<T> repository;

    public LoggingDecorator(Repository<T> repository) {
        this.repository = repository;
    }

    @Override
    public void add(T item) {
        System.out.println("Logging: Before Adding");
        repository.add(item);
        System.out.println("Logging: After Adding");
    }

    @Override
    public T read(int id) {
        System.out.println("Logging: Before Reading");
        T item = repository.read(id);
        System.out.println("Logging: After Reading");
        return item;
    }

    @Override
    public void update(int id, T item) {
        System.out.println("Logging: Before Updating");
        repository.update(id, item);
        System.out.println("Logging: After Updating");
    }

    @Override
    public void delete(int id) {
        System.out.println("Logging: Before Deleting");
        repository.delete(id);
        System.out.println("Logging: After Deleting");
    }
}
