package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.TransferDao;
import com.back.entity.Transfer;
import com.back.service.TransferService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Transfer)表服务实现类
 *
 * @author songjie
 * @since 2022-11-25 22:49:00
 */
@Service("transferService")
public class TransferServiceImpl extends ServiceImpl<TransferDao, Transfer> implements TransferService {

    @Resource
    private TransferDao transferDao;

    @Override
    public Map saveTransfer(Transfer transfer) {
        Map map=new HashMap();
        LambdaQueryWrapper<Transfer> transferLambdaQueryWrapper=new LambdaQueryWrapper<>();
        transferLambdaQueryWrapper.eq(Transfer::getInventoryid,transfer.getInventoryid())
                .eq(Transfer::getType,transfer.getType());
        Transfer transfer1 = transferDao.selectOne(transferLambdaQueryWrapper);
        if (transfer1==null){
            transferDao.insert(transfer);
            map.put("code",1);
            map.put("message","新增"+transfer.getType()+"成功！！！");
        }else {
            transfer.setId(transfer1.getId());
            transferDao.updateById(transfer);
            map.put("code",1);
            map.put("message","修改"+transfer.getType()+"成功！！！");
        }
        return map;
    }

    @Override
    public Map getTransferByIdType(String inventoryid, String type) {
        Map map=new HashMap();
        LambdaQueryWrapper<Transfer>transferLambdaQueryWrapper=new LambdaQueryWrapper<>();
        transferLambdaQueryWrapper.eq(Transfer::getInventoryid,inventoryid)
                .eq(Transfer::getType,type);
        Transfer transfer = transferDao.selectOne(transferLambdaQueryWrapper);
        if (transfer!=null){
            map.put("Transfer",transfer);
            map.put("code",1);
            map.put("message","message");
        }else {
            map.put("code",2);
            map.put("message","message");
        }
        return map;
    }

    @Override
    public Map getAllTransferByType(String type) {
        Map map=new HashMap();
        if (type.equals("all")){
            List<Transfer> transfers = transferDao.selectList(null);
            map.put("transfers",transfers);
        }else {
            LambdaQueryWrapper<Transfer> transferLambdaQueryWrapper=new LambdaQueryWrapper<>();
            transferLambdaQueryWrapper.eq(Transfer::getType,type);
            List<Transfer> transfers = transferDao.selectList(transferLambdaQueryWrapper);
            map.put("transfers",transfers);
        }
        return map;
    }

    @Override
    public void setTransferByIdNameType(Integer id, String name,String type) {
        Transfer transfer = transferDao.selectById(id);
        switch (name){
            case "violationhandling":
                transfer.setViolationhandling(type);
                break;
            case "carexamination":
                transfer.setCarexamination(type);
                break;
            case "licenseplate":
                transfer.setLicenseplate(type);
                break;
            case "showcards":
                transfer.setShowcards(type);
                break;
            case "dataprocessing":
                transfer.setDataprocessing(type);
                break;
        }
        transferDao.updateById(transfer);
    }

    @Override
    public Map getTransferById(Integer id) {
        Transfer transfer = transferDao.selectById(id);
        Map map=new HashMap();
        map.put("transfer",transfer);
        return map;
    }

    @Override
    public Map deleteTransferById(Integer id) {
        Map map=new HashMap();
        Transfer transfer = transferDao.selectById(id);
        if (transfer.getTransferstate().equals("取消")){
            transferDao.deleteById(id);
            map.put("code",1);
            map.put("message","删除过户单成功！！！");
        }else {
            map.put("code",0);
            map.put("message","只能删除已取消的过户单！！！");
        }
        return map;
    }


}

