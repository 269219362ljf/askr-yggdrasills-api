package askr.midgard.handle;


import askr.midgard.iface.SocketHandle;
import askr.midgard.service.SocketApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocketHandler implements SocketHandle {

    private static Logger logger = LoggerFactory.getLogger(SocketHandler.class);

    @Autowired
    private SocketApiService socketApiService;

    @Override
    public void handle() {
        socketApiService.testGetRequest();
    }








}
