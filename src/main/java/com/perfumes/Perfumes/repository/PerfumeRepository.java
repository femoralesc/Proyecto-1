package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Perfume;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PerfumeRepository {

    private List<Perfume> perfumes = new ArrayList<>();

    public List<Perfume> getPerfumes() {}

}
