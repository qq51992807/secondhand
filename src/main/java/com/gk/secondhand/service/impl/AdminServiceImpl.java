package com.gk.secondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.secondhand.dao.AdminDao;
import com.gk.secondhand.entity.Admin;
import com.gk.secondhand.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao,Admin> implements AdminService {

    @Resource
    private AdminDao adminDao;
    @Override
    public Admin findAdmin(Long phone, String password) {
        // TODO Auto-generated method stub
        return adminDao.findAdmin(phone,password);
    }

    @Override
    public Admin findAdminById(Integer id) {
        // TODO Auto-generated method stub
        return adminDao.findAdminById(id);
    }

    @Override
    public void updateAdmin(Admin admins) {
        adminDao.updateAdmin(admins);
    }
}
