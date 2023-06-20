package com.example.demo.controller;

import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Singer;
import com.example.demo.service.singer.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private ISingerService singerService;
    @GetMapping("/page")
    public ResponseEntity<?> pageSinger(Pageable pageable){
        return new ResponseEntity<>(singerService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> pageSinger(){
        return new ResponseEntity<>(singerService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createSinger(@Valid @RequestBody Singer singer){
        singerService.save(singer);
        return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.CREATED);
    }
}
