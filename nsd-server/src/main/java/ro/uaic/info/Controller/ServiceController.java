package ro.uaic.info.Controller;

import ro.uaic.info.HttpMessage.HttpMessageNotFoundException;
import ro.uaic.info.HttpMessage.HttpMessageRequest;
import ro.uaic.info.HttpMessage.HttpMessageResponse;

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
     * Return a http response from inhered methods. Each method should resolve one path - method pair
     * That is assigned here and formatted after calling method
     * @param httpRequest The http request
     * @return HttpMessageResponse returned and formatted from methods
     * @throws HttpMessageNotFoundException Not found exception
     */
    @Override
    public HttpMessageResponse dispatch(HttpMessageRequest httpRequest) throws HttpMessageNotFoundException {

        throw new HttpMessageNotFoundException();
    }

}
