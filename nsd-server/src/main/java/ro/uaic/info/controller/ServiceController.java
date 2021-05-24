package ro.uaic.info.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ro.uaic.info.HttpMessage.HttpMessage;
import ro.uaic.info.HttpMessage.HttpMessageNotFoundException;
import ro.uaic.info.HttpMessage.HttpMessageRequest;
import ro.uaic.info.HttpMessage.HttpMessageResponse;
import ro.uaic.info.Service.AppInstanceService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller witch handle the required service discovery, registration and monitoring
 *
 * @author Adrian-Valentin Panaintecu
 */
public class ServiceController implements DispatcherController {
    /**
     * The request mapping for controller
     */
    public static String mapRegex = "/service(.*)";
    /**
     * The Http request
     */
    private HttpMessageRequest request;

    /**
     * App instance services
     */
    private AppInstanceService service = new AppInstanceService();

    /**
     * Return a http response from inhered methods. Each method should resolve one path - method pair
     * That is assigned here and formatted after calling method
     * @param httpRequest The http request
     * @return HttpMessageResponse returned and formatted from methods
     * @throws HttpMessageNotFoundException Not found exception
     */
    @Override
    public HttpMessageResponse dispatch(HttpMessageRequest httpRequest) throws HttpMessageNotFoundException {
        String path = httpRequest.getUri();
        String method = httpRequest.getMethod();
        Pattern pattern = Pattern.compile("/service/(.*)");
        Matcher matcher = pattern.matcher(path);
        this.request = httpRequest;
        try {
            if (matcher.matches()) {
                String appName = matcher.group(1);
                if(appName.isEmpty() && method.equals(HttpMessage.METHOD.GET)) {
                    return viewAllServices(appName);
                }
                if (!appName.isEmpty()) {
                    if (method.equals(HttpMessage.METHOD.GET)) {
                        return viewInstanceService(appName);
                    }
                    if (method.equals(HttpMessage.METHOD.POST)) {
                        return registerInstanceService(appName);
                    }
                    if (method.equals(HttpMessage.METHOD.PUT)) {
                        return updateInstanceService(appName);
                    }
                    if (method.equals(HttpMessage.METHOD.DELETE)) {
                        return deleteInstanceService(appName);
                    }
                } else {
                    // todo Method not allowed
                    throw new AssertionError();
                }
            }
        } catch (JsonProcessingException e) {
            throw new HttpMessageNotFoundException();
        }
        throw new HttpMessageNotFoundException();
    }

    public HttpMessageResponse registerInstanceService(String appName) throws JsonProcessingException {
        return HttpMessage.JsonResponse(service.add(request.getBody()));
    }

    public HttpMessageResponse viewInstanceService(String appName) {
        return HttpMessage.JsonResponse("{ \"message\": \"App view\"}");
    }

    public HttpMessageResponse updateInstanceService(String appName) {
        return HttpMessage.JsonResponse("{ \"message\": \"App update\"}");
    }

    public HttpMessageResponse deleteInstanceService(String appName) {
        return HttpMessage.JsonResponse("{ \"message\": \"App delete\"}");
    }

    public HttpMessageResponse viewAllServices(String appName) {
        return HttpMessage.JsonResponse("{ \"message\": \"Apps view\"}");
    }

}
