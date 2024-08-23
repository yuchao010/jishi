package com.example.demo.configuration;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MybatisPlus {
    /**
     * mybatisplus拦截器
     * @return
     */
    @Bean
    public MybatisPlusInterceptor myInterceptor(){
        //定义mybatisplus拦截器
        MybatisPlusInterceptor myInterceptor = new MybatisPlusInterceptor();
        //添加分页拦截器
        myInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //返回拦截器对象
        return myInterceptor;
    }

    /**
     * mybatisplus全局配置
     * @param dataSource
     * @return
     */

    //因为mybatisplus 表名设置为order导致报错 因此添加
//    @Bean
//    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource);
//        // 设置 MyBatis-Plus 全局配置
//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setDbConfig(new GlobalConfig.DbConfig()
//                .setTablePrefix("jishi_")  // 表名前缀
//        );
//        sqlSessionFactory.setGlobalConfig(globalConfig);
//        return sqlSessionFactory;
//    }
}
