package cn.edu.nncat.crm.workbench.mapper;

import cn.edu.nncat.crm.workbench.domain.FunnelVO;
import cn.edu.nncat.crm.workbench.domain.Tran;

import java.util.List;

public interface TranMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Sun Dec 04 20:58:34 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Sun Dec 04 20:58:34 CST 2022
     */
    int insert(Tran record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Sun Dec 04 20:58:34 CST 2022
     */
    int insertSelective(Tran record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Sun Dec 04 20:58:34 CST 2022
     */
    Tran selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Sun Dec 04 20:58:34 CST 2022
     */
    int updateByPrimaryKeySelective(Tran record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Sun Dec 04 20:58:34 CST 2022
     */
    int updateByPrimaryKey(Tran record);

    int insertTran(Tran tran);

    List<Tran> selectTranByList();

    Tran selectTranById(String tranId);

    /**
     * 查询交易表中各阶段的数据量
     * @return
     */
    List<FunnelVO> selectCountOfTranGroupByStage();
}