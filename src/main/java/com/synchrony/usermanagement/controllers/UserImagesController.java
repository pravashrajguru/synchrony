package com.synchrony.usermanagement.controllers;

import com.synchrony.usermanagement.models.ImgurResponse;
import com.synchrony.usermanagement.models.UserImagesDto;
import com.synchrony.usermanagement.service.UserImagesService;
import com.synchrony.usermanagement.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/userImages", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserImagesController {

    @Autowired
    UserImagesService userImagesService;

    @GetMapping("/{id}")
    public ResponseEntity<ImgurResponse> getUserImages(@PathVariable final String id) throws IOException, InterruptedException {
        return ResponseEntity.ok(userImagesService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createUserImages(
            @RequestBody final UserImagesDto userImagesDTO) throws Exception {
        final Long createdId = userImagesService.create(userImagesDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }
    @DeleteMapping("/{deleteHash}")
    public ResponseEntity<Void> deleteUserImages(@PathVariable final String deleteHash) throws IOException, InterruptedException, NotFoundException {
        userImagesService.delete(deleteHash);
        return ResponseEntity.noContent().build();
    }

}
