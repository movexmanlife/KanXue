package com.pediy.kanxue.bean;

import java.util.ArrayList;
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

            /**
             * 非服务器传过来的，需要自己手动设置
             */
            private String categroyName;

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

            public String getCategroyName() {
                return categroyName;
            }

            public void setCategroyName(String categroyName) {
                this.categroyName = categroyName;
            }
        }
    }

    /**
     * 将原始数据转化为StickyRecycler的数据
     * @param forumbitsEntityList
     * @return
     */
    public static List<ForumbitsEntity.ForumSubTitleEntity> convertToStickyData(List<ForumbitsEntity> forumbitsEntityList) {
        if (forumbitsEntityList != null || forumbitsEntityList.isEmpty()) {
            return null;
        }
        List<ForumbitsEntity.ForumSubTitleEntity> subList = new ArrayList<>();
        for (ForumbitsEntity entity : forumbitsEntityList) {
            String categoryName = entity.getForumTitle();

            for (ForumbitsEntity.ForumSubTitleEntity subEntity : entity.getForumSubTitle()) {
                subEntity.setCategroyName(categoryName);
                subList.add(subEntity);
            }
        }

        return subList;
    }
}
