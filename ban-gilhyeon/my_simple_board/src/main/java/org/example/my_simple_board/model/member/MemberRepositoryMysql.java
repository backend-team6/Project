package org.example.my_simple_board.model.member;

import org.example.my_simple_board.model.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberRepositoryMysql implements MemberRepository {
    private MemberRepositoryMysql(){}
    private static MemberRepository memberInstance = new MemberRepositoryMysql();
    public static MemberRepository getInstance(){return memberInstance;}

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public int insertMember(MemberDTO member) throws SQLException {
        int result = 0;
        try{
            String sql = "insert into member_tb(user_id,user_pw) values(?,?)";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, member.getId());
            ps.setString(2, member.getPw());
            result = ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("member insert error");
            throw e;
        }finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }

    @Override
    public int updateMember(MemberDTO member) throws SQLException {
        return 0;
    }

    @Override
    public int deleteMember(MemberDTO member) throws SQLException {
        return 0;
    }

    @Override
    public List<MemberDTO> selectAllMembers() throws SQLException {
        List<MemberDTO> list = new ArrayList<MemberDTO>();
        try{
            String sql = "select user_id, user_pw from member_tb";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(makeMemberDTO(rs));
            }
        } catch (SQLException e) {
            System.out.println("member selectAll error");
            throw e;
        } finally {
            DBUtil.close(ps, conn);
        }
        return list;
    }

    @Override
    public MemberDTO selectOneMember(String user_id) throws SQLException {
        MemberDTO member = null;
        try{
            String sql = "select user_id, user_pw from member_tb where user_id='"+user_id+"'";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                member = makeMemberDTO(rs);
            }
        }catch (SQLException e){
            System.out.println("member selectOne error");
            throw e;
        }
        return member;
    }

    private MemberDTO makeMemberDTO(ResultSet rs) throws SQLException {
        MemberDTO member = new MemberDTO();
        member.setId(rs.getString("user_id"));
        member.setPw(rs.getString("user_pw"));
        return member;
    }
}
