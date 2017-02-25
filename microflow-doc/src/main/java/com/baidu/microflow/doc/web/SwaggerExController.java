package com.baidu.microflow.doc.web;

import com.baidu.microflow.doc.doc_hidden.AccessHiddenManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/swaggerEx")
@RestController
public class SwaggerExController {

    @RequestMapping(value = "hiddenMaps", method = RequestMethod.GET)
    public Map<String, List<String>> hiddenMaps() {
        return AccessHiddenManager.docViewAttrMapByApi;
    }
}
