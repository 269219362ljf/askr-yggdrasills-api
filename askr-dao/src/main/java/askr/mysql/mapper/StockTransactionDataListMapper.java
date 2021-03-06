package askr.mysql.mapper;

import askr.entity.StockTransactionDataList;
import askr.entity.StockTransactionDataListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockTransactionDataListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_transaction_data_list
     *
     * @mbg.generated
     */
    long countByExample(StockTransactionDataListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_transaction_data_list
     *
     * @mbg.generated
     */
    int deleteByExample(StockTransactionDataListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_transaction_data_list
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_transaction_data_list
     *
     * @mbg.generated
     */
    int insert(StockTransactionDataList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_transaction_data_list
     *
     * @mbg.generated
     */
    int insertSelective(StockTransactionDataList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_transaction_data_list
     *
     * @mbg.generated
     */
    List<StockTransactionDataList> selectByExample(StockTransactionDataListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_transaction_data_list
     *
     * @mbg.generated
     */
    StockTransactionDataList selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_transaction_data_list
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") StockTransactionDataList record, @Param("example") StockTransactionDataListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_transaction_data_list
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") StockTransactionDataList record, @Param("example") StockTransactionDataListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_transaction_data_list
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StockTransactionDataList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_transaction_data_list
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StockTransactionDataList record);
}