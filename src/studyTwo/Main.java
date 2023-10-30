package studyTwo;

public class Main {
    public static void main(String[] args) {

        Repository<Product> productRepository = new ProductRepository();
        Repository<Product> loggingProductRepository = new LoggingDecorator<>(productRepository);


        Product product = new Product(1, "Sample Product");
        loggingProductRepository.add(product);
        Product retrievedProduct = loggingProductRepository.read(1);
        loggingProductRepository.update(1, new Product(1, "Updated Product"));
        loggingProductRepository.delete(1);
    }
}