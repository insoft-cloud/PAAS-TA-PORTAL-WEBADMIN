package org.openpaas.paasta.portal.web.admin.controller;


import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.common.UserList;
import org.openpaas.paasta.portal.web.admin.model.UserManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;;
import java.util.List;
import java.util.Map;


/**
 * 사용자 목록, 사용자 삭제 및 운영자 권한 부여 등의 API 를 호출 하는 컨트롤러이다.
 *
 * @version 1.0
 * @since 2016.08.31 최초작성
 */
@RestController
public class UserManagementController extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

    private final String V2_URL = "/v2";

    /**
     * 사용자 정보 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/usermgnts"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getUserInfoMain() {
        return userManagementService.getUserInfoMain();
    }


    /**
     * 사용자 정보 목록을 조회한다.
     *
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/usermgnts/{filter}/user")
    @ResponseBody
    public Map<String, Object> getUserInfoList(@PathVariable String filter, HttpServletRequest request, @ModelAttribute UserManagement param) {
        String key = request.getParameter("key");
        return userManagementService.getUserInfoList(Integer.parseInt(key), "/usermgnts/" + filter + "/user?searchKeyword=" + param.getSearchKeyword(), HttpMethod.GET, param);
    }


    @PostMapping(V2_URL + "/usermgnts/{userid}")
    @ResponseBody
    public Map<String, Object> getUserInfoList(@PathVariable String userid, HttpServletRequest request) {
        String key = request.getParameter("key");
        return userManagementService.getUserInfoList(Integer.parseInt(key), "/usermgnts/" + userid, HttpMethod.POST, null);
    }


    /**
     * 비밀번호를 초기화한다.
     *
     * @param map user id
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL + "/usermgnts/password/email")
    @ResponseBody
    public Map<String, Object> setResetPassword(HttpServletRequest request, @RequestBody Map map) {
        String key = request.getParameter("key");
        return userManagementService.setResetPassword(Integer.parseInt(key), "/users/password/email", HttpMethod.POST, map);
    }


    /**
     * 초기 비밀번호를 모를 경우 새 비밀번호로 초기화한다.
     *
     * @param request
     * @param map
     * @return
     */
    @PostMapping(V2_URL + "/usermgnts/password")
    @ResponseBody
    public Map<String, Object> setInitPassword(HttpServletRequest request, @RequestBody Map map) {
        String key = request.getParameter("key");
        return userManagementService.setInitPassword(Integer.parseInt(key), "/users/password/reset", HttpMethod.POST, map);
    }

    /**
     * 운영권한을 수정한다.
     *
     * @param userid user id
     * @param param  model UserManagement
     * @return Map(자바클래스)
     */
    @PutMapping(V2_URL + "/usermgnts/{userid}/authority")
    @ResponseBody
    public Map<String, Object> updateOperatingAuthority(@PathVariable String userid, HttpServletRequest request, @RequestBody UserManagement param) throws UnsupportedEncodingException {
        String key = request.getParameter("key");
        return userManagementService.updateOperatingAuthority(Integer.parseInt(key), "/usermgnts/" + userid + "/authority", HttpMethod.PUT, param);
    }


    /**
     * 사용자 계정을 삭제한다.
     *
     * @param guid user Guid
     * @return Map(자바클래스)
     */
    @DeleteMapping(V2_URL + "/usermgnts/{guid}")
    @ResponseBody
    public Map<String, Object> deleteUserAccount(@PathVariable String guid, HttpServletRequest request) {
        String key = request.getParameter("key");
        return userManagementService.deleteUserAccount(Integer.parseInt(key), guid, HttpMethod.DELETE, null);
    }


    /**
     * 사용자 계정을 등록한다.
     *
     * @param param Info
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL + "/usermgnts/user")
    @ResponseBody
    public Map<String, Object> addUser(HttpServletRequest request, @RequestBody Map param) {
        String key = request.getParameter("key");
        return userManagementService.addUser(Integer.parseInt(key), HttpMethod.POST, param);
    }


    /**
     * 유저 이름으로 조직에 연결
     *
     * @param guid
     * @param request
     * @return
     */
    @PutMapping(V2_URL + "/organizations/{guid}/users")
    @ResponseBody
    public Map<String, Object> associateUserOrg(@PathVariable String guid, HttpServletRequest request, @RequestBody String param) throws Exception {
        String key = request.getParameter("key");
        return userManagementService.associateUserOrg(Integer.parseInt(key), "/organizations/" + guid + "/users", HttpMethod.PUT, param);
    }

    /**
     * 사용자가 로그인 가능 유무 수정
     *
     * @param guid user Guid
     * @return Map(자바클래스)
     */
    @PutMapping(V2_URL + "/usermgnts/{guid}/active")
    @ResponseBody
    public Map<String, Object> updateUserActive(@PathVariable String guid, HttpServletRequest request, @RequestBody UserManagement param) {
        String key = request.getParameter("key");
        return userManagementService.updateUserActive(Integer.parseInt(key), "/usermgnts/" + guid + "/active", HttpMethod.PUT, param);
    }


    @ModelAttribute("configs")
    public List<User> configs() {
        return getServerInfos();
    }

    /**
     * 유저 상세정보전체출력
     *
     * @param userid
     * @return
     */
    @GetMapping(V2_URL + "/users/{userid}/summary")
    public Map<String, Object> GetUserSummary(@PathVariable String userid, HttpServletRequest request) {
        String key = request.getParameter("key");
        return userManagementService.GetUserSummary(Integer.parseInt(key), "/users/" + userid + "/summary", HttpMethod.GET);


    }

    /**
     * 유저의 이름과 Guid를 목록으로 가져온다.
     *
     * @return map all user name
     * @throws Exception the exception
     */
    @GetMapping(V2_URL + "/users/{userId}")
    public Map<String, Object> getUser(@PathVariable String userId, HttpServletRequest request) {
        String key = request.getParameter("key");
        return userManagementService.getUser(Integer.parseInt(key), "/users/" + userId, HttpMethod.GET);
    }

    /**
     * 유저의 역할(Role)를 전부조회한다
     *
     * @param orgId
     * @param spaceId
     * @return
     */
    @PostMapping(V2_URL + "/orgs-user/{orgId}/user-roles/{spaceId}")
    public Map<String, Object> getOrgUserRolesForAdmin(HttpServletRequest request, @PathVariable String orgId, @PathVariable String spaceId, @RequestBody UserManagement users) {
        String key = request.getParameter("key");
        return userManagementService.getOrgUserRolesForAdmin(Integer.parseInt(key), "/orgs-user/" + orgId + "/user-roles/" + spaceId, HttpMethod.POST, users);
    }


    /**
     * 사용자 정보를 불러온다. (portaldb)
     * 2021-04-15 Yoona
     *
     * @param userid
     * @return Map
     */
    @GetMapping(V2_URL + "/usermgnts/info/{userid}")
    @ResponseBody
    public Map<String, Object> infoUser(@PathVariable String userid, HttpServletRequest request, @ModelAttribute UserManagement user) {
        String key = request.getParameter("key");
        return commonService.procCommonApiRestTemplate(Integer.parseInt(key), Constants.V2_URL + "/user/" + userid, HttpMethod.GET, user);


    }

    /**
     * 사용자 정보를 수정한다.
     * 2021-04-26 Yoona
     *
     * @param userid
     * @return Map map
     */
    @PutMapping(V2_URL + "/usermgnts/info/{userid}")
    @ResponseBody
    public Map<String, Object> updateUser(@PathVariable String userid, HttpServletRequest request, @RequestBody Map map) throws Exception {
        String key = request.getParameter("key");
        return userManagementService.updateUser(Integer.parseInt(key), "/user" + "/" + userid, HttpMethod.PUT, map);

    }

  /*  *//**
     * uaa 상세 정보를 불러온다(uaa)
     *
     * @param username
     * @return User user
     */
    @GetMapping(V2_URL + "/usermgnts/{username}/uaa")
    @ResponseBody
    public Map<String, Object> getSummary(@PathVariable String username, HttpServletRequest request, @ModelAttribute UserManagement user) {
        String key = request.getParameter("key");
        return commonService.procCommonApiRestTemplate(Integer.parseInt(key), Constants.V2_URL + "/user/"+username+"/uaa", HttpMethod.GET, user);
    }
    /**
     * uaa 상세 정보를 수정한다.
     * 2021-04-26 Yoona
     *
     * @param map
     * @return Map map
     */
    @PutMapping(V2_URL + "/usermgnts/{username}/uaa")
    @ResponseBody
    public Map<String, Object> updateUaa(@PathVariable String username, HttpServletRequest request, @RequestBody Map map) throws Exception {
        String key = request.getParameter("key");
        return userManagementService.updateUser(Integer.parseInt(key), "/user/"+username+"/uaa", HttpMethod.PUT, map);

    }
}
