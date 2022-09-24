package com.example.RestApiTraining;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/controller")
public class Controller {
    Map<Integer, String> Customer = new HashMap<>() {{
        put(1, "田中");
        put(2, "斎藤");
        put(3, "橋本");
        put(4, "鈴木");
        put(5, "安藤");
    }};

    @GetMapping("/get")
    public Map<Integer, String> getName() {

        return Customer;
    }

    @PostMapping("/post")
    public Map<Integer, String> postName(@RequestParam("post")String param1) {
        Customer.put(Customer.size()+1, param1);

        return Customer;
    }
}
