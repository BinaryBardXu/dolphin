package cn.xubitao.dolphin.foundation.assmbler;

import cn.xubitao.dolphin.foundation.resource.ErrorResource;
import cn.xubitao.dolphin.foundation.resource.RestResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xubitao on 1/2/16.
 */
public abstract class DolphinAssembler extends ResourceAssemblerSupport<Object, RestResource> {
    public DolphinAssembler(Class<?> controllerClass, Class<RestResource> resourceType) {
        super(controllerClass, resourceType);
    }

    public abstract RestResource toRestResource(Object o) throws Exception;

    public RestResource toResource(Object o) {
        try {
            return toRestResource(o);
        } catch (Exception e) {
            return ErrorResource.error(e.getMessage());
        }
    }
    protected List<RestResource> buildResources(List domains, DolphinAssembler dolphineAssembler) {
        List<RestResource> providerResources = new ArrayList<RestResource>();
        for (Object domain : domains) {
            RestResource providerResource = dolphineAssembler.toResource(domain);
            providerResources.add(providerResource);
        }
        return providerResources;
    }
}
