package org.openpaas.paasta.portal.web.admin.service;


import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.model.CommonCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class CommonCodeService {

    @Autowired
    public CommonService commonService;


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param param CommonCode
     * @return Map(자바클래스)
     */
    public Map<String,Object> getCodeDetailList(CommonCode param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        if (param.getGroupId() != null) {
            search = "?";
            search += "groupId=" + param.getGroupId();
        }
        return commonService.procApiRestTemplate(Constants.V2_URL + "/codedetail/" + search, HttpMethod.GET, param, Constants.COMMON_API, Map.class).getBody();
    }


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param no CommonCode
     * @return Map(자바클래스)
     */
    public Map<String,Object> getCodeDetail(int no) {
        return commonService.procApiRestTemplate(Constants.V2_URL + "/codedetail/" + no , HttpMethod.GET, null, Constants.COMMON_API, Map.class).getBody();
    }


    /**
     * 공통그룹 목록을 조회한다.
     *
     * @param param CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    public Map<String,Object> getGroupDetailList(CommonCode param) {
        String search = "";

        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procApiRestTemplate(Constants.V2_URL + "/codegroup" + search, HttpMethod.GET, param, Constants.COMMON_API, Map.class).getBody();
    }



    /**
     * 공통그룹 목록을 조회한다.
     *
     * @param param CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    public Map<String,Object> getGroupDetail(String id, CommonCode param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procApiRestTemplate(Constants.V2_URL + "/codegroup/" + id + search, HttpMethod.GET, param, Constants.COMMON_API, Map.class).getBody();
    }


    /**
     * 공통코드 및 그룹 목록을 조회한다.
     *
     * @param param odeDetail,CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> getCommonCodeJoinGroup(CommonCode param) {
        String search = "";
        if (param.getSearchKeyword() != null) {
            search = "?";
            search += "searchKeyword=" + param.getSearchKeyword();
        }
        return commonService.procApiRestTemplate(Constants.V2_URL + "/commoncode/" + search, HttpMethod.GET, param, Constants.COMMON_API, Map.class).getBody();
    }


    /**
     * 공통 코드 그룹을 등록한다.
     *
     * @param param CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> insertDetailGroup(CommonCode param) {
        param.setUserId(commonService.getUserId());
        return commonService.procApiRestTemplate(Constants.V2_URL + "/codegroup", HttpMethod.POST, param, Constants.COMMON_API, Map.class).getBody();
    }

    /**
     * 공통 코드을 등록한다.
     *
     * @param param CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> insertDetail(CommonCode param) {
        param.setUserId(commonService.getUserId());
        return commonService.procApiRestTemplate(Constants.V2_URL + "/codedetail", HttpMethod.POST, param, Constants.COMMON_API, Map.class).getBody();
    }


    /**
     * 공통 코드 그룹을 수정한다.
     *
     * @param param CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> updateCommonGroup(String id, CommonCode param) {
        param.setUserId(commonService.getUserId());
        return commonService.procApiRestTemplate(Constants.V2_URL + "/codegroup/" + id, HttpMethod.PUT, param, Constants.COMMON_API, Map.class).getBody();
    }


    /**
     * 공통 코드을 수정한다.
     *
     * @param param CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> updateCommonDetail(int no, CommonCode param) {
        param.setUserId(commonService.getUserId());
        return commonService.procApiRestTemplate(Constants.V2_URL + "/codedetail/" + no, HttpMethod.PUT, param, Constants.COMMON_API, Map.class).getBody();
    }


    /**
     * 공통 코드 그룹을 삭제한다.
     *
     * @param id
     * * @return Map(자바클래스)
     */
    public Map<String,Object> deleteCommonGroup(String id) {
        return commonService.procApiRestTemplate(Constants.V2_URL + "/codegroup/" + id, HttpMethod.DELETE, null, Constants.COMMON_API, Map.class).getBody();
    }


    /**
     * 공통 코드을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String,Object> deleteCommonDetail(int no) {
        return commonService.procApiRestTemplate(Constants.V2_URL + "/codedetail/" + no, HttpMethod.DELETE, null, Constants.COMMON_API, Map.class).getBody();
    }

}
