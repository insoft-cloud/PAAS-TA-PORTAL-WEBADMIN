package org.openpaas.paasta.portal.web.admin.service;

import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by MIN on 2018-04-04.
 */
@Service
public class AuthorityManagementService {

    @Autowired
    CommonService commonService;

    /**
     * Gets org space list main.
     *
     * @return the org space list main
     */
    public ModelAndView getOrgSpaceListMain() {
        return new ModelAndView() {{
            setViewName("/authority/authorityMain");
        }};
    }

    /**
     * Gets authority groups.
     *
     * @return the authority groups
     * @throws Exception the exception
     */
    public Map<String, Object> getAuthorityGroups(String reqUrl, HttpMethod httpMethod, String param) throws Exception {
        return commonService.procApiRestTemplate(Constants.V3_URL + reqUrl, httpMethod, param, Constants.CF_API, Map.class).getBody();
    }

    /**
     * Gets uaa user info.
     *
     * @param param the param
     * @return the uaa user info
     * @throws Exception the exception
     */
    public Map<String, Object> getUaaUserInfo(String reqUrl, HttpMethod httpMethod, Map param) throws Exception {
        return commonService.procApiRestTemplate(Constants.V3_URL + reqUrl, httpMethod, param, Constants.CF_API, Map.class).getBody();
    }

    /**
     * Create authority group map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Object> createAuthorityGroup(String reqUrl, HttpMethod httpMethod, Map param) throws Exception {
        return commonService.procApiRestTemplate(Constants.V3_URL + reqUrl, httpMethod, param, Constants.CF_API, Map.class).getBody();
    }

    /**
     * Delete authority group map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Object> deleteAuthorityGroup(String reqUrl, HttpMethod httpMethod, Map param) throws Exception {
        return commonService.procApiRestTemplate(Constants.V3_URL + reqUrl, httpMethod, param, Constants.CF_API, Map.class).getBody();
    }

    /**
     * Add group members map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Object> addGroupMembers(String reqUrl, HttpMethod httpMethod, Map param) throws Exception {
        return commonService.procApiRestTemplate(Constants.V3_URL + reqUrl, httpMethod, param, Constants.CF_API, Map.class).getBody();
    }

    /**
     * Delete group members map.
     *
     * @param param the param
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Object> deleteGroupMembers(String reqUrl, HttpMethod httpMethod, Map param) throws Exception {
        return commonService.procApiRestTemplate(Constants.V3_URL + reqUrl, httpMethod, param, Constants.CF_API, Map.class).getBody();
    }

    /**
     * Gets user name list.
     *
     * @param param the param
     * @return the user name list
     * @throws Exception the exception
     */
    public Map<String, Object> getUserNameList(String reqUrl, HttpMethod httpMethod, Map param) throws Exception {
        return commonService.procApiRestTemplate(Constants.V2_URL + reqUrl, httpMethod, param, Constants.COMMON_API, Map.class).getBody();
    }

}
