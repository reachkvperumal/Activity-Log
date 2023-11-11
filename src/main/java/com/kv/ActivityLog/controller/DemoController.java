package com.kv.ActivityLog.controller;

import com.kv.ActivityLog.dto.Person;
import com.kv.ActivityLog.dto.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class DemoController {


    @GetMapping("/time")
    public String time(@RequestParam(defaultValue = "EST") Optional<String> zoneId) {
        String current = zoneId.get();
        String id = Optional.ofNullable(ZoneId.SHORT_IDS.get(current.toUpperCase())).orElseGet(() -> "-05:00");
        log.info("Passed TimeZone: {} - Looked up TimeZone: {}", current, id);
        UserDO userDO = new UserDO("KV", "Perumal", 36);
        log.info("Records Demo: {}", userDO);
        return LocalDateTime.now(ZoneId.of(id)).toString();
    }

    @PostMapping("/log")
    public String log(@RequestBody Person person) throws Exception {
        log.info("Processing...");
        Thread.sleep(5000);
        // Thread.currentThread().interrupt();
        log.info("completed...");
        if (Thread.interrupted()) {
            throw new InterruptedException("THREAD INTERRUPTED");
        }
        return "SUCCESS!";
    }
}
