package org.example.model.service;

import org.example.model.dto.MemberDTO;
import org.example.model.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public String login(String user_id, String user_pw) throws SQLException {
        return memberRepository.selectOne(user_id,user_pw);
    }

    public int join(MemberDTO memberDTO) throws SQLException {
        return memberRepository.insert(memberDTO);
    }
}
