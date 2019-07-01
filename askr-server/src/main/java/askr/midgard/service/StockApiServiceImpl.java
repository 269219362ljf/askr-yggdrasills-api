package askr.midgard.service;

import askr.entity.StockCodeList;
import askr.entity.StockTransactionDataList;
import askr.model.StockApiListDataDto;
import askr.mysql.mapper.StockCodeListMapper;
import askr.mysql.mapper.StockTransactionDataListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class StockApiServiceImpl implements StockApiService {

    private static Logger logger = LoggerFactory.getLogger(StockApiServiceImpl.class);

    @Autowired
    private StockService stocketService;


    @Resource
    private StockCodeListMapper stockCodeListMapper;

    @Resource
    private StockTransactionDataListMapper stockTransactionDataListMapper;

    @Override
    public void refreshCodeList() {
        try {
            List<StockCodeList> stockCodeLists=stocketService.queryCodeDatas();
            if(stockCodeLists.size()>0){
                stockCodeLists.stream().forEach(stockCodeListMapper::insertSelective);
            }
            logger.info("stockCodeListMapper total count:{}",stockCodeListMapper.countByExample(null));
        }catch (Exception e){
            logger.error("SocketApiService.refreshCodeList is error:{}", e);
        }
    }

    @Override
    public void refreshTransactionDataToday() {
        try {
            List<StockApiListDataDto> stockApiListDataDtos=stocketService.queryTransactionDataToDay();
            if(stockApiListDataDtos.size()>0){
                stockApiListDataDtos.stream().forEach(item->{
                    StockTransactionDataList stockTransactionDataList=new StockTransactionDataList();
                    stockTransactionDataList.setStockCode(item.getSymbol());
                    setData(stockTransactionDataList::setTrade,item.getTrade());
                    setData(stockTransactionDataList::setPricechange,item.getPricechange());
                    setData(stockTransactionDataList::setChangepercent,item.getChangepercent());
                    setData(stockTransactionDataList::setBuy,item.getBuy());
                    setData(stockTransactionDataList::setSell,item.getSell());
                    setData(stockTransactionDataList::setSettlement,item.getSettlement());
                    setData(stockTransactionDataList::setTrade,item.getTrade());
                    setData(stockTransactionDataList::setOpen,item.getOpen());
                    setData(stockTransactionDataList::setHigh,item.getHigh());
                    setData(stockTransactionDataList::setLow,item.getLow());
                    stockTransactionDataList.setVolume(item.getVolume());
                    stockTransactionDataList.setAmount(item.getAmount());
                    stockTransactionDataListMapper.insertSelective(stockTransactionDataList);
                });
            }
            logger.info("stockCodeListMapper total count:{}",stockCodeListMapper.countByExample(null));
        }catch (Exception e){
            logger.error("SocketApiService.refreshCodeList is error:{}", e);
        }
    }

    private void setData(SetMethod method,Double data){
        if(data==null||data==0.0){
            return;
        }else{
            method.set(new BigDecimal(data));
        }
    }

    private interface SetMethod{
        void set(BigDecimal data);
    }
}
