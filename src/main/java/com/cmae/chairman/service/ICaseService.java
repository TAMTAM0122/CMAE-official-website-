package com.cmae.chairman.service;

import com.cmae.chairman.entity.Case;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TAMTAM
 * @since 2024-09-03
 */
public interface ICaseService extends IService<Case> {
    public void addCase(Case caseData);

    public void updateCase(Case caseData);

    public Case getCase(int id);
}
