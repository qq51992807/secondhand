package com.gk.secondhand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 用户
 *
 *
 */
/**
 * 默认为NONE，跟随数据库
 *
 *
 * 局部主键策略的设置：
 * AUTO(0)：数据库ID自增，跟随数据库，不适合于分布式场景
 * NONE(1)：默认，局部不设置ID时跟随全局策略
 * INPUT(2)：用户输入ID，在数据量很少时可以当测试使用
 *
 --以下三种类型仅在插入对象ID为空时，才自动填充
 * ID_WORKER(3)：数值类型的雪花算法主键，这也是全局默认的策略
 * UUID(4)：UUID是128位，它标识性强，但字符串过长会影响查询时间，对查询时间不敏感时使用
 * ID_WORKER_STR(5)：字符串类型的雪花算法主键
 *
 * 这里设置UUID出错
 *
 * MYSQL里通常不写
 *
 * 主键策略定义
 */
public class User {

    @TableId(type=IdType.AUTO)
    private Integer id;

    private String phone;

    private String username;

    private String password;

    private String qq;

    private String createAt;

    private Integer goodsNum;

    private Integer power;

    private String lastLogin;

    private Byte status;
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt == null ? null : createAt.trim();
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin == null ? null : lastLogin.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", phone=" + phone + ", username=" + username + ", password=" + password + ", qq="
				+ qq + ", createAt=" + createAt + ", goodsNum=" + goodsNum + ", power=" + power + ", lastLogin="
				+ lastLogin + ", status=" + status + "]";
	}



    
    
}