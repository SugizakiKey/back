package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.CarAssessment;

import java.util.Map;

/**
 * (CarAssessment)表服务接口
 *
 * @author songjie
 * @since 2022-10-31 19:51:04
 */
public interface CarAssessmentService extends IService<CarAssessment> {
Map getInventoryid();
Map saveCarAssessment(CarAssessment carAssessment);
Map getAllCarAssessment();
void setCarurl(String inventoryid);
Map getCarAssessmentById(String inventoryid);
Map setAssessmentState(String inventoryid);
Map deleteCarAssessmentById(String inventoryid);
Map getCarAssessmentByIds(String inventoryid[]);
Map getIndexInfo();
}

