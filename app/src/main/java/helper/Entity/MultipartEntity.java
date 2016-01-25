package helper.Entity;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by weiyuyang on 16-1-25.
 */
public class MultipartEntity implements HttpEntity {
    private final static char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray();

    private final String NEW_LINE_STR = "\r\n";

    private final String CONTENT_TYPE = "Content-Type: ";
    private final String CONTENT_DISPOSITION = "Content-Disposition: ";

    private final String TYPE_TEXT_CHARSET = "text/plain; charset=UTF-8";

    private final String TYPE_OCTET_STREAM = "application/octet-stream";

    private final byte[] BINARY_ENCODING = "Content-Transfer-Encoding: binary\r\n\r\n".getBytes();

    private final byte[] BIT_ENCODING = "Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes();

    private String mBoundary = null;

    ByteArrayOutputStream mOutputStream = new ByteArrayOutputStream();

    public MultipartEntity() {
        this.mBoundary = generateBoundary();
    }


    private final String generateBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0 ;i<30;i++){
            sb.append(MULTIPART_CHARS[random.nextInt(MULTIPART_CHARS.length)]);
        }
        return sb.toString();
    }

    private void writeFirstBoundary() {

    }

    public void addStringPart(final String paramName, final String value) {

    }

    public void writeToOutputStream(String paramName, byte[] rawData,
                                    String type, byte[] encodingBytes,
                                    String fileName) {

    }


    public void addBinaryPart(String paranName,final byte[] rawData){

    }


    public void addFilePart(final String key,final File file){

    }


    private void closeSilently(Closeable closeable){

    }

    private byte[] getContentDispositionBytes(String paramName,String fileName){

    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public boolean isChunked() {
        return false;
    }

    @Override
    public long getContentLength() {
        return 0;
    }

    @Override
    public Header getContentType() {
        return null;
    }

    @Override
    public Header getContentEncoding() {
        return null;
    }

    @Override
    public InputStream getContent() throws IOException, IllegalStateException {
        return null;
    }

    @Override
    public void writeTo(OutputStream outputStream) throws IOException {

    }

    @Override
    public boolean isStreaming() {
        return false;
    }

    @Override
    public void consumeContent() throws IOException {

    }
}
