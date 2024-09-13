package com.cmae.chairman.service;

import com.cmae.chairman.entity.Honor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TAMTAM
 * @since 2024-09-03
 */
public interface IHonorService extends IService<Honor> {
    public void addHonor(Honor honor);

    public void updateHonor(Honor honor);
}
