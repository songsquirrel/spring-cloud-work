package com.song.basicx;

import com.song.basicx.config.lock.annotation.DistributedLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("test")
    @DistributedLock(key = "LockKey")
    public String test() throws InterruptedException {
        Thread.sleep(8000);
        return "test";
    }
}
