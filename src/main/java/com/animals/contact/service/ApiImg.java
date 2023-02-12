package com.animals.contact.service;

import com.animals.contact.entityApi.Img;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiImg {
    private final RestTemplate restTemplate;

    public ApiImg(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Img getData() {
        return restTemplate.getForObject("https://fakeface.rest/face/json", Img.class);
    }
}
