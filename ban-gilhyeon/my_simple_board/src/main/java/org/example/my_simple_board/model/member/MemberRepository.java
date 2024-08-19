package org.example.my_simple_board.model.member;

import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.List;

public interface MemberRepository {
    int insertMember(MemberDTO member) throws SQLException;
    int updateMember(MemberDTO member) throws SQLException;
    int deleteMember(MemberDTO member) throws SQLException;
    List<MemberDTO> selectAllMembers() throws SQLException;
    MemberDTO selectOneMember(String user_id) throws SQLException;
}
