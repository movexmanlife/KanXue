package com.pediy.kanxue.api;

public class ApiConst {
    public static final String DOMAIN = "http://bbs.pediy.com";
    public static final String PATH = "/";
    /**
     * 看雪安卓客户端专用的模板id，可通过在看雪任一url后加入模板参数查看各接口返回数据
     */
    public static final String STYLE = "styleid=12";
    // 登录返回状态码
    public static final int LOGIN_SUCCESS = 0;
    public static final int LOGIN_FAIL_LESS_THAN_FIVE = 1; // 用户名或密码错误尝试5次以内
    public static final int LOGIN_FAIL_MORE_THAN_FIVE = 2; // 登录失败次数超过5次,15分钟后才可继续登录
    public static final String LOGIN_BASE_URL = DOMAIN + PATH;
}
