package askr.midgard.service;

import askr.entity.StockCodeList;
import askr.model.StockApiListDataDto;


import java.util.List;

public interface StockService {

    List<StockCodeList> queryCodeDatas();

    List<StockApiListDataDto> queryTransactionDataToDay();

    List<StockApiListDataDto> queryTransactionDataHis();




}
