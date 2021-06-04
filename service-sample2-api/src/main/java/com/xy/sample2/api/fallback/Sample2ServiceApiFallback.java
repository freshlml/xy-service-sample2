package com.xy.sample2.api.fallback;

import com.sc.common.enums.ScanTypeEnum;
import com.sc.common.vo.JsonResult;
import com.sc.common.vo.PageJsonResultVo;
import com.xy.sample2.api.Sample2ServiceApi;
import com.xy.sample2.api.bo.Sample2ScanAddBo;
import com.xy.sample2.api.bo.Sample2ScanBo;
import com.xy.sample2.api.bo.Sample2ScanPageBo;

import java.time.LocalDateTime;
import java.util.List;

/*@Component
@Slf4j*/
public class Sample2ServiceApiFallback implements Sample2ServiceApi {

    @Override
    public JsonResult<Sample2ScanBo> getById(Long id) {
        //log.error("rpc调用失败id={}", id);
        return JsonResult.buildFailedResult("getById调用失败,Sample2Service");
    }

    @Override
    public JsonResult<List<Sample2ScanBo>> getByIds(List<Long> ids) {
        return JsonResult.buildFailedResult("getByIds调用失败,Sample2Service");
    }

    @Override
    public JsonResult<PageJsonResultVo<Sample2ScanBo>> listByPojo(Sample2ScanPageBo scanPageBo) {
        return JsonResult.buildFailedResult("listByPojo调用失败,Sample2Service");
    }

    @Override
    public JsonResult<PageJsonResultVo<Sample2ScanBo>> listByPojo2(Sample2ScanPageBo scanPageBo) {
        return JsonResult.buildFailedResult("listByPojo2调用失败,Sample2Service");
    }

    @Override
    public JsonResult<PageJsonResultVo<Sample2ScanBo>> listByParams(Long id, ScanTypeEnum scanType, LocalDateTime scanTime, Long page, Long pageSize) {
        return JsonResult.buildFailedResult("listByParams调用失败,Sample2Service");
    }

    @Override
    public JsonResult save(Sample2ScanAddBo scanAddBo) {
        return JsonResult.buildFailedResult("save调用失败,Sample2Service");
    }

}
