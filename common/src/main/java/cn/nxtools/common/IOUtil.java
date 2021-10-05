package cn.nxtools.common;

import cn.nxtools.common.exception.IORuntimeException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cn.nxtools.common.StringUtil.isEmpty;
import static cn.nxtools.common.CollectionUtil.isEmpty;
import static cn.nxtools.common.base.Objects.isNull;
import static cn.nxtools.common.base.Objects.nonNull;
import static cn.nxtools.common.base.Preconditions.checkNotNull;

/**
 * @author niuchangqing
 * IO工具类
 */
public class IOUtil {

    /**
     * 私有构造方法
     */
    private IOUtil(){
    }

    private static final Logger logger = Logger.getLogger(IOUtil.class.getName());

    /**
     * 默认buffer size大小
     */
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * 获取当前系统换行符
     */
    public static final String LINE_SEPARATOR;

    static {
        StringWriter buf = new StringWriter();
        PrintWriter out = new PrintWriter(buf);
        out.println();
        LINE_SEPARATOR = buf.toString();
        out.close();
    }
    /**
     * 关闭
     * 不抛出异常
     * @param closeable         要关闭的对象
     */
    public static void closeQuietly(final Closeable closeable) {
        if (nonNull(closeable)) {
            try {
                closeable.close();
            }catch (IOException e) {
                logger.log(Level.WARNING, "exception thrown when closing " + closeable, e);
            }
        }
    }

    /**
     * 批量关闭
     * 不抛出异常
     * @param closeables           要关闭的数组
     */
    public static void closeQuietly(final Closeable... closeables) {
        if (isNull(closeables)) {
            return;
        }
        for (Closeable closeable : closeables) {
            closeQuietly(closeable);
        }
    }

    /**
     * 关闭一个URLConnection
     * @param conn                  URLConnection
     */
    public static void close(final URLConnection conn) {
        if (isNull(conn)) {
            return;
        }
        if (conn instanceof HttpURLConnection) {
            ((HttpURLConnection) conn).disconnect();
        }
    }

    /**
     * 关闭
     * 抛出异常
     * @param closeable         要关闭的对象
     * @throws IOException      io异常
     */
    public static void close(final Closeable closeable) throws IOException {
        if (nonNull(closeable)) {
            closeable.close();
        }
    }

    /**
     * 批量关闭
     * 抛出异常
     * @param closeables        要关闭的对象
     * @throws IOException      IO异常
     */
    public static void close(final Closeable... closeables) throws IOException {
        if (isNull(closeables)) {
            return;
        }
        for (final Closeable closeable : closeables) {
            close(closeable);
        }
    }

    /**
     * input转byte[]
     * @param inputStream       InputStream
     * @return                  byte[]
     * @throws NullPointerException inputStream为空抛出
     */
    public static byte[] toByteArray(final InputStream inputStream) {
        checkNotNull(inputStream);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int n = 0;
        try {
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            return output.toByteArray();
        }catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * input转byte
     * 已知大小的输入流
     * @param inputStream               InputStream
     * @param size                      输入流的大小
     * @return                          byte[]
     */
    public static byte[] toByteArray(final InputStream inputStream, long size) {
        if(size > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + size);
        }
        return toByteArray(inputStream, (int) size);
    }

    /**
     * input转byte
     * 已知大小的输入流
     * @param inputStream               InputStream
     * @param size                      输入流的大小
     * @return                          byte[]
     */
    public static byte[] toByteArray(final InputStream inputStream, int size) {
        checkNotNull(inputStream);
        if (size < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero " + size);
        }
        byte[] buffer = new byte[size];
        try {
            int n = 0;
            int offset = 0;
            while (offset < size && (n = inputStream.read(buffer, offset, size - offset)) != -1) {
                offset += n;
            }
            if (offset != size) {
                throw new IORuntimeException("Unexpected readed size. current: " + offset + ", excepted: " + size);
            }
            return buffer;
        }catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * Reader转byte[]
     * @param reader            Reader
     * @return                  byte[]
     * @throws IOException      IOException
     * @throws NullPointerException  reader为空抛出
     */
    public static byte[] toByteArray(final Reader reader) throws IOException {
        checkNotNull(reader);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(reader, output);
        return output.toByteArray();
    }

    /**
     * Reader转byte[]
     * @param reader                Reader
     * @param encoding              指定编码方式
     * @return                      byte[]
     * @throws IOException          IOException
     * @throws NullPointerException reader为空时抛出
     */
    public static byte[] toByteArray(final Reader reader, final String encoding) throws IOException {
        checkNotNull(reader);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(reader, output, encoding);
        return output.toByteArray();
    }

    /**
     * 将reader中的char复制给output
     * @param reader                Reader
     * @param output                OutputStream
     * @throws IOException          IOException
     */
    public static void copy(final Reader reader, final OutputStream output) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(output);
        copy(reader, out);
        out.flush();
    }

    /**
     * 将reader中的内容复制给output，并指定编码方式
     * @param reader                Reader
     * @param output                OutputStream
     * @param encoding              encoding
     * @throws IOException          IOException
     */
    public static void copy(final Reader reader, final OutputStream output, String encoding) throws IOException {
        if (isNull(encoding)) {
            copy(reader, output);
        }else {
            OutputStreamWriter out = new OutputStreamWriter(output, encoding);
            copy(reader, out);
            out.flush();
        }
    }

    /**
     * 将reader中的内容复制给writer
     * @param reader                Reader
     * @param writer                Writer
     * @return                      内容总长度
     * @throws IOException          IOException
     */
    public static long copy(final Reader reader, Writer writer) throws IOException {
        char[] chars = new char[DEFAULT_BUFFER_SIZE];
        int n = 0;
        long count = 0;
        while (-1 != (n = reader.read(chars))) {
            writer.write(chars, 0, n);
            count += n;
        }
        return count;
    }

    /**
     * 写入字符串
     * @param data                  指定字符串
     * @param output                OutputStream
     * {@link String#getBytes()}
     */
    public static void write(String data, OutputStream output) {
        if (isNull(data)) {
            //空，不处理
            return;
        }
        try {
            output.write(data.getBytes());
        }catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * 写入字符串
     * @param data                  指定的字符串
     * @param output                OutputStream
     * @param encoding              编码格式,{@link String#getBytes(String)}
     */
    public static void write(String data, OutputStream output, String encoding) {
        if (isNull(data)) {
            return;
        }
        if (isEmpty(encoding)) {
            write(data, output);
        }else {
            try {
                output.write(data.getBytes(encoding));
            }catch (IOException e) {
                throw new IORuntimeException(e);
            }
        }
    }

    /**
     * 按行写入数据,集合中的一条数据占一行,末尾自动追加换行符
     * @param lines                     写入的数据
     * @param output                    OutputStream
     * @param encoding                  编码格式,可以为空
     * @param lineEnding                换行符,可以为空,为空{@link #LINE_SEPARATOR}
     */
    public static void writeLines(Collection<?> lines, OutputStream output, String encoding, String lineEnding) {
        if (isEmpty(lines)) {
            return;
        }
        if (isNull(encoding)) {
            writeLinesForLineEnding(lines, output, lineEnding);
            return;
        }
        if (isEmpty(lineEnding)) {
            lineEnding = LINE_SEPARATOR;
        }
        for (Object line : lines) {
            try {
                if (nonNull(line)) {
                    output.write(line.toString().getBytes(encoding));
                }
                output.write(lineEnding.getBytes(encoding));
            }catch (IOException e) {
                throw new IORuntimeException(e);
            }
        }
    }

    /**
     * 按行写入数据
     * 行之间自动追加换行符
     * @param lines                 写入的数据
     * @param output                OutputStream
     * @param encoding              编码格式
     */
    public static void writeLines(Collection<?> lines, OutputStream output, String encoding) {
        writeLines(lines, output, encoding, null);
    }

    /**
     * 按行写入数据
     * 行之间自动追加换行符
     * @param lines                 写入的数据
     * @param output                OutputStream
     */
    public static void writeLines(Collection<?> lines, OutputStream output) {
        writeLines(lines, output, null);
    }

    private static void writeLinesForLineEnding(Collection<?> lines, OutputStream output, String lineEnding) {
        if (isEmpty(lines)) {
            return;
        }
        if (isNull(lineEnding)) {
            lineEnding = LINE_SEPARATOR;
        }
        for (Object line : lines) {
            try {
                if (nonNull(line)) {
                    output.write(line.toString().getBytes());
                }
                output.write(lineEnding.getBytes());
            }catch (IOException e) {
                throw new IORuntimeException(e);
            }
        }
    }

    /**
     * inputStream转字符串
     * @param inputStream                   InputStream
     * @param encoding                      编码格式
     * @return                              String
     */
    public static String toString(InputStream inputStream, String encoding) {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            if (isNull(encoding)) {
                reader = new InputStreamReader(inputStream);
            }else {
                reader = new InputStreamReader(inputStream, encoding);
            }
            char[] buffer = new char[DEFAULT_BUFFER_SIZE];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        }catch (IOException e) {
            throw new IORuntimeException(e);
        }finally {
            closeQuietly(reader);
        }
        if (writer != null) {
            return writer.toString();
        }else {
            return null;
        }
    }

    /**
     * inputStream转字符串
     * @param inputStream               InputStream
     * @return                          返回字符串
     */
    public static String toString(InputStream inputStream) {
        return toString(inputStream, null);
    }
}
