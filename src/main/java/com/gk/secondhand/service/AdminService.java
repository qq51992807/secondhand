package com.gk.secondhand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gk.secondhand.entity.Admin;

public interface AdminService extends IService<Admin> {
    public Admin findAdmin(Long phone, String password);

    public Admin findAdminById(Integer id);

    public void updateAdmin(Admin admins);
}
