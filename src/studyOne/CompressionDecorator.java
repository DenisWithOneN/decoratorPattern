package studyOne;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class CompressionDecorator implements DataSource {

    private DataSource dataSource;

    public CompressionDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String read(String filePath) {
        String compressedContent = dataSource.read(filePath);
        return decompress(compressedContent);
    }

    @Override
    public void write(String filePath, String content) {
        String compressedContent = compress(content);
        dataSource.write(filePath, compressedContent);
    }

    private String compress(String content) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream);
            deflaterOutputStream.write(content.getBytes());
            deflaterOutputStream.close();
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return content;
        }
    }

    private String decompress(String compressedContent) {
        try {
            byte[] compressedData = Base64.getDecoder().decode(compressedContent);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InflaterInputStream inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(compressedData));
            int b;
            while ((b = inflaterInputStream.read()) != -1) {
                byteArrayOutputStream.write(b);
            }
            inflaterInputStream.close();
            return new String(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return compressedContent;
        }
    }
}

