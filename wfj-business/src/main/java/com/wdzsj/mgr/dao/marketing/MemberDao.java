package com.wdzsj.mgr.dao.marketing;

import com.wdzsj.mgr.entity.marketing.Member;

import java.util.List;
import java.util.Map;

/**
 * 会员
 */
public interface MemberDao {
    List<Member> selectByPrimaryKey(String cid);

    int insert(Member member);

    int insertSelective(Member member);

    int updateByCidSelective(Member member);
}