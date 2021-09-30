package configuration;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

public class AddressConfiguration {

    private final static Logger LOG = LogManager.getRootLogger();
    private static final String ERROR_LOG_LEVER = "error";
    public static final String KILL_NODE_CMD = "taskkill /F /IM node.exe";
    private static AppiumDriverLocalService appiumDriverLocalService;

    private AddressConfiguration(){

    }

    public static AppiumDriverLocalService getAppiumDriverLocalService(int port){
        if(appiumDriverLocalService == null) startService(port);
        return appiumDriverLocalService;
    }

    public static void startService(int port){
        makePortAvailableIfOccupied(port);
        appiumDriverLocalService = new AppiumServiceBuilder()
                .withIPAddress(ConfigurationReader.get().appiumAddress())
                .usingPort(port)
                .withArgument(SESSION_OVERRIDE)
                .withArgument(LOG_LEVEL, ERROR_LOG_LEVER)
                .build();
        appiumDriverLocalService.start();
        LOG.info("Appium server started on port {}", port);
    }

    public static void stopService(){
        Optional.ofNullable(appiumDriverLocalService).ifPresent(service ->{
            service.stop();
            LOG.info("Appium server was stopped");
        });
    }

    private static void makePortAvailableIfOccupied(int port){
        if (!isPortFree(port)){
            try {
                Runtime.getRuntime().exec(KILL_NODE_CMD);
            } catch (IOException e) {
                LOG.error("Couldn't execute runtime command, message: {}", e.getMessage());
            }
        }
    }

    private static boolean isPortFree(int port){
        boolean isFree = true;
        try(ServerSocket ignored = new ServerSocket(port)) {
            LOG.info("Specified port {} is available", port);
        }catch (Exception e){
            isFree = false;
            LOG.warn("Specified port {} is occupied by some process, that will be terminated", port);
        }
        return isFree;
    }
}
