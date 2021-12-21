package cn.nxtools.common.compress;



import cn.nxtools.common.ArrayUtil;
import cn.nxtools.common.BooleanUtil;
import cn.nxtools.common.FileUtil;
import cn.nxtools.common.IOUtil;
import cn.nxtools.common.StringUtil;
import cn.nxtools.common.base.Objects;
import cn.nxtools.common.exception.IORuntimeException;


import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zip写入封装
 */
public class ZipWriter implements Closeable {

    /**
     * {@link ZipOutputStream}
     */
    private final ZipOutputStream zipOutput;

    /**
     * 创建ZipWriter
     * @param zipFile               Zip文件
     * @param charset               编码
     * @return                      ZipWriter
     */
    public static ZipWriter open(File zipFile, Charset charset) {
        return new ZipWriter(zipFile, charset);
    }

    /**
     * 创建ZipWriter
     * @param output                {@link OutputStream}
     * @param charset               编码
     * @return                      ZipWriter
     */
    public static ZipWriter open(OutputStream output, Charset charset) {
        return new ZipWriter(output, charset);
    }

    /**
     * 构造函数
     * @param zipFile               Zip文件
     * @param charset               编码
     */
    public ZipWriter(File zipFile, Charset charset) {
        this.zipOutput = getZipOutputStream(zipFile, charset);
    }

    /**
     * 构造函数
     * @param output                {@link OutputStream}
     * @param charset               编码
     */
    public ZipWriter(OutputStream output, Charset charset) {
        this.zipOutput = getZipOutputStream(output, charset);
    }

    /**
     * 构造函数
     * @param output                {@link ZipOutputStream}
     */
    public ZipWriter(ZipOutputStream output) {
        this.zipOutput = output;
    }


    /**
     * 获得 ZipOutputStream
     * @param zipFile               zip压缩文件
     * @param charset               编码格式
     * @return                      ZipOutputStream
     */
    private static ZipOutputStream getZipOutputStream(File zipFile, Charset charset) {
        return getZipOutputStream(FileUtil.openOutputStream(zipFile, true), charset);
    }

    /**
     * 获得 ZipOutputStream
     * @param outputStream          压缩文件流
     * @param charset               编码格式
     * @return                      ZipOutputStream
     */
    private static ZipOutputStream getZipOutputStream(OutputStream outputStream, Charset charset) {
        if (outputStream instanceof ZipOutputStream) {
            return (ZipOutputStream) outputStream;
        }
        return new ZipOutputStream(outputStream, charset);
    }

    /**
     * 设置压缩级别,可选1～9,-1表示默认
     * @param level                 压缩级别
     * @return                      ZipWriter
     */
    public ZipWriter setLevel(int level) {
        this.zipOutput.setLevel(level);
        return this;
    }

    /**
     * 设置注释
     * @param comment               注释内容
     * @return                      ZipWriter
     */
    public ZipWriter setComment(String comment) {
        this.zipOutput.setComment(comment);
        return this;
    }

    /**
     * 获取ZipOutputStream
     * @return                      {@link ZipOutputStream}
     */
    public ZipOutputStream getZipOutput() {
        return this.zipOutput;
    }

    public ZipWriter add(boolean includeSrcDir, FileFilter filter, File... files) {
        if (Objects.isNull(files)) {
            return this;
        }
        for (File file : files) {
            //压缩文件父级目录
            String rootDir = StringUtil.EMPTY;
            try {
                rootDir = file.getCanonicalPath();
                if ((BooleanUtil.isFalse(file.isDirectory())) || includeSrcDir) {
                    //如果是文件,截取父级目录路径。如果设置包含目录，同样截取上级目录路径
                    rootDir = file.getCanonicalFile().getParentFile().getCanonicalPath();
                }
            } catch (IOException e) {
                throw new IORuntimeException(e);
            }
            add(file, rootDir, filter);
        }
        return this;
    }

    /**
     * 添加文件流到压缩包中,添加完成后关闭文件流
     * 如果输入流为{@code null},则只创建空目录
     * @param path                  压缩的路径,{@code null} 和 ""表示加入空目录,非被压缩的文件
     * @param in                    需要压缩的输入流
     * @return                      ZipWriter
     * @throws IORuntimeException   IO异常
     */
    public ZipWriter add(String path, InputStream in) {
        path = Objects.defaultIfNull(path, StringUtil.EMPTY);
        if (in == null) {
            //空目录,检查路径是否以文件夹分隔符结尾
            if (path.endsWith(File.separator) == false) {
                path = path.concat(File.separator);
                if (StringUtil.isNull(path)) {
                    return this;
                }
            }
        }
        return putEntry(path, in);
    }


    /**
     * 递归压缩文件夹或文件
     * rootDir为压缩的路径
     * 如: file的路径为/1/2/3/4.txt, rootDir为/1/2, 压缩后的文件目录为/3/4.txt
     * @param file                  被压缩的文件
     * @param rootDir               压缩文件的根目录
     * @param filter                文件过滤器,自定义哪些文件不需要加入压缩, null表示不过滤
     * @return                      ZipWriter
     * @throws IORuntimeException   IO异常
     */
    private ZipWriter add(File file, String rootDir, FileFilter filter) {
        if (Objects.isNull(file) || (Objects.nonNull(filter) && filter.accept(file) == false)) {
            return this;
        }
        //获取压缩文件夹的目录路径
        String subPath = FileUtil.getCanonicalPath(file).replaceFirst(rootDir, StringUtil.EMPTY);
        if (file.isDirectory()) {
            //目录
            File[] files = file.listFiles();
            if (ArrayUtil.isEmpty(files)) {
                //加入目录
                add(subPath, null);
            } else {
                for (File childrenFile : files) {
                    add(childrenFile, rootDir, filter);
                }
            }
        } else {
            //非文件夹,直接压缩
            putEntry(subPath, FileUtil.openInputStream(file));
        }
        return this;
    }

    /**
     * 添加文件流到压缩包中,完成后关闭文件流
     * 如果文件流为{@code null}, 只会创建空目录
     * @param path                  压缩路径,{@code null}和""表示根目录下
     * @param in                    需要压缩的输入流
     * @return                      ZipWrite
     * @throws IORuntimeException   IO异常
     */
    private ZipWriter putEntry(String path, InputStream in) {
        try {
            this.zipOutput.putNextEntry(new ZipEntry(path));
            if (in != null) {
                IOUtil.copy(in, this.zipOutput);
            }
            this.zipOutput.closeEntry();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            IOUtil.closeQuietly(in);
        }
        IOUtil.flushQuietly(this.zipOutput);
        return this;
    }
    @Override
    public void close() {
        try {
            zipOutput.finish();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            IOUtil.closeQuietly(this.zipOutput);
        }
    }
}
