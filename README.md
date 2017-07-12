#1 mvp_workmap_loc
图片定位
mvp struct with datepicker
本框架采用了mvp框架，充分参考了谷歌的mvpdemo，
而且集成了，流行的rxjava-retrofit-okhtp网络访问框架，
为了兼容不同android系统的时间控件的差异，特此集成datepickerk框架。

#2.重点
greendao的配置
retrofit 注解的学习，service，loader，interactor等学习
mvp 如何解耦，如何在activity层面操作。

#3.特点：
adapter基类采用反射
数据库操作基类使用反射，和greendao框架。2者都有，但是更推荐使用greendao。
按照业务逻辑构建包文件

com.automic.roomdemo

--application

--baseparts

           --dao
                --DaoMaster(greenDao自动生成)
                --DaoSession(greenDao自动生成)
                --EmployeesWorkSationDao (greenDao自动生成)
                --SqlHelper (利用反射自己写的)
                --SqlHelperImpl(利用反射自己写的)
                --DBOpenHelper(利用反射自己写的)
           公共基类
           
--buraininfo(业务one)

             --bean(业务one对象)
             
             --model(业务one子模型)
             
             --presenter(业务one主持人)
             
             --view(业务one视图)
             
                   --activity
                   --fragment
                   --adapter
                   
--http(网络公共基类)

--receiver(广播接收)

--utils(工具类)

--views(自定义view)

--MainActivity


具体介绍见：csdn
我将公司信息过滤后，原理和方法都写到博客里去。
