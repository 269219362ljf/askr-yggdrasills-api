package askr.midgard.service;

import askr.entity.StockCodeList;
import askr.model.StockApiListDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private static Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private AggregationAPIService aggregationAPIService;


    @Override
    public List<StockCodeList> queryCodeDatas() {
        return aggregationAPIService.queryCodeDatas();
    }

    @Override
    public List<StockApiListDataDto> queryTransactionDataToDay() {
        return aggregationAPIService.queryTransactionDataToDay();
    }

    @Override
    public List<StockApiListDataDto> queryTransactionDataHis() {
        return null;
    }
}
