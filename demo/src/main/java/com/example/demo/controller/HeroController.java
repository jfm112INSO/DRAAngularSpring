package com.example.demo.controller;

import com.example.demo.dto.HeroDTO;
import com.example.demo.entity.Hero;
import com.example.demo.mapper.HeroMapper;
import com.example.demo.repository.HeroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;

    public HeroController(HeroRepository heroRepository, HeroMapper heroMapper) {
        this.heroRepository = heroRepository;
        this.heroMapper = heroMapper;
    }

    @GetMapping
    public List<HeroDTO> getAllHeroes() {
        return heroRepository.findAll().stream()
                .map(heroMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public HeroDTO createHero(@RequestBody HeroDTO heroDTO) {
        Hero hero = heroMapper.toEntity(heroDTO);
        Hero savedHero = heroRepository.save(hero);
        return heroMapper.toDto(savedHero);
    }
}
