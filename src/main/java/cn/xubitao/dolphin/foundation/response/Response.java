package cn.xubitao.dolphin.foundation.response;

import cn.xubitao.dolphin.foundation.assmbler.DolphinAssembler;
import cn.xubitao.dolphin.foundation.exceptions.ClientException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by xubitao on 1/2/16.
 */
public class Response {
    private ResourceSupport restResource;
    private HttpStatus httpStatus = HttpStatus.OK;

    public ResourceSupport getRestResource() {
        return restResource;
    }

    public void setRestResource(ResourceSupport restResource) {
        this.restResource = restResource;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public static HttpEntity<ResourceSupport> build(Object domain, DolphinAssembler resourceAssembler) {
        Response response = new Response();
        ResourceSupport domainResource = resourceAssembler.toResource(domain, new Integer[0]);
        ResourceSupport resource = new ResourceSupport();
        setResponse(domain, response, domainResource, resource);
        return new ResponseEntity<ResourceSupport>(response.getRestResource(), response.getHttpStatus());
    }

    public static HttpEntity<ResourceSupport> build(Object domain, DolphinAssembler resourceAssembler, Object... pathOrRequestVariables) {
        Response response = new Response();
        ResourceSupport domainResource = resourceAssembler.toResource(domain, pathOrRequestVariables);
        ResourceSupport resource = new ResourceSupport();
        setResponse(domain, response, domainResource, resource);
        return new ResponseEntity<ResourceSupport>(response.getRestResource(), response.getHttpStatus());
    }

    private static void setResponse(Object domain, Response response, ResourceSupport domainResource, ResourceSupport resource) {
        if (domain == null) {
            response.setHttpStatus(HttpStatus.NOT_FOUND);
            resource.add(domainResource.getLinks());
            response.setRestResource(resource);
        } else {
            response.setRestResource(domainResource);
        }
    }

    public static ResponseEntity ok() {
        return new ResponseEntity(HttpStatus.OK);
    }

    public static ResponseEntity ok(ResourceSupport resourceSupport) {
        return new ResponseEntity(resourceSupport, HttpStatus.OK);
    }

    public static ResponseEntity created(ResourceSupport resourceSupport) {
        return new ResponseEntity(resourceSupport, HttpStatus.CREATED);
    }

    public static JSONObject error(Exception exception) {
        JSONObject message = new JSONObject();
        message.put("message", exception.getMessage());
        return message;
    }
}
