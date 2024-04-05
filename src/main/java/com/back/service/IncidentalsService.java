package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.Incidentals;

import java.util.Map;

/**
 * (Incidentals)表服务接口
 *
 * @author songjie
 * @since 2022-11-26 16:48:37
 */
public interface IncidentalsService extends IService<Incidentals> {
void saveIncidentals(String inventoryid, String branddept, String personinchargeaccount, Integer rows, String costitems[], Double amounts[],
                     String types[], String remarks[], String dates[]);


Map getAllIncidentalsInfo();
}

