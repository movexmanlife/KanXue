package com.pediy.kanxue.bean;

import java.util.List;

public class TopicBean {
    private List<ForumbitsEntity> forumbits;

    public List<ForumbitsEntity> getForumbits() {
        return forumbits;
    }

    public void setForumbits(List<ForumbitsEntity> forumbits) {
        this.forumbits = forumbits;
    }

    public static class ForumbitsEntity {
        /**
         * 初学者园地、智能设备、Windows、移动平台、信息安全、职场风云、论坛生活、站务管理
         */
        private String forumTitle;
        private List<ForumSubTitleEntity> forumSubTitle;

        public String getForumTitle() {
            return forumTitle;
        }

        public void setForumTitle(String forumTitle) {
            this.forumTitle = forumTitle;
        }

        public List<ForumSubTitleEntity> getForumSubTitle() {
            return forumSubTitle;
        }

        public void setForumSubTitle(List<ForumSubTitleEntity> forumSubTitle) {
            this.forumSubTitle = forumSubTitle;
        }

        public static class ForumSubTitleEntity {
            private int id;
            private int imgId;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getImgId() {
                return imgId;
            }

            public void setImgId(int imgId) {
                this.imgId = imgId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
