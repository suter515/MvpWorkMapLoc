package com.automic.roomdemo.buworkroom.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 类注释：员工工位
 * Created by sujingtai on 2017/6/30 0030 上午 11:21
 */
@Entity
public class EmployeesWorkStation {
@Id(autoincrement = true)
    private long id;
    private String name;
    private String tell;
    private String section;//部门
    private String group;
    @Unique
    private String workStaionId;//工位号
    private int sex;//0是女的，1是男的
    private String imageurl;
    @Generated(hash = 1852769234)
    public EmployeesWorkStation(long id, String name, String tell, String section,
                                String group, String workStaionId, int sex, String imageurl) {
        this.id = id;
        this.name = name;
        this.tell = tell;
        this.section = section;
        this.group = group;
        this.workStaionId = workStaionId;
        this.sex = sex;
        this.imageurl = imageurl;
    }
    @Generated(hash = 1121216895)
    public EmployeesWorkStation() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTell() {
        return this.tell;
    }
    public void setTell(String tell) {
        this.tell = tell;
    }
    public String getSection() {
        return this.section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public String getWorkStaionId() {
        return this.workStaionId;
    }
    public void setWorkStaionId(String workStaionId) {
        this.workStaionId = workStaionId;
    }
    public int getSex() {
        return this.sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getGroup() {
        return this.group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public String getImageurl() {
        return this.imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
