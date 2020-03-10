package com.gk.secondhand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Chat {
    @TableId(type = IdType.AUTO)
    private int id;
    private int bid;
    private String bidName;
    private int uid;
    private String content;
    private String uidImg;
    private String bidImg;
    private int status;
    private String sendData;
    private String sendUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUidImg() {
        return uidImg;
    }

    public void setUidImg(String uidImg) {
        this.uidImg = uidImg;
    }

    public String getBidImg() {
        return bidImg;
    }

    public void setBidImg(String bidImg) {
        this.bidImg = bidImg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSendData() {
        return sendData;
    }

    public void setSendData(String sendData) {
        this.sendData = sendData;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", bid=" + bid +
                ", bidName='" + bidName + '\'' +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", uidImg='" + uidImg + '\'' +
                ", bidImg='" + bidImg + '\'' +
                ", status=" + status +
                ", sendData='" + sendData + '\'' +
                ", sendUser='" + sendUser + '\'' +
                '}';
    }
}
