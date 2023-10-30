package studyOne;

import java.util.Base64;

public class EncryptionDecorator implements DataSource {

    private DataSource dataSource;

    public EncryptionDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String read(String filePath) {
        String encryptedContent = dataSource.read(filePath);
        return decrypt(encryptedContent);
    }

    @Override
    public void write(String filePath, String content) {
        String encryptedContent = encrypt(content);
        dataSource.write(filePath, encryptedContent);
    }

    private String encrypt(String content) {
        return Base64.getEncoder().encodeToString(content.getBytes());
    }

    private String decrypt(String encryptedContent) {
        return new String(Base64.getDecoder().decode(encryptedContent));
    }
}

