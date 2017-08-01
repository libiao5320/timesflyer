package com.lee.content.model;

import java.io.Serializable;

/**
 * Created by libia on 2016/2/1.
 */
public class UrlSeed implements Serializable {

        private String url;
        private String name;
        private String createDate;
        private String lastUpDate;
        private int isCatch;
        private int catchType;

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCreateDate() {
                return createDate;
        }

        public void setCreateDate(String createDate) {
                this.createDate = createDate;
        }

        public String getLastUpDate() {
                return lastUpDate;
        }

        public void setLastUpDate(String lastUpDate) {
                this.lastUpDate = lastUpDate;
        }

        public int getIsCatch() {
                return isCatch;
        }

        public void setIsCatch(int isCatch) {
                this.isCatch = isCatch;
        }

        public int getCatchType() {
                return catchType;
        }

        public void setCatchType(int catchType) {
                this.catchType = catchType;
        }
}
