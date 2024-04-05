package com.back.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.back.entity.Equip;

/**
 * (Equip)表数据库访问层
 *
 * @author songjie
 * @since 2022-11-27 14:12:17
 */
public interface EquipDao extends BaseMapper<Equip> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<Equip> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<Equip> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<Equip> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<Equip> entities);
Double getTotalByInventoryid(@Param("inventoryid")String inventoryid);
Double getTotal();
}

