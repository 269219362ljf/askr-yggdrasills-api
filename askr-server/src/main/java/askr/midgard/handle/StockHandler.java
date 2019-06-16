package askr.midgard.handle;


import askr.midgard.iface.SocketHandle;
import askr.midgard.service.StocketApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockHandler implements SocketHandle {

    private static Logger logger = LoggerFactory.getLogger(StockHandler.class);

    @Autowired
    private StocketApiService stocketApiService;

    @Override
    public void handle() {
        stocketApiService.refreshCodeList();
    }








}
