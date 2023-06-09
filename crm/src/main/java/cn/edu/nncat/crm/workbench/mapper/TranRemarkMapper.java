package cn.edu.nncat.crm.workbench.mapper;

import cn.edu.nncat.crm.workbench.domain.TranRemark;

import java.util.List;

public interface TranRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Tue Dec 06 15:18:53 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Tue Dec 06 15:18:53 CST 2022
     */
    int insert(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Tue Dec 06 15:18:53 CST 2022
     */
    int insertSelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Tue Dec 06 15:18:53 CST 2022
     */
    TranRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Tue Dec 06 15:18:53 CST 2022
     */
    int updateByPrimaryKeySelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Tue Dec 06 15:18:53 CST 2022
     */
    int updateByPrimaryKey(TranRemark record);

    /**
     * 批量保存创建的交易备注
     * @param tranRemarkList
     * @return
     */
    int insertTranRemarkByList(List<TranRemark> tranRemarkList);

    /**
     * 根据交易id查询交易备注列表
     * @param tranId
     * @return
     */
    List<TranRemark> selectTranRemarkListByTranId(String tranId);
}