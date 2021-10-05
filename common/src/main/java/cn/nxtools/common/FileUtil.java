package cn.nxtools.common;

import cn.nxtools.common.exception.FileException;
import cn.nxtools.common.exception.IORuntimeException;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Collection;

import static cn.nxtools.common.IOUtil.closeQuietly;
import static cn.nxtools.common.base.Objects.isNull;
import static cn.nxtools.common.base.Preconditions.checkNotNull;

/**
 * 文件处理工具
 * @author niuchangqing
 */
public class FileUtil {

    /**
     * 1KB=1024字节
     */
    public static final long ONE_KB = 1024;

    /**
     * 1MB
     */
    public static final long ONE_MB = ONE_KB * ONE_KB;

    /**
     * 复制文件的buffer大小
     */
    private static final long FILE_COPY_BUFFER_SIZE = ONE_MB * 30;

    /**
     * 1GB
     */
    public static final long ONE_GB = ONE_MB * 1024;

    /**
     * 1TB
     */
    public static final long ONE_TB = ONE_GB * 1024;

    /**
     * 私有化构造方法
     */
    private FileUtil() {
    }

    /**
     * 移动文件
     * src和target必须是文件,文件夹不行
     * @param source                            源文件,必须为文件
     * @param target                            目标文件,必须为文件
     * @throws NullPointerException             source 或者 target为空时
     * @throws FileException                    source文件不存在,source文件是目录,target已存在,target是目录
     */
    public static void moveFile(File source, File target) {
        checkNotNull(source, "Source must not be null");
        checkNotNull(target, "Target must not be null");
        if (!source.exists()) {
            //源文件不存在
            throw new FileException("Source " + source + " not exist");
        }
        if (source.isDirectory()) {
            //源文件为目录
            throw new FileException("Source " + source + " is a directory");
        }
        if (target.exists()) {
            //目标文件已经存在
            throw new FileException("Target file " + target + " already exist");
        }
        //判断移动前和移动后的文件路径是否一样
        boolean isSame = equalsCanonicalPath(source, target);
        if (isSame) {
            //移动前和移动后文件路径一致
            throw new FileException("Source " + source + " and target " + target + " are the same");
        }
        boolean flag = source.renameTo(target);
        out:if (!flag) {
            copyFile(source, target);
            if (source.delete()) {
                break out;
            }
            target.delete();
            throw new FileException("Failed to delete source file " + source + " after copying file" + target);
        }
    }

    /**
     * 移动文件夹
     * @param source                    源文件夹,必须为文件夹
     * @param target                    目标文件夹,必须为文件夹
     * @throws NullPointerException     source或者target为空时
     * @throws FileException            source不存在,source不是文件夹,target已存在
     */
    public static void moveDirectory(File source, File target) {
        checkNotNull(source, "Source must not be null");
        checkNotNull(target, "Target must not be null");
        if (!source.exists()) {
            //源文件夹不存在
            throw new FileException("Source " + source + " not exist");
        }
        if (!source.isDirectory()) {
            //源文件不是文件夹
            throw new FileException("Source " + source + " is not a directory");
        }
        if (target.exists()) {
            //目标文件夹已存在
            throw new FileException("Target " + target + " already exist");
        }
        //判断移动前和移动后的路径是否一样
        boolean isSame = equalsCanonicalPath(source, target);
        if (isSame) {
            throw new FileException("Source " + source + " and target " + target + " are the same");
        }
        //源文件重命名
        boolean flag = source.renameTo(target);
        out:if (!flag) {
            copyDirectory(source,  target);
            if (deleteQuietly(source)) {
                //源文件删除成功
                break out;
            }
            deleteQuietly(target);
            throw new FileException("Failed to delete source directory " + source + " after copying directory" + target);
        }
    }

    /**
     * 移动文件至指定的文件夹中
     * @param source                    源文件,必须为文件类型
     * @param target                    指定的目录,必须为目录
     * @throws FileException            FileException
     */
    public static void moveFileToDirectory(File source, File target) {
        checkNotNull(source, "Source must not be null");
        checkNotNull(target, "Target must not be null");
        if (!source.exists()) {
            //源文件不存在
            throw new FileException("Source " + source + " not exist");
        }
        if (source.isDirectory()) {
            //源文件为目录
            throw new FileException("Source " + source + " exist but is a directory");
        }
        if (target.exists() && !target.isDirectory()) {
            //指定目录为文件类型
            throw new FileException("Target " + target + " exist but is a file");
        }
        moveFile(source, new File(target, source.getName()));
    }

    /**
     * 移动文件/文件夹至指定的文件夹中
     * @param source                        源文件/文件夹
     * @param target                        指定的目录,必须为目录
     * @throws FileException                FileException
     */
    public static void moveToDirectory(File source, File target) {
        checkNotNull(source, "Source must not be null");
        checkNotNull(target, "Target must not be null");
        if (!source.exists()) {
            //源文件/文件夹不存在
            throw new FileException("Source " + source + " not exist");
        }
        if (source.isDirectory()) {
            //文件夹
            moveDirectoryToDirectory(source, target);
        }else {
            //文件
            moveFileToDirectory(source, target);
        }
    }

    /**
     * 移动文件夹至目标文件夹中
     * @param source                        源文件夹,必须为目录/文件夹
     * @param target                        指定的目录/文件夹,必须为目录/文件夹
     * @throws FileException                FileException
     */
    public static void moveDirectoryToDirectory(File source, File target) {
        checkNotNull(source, "Source must not be null");
        checkNotNull(target, "Target must not be null");
        if (!source.exists()) {
            //源文件夹不存在
            throw new FileException("Source " + source + " not exist");
        }
        if (!target.exists()) {
            //创建目录
            target.mkdirs();
        }
        if (!target.exists()) {
            //创建目录后,目录不存在,无权限创建
            throw new FileException(target + " directory cannot be created");
        }
        if (!target.isDirectory()) {
            //指定的目录/文件夹为文件类型
            throw new FileException(target + " is not a directory");
        }
        moveDirectory(source, new File(target,source.getName()));
    }

    /**
     * 文件复制
     * 如果target文件已存在,会覆盖原有的文件
     * @param source                    源文件,必须为文件
     * @param target                    目标文件,必须为文件
     * @throws FileException            source和target路径一样,source不存在
     * @throws NullPointerException     source或target为空
     */
    public static void copyFile(File source, File target) {
        checkNotNull(source, "Source must not be null");
        checkNotNull(target, "Target must not be null");
        if (!source.exists()) {
            //源文件不存在
            throw new FileException("Source " + source + " not exist");
        }
        if (source.isDirectory()) {
            //源文件是一个目录类型
            throw new FileException("Source " + source + " is a directory");
        }
        //比较copy前和copy后的文件路径是否一样
        boolean isSame = equalsCanonicalPath(source, target);
        if (isSame) {
            //copy前和copy后的文件路径一样
            throw new FileException("Source " + source + " and target " + target + " are the same");
        }
        //创建目录
        File parentFile = target.getParentFile();
        if (!parentFile.mkdirs() && !parentFile.isDirectory()) {
            //创建目录失败
            throw new FileException(parentFile + " directory cannot be created");
        }
        //开始复制文件
        doCopyFile(source, target);
    }

    /**
     * 复制文件夹
     * 如果target中有和source中的文件名重复,会直接覆盖处理
     * @param source                源文件夹,必须为文件夹
     * @param target                目标文件夹,必须为文件夹
     * @throws FileException        source和target唯一绝对路径一样,source不存在
     */
    public static void copyDirectory(File source, File target) {
        checkNotNull(source, "Source must not be null");
        checkNotNull(target, "Target must not be null");
        if (!source.exists()) {
            //源文件夹不存在
            throw new FileException("Source " + source + " not exist");
        }
        if (!source.isDirectory()) {
            //文件存在但不是文件夹
            throw new FileException("Source " + source + " exist but is not a directory");
        }
        //比较copy文件夹之前的路径和之后的路径是否一样
        boolean isSame = equalsCanonicalPath(source, target);
        if (isSame) {
            throw new FileException("Source " + source + " and target " + target + " are the same");
        }
        doCopyDirectory(source, target);
    }


    /**
     * 复制文件
     * 文件复制使用Java NIO中的FileChannel方式
     * @param source            源文件
     * @param target            目标文件
     * @throws FileException,IORuntimeException    target存在时无写入权限,IOException,复制后的文件大小和原文件不一致
     */
    private static void doCopyFile(File source, File target) {
        if (target.exists() && !target.canWrite()) {
            //文件存在，但是无法写入
            throw new FileException("Target " + target + " exist but cannot be written to");
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            long size = inChannel.size();
            long pos = 0;
            long count = 0;
            while (pos < size) {
                count = size - pos > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : size - pos;
                pos += outChannel.transferFrom(inChannel, pos, count);
            }
        }catch (IOException e) {
            throw new IORuntimeException(e);
        }finally {
            closeQuietly(fis, fos, inChannel, outChannel);
        }
        if (source.length() != target.length()) {
            throw new FileException("Failed to copy full contents from " + source + " to " + target);
        }
    }

    /**
     * 复制文件夹
     * @param source            源文件夹
     * @param target            目标文件夹
     * @throws FileException    target存在但不是目录,无创建/写入权限
     */
    private static void doCopyDirectory(File source, File target) {
        File[] files = source.listFiles();
        if (files == null) {
            //目录下没有文件
            return;
        }
        if (target.exists()) {
            if (!target.isDirectory()) {
                //目标文件已存在，但不是文件夹
                throw new FileException("Target " + target + " exist but is not a directory");
            }
        }else {
            if (!target.mkdirs() && !target.isDirectory()) {
                //无法创建目录
                throw new FileException("Target " + target + " directory cannot be created");
            }
        }
        //判断是否有写入权限
        if (!target.canWrite()) {
            throw new FileException("Target " + target + " cannot be written to");
        }
        for (File file : files) {
            File tgtFile = new File(target, file.getName());
            if (file.isDirectory()) {
                doCopyDirectory(file, tgtFile);
            }else {
                doCopyFile(file, tgtFile);
            }
        }
    }

    /**
     * 删除文件
     * 即可以删除文件也可以删除文件夹
     * 遇到错误不抛出异常
     * @param file                  要删除的目标文件
     * @return                      true=删除成功，false=删除过程中出现失败
     */
    public static boolean deleteQuietly(File file) {
        if (isNull(file) || false == file.exists()) {
            //文件为空,返回删除成功
            return true;
        }
        try {
            if (file.isDirectory()) {
                //清空文件夹中的文件
                cleanDirectory(file);
            }
        }catch (Exception e) {
        }

        try {
            return file.delete();
        }catch (Exception e) {
            return false;
        }
    }


    /**
     * 清空文件夹
     * 只清空文件夹中的内容,当前文件夹不会被删除
     * @param directory         指定文件夹
     * @return                  true=清除成功,false=清除过程中出现失败
     * @throws FileException    FileException
     */
    public static boolean cleanDirectory(File directory) {
        if (isNull(directory) || directory.exists() == false) {
            //directory为空或者文件不存在,返回true
            return true;
        }
        if (!directory.isDirectory()) {
            //不是文件夹,抛出异常
            throw new FileException("clean " + directory + " is not a directory");
        }
        File[] files = directory.listFiles();
        if (files == null) {
            //文件夹中没有文件,返回true
            return true;
        }
        boolean flag = false;
        for (File file : files) {
            flag |= !deleteQuietly(file);
        }
        return !flag;
    }

    /**
     * 文件的唯一绝对路径比较
     * @param file1                第一个文件
     * @param file2                第二个文件
     * @return                     ture=一样,false=不一样
     * @throws FileException        FileException
     */
    public static boolean equalsCanonicalPath(File file1, File file2) {
        checkNotNull(file1, "file1 must not be null");
        checkNotNull(file2, "file2 must not be null");
        try {
            return file1.getCanonicalPath().equals(file2.getCanonicalPath());
        }catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * 复制文件到指定的文件夹中
     * 如果源文件已经在目标文件夹中,则进行覆盖
     * @param source                源文件,必须为文件类型
     * @param target                目标文件夹,必须是文件夹类型
     * @throws FileException        FileException
     */
    public static void copyFileToDirectory(File source, File target) {
        checkNotNull(source, "Source must not be null");
        checkNotNull(target, "Target must not be null");
        if (target.exists() && !target.isDirectory()) {
            //目标文件存在，但不是一个文件夹
            throw new FileException("Target " + target + " exist but is not a directory");
        }
        copyFile(source, new File(target, source.getName()));
    }

    /**
     * 复制文件夹至指定的文件夹中
     * @param source                源文件夹,必须为文件夹/目录
     * @param target                目标文件夹,必须是文件夹/目录
     * @throws FileException        FileException
     */
    public static void copyDirectoryToDirectory(File source, File target) {
        checkNotNull(source, "Source must not be null");
        checkNotNull(target, "Target must not be null");
        if (target.exists() && !target.isDirectory()) {
            //目标已存在,但不是一个文件夹/目录
            throw new FileException("Target " + target + " exist but is not a directory");
        }
        copyDirectory(source, new File(target, source.getName()));
    }

    /**
     * 打开指定文件的(输出)OutputStream
     * 如果文件存在即打开,否则创建文件,并创建文件的父目录
     * 打开失败这抛出异常
     * 必须为文件类型否则抛出异常
     * @param file                  指定的文件
     * @param append                是否为文件追加内容操作,如果append=true，则内容将添加到文件的末尾而不是进行覆盖
     * @return                      FileOutputStream
     * @throws FileException        FileException
     */
    public static FileOutputStream openOutputStream(File file, boolean append) {
        checkNotNull(file, "File " + file + " must not be null");
        if (file.exists()) {
            //判断是否是文件夹
            if (file.isDirectory()) {
                throw new FileException("File " + file + " exist but is a directory");
            }
            //判断是否有写入权限
            if (!file.canWrite()) {
                throw new FileException("File " + file + " cannot be written to");
            }
        }else {
            //创建父级目录
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    throw new FileException("Directory " + parent + " could not be created");
                }
            }
        }
        try {
            return new FileOutputStream(file, append);
        }catch (IOException e) {
            //丢出异常
            throw new IORuntimeException(e);
        }
    }

    /**
     * 将字符串写入指定的file中
     * 如果文件中存在内容，则进行覆盖处理
     * 追加写入字符串可以调用{@link #writeString(String, File, boolean)}
     * @param data                  写入的字符串
     * @param file                  指定的文件
     * @param encoding              编码格式,调用{@link String#getBytes(String)}
     */
    public static void writeString(final String data, File file, String encoding) {
        writeString(data, file, false, encoding);
    }

    /**
     * 将字符串写入指定的file文件中
     * @param data                      写入的字符串
     * @param file                      指定的文件
     * @param append                    是否为追加方式写入,如果append=true，则内容将添加到文件的末尾而不是进行覆盖
     */
    public static void writeString(final String data, File file, boolean append) {
        writeString(data, file, append, null);
    }

    /**
     * 将字符串写入指定的file文件中
     * 文件不存在则新建文件
     * @param data                      要写入的字符串
     * @param file                      指定的文件
     * @param append                    是否为追加方式写入,如果append=true，则内容将添加到文件的末尾而不是进行覆盖
     * @param encoding                  编码格式,调用{@link String#getBytes(String)}
     */
    public static void writeString(final String data, File file, boolean append, String encoding) {
        OutputStream output = null;
        try {
            output = openOutputStream(file, append);
            IOUtil.write(data, output, encoding);
        }finally {
            closeQuietly(output);
        }
    }

    /**
     * CharSequence内容写入指定的file文件中
     * 文件不存在则新建文件
     * @param data                      写入的数据
     * @param file                      指定的文件
     * @param append                    是否为追加方式写入,如果append=true，则内容将添加到文件的末尾而不是进行覆盖
     * @param encoding                  编码格式
     */
    public static void write(final CharSequence data, File file, boolean append, String encoding) {
        String str = isNull(data) ? null : data.toString();
        writeString(str, file, append, encoding);
    }

    /**
     * CharSequence内容写入指定的file文件中
     * 文件不存在则新建文件
     * @param data                      写入的数据
     * @param file                      指定的文件
     * @param append                    是否为追加方式写入,如果append=true，则内容将添加到文件的末尾而不是进行覆盖
     */
    public static void write(final CharSequence data, File file, boolean append) {
        write(data, file, append, null);
    }

    /**
     * CharSequence内容写入指定的file文件中
     * 文件不存在则新建文件
     * 如果文件已存在,覆盖已有文件内容
     * @param data                      写入的数据
     * @param file                      指定的文件
     * @param encoding                  编码格式
     */
    public static void write(final CharSequence data, File file, String encoding) {
        write(data, file, false, encoding);
    }

    /**
     * CharSequence内容写入指定的file文件中
     * 文件不存在则新建文件
     * 如果文件已存在,覆盖已有文件内容
     * @param data                      写入的数据
     * @param file                      指定的文件
     */
    public static void write(final CharSequence data, File file) {
        write(data, file, false, null);
    }

    /**
     * 写入行数据,集合中的一条数据占一行,如果位指定换行符,自动获取系统换行符
     * @param data                      写入的集合数据
     * @param file                      指定的文件
     * @param append                    是否为追加方式写入,如果append=true，则内容将添加到文件的末尾而不是进行覆盖
     * @param encoding                  编码格式
     * @param lineEnding                换行符,为空默认获取系统换行符
     */
    public static void writeLines(final Collection<?> data, File file, boolean append, String encoding, String lineEnding) {
        OutputStream output = null;
        try {
            output = openOutputStream(file, append);
            IOUtil.writeLines(data, output, encoding, lineEnding);
        }finally {
            closeQuietly(output);
        }
    }

    /**
     * 写入行数据
     * @param data                      写入的集合数据
     * @param file                      指定的文件
     * @param append                    是否为追加方式写入,如果append=true，则内容将添加到文件的末尾而不是进行覆盖
     * @param encoding                  换行符,为空默认获取系统换行符
     */
    public static void writeLines(final Collection<?> data, File file, boolean append, String encoding) {
        writeLines(data, file, append, encoding, null);
    }

    /**
     * 写入行数据
     * @param data                      写入的集合数据
     * @param file                      指定的文件
     * @param append                    是否为追加方式写入,如果append=true，则内容将添加到文件的末尾而不是进行覆盖
     */
    public static void writeLines(final Collection<?> data, File file, boolean append) {
        writeLines(data, file, append, null, null);
    }

    /**
     * 写入行数据
     * 如果文件存在,覆盖文件内容
     * @param data                      写入的集合数据
     * @param file                      指定的文件
     */
    public static void writeLines(final Collection<?> data, File file) {
        writeLines(data, file, false, null, null);
    }

    /**
     * 写入行数据
     * 如果文件存在,覆盖文件内容
     * @param data                      写入的集合数据
     * @param file                      指定的文件
     * @param encoding                  编码格式
     */
    public static void writeLines(final Collection<?> data, File file, String encoding) {
        writeLines(data, file, false, encoding, null);
    }

    /**
     * 写入byte至文件中
     * @param data                      写入的数据
     * @param file                      指定的文件
     * @param append                    是否为追加方式写入,如果append=true，则内容将添加到文件的末尾而不是进行覆盖
     */
    public static void write(byte[] data, File file, boolean append) {
        OutputStream output = null;
        try {
            output = openOutputStream(file, append);
            output.write(data);
        }catch (IOException e) {
            throw new IORuntimeException(e);
        }finally {
            closeQuietly(output);
        }
    }

    /**
     * 写入byte至文件中
     * 如果文件已存在,会覆盖文件中的内容
     * @param data                      写入的数据
     * @param file                      指定的文件
     */
    public static void write(byte[] data, File file) {
        write(data, file, false);
    }

    /**
     * 打开指定文件的(输入)FileInputStream
     * @param file                      指定的文件,不能为空,不能为文件夹/目录
     * @return                          FileInputStream
     */
    public static FileInputStream openInputStream(File file) {
        checkNotNull(file, "File " + file + " must not be null");
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new FileException("File " + file + " exist but is a directory");
            }
            if (!file.canRead()) {
                throw new FileException("File " + file + " cannot be read");
            }
        }else {
            throw new FileException("File " + file + " not exist");
        }
        try {
            return new FileInputStream(file);
        }catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * 读取一个文件中的内容,返回byte[]
     * @param file                      读取的文件
     * @return                          byte[]
     */
    public static byte[] readFileToByteArray(File file) {
        InputStream in = null;
        try {
            in = openInputStream(file);
            byte[] bytes = IOUtil.toByteArray(in, file.length());
            return bytes;
        }finally {
            closeQuietly(in);
        }
    }

    /**
     * 读取文件内容为字符串
     * @param file                      读取的文件
     * @return                          字符串(String)
     */
    public static String readFileToString(File file) {
        return readFileToString(file, null);
    }

    /**
     * 读取文件内容为字符串
     * @param file                      读取的文件
     * @param encoding                  编码格式
     * @return                          字符串(String)
     */
    public static String readFileToString(File file, String encoding) {
        InputStream in = null;
        try {
            in = openInputStream(file);
            return IOUtil.toString(in, encoding);
        }finally {
            closeQuietly(in);
        }
    }
}
