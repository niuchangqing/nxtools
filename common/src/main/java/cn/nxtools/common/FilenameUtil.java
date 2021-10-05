package cn.nxtools.common;

import static cn.nxtools.common.StringUtil.EMPTY;
import static cn.nxtools.common.base.Objects.isNull;

/**
 * 文件名称处理工具
 */
public class FilenameUtil {

    private FilenameUtil(){
    }

    public static final char EXTENSION_SEPARATOR = '.';

    /**
     * unix系统文件夹分隔符
     */
    private static final char UNIX_SEPARATOR = '/';

    /**
     * windows系统文件夹分隔符
     */
    private static final char WINDOWS_SEPARATOR = '\\';

    private static final int NOT_FOUND = -1;

    /**
     * 获取文件后缀名
     * 如：图片.JPG = JPG
     * @param filename          文件名称
     * @return                  文件后缀名
     */
    public static String getExtension(final String filename) {
        if (isNull(filename)) {
            return null;
        }

        int i = filename.lastIndexOf(EXTENSION_SEPARATOR);
        if (i == NOT_FOUND) {
            return EMPTY;
        }
        return filename.substring(i + 1);
    }

    /**
     * 获取文件名称，带后缀名
     * 如：/文件夹/图片.JPG = 图片.JPG
     * @param filename            文件名称/文件路径
     * @return                    文件名称,带后缀名
     */
    public static String getName(final String filename) {
        if (isNull(filename)) {
            return null;
        }
        int i = indexOfLastSeparator(filename);
        if (i == NOT_FOUND) {
            return EMPTY;
        }
        return filename.substring(i + 1);
    }

    /**
     * 获取文件名的最后一个文件夹分割符位置
     * @param filename              文件名称
     * @return                      最后一个文件分割符所在位置,没有返回-1
     */
    public static int indexOfLastSeparator(final String filename) {
        if (isNull(filename)) {
            return NOT_FOUND;
        }
        //unix系统分割符号
        int unix = filename.lastIndexOf(UNIX_SEPARATOR);
        int windows = filename.lastIndexOf(WINDOWS_SEPARATOR);
        return Math.max(unix, windows);
    }

    /**
     * 获取文件名称，不包含后缀名
     * @param filename              文件名称
     * @return                      文件名称，不包含后缀名
     */
    public static String getBaseName(final String filename) {
        final String name = getName(filename);
        if (isNull(name)) {
            return null;
        }
        int i = name.lastIndexOf(EXTENSION_SEPARATOR);
        if (i == NOT_FOUND) {
            return name;
        }
        return name.substring(0, i);
    }
}
