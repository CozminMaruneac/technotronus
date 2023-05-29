package com.usv.technotronus.features.domain;

import com.usv.technotronus.features.domain.dto.CreateDomainDto;
import com.usv.technotronus.features.domain.dto.DomainDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domains")
@RequiredArgsConstructor
public class DomainController {

    private final DomainService domainService;

    @GetMapping
    public List<DomainDto> getAllDomains() {
        return domainService.getAllDomains();
    }

    @GetMapping("/{id}")
    public DomainDto getDomainById(@PathVariable Integer id) {
        return domainService.getDomainById(id);
    }

    @PostMapping
    public ResponseEntity<DomainDto> createDomain(@RequestBody CreateDomainDto createDomainDto) {
        DomainDto createdDomain = domainService.createDomain(createDomainDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdDomain);
    }

    @PutMapping("/{id}")
    public DomainDto updateDomain(@PathVariable Integer id, @RequestBody DomainDto updateDomainDto) {
        return domainService.updateDomain(id, updateDomainDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDomain(@PathVariable Integer id) {
        domainService.deleteDomain(id);
    }
}
