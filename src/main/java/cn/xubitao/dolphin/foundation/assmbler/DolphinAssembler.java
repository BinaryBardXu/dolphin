package cn.xubitao.dolphin.foundation.assmbler;

import cn.xubitao.dolphin.foundation.resource.ErrorResource;
import cn.xubitao.dolphin.foundation.resource.RestResource;
import org.springframework.hateoas.ResourceSupport;
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

    protected Integer[] pathVariables;

    public abstract RestResource toRestResource(Object o, Integer... pathVariables) throws Exception;

    protected List<ResourceSupport> buildResources(List domains, DolphinAssembler dolphinAssembler, Integer... pathVariables) {
        dolphinAssembler.pathVariables = pathVariables;
        List<ResourceSupport> providerResources = new ArrayList<ResourceSupport>();
        for (Object domain : domains) {
            ResourceSupport providerResource = dolphinAssembler.toResource(domain, pathVariables);
            providerResources.add(providerResource);
        }
        return providerResources;
    }

    public RestResource toResource(Object o) {
        return null;
    }

    public ResourceSupport toResource(Object domain, Integer[] pathVariables) {
        try {
            this.pathVariables = pathVariables;
            return toRestResource(domain, pathVariables);
        } catch (Exception e) {
            return ErrorResource.error(e.getMessage());
        }
    }
}
