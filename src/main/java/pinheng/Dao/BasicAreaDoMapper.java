package pinheng.Dao;

import pinheng.Do.BasicAreaDo;

import java.util.List;
import java.util.Map;

public interface BasicAreaDoMapper {

    BasicAreaDo getBasicAreaDoById(String areaId);

    List<Map<String,Object>> getNextArea(Integer areaId);

    Map<String,Object> getOneArea(String oneId);

    Map<String, Object> getThreeArea(String threeId);

    List<Map<String, Object>> getthreeArea(int twoareaId);

    List<BasicAreaDo> getArea(Map<String,Object> paramMap);

    String queryAreaName(Integer areaId);


}
