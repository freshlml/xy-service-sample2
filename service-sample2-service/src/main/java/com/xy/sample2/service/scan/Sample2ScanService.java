package com.xy.sample2.service.scan;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xy.sample2.api.bo.Sample2ScanBo;
import com.xy.sample2.api.bo.Sample2ScanPageBo;
import com.xy.sample2.entity.scan.Sample2Scan;

public interface Sample2ScanService extends IService<Sample2Scan> {

    IPage<Sample2ScanBo> listByPojo(Sample2ScanPageBo scanPageBo);
}
