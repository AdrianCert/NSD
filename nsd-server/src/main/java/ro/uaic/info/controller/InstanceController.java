package ro.uaic.info.controller;

import ro.uaic.info.HttpMessage.HttpMessage;
import ro.uaic.info.HttpMessage.HttpMessageNotFoundException;
import ro.uaic.info.HttpMessage.HttpMessageRequest;
import ro.uaic.info.HttpMessage.HttpMessageResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller witch handle the required service instance
 *
 * @author Adrian-Valentin Panaintecu
 */
public class InstanceController implements DispatcherController {
    /**
     * The request mapping for controller
     */
    public static String mapRegex = "/instance(.*)";

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
        Pattern pattern = Pattern.compile("/instance/(.*)");
        Matcher matcher = pattern.matcher(path);
        if (matcher.matches() && method.equals(HttpMessage.METHOD.GET)) {
            String appName = matcher.group(1);
            return appName.isEmpty() ? getAppList() : getInstance(appName);
        }

        throw new HttpMessageNotFoundException();
    }

    public HttpMessageResponse getAppList() {
        return HttpMessage.JsonResponse("{ \"message\": \"Apps list\"}");
    }

    public HttpMessageResponse getInstance(String appName) {
        return HttpMessage.JsonResponse("{ \"message\": \"App instance\"}");
    }
}
