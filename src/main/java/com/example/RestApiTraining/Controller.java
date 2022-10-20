package com.example.RestApiTraining;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/customers")
    public Map<Integer, String> getName() {

        return Customer;
    }

    @PostMapping("/customers")
    public ResponseEntity<Map<String, String>> create(@RequestBody @Validated CreateForm form, UriComponentsBuilder uriComponentsBuilder) {
        int newId = Customer.size()+1;
        Customer.put(newId, form.getName());

        URI url = uriComponentsBuilder.path("/customers" + newId)
                .build()
                .toUri();
        return ResponseEntity.created(url).body(Map.of("massage","name successfully created"));
    }

    @PatchMapping("/customers/{id}")
    public ResponseEntity<Map<String, String>> patch(@PathVariable("id") int customersId, @RequestBody UpdateForm form) {
        Customer.put(customersId, form.getName());

        return ResponseEntity.ok(Map.of("message", "name successfully updated"));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("id") int customersId) {
        Customer.remove(customersId);
        return ResponseEntity.ok(Map.of("message", "name successfully created"));
    }
}
