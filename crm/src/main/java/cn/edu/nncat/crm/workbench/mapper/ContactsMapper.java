package cn.edu.nncat.crm.workbench.mapper;

import cn.edu.nncat.crm.commons.contants.Contants;
import cn.edu.nncat.crm.workbench.domain.Contacts;

import java.util.List;

public interface ContactsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Sat Dec 03 20:55:23 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Sat Dec 03 20:55:23 CST 2022
     */
    int insert(Contacts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Sat Dec 03 20:55:23 CST 2022
     */
    int insertSelective(Contacts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Sat Dec 03 20:55:23 CST 2022
     */
    Contacts selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Sat Dec 03 20:55:23 CST 2022
     */
    int updateByPrimaryKeySelective(Contacts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Sat Dec 03 20:55:23 CST 2022
     */
    int updateByPrimaryKey(Contacts record);

    /**
     * 保存创建的联系人
     * @param contacts
     * @return
     */
    int insertContacts(Contacts contacts);

    /**
     * 用name模糊查询联系人列表
     * @param fullname
     * @return
     */
    List<Contacts> selectContactsListByName(String fullname);
}