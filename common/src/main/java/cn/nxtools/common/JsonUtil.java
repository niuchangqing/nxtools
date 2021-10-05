package cn.nxtools.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import cn.nxtools.common.exception.JsonException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static cn.nxtools.common.LocalDateTimeUtil.*;

/**
 * @author niuchangqing
 * json工具
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 初始化JavaTimeModule
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //处理LocalDateTime
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(YYYY_MM_DD_HH_MM_SS_SSS));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(YYYY_MM_DD_HH_MM_SS_SSS));
        //处理LocalDate
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(YYYY_MM_DD));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(YYYY_MM_DD));
        //处理LocalTime
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(HH_MM_SS));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(HH_MM_SS));
        //注册时间模块, 支持支持jsr310, 即新的时间类(java.time包下的时间类)
        objectMapper.registerModule(javaTimeModule);
    }

    private JsonUtil() {};


    /**
     * 获取ObjectMapper对象
     * @return              ObjectMapper
     */
    public static ObjectMapper getObjectMapper(){
        return objectMapper;
    }

    /**
     * 对象转字符串
     * @param obj       参数
     * @return          返回字符串
     */
    public static String toString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        }catch (JsonProcessingException e) {
            throw new JsonException("json obj to string error",e);
        }
    }


    /**
     * 字符串转javaBean
     * @param str       指定字符串
     * @param clazz     javaBean.class
     * @param <T>       javaBean
     * @return          返回指定转换的结果对象
     */
    public static <T> T toObj(final String str, Class<T> clazz) {
        try {
            return objectMapper.readValue(str, clazz);
        }catch (JsonProcessingException e) {
            throw new JsonException("string to object error",e);
        }
    }

    /**
     * 字符串转指定java对象
     * <p>
     *     嵌套对象使用,如:{@code List<User>}
     * </p>
     * @param str       指定字符串
     * @param reference TypeReference
     * @param <T>       java对象
     * @return          返回指定转换的结果对象
     */
    public static <T> T toObj(final String str, TypeReference<T> reference) {
        try {
            return objectMapper.readValue(str, reference);
        }catch (JsonProcessingException e) {
            throw new JsonException("string to object error",e);
        }
    }

    /**
     * 字符串转JsonNode
     * @param str       指定字符串
     * @return          返回JsonNode
     */
    public static JsonNode readNode(final String str) {
        try {
            return objectMapper.readTree(str);
        }catch (JsonProcessingException e) {
            throw new JsonException("string to jsonNode error",e);
        }
    }
}
