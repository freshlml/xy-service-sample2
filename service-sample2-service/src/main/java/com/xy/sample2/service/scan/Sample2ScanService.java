package com.xy.sample2.service.scan;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sc.common.bo.scan.Sample2ScanBo;
import com.sc.common.bo.scan.Sample2ScanPageBo;
import com.xy.sample2.entity.scan.Sample2Scan;

public interface Sample2ScanService extends IService<Sample2Scan> {

    IPage<Sample2ScanBo> listByPojo(Sample2ScanPageBo scanPageBo);
}
