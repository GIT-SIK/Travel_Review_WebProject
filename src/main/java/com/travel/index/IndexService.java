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
import java.util.Calendar;
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


    /* 관리자페이지 -> 메인페이지 슬라이드 추가 & 삭제 / 슬라이드 타이틀 / 축제 출력 기본 날짜 변경 */
    /* 2차 인증을 통하여 관리자인지 확인하여 실행하도록 코드 작성하였음. */
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


    public boolean updateIndexFestivalDate(String role, String festivalMonth){
        if(role.equals("ROLE_ADMIN"))
        {
            int month = Integer.parseInt(festivalMonth);
            if(month==0){
                /* 자동으로 날짜에 맞춰서 설정한다. */
                indexViewRepository.updateIndexFestivalMonthAuto();
            } else {
                /* 선택된 월로 날짜에 맞춰서 설정한다. */
                /* 날짜 형태로 포멧하여 String으로 UPDATE 함수호출 */
                Calendar cal = Calendar.getInstance();
                cal.set(cal.get(Calendar.YEAR), month-1, 1);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                String dateTemp = sdf.format(cal.getTime());

                indexViewRepository.updateIndexFestivalMonth(dateTemp);
            }
            return true;
        } else {
            return false;
        }
    }
}
