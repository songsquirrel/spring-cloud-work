package com.song.basicx;

import com.song.basicx.config.exception.BizException;
import com.song.basicx.config.lock.annotation.DistributedLock;
import com.song.basicx.enums.ResultCodeEnum;
import com.song.basicx.model.respond.ApiResult;
import com.song.basicx.utils.ApiResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/song")
public class TestController {

    @GetMapping("test")
//    @DistributedLock(key = "LockKey")
    public ApiResult<String> test() throws InterruptedException {
        log.info("==========");
        throw new BizException(ResultCodeEnum.FAILED);
//        Thread.sleep(30000);
//        return ApiResultUtil.success();
    }
}
