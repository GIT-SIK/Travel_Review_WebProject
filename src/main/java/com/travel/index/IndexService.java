package com.travel.index;

import com.travel.domain.Festival;
import com.travel.domain.IdxSlide;
import com.travel.domain.IdxView;
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


    public List<IdxSlide> findAllSlide() {
        return indexSlideRepository.findAllSlide();
    }

    public List<IdxView> findAllView(){
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
        List<IdxView> index = indexViewRepository.findAllView();
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


    /* 관리자페이지에서 메인페이지에 있는 슬라이드 추가 & 삭제할 수 있는 로직*/
    public boolean deleteSlide(String role ,String idx) {

        if(role.equals("ROLE_ADMIN"))
        {
            indexSlideRepository.deleteById(Integer.parseInt(idx));
            return true;
        } else {
            return false;
        }
    }

    public boolean addSlide(String role, String slideLink, String slideTitle, String slideCentent, String slidePosition){

        if(role.equals("ROLE_ADMIN"))
        {
            IdxSlide idxSlide = new IdxSlide();
            idxSlide.setSlideLink(slideLink);
            idxSlide.setSlideTitle(slideTitle);
            idxSlide.setSlideContent(slideCentent);
            idxSlide.setSlidePosition(slidePosition);
            indexSlideRepository.save(idxSlide);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateSlideTitle(String role, String slideTitleData){
        if(role.equals("ROLE_ADMIN"))
        {
            indexViewRepository.updateSlideTitle(slideTitleData);
            return true;
        } else {
            return false;
        }
    }

}
