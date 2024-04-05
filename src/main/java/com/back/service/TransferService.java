package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.Transfer;

import java.util.Map;

/**
 * (Transfer)表服务接口
 *
 * @author songjie
 * @since 2022-11-25 22:49:00
 */
public interface TransferService extends IService<Transfer> {
Map saveTransfer(Transfer transfer);
Map getTransferByIdType(String inventoryid,String type);
Map getAllTransferByType(String type);
void setTransferByIdNameType(Integer id,String name,String type);
Map getTransferById(Integer id);
Map deleteTransferById(Integer id);
}

