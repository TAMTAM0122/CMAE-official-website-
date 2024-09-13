package com.cmae.chairman.service.impl;

import com.cmae.chairman.entity.Honor;
import com.cmae.chairman.mapper.HonorMapper;
import com.cmae.chairman.service.IHonorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author TAMTAM
 * @since 2024-09-03
 */
@Service
public class HonorServiceImpl extends ServiceImpl<HonorMapper, Honor> implements IHonorService {
    @Autowired
    private HonorMapper honorMapper;

    @Override
    public void addHonor(Honor honor) {
        honorMapper.insert(honor);
    }

    @Override
    public void updateHonor(Honor honor) {
        honorMapper.updateById(honor);
    }
}
