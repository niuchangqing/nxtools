package cn.nxtools.common;

import cn.nxtools.common.compress.ZipWriter;
import cn.nxtools.common.exception.FileException;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipOutputStream;

/**
 * 压缩工具
 * @author      niuchangqing
 * @since       1.0.2
 */
public class ZipUtil {

    /**
     * 私有构造方法
     */
    private ZipUtil() {
    }

    /**
     * 默认编码,使用当前系统编码格式
     */
    private static final Charset DEFAULT_CHARSET;

    static {
        DEFAULT_CHARSET = Charset.defaultCharset();
    }

    /**
     * 文件压缩
     * <pre>
     * 压缩到当前目录
     * 默认为系统编码
     * </pre>
     * @param srcPath               待压缩文件路径
     * @return                      压缩后的Zip文件
     */
    public static File zip(String srcPath) {
        return zip(srcPath, DEFAULT_CHARSET);
    }

    /**
     * 文件压缩<br>
     * <pre>
     * 默认压缩到当前目录
     * 指定编码格式
     * </pre>
     * @param srcPath               待压缩文件路径
     * @param charset               编码 如:{@code Charset.forName("UTF-8")}
     * @return                      压缩后的Zip文件
     */
    public static File zip(String srcPath, Charset charset) {
        return zip(new File(srcPath), charset);
    }

    /**
     * 压缩文件<br>
     * <B>zipPath不能在srcPath路径下</B>
     * <pre>
     * 压缩到指定目录
     * 默认使用系统编码
     * </pre>
     * @param srcPath               待压缩文件路径
     * @param zipPath               压缩后的文件路径
     * @return                      压缩后的Zip文件
     */
    public static File zip(String srcPath, String zipPath) {
        return zip(srcPath, zipPath, DEFAULT_CHARSET);
    }

    /**
     * 压缩文件<br>
     * <B>zipPath不能在srcPath路径下</B>
     * <pre>
     * 压缩到指定目录
     * 指定编码
     * </pre>
     * @param srcPath               待压缩文件路径
     * @param zipPath               压缩后的文件路径
     * @param charset               编码
     * @return                      压缩后的Zip文件
     */
    public static File zip(String srcPath, String zipPath, Charset charset) {
        return zip(new File(srcPath), new File(zipPath), charset);
    }

    /**
     * 文件压缩
     * <pre>
     * 默认压缩到当前目录
     * 默认使用系统编码
     * </pre>
     * @param srcFile               待压缩文件
     * @return                      压缩后的Zip文件
     */
    public static File zip(File srcFile) {
        return zip(srcFile, DEFAULT_CHARSET);
    }

    /**
     * 文件压缩
     * <pre>
     * 默认压缩到当前目录
     * 指定编码格式
     * </pre>
     * @param srcFile               待压缩文件
     * @param charset               编码格式
     * @return                      压缩后的Zip文件
     */
    public static File zip(File srcFile, Charset charset) {
        //获取压缩文件路径
        String zipPath = FileUtil.getCanonicalPath(srcFile.getParentFile()) +
                File.separator +
                FilenameUtil.getBaseName(FileUtil.getCanonicalPath(srcFile)) + ".zip";
        File zipFile = new File(zipPath);
        return zip(srcFile, zipFile, charset);
    }

    /**
     * 压缩文件<br>
     * <B>zipPath不能在srcPath路径下</B>
     * <pre>
     * 指定压缩文件路径
     * 默认使用系统编码
     * </pre>
     * @param srcFile                   待压缩文件
     * @param zipFile                   压缩后的文件
     * @return                          压缩后的Zip文件
     */
    public static File zip(File srcFile, File zipFile) {
        return zip(srcFile, zipFile, DEFAULT_CHARSET);
    }


    /**
     * 压缩文件<br>
     * <B>zipPath不能在srcPath路径下</B>
     * <pre>
     * 指定压缩文件路径
     * 指定编码
     * </pre>
     * @param srcFile                   待压缩文件
     * @param zipFile                   压缩后的文件
     * @param charset                   编码
     * @return                          压缩后的Zip文件
     */
    public static File zip(File srcFile, File zipFile, Charset charset) {
        return zip(srcFile, zipFile, charset, false);
    }
    /**
     * 文件压缩
     * <B>zipPath不能在srcPath路径下</B>
     * <pre>
     * 指定压缩文件路径
     * 指定编码
     * </pre>
     * @param srcFile                   待压缩文件
     * @param zipFile                   打包后的zip文件
     * @param charset                   编码格式,为空默认使用系统编码
     * @param includeSrcDir             是否包含被打包的文件目录
     * @return                          打包后的zip文件
     */
    public static File zip(File srcFile, File zipFile, Charset charset, boolean includeSrcDir) {
        return zip(zipFile, charset, includeSrcDir, srcFile);
    }

    /**
     * 多文件压缩
     * <B>zipPath不能在srcPath路径下</B>
     * @param zipFile                   压缩的Zip文件
     * @param charset                   编码, 系统编码获取{@link Charset#defaultCharset()}, 指定编码获取{@link Charset#forName(String)}
     * @param includeSrcDir             是否包含被打包目录
     * @param srcFiles                  待压缩文件集合
     * @return                          压缩后的Zip文件
     * @throws FileException            File/IO异常
     */
    public static File zip(File zipFile, Charset charset, boolean includeSrcDir, File... srcFiles) {
        return zip(zipFile, charset, includeSrcDir, null, srcFiles);
    }

    /**
     * 多文件压缩
     * <B>zipPath不能在srcPath路径下</B>
     * @param zipFile                   压缩的Zip文件
     * @param charset                   编码, 系统编码获取{@link Charset#defaultCharset()}, 指定编码获取{@link Charset#forName(String)}
     * @param includeSrcDir             是否包含被打包目录
     * @param filter                    哪些文件不需要被压缩
     * @param srcFiles                  待压缩文件集合
     * @return                          压缩后的Zip文件
     * @throws FileException            File/IO异常
     */
    public static File zip(File zipFile, Charset charset, boolean includeSrcDir, FileFilter filter, File... srcFiles) {
        checkFiles(zipFile, srcFiles);
        try (ZipWriter zipWriter = ZipWriter.open(zipFile, charset)) {
            zipWriter.add(includeSrcDir, filter, srcFiles);
        }
        return zipFile;
    }

    /**
     * 文件压缩<br>
     * 使用{@link InputStream} 方式 <br>
     * srcPath可以是文件路径+名称可以是文件名称<br>
     * <pre>
     * 默认系统编码格式
     * /test/test.txt 压缩后路径 /test/test.txt
     * test.txt 压缩后路径 test.txt
     * </pre>
     * @param srcPath                   在压缩文件中的路径/文件名
     * @param in                        待压缩的文件流, 完成关闭流
     * @param zipFile                   生成的Zip文件路径
     * @return                          返回Zip文件
     */
    public static File zip(String srcPath, InputStream in, File zipFile) {
        return zip(srcPath, in, zipFile, DEFAULT_CHARSET);
    }

    /**
     * 文件压缩<br>
     * 使用{@link InputStream} 方式 <br>
     * srcPath可以是文件路径+名称可以是文件名称<br>
     * <pre>
     * /test/test.txt 压缩后路径 /test/test.txt
     * test.txt 压缩后路径 test.txt
     * </pre>
     * @param srcPath                   在压缩文件中的路径/文件名
     * @param in                        待压缩的文件流, 完成关闭流
     * @param zipFile                   生成的Zip文件路径
     * @param charset                   编码
     * @return                          返回Zip文件
     */
    public static File zip(String srcPath, InputStream in, File zipFile, Charset charset) {
        return zip(new String[]{srcPath}, new InputStream[]{in}, zipFile, charset);
    }

    /**
     * 文件压缩<br>
     * 使用{@link InputStream} 方式 <br>
     * srcPath可以是文件路径+名称可以是文件名称<br>
     * <pre>
     * 默认使用系统编码格式
     * /test/test.txt 压缩后路径 /test/test.txt
     * test.txt 压缩后路径 test.txt
     * </pre>
     * 使用
     * <pre>
     * String[] srcPaths = new String[]{"111.jpg","222.txt"}
     * InputStream[] ins = new InputStream[]{in1, in2}
     * //111.jpg对应in1
     * //222.txt对应in2
     * </pre>
     * @param srcPaths                  在压缩文件中的路径/文件名
     * @param ins                       待压缩的文件流集合, 完成关闭流
     * @param zipFile                   生成的Zip文件路径
     * @return                          返回Zip文件
     */
    public static File zip(String[] srcPaths, InputStream[] ins, File zipFile) {
        return zip(srcPaths, ins, zipFile, DEFAULT_CHARSET);
    }

    /**
     * 文件压缩<br>
     * 使用{@link InputStream} 方式 <br>
     * srcPath可以是文件路径+名称可以是文件名称<br>
     * <pre>
     * /test/test.txt 压缩后路径 /test/test.txt
     * test.txt 压缩后路径 test.txt
     * </pre>
     * 使用
     * <pre>
     * String[] srcPaths = new String[]{"111.jpg","222.txt"}
     * InputStream[] ins = new InputStream[]{in1, in2}
     * //111.jpg对应in1
     * //222.txt对应in2
     * </pre>
     * @param srcPaths                  在压缩文件中的路径/文件名
     * @param ins                       待压缩的文件流集合, 完成关闭流
     * @param zipFile                   生成的Zip文件路径
     * @param charset                   编码
     * @return                          返回Zip文件
     */
    public static File zip(String[] srcPaths, InputStream[] ins, File zipFile, Charset charset) {
        if (ArrayUtil.isEmpty(srcPaths) || ArrayUtil.isEmpty(ins)) {
            //srcPaths和ins不能为空
            throw new IllegalArgumentException("srcPaths or ins is empty");
        }
        if (srcPaths.length != ins.length) {
            //srcPaths和ins长度不匹配; 需要一一对应
            throw new IllegalArgumentException("srcPaths length is not equal to ins length");
        }
        try(ZipWriter zipWriter = ZipWriter.open(zipFile, charset)) {
            for (int i = 0; i < srcPaths.length; i++) {
                zipWriter.add(srcPaths[i], ins[i]);
            }
        }
        return zipFile;
    }

    /**
     * 文件压缩<br>
     * <pre>
     * 默认使用系统编码
     * String[] srcPaths = new String[]{"111.jpg","222.txt"}
     * InputStream[] ins = new InputStream[]{in1, in2}
     * //111.jpg对应in1
     * //222.txt对应in2
     * </pre>
     * @param output                    压缩文件流,完成后关闭
     * @param srcPaths                  在压缩文件中的路径/文件名
     * @param ins                       待压缩文件流
     */
    public static void zip(OutputStream output, String[] srcPaths, InputStream[] ins) {
        zip(output, srcPaths, ins, DEFAULT_CHARSET);
    }

    /**
     * 文件压缩<br>
     * <pre>
     * String[] srcPaths = new String[]{"111.jpg","222.txt"}; <br>
     * InputStream[] ins = new InputStream[]{in1, in2}; <br>
     * //111.jpg对应in1 <br>
     * //222.txt对应in2
     * </pre>
     * @param output                    压缩文件流,完成后关闭
     * @param srcPaths                  在压缩文件中的路径/文件名
     * @param ins                       待压缩文件流
     * @param charset                   编码格式
     */
    public static void zip(OutputStream output, String[] srcPaths, InputStream[] ins, Charset charset) {
        if (ArrayUtil.isEmpty(srcPaths) || ArrayUtil.isEmpty(ins)) {
            //srcPaths和ins不能为空
            throw new IllegalArgumentException("srcPaths or ins is empty");
        }
        if (srcPaths.length != ins.length) {
            //srcPaths和ins长度不匹配; 需要一一对应
            throw new IllegalArgumentException("srcPaths length is not equal to ins length");
        }
        try (ZipWriter zipWriter = ZipWriter.open(output, charset)) {
            for (int i = 0; i < srcPaths.length; i++) {
                zipWriter.add(srcPaths[i], ins[i]);
            }
        }
    }

    /**
     * 文件压缩<br>
     * <pre>
     * 默认使用系统编码
     * String[] srcPaths = new String[]{"111.jpg","222.txt"}
     * InputStream[] ins = new InputStream[]{in1, in2}
     * //111.jpg对应in1
     * //222.txt对应in2
     * </pre>
     * @param zipOutput                 压缩文件流,完成后关闭
     * @param srcPaths                  在压缩文件中的路径/文件名
     * @param ins                       待压缩文件流
     */
    public static void zip(ZipOutputStream zipOutput, String[] srcPaths, InputStream[] ins) {
        zip(zipOutput, srcPaths, ins, DEFAULT_CHARSET);
    }

    /**
     * 文件压缩<br>
     * <pre>
     * String[] srcPaths = new String[]{"111.jpg","222.txt"}
     * InputStream[] ins = new InputStream[]{in1, in2}
     * //111.jpg对应in1
     * //222.txt对应in2
     * </pre>
     * @param zipOutput                 压缩文件流,完成后关闭
     * @param srcPaths                  在压缩文件中的路径/文件名
     * @param charset                   编码
     * @param ins                       待压缩流
     */
    public static void zip(ZipOutputStream zipOutput, String[] srcPaths, InputStream[] ins, Charset charset) {
        if (ArrayUtil.isEmpty(srcPaths) || ArrayUtil.isEmpty(ins)) {
            //srcPaths和ins不能为空
            throw new IllegalArgumentException("srcPaths or ins is empty");
        }
        if (srcPaths.length != ins.length) {
            //srcPaths和ins长度不匹配; 需要一一对应
            throw new IllegalArgumentException("srcPaths length is not equal to ins length");
        }
        try (ZipWriter zipWriter = ZipWriter.open(zipOutput, charset)) {
            for (int i = 0; i < srcPaths.length; i++) {
                zipWriter.add(srcPaths[i], ins[i]);
            }
        }
    }

    // -------------------------------------------------------------------------- private method start

    /**
     * 校验压缩文件的路径,防止压缩文件在源文件内(防止递归压缩错误)
     * @param zipFile                   zip文件
     * @param srcFiles                  被压缩的源文件
     */
    private static void checkFiles(File zipFile, File... srcFiles) {
        if (zipFile.isDirectory()) {
            //压缩文件为文件夹,抛出异常
            throw new FileException("Zip file {} must not be a directory ", zipFile);
        }

        for (File srcFile : srcFiles) {
            if (srcFile == null) {
                continue;
            }
            if (BooleanUtil.isFalse(srcFile.exists())) {
                //被压缩的文件不存在
                throw new FileException("File {} not exist ", srcFile);
            }

            // 压缩文件不能在被压缩目录中
            if (srcFile.isDirectory() && FileUtil.isSub(srcFile, zipFile.getParentFile())) {
                throw new FileException("Zip file path {} must not be the child directory of {}", zipFile, srcFile);
            }
        }

    }

    // -------------------------------------------------------------------------- private method end
}
