package com.back.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.back.entity.Admin;

/**
 * (Admin)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-08 20:17:49
 */
public interface AdminDao extends BaseMapper<Admin> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<Admin> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<Admin> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<Admin> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<Admin> entities);

Admin selectByAccount(@Param("account") String account);
//逻辑删除掉的账号也查出来
Admin selectByAccount1(@Param("account") String account);

List<String> getAllAccount();

}

