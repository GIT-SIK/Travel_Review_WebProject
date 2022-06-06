package com.travel.etc;

import java.time.LocalDate;
import java.time.ZoneId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Today {
    private String today = String.valueOf(LocalDate.now(ZoneId.of("Asia/Seoul")));
}
