package askr.mysql.mapper;

import askr.entity.StockCodeList;
import askr.entity.StockCodeListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockCodeListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_code_list
     *
     * @mbg.generated
     */
    long countByExample(StockCodeListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_code_list
     *
     * @mbg.generated
     */
    int deleteByExample(StockCodeListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_code_list
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_code_list
     *
     * @mbg.generated
     */
    int insert(StockCodeList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_code_list
     *
     * @mbg.generated
     */
    int insertSelective(StockCodeList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_code_list
     *
     * @mbg.generated
     */
    List<StockCodeList> selectByExample(StockCodeListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_code_list
     *
     * @mbg.generated
     */
    StockCodeList selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_code_list
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") StockCodeList record, @Param("example") StockCodeListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_code_list
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") StockCodeList record, @Param("example") StockCodeListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_code_list
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StockCodeList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_code_list
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StockCodeList record);
}