package com.gk.secondhand.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gk.secondhand.entity.Admin;

public interface AdminDao extends BaseMapper<Admin> {
    public Admin findAdmin(Long phone, String password);

    public Admin findAdminById(Integer id);

    public void updateAdmin(Admin admins);
}
