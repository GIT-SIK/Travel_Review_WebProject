package com.travel.index;

import com.travel.domain.Festival;
import com.travel.domain.idxSlide;
import com.travel.domain.idxView;
import com.travel.festival.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndexService {

    @Autowired
    IndexViewRepository indexViewRepository;

    @Autowired
    IndexSlideRepository indexSlideRepository;

    @Autowired
    FestivalRepository festivalRepository;


    public List<idxSlide> findAllSlide() {
        return indexSlideRepository.findAllSlide();
    }

    public List<idxView> findAllView(){
        return indexViewRepository.findAllView();
    }
    /* index 페이지 / 지도와 축제 부분 코드*/

    /* 메인에 년 월 가져오는 함수 */
    public String findDate(){
        Date dateTemp = indexViewRepository.findAllView().get(0).getFestivalDate();;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String date = dateFormat.format(dateTemp);
        return date;
    }

    /* 메인에 지도에 축제를 가져오는 함수 */
    public List<Festival> findAllbyDataLocal(String local) {

        /* 날짜 변환 */
        List<idxView> index = indexViewRepository.findAllView();
        Date dateTemp = index.get(0).getFestivalDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String date = dateFormat.format(dateTemp);

        /* 로컬 변환 */
        String localTemp = local.trim();
        if(localTemp.equals("충남"))
            localTemp = "충청남도";

        if(localTemp.equals("충북"))
            localTemp = "충청북도";

        if(localTemp.equals("경북"))
            localTemp = "경상북도";

        if(localTemp.equals("경남"))
            localTemp = "경상남도";

        if(localTemp.equals("전북"))
            localTemp = "전라북도";

        if(localTemp.equals("전남"))
            localTemp = "전라남도";

        List<Festival> indexFestivalList = festivalRepository.findALLByDateLocal(date, localTemp);

        return indexFestivalList;
    }

}
