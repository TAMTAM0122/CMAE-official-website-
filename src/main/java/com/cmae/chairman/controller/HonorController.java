package com.cmae.chairman.controller;

import com.cmae.chairman.entity.Case;
import com.cmae.chairman.entity.Honor;
import com.cmae.chairman.service.IHonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@RequestMapping("/Honor")
public class HonorController {
    @Autowired
    private IHonorService honorService;

    @GetMapping("/GetHonorAll")
    public List<Honor> listHonor() {
        return honorService.list();
    }

    @PostMapping("/CreateHonor")
    public ResponseEntity<String> createHonor(@RequestBody Honor honor) {
        honorService.addHonor(honor);
        return ResponseEntity.ok("创建成功");
    }

    @PostMapping("/DeleteHonor")
    public ResponseEntity<String> deleteHonor(@RequestParam int id) {
        boolean result = honorService.removeById(id);
        if (result) {
            return ResponseEntity.ok("删除成功！");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("荣誉图片未找到！");
        }
    }

    @PostMapping("/ModifiedHonor")
    public ResponseEntity<String> modifiedHonor(@RequestBody Honor honor) {
        honorService.updateHonor(honor);
        return ResponseEntity.ok("修改成功");
    }
}
