package cn.nxtools.jwt;

import cn.nxtools.common.CollectionUtil;
import cn.nxtools.common.JsonUtil;
import cn.nxtools.common.LocalDateTimeUtil;
import cn.nxtools.common.StringUtil;
import cn.nxtools.common.base.Preconditions;
import cn.nxtools.common.collect.Lists;
import cn.nxtools.common.collect.Maps;
import cn.nxtools.jwt.autoconfigure.JwtServerProperties;
import cn.nxtools.jwt.config.JwtSecretKey;
import cn.nxtools.jwt.domain.CustomUserDetail;
import cn.nxtools.jwt.domain.JwtTokenDto;
import com.fasterxml.jackson.core.type.TypeReference;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * jwt相关操作方法
 * 当前类中所有方法的token入参,如果token有固定字符开始,截取后调用
 * 如:Bearer eyJhbGciOiJIUzI1NiIsInppcCI6IkRFRiJ9.eNokzL0KgzAYheF7-WYD5tfo7lYoWDrVIp-aNOlgi0mgVLz3RjqeB867QUgjNPB1uDwCLlBACmYd_JyRMo5hFlJlxRhxcubgbc_bfN7QUMW5LqVmsgCP8Q8VrcUBmKJ7rT56E_Lp1kN3PrXD9dJ2Pdxz4Rl9dkmFNXVpCXJridBiInpilNSjtpWy0ijGYP8BAAD__w.WpJKTlkAA8mwQSH0-VkPRWiyDjKZtn9Uftw6ZuceJKo
 * 调用:eyJhbGciOiJIUzI1NiIsInppcCI6IkRFRiJ9.eNokzL0KgzAYheF7-WYD5tfo7lYoWDrVIp-aNOlgi0mgVLz3RjqeB867QUgjNPB1uDwCLlBACmYd_JyRMo5hFlJlxRhxcubgbc_bfN7QUMW5LqVmsgCP8Q8VrcUBmKJ7rT56E_Lp1kN3PrXD9dJ2Pdxz4Rl9dkmFNXVpCXJridBiInpilNSjtpWy0ijGYP8BAAD__w.WpJKTlkAA8mwQSH0-VkPRWiyDjKZtn9Uftw6ZuceJKo
 * @author ncq
 */
public class JwtUtil {

    private static final Logger logger = Logger.getLogger(JwtUtil.class.getName());

    @Autowired(required = false)
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JwtServerProperties jwtServerProperties;

    @Autowired(required = false)
    private JwtSecretKey jwtSecretKey;

    public static final String REFRESH_TOKEN_PERMISSIONS = "ROLE_REFRESH_TOKEN_PERMISSIONS";

    /**
     * claims存放权限集合key
     */
    private static final String CLAIMS_AUTHORITIES_KEY = "authorities";

    /**
     * claims存放用户ID的key
     */
    private static final String CLAIMS_USER_ID_KEY = "user_id";

    /**
     * claims存放用户附加信息key
     */
    private static final String CLAIMS_ATTACHED_KEY = "attached";

    /**
     * token失效最后时间
     * key=token唯一标记
     * value=当前时间戳
     * 有redisTemplate时使用redis进行存储不在进行本地缓存存储
     * 无redisTemplate时使用TOKEN_DEADLINE字段进行本地缓存存储
     * 注意无清除map集合操作,用户量大建议使用redis,防止内存溢出或map扩容问题
     * 不需要token失效功能,配置nxtools.jwt.enabledLogout=false即可
     */
    private final Map<String, Long> TOKEN_DEADLINE = new ConcurrentHashMap<>();
    /**
     * %s={@link cn.nxtools.jwt.domain.JwtTokenDto#jti}
     * %s={@link Claims#getId()}
     * jti和getId()值一致
     */
    private static final String REDIS_TOKEN_DEADLINE_KEY = "NXTOOLS_JWT_DEADLINE_KEY_%s";

    /**
     * 用户所有客户端token都失效最后时间
     * key=用户唯一标记,user_id
     * value=当前时间戳
     * 有redisTemplate时使用redis进行存储不在进行本地缓存存储
     * 无redisTemplate时使用TOKEN_DEADLINE字段进行本地缓存存储
     * 注意无清除map集合操作,用户量大请使用redis,防止内存溢出或map扩容问题
     * 不需要token失效功能,配置nxtools.jwt.enabledLogout=false即可
     */
    private final Map<String, Long> TOKEN_DEADLINE_ALL_CLIENT = new ConcurrentHashMap<>();
    /**
     * %s={@link CustomUserDetail#userId}
     */
    private static final String REDIS_TOKEN_DEADLINE_ALL_CLIENT_KEY = "NXTOOLS_JWT_DEADLINE_ALL_CLIENT_KEY_%s";

    /**
     * 根据指定token获取用户信息
     * @param token     access_token
     * @return          token解析出来的用户信息
     */
    public CustomUserDetail tokenToUser(String token) {
        Preconditions.checkNotNull(token, "access_token cannot be null");
        CustomUserDetail userDetail = null;
        try {
            Claims claims = tokenToClaims(token);
            userDetail = claimsToUser(claims);
        } catch (Exception e) {
            logger.log(Level.WARNING, "token to user error", e);
        }
        return userDetail;
    }

    /**
     * 解析token
     */
    private Claims tokenToClaims(String token) {
        //access_token
        Claims claims = null;
        try {
            if (jwtServerProperties.getSignatureAlgorithm().isNone()) {
                //无签名算法
                claims = Jwts.parser()
                        .parseClaimsJwt(token)
                        .getBody();
            } else if (jwtServerProperties.getSignatureAlgorithm().isHmac()) {
                //secret签名
                claims = Jwts.parser()
                        .setSigningKey(jwtServerProperties.getSecret())
                        .parseClaimsJws(token)
                        .getBody();
            } else {
                //公私钥签名
                claims = Jwts.parser()
                        .setSigningKey(jwtSecretKey.getPublicKey())
                        .parseClaimsJws(token)
                        .getBody();
            }
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        } catch (Exception e) {
            logger.log(Level.WARNING,"token to claims error", e);
        }
        return claims;
    }

    private CustomUserDetail claimsToUser(Claims claims) {
        String username = claims.getSubject();
        //权限集合
        String authoritiesStr = claims.get(CLAIMS_AUTHORITIES_KEY).toString();
        List<SimpleGrantedAuthority> authorities = JsonUtil.toObj(authoritiesStr, new TypeReference<List<String>>() {
        }).stream().map(m -> new SimpleGrantedAuthority(m)).collect(Collectors.toList());
        CustomUserDetail userDetail = new CustomUserDetail(username, authorities);
        //设置用户ID
        String userId = claims.get(CLAIMS_USER_ID_KEY).toString();
        userDetail.setUserId(userId);
        //用户附加信息
        if (claims.get(CLAIMS_ATTACHED_KEY) != null) {
            Map<String, String> attached = JsonUtil.toObj(claims.get(CLAIMS_ATTACHED_KEY).toString(), new TypeReference<Map<String, String>>() {
            });
            userDetail.setAttached(attached);
        }
        return userDetail;
    }

    /**
     * 检查token是否有效
     * @param token             access_token or refresh_token
     * @param userDetail        用户信息
     * @return                  true=有效，false=无效
     */
    public boolean checkToken(String token, CustomUserDetail userDetail) {
        Preconditions.checkNotNull(userDetail, "userDetail cannot be null");
        final String userId = getUserIdFromToken(token);
        if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(userDetail.getUserId())) {
            return false;
        }
        //token中的user_id和userDetail中的user_id不一样
        if (!userId.equals(userDetail.getUserId())) {
            return false;
        }
        //校验token是否有效
        return checkToken(token);
    }

    /**
     * 检查token是否有效
     * @param token             access_token or refresh_token
     * @return                  true=有效，false=无效
     */
    public boolean checkToken(String token) {
        //判断token是否超时
        if (checkTokenExpired(token)) {
            //已超时
            return false;
        }
        //判断用户是否已经注销
        if (checkTokenLogout(token)) {
            //已注销
            return false;
        }
        //有效
        return true;
    }

    /**
     * token获取token中包含的用户唯一标识(user_id)
     * @param token             access_token or refresh_token
     * @return                  user_id
     */
    public String getUserIdFromToken(String token) {
        if (StringUtil.isEmpty(token)) {
            return null;
        }
        Claims claims = tokenToClaims(token);
        return claims.get(CLAIMS_USER_ID_KEY).toString();
    }

    /**
     * 校验token是否已到期
     * @param token             access_token or refresh_token
     * @return                  true=已到期(不可用),false=未到期
     */
    private boolean checkTokenExpired(String token) {
        Date expirationDate = getExpirationFromToken(token);
        return expirationDate.before(new Date());
    }

    /**
     * 获取token超时时间
     * @param token             access_token or refresh_token
     * @return                  token到期时间
     */
    private Date getExpirationFromToken(String token) {
        Claims claims = tokenToClaims(token);
        return claims.getExpiration();
    }

    /**
     * token是否已经注销
     * @param token             access_token or refresh_token
     * @return                  true=已注销,false=未注销
     */
    public boolean checkTokenLogout(String token) {
        if (jwtServerProperties.getEnabledLogout() == null || !jwtServerProperties.getEnabledLogout()) {
            //未启用注销功能,默认返回未注销
            return false;
        }
        Claims claims = tokenToClaims(token);
        Long deadlineTime = null;
        if (jwtServerProperties.getLogoutAllClients() == null || !jwtServerProperties.getLogoutAllClients()) {
            //只注销当前调用token
            String id = claims.getId();
            if (checkRedisEnabled()) {
                //使用redis缓存
                deadlineTime = getDeadlineTimeFromRedis(String.format(REDIS_TOKEN_DEADLINE_KEY, id));
            } else {
                //使用本地内存缓存
                deadlineTime = TOKEN_DEADLINE.get(id);
            }
        } else {
            //注销全部客户端token
            String userId = claims.get(CLAIMS_USER_ID_KEY).toString();
            if (checkRedisEnabled()) {
                //使用redis缓存
                deadlineTime = getDeadlineTimeFromRedis(String.format(REDIS_TOKEN_DEADLINE_ALL_CLIENT_KEY, userId));
            } else {
                //使用本地内存缓存
                deadlineTime = TOKEN_DEADLINE_ALL_CLIENT.get(userId);
            }
        }

        if (deadlineTime == null) {
            //注销时间为空,未注销
            return false;
        }
        //判断退出时间戳晚于token创建时间
        Date date = claims.getIssuedAt();
        if (new Date(deadlineTime).before(date)) {
            //注销时间在创建时间之前(注销时间小于token创建时间),未注销
            return false;
        } else {
            //注销时间在创建时间之后(注销时间大于token创建时间),已注销
            return true;
        }
    }

    /**
     * 检查redisTemplate是否可用
     * @return              true=可用，false=不可用
     */
    private boolean checkRedisEnabled() {
        if (redisTemplate != null && redisTemplate.getConnectionFactory().getConnection() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据key值获取过期时间戳
     * @param key               指定的key
     * @return                  时间戳 {@link Date#getTime()}
     */
    private Long getDeadlineTimeFromRedis(String key) {
        String deadlineTimeStr = redisTemplate.opsForValue().get(key);
        if (StringUtil.isEmpty(deadlineTimeStr)) {
            return null;
        } else {
            return Long.valueOf(deadlineTimeStr);
        }
    }

    /**
     * 生成jwtTokenDto
     * @param userDetail        用户信息
     * @return                  JwtTokenDto
     */
    public JwtTokenDto generateJwtTokenDto(CustomUserDetail userDetail) {
        Preconditions.checkNotNull(userDetail, "userDetail cannot be null");
        Preconditions.checkNotNull(userDetail.getUserId(), "userId cannot be null");
        Map<String, Object> claims = userDetailToClaims(userDetail);
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        //生成access_token
        String accessToken = generateAccessToken(userDetail.getUsername(), claims);
        jwtTokenDto.setAccess_token(accessToken);
        //生成refresh_token
        String refreshToken = generateRefreshToken(userDetail.getUsername(), claims);
        jwtTokenDto.setRefresh_token(refreshToken);

        Claims accessClaims = tokenToClaims(accessToken);
        //access_token唯一标记
        jwtTokenDto.setJti(accessClaims.getId());
        //access_token过期时间戳
        jwtTokenDto.setExpires_in(accessClaims.getExpiration().getTime());

        Claims refreshClaims = tokenToClaims(refreshToken);
        //refresh_token过期时间戳
        jwtTokenDto.setRefresh_expires_in(refreshClaims.getExpiration().getTime());
        return jwtTokenDto;
    }


    private String generateAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, jwtServerProperties.getExpiration());
    }

    private String generateRefreshToken(String subject, final Map<String, Object> claims) {
        Map<String, Object> refreshClaims = Maps.newHashMap(claims);
        refreshClaims.put(CLAIMS_AUTHORITIES_KEY, JsonUtil.toString(Lists.newArrayList(jwtServerProperties.getRefreshTokenPermissions())));
        return generateToken(subject, refreshClaims, jwtServerProperties.getRefreshExpiration());
    }

    /**
     * 生成token方法
     * @param subject           jwt subject，可以是登陆账号等
     * @param claims            jwt claims存放信息
     * @param expiration        token超时时间
     * @return                  token字符串
     */
    private String generateToken(String subject, Map<String, Object> claims, long expiration) {
        Date date = new Date();
        claims.putAll(jwtServerProperties.getClaims());
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                //当前token唯一标记
                .setId(UUID.randomUUID().toString())
                //token生成时间
                .setIssuedAt(date)
                //过期时间
                .setExpiration(new Date(date.getTime() + expiration * 1000));

        if (jwtServerProperties.getSignatureAlgorithm().isNone()) {
            //不需要签名算法
        } else if (jwtServerProperties.getSignatureAlgorithm().isHmac()) {
            jwtBuilder.signWith(jwtServerProperties.getSignatureAlgorithm().getJwtSignatureAlgorithm(), jwtServerProperties.getSecret());
        }else {
            jwtBuilder.signWith(jwtServerProperties.getSignatureAlgorithm().getJwtSignatureAlgorithm(), jwtSecretKey.getPrivateKey());
        }
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 将userDetail中的部分属性转换未claims
     * @param userDetail                    userDetail
     * @return                              Map
     */
    private Map<String, Object> userDetailToClaims(CustomUserDetail userDetail) {
        Map<String, Object> claims = Maps.newHashMapWithSize(16);
        claims.put(CLAIMS_USER_ID_KEY, userDetail.getUserId());
        if (CollectionUtil.isEmpty(userDetail.getAttached())) {
            claims.put(CLAIMS_ATTACHED_KEY, JsonUtil.toString(Maps.newHashMap()));
        } else {
            claims.put(CLAIMS_ATTACHED_KEY, JsonUtil.toString(userDetail.getAttached()));
        }
        List<String> authoritiesStrList = null;
        if (CollectionUtil.isEmpty(userDetail.getAuthorities())) {
            authoritiesStrList = Lists.newArrayListWithSize(0);
        } else {
            authoritiesStrList = Lists.newArrayListWithSize(userDetail.getAuthorities().size());
            for (GrantedAuthority authority : userDetail.getAuthorities()) {
                authoritiesStrList.add(authority.getAuthority());
            }
        }
        claims.put(CLAIMS_AUTHORITIES_KEY, JsonUtil.toString(authoritiesStrList));
        return claims;
    }

    /**
     * 注销,可根据配置进行注销单个token还是当前用户所有客户端
     * 不建议使用，正常token都是无状态的，除非业务强制要求，否则让web端清除token缓存即可
     * @param accessToken           用户请求token
     */
    public void logout(String accessToken) {
        if (StringUtil.isEmpty(accessToken)) {
            //token为空，直接返回
            return;
        }
        //判断是否开启注销功能
        if (jwtServerProperties.getEnabledLogout() != null && !jwtServerProperties.getEnabledLogout()) {
            //未开启注销功能,直接返回
            return;
        }
        //判断是否过期或已注销
        if (!checkToken(accessToken)) {
            //已过期或者无效
            return;
        }
        Claims claims = tokenToClaims(accessToken);
        //是否使用redis进行存储过期记录
        if (checkRedisEnabled()) {
            //redis
            if (jwtServerProperties.getLogoutAllClients() != null && jwtServerProperties.getLogoutAllClients()) {
                //注销全部客户端已签发的token
                String key = String.format(REDIS_TOKEN_DEADLINE_ALL_CLIENT_KEY, claims.get(CLAIMS_USER_ID_KEY));
                redisTemplate.opsForValue().set(key, String.valueOf(LocalDateTimeUtil.currentTimeMillis()));
            } else {
                //注销当前正在使用的token
                String key = String.format(REDIS_TOKEN_DEADLINE_KEY, claims.getId());
                long timeout = claims.getExpiration().getTime() - LocalDateTimeUtil.currentTimeMillis();
                if (timeout > 0) {
                    redisTemplate.opsForValue().set(key, String.valueOf(LocalDateTimeUtil.currentTimeSecond()), timeout, TimeUnit.MILLISECONDS);
                }
            }
        } else {
            //内存记录
            if (jwtServerProperties.getLogoutAllClients() != null && jwtServerProperties.getLogoutAllClients()) {
                //注销全部客户端已签发的token
                TOKEN_DEADLINE_ALL_CLIENT.put(claims.get(CLAIMS_USER_ID_KEY).toString(), LocalDateTimeUtil.currentTimeMillis());
            } else {
                //注销当前正在使用的token
                TOKEN_DEADLINE.put(claims.getId(), LocalDateTimeUtil.currentTimeMillis());
            }
        }

    }
}
