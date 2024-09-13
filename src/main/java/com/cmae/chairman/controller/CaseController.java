package com.cmae.chairman.controller;

import com.cmae.chairman.entity.Case;
import com.cmae.chairman.service.ICaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author TAMTAM
 * @since 2024-09-03
 */
@RestController
@RequestMapping("/Case")
public class CaseController {
    @Autowired
    private ICaseService caseService;

    @GetMapping("/GetCasesById/{id}")
    public Case getCasesById(@PathVariable("id") Integer id){
        return caseService.getCase(id);
    }

    @GetMapping("/GetListAll")
    public List<Case> listCases() {
        return caseService.list();
    }

    //添加案例接口
    @PostMapping("/CreateCases")
    public ResponseEntity<String> createCases(@RequestBody Case caseData) {
        caseService.addCase(caseData);
        return ResponseEntity.ok("创建成功");
    }

    // 删除案例接口
    @PostMapping("/DeleteCases")
    public ResponseEntity<String> deleteCaseById(@RequestParam("id") Integer id) {
        // 检查是否成功删除
        boolean result = caseService.removeById(id);
        if (result) {
            return ResponseEntity.ok("删除成功！");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("案例未找到！");
        }
    }

    @PostMapping("/ModifiedCases")
    public ResponseEntity<String> modifiedCases(@RequestBody Case caseData) {
        caseService.updateCase(caseData);
        return ResponseEntity.ok("修改成功");
    }


}
