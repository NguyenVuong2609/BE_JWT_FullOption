package com.example.demo.service.song;

import com.example.demo.model.Song;
import com.example.demo.service.IGenericService;


public interface ISongService extends IGenericService<Song> {
    Iterable<Song> findByUserId(Long id);
}
