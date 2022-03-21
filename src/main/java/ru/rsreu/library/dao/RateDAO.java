package ru.rsreu.library.dao;

import ru.rsreu.library.model.books.Rate;

import java.util.ArrayList;
import java.util.Map;

public interface RateDAO {
    ArrayList<Rate> findAll();

    Map<String, Float> findAvgRates();

    void deleteRate(int id);
}
