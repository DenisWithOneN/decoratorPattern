package studyOne;

public class Main {

    public static void main(String[] args) {
        DataSource dataSource = new FileDataSource();
        dataSource = new EncryptionDecorator(dataSource);
        dataSource = new CompressionDecorator(dataSource);

        String filePath = "example.txt";
        String content = "This is a test message.";

        dataSource.write(filePath, content);
        String readContent = dataSource.read(filePath);

        System.out.println("Original content: " + content);
        System.out.println("Read content: " + readContent);
    }
}
