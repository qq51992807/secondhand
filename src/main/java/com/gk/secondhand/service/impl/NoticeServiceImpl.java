package com.gk.secondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.secondhand.dao.NoticeDao;
import com.gk.secondhand.entity.Notice;
import com.gk.secondhand.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeDao,Notice> implements NoticeService {

    @Resource
    private NoticeDao noticeDao;

    @Override
    public List<Notice> getNoticeList() {

        return noticeDao.getNoticeList();
    }

    @Override
    public void insertSelective(Notice notice) {
        noticeDao.insertSelective(notice);

    }
}
