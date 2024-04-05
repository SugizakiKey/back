package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.Returncar;

/**
 * (Returncar)表服务接口
 *
 * @author songjie
 * @since 2022-11-29 16:53:27
 */
public interface ReturncarService extends IService<Returncar> {
void saveReturnCar(Returncar returncar);
}

