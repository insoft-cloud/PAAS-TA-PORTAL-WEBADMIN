package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.common.UserList;
import org.openpaas.paasta.portal.web.admin.controller.UserManagementController;
import org.openpaas.paasta.portal.web.admin.model.UserManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MIN on 2018-03-28.
 */
@Service
public class UserManagementService extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    CommonService commonService;

    public ModelAndView getUserInfoMain() {
        return new ModelAndView() {{
            setViewName("/userManagement/userManagementMain");
        }};
    }


    /**
     * 사용자 정보 목록을 조회한다.
     *
     * @return Map(자바클래스)
     */
    public Map<String, Object> getUserInfoList(int key, String reqUrl, HttpMethod httpMethod, Object param) {
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + reqUrl, httpMethod, param);
    }


    /**
     * 비밀번호를 초기화한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> setResetPassword(int key, String reqUrl, HttpMethod httpMethod, Object param) {
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + reqUrl, httpMethod, param);
    }

    /**
     * 초기 비밀번호를 모를 경우 새 비밀번호로 초기화한다.
     *
     * @param key
     * @param reqUrl
     * @param httpMethod
     * @param param
     * @return
     */
    public Map<String, Object> setInitPassword(int key, String reqUrl, HttpMethod httpMethod, Object param) {
        return commonService.procCfApiRestTemplate(key, Constants.V2_URL + reqUrl, httpMethod, param);
    }

    /**
     * 운영권한을 부여한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateOperatingAuthority(int key, String reqUrl, HttpMethod httpMethod, Object param) {
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + reqUrl, httpMethod, param);
    }


    /**
     * 사용자 계정을 삭제한다.
     *
     * @param param UserMana    gement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteUserAccount(int key, String guid, HttpMethod httpMethod, Object param) {
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + "/user/" + guid + "/all", httpMethod, param);
    }

    /**
     * 사용자 계정을 등록한다.
     *
     * @param param Info
     * @return Map(자바클래스)
     */
    public Map<String, Object> addUser(int key, HttpMethod httpMethod, Map param) {
        Map<String, Object> result = commonService.procCfApiRestTemplate(key, Constants.V3_URL + "/users", HttpMethod.POST, param);
        if ((boolean) result.get("result")) {
            param.remove("active");
            param.put("active", param.get("active2").toString());
            commonService.procCommonApiRestTemplate(key, Constants.V2_URL + "/user", httpMethod, param);
        }
        return result;
    }


    /**
     * 유저 이름으로 조직에 연결
     *
     * @param key
     * @param reqUrl
     * @param httpMethod
     * @return
     */
    public Map<String, Object> associateUserOrg(int key, String reqUrl, HttpMethod httpMethod, String param) throws Exception {
        return commonService.procCfApiRestTemplate(key, Constants.V2_URL + reqUrl, httpMethod, param);
    }

    /**
     * 사용자가 로그인 가능 유무 수정
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateUserActive(int key, String reqUrl, HttpMethod httpMethod, UserManagement param) {
        Map<String, Object> result = commonService.procCfApiRestTemplate(key, Constants.V3_URL + "/user/" + param.getUserGuid() + "/active", httpMethod, param);
        param.setActive(result.get("active").toString().equals("true") ? "Y" : "N");
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + reqUrl, httpMethod, param);
    }

    /**
     * 유저 상세정보전체출력
     *
     * @return
     */
    public Map<String, Object> GetUserSummary(int key, String reqUrl, HttpMethod httpMethod) {
        return commonService.procCfApiRestTemplate(key, Constants.V3_URL + reqUrl, httpMethod, null);
    }

    /**
     * 유저의 이름과 Guid를 목록으로 가져온다.
     *
     * @return map all user name
     * @throws Exception the exception
     */
    public Map<String, Object> getUser(int key, String reqUrl, HttpMethod httpMethod) {
        return commonService.procCfApiRestTemplate(key, Constants.V3_URL + reqUrl, httpMethod, null);
    }

    /**
     * 유저의 역할(Role)를 전부조회한다
     *
     * @return
     */
    public Map<String, Object> getOrgUserRolesForAdmin(int key, String reqUrl, HttpMethod httpMethod, UserManagement users) {
        return commonService.procCfApiRestTemplate(key, Constants.V3_URL + reqUrl, httpMethod, users);
    }

    /**
     * 사용자 정보를 수정한다.
     *
     * @param param
     * @return map
     * 2021-04-26 Yoona
     */
    public Map<String, Object> updateUser(int key, String reqUrl, HttpMethod httpMethod, Object param) {
        return commonService.procCommonApiRestTemplate(key, Constants.V2_URL + reqUrl, httpMethod, param);
    }

}

