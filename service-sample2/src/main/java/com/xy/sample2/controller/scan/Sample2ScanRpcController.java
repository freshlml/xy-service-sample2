package com.xy.sample2.controller.scan;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.sc.common.bo.scan.Sample2ScanAddBo;
import com.sc.common.bo.scan.Sample2ScanBo;
import com.sc.common.bo.scan.Sample2ScanPageBo;
import com.sc.common.enums.JsonResultEnum;
import com.sc.common.enums.ScanTypeEnum;
import com.sc.common.utils.AssertUtils;
import com.sc.common.vo.JsonResult;
import com.xy.sample2.entity.scan.Sample2Scan;
import com.xy.sample2.service.scan.Sample2ScanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对外提供Rpc服务
 */
@Slf4j
@RestController
@RequestMapping("/sample2ScanRpc/")
public class Sample2ScanRpcController {

    @Autowired
    private Sample2ScanService sample2ScanService;

    @GetMapping("getById")
    public JsonResult getById(Long id) {
        //AssertUtils.ifNull(id, () -> "id不能为空", () -> JsonResultEnum.FAIL.getCode());
        Sample2Scan scan = sample2ScanService.getById(id);
        Sample2ScanBo scanBo = null;
        if(scan != null) {
            scanBo = Sample2ScanBo.builder().id(scan.getId()).name(scan.getName()).scanType(scan.getScanType())
                        .scanTime(scan.getScanTime())
                        .createTime(scan.getCreateTime())
                        .modifyTime(scan.getModifyTime())
                        .build();
        }
        return JsonResult.buildSuccessResult(scanBo);
    }

    @GetMapping("getByIds")
    public JsonResult getByIds(@RequestParam(name = "ids", required = false) List<Long> ids) {
        List<Sample2ScanBo> result = Lists.newArrayList();
        if(ids != null && !ids.isEmpty()) {
            List<Sample2Scan> scanResult = sample2ScanService.list(new QueryWrapper<Sample2Scan>().lambda().select(Sample2Scan::getId, Sample2Scan::getName, Sample2Scan::getScanTime, Sample2Scan::getScanType).in(Sample2Scan::getId, ids));
            result = scanResult.stream().map(scan -> Sample2ScanBo.builder().id(scan.getId()).name(scan.getName()).scanType(scan.getScanType()).scanTime(scan.getScanTime()).build()).collect(Collectors.toList());
        }
        return JsonResult.buildSuccessResult(result);
    }

    //Get请求的Enum中@JsonCreator
    @GetMapping("listByPojo")
    public JsonResult listByPojo(Sample2ScanPageBo scanPageBo) {
        AssertUtils.ifNull(scanPageBo, () -> "查询参数不能为空", () -> JsonResultEnum.FAIL.getCode());
        AssertUtils.ifTrue(scanPageBo.getPageSize()<0, () -> "pageSize不能为负数", () -> JsonResultEnum.FAIL.getCode());
        log.info("请求参数: {}", scanPageBo);

        IPage<Sample2ScanBo> result = sample2ScanService.listByPojo(scanPageBo);
        return JsonResult.buildSuccessResult(result);
    }

    //compare with listByPojo, Get请求将参数放在请求体，使用@RequestBody解析请求体参数
    @GetMapping("listByPojo2")
    public JsonResult listByPojo2(@RequestBody Sample2ScanPageBo scanPageBo) {
        AssertUtils.ifNull(scanPageBo, () -> "查询参数不能为空", () -> JsonResultEnum.FAIL.getCode());
        AssertUtils.ifTrue(scanPageBo.getPageSize()<0, () -> "pageSize不能为负数", () -> JsonResultEnum.FAIL.getCode());

        IPage<Sample2ScanBo> result = sample2ScanService.listByPojo(scanPageBo);
        return JsonResult.buildSuccessResult(result);
    }

    @GetMapping("listByParams")
    public JsonResult listByParams(@RequestParam(name = "id", required = false) Long id,
                                   @RequestParam(name = "scanType", required = false) ScanTypeEnum scanType,
                                   @RequestParam(name = "scanTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime scanTime,
                                   @RequestParam(name = "page", required = false) Long page,
                                   @RequestParam(name = "pageSize", required = false) Long pageSize) {

        Sample2ScanPageBo scanPageBo = Sample2ScanPageBo.builder().id(id).scanTime(scanTime).scanType(scanType).build();
        scanPageBo.setPage(page);
        scanPageBo.setPageSize(pageSize);

        IPage<Sample2ScanBo> result = sample2ScanService.listByPojo(scanPageBo);
        return JsonResult.buildSuccessResult(result);
    }

    @PostMapping("save")
    public JsonResult save(@RequestBody @Valid Sample2ScanAddBo scanAddBo) {
        Sample2Scan scan = Sample2Scan.builder().name(scanAddBo.getName()).scanType(scanAddBo.getScanType()).scanTime(scanAddBo.getScanTime()).build();
        sample2ScanService.save(scan);
        return JsonResult.buildSuccessResult("保存成功");
    }

}
