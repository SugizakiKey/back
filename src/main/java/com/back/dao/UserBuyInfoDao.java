package com.back.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.back.entity.UserBuyInfo;

/**
 * (UserBuyInfo)表数据库访问层
 *
 * @author songjie
 * @since 2022-11-28 15:37:11
 */
public interface UserBuyInfoDao extends BaseMapper<UserBuyInfo> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<UserBuyInfo> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<UserBuyInfo> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<UserBuyInfo> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<UserBuyInfo> entities);
List<UserBuyInfo>getDeleteUserInfo();
void deleteReallyById(@Param("id")Integer id);
}

