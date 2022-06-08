package com.travel.festival;

import com.travel.domain.Festival;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;

    public List<Festival> festivalData(LocalDate month) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String date = formatter.format(month);
        List<Festival> result = festivalRepository.findByDate(date);
        return result;
    }
}
