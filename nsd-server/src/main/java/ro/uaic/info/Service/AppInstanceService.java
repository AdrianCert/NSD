package ro.uaic.info.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ro.uaic.info.entity.AppInstance;
import ro.uaic.info.repository.AppInstanceRepository;

public class AppInstanceService {

    private AppInstanceRepository repository = AppInstanceRepository.get();
    private ObjectMapper objectMapper = new ObjectMapper();

    public String add(String json) throws JsonProcessingException {
        AppInstance model = objectMapper.readValue(json, AppInstance.class);
        repository.add(model);
        return json;
    }
}
