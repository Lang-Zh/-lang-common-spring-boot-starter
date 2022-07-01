package cn.lang.security.handler;

import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @ClassName : PreAuthorizeHandler
 * @Description : 鉴权处理器 需要使用者自己实现对应的方法
 * @author : Lang
 * @Date: 2022-06-17
 */
public interface PreAuthorizeHandler {

    /**
     * 所有权限标识
     */
    String ALL_PERMISSION = "*:*:*";

    /**
     * 管理员角色权限标识
     */
    String SUPER_ADMIN = "admin";

    /**
     * 数组为0时
     */
    Integer ARRAY_EMPTY = 0;

    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    default boolean hasPermission(String permission) {
         return true;
    }

    /**
     * 验证用户是否不具备某权限，与 lackPermission逻辑相反
     *
     * @param permission 权限字符串
     * @return 用户是否不具备某权限
     */
    default boolean lacksPermission(String permission) {
        return !hasPermission(permission);
    }

    /**
     * 验证用户是否具有以下任意一个权限
     *
     * @param permissions 权限列表
     * @return 用户是否具有以下任意一个权限
     */
    default boolean hasAnyPermission(String[] permissions) {
        return true;
    }

    /**
     * 判断用户是否拥有某个角色
     *
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    default boolean hasRole(String role) {
        return true;
    }

    /**
     * 验证用户是否不具备某角色，与 isRole逻辑相反。
     *
     * @param role 角色名称
     * @return 用户是否不具备某角色
     */
    default boolean lacksRole(String role) {
        return !hasRole(role);
    }

    /**
     * 验证用户是否具有以下任意一个角色
     *
     * @param roles 角色列表
     * @return 用户是否具有以下任意一个角色
     */
    default boolean hasAnyRoles(String[] roles) {
        return true;
    }

    /**
     * 判断是否包含权限
     *
     * @param authorities 权限列表
     * @param permission  权限字符串
     * @return 用户是否具备某权限
     */
    default boolean hasPermissions(Collection<String> authorities, String permission) {
        return authorities.stream().filter(StringUtils::hasText)
                .anyMatch(x -> ALL_PERMISSION.contains(x) || PatternMatchUtils.simpleMatch(permission, x));
    }
}
