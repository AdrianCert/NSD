package ro.uaic.info.repository;

import ro.uaic.info.entity.AppInstance;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AppInstanceRepository {
    private static AppInstanceRepository instanceRepository = null;
    private final List<AppInstance> appsInstance = new LinkedList<>();
    private final HashMap<Integer, AppInstance> hashIndexer = new HashMap<>();
    private final HashMap<String, Integer> appsName = new HashMap<>();

    private AppInstanceRepository() {
    }

    public static AppInstanceRepository get() {
        if(instanceRepository == null) {
            instanceRepository = new AppInstanceRepository();
        }
        return instanceRepository;
    }

    private void appNameSync(String appName, Integer increment) {
        if(!appsName.containsKey(appName)) {
            appsName.put(appName, 0);
        }
        appsName.put(appName, appsName.get(appName) + increment);

        if(appsName.get(appName) < 0) {
            appsName.put(appName, 0);
        }
    }

    public void add(AppInstance appInstance) {
        appsInstance.add(appInstance);
        appNameSync(appInstance.getAppName(), 1);
    }

    public void update(AppInstance appInstance) {
        appsInstance.remove(hashIndexer.get(appInstance.getId()));
        appsInstance.add(appInstance);
    }

    public void delete(AppInstance appInstance) {
        appNameSync(appInstance.getAppName(), -1);
        appsInstance.remove(appInstance);
    }

    public HashMap<String, Integer> getAppsName() {
        return appsName;
    }
}
