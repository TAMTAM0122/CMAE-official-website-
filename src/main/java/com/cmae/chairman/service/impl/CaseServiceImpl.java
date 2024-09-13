package com.cmae.chairman.service.impl;

import com.cmae.chairman.entity.Case;
import com.cmae.chairman.mapper.CaseMapper;
import com.cmae.chairman.service.ICaseService;
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
public class CaseServiceImpl extends ServiceImpl<CaseMapper, Case> implements ICaseService {
    @Autowired
    private CaseMapper caseMapper;

    // 添加新 case
    public void addCase(Case caseData) {
        caseMapper.insert(caseData);  // 使用MyBatis-Plus的 insert 方法
    }

    public void updateCase(Case caseData) {
        caseMapper.updateById(caseData);
    }

    @Override
    public Case getCase(int id) {
        return caseMapper.selectById(id);
    }
}
