package com.example.RestApiTraining;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/get")
    public Map<Integer, String> getName() {

        return Customer;
    }

    @PostMapping("/post")
    public Map<Integer, String> postName(@RequestParam("post")String param1) {
        Customer.put(Customer.size()+1, param1);

        return Customer;
    }

    @PostMapping("/post2")
    public ResponseEntity<String> create(@RequestBody CreateForm form) {
        int newId = Customer.size()+1;
        Customer.put(newId, form.getName());

        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/post2/" + newId)
                .build()
                .toUri();
        return ResponseEntity.created(url).body("name successfully created");
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Map<String, String>> patch(@PathVariable("id") int patchId, @RequestBody PatchForm form) {
        Customer.put(patchId, form.getName());

        return ResponseEntity.ok(Map.of("message", "name successfully updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int deleteId) {
        Customer.remove(deleteId);
        return new ResponseEntity<>("name successfully created", HttpStatus.OK);
    }
}
