package org.example.springbootpractice2024.service;

import org.example.springbootpractice2024.dao.stockinrecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockinServiceImpl implements StockinService {

    @Autowired
    private stockinrecordsRepository repo;
}
