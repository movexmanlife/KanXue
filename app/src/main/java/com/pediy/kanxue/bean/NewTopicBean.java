package com.pediy.kanxue.bean;

import java.util.List;

/**
 * 新帖
 */
public class NewTopicBean {
    private int pagenav;
    private int time;
    /**
     * avatar : 1
     * avatardateline : 1430183145
     * globalsticky : 0
     * goodnees : 0
     * lastpostdate : 2016-06-14
     * open : 1
     * postuserid : 366044
     * postusername : ggggwwww
     * replycount : 5
     * sticky : 0
     * threadid : 211017
     * threadtitle : 【原创】【原创】【原创】让子弹继续飞-如何利用一个漏洞代码root更多手机
     * views : 285
     */
    private List<ThreadListEntity> threadList;

    public int getPagenav() {
        return pagenav;
    }

    public void setPagenav(int pagenav) {
        this.pagenav = pagenav;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<ThreadListEntity> getThreadList() {
        return threadList;
    }

    public void setThreadList(List<ThreadListEntity> threadList) {
        this.threadList = threadList;
    }

    public static class ThreadListEntity {
        private int avatar;
        private String avatardateline;
        private int globalsticky;
        private int goodnees;
        private String lastpostdate;
        private int open;
        private int postuserid;
        private String postusername;
        private int replycount;
        private int sticky;
        private int threadid;
        private String threadtitle;
        private int views;

        public int getAvatar() {
            return avatar;
        }

        public void setAvatar(int avatar) {
            this.avatar = avatar;
        }

        public String getAvatardateline() {
            return avatardateline;
        }

        public void setAvatardateline(String avatardateline) {
            this.avatardateline = avatardateline;
        }

        public int getGlobalsticky() {
            return globalsticky;
        }

        public void setGlobalsticky(int globalsticky) {
            this.globalsticky = globalsticky;
        }

        public int getGoodnees() {
            return goodnees;
        }

        public void setGoodnees(int goodnees) {
            this.goodnees = goodnees;
        }

        public String getLastpostdate() {
            return lastpostdate;
        }

        public void setLastpostdate(String lastpostdate) {
            this.lastpostdate = lastpostdate;
        }

        public int getOpen() {
            return open;
        }

        public void setOpen(int open) {
            this.open = open;
        }

        public int getPostuserid() {
            return postuserid;
        }

        public void setPostuserid(int postuserid) {
            this.postuserid = postuserid;
        }

        public String getPostusername() {
            return postusername;
        }

        public void setPostusername(String postusername) {
            this.postusername = postusername;
        }

        public int getReplycount() {
            return replycount;
        }

        public void setReplycount(int replycount) {
            this.replycount = replycount;
        }

        public int getSticky() {
            return sticky;
        }

        public void setSticky(int sticky) {
            this.sticky = sticky;
        }

        public int getThreadid() {
            return threadid;
        }

        public void setThreadid(int threadid) {
            this.threadid = threadid;
        }

        public String getThreadtitle() {
            return threadtitle;
        }

        public void setThreadtitle(String threadtitle) {
            this.threadtitle = threadtitle;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }
    }
}
