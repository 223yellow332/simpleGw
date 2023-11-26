package com.calmdown.simpleGw.controller;

import com.calmdown.simpleGw.domain.MobileCarrier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EnumViewController {

    @GetMapping("/docs")
    public Docs findAll() {
        Map<String, String> mobileCarrier = getDocs(MobileCarrier.values());

        return Docs.builder()
                .mobileCarrier(mobileCarrier)
                .build();
    }

    private Map<String, String> getDocs(Enum[] enums) {
        return Arrays.stream(enums)
                .collect(Collectors.toMap(Enum::name, Enum::toString));
    }
}
