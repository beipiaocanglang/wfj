package com.wdzsj.mgr.service.marketing;

import com.wdzsj.mgr.dao.marketing.MemberDao;
import com.wdzsj.mgr.entity.marketing.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员服务
 */
@Service
public class MemberService {
    @Resource
    private MemberDao memberDao;

    public int insertSelective(Member member){
        return memberDao.insertSelective(member);
    }

    public List<Member>  selectByPrimaryKey(String cid){
        List<Member> member = memberDao.selectByPrimaryKey(cid);
        return member;
    }

    public int updateByCidSelective(Member member){
        return memberDao.updateByCidSelective(member);
    }
}
