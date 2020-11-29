package com.windmillsmartsolutions.controller.v1.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringOperationsController {
    
    @PostMapping(value = "/maxString")    
    public String getLongesString (@RequestBody Map<String, List<String>> request) {        
        Optional<String> longest = ((List<String>)request.get("data")).stream()
            .sorted((e1, e2) -> e1.length() > e2.length() ? -1 : 1)
            .findFirst();

        return longest.get();        
    }

    @DeleteMapping(value = "/deleteString")    
    public List<String> deleteStrings (@RequestBody Map<String, List<String>> request) {        
        List<String> filtered = ((List<String>)request.get("data")).stream().filter(x -> x.length() <= 10).collect(Collectors.toList());
        System.out.println(((List<String>)request.get("data")).stream().map(x -> x + "111111111111").collect(Collectors.toList()));
        return filtered;
    }
}

