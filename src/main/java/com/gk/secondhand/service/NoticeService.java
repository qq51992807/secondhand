package com.gk.secondhand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gk.secondhand.dao.NoticeDao;
import com.gk.secondhand.entity.Notice;

import java.util.List;

public interface NoticeService extends IService<Notice> {
    List<Notice> getNoticeList();

    public void insertSelective(Notice notice);
}
