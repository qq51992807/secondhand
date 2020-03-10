package com.gk.secondhand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Friend {
    @TableId(type = IdType.AUTO)
    private int id;
    private int bid;
    private int uid;
    private String bidName;
    private int noReadNum;

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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
    }

    public int getNoReadNum() {
        return noReadNum;
    }

    public void setNoReadNum(int noReadNum) {
        this.noReadNum = noReadNum;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", bid=" + bid +
                ", uid=" + uid +
                ", bidName='" + bidName + '\'' +
                ", noReadNum=" + noReadNum +
                '}';
    }
}
