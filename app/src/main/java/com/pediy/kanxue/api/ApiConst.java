package com.pediy.kanxue.api;

public class ApiConst {
    public static final String DOMAIN = "http://bbs.pediy.com";
    public static final String PATH = "/";
    /**
     * 看雪安卓客户端专用的模板id，可通过在看雪任一url后加入模板参数查看各接口返回数据
     */
    public static final String STYLE = "styleid=12";
    public static final int SYTLE_ID = 12;

    // 登录返回状态码
    public static final int LOGIN_SUCCESS = 0;
    public static final int LOGIN_FAIL_LESS_THAN_FIVE = 1; // 用户名或密码错误尝试5次以内
    public static final int LOGIN_FAIL_MORE_THAN_FIVE = 2; // 登录失败次数超过5次,15分钟后才可继续登录

    // 发新贴返回状态码
    public static final int NEW_POST_SUCCESS = 0;
    public static final int NEW_POST_FAIL_WITHIN_THIRTY_SECONDS = 1; // 三十秒内发两个贴
    public static final int NEW_POST_FAIL_WITHIN_FIVE_MINUTES = 2; // 5分钟内发相同内容
    public static final int NEW_POST_FAIL_NOT_ENOUGH_KX = 3; // 看雪币不足

    // 一些板块的id号
    public static final int HELP_FORUM_ID = 20; // 看雪求助问答版块的id
    // public static final int SOFTWARE_DEBUG_FORUM_ID = 4; //软件调试版块的id
    public static final int GET_JOB_FORUM_ID = 47; // 考聘版块id
    public static final int POST_CONTENT_SIZE_MIN = 6; // 发帖或回帖的最小长度
    public static final int NEW_FORUM_ID = 153; // 新贴集合版块id
    public static final int LIFE_FORUM_ID = 45; // 生活放心情版块id
    public static final int SECURITY_FORUM_ID = 61; // 生活放心情版块id

    // 几种置顶类型
    public static final int GLOBAL_TOP_FORUM = -1;
    public static final int AREA_TOP_FORUM = 116;
    public static final int TOP_FORUM = 1;

    public static final int ALLOW_LOGIN_USERNAME_OR_PASSWD_ERROR_NUM = 5;

    public static final String LOGIN_BASE_URL = DOMAIN + PATH;
    /**
     * 倒序
     */
    public static final String DESC = "desc";

    /**
     * 获取看雪用户头像的url
     * @param userId 用户id
     * @return
     */
    public static String getUserAvatarUrl(int userId) {
        return DOMAIN + PATH + "image.php?u=" + userId;
    }
}
