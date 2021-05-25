package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.model.ServiceBroker;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class ServicePlansController extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServicePlansController.class);
    @Autowired
    private CommonService commonService;

    /**
     * 서비스 제어 메인 화면이다.
     *
     * @return model and view
     */
    @GetMapping(value = {"/serviceplans"})
    public ModelAndView serviceBroker() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("servicePlan/servicePlansMain");

        return mv;
    }


    /**
     * 카탈로그 서비스 이용사양 목록을 조회한다.
     *
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/catalogs/serviceplan-admin"})
    public Map<String, Object> getCatalogService() throws Exception {
        return commonService.procApiRestTemplate(Constants.V3_URL + "/catalogs/serviceplan-admin", HttpMethod.GET, null, Constants.CF_API, Map.class).getBody();
    }

    /**
     * 서비스 제어 리스트를 조회한다.
     *
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/serviceplans"})
    @ResponseBody
    public Map<String, Object> getServicePlans() {
        return commonService.procApiRestTemplate(Constants.V3_URL + "/serviceplans", HttpMethod.GET, null, Constants.CF_API, Map.class).getBody();
    }

    /**
     * 해당 서비스 제어 상세내용을 조회한다.
     *
     * @param serviceBroker serviceBroker
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/serviceplans/{guid}"})
    @ResponseBody
    public Map<String, Object> getServicePlan(@ModelAttribute ServiceBroker serviceBroker, @PathVariable String guid) {
        return commonService.procApiRestTemplate(Constants.V3_URL + "/serviceplans/" + guid, HttpMethod.GET, serviceBroker, Constants.CF_API, Map.class).getBody();
    }

    /**
     * 해당 서비스 제어 상세내용을 수정한다.
     *
     * @param serviceBroker serviceBroker
     * @return ModelAndView model
     */
    @PutMapping(value = {Constants.V2_URL + "/serviceplans/{guid}"})
    @ResponseBody
    public Map<String, Object> updateServicePlan(@RequestBody ServiceBroker serviceBroker, @PathVariable String guid) {
        return commonService.procApiRestTemplate(Constants.V3_URL + "/serviceplans/" + guid, HttpMethod.PUT, serviceBroker, Constants.CF_API, Map.class).getBody();
    }

    /**
     * 서비스 Plan에 Access 등록 되어있는 조직을 조회한다.
     *
     * @param serviceplanId serviceplanId
     * @return ModelAndView model
     */
    @GetMapping(value = {Constants.V2_URL + "/serviceplans/{serviceplanId}/visibilites"})
    @ResponseBody
    public Map<String, Object> getServicePlanVisibilites(@PathVariable String serviceplanId) {
        return commonService.procApiRestTemplate(Constants.V3_URL + "/serviceplans/" + serviceplanId + "/visibilites", HttpMethod.GET, null, Constants.CF_API, Map.class).getBody();
    }


    /**
     * 서비스 Plan에 Access 등록 되어있는 조직을 추가한다.
     *
     * @param param the param
     * @param guid  guid
     * @return ModelAndView model
     */
    @PutMapping(value = {Constants.V2_URL + "/serviceplanvisibilities/{guid}"})
    public Map<String, Object> updateServicePlanVisibility(@RequestBody Map param, @PathVariable String guid) throws Exception {
        return commonService.procApiRestTemplate(Constants.V3_URL + "/serviceplanvisibilities/" + guid, HttpMethod.PUT, param, Constants.CF_API, Map.class).getBody();
    }

    /**
     * 서비스 Plan에 Access 등록 되어있는 조직을 삭제한다.
     *
     * @param guid  guid
     * @return boolean boolean
     * @throws Exception the exception
     */
    @DeleteMapping(value = {Constants.V2_URL + "/serviceplanvisibilities/{guid}"})
    public Map<String, Object> deleteServicePlanVisibility(@PathVariable String guid) throws Exception {
        return commonService.procApiRestTemplate(Constants.V3_URL + "/serviceplanvisibilities/" + guid, HttpMethod.DELETE, null, Constants.CF_API, Map.class).getBody();
    }



    /**
     * 서비스 Plan에 Access 등록 되어있는 모든 조직을 삭제한다.
     *
     * @param guid  guid
     * @return boolean boolean
     * @throws Exception the exception
     */
    @DeleteMapping(value = {Constants.V2_URL + "/serviceplanvisibilities/all/{guid}"})
    public Map<String, Object> deleteAllServicePlanVisibility(@PathVariable String guid) throws Exception {
        return commonService.procApiRestTemplate(Constants.V3_URL + "/serviceplanvisibilities/all/" + guid, HttpMethod.DELETE, null, Constants.CF_API, Map.class).getBody();
    }
}
